package grading;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import studying.Course;
import studying.Lesson;
import users.Student;

public class Transcript {
    private Student student;
    private List<GradeReport> gradeReports;

    public Transcript(Student student) {
        this.student = student;
        this.gradeReports = new ArrayList<>();
    }

    public void addGradeReport(GradeReport gradeReport) {
        if (gradeReport != null) {
            gradeReports.add(gradeReport);
        }
    }

    public String generateTranscript() {
    	fillGradeReports();
        StringBuilder transcript = new StringBuilder();
        transcript.append("Transcript for: ").append(student.getFullName()).append("\n\n");
        for (GradeReport gradeReport : gradeReports) {
            transcript.append(gradeReport.generateReport(student)).append("\n");
        }
        return transcript.toString();
    }

    public double calculateOverallGPA() {
        if (gradeReports.isEmpty()) {
            return 0.0;
        }
        double totalGPA = 0.0;
        for (GradeReport gradeReport : gradeReports) {
            totalGPA += gradeReport.getMark().getGpaNumeric();
        }
        return totalGPA / gradeReports.size();
    }

    public void printStatistics() {
        System.out.println("Transcript Statistics:");
        System.out.println("Total Courses: " + gradeReports.size());
        System.out.println("Average GPA: " + calculateOverallGPA());
    }

    public List<GradeReport> getGradeReports() {
        return gradeReports;
    }
    
    public void fillGradeReports() {
        for (Map.Entry<Course, List<Lesson>> entry : student.getRegisteredCourses().entrySet()) {
            Course course = entry.getKey();
            GradeBook gradeBookEntry = student.getGradeForCourse(course);
            if (gradeBookEntry != null) {
            	addGradeReport(new GradeReport(gradeBookEntry.getMark(), course));
            }
        }
    }
}
