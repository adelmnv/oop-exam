package users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import communication.Message;
import communication.Request;
import enums.LessonType;
import enums.ManagerType;
import enums.NewsTopic;
import news.News;
import news.NewsManager;
import repositories.CourseRepository;
import repositories.RequestRepository;
import repositories.StudentRepository;
import repositories.TeacherRepository;
import repositories.UserRepository;
import studying.Course;
import studying.Lesson;
import utils.IdGenerator;

public class Manager extends Employee {
    private static final long serialVersionUID = 1L;
    private ManagerType managerType;
    private List<Request> signedRequests = new ArrayList<>();

    public Manager(String firstName, String lastName, String email, String password, int salary, ManagerType managerType) {
        super(IdGenerator.generateUniqueId("M"), firstName, lastName, email, password, salary);
        this.managerType = managerType;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    public List<Request> getSignedRequests() {
        return signedRequests;
    }

    public void setSignedRequests(List<Request> signedRequests) {
        this.signedRequests = signedRequests;
    }
    
    public void displayManagerMenu() {
    	System.out.println("Manager Functions");
        System.out.println("1. Create Lessons for a Course");
        System.out.println("2. Assign Course to a Teacher");
        System.out.println("3. View Teacher Info");
        System.out.println("4. View Student Info");
        System.out.println("5. View Transcript");
        System.out.println("6. Create Report");
        System.out.println("7. Manage News");
        System.out.println("8. View Signed Requests");
        System.out.println("9. View All Students");
        System.out.println("10. View All Courses");
        System.out.println("11. View All Teachers");
        System.out.println("12. Fetch Unsigned Requests");
        System.out.println("13. Send Message to User by ID");
        System.out.println("14. View Received Messages");
        System.out.println("0. Exit");
    }

    @Override
    public void displayFunct() {
    	Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = getChoice(scanner);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleCreateLessons(scanner);
                    break;
                case 2:
                    handleAssignCourse(scanner);
                    break;
                case 3:
                    handleViewTeacherInfo(scanner);
                    break;
                case 4:
                    handleViewStudentInfo(scanner);
                    break;
                case 5:
                    handleViewTranscript(scanner);
                    break;
                case 6:
                    handleCreateReport(scanner);
                    break;
                case 7:
                    manageNews();
                    break;
                case 8:
                    viewSignedRequest();
                    break;
                case 9:
                    viewStudents();
                    break;
                case 10:
                    viewCourses();
                    break;
                case 11:
                    viewTeachers();
                    break;
                case 12:
                    fetchUnsignedRequests();
                    break;
                case 13:
                	handleSendMessageToUser(scanner);
                    break;
                case 14:
                	viewReceivedMessages();
                    break;
                case 0:
                    System.out.println("Exiting Manager Menu.");
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
    
    private void handleCreateLessons(Scanner scanner) {
        System.out.print("Enter the course code to create lessons for: ");
        String courseCode = scanner.nextLine();
        Course course = CourseRepository.getInstance().getCourseByCode(courseCode);

        if (course != null) {
            createLessonsForCourse(course);
        } else {
            System.out.println("Course not found. Please try again.");
        }
    }

    public void createLessonsForCourse(Course course) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of lessons for the course: " + course.getCourseName());
        int lessonCount = scanner.nextInt();
        scanner.nextLine();
        List<Lesson> lessons = new ArrayList<>();
        List<Teacher> teachersForCourse = TeacherRepository.getInstance().getAllTeachersByCourse(course);
        
        if (teachersForCourse.isEmpty()) {
            System.out.println("No teachers are assigned to this course. Please assign a teacher first.");
            return;
        }

        System.out.println("Select a teacher for the lessons:");
        for (int i = 0; i < teachersForCourse.size(); i++) {
            Teacher teacher = teachersForCourse.get(i);
            System.out.println((i + 1) + ". " + teacher.getFullName());
        }

        int teacherChoice = scanner.nextInt();
        scanner.nextLine();
        Teacher selectedTeacher = teachersForCourse.get(teacherChoice - 1);

        for (int i = 0; i < lessonCount; i++) {
            System.out.println("Lesson " + (i + 1) + ":");
            System.out.print("Enter the room number: ");
            String room = scanner.nextLine();
            System.out.print("Enter the day of the week: ");
            String dayOfWeek = scanner.nextLine();
            System.out.print("Enter the time of the lesson: ");
            String time = scanner.nextLine();
            System.out.print("Enter the lesson type (1 - Lecture, 2 - Practice): ");
            int lessonTypeChoice = scanner.nextInt();
            scanner.nextLine();
            LessonType lessonType = (lessonTypeChoice == 1) ? LessonType.LECTURE : LessonType.PRACTICE;
            Lesson lesson = new Lesson(room, dayOfWeek, time, lessonType, selectedTeacher);
            lessons.add(lesson);
        }

        course.setLessons(lessons);
        System.out.println("Lessons successfully added to the course: " + course.getCourseName());
        scanner.close();
    }


//    public void approveRegistration(Student student) {
//        StudentRepository studentRepository = StudentRepository.getInstance();
//        studentRepository.approveStudentRegistration(student);
//        System.out.println("Registration approved for student: " + student.getFirstName() + " " + student.getLastName());
//    }
    
    private void handleAssignCourse(Scanner scanner) {
        System.out.print("Enter the teacher ID: ");
        String teacherId = scanner.nextLine();
        Teacher teacher = TeacherRepository.getInstance().getTeacherById(teacherId);

        System.out.print("Enter the course code: ");
        String courseCode = scanner.nextLine();
        Course course = CourseRepository.getInstance().getCourseByCode(courseCode);

        if (teacher != null && course != null) {
            assignCourseToTeacher(teacher, course);
        } else {
            System.out.println("Invalid teacher ID or course code. Please try again.");
        }
    }
    
    public void assignCourseToTeacher(Teacher teacher, Course course) {
        if (!teacher.getCourseCodes().contains(course.getCourseCode())) {
            teacher.addCourse(course.getCourseCode());
            TeacherRepository.getInstance().addTeacher(teacher);
            System.out.println("Course " + course.getCourseName() + " assigned to teacher " + teacher.getFirstName() + " " + teacher.getLastName());
        } else {
            System.out.println("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " is already teaching course " + course.getCourseName());
        }
    }
    
    private void handleViewTeacherInfo(Scanner scanner) {
        System.out.print("Enter the teacher ID: ");
        String teacherId = scanner.nextLine();
        Teacher teacher = TeacherRepository.getInstance().getTeacherById(teacherId);

        if (teacher != null) {
            System.out.println(viewTeacherInfo(teacher));
        } else {
            System.out.println("Teacher not found.");
        }
    }

    public String viewTeacherInfo(Teacher teacher) {
        return "Teacher Info: " + teacher.getFirstName() + " " + teacher.getLastName() + ", Email: " + teacher.getEmail();
    }

    private void handleViewStudentInfo(Scanner scanner) {
        System.out.print("Enter the student ID: ");
        String studentId = scanner.nextLine();
        Student student = StudentRepository.getInstance().findStudentById(studentId);

        if (student != null) {
            System.out.println(viewStudentInfo(student));
        } else {
            System.out.println("Student not found.");
        }
    }
    
    public String viewStudentInfo(Student student) {
        return "Student Info: " + student.getFirstName() + " " + student.getLastName() + ", Email: " + student.getEmail();
    }

    private void handleViewTranscript(Scanner scanner) {
        System.out.print("Enter the student ID: ");
        String studentId = scanner.nextLine();
        Student student = StudentRepository.getInstance().findStudentById(studentId);

        if (student != null) {
            viewTranscript(student);
        } else {
            System.out.println("Student not found.");
        }
    }
    
    public void viewTranscript(Student student) {
        System.out.println("Transcript for " + student.getFirstName() + " " + student.getLastName() + ":");
        student.viewTranscript();
    }
    
    private void handleCreateReport(Scanner scanner) {
        System.out.print("Enter the report content: ");
        String reportContent = scanner.nextLine();
        createReport(reportContent);
    }

    public void createReport(String report) {
        String reportTitle = "Monthly Report: " + new Date().toString();
        String reportContent = "Report Content:\n" + report;
        String reportFooter = "\n--- End of Report ---";

        System.out.println("========== REPORT ==========");
        System.out.println("Title: " + reportTitle);
        System.out.println(reportContent);
        System.out.println(reportFooter);
    }

    public void manageNews() {
        Scanner scanner = new Scanner(System.in);
        NewsManager newsManager = NewsManager.getInstance();

        while (true) {
            System.out.println("News Management Menu:");
            System.out.println("1. Add News");
            System.out.println("2. View All News");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter news title: ");
                String title = scanner.nextLine();
                System.out.print("Enter news description: ");
                String description = scanner.nextLine();
                System.out.println("Select topic for news (1 - Research, 2 - Events, 3 - Announcement): ");
                int topicChoice = scanner.nextInt();
                scanner.nextLine();
                NewsTopic topic = NewsTopic.values()[topicChoice - 1];

                News news = new News(description, title, topic);
                newsManager.addNews(news);
                System.out.println("News added successfully!");

            } else if (choice == 2) {
                System.out.println("Viewing All News:");
                for (News news : newsManager.getNewsList()) {
                    System.out.println(news);
                    System.out.println("------------------------------");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }

    public void viewSignedRequest() {
        signedRequests.forEach(request -> System.out.println(request));
    }

    public List<Student> giveListOfStudents() {
        StudentRepository studentRepository = StudentRepository.getInstance();
        return studentRepository.getAllStudents();
    }

    public void viewStudents() {
        List<Student> students = giveListOfStudents();
        students.forEach(student -> System.out.println(student));
    }

    public void viewCourses() {
        CourseRepository courseRepository = CourseRepository.getInstance();
        List<Course> courses = courseRepository.getAllCourses();
        courses.forEach(course -> System.out.println(course));
    }

    public void viewTeachers() {
        TeacherRepository teacherRepository = TeacherRepository.getInstance();
        List<Teacher> teachers = teacherRepository.getAllTeachers();
        teachers.forEach(teacher -> System.out.println(teacher));
    }

    public void fetchUnsignedRequests() {
        List<Request> requests = RequestRepository.getInstance().getUnsignedRequests();
        this.setSignedRequests(requests);
        System.out.println("Fetched " + requests.size() + " unsigned requests.");
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


