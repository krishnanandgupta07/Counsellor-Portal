package com.king.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="enquiry_tbl")
@Setter
@Getter
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer enquiryId;
	private String stuName;
	private String phNo;
	private String classMode;
	private String courses;
	private String enqStatus;
	@ManyToOne
	@JoinColumn(name=" counsellor_Id")
	private Counsellor counsellor;
	

}
