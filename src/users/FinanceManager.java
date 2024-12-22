package users;

import java.util.List;
import java.util.Scanner;

import communication.Message;
import enums.ManagerType;
import repositories.UserRepository;
import studying.Course;
import utils.IdGenerator;

public class FinanceManager extends Employee {
    private static final long serialVersionUID = 1L;
    private ManagerType managerType;

    public FinanceManager(String firstName, String lastName, String email, String password, int salary) {
        super(IdGenerator.generateUniqueId("FM"), firstName, lastName, email, password, salary);
        this.managerType = ManagerType.FINANCE;
    }

    public void displayFinanceManagerMenu() {
        System.out.println("Finance Manager functions:");
        System.out.println("0. Exit");
        System.out.println("1. Generate salary report");
        System.out.println("2. Generate scholarship report");
        System.out.println("3. Send Message to User by ID");
        System.out.println("4. View Received Messages");
    }


    public void generateSalaryReport() {
    	List<Employee> employees = UserRepository.getInstance().getUsersForType(Employee.class);
        for (Employee employee : employees) {
            int baseSalary = employee.getSalary();
            System.out.println(employee.getFullName() + " receives salary: " + baseSalary);
        }
    }
    
    public void generateScholarshipReport() {
        List<Student> students = UserRepository.getInstance().getUsersForType(Student.class);
        for (Student student : students) {
            boolean receivesScholarship = true;
            if(student.canHaveScholarship()) {
            	for (double totalMark : student.getAllTotalMarks()) {
                    if (totalMark < 70) {
                        student.setCanHaveScholarship(false);
                        receivesScholarship = false;
                        break;
                    }
                }
            }else {
            	receivesScholarship = false;
            }
            
            if (receivesScholarship) {
                System.out.println(student.getFullName() + " qualifies for a scholarship.");
            } else {
                System.out.println(student.getFullName() + " doesn't qualify for a scholarship.");
            }
        }
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }
    
    private void handleSendMessageToUser(Scanner scanner) {
        System.out.print("Enter user ID to send message: ");
        String userId = scanner.nextLine();
        
        System.out.print("Enter message content: ");
        String content = scanner.nextLine();
        
        sendMessageToUserById(userId, content);
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
    public void displayFunct() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                displayFinanceManagerMenu();
                int choice = getChoice(scanner);
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        generateSalaryReport();
                        break;
                    case 2:
                        generateScholarshipReport();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        UserRepository.getInstance().updateUser(this);
                        super.logout();
                        return;
                    case 3:
                    	handleSendMessageToUser(scanner);
                    	break;
                    case 4:
                    	viewReceivedMessages();
                    	break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            //scanner.close();
        }
   }
    
    private int getChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        try {
        	return scanner.nextInt();
        }catch(Exception e) {
        	return -1;
        }
        
    }
    
    @Override
    public String toString() {
        return String.format("+---------------------------------------------+\n" +
                             "| %-15s | %-28s |\n" +
                             "+---------------------------------------------+\n" +
                             "| %-15s | %-28s |\n" +
                             "| %-15s | %-28s |\n" +
                             "| %-15s | %-28s |\n" +
                             "| %-15s | %-28s |\n" +
                             "+---------------------------------------------+\n", 
                             "Manager ID", getId(),
                             "Full Name", getFullName(),
                             "Manager Type", managerType,
                             "Salary", getSalary(),
                             "Email", getEmail());
    }


}
