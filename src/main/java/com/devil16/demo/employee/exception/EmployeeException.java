package com.devil16.demo.employee.exception;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class EmployeeException extends Exception {
	
	/*
	 * classes that implement the java.io.Serializable (directly or via their parent class) can be serialized
	 * 
	 * serialization is the process of converting the current state of an object to a byte-stream;
	 * de-serialization is the process of recreating object from the byte-stream
	 * 
	 * the Serialization runtime associates a version number with each Serializable class called a SerialVersionUID, 
	 * which is used during de-serialization to verify that 
	 * sender and receiver of a serialized object have loaded classes for that object which are compatible with respect to serialization 
	 * if the receiver has loaded a class for the object that has different UID than that of corresponding sender’s class, 
	 * the de-serialization will result in an InvalidClassException 
	 * a serializable class can declare its own UID explicitly by declaring a field name.
	 * it must be static, final and of type long
	 */
	
	/**
	 * serialVersionUID to convey the current version of the EmployeeException class to the JVM
	 */
	@Builder.Default
	private static final long serialVersionUID = 1L;
	
	/**
	 * application Error Code
	 */
	@Builder.Default
	private String errorCode = StringUtils.EMPTY;
	
	/**
	 * application Error Message
	 */
	@Builder.Default
	private String errorDescription = StringUtils.EMPTY;
	
	/**
	 * system generated exception Object
	 */
	@Builder.Default
	private Throwable throwable = null;
	
	/**
	 * request Objects that caused the exception
	 */
	@Singular
	private List<Object> requestObjects;
	
	/**
	 * service component that ran into the exception
	 */
	@Builder.Default
	private EmployeeExceptionResource exceptionResource = EmployeeExceptionResource.None;
	
	/**
	 * EmployeeException() - 
	 * 
	 * constructor to initialize with a Throwable
	 * 
	 * @param t Throwable
	 */
	public EmployeeException(Throwable t) {
		super(t);
	}
	
	/**
	 * EmployeeException() - 
	 * 
	 * constructor to initialize with a String
	 * 
	 * @param message String
	 */
	public EmployeeException(String message) {
		super(message);
	}
	
	
	/**
	 * EmployeeException() - 
	 * 
	 * constructor to initialize with a String and a Throwable
	 * 
	 * @param message String
	 * @param t Throwable
	 */
	public EmployeeException(String message, Throwable t) {
		super(message, t);
	}
	
}
