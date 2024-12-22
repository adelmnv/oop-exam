package users;

import java.util.List;
import java.util.Scanner;

import communication.Message;
import enums.Faculty;
import enums.ManagerType;
import enums.TeacherTitle;
import utils.IdGenerator;
import repositories.CourseRepository;
import repositories.StudentRepository;
import repositories.TeacherRepository;
import repositories.UserRepository;

public class Admin extends Employee {
    private static final long serialVersionUID = 1L;

    public Admin(String firstName, String lastName, String email, String password, int salary) {
        super(IdGenerator.generateUniqueId("A"), firstName, lastName, email, password, salary);
    }
    
    private void addUser(User user) {
        UserRepository.getInstance().addUser(user);
    }

    private void removeUser(User user) {
        UserRepository.getInstance().removeUser(user);
    }
    
    public void createUserFromInput(Scanner scanner) {
        System.out.print("Enter user type (student, manager, teacher, admin, dean, finance manager): ");
        String userType = scanner.nextLine().toLowerCase();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = createUserBasedOnType(scanner, userType, firstName, lastName, email, password);
        if (user != null) {
            addUser(user);
        } else {
            System.out.println("Invalid user type.");
        }
    }

    private User createUserBasedOnType(Scanner scanner, String userType, String firstName, String lastName, String email, String password) {
        int salary;
        switch (userType.toLowerCase()) {
            case "student":
                System.out.print("Enter year of study: ");
                int yearOfStudy = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter faculty: ");
                String facultyName = scanner.nextLine();
                Faculty faculty = Faculty.valueOf(facultyName.toUpperCase());
                System.out.print("Can have scholarship (true/false): ");
                boolean canHaveScholarship = scanner.nextBoolean();
                return new Student(firstName, lastName, email, password, yearOfStudy, faculty, canHaveScholarship);

            case "manager":
                System.out.print("Enter manager type (OR, FINANCE, DEPARTMENT): ");
                String managerTypeStr = scanner.nextLine();
                ManagerType managerType = ManagerType.valueOf(managerTypeStr.toUpperCase());
                System.out.print("Enter salary: ");
                salary = scanner.nextInt();
                scanner.nextLine();
                return new Manager(firstName, lastName, email, password, salary, managerType);

            case "teacher":
                System.out.print("Enter faculty: ");
                String teacherFaculty = scanner.nextLine();
                Faculty teacherFac = Faculty.valueOf(teacherFaculty.toUpperCase());
                System.out.print("Enter teacher title (LECTOR, TUTOR, PROFFESOR, SENIOR_LECTOR): ");
                String teacherTitleStr = scanner.nextLine();
                TeacherTitle teacherTitle = TeacherTitle.valueOf(teacherTitleStr.toUpperCase());
                System.out.print("Enter salary: ");
                salary = scanner.nextInt();
                scanner.nextLine();
                return new Teacher(firstName, lastName, email, password, salary, teacherFac, teacherTitle);

            case "admin":
                System.out.print("Enter salary: ");
                salary = scanner.nextInt();
                scanner.nextLine();
                return new Admin(firstName, lastName, email, password, salary);

            case "dean":
                System.out.print("Enter salary: ");
                salary = scanner.nextInt();
                scanner.nextLine();
                return new Dean(firstName, lastName, email, password, salary);

            case "finance manager":
                System.out.print("Enter salary: ");
                salary = scanner.nextInt();
                scanner.nextLine();
                return new FinanceManager(firstName, lastName, email, password, salary);

            default:
                return null;
        }
    }

    public void viewLogs() {
        System.out.println("admin view the log");
    }
    
    private void displayAdminMenu() {
        System.out.println("Admin Functions:");
        System.out.println("0. Exit");
        System.out.println("1. Create User");
        System.out.println("2. Remove User");
        System.out.println("3. View All Users");
        System.out.println("4. View All Courses");
        System.out.println("5. View All Students");
        System.out.println("6. View All Teachers");
        System.out.println("7. View All Managers");
        System.out.println("8. Send Message to User by ID");
        System.out.println("9. View Received Messages");
    }

    private int getChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void removeUserMenu(Scanner scanner) {
        System.out.print("Enter user ID to remove: ");
        String userId = scanner.next();
        User user = UserRepository.getInstance().getUserById(userId);
        if (user != null) {
            removeUser(user);
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void viewAllUsers() {
        System.out.println("All Users:");
        UserRepository.getInstance().getAllUsers().forEach(System.out::println);
    }

    private void viewAllCourses() {
        System.out.println("All Courses:");
        CourseRepository.getInstance().getAllCourses().forEach(System.out::println);
    }

    private void viewAllStudents() {
        System.out.println("All Students:");
        StudentRepository.getInstance().getAllStudents().forEach(System.out::println);
    }

    private void viewAllTeachers() {
        System.out.println("All Teachers:");
        TeacherRepository.getInstance().getAllTeachers().forEach(System.out::println);
    }

    private void viewAllManagers() {
        System.out.println("All Managers:");
        UserRepository.getInstance().getUsersForType(Manager.class).forEach(System.out::println);
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
            }
        }
    }

    @Override
    public void displayFunct() {
        Scanner scanner = new Scanner(System.in);
        try {
        	while (true) {
                displayAdminMenu();
                int choice = getChoice(scanner);
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        createUserFromInput(scanner);
                        break;
                    case 2:
                        removeUserMenu(scanner);
                        break;
                    case 3:
                        viewAllUsers();
                        break;
                    case 4:
                        viewAllCourses();
                        break;
                    case 5:
                        viewAllStudents();
                        break;
                    case 6:
                        viewAllTeachers();
                        break;
                    case 7:
                        viewAllManagers();
                        break;
                    case 8:
                    	handleSendMessageToUser(scanner);
                        break;
                    case 9:
                    	viewReceivedMessages();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        UserRepository.getInstance().updateUser(this);
                        super.logout();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
        	scanner.close();
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
                             "+---------------------------------------------+\n",
                             "Admin ID", getId(),
                             "Full Name", getFullName(),
                             "Salary", getSalary(),
                             "Email", getEmail());
    }

}
