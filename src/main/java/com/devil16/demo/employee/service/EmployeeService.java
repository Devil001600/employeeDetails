package com.devil16.demo.employee.service;

import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.entity.EmployeeEntity;
import com.devil16.demo.employee.exception.EmployeeException;

/**
 * EmployeeService interface -
 *  
 * this is the Service Layer interface; 
 * it houses business and service logic to manipulate the employee_details table and expose it to the user
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 */

public interface EmployeeService {
	
	/**
	 * getEmployeeId() - 
	 * 
	 * fetches an Employee's details by the EmployeeId
	 * 
	 * @param employeeDto
	 * @return EmployeeDto object
	 * @throws EmployeeException 
	 */
	public EmployeeDto getEmployeeById(EmployeeDto employeeDto) throws EmployeeException;
	
	/**
	 * convertDtoToEntity() - 
	 * 
	 * creates an EmployeeEntity object from the values of an EmployeeDto object  
	 * 
	 * @param employeeDto
	 * @return EmployeeEntity object
	 * @throws EmployeeException 
	 */
	public EmployeeEntity convertDtoToEntity(EmployeeDto employeeDto) throws EmployeeException;
	
	/**
	 * getEntityById() - 
	 * 
	 * fetches the details of an Employee from the dataBase by their employeeId
	 * 
	 * @param employeeEntity
	 * @return EmployeeEntity object
	 * @throws EmployeeException
	 */
	public EmployeeEntity getEntityById(EmployeeEntity employeeEntity) throws EmployeeException;
	
	/**
	 * convertEntityToDto() - 
	 * 
	 * creates an EmployeeDto object from the values of an EmployeeEntity object
	 * 
	 * @param employeeEntity
	 * @return
	 * @throws EmployeeException 
	 */
	public EmployeeDto convertEntityToDto(EmployeeEntity employeeEntity) throws EmployeeException;

}