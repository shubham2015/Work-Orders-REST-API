package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import workorders.*;

class workordertests {
	
	Repository r = new Repository();
	
	// Test for number of workers registered in the database
	@Test
	public void getWorkers() {
		List<WorkerDTO> workers = new ArrayList<>();
		workers = r.getEmployee();
		assertEquals("workers list",1,workers.size());
	}
    
	// Test for number of work orders assigned in the database for a particular user
	@Test
	public void getWorkOrders() {
		List<FetchDTO> workorders = new ArrayList<>();
		workorders = r.getWorkOrdersByUser("john");
		assertEquals("workers list",1,workorders.size());
	}
	// Test for all the work orders assigned to the workers
	@Test
	public void getAssignments() {
		List<AssignmentDTO> assignments = new ArrayList<>();
		assignments = r.getAssignments();
		assertEquals("workers list",1,assignments.size());
	}
}
