package test;


import research.Researcher;
import users.Employee;
import users.Student;
import communication.Message;
import communication.MessageRepository;
import communication.Request;
import communication.RequestRepository;

public class EmployeeTest {
    public static void main(String[] args) {
        // Create some Employee objects
        Employee employee1 = new Employee("E123", "John", "Doe", "john.doe@example.com", "password123", 50000) {
            @Override
            public void displayFunct() {
                System.out.println("Employee function: Manage team and research projects.");
            }
        };
        
        Employee employee2 = new Employee("E124", "Jane", "Smith", "jane.smith@example.com", "password456", 55000) {
            @Override
            public void displayFunct() {
                System.out.println("Employee function: Handle client relations.");
            }
        };

        // Create a Student object
        Student student1 = new Student("Mark", "Twain", "mark.twain@example.com", "password789", "S001", 3, null, true);

        // Test sending a message from employee1 to employee2
        employee1.sendMessage(employee2, "Hello Jane, let's discuss the new project.");
        
        // Test viewing messages
        System.out.println("Employee1's sent messages:");
        for (Message message : employee1.getSentMessages()) {
            System.out.println(message.getContent());
        }

        System.out.println("Employee2's received messages:");
        for (Message message : employee2.getReceivedMessages()) {
            System.out.println(message.getContent());
        }

        // Test creating a request
        employee1.createRequest("Request for approval on project proposal.");
        
        // Test viewing sent requests
        System.out.println("Employee1's sent requests:");
        for (Request request : employee1.getSentRequests()) {
            System.out.println(request.getContent());
        }

        // Demonstrate becoming a researcher
        Researcher researcher = employee1.becomeResearcher();
        System.out.println("Researcher Name: " + researcher.getResearcherName());

       
    }
}
