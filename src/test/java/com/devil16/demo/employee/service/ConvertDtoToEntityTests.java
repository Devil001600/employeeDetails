package com.devil16.demo.employee.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

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

import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.dto.mapper.EmployeeDtoMapper;
import com.devil16.demo.employee.entity.EmployeeEntity;
import com.devil16.demo.employee.exception.EmployeeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

/**
 * ConvertDtoToEntityTests class - 
 * 
 * carries unit-tests for the convertDtoToEntity() in EmployeeService class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2022-01-23
 *	
 * @see com.devil16.demo.employee.service.EmployeeService
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class ConvertDtoToEntityTests {
	
	@Mock
	private EmployeeDtoMapper employeeDtoMapper; 
	
	@InjectMocks
	private EmployeeService employeeService = new EmployeeServiceImpl();
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private Resource employeeDtoResource = new ClassPathResource("/com/devil16/demo/employee/dto/EmployeeDtoSample.json");
	
	private Resource employeeEntityResource = new ClassPathResource("/com/devil16/demo/employee/entity/EmployeeEntitySample.json");
	
	private EmployeeDto employeeDtoReference = new EmployeeDto();
	
	private EmployeeEntity employeeEntityReference = new EmployeeEntity();
	
	private EmployeeEntity employeeEntityActual = new EmployeeEntity();
	
	/*
	 * initializes required resources before each test
	 */
	@BeforeEach
	public void setUp() {
		
		this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		
		try (Reader employeeDtoResourceReader = new InputStreamReader(this.employeeDtoResource.getInputStream(), StandardCharsets.UTF_8)) {
			
			this.employeeDtoReference = this.objectMapper.readValue(FileCopyUtils.copyToString(employeeDtoResourceReader), EmployeeDto.class);
			
		} catch(IOException ioe) {
			
			ioe.printStackTrace();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
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
	 * checks if the EmployeeService.convertDtoToEntity() is working as expected
	 */
	@Test
	public void convertDtoToEntityShould() {
		
		try {
			
			//mock a call to the mapstruct-mapper and the expected return 
			Mockito.doReturn(this.employeeEntityReference).when(this.employeeDtoMapper).employeeDtoToEmployeeEntityMapper(this.employeeDtoReference);
			
			//execute the service-method
			this.employeeEntityActual = this.employeeService.convertDtoToEntity(this.employeeDtoReference);
			
			//verify the mapstruct-mapper call was made internally
			verify(this.employeeDtoMapper).employeeDtoToEmployeeEntityMapper(this.employeeDtoReference);
			
		} catch (EmployeeException ee) {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
		}
		
			//assert the return off the mocked call is as expected
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
	 * checks if the EmployeeService.convertDtoToEntity() exception is being thrown as expected
	 */
	@Test
	public void convertDtoToEntityExceptionShould() {
		
		try {
			
			Mockito.doThrow(IllegalArgumentException.class).when(this.employeeDtoMapper).employeeDtoToEmployeeEntityMapper(this.employeeDtoReference);
			
			this.employeeEntityActual = this.employeeService.convertDtoToEntity(this.employeeDtoReference);
			
			verify(this.employeeDtoMapper).employeeDtoToEmployeeEntityMapper(this.employeeDtoReference);
			
		} catch (EmployeeException ee) {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
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
