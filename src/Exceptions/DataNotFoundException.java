package Exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import workorders.ErrorMessage;


public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public DataNotFoundException(String message) {	
		super(message);
		System.out.println("exception captures");
	}


	

	
	
}
