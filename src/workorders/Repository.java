// Performs major database functionalities like establishing JDBC connection, creating and executing SQL queries on the database.
package workorders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Exceptions.DataNotFoundException;
import Exceptions.WorkOrderCreationException;

public class Repository {

	Connection con = null;
	String url = "jdbc:mysql://us-cdbr-iron-east-02.cleardb.net/heroku_6f29f635298aa0b";
	String username = "b0b7df80b105da";
	String password = "c62c7656";

	public Repository() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
    public boolean isPresent(String name) {
		String sql = "select * from worker where worker.name = ?";
		WorkerDTO w = new WorkerDTO();
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,name);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				w.setName(rs.getString(1));
				w.setCompany(rs.getString(2));
				w.setEmail(rs.getString(3));
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if(w.getName() == name)
		    return true;
		else
			return false;
		
    }
	public List<WorkerDTO> getEmployee() {
		List<WorkerDTO> workers = new LinkedList<>();
		String sql = "select * from worker";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				WorkerDTO w = new WorkerDTO();
				w.setName(rs.getString(1));
				w.setCompany(rs.getString(2));
				w.setEmail(rs.getString(3));
				workers.add(w);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return workers;
	}

	public void createWorker(WorkerDTO w) {
		System.out.println("received request for creating worker");
		String sql = "insert into worker values (?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, w.getName());
			st.setString(2, w.getCompany());
			st.setString(3, w.getEmail());
			st.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteWorker(String name) {
		System.out.println("received request for deleting worker "+name);
		Repository r = new Repository();
		if(!r.isPresent(name))
			throw new DataNotFoundException("Record for user "+name.toString()+" not found and cannot be deleted");
		String sql = "delete from worker where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void createWorkOrder(WorkOrderDTO w) {
		System.out.println("received request for creating workorder");
		String sql = "insert into workorders values (?,?,?)";
		try {

			java.util.Date myDate = w.getDeadline();
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, w.getTitle());
			st.setString(2, w.getDescription());
			st.setDate(3, sqlDate);
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public List<FetchDTO> getWorkOrdersByUser(String empname) {
		List<FetchDTO> workorders = new ArrayList<>();
		String sql = "SELECT a.empname,w.description,w.deadline,w.title from assignment a, workorders w WHERE a.title=w.title and a.empname=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,empname);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				FetchDTO w = new FetchDTO();
				w.setEmpname(rs.getString(1));
				w.setTitle(rs.getString(4));
				w.setDescription(rs.getString(2));
				w.setDeadline(rs.getDate(3));
				workorders.add(w);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return workorders;
	}

	public void assignWorker(String empname,String titles) {
		
		System.out.println("received request for assigning workorder");
		Repository r = new Repository();
		if(!r.isPresent(empname))
			throw new DataNotFoundException("Record for user "+empname.toString()+" is not found and cannot be assigned");	
		try {
			String getsql = "select count(title) from assignment where title = ?";
			PreparedStatement getst = con.prepareStatement(getsql);
			getst.setString(1,titles);
			ResultSet rs = getst.executeQuery();
			rs.next();
			int rows = rs.getInt(1);
			System.out.println("Number of rows "+rows);
			if(rows <5)
			{
				String sql = "insert into assignment values (?,?)";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, empname);
				st.setString(2, titles);
				st.executeUpdate();
			}
			else
			{
				throw new WorkOrderCreationException("Cannot Assign more than 5 workers to same work order");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<AssignmentDTO> getAssignments() {
		List<AssignmentDTO> assignments = new ArrayList<>();
		String sql = "select * from assignment";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				AssignmentDTO w = new AssignmentDTO();
				w.setEmpName(rs.getString(1));
				w.setTitle(rs.getString(2));
				
				assignments.add(w);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return assignments;
	}

	public List<WorkOrderDTO> getWorkOrdersByDeadline() {
		List<WorkOrderDTO> assignments = new ArrayList<>();
		String sql = "select * from workorders order by deadline";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				WorkOrderDTO w = new WorkOrderDTO();
				w.setTitle(rs.getString(1));
				w.setDescription(rs.getString(2));
				w.setDeadline(rs.getDate(3));
				assignments.add(w);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return assignments;
	}
    
}
