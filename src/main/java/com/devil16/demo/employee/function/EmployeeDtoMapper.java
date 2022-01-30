//package com.devil16.demo.employee.function;
//
//import java.util.function.Function;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.devil16.demo.employee.dto.EmployeeDto;
//import com.devil16.demo.employee.entity.EmployeeEntity;
//import com.devil16.demo.employee.service.EmployeeService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//@Scope("prototype")
//public class EmployeeDtoMapper implements Function<EmployeeDto, EmployeeEntity> {
//	
//	@Autowired
//	private EmployeeService employeeService;
//
//	@Override
//	public EmployeeEntity apply(EmployeeDto employeeDto) {
//		
//		try {
//			
//			return employeeService.convertDtoToEntity(employeeDto);
//			
//		} catch (Exception e) {
//			
//			log.error("Exception Type : {}", e.getClass().getName());
//			log.error("Exception Info : {}", e.getMessage());
//			
//			return EmployeeEntity.builder().build();
//		}
//
//	}
//
//}
