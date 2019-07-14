// Class to hold error messages, its status code and the message content
package workorders;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
  String errorMessage;
  int errorCode;
  
  public ErrorMessage() {
	super();
	// TODO Auto-generated constructor stub
}
public ErrorMessage(String errorMessage,int errorCode) {
	  this.errorCode = errorCode;
	  this.errorMessage = errorMessage;
  }
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
public int getErrorCode() {
	return errorCode;
}
public void setErrorCode(int errorCode) {
	this.errorCode = errorCode;
}
}
