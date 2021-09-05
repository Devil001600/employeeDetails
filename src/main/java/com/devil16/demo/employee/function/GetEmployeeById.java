package com.devil16.demo.employee.function;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.exception.EmployeeException;
import com.devil16.demo.employee.exception.EmployeeExceptionConstants;
import com.devil16.demo.employee.exception.EmployeeExceptionResource;
import com.devil16.demo.employee.response.EmployeeResponse;
import com.devil16.demo.employee.service.EmployeeService;

/**
 * GetTpsDevById class - 
 * 
 * acts as a REST (Representational State Transfer) controller,
 * which is exposed to the web
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 * 
 */

/*
 * we are not using the usual @RestController annotation here
 * 
 * instead we are implementing the Function<> functional interface
 * to implement the Spring Cloud Function (https://spring.io/projects/spring-cloud-function)
 * 
 */
@Component
@Scope("prototype")
public class GetEmployeeById implements Function<EmployeeDto, EmployeeDto> {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeResponse employeeResponse;

	@Override
	public EmployeeDto apply(EmployeeDto employeeDto) {
		
		try {
			
			return employeeResponse.employeeDetailsSuccessResponse(
					employeeService.getEmployeeById(employeeDto));
			
		} catch (EmployeeException ee) {
			
			return employeeResponse.employeeDetailsExceptionResponse(ee);
			
		} catch (Exception e) {
			
			EmployeeException ee = EmployeeException.
					builder().
					errorCode(EmployeeExceptionConstants.ED_UNKN.getErrorCode()).
					errorDescription(EmployeeExceptionConstants.ED_UNKN.getErrorDescription()).
					throwable(e).
					requestObject(employeeDto).
					exceptionResource(EmployeeExceptionResource.GetEmployeeById).
					build();
			
			return employeeResponse.employeeDetailsExceptionResponse(ee);
			
		}

	}
	
}