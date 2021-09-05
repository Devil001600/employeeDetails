package com.devil16.demo.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devil16.demo.employee.dao.EmployeeDao;
import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.dtomapper.EmployeeDtoMapper;
import com.devil16.demo.employee.entity.EmployeeEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeServiceImpl class - 
 * 
 * implements EmployeeServiceImpl interface
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 * 
 * @see com.devil16.demo.employee.service.EmployeeService
 */

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDtoMapper employeeDtoMapper;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public EmployeeEntity convertDtoToEntity(EmployeeDto employeeDto){
		log.info("tpsDevDto recieved :{}", employeeDto);
		return employeeDtoMapper.employeeDtoToEmployeeEntityMapper(employeeDto);
	}
	
	@Override
	public EmployeeEntity getEntityById(EmployeeEntity employeeEntity) throws Exception{
		log.info("tpsDevEntity created :{}", employeeEntity);
		return employeeDao.selectEmployeeByCommitId(employeeEntity.getCommit_Id());
	}
	
	@Override
	public EmployeeDto convertEntityToDto(EmployeeEntity employeeEntity){
		return employeeDtoMapper.employeeEntityToEmployeeDtoMapper(employeeEntity);
	}
	
}