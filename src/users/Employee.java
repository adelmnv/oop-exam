package users;

import java.util.ArrayList;
import java.util.List;

import communication.Message;
import communication.MessageRepository;
import communication.Request;
import communication.RequestRepository;

public abstract class Employee extends User{
	private static final long serialVersionUID = 1L;
	private int salary;
	private List<Integer> receivedMessageIds = new ArrayList<>();
    private List<Integer> sentMessageIds = new ArrayList<>();
    private List<Integer> sentRequestIds = new ArrayList<>();

	public Employee(String id, String firstName, String lastName, String email, String password, int salary) {
		super(id, firstName, lastName, email, password);
		this.salary = salary;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void sendMessage(Employee recipient, String content) {
		Message message = new Message(this, content, recipient);
        int messageId = MessageRepository.addMessage(message);
        this.sentMessageIds.add(messageId);
        recipient.getReceivedMessageIds().add(messageId);
    }

    public List<Integer> getReceivedMessageIds() {
        return receivedMessageIds;
    }

    public List<Integer> getSentMessageIds() {
        return sentMessageIds;
    }
    
    public List<Message> getReceivedMessages() {
        List<Message> messages = new ArrayList<>();
        for (int id : receivedMessageIds) {
            messages.add(MessageRepository.getMessageById(id));
        }
        return messages;
    }

    public List<Message> getSentMessages() {
        List<Message> messages = new ArrayList<>();
        for (int id : sentMessageIds) {
            messages.add(MessageRepository.getMessageById(id));
        }
        return messages;
    }
    
    public void  createRequest(String content) {
        Request request = new Request(this, content);
        int requestId = RequestRepository.addRequest(request);
        this.sentRequestIds.add(requestId);
    }
    
    public abstract void displayFunct();

}
