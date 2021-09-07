package com.devil16.demo.employee.entity;

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
 * EmployeeEntityTests class - 
 * 
 * carries various unit-tests for the EmployeeEntity class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-09-13
 *	
 * @see com.devil16.demo.employee.entity.EmployeeEntity
 */
public class EmployeeEntityTests {
	
	/*
	 * checks if the equals() is working as expected
	 */
	@Test
	public void employeeEntityEqualsShould() {
		
		EqualsVerifier.
		forClass(EmployeeEntity.class).
		suppress(Warning.NONFINAL_FIELDS).
		verify();
		
	}
	
	/*
	 * checks if the toString() is working as expected
	 */
	@Test
	public void employeeEntityToStringShould() {
		
		ToStringVerifier.
		forClass(EmployeeEntity.class).
		verify();
		
	}
	
	/*
	 * checks if the no-args constructor and the setters are working as expected
	 */
	@Test
	public void employeeEntityNoArgsShould() {
		
		EmployeeEntity employeeEntity = new EmployeeEntity();
		
		employeeEntity.setCommit_Id("XBBLVZA");
		employeeEntity.setFirst_Name("Debanshu");
		employeeEntity.setLast_Name("Pani");
		employeeEntity.setType(EmployeeType.Permanent.toString());
		employeeEntity.setCost_Bill_Link("EMP.FI.XBBLVZA.COM");
		employeeEntity.setCost_Settle_Link("EMP.SM.XBBLVZA.COM");
		employeeEntity.setDesignation(EmployeeDesignation.SeniorApplicationDeveloper.toString());
		employeeEntity.setTeam(EmployeeTeam.Billing.toString());
		employeeEntity.setLine_Of_Business(EmployeeLob.Trading.toString());
		employeeEntity.setEmail_Id("debanshu.pani@devil16.com");
		employeeEntity.setPhone("+16467823532");
		employeeEntity.setStatus(EmployeeStatus.Active.toString());
		employeeEntity.setDate_Of_Joining(LocalDate.of(2014, 12, 29));
		employeeEntity.setLast_Working_Date(LocalDate.of(1970, 01, 01));
		
		assertAll(
			() -> assertTrue(employeeEntity instanceof EmployeeEntity, "EmployeeEntity no-args constructor created wrong object type"),
			
			() -> assertEquals("XBBLVZA", employeeEntity.getCommit_Id()),
			() -> assertEquals("Debanshu", employeeEntity.getFirst_Name()),
			() -> assertEquals("Pani", employeeEntity.getLast_Name()),
			() -> assertEquals(EmployeeType.Permanent.toString(), employeeEntity.getType()),
			() -> assertEquals("EMP.FI.XBBLVZA.COM", employeeEntity.getCost_Bill_Link()),
			() -> assertEquals("EMP.SM.XBBLVZA.COM", employeeEntity.getCost_Settle_Link()),
			() -> assertEquals(EmployeeDesignation.SeniorApplicationDeveloper.toString(), employeeEntity.getDesignation()),
			() -> assertEquals(EmployeeTeam.Billing.toString(), employeeEntity.getTeam()),
			() -> assertEquals(EmployeeLob.Trading.toString(), employeeEntity.getLine_Of_Business()),
			() -> assertEquals("debanshu.pani@devil16.com", employeeEntity.getEmail_Id()),
			() -> assertEquals("+16467823532", employeeEntity.getPhone()),
			() -> assertEquals(EmployeeStatus.Active.toString(), employeeEntity.getStatus()),
			() -> assertEquals(LocalDate.of(2014, 12, 29), employeeEntity.getDate_Of_Joining()),
			() -> assertEquals(LocalDate.of(1970, 01, 01), employeeEntity.getLast_Working_Date())
		);
		
	}
	
	/*
	 * checks if the no-args constructor and the setters are working as expected
	 */
	@Test
	public void employeeEntityBuilderShould() {
		
		EmployeeEntity employeeEntity = EmployeeEntity.
				builder().
				commit_Id("XBBLVZA").
				first_Name("Debanshu").
				last_Name("Pani").
				type(EmployeeType.Permanent.toString()).
				cost_Bill_Link("EMP.FI.XBBLVZA.COM").
				cost_Settle_Link("EMP.SM.XBBLVZA.COM").
				designation(EmployeeDesignation.SeniorApplicationDeveloper.toString()).
				team(EmployeeTeam.Billing.toString()).line_Of_Business(EmployeeLob.Trading.toString()).
				email_Id("debanshu.pani@devil16.com").
				phone("+16467823532").
				status(EmployeeStatus.Active.toString()).
				date_Of_Joining(LocalDate.of(2014, 12, 29)).
				last_Working_Date(LocalDate.of(1970, 01, 01)).
				build();
		
		assertAll(
			() -> assertTrue(employeeEntity instanceof EmployeeEntity, "EmployeeEntity no-args constructor created wrong object type"),
			
			() -> assertEquals("XBBLVZA", employeeEntity.getCommit_Id()),
			() -> assertEquals("Debanshu", employeeEntity.getFirst_Name()),
			() -> assertEquals("Pani", employeeEntity.getLast_Name()),
			() -> assertEquals(EmployeeType.Permanent.toString(), employeeEntity.getType()),
			() -> assertEquals("EMP.FI.XBBLVZA.COM", employeeEntity.getCost_Bill_Link()),
			() -> assertEquals("EMP.SM.XBBLVZA.COM", employeeEntity.getCost_Settle_Link()),
			() -> assertEquals(EmployeeDesignation.SeniorApplicationDeveloper.toString(), employeeEntity.getDesignation()),
			() -> assertEquals(EmployeeTeam.Billing.toString(), employeeEntity.getTeam()),
			() -> assertEquals(EmployeeLob.Trading.toString(), employeeEntity.getLine_Of_Business()),
			() -> assertEquals("debanshu.pani@devil16.com", employeeEntity.getEmail_Id()),
			() -> assertEquals("+16467823532", employeeEntity.getPhone()),
			() -> assertEquals(EmployeeStatus.Active.toString(), employeeEntity.getStatus()),
			() -> assertEquals(LocalDate.of(2014, 12, 29), employeeEntity.getDate_Of_Joining()),
			() -> assertEquals(LocalDate.of(1970, 01, 01), employeeEntity.getLast_Working_Date())
		);
		
	}
	
}
