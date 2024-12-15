package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Faculty;
import research.Researcher;
import research.StudentResearcher;
import studying.Course;
import studying.CourseRepository;
import studying.Lesson;
import studying.LessonComparator;
import utils.IdGenerator;

public class Student extends User{
	private static final long serialVersionUID = 1L;
	private String studentId;
	private int yearOfStudy;
	private Faculty faculty;
	private boolean canHaveScholarship;
	private Researcher supervisor;
	private Map<Course, List<Lesson>> registeredCourses = new HashMap<>();
	
	public Student(String firstName, String lastName, String email, String password, String studentId, int yearOfStudy, Faculty faculty, boolean canHaveScholarship) {
		super(IdGenerator.generateUniqueId("B"), firstName, lastName, email, password);
		this.studentId = studentId;
		this.yearOfStudy = yearOfStudy;
		this.faculty = faculty;
		this.canHaveScholarship = canHaveScholarship;
		this.supervisor = null;
	}

	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public int getYearOfStudy() {
		return yearOfStudy;
	}
	
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
	public Faculty getFaculty() {
		return faculty;
	}
	
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	public boolean canHaveScholarship() {
		return canHaveScholarship;
	}
	
	public void setCanHaveScholarship(boolean canHaveScholarship) {
		this.canHaveScholarship = canHaveScholarship;
	}
	
	public String getFullName() {
		return super.getFirstName() + super.getLastName();
	}

	@Override
	public void displayFunct() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Course> viewCourses(){
		return CourseRepository.getAllCourses();
	}
	
	public String viewCourseDetails(Course course) {
		return course.getCourseDetails();
	}
	
	public String viewCourseDetails(String courseCode) {
		return CourseRepository.getCourseByCode(courseCode).getCourseDetails();
	}
	
	public List<Lesson> getRegisteredLessons(String courseCode) {
        Course course = CourseRepository.getCourseByCode(courseCode);
        return course != null ? registeredCourses.getOrDefault(course, new ArrayList<>()) : new ArrayList<>();
    }

    public Map<Course, List<Lesson>> getRegisteredCourses() {
        return registeredCourses;
    }
	
    private boolean isStudentLimitAvailable(Course course) {
        if (course.getLimit() <= course.getRegisteredStudentCount()) {
            System.out.println("Cannot register for course: Student limit reached.");
            return false;
        }
        return true;
    }

    private boolean isAlreadyRegistered(Course course) {
        if (registeredCourses.containsKey(course)) {
            System.out.println("Already registered for this course.");
            return true;
        }
        return false;
    }

    private void addCourseRegistration(Course course, List<Lesson> selectedLessons) {
    	registeredCourses.put(course, new ArrayList<>(selectedLessons));
        course.registerStudent(this);
        System.out.println("Successfully registered for course: " + course.getCourseName());
    }
    
    public boolean registerForCourse(String courseCode, List<Lesson> selectedLessons) {
        Course course = CourseRepository.getCourseByCode(courseCode);
        if (course == null) {
            return false;
        }
        if (!isStudentLimitAvailable(course)) {
            return false;
        }
        if (isAlreadyRegistered(course)) {
            return false;
        }
        addCourseRegistration(course, selectedLessons);
        return true;
    }
    
    public void viewSchedule() {
    	Map<Lesson, Course> lessonToCourseMap = new HashMap<>();
    	for (Map.Entry<Course, List<Lesson>> entry : registeredCourses.entrySet()) {
    	    Course course = entry.getKey();
    	    List<Lesson> lessons = entry.getValue();
    	    for (Lesson lesson : lessons) {
    	        lessonToCourseMap.put(lesson, course);
    	    }
    	}
    	List<Lesson> schedule = new ArrayList<>(lessonToCourseMap.keySet());
    	schedule.sort(new LessonComparator());
    	for (Lesson lesson : schedule) {
    	    Course course = lessonToCourseMap.get(lesson);
    	    System.out.println("Course: " + course.getCourseName() + " " + lesson.getLessonDetails());
    	}
    }
    
    public boolean setSupervisor(Researcher supervisor) {
        if (getYearOfStudy() != 4) {
            System.out.println("Only 4th-year students can have a supervisor.");
            return false;
        }
        if (supervisor.calculateHIndex() < 3) {
            System.out.println(supervisor.getResearcherName() + " does not have enough h-index to be a supervisor.");
            return false;
        }
        this.supervisor = supervisor;
        System.out.println(supervisor.getResearcherName() + " is now the supervisor of " + getFullName());
        return true;  
    }
    
    public Researcher getSupervisor() {
    	 return supervisor;
    }
    
    public Researcher becomeResearcher() {
    	Researcher researcher = new StudentResearcher(this);
        System.out.println(getFullName() + " is now a researcher.");
        return researcher;
    }
}
