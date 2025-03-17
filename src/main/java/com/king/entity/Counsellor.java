package com.king.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="counsellor_tbl")
@Setter
@Getter
public class Counsellor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer counsellorId;
	private String name;
	private String emailId;
	private String pwd;
	private String phNo;
	@OneToMany(mappedBy = "counsellor",cascade = CascadeType.ALL)
	private List<Enquiry> enquiries;

}
