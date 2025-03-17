package com.king.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry,Integer> {

}
