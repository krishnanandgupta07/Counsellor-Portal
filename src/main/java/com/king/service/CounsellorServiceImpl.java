package com.king.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.dto.CounsellorDTO;
import com.king.entity.Counsellor;
import com.king.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements ICounsellorService {
	@Autowired
	private CounsellorRepo consRepo;

	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) {
		Counsellor entity=consRepo.findByEmailAndPwd(counsellorDTO.getEmail(),counsellorDTO.getPwd());
		if(entity!=null) {
			//copy entity obj data into dto  obj and return dto obj
			
			CounsellorDTO dto =new CounsellorDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

	@Override
	public boolean uniqueEmail(String email) {
		Counsellor entity = consRepo.findByEmail(email);
		
		return entity==null;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {

		//copy dto object data to entity obj data
		Counsellor entity= new Counsellor();
		BeanUtils.copyProperties(counsellorDTO, entity);
		
		Counsellor savedEntity=consRepo.save(entity);
		
		
		return null != savedEntity.getCounsellorId();
	}

}
