package com.devil16.demo.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EmployeeDetails - 
 * 
 * houses the main()
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2021-02-11
 * 
 */

@SpringBootApplication(scanBasePackages = {"com.devil16.demo.*"})
public class EmployeeDetails {
	
	/**
	 * main() - 
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetails.class, args);
	}

}
