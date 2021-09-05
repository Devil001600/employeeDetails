package com.devil16.demo.employee.persistence;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.devil16.demo.employee.dao.EmployeeDao;
import com.devil16.demo.employee.entity.EmployeeEntity;

/**
 * SelectEmployeeById class - 
 * 
 * houses the persistence functions
 * 
 * it uses the SqlSe4ssionFactory bean and the MyBatis mappers to manipulate the DataBase
 *  
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 *
 * @see com.devil16.demo.employee.config.PersistenceConfigH2
 * @see com.devil16.demo.employee.dao.EmployeeDao
 */
public class SelectEmployeeById {
	
	@Autowired
	@Qualifier ("SqlSessionFactoryH2")
	SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	EmployeeDao employeeDao;
	
	public EmployeeEntity selectEmployeeById(String commitId) throws Exception {
		
		return employeeDao.selectEmployeeByCommitId(commitId);
		
	}

}