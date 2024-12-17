package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import enums.Faculty;
import grading.GradeBook;
import grading.Mark;
import grading.Transcript;
import research.Researcher;
import research.StudentResearcher;
import studying.Course;
import studying.CourseRepository;
import studying.Lesson;
import studying.LessonComparator;
import utils.IdGenerator;

//просмотр журналов новостей и проектов
public class Student extends User{
	private static final long serialVersionUID = 1L;
	private int yearOfStudy;
	private Faculty faculty;
	private boolean canHaveScholarship;
	private Researcher supervisor;
	private Map<Course, List<Lesson>> registeredCourses = new HashMap<>();
	
	public Student(String firstName, String lastName, String email, String password, String studentId, int yearOfStudy, Faculty faculty, boolean canHaveScholarship) {
		super(IdGenerator.generateUniqueId("B"), firstName, lastName, email, password);
		this.yearOfStudy = yearOfStudy;
		this.faculty = faculty;
		this.canHaveScholarship = canHaveScholarship;
		this.supervisor = null;
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
		System.out.println("Student Functions:");
	    System.out.println("1. View Schedule");
	    System.out.println("2. View Courses");
	    System.out.println("3. Register for Course");
	    System.out.println("4. View Course Details");
	    System.out.println("5. View Grades for All Courses");
	    System.out.println("6. View Grade for Specific Course");
	    System.out.println("7. Set Supervisor");
	    System.out.println("8. Become a Researcher");
	    System.out.println("9. Join Student Organization");
	    System.out.println("10. View Transcript");
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
    
    public void joinStudentOrganization(StudentOrganization so, String role) {
    	so.addMember(this, role);
    }
    
    public GradeBook getGradeForCourse(Course course) {
        return course.getGradebook().get(this);
    }

    Map<String, String> getAllGrades() {
        Map<String, String> grades = new HashMap<>();
        for (Map.Entry<Course, List<Lesson>> entry : registeredCourses.entrySet()) {
            Course course = entry.getKey();
            GradeBook gradeBookEntry = getGradeForCourse(course);
            if (gradeBookEntry != null) {
                grades.put(course.getCourseCode(), gradeBookEntry.getGradeDetails());
            } else {
                grades.put(course.getCourseCode(), "No grades recorded yet.");
            }
        }
        return grades;
    }
    
    List<Double> getAllTotalMarks(){
    	List<Double> totalMarks = new ArrayList<>();
    	for (Course course : registeredCourses.keySet()) {
    	    GradeBook gradeBookEntry = getGradeForCourse(course);
    	    if (gradeBookEntry != null) {
    	        totalMarks.add(gradeBookEntry.getTotalMark());
    	    }
    	}
    	return totalMarks;
    }

    public String getGradeForSpecificCourse(String courseCode) {
        Course course = CourseRepository.getCourseByCode(courseCode);
        if (course == null || !registeredCourses.containsKey(course)) {
            return null;
        }
        GradeBook gradeBookEntry = getGradeForCourse(course);
        return (gradeBookEntry != null) ? gradeBookEntry.getGradeDetails() : "No grades recorded yet.";
    }

    public void displayGradesForAllCourses() {
        Map<String, String> grades = getAllGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades available to display.");
        } else {
            grades.forEach((key, value) -> {
                System.out.println("Course Code: " + key + " | Grade: " + value);
            });
        }
    }

    public void displayGradeForSpecificCourse(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
            System.out.println("Invalid course code provided.");
            return;
        }
        String grade = getGradeForSpecificCourse(courseCode);
        if (grade == null) {
            System.out.println("You are not registered for the course with code: " + courseCode);
        } else {
            System.out.println("Course Code: " + courseCode + " | Grade: " + grade);
        }
    }
    
    public void viewTranscript() {
    	Transcript transcript = new Transcript(this);
    	System.out.println(transcript.generateTranscript());
    	transcript.printStatistics();
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + getId() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", faculty=" + faculty +
                ", canHaveScholarship=" + canHaveScholarship +
                ", supervisor=" + (supervisor != null ? supervisor.getResearcherName() : "No supervisor") +
                ", registeredCourses=" + registeredCourses.size() +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return Objects.equals(getId(), student.getId()) &&
        	   Objects.equals(getFullName(), student.getFullName()) &&
               yearOfStudy == student.yearOfStudy &&
               faculty == student.faculty &&
               canHaveScholarship == student.canHaveScholarship;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), yearOfStudy, faculty, canHaveScholarship, supervisor);
    }

}
