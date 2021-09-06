package com.devil16.demo.employee.response;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.exception.EmployeeException;
import com.devil16.demo.employee.exception.EmployeeExceptionConstants;
import com.devil16.demo.employee.exception.EmployeeExceptionResource;

import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeResponse - 
 * 
 * central exception-handler / response-builder 
 * for the EmployeeDetails service
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 *
 */
@Slf4j
@Component
public class EmployeeResponse {
	
	/**
	 * employeeDetailsSuccessResponse() - 
	 * 
	 * for successful Execution (no Exception scenario)
	 * creates a dummy exception
	 * (for the purpose of logging)
	 * 
	 * then calls
	 * @see #handleEmployeeException(EmployeeException, EmployeeDto)
	 * 
	 * @param employeeDto EmployeeDto
	 * @return employeeDto EmployeeDto
	 * 
	 */
	public EmployeeDto employeeDetailsSuccessResponse(EmployeeDto employeeDto) {
		
		EmployeeException ee = EmployeeException.
				builder().
				errorCode(EmployeeExceptionConstants.ED_OK.getErrorCode()).
				errorDescription(EmployeeExceptionConstants.ED_OK.getErrorDescription()).
				exceptionResource(EmployeeExceptionResource.GetEmployeeById).
				build();
		
		return this.handleEmployeeException(ee, employeeDto);
		
	}
	
	/**
	 * employeeDetailsExceptionResponse() - 
	 * 
	 * for Exceptions in the App,
	 * 
	 * creates a dummy EmployeeDto bean
	 * 
	 * then calls
	 * @see #handleEmployeeException(EmployeeException, EmployeeDto)
	 * 
	 * @param ee EmployeeException
	 * @return employeeDto EmployeeDto
	 */
	public EmployeeDto employeeDetailsExceptionResponse(EmployeeException ee) {
		
		return this.handleEmployeeException(ee, EmployeeDto.builder().build());
		
	}
	
	/**
	 * handleEmployeeException() - 
	 * 
	 * handles / resolves EmployeeExceptions
	 * 
	 * logs the details in the console
	 * 
	 * @param ee EmployeeException
	 * @param employeeDto EmployeeDto
	 * @return employeeDto EmployeeDto
	 * 
	 */
	public EmployeeDto handleEmployeeException(EmployeeException ee, EmployeeDto employeeDto) {
		
		if (Objects.isNull(ee.getThrowable())) {
			
			log.info("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			
		} else {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
			if (!Objects.isNull(ee.getThrowable().getCause())) {
				log.error("Exception Cause : {}", ee.getThrowable().getCause().getMessage());
			}
			
			log.error("Request Object : {}", ee.getRequestObjects());
			
		}
		
		return employeeDto;
		
	}
	
}
