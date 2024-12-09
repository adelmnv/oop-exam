package users;

import enums.Faculty;

// доделать
public class Student extends User{
	private static final long serialVersionUID = 1L;
	private String studentId;
	private int yearOfStudy;
	private Faculty faculty;
	private boolean canHaveScholarship;
	
	public Student(String id, String firstName, String lastName, String email, String password, String studentId, int yearOfStudy, Faculty faculty, boolean canHaveScholarship) {
		super(id, firstName, lastName, email, password);
		this.studentId = studentId;
		this.yearOfStudy = yearOfStudy;
		this.faculty = faculty;
		this.canHaveScholarship = canHaveScholarship;
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
	
	public boolean isCanHaveScholarship() {
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
}
