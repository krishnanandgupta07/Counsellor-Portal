package com.king.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.king.dto.CounsellorDTO;
import com.king.dto.DashboardDTO;
import com.king.dto.FilterEnquiriesDTO;
import com.king.dto.ViewEnquiriesDTO;
import com.king.entity.Counsellor;
import com.king.entity.Enquiry;
import com.king.repo.CounsellorRepo;
import com.king.repo.EnquiryRepo;
@Service
public class EnquiryServiceImpl implements IEnquiryService {

	@Autowired
	private EnquiryRepo enqRepo;
	@Autowired
	private CounsellorRepo consRepo;
	@Override
	public DashboardDTO getDashBoardInfo(Integer counsellorId) {
		
		List<Enquiry> enqList=enqRepo.findByCounsellorCounsellorId(counsellorId);
		DashboardDTO dto=new DashboardDTO();
		
				int openCnt = enqList.stream()
													  .filter(enq->enq.getEnqStatus().equals("OPEN"))
													 .collect(Collectors.toList()).size();
		
				int enrollCnt = enqList.stream()
													 .filter(enq->enq.getEnqStatus().equals("ENROLLED"))
													 .collect(Collectors.toList()).size();
		
					int lostCnt = enqList.stream()
													    .filter(enq->enq.getEnqStatus().equals("LOST"))
													    .collect(Collectors.toList()).size();
		
					dto.setTotalEnquiries(enqList.size());
					dto.setOpenEnquiries(openCnt);
					dto.setEnrolledEnquiries(enrollCnt);
					dto.setLostEnquiries(lostCnt);
		
						return dto;
	
	}

	@Override
	public boolean addEnquiry(ViewEnquiriesDTO enqDTO, Integer counsellorId) {

			Enquiry entity=new Enquiry();
			BeanUtils.copyProperties(enqDTO, entity);
			//Setting foreign key to enquiry object
			Optional<Counsellor> byId=consRepo.findById(counsellorId);
			if(byId.isPresent()) {
				Counsellor counsellor=byId.get();
				entity.setCounsellor(counsellor);
			}
			
			Enquiry save= enqRepo.save(entity);
			return save.getEnquiryId()!=null;
	}

	@Override
	public List<ViewEnquiriesDTO> getEnquiries(Integer counsellorId) {

			List<ViewEnquiriesDTO> enqDtoList=new ArrayList<ViewEnquiriesDTO>();
			List<Enquiry> enqList= enqRepo.findByCounsellorCounsellorId(counsellorId);
			for(Enquiry entity:enqList) {
				ViewEnquiriesDTO dto = new ViewEnquiriesDTO();
				BeanUtils.copyProperties(entity, dto);
				enqDtoList.add(dto);
			}
			return enqDtoList;
	}

	@Override
	public List<ViewEnquiriesDTO> getEnquiries(FilterEnquiriesDTO filterDTO, Integer counsellorId) {
		
			Enquiry entity= new Enquiry();
			
			if(filterDTO.getClassMode()!=null && !filterDTO.getClassMode().equals("")){
				entity.setClassMode(filterDTO.getClassMode());
			}
			
			if(filterDTO.getCourses()!=null && !filterDTO.getCourses().equals("")){
				entity.setCourses(filterDTO.getCourses());
			}
			
			if(filterDTO.getCourseStatus()!=null && !filterDTO.getCourseStatus().equals("")){
				entity.setEnqStatus(filterDTO.getCourseStatus());
			}	
				Counsellor counsellor=new Counsellor();
				counsellor.setCounsellorId(counsellorId);
				entity.setCounsellor(counsellor);
				
				Example<Enquiry> of=Example.of(entity);
				List<Enquiry> enqList=enqRepo.findAll(of);
				
				List<ViewEnquiriesDTO> enqDtoList= new ArrayList<ViewEnquiriesDTO>();
				for(Enquiry enq: enqList) {
					ViewEnquiriesDTO dto=new ViewEnquiriesDTO();
					BeanUtils.copyProperties(enq, dto);
					enqDtoList.add(dto);
			}
		return enqDtoList;
	}

	@Override
	public ViewEnquiriesDTO getEnquiriesById(Integer enqId) {
	
			Optional<Enquiry> byId=enqRepo.findById(enqId);
			if(byId.isPresent()) {
				Enquiry entity= byId.get();
				ViewEnquiriesDTO dto= new ViewEnquiriesDTO();
				BeanUtils.copyProperties(entity, dto);
				return dto;
			}
		return null;
	}

}
