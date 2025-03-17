package com.king.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.dto.CounsellorDTO;
import com.king.dto.DashboardDTO;
import com.king.dto.FilterEnquiriesDTO;
import com.king.dto.ViewEnquiriesDTO;
import com.king.repo.EnquiryRepo;
@Service
public class EnquiryServiceImpl implements IEnquiryService {

	@Autowired
	private EnquiryRepo eRepo;
	@Override
	public DashboardDTO getDashBoardInfo(Integer counsellorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEnquiry(ViewEnquiriesDTO enqDTO, CounsellorDTO counsellorId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ViewEnquiriesDTO> getEnquiries(Integer counsellorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewEnquiriesDTO> getEnquiries(FilterEnquiriesDTO filterDTO, Integer counsellorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewEnquiriesDTO getEnquiriesById(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}

}
