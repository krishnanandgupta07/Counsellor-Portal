package com.king.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.king.dto.CounsellorDTO;
import com.king.dto.DashboardDTO;
import com.king.service.CounsellorServiceImpl;
import com.king.service.EnquiryServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private EnquiryServiceImpl enqService;
	@Autowired
	private CounsellorServiceImpl consService;
	

	@GetMapping("/")
	public String index(Model model) {
		CounsellorDTO cdto= new CounsellorDTO();
		model.addAttribute("counsellor",cdto);
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		session.invalidate();

		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor", cdto);

		return "index";
	}
	
	@PostMapping("/login")
	public String handledLogin(HttpServletRequest req, CounsellorDTO counsellor, Model model) {
		CounsellorDTO counsellorDto= consService.login(counsellor);
		
		if (counsellorDto == null) {
			CounsellorDTO cdto= new CounsellorDTO();
			model.addAttribute("counsellor",cdto);
			model.addAttribute("emsg","Invalid Credential");
			return "index";
			
		}else
		{
			Integer counsellorId= counsellorDto.getCounsellorId();
			//store counsellor id in http session object
			
			HttpSession session= req.getSession(true);
			session.setAttribute("counsellorId", counsellorId);
			
			DashboardDTO dashboardDto= enqService.getDashBoardInfo(counsellorId);
			model.addAttribute("dashboardDto",dashboardDto);
			
			return "dashboard";
		}
	}
	
	
	@GetMapping("/register")
	public String registerPage(Model model) {

		CounsellorDTO cdto = new CounsellorDTO();
		model.addAttribute("counsellor", cdto);

		return "register";
	}

	@PostMapping("/register")
	public String handleRegister(@ModelAttribute("counsellor") CounsellorDTO counsellor, Model model) {
		boolean unique = consService.uniqueEmail(counsellor.getEmail());
		if (unique) {
			boolean register = consService.register(counsellor);
			if (register) {
				model.addAttribute("smsg", "Registration Success");
			} else {
				model.addAttribute("emsg", "Registration Failed");
			}
		} else {
			model.addAttribute("emsg", "Enter Unique Email");
		}
		return "register";
	}

	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		DashboardDTO dashboardDto = enqService.getDashBoardInfo(counsellorId);

		model.addAttribute("dashboardDto", dashboardDto);

		return "dashboard";

	}
}
