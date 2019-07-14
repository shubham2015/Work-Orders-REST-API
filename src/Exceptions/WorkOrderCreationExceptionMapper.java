package Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import workorders.ErrorMessage;

@Provider
public class WorkOrderCreationExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {

		ErrorMessage message = new ErrorMessage(ex.getMessage(), 404);
		return Response.status(Status.METHOD_NOT_ALLOWED).entity(message).type("application/xml").build();
	}

}
