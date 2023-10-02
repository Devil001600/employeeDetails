package com.devil16.demo.employee.function;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.exception.EmployeeException;
import com.devil16.demo.employee.exception.EmployeeExceptionConstants;
import com.devil16.demo.employee.exception.EmployeeExceptionResource;
import com.devil16.demo.employee.response.EmployeeResponse;
import com.devil16.demo.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Scope("prototype")
public class GetEmployees implements Supplier<List<EmployeeDto>> {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeResponse employeeResponse;

	@Override
	public List<EmployeeDto> get() {
		
		try {
			
			return employeeService.getEmployees();
			
		} catch (Exception e) {
			
			log.error("Exception : {}", e);
			
			return List.of();
			
		}

	}
	
}