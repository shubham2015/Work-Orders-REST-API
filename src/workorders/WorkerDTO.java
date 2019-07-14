// DTO class to hold information about the worker, name, company and email address
package workorders;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorkerDTO {

	String name;
	String company;
	String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
