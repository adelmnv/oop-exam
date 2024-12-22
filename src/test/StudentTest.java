package test;

import enums.Faculty;
import enums.LessonType;
import enums.TeacherTitle;
import grading.GradeBook;
import research.*;
import studying.*;
import users.*;

import java.util.Date;

public class StudentTest {
    public static void main(String[] args) {
        // Создание студентов
        Student student1 = new Student("John", "Doe", "john.doe@example.com", "studentpassword","234456",1,Faculty.KMA,true);
        Student student2 = new Student("Arman", "Kairat", "arman@example.com", "studentpassword","234456",1,Faculty.KMA,true);

        // Создание преподавателей
        Teacher teacher1 = new Teacher("Mr.", "Smith", "smith@example.com", "password123",50000,Faculty.BS,TeacherTitle.LECTOR);
        Teacher teacher2 = new Teacher("Mrs.", "Johnson", "johnson@example.com", "password456",60000,Faculty.ISE,TeacherTitle.TUTOR);

        // Создание уроков
        Lesson lesson1 = new Lesson("Room 101", "Monday", "9:00 AM", LessonType.LECTURE, teacher1);
        Lesson lesson2 = new Lesson("Room 102", "Wednesday", "11:00 AM", LessonType.PRACTICE, teacher2);

        // Создание курса
        Course course1 = new Course("Introduction to Engineering", "ENG101", Faculty.KMA, 3, 2024, 30);
        course1.getLessons().add(lesson1);
        course1.getLessons().add(lesson2);

        // Регистрируем студентов на курс
        course1.registerStudent(student1);
        course1.registerStudent(student2);

        // Добавление оценки студентам
        GradeBook gradebook1 = new GradeBook();
        gradebook1.setFirstAttestationMark(85.0);
        gradebook1.setSecondAttestationMark(90.0);
        gradebook1.setFinalExamMark(88.0);
        course1.getGradebook().put(student1, gradebook1);

        GradeBook gradebook2 = new GradeBook();
        gradebook2.setFirstAttestationMark(78.0);
        gradebook2.setSecondAttestationMark(80.0);
        gradebook2.setFinalExamMark(79.0);
        course1.getGradebook().put(student2, gradebook2);

        // Выводим информацию о курсе
        System.out.println(course1.getCourseDetails());

        // Добавление посещаемости
        gradebook1.addAttendance(new Date());
        gradebook2.addAttendance(new Date());

        // Работа с исследованиями
        StudentResearcher researcher1 = new StudentResearcher(student1);
        ResearchPaper paper1 = new ResearchPaper("learning sorting algorithms",researcher1, 2024, 12, 5);
        researcher1.publishPaper(paper1);

        ResearchProject project1 = new ResearchProject("Robotics", researcher1);
        researcher1.joinProject(project1);
        researcher1.becomeProjectLeader(project1);

        // Расчет H-индекса
        System.out.println("H-Index of " + student1.getFullName() + ": " + researcher1.calculateHIndex());

        //информацию о студенте и его исследованиях
        System.out.println(researcher1);

    }
}
