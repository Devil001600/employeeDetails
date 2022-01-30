package com.devil16.demo.employee.function;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
import com.devil16.demo.employee.exception.EmployeeException;
import com.devil16.demo.employee.response.EmployeeResponse;
import com.devil16.demo.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

/**
 * GetEmployeeByIdTests class - 
 * 
 * carries unit-tests for the GetEntityById() Function
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2022-01-29
 *	
 * @see com.devil16.demo.employee.function.GetEmployeeById
 */

@Slf4j
@ExtendWith(MockitoExtension.class)
public class GetEmployeeByIdTests {
	
	@Mock
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeResponse employeeResponse;
	
	@InjectMocks
	private GetEmployeeById getEmployeeById = new GetEmployeeById();
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private Resource employeeDtoResource = new ClassPathResource("/com/devil16/demo/employee/dto/EmployeeDtoSample.json");
	
	private EmployeeDto employeeDtoReference = new EmployeeDto();
	
	private EmployeeDto employeeDtoActual = new EmployeeDto();
	
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
		
	}
	
	/*
	 * checks if the GetEmployeeById Function works as expected
	 */
	@Test
	public void getEmployeeByIdShould() {
		
		try {
			
		//	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getEmployeeById").accept(MediaType.APPLICATION_JSON);
		//	this.mockMvc.perform((RequestBuilder) ((ResultActions) MockMvcRequestBuilders.get(("/getEmployeeById"))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()));
			Mockito.doReturn(this.employeeDtoReference).when(this.employeeService).getEmployeeById(EmployeeDto.builder().commitId(this.employeeDtoReference.getCommitId()).build());
			
			Mockito.doReturn(this.employeeDtoReference).when(this.employeeResponse).employeeDetailsSuccessResponse(this.employeeDtoReference);
		
			this.employeeDtoActual = this.getEmployeeById.apply(EmployeeDto.builder().commitId(this.employeeDtoReference.getCommitId()).build());
			
			verify(this.employeeService).getEmployeeById(EmployeeDto.builder().commitId(this.employeeDtoReference.getCommitId()).build());
			
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
	 * checks the EmployeeException thrown by EmployeeService.getEmployeeById() is handled properly
	 */
	@Test
	public void getEmployeeByIdEmployeeExceptionShould() {
		
		try {
			
			Mockito.doThrow(EmployeeException.class).when(this.employeeService).getEmployeeById(EmployeeDto.builder().commitId(this.employeeDtoReference.getCommitId()).build());
			
			Mockito.doReturn(EmployeeDto.builder().build()).when(this.employeeResponse).employeeDetailsExceptionResponse(any(EmployeeException.class));
		
			this.employeeDtoActual = this.getEmployeeById.apply(EmployeeDto.builder().commitId(this.employeeDtoReference.getCommitId()).build());
			
			verify(this.employeeResponse).employeeDetailsExceptionResponse(any(EmployeeException.class));
			
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
	
	/*
	 * checks any Exception thrown by EmployeeService.getEmployeeById() is handled properly
	 */
	@Test
	public void getEmployeeByIdExceptionShould() {
		
		try {
			
			Mockito.doThrow(IllegalArgumentException.class).when(this.employeeService).getEmployeeById(EmployeeDto.builder().commitId(this.employeeDtoReference.getCommitId()).build());
			
			Mockito.doReturn(EmployeeDto.builder().build()).when(this.employeeResponse).employeeDetailsExceptionResponse(any(EmployeeException.class));
		
			this.employeeDtoActual = this.getEmployeeById.apply(EmployeeDto.builder().commitId(this.employeeDtoReference.getCommitId()).build());
			
			verify(this.employeeResponse).employeeDetailsExceptionResponse(any(EmployeeException.class));
			
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
