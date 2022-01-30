package com.devil16.demo.employee.persistence;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import javax.json.Json;
import javax.json.JsonPatch;

import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import com.devil16.demo.employee.entity.EmployeeEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * SelectEmployeeByIdIT class - 
 * 
 * carries various integration-tests for the SelectEmployeeById class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2022-01-22
 *	
 * @see com.devil16.demo.employee.persistence.SelectEmployeeById
 */
@SpringBootTest
public class SelectEmployeeByIdIT {
	
	@Autowired
	private SelectEmployeeById selectEmployeeById;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private Resource employeeEntityResource = new ClassPathResource("/com/devil16/demo/employee/entity/EmployeeEntitySample.json");
	
	private EmployeeEntity employeeEntityReference = new EmployeeEntity();
	
	private EmployeeEntity employeeEntityActual = new EmployeeEntity();
	
	private JsonPatch jsonPatch;
	
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
		
	}
	
	/*
	 * checks if SelectEmployeeById.selectEmployeeByCommitId() is working as expected
	 */
	@Test
	public void selectEmployeeByCommitIdShould() {
		
		/*
		 * creates a JSON-Patch object by comparing 
		 * the reference EmployeeEntity object (read from the classpath resource) with
		 * the actual EmployeeEntity object (created by fetching the object from DB);
		 * 
		 * then validates the JSON-Patch to show no differences
		 */
		try {
			
			this.employeeEntityActual = this.selectEmployeeById.selectEmployeeByCommitId(this.employeeEntityReference.getCommit_Id());
			
		} catch (PersistenceException pe) {
			
			pe.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			this.jsonPatch = Json.createDiff(
					Json.createReader(new StringReader(this.objectMapper.writeValueAsString(this.employeeEntityReference))).readObject(), 
					Json.createReader(new StringReader(this.objectMapper.writeValueAsString(this.employeeEntityActual))).readObject());
			
		} catch (JsonProcessingException jpe) {
			
			jpe.printStackTrace();
			
		}
		
		assertAll(
			() -> assertEquals(0, this.jsonPatch.toJsonArray().size())
		);
		
	}
	
}
