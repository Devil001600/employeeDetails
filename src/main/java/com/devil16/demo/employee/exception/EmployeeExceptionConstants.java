package com.devil16.demo.employee.exception;

public enum EmployeeExceptionConstants {

	//No Exceptions
	ED_OK("ED-000", "Success!"),
	
	//System || Data Exceptions
	ED_MAP_EE_FAIL("ED-100", "The Employee Entity mapping has failed for the requested Dto!"),
	ED_MAP_ED_FAIL("ED-101", "The Employee Dto mapping has failed for the requested Entity!"),
	
	ED_GET_ED_FAIL("ED-200", "The Employee Details fetch has failed for the requested ID!"),
	ED_NO_ED_FOUND("ED-201", "No Employee Details found for the requested ID!"),
	
	//Default Exception
	ED_UNKN("ED-999", "EmployeeDetails Service encountered unknown Exception for the Request!");
		
	/**
	 * application error code
	 */
	private String errorCode;
	
	/**
	 * application error description
	 */
	private String errorDescription;
	
	/**
	 * setErrorCode() - 
	 * 
	 * setter method for error code
	 * 
	 * @param errorCode String
	 * 
	 */
	public void setErrorCode(String errorCode) {
		
		this.errorCode = errorCode;
	}
	
	/**
	 * getErrorCode() - 
	 * 
	 * getter method for error code
	 * 
	 * @return this.errorCode String
	 * 
	 */
	public String getErrorCode() {
		
		return this.errorCode;
		
	}
	
	/**
	 * setErrorDescription() - 
	 * 
	 * setter method for errorDescription
	 *  
	 * @param errorDescription String
	 * 
	 */
	public void setErrorDescription(String errorDescription) {
		
		this.errorDescription = errorDescription;
		
	}
	
	/**
	 * getErrorDescription() - 
	 * 
	 * getter method for errorDescription
	 * 
	 * @return this.errorDescription String
	 * 
	 */
	public String getErrorDescription() {
		
		return this.errorDescription;
		
	}
	
	/*
	 * enum constructors can only be private 
	 */
	
	/**
	 * EmployeeExceptionConstants() - 
	 * 
	 * all-args constructor
	 * 
	 * @param errorCode String 
	 * @param errorDescription String
	 * 
	 */
	private EmployeeExceptionConstants(String errorCode, String errorDescription) {
		
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		
	}
	
}
