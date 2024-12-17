package communication;

import java.io.Serializable;

import users.Employee;

public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	private final Employee sender;
	private final String content; 
	private boolean isSigned;
	
	public Request(Employee sender, String content) {
		this.sender = sender;
		this.content = content;
		this.isSigned = false;
	}
	
	public Employee getSender() {
		return sender;
	}
	
	public String getContent() {
		return content;
	}
	
	public boolean isSigned() {
		return isSigned;
	}
	
	public void setSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}
	
	

}
