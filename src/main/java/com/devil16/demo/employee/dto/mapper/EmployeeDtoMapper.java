package com.devil16.demo.employee.dto.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import com.devil16.demo.employee.dto.EmployeeDto;
import com.devil16.demo.employee.entity.EmployeeEntity;

/**
 * EmployeeDtoMapper interface - 
 * 
 * houses abstract methods to map the EmployeeDto and EmployeeEntity beans with each other
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 */

/*
 * we are using the MapStruct(https://mapstruct.org) framework to map these two beans with each other.
 * 
 * MapStruct will override the abstract methods 
 * and create an implementation of this interface. 
 * 
 * the generated implementation can be found at target-generated-sources-annotations
 * 
 */

//the following annotation allows spring to inject the Mapper Implementation where required
@Mapper(componentModel = "spring",
builder=@Builder(disableBuilder = true),
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeDtoMapper {
	
	/**
	 * employeeDtoToEmployeeEntityMapper() - 
	 * 
	 * maps an EmployeeDto Object to an EmployeeEntity object
	 * 
	 * @param employeeDto
	 * @return EmployeeEntity object
	 */
	@Mappings({ @Mapping(source = "commitId", target = "commit_Id"),
				@Mapping(source = "firstName", target = "first_Name"),
				@Mapping(source = "lastName", target = "last_Name"),
				@Mapping(source = "type", target = "type"),
				@Mapping(source = "costBillLink", target = "cost_Bill_Link"),
				@Mapping(source = "costSettleLink", target = "cost_Settle_Link"),
				@Mapping(source = "designation", target = "designation"),
				@Mapping(source = "team", target = "team"),
				@Mapping(source = "lineOfBusiness", target = "line_Of_Business"),
				@Mapping(source = "emailId", target = "email_Id"),
				@Mapping(source = "phone", target = "phone"),
				@Mapping(source = "status", target = "status"),
				@Mapping(source = "dateOfJoining", target = "date_Of_Joining"),
				@Mapping(source = "lastWorkingDate", target = "last_Working_Date")
	})
	EmployeeEntity employeeDtoToEmployeeEntityMapper(EmployeeDto employeeDto);
	
	/**
	 * employeeEntityToEmployeeDtoMapper() - 
	 * 
	 * maps an EmployeeEntity Object to an EmployeeDto object
	 * 
	 * @param employeeEntity
	 * @return EmployeeDto object
	 */
	@Mappings({ @Mapping(source = "commit_Id", target = "commitId"),
				@Mapping(source = "first_Name", target = "firstName"),
				@Mapping(source = "last_Name", target = "lastName"),
				@Mapping(source = "type", target = "type"),
				@Mapping(source = "cost_Bill_Link", target = "costBillLink"),
				@Mapping(source = "cost_Settle_Link", target = "costSettleLink"),
				@Mapping(source = "designation", target = "designation"),
				@Mapping(source = "team", target = "team"),
				@Mapping(source = "line_Of_Business", target = "lineOfBusiness"),
				@Mapping(source = "email_Id", target = "emailId"),
				@Mapping(source = "phone", target = "phone"),
				@Mapping(source = "status", target = "status"),
				@Mapping(source = "date_Of_Joining", target = "dateOfJoining"),
				@Mapping(source = "last_Working_Date", target = "lastWorkingDate")
	})
	EmployeeDto employeeEntityToEmployeeDtoMapper(EmployeeEntity employeeEntity);

}