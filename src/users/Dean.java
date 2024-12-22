package users;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import communication.Complaint;
import communication.Message;
import communication.Request;
import repositories.ComplaintRepository;
import repositories.RequestRepository;
import repositories.UserRepository;
import utils.IdGenerator;

public class Dean extends Employee{
	private static final long serialVersionUID = 1L;
	private List<Complaint> complaints = new ArrayList<>();
	private List<Request> requests = new ArrayList<>();

	public Dean(String firstName, String lastName, String email, String password, int salary) {
		super(IdGenerator.generateUniqueId("D"),firstName, lastName, email, password, salary);
	}

    public void displayDeanMenu() {
        System.out.println("Dean Functions:");
        System.out.println("0. Exit");
        System.out.println("1. Fetch Unanswered Complaints");
        System.out.println("2. Fetch Unsigned Requests");
        System.out.println("3. Answer a Complaint");
        System.out.println("4. Sign a Request");
        System.out.println("5. View All Complaints");
        System.out.println("6. View All Requests");
        System.out.println("7. Send Message to User by ID");
        System.out.println("8. View Received Messages");
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
        List<Complaint> complaints = ComplaintRepository.getInstance().getUnansweredComplaints();
        System.out.println("Fetched " + complaints.size() + " unanswered complaints.");
        this.setComplaints(complaints);
    }
	
	public void fetchUnsignedRequests() {
        List<Request> requests = RequestRepository.getInstance().getUnsignedRequests();
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
    
    @Override
    public void displayFunct() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayFunct();
            System.out.print("Enter your choice: ");
            choice = getChoice(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    fetchUnansweredComplaints();
                    break;
                case 2:
                    fetchUnsignedRequests();
                    break;
                case 3:
                    answerComplaintMenu(scanner);
                    break;
                case 4:
                    signRequestMenu(scanner);
                    break;
                case 5:
                    viewAllComplaints();
                    break;
                case 6:
                    viewAllRequests();
                    break;
                case 7:
                    handleSendMessageToUser(scanner);
                    break;
                case 8:
                    viewReceivedMessages();
                    break;
                case 0:
                    System.out.println("Exiting Dean's menu.");
                    UserRepository.getInstance().updateUser(this);
                    super.logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        //scanner.close();
    }
    
    private int getChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        try {
        	return scanner.nextInt();
        }catch(Exception e) {
        	return -1;
        }
        
    }

    private void handleSendMessageToUser(Scanner scanner) {
        System.out.print("Enter user ID to send message: ");
        String userId = scanner.nextLine();
        
        System.out.print("Enter message content: ");
        String content = scanner.nextLine();
        
        sendMessageToUserById(userId, content);
    }

    private void answerComplaintMenu(Scanner scanner) {
        if (complaints.isEmpty()) {
            System.out.println("No complaints to answer.");
            return;
        }
        System.out.println("Select a complaint to answer:");
        for (int i = 0; i < complaints.size(); i++) {
            System.out.println(i + ". " + complaints.get(i).getSender().getFirstName() + " - " + complaints.get(i));
        }
        int complaintIndex = scanner.nextInt();
        scanner.nextLine();
        if (complaintIndex >= 0 && complaintIndex < complaints.size()) {
            System.out.println("Enter your response message:");
            String responseMessage = scanner.nextLine();
            answerComplaint(complaints.get(complaintIndex), responseMessage);
        } else {
            System.out.println("Invalid complaint index.");
        }
    }

    private void signRequestMenu(Scanner scanner) {
        if (requests.isEmpty()) {
            System.out.println("No requests to sign.");
            return;
        }
        System.out.println("Select a request to sign:");
        for (int i = 0; i < requests.size(); i++) {
            System.out.println(i + ". " + requests.get(i).getSender().getFirstName() + " - " + requests.get(i));
        }
        int requestIndex = scanner.nextInt();
        if (requestIndex >= 0 && requestIndex < requests.size()) {
            signRequest(requests.get(requestIndex));
        } else {
            System.out.println("Invalid request index.");
        }
    }

    private void viewAllComplaints() {
        if (complaints.isEmpty()) {
            System.out.println("No complaints available.");
        } else {
            System.out.println("All Complaints:");
            for (Complaint complaint : complaints) {
                System.out.println(complaint.getSender().getFirstName() + " - " + complaint);
            }
        }
    }

    private void viewAllRequests() {
        if (requests.isEmpty()) {
            System.out.println("No requests available.");
        } else {
            System.out.println("All Requests:");
            for (Request request : requests) {
                System.out.println(request.getSender().getFirstName() + " - " + request);
            }
        }
    }
    
    public void sendMessageToUserById(String userId, String content) {
        User user = UserRepository.getInstance().getUserById(userId);
        if (user instanceof Employee) {
            super.sendMessage((Employee) user, content);
            System.out.println("Message sent to " + user.getFirstName() + " " + user.getLastName());
        } else {
            System.out.println("User with ID " + userId + " is not an employee.");
        }
    }

    public void viewReceivedMessages() {
        List<Message> messages = super.getReceivedMessages();
        if (messages.isEmpty()) {
            System.out.println("No messages received.");
        } else {
            System.out.println("Received Messages:");
            for (Message message : messages) {
                System.out.println(message.toString());
                message.markAsOpened();
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder complaintsInfo = new StringBuilder();
        if (complaints.isEmpty()) {
            complaintsInfo.append("No complaints.");
        } else {
            for (Complaint complaint : complaints) {
                complaintsInfo.append("- ").append(complaint.toString()).append("\n");
            }
        }

        StringBuilder requestsInfo = new StringBuilder();
        if (requests.isEmpty()) {
            requestsInfo.append("No requests.");
        } else {
            for (Request request : requests) {
                requestsInfo.append("- ").append(request.toString()).append("\n");
            }
        }

        return String.format("+---------------------------------------------+\n" +
                             "| %-15s | %-28s |\n" +
                             "+---------------------------------------------+\n" +
                             "| %-15s | %-28s |\n" +
                             "| %-15s | %-28s |\n" +
                             "| %-15s | %-28s |\n" +
                             "| %-15s | %-28s |\n" +
                             "| %-15s | %-28s |\n" +
                             "+---------------------------------------------+\n",
                             "Dean ID", getId(),
                             "Full Name", getFullName(),
                             "Salary", getSalary(),
                             "Email", getEmail(),
                             "Complaints", complaintsInfo.toString().trim(),
                             "Requests", requestsInfo.toString().trim());
    }


}
