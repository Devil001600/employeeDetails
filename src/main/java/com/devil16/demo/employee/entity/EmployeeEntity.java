package com.devil16.demo.employee.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EmployeeEntity class -
 * 
 * this is the Employee Entity class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 * 
 * @see com.devil16.demo.employee.entity.EmployeeDto
 * @see com.devil16.demo.employee.dtomapper.EmployeeDtoMapper
 */

/*
 * Entity objects are the POJO representation of DBTables 
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EmployeeEntity {
	
	private String commit_Id;
	private String first_Name;
	private String last_Name;
	private String type;
	private String cost_Bill_Link;
	private String cost_Settle_Link;
	private String designation;
	private String team;
	private String line_Of_Business;
	private String email_Id;
	private String phone;
	private String status;
	private LocalDate date_Of_Joining;
	private LocalDate last_Working_Date;
	
}