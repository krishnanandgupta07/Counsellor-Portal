package com.king.service;

import com.king.dto.CounsellorDTO;

public interface ICounsellorService {

	public CounsellorDTO login(CounsellorDTO counsellorDTO);
	public boolean uniqueEmail(String email);
	public boolean register(CounsellorDTO counsellorDTO);
}
