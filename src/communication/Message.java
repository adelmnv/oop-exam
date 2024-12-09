package communication;

import users.Employee;

public class Message {
	private Employee sender;
	private final String content;
	private final Employee recipient; 
	private boolean isOpened;
	
	public Message(Employee sender, String content, Employee recipient) {
		this.sender = sender;
		this.content = content;
		this.recipient = recipient;
		this.isOpened = false;
	}

	public Employee getSender() {
		return sender;
	}

	public Employee getRecipient() {
		return recipient;
	}
	
	public String getContent() {
		return content;
	}
	
	public void markAsOpened() {
        this.isOpened = true;
    }
	
	public boolean getIsOpened() {
		return isOpened;
	}
}
