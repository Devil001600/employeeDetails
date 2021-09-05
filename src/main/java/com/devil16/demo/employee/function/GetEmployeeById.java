package com.devil16.demo.employee.function;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.devil16.demo.employee.dto.EmployeeDto;
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
	private EmployeeService tpsDevService;

	@Override
	public EmployeeDto apply(EmployeeDto tpsDevDto) {
		try {
			return tpsDevService.convertEntityToDto(tpsDevService.getEntityById(tpsDevService.convertDtoToEntity(tpsDevDto)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
}