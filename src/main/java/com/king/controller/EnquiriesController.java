package com.king.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.king.dto.FilterEnquiriesDTO;
import com.king.dto.ViewEnquiriesDTO;
import com.king.service.IEnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class EnquiriesController {

	
	@Autowired
	private IEnquiryService enqService;
	
	
	@GetMapping("/edit-enquiry")
	public String editEnquriy(@RequestParam("enqId") Integer enqId, 
								Model model) {
		
		ViewEnquiriesDTO enqDto = enqService.getEnquiriesById(enqId);
		model.addAttribute("enquiry", enqDto);

		return "add-enquiry";
	}
	
	@GetMapping("/enquiry-page")
	public String loadEnqPage(Model model) {

		ViewEnquiriesDTO enqDto = new ViewEnquiriesDTO();
		model.addAttribute("enquiry", enqDto);

		return "add-enquiry";
	}
	
	@PostMapping("/add-enquiry")
	public String addEnquiry(HttpServletRequest req, 
							@ModelAttribute("enquiry")ViewEnquiriesDTO enquiry, 
							Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		boolean status = enqService.addEnquiry(enquiry, counsellorId);
		if(status) {
			model.addAttribute("smsg", "Enquiry Saved");
		}else {
			model.addAttribute("emsg", "Failed to save enquiry");
		}
		
		return "add-enquiry";
	}
	
	@GetMapping("/view-enquiries")
	public String getEnquiries(HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		List<ViewEnquiriesDTO> enqList = enqService.getEnquiries(counsellorId);
		
		model.addAttribute("enquiries", enqList);	
		
		FilterEnquiriesDTO filterDTO = new FilterEnquiriesDTO();
		model.addAttribute("filterDTO", filterDTO);
		
		return "view-enquiries";
	}
	
	@PostMapping("/filter-enquiries")
	public String filterEnquires(HttpServletRequest req, @ModelAttribute("filterDTO") FilterEnquiriesDTO filterDTO, Model model) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		List<ViewEnquiriesDTO> enqList = enqService.getEnquiries(filterDTO, counsellorId);
		
		model.addAttribute("enquiries", enqList);	
		
		return "view-enquiries";
	}

}
