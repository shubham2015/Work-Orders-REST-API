// DTO class for holding worker name and the work order title assigned
package workorders;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AssignmentDTO {

	String empname;
	String title;
	public String getEmpName() {
		return empname;
	}
	public void setEmpName(String empname) {
		this.empname = empname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
