package users;

import java.util.ArrayList;
import java.util.List;

import communication.Complaint;
import communication.ComplaintRepository;
import enums.Faculty;
import enums.TeacherTitle;
import enums.UrgencyLevel;
import studying.Course;
import studying.GradeBook;
import studying.Mark;

//доделать
public class Teacher extends Employee {
	private static final long serialVersionUID = 1L;
	private Faculty faculty;
	private TeacherTitle teacherTitle;
	private List<Course> courses = new ArrayList<>();
	private List<Integer> sentComplaintIds = new ArrayList<>();

	public Teacher(String id, String firstName, String lastName, String email, String password, int salary, Faculty faculty, TeacherTitle teacherTitle) {
		super(id, firstName, lastName, email, password, salary);
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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public String getFullName() {
		return super.getFirstName() + super.getLastName();
	}
	
	@Override
	public void displayFunct() {
		// TODO Auto-generated method stub
		
	}
	
    public String viewStudentInfo(Student student, Course course) {
        GradeBook gradeBook = course.getGradebook().get(student);
        if (gradeBook != null) {
            return String.format("Student: %s\nGrade Details:\n%s", student.getFullName(), gradeBook.getGradeDetails());
        } else {
            return "No grade information available for the student.";
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
    	int complaintId = ComplaintRepository.addComplaint(complaint);
        this.sentComplaintIds.add(complaintId);
    }
    
    public List<Complaint> getSentComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        for (int id : sentComplaintIds) {
            complaints.add(ComplaintRepository.getComplaintById(id));
        }
        return complaints;
    }

}
