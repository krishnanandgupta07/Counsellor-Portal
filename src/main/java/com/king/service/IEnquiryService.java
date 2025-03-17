package com.king.service;

import java.util.List;

import com.king.dto.CounsellorDTO;
import com.king.dto.DashboardDTO;
import com.king.dto.FilterEnquiriesDTO;
import com.king.dto.ViewEnquiriesDTO;


public interface IEnquiryService {

	public DashboardDTO getDashBoardInfo(Integer counsellorId);
	public boolean addEnquiry(ViewEnquiriesDTO enqDTO ,CounsellorDTO counsellorId);
	public List<ViewEnquiriesDTO> getEnquiries(Integer counsellorId);
	public List<ViewEnquiriesDTO> getEnquiries(FilterEnquiriesDTO filterDTO,Integer counsellorId);
	public ViewEnquiriesDTO getEnquiriesById(Integer enqId);
}
