package grading;

import java.io.Serializable;

import studying.Course;
import users.Student;

public class GradeReport implements Serializable{
	private static final long serialVersionUID = 1L;
	private Mark mark;
    private Course course;

    public GradeReport(Mark mark, Course course) {
        this.mark = mark;
        this.course = course;
    }

    public String generateReport(Student student) {
        StringBuilder report = new StringBuilder();
        report.append("Grade Report for: ").append(student.getFullName()).append("\n");
        report.append("Course: ").append(course.getCourseName()).append("\n");
        report.append("Course Code: ").append(course.getCourseCode()).append("\n");
        report.append("First Attestation: ").append(mark.getFirstAttestation()).append("\n");
        report.append("Second Attestation: ").append(mark.getSecondAttestation()).append("\n");
        report.append("Final Exam: ").append(mark.getFinalExam()).append("\n");
        report.append("Total Mark: ").append(mark.getTotalMark()).append("\n");
        report.append("GPA (Numeric): ").append(mark.getGpaNumeric()).append("\n");
        report.append("GPA (Letter): ").append(mark.getGpaLetter()).append("\n");
        return report.toString();
    }

    public Mark getMark() {
        return mark;
    }

    public Course getCourse() {
        return course;
    }
}
