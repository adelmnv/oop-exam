package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import communication.Complaint;
import communication.Message;
import enums.Faculty;
import enums.TeacherTitle;
import enums.UrgencyLevel;
import grading.GradeBook;
import grading.Mark;
import repositories.ComplaintRepository;
import research.TeacherResearcher;
import research.Researcher;
import studying.Course;
import repositories.CourseRepository;
import repositories.StudentRepository;
import repositories.UserRepository;
import studying.Lesson;
import utils.IdGenerator;

public class Teacher extends Employee {
	private static final long serialVersionUID = 1L;
	private Faculty faculty;
	private TeacherTitle teacherTitle;
	private List<String> courseCodes = new ArrayList<>();
	private List<Integer> sentComplaintIds = new ArrayList<>();

	public Teacher(String firstName, String lastName, String email, String password, int salary, Faculty faculty, TeacherTitle teacherTitle) {
		super(IdGenerator.generateUniqueId("T"), firstName, lastName, email, password, salary);
		this.faculty = faculty;
		this.teacherTitle = teacherTitle;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public TeacherTitle getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(TeacherTitle teacherTitle) {
		this.teacherTitle = teacherTitle;
	}
	
	public List<String> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(List<String> courseCodes) {
        this.courseCodes = courseCodes;
    }
	
	public String getFullName() {
		return super.getFirstName() + super.getLastName();
	}
	
	public void displayTeacherMenu() {
		System.out.println("Teacher Functions:");
		System.out.println("0. Exit");
        System.out.println("1. View Teaching Courses Info");
        System.out.println("2. View Course Info");
        System.out.println("3. View Student Info");
        System.out.println("4. Grade Student");
        System.out.println("5. Create Complaint");
        System.out.println("6. View Complaints");
        System.out.println("7. Become a Researcher");
        System.out.println("8. View Schedule");
        System.out.println("9. Send Message to User by ID");
        System.out.println("10. View Received Messages");
	}
	
	@Override
	public void displayFunct() {
	    Scanner scanner = new Scanner(System.in);
	    try {
	    	while (true) {
		        displayTeacherMenu();
		        System.out.print("Enter your choice: ");
		        int choice;
		        try {
		            choice = Integer.parseInt(scanner.nextLine());
		        } catch (NumberFormatException e) {
		            System.out.println("Invalid input. Please enter a valid number.");
		            continue;
		        }

		        switch (choice) {
		            case 0:
		                System.out.println("Exiting Teacher Menu.");
		                UserRepository.getInstance().updateUser(this);
                        super.logout();
		                return;
		            case 1:
		                System.out.println(viewCourses());
		                break;
		            case 2:
		                handleViewCourseInfo(scanner);
		                break;
		            case 3:
		                handleViewStudentInfo(scanner);
		                break;
		            case 4:
		                handleGradeStudent(scanner);
		                break;
		            case 5:
		                handleCreateComplaint(scanner);
		                break;
		            case 6:
		                handleViewComplaints();
		                break;
		            case 7:
		                becomeResearcher();
		                break;
		            case 8:
		                System.out.println(viewSchedule());
		                break;
		            case 9:
		            	handleSendMessageToUser(scanner);
		                break;
		            case 10:
		            	viewReceivedMessages();
		                break;
		            default:
		                System.out.println("Invalid choice. Please select a valid option.");
		        }
		    }
	    }finally {
	    	scanner.close();
	    }
	}
	
	private void handleGradeStudent(Scanner scanner) {
	    System.out.print("Enter student ID: ");
	    String studentId = scanner.nextLine();
	    Student student = StudentRepository.getInstance().findStudentById(studentId);

	    if (student == null) {
	        System.out.println("Student not found.");
	        return;
	    }

	    System.out.print("Enter course code: ");
	    String courseCode = scanner.nextLine();
	    Course course = CourseRepository.getInstance().getCourseByCode(courseCode);

	    if (course == null) {
	        System.out.println("Course not found.");
	        return;
	    }

	    System.out.println("Grade Options:");
	    System.out.println("1. Add First Attestation Mark");
	    System.out.println("2. Add Second Attestation Mark");
	    System.out.println("3. Add Final Exam Mark");
	    System.out.print("Enter your choice: ");
	    int gradeOption = scanner.nextInt();
	    scanner.nextLine();

	    switch (gradeOption) {
	        case 1:
	            handleFirstAttestationMark(student, course, scanner);
	            break;
	        case 2:
	            handleSecondAttestationMark(student, course, scanner);
	            break;
	        case 3:
	            handleFinalExamMark(student, course, scanner);
	            break;
	        default:
	            System.out.println("Invalid option.");
	    }
	}

	private void handleFirstAttestationMark(Student student, Course course, Scanner scanner) {
	    System.out.print("Enter first attestation mark: ");
	    double mark = scanner.nextDouble();
	    scanner.nextLine(); // Consume newline character

	    putFirstAttestationMark(student, mark, course);
	    System.out.println("First attestation mark added successfully.");
	}

	private void handleSecondAttestationMark(Student student, Course course, Scanner scanner) {
	    System.out.print("Enter second attestation mark: ");
	    double mark = scanner.nextDouble();
	    scanner.nextLine(); // Consume newline character

	    try {
	        putSecondAttestationMark(student, mark, course);
	        System.out.println("Second attestation mark added successfully.");
	    } catch (IllegalStateException e) {
	        System.out.println(e.getMessage());
	    }
	}

	private void handleFinalExamMark(Student student, Course course, Scanner scanner) {
	    System.out.print("Enter final exam mark: ");
	    double mark = scanner.nextDouble();
	    scanner.nextLine(); // Consume newline character

	    try {
	        putFinalExamMark(student, mark, course);
	        System.out.println("Final exam mark added successfully.");
	    } catch (IllegalStateException e) {
	        System.out.println(e.getMessage());
	    }
	}

	private void handleViewCourseInfo(Scanner scanner) {
	    System.out.print("Enter the course code: ");
	    String courseCode = scanner.nextLine();
	    System.out.println(viewCourseInfo(courseCode));
	}
	
	private void handleViewStudentInfo(Scanner scanner) {
	    System.out.print("Enter the student ID: ");
	    String studentId = scanner.nextLine();
	    System.out.print("Enter the course code: ");
	    String courseCode = scanner.nextLine();
	    Student student = StudentRepository.getInstance().findStudentById(studentId);
	    if (student != null) {
	        System.out.println(viewStudentInfo(student, courseCode));
	    } else {
	        System.out.println("Student not found.");
	    }
	}
	
	private void handleCreateComplaint(Scanner scanner) {
	    System.out.print("Enter the student ID: ");
	    String studentId = scanner.nextLine();
	    System.out.print("Enter the urgency level (LOW, MEDIUM, HIGH): ");
	    String urgencyLevelStr = scanner.nextLine().toUpperCase();
	    try {
	        UrgencyLevel urgencyLevel = UrgencyLevel.valueOf(urgencyLevelStr);
	        Student student = StudentRepository.getInstance().findStudentById(studentId);
	        if (student != null) {
	            createComplaint(urgencyLevel, student);
	            System.out.println("Complaint created successfully.");
	        } else {
	            System.out.println("Student not found.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println("Invalid urgency level.");
	    }
	}
	
	private void handleViewComplaints() {
	    List<Complaint> complaints = getSentComplaints();
	    if (complaints.isEmpty()) {
	        System.out.println("No complaints sent.");
	    } else {
	        System.out.println("Sent Complaints:");
	        for (Complaint complaint : complaints) {
	            System.out.println(complaint);
	        }
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
            }
        }
    }
    
    public void addCourse(String courseCode) {
        if (!courseCodes.contains(courseCode)) {
            courseCodes.add(courseCode);
        }
    }

    public void removeCourse(String courseCode) {
        courseCodes.remove(courseCode);
    }

    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        for (String courseCode : courseCodes) {
            Course course = CourseRepository.getInstance().getCourseByCode(courseCode);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }
    
    public String viewCourses() {
        if (courseCodes.isEmpty()) {
            return "The teacher is not teaching any courses.";
        } else {
            StringBuilder courseList = new StringBuilder("Courses taught by the teacher:\n");
            for (String courseCode : courseCodes) {
                Course course = CourseRepository.getInstance().getCourseByCode(courseCode);
                if (course != null) {
                    courseList.append(course.getCourseDetails()).append("\n");
                }
            }
            return courseList.toString();
        }
    }
    
    public String viewCourseInfo(Course course) {
    	if(courseCodes.contains(course.getCourseCode())) {
    		return course.getCourseDetails();
    	}else {
            return "No course information available for the teacher.";
        }
    }
    
    public String viewCourseInfo(String courseCode) {
        Course course = CourseRepository.getInstance().getCourseByCode(courseCode);
        if (course != null) {
            return course.getCourseDetails();
        } else {
            return "No course information available for the teacher.";
        }
    }
    
    
	  public String viewStudentInfo(Student student, Course course) {
	  GradeBook gradeBook = course.getGradebook().get(student);
	  if (gradeBook != null) {
	      return String.format("Student: %s\nGrade Details:\n%s", student.getFullName(), gradeBook.getGradeDetails());
	  } else {
	      return "No grade information available for the student.";
	  }
	}
	
	public String viewStudentInfo(Student student, String courseCode) {
	  Course course = CourseRepository.getInstance().getCourseByCode(courseCode);
	  if (course != null) {
	      GradeBook gradeBook = course.getGradebook().get(student);
	      if (gradeBook != null) {
	          return String.format("Student: %s\nGrade Details:\n%s", student.getFullName(), gradeBook.getGradeDetails());
	      } else {
	          return "No grade information available for the student.";
	      }
	  } else {
	      return "Course not found.";
	  }
	}
	
    public void putMark(Student student, Mark mark, Course course) {
        GradeBook gradeBook = course.getGradebook().get(student);
        if (gradeBook != null) {
            gradeBook.setMark(mark);
        } else {
            course.getGradebook().put(student, new GradeBook(mark));
        }
    }
    
    public void putFirstAttestationMark(Student student, double firstAttestation, Course course) {
        GradeBook gradeBook = course.getGradebook().get(student);
        if (gradeBook != null) {
            gradeBook.setFirstAttestationMark(firstAttestation);
        } else {
            Mark mark = new Mark();
            mark.setFirstAttestation(firstAttestation);
            course.getGradebook().put(student, new GradeBook(mark));
        }
    }

    public void putSecondAttestationMark(Student student, double secondAttestation, Course course) {
        GradeBook gradeBook = course.getGradebook().get(student);
        if (gradeBook != null) {
            if (gradeBook.getFirstAttestationMark() != null) {
                gradeBook.setSecondAttestationMark(secondAttestation);
            } else {
                throw new IllegalStateException("First attestation mark must be set before the second attestation.");
            }
        } else {
            throw new IllegalStateException("No gradebook entry found for the student.");
        }
    }

    public void putFinalExamMark(Student student, double finalExam, Course course) {
        GradeBook gradeBook = course.getGradebook().get(student);
        if (gradeBook != null) {
            if (gradeBook.getSecondAttestationMark() != null) {
                gradeBook.setFinalExamMark(finalExam);
            } else {
                throw new IllegalStateException("Second attestation mark must be set before the final exam.");
            }
        } else {
            throw new IllegalStateException("No gradebook entry found for the student.");
        }
    }
    
    public void createComplaint(UrgencyLevel urgencyLevel, Student student) {
    	Complaint complaint = new Complaint(this, urgencyLevel, student);
    	int complaintId = ComplaintRepository.getInstance().addComplaint(complaint);
        this.sentComplaintIds.add(complaintId);
    }
    
    public List<Complaint> getSentComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        for (int id : sentComplaintIds) {
            complaints.add(ComplaintRepository.getInstance().getComplaintById(id));
        }
        return complaints;
    }
    
    public String viewSchedule() {
        StringBuilder schedule = new StringBuilder();
        if (courseCodes.isEmpty()) {
            return "The teacher is not teaching any courses.";
        }
        for (String courseCode : courseCodes) {
            Course course = CourseRepository.getInstance().getCourseByCode(courseCode);
            if (course != null) {
                schedule.append("Schedule for course: ").append(course.getCourseName()).append("\n");
                for (Lesson lesson : course.getLessons()) {
                    if (lesson.getInstructor().equals(this)) {
                        schedule.append("  - ").append(lesson.getLessonDetails()).append("\n");
                    }
                }
            }
        }
        if (schedule.length() == 0) {
            return "No lessons assigned for the teacher.";
        }

        return schedule.toString();
    }
    
    @Override
    public Researcher becomeResearcher() {
    	Researcher researcher = new TeacherResearcher(this);
        System.out.println(getFullName() + " is now a researcher.");
        return researcher;
    }
    
    @Override
    public String toString() {
        String format = "+------------------+---------------------+\n" +
                        "| %-16s | %-19s |\n" +
                        "+------------------+---------------------+\n" +
                        "| %-16s | %-19s |\n" +
                        "| %-16s | %-19s |\n" +
                        "| %-16s | %-19s |\n" +
                        "| %-16s | %-19s |\n" +
                        "+------------------+---------------------+\n";
        
        return String.format(format,
        		"Teacher ID", getId(),
                "Teacher", getFirstName() + " " + getLastName(),
                "Title", teacherTitle,
                "Faculty", faculty,
                "Courses", courseCodes != null ? courseCodes : "No courses");
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Teacher teacher = (Teacher) obj;
        return Objects.equals(getId(), teacher.getId()) &&
               Objects.equals(getFirstName(), teacher.getFirstName()) &&
               Objects.equals(getLastName(), teacher.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName());
    }

}
