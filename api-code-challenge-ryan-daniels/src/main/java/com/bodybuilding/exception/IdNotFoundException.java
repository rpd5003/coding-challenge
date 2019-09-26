package com.bodybuilding.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1;
	
	public IdNotFoundException(String id){
		super("Resource Not Found with ID = " + id);
		System.out.println("ERROR: ID " + id + " resulted in " + HttpStatus.NOT_FOUND);
	}
}	
