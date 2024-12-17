package studying;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Faculty;
import grading.GradeBook;
import users.Student;

public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	private String courseName;
	private String courseCode;
	private Faculty faculty;
	private int credits;
	private int year;
	private int limit;
	private List<Lesson> lessons = new ArrayList<>();
	private HashMap<Student,GradeBook> gradebook = new HashMap<>();
	
	public Course(String courseName, String courseCode, Faculty faculty, int credits, int year, int limit) {
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.faculty = faculty;
		this.credits = credits;
		this.year = year;
		this.limit = limit;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public HashMap<Student,GradeBook> getGradebook() {
		return gradebook;
	}

	public void setGradebook(HashMap<Student,GradeBook> gradebook) {
		this.gradebook = gradebook;
	}
	
	public int getRegisteredStudentCount() {
        return gradebook.size();
    }
	
	public String getCourseDetails() {
		StringBuilder details = new StringBuilder();

	    details.append("Course Name: ").append(courseName).append("\n");
	    details.append("Course Code: ").append(courseCode).append("\n");
	    details.append("Faculty: ").append(faculty != null ? faculty : "Not assigned").append("\n");
	    details.append("Credits: ").append(credits).append("\n");
	    details.append("Year: ").append(year).append("\n");
	    details.append("Student Limit: ").append(limit).append("\n");
	    
	    details.append("Lessons:\n");
	    if (!lessons.isEmpty()) {
	        for (Lesson lesson : lessons) {
	            details.append("  - ").append(lesson.getLessonDetails()).append("\n");
	        }
	    } else {
	        details.append("  No lessons assigned\n");
	    }

	    details.append("Gradebook:\n");
	    if (!gradebook.isEmpty()) {
	        for (Map.Entry<Student, GradeBook> entry : gradebook.entrySet()) {
	            details.append("  Student: ").append(entry.getKey().getFullName()).append(", Grade: ").append(entry.getValue().getGradeDetails()).append("\n");
	        }
	    } else {
	        details.append("  No grades recorded\n");
	    }

	    return details.toString();
	}
	
	public void registerStudent(Student student) {
	    if (!gradebook.containsKey(student)) {
	        gradebook.put(student, new GradeBook());
	        System.out.println("Student " + student.getFullName() + " has been added to the course " + courseName);
	    } else {
	        System.out.println("Student " + student.getFullName() + " is already registered for the course " + courseName);
	    }
	}
	
}
