package com.devil16.demo.employee.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.devil16.demo.employee.dao.EmployeeDao;
import com.devil16.demo.employee.entity.EmployeeEntity;
import com.devil16.demo.employee.exception.EmployeeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

/**
 * GetEntityByIdTests class - 
 * 
 * carries unit-tests for the getEntityById() in EmployeeService class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2022-01-23
 *	
 * @see com.devil16.demo.employee.service.EmployeeService
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class GetEntityByIdTests {
	
	@Mock
	private EmployeeDao employeeDao;
	
	@InjectMocks
	private EmployeeService employeeService = new EmployeeServiceImpl();
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private Resource employeeEntityResource = new ClassPathResource("/com/devil16/demo/employee/entity/EmployeeEntitySample.json");
	
	private EmployeeEntity employeeEntityReference = new EmployeeEntity();
	
	private EmployeeEntity employeeEntityActual = new EmployeeEntity();
	
	/*
	 * initializes required resources before each test
	 */
	@BeforeEach
	public void setUp() {
		
		this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		
		try (Reader employeeEntityResourceReader = new InputStreamReader(this.employeeEntityResource.getInputStream(), StandardCharsets.UTF_8)) {
			
			this.employeeEntityReference = this.objectMapper.readValue(FileCopyUtils.copyToString(employeeEntityResourceReader), EmployeeEntity.class);
			
		} catch(IOException ioe) {
			
			ioe.printStackTrace();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		this.employeeEntityActual = new EmployeeEntity();
		
	}
	
	/*
	 * checks if the EmployeeService.getEntityById() is working as expected
	 */
	@Test
	public void getEntityByIdShould() {
		
		try {
			
			Mockito.doReturn(this.employeeEntityReference).when(this.employeeDao).selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
			this.employeeEntityActual = this.employeeService.getEntityById(this.employeeEntityReference);
			
			verify(this.employeeDao).selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
		} catch (EmployeeException ee) {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
		} catch (Exception e) {
			
			log.error("Exception Type : {}", e.getClass().getName());
			log.error("Exception Info : {}", e.getMessage());
			
		}
		
		assertAll(
			() -> assertTrue(this.employeeEntityActual.getCommit_Id().equals(this.employeeEntityReference.getCommit_Id())),
			() -> assertTrue(this.employeeEntityActual.getFirst_Name().equals(this.employeeEntityReference.getFirst_Name())),
			() -> assertTrue(this.employeeEntityActual.getLast_Name().equals(this.employeeEntityReference.getLast_Name())),
			() -> assertTrue(this.employeeEntityActual.getType().equals(this.employeeEntityReference.getType())),
			() -> assertTrue(this.employeeEntityActual.getCost_Bill_Link().equals(this.employeeEntityReference.getCost_Bill_Link())),
			() -> assertTrue(this.employeeEntityActual.getCost_Settle_Link().equals(this.employeeEntityReference.getCost_Settle_Link())),
			() -> assertTrue(this.employeeEntityActual.getDesignation().equals(this.employeeEntityReference.getDesignation())),
			() -> assertTrue(this.employeeEntityActual.getTeam().equals(this.employeeEntityReference.getTeam())),
			() -> assertTrue(this.employeeEntityActual.getLine_Of_Business().equals(this.employeeEntityReference.getLine_Of_Business())),
			() -> assertTrue(this.employeeEntityActual.getEmail_Id().equals(this.employeeEntityReference.getEmail_Id())),
			() -> assertTrue(this.employeeEntityActual.getPhone().equals(this.employeeEntityReference.getPhone())),
			() -> assertTrue(this.employeeEntityActual.getStatus().equals(this.employeeEntityReference.getStatus())),
			() -> assertTrue(this.employeeEntityActual.getDate_Of_Joining().equals(this.employeeEntityReference.getDate_Of_Joining())),
			() -> assertTrue(this.employeeEntityActual.getLast_Working_Date().equals(this.employeeEntityReference.getLast_Working_Date()))
		);
		
	}
	
	/*
	 * checks if the EmployeeService.getEntityById() is handling null results as expected
	 */
	@Test
	public void getEntityByIdNullReturnShould() {
		
		try {
			
			Mockito.doReturn(null).when(this.employeeDao).selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
			this.employeeEntityActual = this.employeeService.getEntityById(this.employeeEntityReference);
			
			verify(this.employeeDao).selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
		} catch (EmployeeException ee) {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
		} catch (Exception e) {
			
			log.error("Exception Type : {}", e.getClass().getName());
			log.error("Exception Info : {}", e.getMessage());
			
		}
		
		assertAll(
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCommit_Id())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getFirst_Name())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLast_Name())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getType())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCost_Bill_Link())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCost_Settle_Link())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getDesignation())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getTeam())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLine_Of_Business())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getEmail_Id())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getPhone())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getStatus())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getDate_Of_Joining())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLast_Working_Date()))
		);
		
	}
	
	/*
	 * checks if the EmployeeService.getEntityById() is handing PersisteException as expected
	 */
	@Test
	public void getEntityByIdPersistenceExceptionShould() {
		
		try {
			
			Mockito.doThrow(PersistenceException.class).when(this.employeeDao).selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
			this.employeeEntityActual = this.employeeService.getEntityById(this.employeeEntityReference);
			
			verify(this.employeeDao).selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
		} catch (EmployeeException ee) {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
		} catch (Exception e) {
			
			log.error("Exception Type : {}", e.getClass().getName());
			log.error("Exception Info : {}", e.getMessage());
			
		}
		
		assertAll(
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCommit_Id())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getFirst_Name())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLast_Name())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getType())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCost_Bill_Link())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCost_Settle_Link())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getDesignation())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getTeam())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLine_Of_Business())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getEmail_Id())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getPhone())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getStatus())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getDate_Of_Joining())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLast_Working_Date()))
		);
		
	}
	
	/*
	 * checks if the EmployeeService.getEntityById() is handing Exception as expected
	 */
	@Test
	public void getEntityByIdExceptionShould() {
		
		try {
			
			Mockito.doThrow(IllegalArgumentException.class).when(this.employeeDao).selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
			this.employeeEntityActual = this.employeeService.getEntityById(this.employeeEntityReference);
			
			verify(this.employeeDao).selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
		} catch (EmployeeException ee) {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
		} catch (Exception e) {
			
			log.error("Exception Type : {}", e.getClass().getName());
			log.error("Exception Info : {}", e.getMessage());
			
		}
		
		assertAll(
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCommit_Id())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getFirst_Name())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLast_Name())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getType())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCost_Bill_Link())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getCost_Settle_Link())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getDesignation())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getTeam())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLine_Of_Business())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getEmail_Id())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getPhone())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getStatus())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getDate_Of_Joining())),
			() -> assertTrue(Objects.isNull(this.employeeEntityActual.getLast_Working_Date()))
		);
		
	}
	
}
