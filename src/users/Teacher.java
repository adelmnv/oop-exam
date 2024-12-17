package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import communication.Complaint;
import communication.ComplaintRepository;
import enums.Faculty;
import enums.TeacherTitle;
import enums.UrgencyLevel;
import grading.GradeBook;
import grading.Mark;
import research.TeacherResearcher;
import research.Researcher;
import studying.Course;
import studying.CourseRepository;
import studying.Lesson;
import utils.IdGenerator;

//просмотр журналов новостей и проектов
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
	
	@Override
    public void displayFunct() {
        System.out.println("Teacher Functions:");
        System.out.println("1. View Teaching Courses Info");
        System.out.println("2. View Course Info");
        System.out.println("3. View Student Info");
        System.out.println("4. Grade Student");
        System.out.println("5. Create Complaint");
        System.out.println("6. View Complaints");
        System.out.println("7. Become a Researcher");
        System.out.println("8. View Schedule");
        System.out.println("9. View Messages");
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
            Course course = CourseRepository.getCourseByCode(courseCode);
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
                Course course = CourseRepository.getCourseByCode(courseCode);
                if (course != null) {
                    courseList.append(course.getCourseDetails()).append("\n");
                }
            }
            return courseList.toString();
        }
    }
    
//    public String viewCourseInfo(Course course) {
//    	if(courseCodes.contains(course.getCourseCode())) {
//    		return course.getCourseDetails();
//    	}else {
//            return "No course information available for the teacher.";
//        }
//    }
    
    public String viewCourseInfo(String courseCode) {
        Course course = CourseRepository.getCourseByCode(courseCode);
        if (course != null) {
            return course.getCourseDetails();
        } else {
            return "No course information available for the teacher.";
        }
    }
    
    
//	  public String viewStudentInfo(Student student, Course course) {
//	  GradeBook gradeBook = course.getGradebook().get(student);
//	  if (gradeBook != null) {
//	      return String.format("Student: %s\nGrade Details:\n%s", student.getFullName(), gradeBook.getGradeDetails());
//	  } else {
//	      return "No grade information available for the student.";
//	  }
//	}
	
	public String viewStudentInfo(Student student, String courseCode) {
	  Course course = CourseRepository.getCourseByCode(courseCode);
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
    
    public String viewSchedule() {
        StringBuilder schedule = new StringBuilder();
        if (courseCodes.isEmpty()) {
            return "The teacher is not teaching any courses.";
        }
        for (String courseCode : courseCodes) {
            Course course = CourseRepository.getCourseByCode(courseCode);
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
        return String.format("Teacher: %s %s\nTitle: %s\nFaculty: %s\nCourses: %s", 
                              getFirstName(), getLastName(), teacherTitle, faculty, courseCodes);
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
