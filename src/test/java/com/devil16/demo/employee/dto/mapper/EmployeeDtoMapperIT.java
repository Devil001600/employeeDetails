package com.devil16.demo.employee.dto.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import javax.json.Json;
import javax.json.JsonPatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.entity.EmployeeEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * EmployeeDtoMapperIT class - 
 * 
 * carries various integration-tests for the EmployeeDtoMapper class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2022-01-12
 *	
 * @see com.devil16.demo.employee.dto.mapper.EmployeeDtoMapper
 */
public class EmployeeDtoMapperIT {
	
	private EmployeeDtoMapper employeeDtoMapper = Mappers.getMapper(EmployeeDtoMapper.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private Resource employeeDtoResource = new ClassPathResource("/com/devil16/demo/employee/dto/EmployeeDtoSample.json");
	
	private Resource employeeEntityResource = new ClassPathResource("/com/devil16/demo/employee/entity/EmployeeEntitySample.json");
	
	private EmployeeDto employeeDtoReference = new EmployeeDto();
	
	private EmployeeEntity employeeEntityReference = new EmployeeEntity();
	
	private JsonPatch jsonPatch;
	
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
		
	}
	
	/*
	 * checks the EmployeeDtoMapper.employeeDtoToEmployeeEntityMapper() is working as expected
	 */
	@Test
	public void employeeDtoToEmployeeEntityMapperShould() {
		
		/*
		 * creates a JSON-Patch object by comparing 
		 * the reference EmployeeDto object (read from the classpath resource) with
		 * the actual EmployeeDto object (created by executing the mapstruct mapping logic);
		 * 
		 * then validates the JSON-Patch to show no differences
		 */
		
		try {
			
			this.jsonPatch = Json.createDiff(
					Json.createReader(new StringReader(this.objectMapper.writeValueAsString(this.employeeEntityReference))).readObject(), 
					Json.createReader(new StringReader(this.objectMapper.writeValueAsString(this.employeeDtoMapper.employeeDtoToEmployeeEntityMapper(employeeDtoReference)))).readObject());
			
		} catch (JsonProcessingException jpe) {
			
			jpe.printStackTrace();
			
		}
		
		assertAll(
			() -> assertEquals(0, this.jsonPatch.toJsonArray().size())
		);
		
	}
	
	/*
	 * checks the EmployeeDtoMapper.employeeEntityToEmployeeDtoMapper() is working as expected
	 */
	@Test
	public void employeeEntityToEmployeeDtoMapperShould() {
		
		try {
			
			this.jsonPatch = Json.createDiff(
					Json.createReader(new StringReader(this.objectMapper.writeValueAsString(this.employeeDtoReference))).readObject(), 
					Json.createReader(new StringReader(this.objectMapper.writeValueAsString(this.employeeDtoMapper.employeeEntityToEmployeeDtoMapper(employeeEntityReference)))).readObject());
			
		} catch (JsonProcessingException jpe) {
			
			jpe.printStackTrace();
			
		}
		
		assertAll(
			() -> assertEquals(0, this.jsonPatch.toJsonArray().size())
		);
		
	}
	
}
