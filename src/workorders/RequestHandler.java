
// Handles all the requests made to the server and calls appropriate method in the Repository class to perform those operations assigned.
package workorders;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Exceptions.DataNotFoundException;

@Path("api")
public class RequestHandler {
	
	Repository r = new Repository();

	// Create a new worker record
	@POST
	@Path("createworker")
	@Consumes(MediaType.APPLICATION_XML	)
	public void createWorker(WorkerDTO w) {
		//System.out.println("sending request for worker");
		r.createWorker(w);
		
	}
	//Get all the workers who are registered
	@GET
	@Path("workers")
	@Produces(MediaType.APPLICATION_XML)
	public List<WorkerDTO> getAllWorkers(){
		
		return r.getEmployee();
		
	}
	//Deleting a worker record
	@DELETE
	@Path("deleteworker/{name}")
	public void deleteWorker(@PathParam("name") String name){
		
		r.deleteWorker(name);
	}
	// Creating a work order for only the workers whose records are present
	@POST
	@Path("createworkorder")
	@Consumes(MediaType.APPLICATION_XML	)
	public void createWorkOrder(WorkOrderDTO w) {
		System.out.println("sending request for work Order");
		r.createWorkOrder(w);	
	}
	// Assigning a particular work order to a employee
	@POST
	@Path("assign/{empname}/{task}")
	@Consumes(MediaType.APPLICATION_XML)
	public void assignWorker(@PathParam("empname") String emp, @PathParam("task") String title) {
		System.out.println("Assigning a worker to an order");
		r.assignWorker(emp,title);
	}
	
	//Return all the work orders that have been assigned
	@GET
	@Path("assignments")
	@Produces(MediaType.APPLICATION_XML)
	public List<AssignmentDTO> getAssignments(){
		return r.getAssignments();
	}
	
	//Retrieve workorders by username
	@GET
	@Path("workorders/{empname}")
	@Produces(MediaType.APPLICATION_XML)
	public List<FetchDTO> getWorkOrders(@PathParam("empname") String empname) {
		System.out.println("sending request for getting workorders for a particular user");
		List<FetchDTO> workorders = new ArrayList<FetchDTO>();
		workorders = r.getWorkOrdersByUser(empname);
		if(workorders.size()==0)
			throw new DataNotFoundException("Record for user "+empname.toString()+" not found");
		return r.getWorkOrdersByUser(empname);
	}
	
	// Retrieve workorders sorted by deadline
	@GET
	@Path("workordersbydeadline")
	@Produces(MediaType.APPLICATION_XML)
	public List<WorkOrderDTO> getWorkOrdersByDeadline() {
		System.out.println("sending request for getting workorders sorted by deadline");
		List<WorkOrderDTO> workorders = new ArrayList<WorkOrderDTO>();
		workorders = r.getWorkOrdersByDeadline();
		if(workorders.size()==0)
			throw new DataNotFoundException("Work Orders are not found");
		return r.getWorkOrdersByDeadline();
	}
}
