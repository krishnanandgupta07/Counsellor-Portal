package com.king.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.dto.CounsellorDTO;
import com.king.repo.CounsellorRepo;
@Service
public class ConselllorServiceImpl implements ICounsellorService {
	@Autowired
	private CounsellorRepo cRepo;

	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) {
		
		return null;
	}

	@Override
	public boolean uniqueEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
