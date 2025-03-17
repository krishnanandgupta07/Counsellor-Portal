package com.king.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.king.entity.Counsellor;

public interface CounsellorRepo extends JpaRepository< Counsellor,Integer> {

}
