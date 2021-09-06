package com.devil16.demo.employee.service;

import java.util.Objects;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devil16.demo.employee.dao.EmployeeDao;
import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.dtomapper.EmployeeDtoMapper;
import com.devil16.demo.employee.entity.EmployeeEntity;
import com.devil16.demo.employee.exception.EmployeeException;
import com.devil16.demo.employee.exception.EmployeeExceptionConstants;
import com.devil16.demo.employee.exception.EmployeeExceptionResource;

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
	public EmployeeDto getEmployeeById(EmployeeDto employeeDto) throws EmployeeException {
		
		log.debug("selection of Employee Details has started for the given ID!");
		
		return this.convertEntityToDto(this.getEntityById(this.convertDtoToEntity(employeeDto)));
		
	}
	
	
	@Override
	public EmployeeEntity convertDtoToEntity(EmployeeDto employeeDto) throws EmployeeException {
		
		log.debug("employeeDto recieved :{}", employeeDto);
		
		EmployeeEntity employeeEntity = EmployeeEntity.builder().build();
		
		try {
			
			employeeEntity = employeeDtoMapper.employeeDtoToEmployeeEntityMapper(employeeDto);
			
		} catch (Exception e) {
			
			throw EmployeeException.
			builder().
			errorCode(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorCode()).
			errorDescription(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorDescription()).
			throwable(e).
			requestObject(employeeDto).
			exceptionResource(EmployeeExceptionResource.EmployeeDtoToEmployeeEntityMapper).
			build();
			
		}
		
		return employeeEntity;
		
	}
	
	@Override
	public EmployeeDto convertEntityToDto(EmployeeEntity employeeEntity) throws EmployeeException {
		
		log.debug("employeeEntity recieved :{}", employeeEntity);
		
		EmployeeDto employeeDto = EmployeeDto.builder().build();
		
		try {
			
			employeeDto = employeeDtoMapper.employeeEntityToEmployeeDtoMapper(employeeEntity);
			
		} catch (Exception e) {
			
			throw EmployeeException.
			builder().
			errorCode(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorCode()).
			errorDescription(EmployeeExceptionConstants.ED_MAP_ED_FAIL.getErrorDescription()).
			throwable(e).
			requestObject(employeeEntity).
			exceptionResource(EmployeeExceptionResource.EmployeeEntityToEmployeeDtoMapper).
			build();
			
		}
		
		return employeeDto;
		
	}
	
	@Override
	public EmployeeEntity getEntityById(EmployeeEntity employeeEntity) throws EmployeeException {
		
		log.debug("employee details being fetched for employeeId : {}", employeeEntity.getCommit_Id());
		
		EmployeeEntity employeeEntityFetched = EmployeeEntity.builder().build();
		
		try {
			
			employeeEntityFetched = employeeDao.selectEmployeeByCommitId(employeeEntity.getCommit_Id());
			
		} catch (PersistenceException pe) {
			
			throw EmployeeException.
			builder().
			errorCode(EmployeeExceptionConstants.ED_GET_ED_FAIL.getErrorCode()).
			errorDescription(EmployeeExceptionConstants.ED_GET_ED_FAIL.getErrorDescription()).
			throwable(pe).
			requestObject(employeeEntity.getCommit_Id()).
			exceptionResource(EmployeeExceptionResource.SelectEmployeeByCommitId).
			build();
			
		} catch (Exception e) {
			
			throw EmployeeException.
			builder().
			errorCode(EmployeeExceptionConstants.ED_GET_ED_FAIL.getErrorCode()).
			errorDescription(EmployeeExceptionConstants.ED_GET_ED_FAIL.getErrorDescription()).
			throwable(e).
			requestObject(employeeEntity.getCommit_Id()).
			exceptionResource(EmployeeExceptionResource.SelectEmployeeByCommitId).
			build();
			
		}
		
		if (Objects.isNull(employeeEntityFetched)) {
			
			throw EmployeeException.
			builder().
			errorCode(EmployeeExceptionConstants.ED_NO_ED_FOUND.getErrorCode()).
			errorDescription(EmployeeExceptionConstants.ED_NO_ED_FOUND.getErrorDescription()).
			throwable(new EmptyResultDataAccessException(1)).
			requestObject(employeeEntity.getCommit_Id()).
			exceptionResource(EmployeeExceptionResource.SelectEmployeeByCommitId).
			build();
			
		} else {
			
			log.debug("employee details fetched : {}", employeeEntityFetched);
			
		}
			
		return employeeEntityFetched; 
		
	}
	
}