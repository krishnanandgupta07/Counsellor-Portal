package com.king.dto;

import lombok.Data;

@Data
public class DashboardDTO {

	private Integer totalEnquiries;
	private Integer openEnquiries;
	private Integer enrolledEnquiries;
	private Integer lostEnquiries;
}
