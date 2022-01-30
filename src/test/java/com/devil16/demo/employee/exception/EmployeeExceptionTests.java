package com.devil16.demo.employee.exception;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import com.devil16.demo.employee.dto.EmployeeDto;
import com.jparams.verifier.tostring.ToStringVerifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * EmployeeExceptionTests class - 
 * 
 * carries unit-tests for the EmployeeException class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2022-01-29
 *	
 * @see com.devil16.demo.employee.exception.EmployeeException
 */

public class EmployeeExceptionTests {
	
	/*
	 * checks if the equals() is working as expected
	 */
	@Test
	public void employeeExceptionEqualsShould() {
		
		EqualsVerifier.
		forClass(EmployeeException.class).
		suppress(Warning.NONFINAL_FIELDS).
		suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).
		verify();
		
	}
	
	/*
	 * checks if the toString() is working as expected
	 */
	@Test
	public void employeeExceptionToStringShould() {
		
		ToStringVerifier.
		forClass(EmployeeException.class).withIgnoredFields(new String[]{"backtrace", "detailMessage", "cause", "stackTrace", "depth", "suppressedExceptions"}).
		verify();
		
	}
	
	/*
	 * checks if the no-args constructor and the setters are working as expected
	 */
	@Test
	public void employeeExceptionNoArgsShould() {
		
		EmployeeException employeeException = new EmployeeException();
				
		employeeException.setErrorCode(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorCode());
		employeeException.setErrorDescription(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorDescription());
		employeeException.setThrowable(new IllegalArgumentException("Dummy Exception"));
		employeeException.setRequestObjects(Arrays.asList(new Object[]{EmployeeDto.builder().build()}));
		employeeException.setExceptionResource(EmployeeExceptionResource.EmployeeDtoToEmployeeEntityMapper);
				
		assertAll(
			() -> assertTrue(employeeException instanceof EmployeeException, "EmployeeException builder created wrong object type"),
				
			() -> assertEquals(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorCode(), employeeException.getErrorCode()),
			() -> assertEquals(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorDescription(), employeeException.getErrorDescription()),
			() -> assertTrue(employeeException.getThrowable() instanceof IllegalArgumentException),
			() -> assertEquals("Dummy Exception", employeeException.getThrowable().getMessage()),
			() -> assertTrue(EmployeeDto.builder().build().equals(employeeException.getRequestObjects().get(0))),
			() -> assertEquals(EmployeeExceptionResource.EmployeeDtoToEmployeeEntityMapper, employeeException.getExceptionResource())
		);
		
	}
	
	/*
	 * checks if the builder and the setters are working as expected
	 */
	@Test
	public void employeeExceptionBuilderShould() {
		
		EmployeeException employeeException = EmployeeException.
				builder().
				errorCode(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorCode()).
				errorDescription(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorDescription()).
				throwable(new IllegalArgumentException("Dummy Exception")).
				requestObject(EmployeeDto.builder().build()).
				exceptionResource(EmployeeExceptionResource.EmployeeDtoToEmployeeEntityMapper).
				build();
		
		assertAll(
			() -> assertTrue(employeeException instanceof EmployeeException, "EmployeeException builder created wrong object type"),
				
			() -> assertEquals(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorCode(), employeeException.getErrorCode()),
			() -> assertEquals(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorDescription(), employeeException.getErrorDescription()),
			() -> assertTrue(employeeException.getThrowable() instanceof IllegalArgumentException),
			() -> assertEquals("Dummy Exception", employeeException.getThrowable().getMessage()),
			() -> assertTrue(EmployeeDto.builder().build().equals(employeeException.getRequestObjects().get(0))),
			() -> assertEquals(EmployeeExceptionResource.EmployeeDtoToEmployeeEntityMapper, employeeException.getExceptionResource())
		);
		
	}
	
}
