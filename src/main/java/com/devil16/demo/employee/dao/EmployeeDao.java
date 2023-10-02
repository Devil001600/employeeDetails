package com.devil16.demo.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.devil16.demo.employee.entity.EmployeeEntity;

/**
 * DevDao interface - 
 * 
 * this is the DAO (Data Access Object)
 * this acts as a MyBatis Persistence Mapper
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 * 
 * @see com.devil16.demo.employee.persistence.SelectEmployeeById
 */

/* 
 * we are using the MyBatis (https://mybatis.org/mybatis-3) framework here
 * 
 * MapStruct allows to map abstract methods to sql statements and
 * makes it really convinient with mapping the Object to the Relational DB
 * 
 */
@Mapper
public interface EmployeeDao {
	
	EmployeeEntity selectEmployeeByCommitId(String id) throws Exception;
	
	List<EmployeeEntity> selectAllEmployees() throws Exception;
	
}