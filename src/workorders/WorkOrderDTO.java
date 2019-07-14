// DTO class to hold information about title, description and deadline of the work order.

package workorders;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class WorkOrderDTO {

	String title;
	String description;
	Date deadline;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	
}
