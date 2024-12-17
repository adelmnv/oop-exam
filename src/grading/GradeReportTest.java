package grading;
import enums.Faculty;
import studying.Course;
import users.Student;

public class GradeReportTest {

    public static void main(String[] args) {
     
        Student student = new Student("John"," Doe","john@email.com","john2022","23B031286",1,Faculty.ISE,true);        
        Course course = new Course("Introduction to Programming", "CS101",Faculty.KMA,5,1,30);
     
        Mark mark = new Mark();
        mark.setFirstAttestation(30);
        mark.setSecondAttestation(20);
        mark.setFinalExam(20);

        // Создаем отчет по оценкам для этого студента и курса
        GradeReport gradeReport = new GradeReport(mark, course);

        String report = gradeReport.generateReport(student);
        System.out.println(report);
    }
}
