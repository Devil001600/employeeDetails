package com.devil16.demo.employee.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
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
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeDesignation;
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeLob;
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeStatus;
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeTeam;
import com.devil16.demo.employee.dto.EmployeeDtoEnums.EmployeeType;
import com.devil16.demo.employee.dto.mapper.EmployeeDtoMapper;
import com.devil16.demo.employee.entity.EmployeeEntity;
import com.devil16.demo.employee.exception.EmployeeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

/**
 * ConvertDtoToEntityTests class - 
 * 
 * carries unit-tests for the convertEntityToDto() in EmployeeService class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2022-01-23
 *	
 * @see com.devil16.demo.employee.service.EmployeeService
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class ConvertEntityToDtoTests {
	
	@Mock
	private EmployeeDtoMapper employeeDtoMapper; 
	
	@InjectMocks
	private EmployeeService employeeService = new EmployeeServiceImpl();
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private Resource employeeDtoResource = new ClassPathResource("/com/devil16/demo/employee/dto/EmployeeDtoSample.json");
	
	private Resource employeeEntityResource = new ClassPathResource("/com/devil16/demo/employee/entity/EmployeeEntitySample.json");
	
	private EmployeeDto employeeDtoReference = new EmployeeDto();

	private EmployeeDto employeeDtoActual = new EmployeeDto();
	
	private EmployeeEntity employeeEntityReference = new EmployeeEntity();
	
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
		
		this.employeeDtoActual = new EmployeeDto();
		
	}
	
	/*
	 * checks if the EmployeeService.convertEntityToDto() is working as expected
	 */
	@Test
	public void convertEntityToDtoShould() {
		
		try {
			
			Mockito.doReturn(this.employeeDtoReference).when(this.employeeDtoMapper).employeeEntityToEmployeeDtoMapper(this.employeeEntityReference);
			
			this.employeeDtoActual = this.employeeService.convertEntityToDto(this.employeeEntityReference);
			
			verify(this.employeeDtoMapper).employeeEntityToEmployeeDtoMapper(this.employeeEntityReference);
			
		} catch (EmployeeException ee) {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
		}
		
		assertAll(
			() -> assertTrue(this.employeeDtoActual.getCommitId().equals(this.employeeDtoReference.getCommitId())),
			() -> assertTrue(this.employeeDtoActual.getFirstName().equals(this.employeeDtoReference.getFirstName())),
			() -> assertTrue(this.employeeDtoActual.getLastName().equals(this.employeeDtoReference.getLastName())),
			() -> assertTrue(this.employeeDtoActual.getType().equals(this.employeeDtoReference.getType())),
			() -> assertTrue(this.employeeDtoActual.getCostBillLink().equals(this.employeeDtoReference.getCostBillLink())),
			() -> assertTrue(this.employeeDtoActual.getCostSettleLink().equals(this.employeeDtoReference.getCostSettleLink())),
			() -> assertTrue(this.employeeDtoActual.getDesignation().equals(this.employeeDtoReference.getDesignation())),
			() -> assertTrue(this.employeeDtoActual.getTeam().equals(this.employeeDtoReference.getTeam())),
			() -> assertTrue(this.employeeDtoActual.getLineOfBusiness().equals(this.employeeDtoReference.getLineOfBusiness())),
			() -> assertTrue(this.employeeDtoActual.getEmailId().equals(this.employeeDtoReference.getEmailId())),
			() -> assertTrue(this.employeeDtoActual.getPhone().equals(this.employeeDtoReference.getPhone())),
			() -> assertTrue(this.employeeDtoActual.getStatus().equals(this.employeeDtoReference.getStatus())),
			() -> assertTrue(this.employeeDtoActual.getDateOfJoining().equals(this.employeeDtoReference.getDateOfJoining())),
			() -> assertTrue(this.employeeDtoActual.getLastWorkingDate().equals(this.employeeDtoReference.getLastWorkingDate()))
		);
		
	}
	
	/*
	 * checks if the EmployeeService.convertEntityToDto() exception is being thrown as expected
	 */
	@Test
	public void convertEntityToDtoExceptionShould() {
		
		try {
			
			Mockito.doThrow(IllegalArgumentException.class).when(this.employeeDtoMapper).employeeEntityToEmployeeDtoMapper(this.employeeEntityReference);
			
			this.employeeDtoActual = this.employeeService.convertEntityToDto(this.employeeEntityReference);
			
			verify(this.employeeDtoMapper).employeeEntityToEmployeeDtoMapper(this.employeeEntityReference);
			
		} catch (EmployeeException ee) {
			
			log.error("App Resp : {}. {}. {}", ee.getExceptionResource(), ee.getErrorCode(), ee.getErrorDescription());
			log.error("Exception Type : {}", ee.getThrowable().getClass().getName());
			log.error("Exception Info : {}", ee.getThrowable().getMessage());
			
		}
		
		assertAll(
			() -> assertTrue(this.employeeDtoActual.getCommitId().equals(StringUtils.EMPTY)),
			() -> assertTrue(this.employeeDtoActual.getFirstName().equals(StringUtils.EMPTY)),
			() -> assertTrue(this.employeeDtoActual.getLastName().equals(StringUtils.EMPTY)),
			() -> assertTrue(this.employeeDtoActual.getType().equals(EmployeeType.Novalue)),
			() -> assertTrue(this.employeeDtoActual.getCostBillLink().equals(StringUtils.EMPTY)),
			() -> assertTrue(this.employeeDtoActual.getCostSettleLink().equals(StringUtils.EMPTY)),
			() -> assertTrue(this.employeeDtoActual.getDesignation().equals(EmployeeDesignation.Novalue)),
			() -> assertTrue(this.employeeDtoActual.getTeam().equals(EmployeeTeam.Novalue)),
			() -> assertTrue(this.employeeDtoActual.getLineOfBusiness().equals(EmployeeLob.Novalue)),
			() -> assertTrue(this.employeeDtoActual.getEmailId().equals(StringUtils.EMPTY)),
			() -> assertTrue(this.employeeDtoActual.getPhone().equals(StringUtils.EMPTY)),
			() -> assertTrue(this.employeeDtoActual.getStatus().equals(EmployeeStatus.Novalue)),
			() -> assertTrue(this.employeeDtoActual.getDateOfJoining().equals(LocalDate.of(1970,01,01))),
			() -> assertTrue(this.employeeDtoActual.getLastWorkingDate().equals(LocalDate.of(1970,01,01)))
		);

	}
	
}
