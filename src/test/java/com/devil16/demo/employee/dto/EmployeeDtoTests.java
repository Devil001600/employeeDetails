package com.devil16.demo.employee.dto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeDesignation;
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeLob;
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeStatus;
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeTeam;
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeType;
import com.jparams.verifier.tostring.ToStringVerifier;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * EmployeeDtoTests class - 
 * 
 * carries various unit-tests for the EmployeeDto class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2022-01-12
 *	
 * @see com.devil16.demo.employee.dto.EmployeeDto
 */
public class EmployeeDtoTests {
	
	/*
	 * checks if the equals() is working as expected
	 */
	@Test
	public void employeeDtoEqualsShould() {
		
		EqualsVerifier.
		forClass(EmployeeDto.class).
		suppress(Warning.NONFINAL_FIELDS).
		verify();
		
	}
	
	/*
	 * checks if the toString() is working as expected
	 */
	@Test
	public void employeeDtoToStringShould() {
		
		ToStringVerifier.
		forClass(EmployeeDto.class).
		verify();
		
	}
	
	/*
	 * checks if the no-args constructor and the setters are working as expected
	 */
	@Test
	public void employeeDtoNoArgsShould() {
		
		EmployeeDto employeeDto = new EmployeeDto();
		
		employeeDto.setCommitId("XBBLVZA");
		employeeDto.setFirstName("Debanshu");
		employeeDto.setLastName("Pani");
		employeeDto.setType(EmployeeType.Permanent);
		employeeDto.setCostBillLink("EMP.FI.XBBLVZA.COM");
		employeeDto.setCostSettleLink("EMP.SM.XBBLVZA.COM");
		employeeDto.setDesignation(EmployeeDesignation.SeniorApplicationDeveloper);
		employeeDto.setTeam(EmployeeTeam.Billing);
		employeeDto.setLineOfBusiness(EmployeeLob.Trading);
		employeeDto.setEmailId("debanshu.pani@devil16.com");
		employeeDto.setPhone("+16467823532");
		employeeDto.setStatus(EmployeeStatus.Active);
		employeeDto.setDateOfJoining(LocalDate.of(2014, 12, 29));
		employeeDto.setLastWorkingDate(LocalDate.of(1970, 01, 01));
		
		assertAll(
			() -> assertTrue(employeeDto instanceof EmployeeDto, "EmployeeDto no-args constructor created wrong object type"),
			
			() -> assertEquals("XBBLVZA", employeeDto.getCommitId()),
			() -> assertEquals("Debanshu", employeeDto.getFirstName()),
			() -> assertEquals("Pani", employeeDto.getLastName()),
			() -> assertEquals(EmployeeType.Permanent, employeeDto.getType()),
			() -> assertEquals("EMP.FI.XBBLVZA.COM", employeeDto.getCostBillLink()),
			() -> assertEquals("EMP.SM.XBBLVZA.COM", employeeDto.getCostSettleLink()),
			() -> assertEquals(EmployeeDesignation.SeniorApplicationDeveloper, employeeDto.getDesignation()),
			() -> assertEquals(EmployeeTeam.Billing, employeeDto.getTeam()),
			() -> assertEquals(EmployeeLob.Trading, employeeDto.getLineOfBusiness()),
			() -> assertEquals("debanshu.pani@devil16.com", employeeDto.getEmailId()),
			() -> assertEquals("+16467823532", employeeDto.getPhone()),
			() -> assertEquals(EmployeeStatus.Active, employeeDto.getStatus()),
			() -> assertEquals(LocalDate.of(2014, 12, 29), employeeDto.getDateOfJoining()),
			() -> assertEquals(LocalDate.of(1970, 01, 01), employeeDto.getLastWorkingDate())
		);
		
	}
	
	/*
	 * checks if the builder and the setters are working as expected
	 */
	@Test
	public void employeeEntityBuilderShould() {
		
		EmployeeDto employeeDto = EmployeeDto.
				builder().
				commitId("XBBLVZA").
				firstName("Debanshu").
				lastName("Pani").
				type(EmployeeType.Permanent).
				costBillLink("EMP.FI.XBBLVZA.COM").
				costSettleLink("EMP.SM.XBBLVZA.COM").
				designation(EmployeeDesignation.SeniorApplicationDeveloper).
				team(EmployeeTeam.Billing).
				lineOfBusiness(EmployeeLob.Trading).
				emailId("debanshu.pani@devil16.com").
				phone("+16467823532").
				status(EmployeeStatus.Active).
				dateOfJoining(LocalDate.of(2014, 12, 29)).
				lastWorkingDate(LocalDate.of(1970, 01, 01)).
				build();
		
		assertAll(
			() -> assertTrue(employeeDto instanceof EmployeeDto, "EmployeeDto builder created wrong object type"),
			
			() -> assertEquals("XBBLVZA", employeeDto.getCommitId()),
			() -> assertEquals("Debanshu", employeeDto.getFirstName()),
			() -> assertEquals("Pani", employeeDto.getLastName()),
			() -> assertEquals(EmployeeType.Permanent, employeeDto.getType()),
			() -> assertEquals("EMP.FI.XBBLVZA.COM", employeeDto.getCostBillLink()),
			() -> assertEquals("EMP.SM.XBBLVZA.COM", employeeDto.getCostSettleLink()),
			() -> assertEquals(EmployeeDesignation.SeniorApplicationDeveloper, employeeDto.getDesignation()),
			() -> assertEquals(EmployeeTeam.Billing, employeeDto.getTeam()),
			() -> assertEquals(EmployeeLob.Trading, employeeDto.getLineOfBusiness()),
			() -> assertEquals("debanshu.pani@devil16.com", employeeDto.getEmailId()),
			() -> assertEquals("+16467823532", employeeDto.getPhone()),
			() -> assertEquals(EmployeeStatus.Active, employeeDto.getStatus()),
			() -> assertEquals(LocalDate.of(2014, 12, 29), employeeDto.getDateOfJoining()),
			() -> assertEquals(LocalDate.of(1970, 01, 01), employeeDto.getLastWorkingDate())
		);
		
	}
}
