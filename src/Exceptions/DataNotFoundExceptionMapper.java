package Exceptions;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import workorders.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
	@Override
	public Response toResponse(DataNotFoundException ex) {
		System.out.println("bitch");
		ErrorMessage message = new ErrorMessage(ex.getMessage(),404);
		// TODO Auto-generated method stub
		
		return Response.status(Status.NOT_FOUND).entity(message).type("application/xml").build();
	}

}
