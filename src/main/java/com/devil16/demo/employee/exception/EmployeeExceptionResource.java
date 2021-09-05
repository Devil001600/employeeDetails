package com.devil16.demo.employee.exception;

/**
 * EmployeeExceptionResource enum - 
 * 
 * lists all the components of the EmployeeDetails service
 * 
 * the appropriate one shall be reported in case of exceptions
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 */
public enum EmployeeExceptionResource {
	
	//Service
	EmployeeService,
	
	//Function
	GetEmployeeById,
	
	//Mapper
	EmployeeDtoToEmployeeEntityMapper,
	EmployeeEntityToEmployeeDtoMapper,
	
	//DAO
	SelectEmployeeByCommitId,
	
	//Constraint
	
	//Default
	None;
		
}
