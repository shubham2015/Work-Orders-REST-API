package Exceptions;

public class WorkOrderCreationException extends RuntimeException{
	private static final long serialVersionUID = 1L;


	public WorkOrderCreationException(String message) {
		
		super(message);
		
	}

}
