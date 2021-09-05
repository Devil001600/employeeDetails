package com.devil16.demo.employee.service;

import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.entity.EmployeeEntity;

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
	 * convertDtoToEntity() - 
	 * 
	 * creates an EmployeeEntity object from the values of an EmployeeDto object  
	 * 
	 * @param tpsDevDto
	 * @return EmployeeEntity object
	 */
	public EmployeeEntity convertDtoToEntity(EmployeeDto employeeDto);
	
	/**
	 * getEntityById() - 
	 * 
	 * fetches the details of an Employee from the dataBase by their employeeId
	 * 
	 * @param tpsDev
	 * @return EmployeeEntity object
	 * @throws Exception
	 */
	public EmployeeEntity getEntityById(EmployeeEntity employeeEntity) throws Exception;
	
	/**
	 * convertEntityToDto() - 
	 * 
	 * creates an EmployeeDto object from the values of an EmployeeEntity object
	 * 
	 * @param employeeEntity
	 * @return
	 */
	public EmployeeDto convertEntityToDto(EmployeeEntity employeeEntity);

}