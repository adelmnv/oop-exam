package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import communication.Request;
import communication.RequestRepository;
import enums.LessonType;
import enums.ManagerType;
import research.EmployeeResearcher;
import research.Researcher;
import studying.Course;
import studying.Lesson;
import utils.IdGenerator;

//доделать
public class Manager extends Employee{
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
	
	@Override
	public void displayFunct() {
		// TODO Auto-generated method stub
		
	}
	
	public void createLessonsForCourse(Course course) {
		Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество уроков для курса: " + course.getCourseName());
        int lessonCount = scanner.nextInt();
        scanner.nextLine();
        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < lessonCount; i++) {
            System.out.println("Урок " + (i + 1) + ":");
            System.out.print("Введите номер аудитории: ");
            String room = scanner.nextLine();
            System.out.print("Введите день недели: ");
            String dayOfWeek = scanner.nextLine();
            System.out.print("Введите время урока: ");
            String time = scanner.nextLine();
            System.out.print("Введите тип урока (1 - лекция, 2 - практика): ");
            int lessonTypeChoice = scanner.nextInt();
            scanner.nextLine();
            LessonType lessonType = (lessonTypeChoice == 1) ? LessonType.LECTURE : LessonType.PRACTICE;

//            System.out.print("Введите имя преподавателя: ");
//            String instructorName = scanner.nextLine();
//            Teacher instructor = new Teacher(instructorName);

            //Lesson lesson = new Lesson (room, dayOfWeek, time, lessonType, instructor);
            //lessons.add(lesson);
        }

        course.setLessons(lessons);
        System.out.println("Уроки успешно добавлены к курсу: " + course.getCourseName());
    }
	
	public void assignCourseToTeacher(Course course, Teacher teacher) {
		teacher.addCourse(course.getCourseCode());
	}
	
	public void viewTeacherInfo() {}
	public void viewStudentInfo() {}
	
	
	public void fetchUnsignedRequests() {
        List<Request> requests = RequestRepository.getSignedRequests();
        System.out.println("Fetched " + requests.size() + " signed requests.");
        this.setSignedRequests(requests);
    }
	


}
