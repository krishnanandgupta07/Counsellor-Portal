package com.king.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.king.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry,Integer> {

	public List<Enquiry> findByCounsellorCounsellorId(Integer counsellorId);
}
