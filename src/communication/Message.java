package communication;

import java.io.Serializable;

import users.Employee;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private final Employee sender;
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
    
    @Override
    public String toString() {
    	return String.format(
                "Message Details:\n- Sender: %s %s (Email: %s)\n- Recipient: %s %s (Email: %s)\n- Content: %s\n- Status: %s",
                sender.getFirstName(),
                sender.getLastName(),
                sender.getEmail(),
                recipient.getFirstName(),
                recipient.getLastName(),
                recipient.getEmail(),
                content,
                isOpened ? "Opened" : "Unread"
            );
    }
}
