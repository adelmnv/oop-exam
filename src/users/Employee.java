package users;

import java.util.ArrayList;
import java.util.List;

import communication.Message;
import communication.Request;
import repositories.MessageRepository;
import repositories.RequestRepository;
import repositories.ResearcherRepository;
import research.EmployeeResearcher;
import research.Researcher;

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
	
	public String getFullName() {
		return super.getFirstName() + " " + super.getLastName();
	}
	
	public void sendMessage(Employee recipient, String content) {
		Message message = new Message(this, content, recipient);
        int messageId = MessageRepository.getInstance().addMessage(message);
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
            messages.add(MessageRepository.getInstance().getMessageById(id));
        }
        return messages;
    }

    public List<Message> getSentMessages() {
        List<Message> messages = new ArrayList<>();
        for (int id : sentMessageIds) {
            messages.add(MessageRepository.getInstance().getMessageById(id));
        }
        return messages;
    }
    
    public void  createRequest(String content) {
        Request request = new Request(this, content);
        int requestId = RequestRepository.getInstance().addRequest(request);
        this.sentRequestIds.add(requestId);
    }
    
    public List<Request> getSentRequests() {
        List<Request> requests = new ArrayList<>();
        for (int id : sentRequestIds) {
            requests.add(RequestRepository.getInstance().getRequestById(id));
        }
        return requests;
    }
    
    
    public abstract void displayFunct();
    
    public void becomeResearcher() {
    	ResearcherRepository.getInstance().addResearcher(this);
        System.out.println(getFullName() + " is now a researcher.");
    }

}
