
// DTO class to hold work orders and the the worker assigned towards
package workorders;


import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class FetchDTO {

	String empname;
	String description;
	Date deadline;
	String title;
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
