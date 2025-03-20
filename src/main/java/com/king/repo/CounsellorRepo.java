package com.king.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.king.entity.Counsellor;

public interface CounsellorRepo extends JpaRepository< Counsellor,Integer> {
	
	public Counsellor findByEmailAndPwd(String email,String pwd);
	public Counsellor findByEmail(String email);

}
