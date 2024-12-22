package test;
import java.util.Date;

import grading.GradeBook;
import grading.Mark;

public class GradeBookTest {

    public static void main(String[] args) {
        Mark mark = new Mark();
        
        mark.setFirstAttestation(30);
        mark.setSecondAttestation(20);
        mark.setFinalExam(40);

        GradeBook gradeBook = new GradeBook(mark);

        gradeBook.addAttendance(new Date());

        // Получаем и выводим подробности о студенте
        System.out.println("Grade Details:\n" + gradeBook.getGradeDetails());

        // Выводим GPA и оценки
        System.out.println("First Attestation Mark: " + gradeBook.getFirstAttestationMark());
        System.out.println("Second Attestation Mark: " + gradeBook.getSecondAttestationMark());
        System.out.println("Final Exam Mark: " + gradeBook.getFinalExamMark());

        //  итоговую оценку, GPA в числовом и буквенном формате
        System.out.println("Total Mark: " + gradeBook.getTotalMark());
        System.out.println("GPA (Numeric): " + gradeBook.getGpaNumeric());
        System.out.println("GPA (Letter): " + gradeBook.getGpaLetter());

        //  количество посещений
        System.out.println("Attendance Count: " + gradeBook.getAttendanceCount());
    }
}
