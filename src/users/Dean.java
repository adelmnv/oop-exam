package users;

import java.util.List;
import java.util.ArrayList;

import communication.Complaint;
import communication.ComplaintRepository;
import communication.Request;
import communication.RequestRepository;
import utils.IdGenerator;

public class Dean extends Employee{
	private static final long serialVersionUID = 1L;
	private List<Complaint> complaints = new ArrayList<>();
	private List<Request> requests = new ArrayList<>();

	public Dean(String firstName, String lastName, String email, String password, int salary) {
		super(IdGenerator.generateUniqueId("D"),firstName, lastName, email, password, salary);
	}

	@Override
	public void displayFunct() {
		// TODO Auto-generated method stub
		
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	
	public void fetchUnansweredComplaints() {
        List<Complaint> complaints = ComplaintRepository.getUnansweredComplaints();
        System.out.println("Fetched " + complaints.size() + " unanswered complaints.");
        this.setComplaints(complaints);
    }
	
	public void fetchUnsignedRequests() {
        List<Request> requests = RequestRepository.getUnsignedRequests();
        System.out.println("Fetched " + requests.size() + " insigned requests.");
        this.setRequests(requests);
    }

    public void answerComplaint(Complaint complaint, String messageContent) {
        if (complaint.isAnswered()) {
            System.out.println("This complaint has already been answered.");
            return;
        }
        complaint.setAnswered(true);
        super.sendMessage(complaint.getSender(), messageContent);
        System.out.println("Dean responded to complaint from teacher: " + complaint.getSender().getFirstName());
    }
    
    public void signRequest(Request request) {
    	if (request.isSigned()) {
            System.out.println("This request has already been signed.");
            return;
        }
        request.setSigned(true);
        super.sendMessage(request.getSender(), "Dean signed your request");
        System.out.println("Dean signed request from employee: " + request.getSender().getFirstName());
    }

}
