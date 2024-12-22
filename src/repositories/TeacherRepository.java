package repositories;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import studying.Course;
import users.Teacher;

public class TeacherRepository {
    private static TeacherRepository instance;

    private TeacherRepository() {}

    public static TeacherRepository getInstance() {
        if (instance == null) {
            instance = new TeacherRepository();
        }
        return instance;
    }

    public void addTeacher(Teacher teacher) {
        UserRepository.getInstance().addUser(teacher);
    }

    public Teacher getTeacherById(String teacherId) {
        return UserRepository.getInstance().getUsersForType(Teacher.class).stream()
                .filter(teacher -> teacher.getId().equals(teacherId))
                .findFirst()
                .orElse(null);
    }

    public List<Teacher> getAllTeachers() {
        return UserRepository.getInstance().getUsersForType(Teacher.class);
    }

    public List<Teacher> getAllTeachersByCourse(Course course) {
        return UserRepository.getInstance().getUsersForType(Teacher.class).stream()
                .filter(teacher -> teacher.getCourseCodes().contains(course.getCourseCode()))
                .collect(Collectors.toList());
    }

    public void removeTeacher(String teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        if (teacher != null) {
            UserRepository.getInstance().removeUser(teacher);
        } else {
            System.out.println("Teacher with ID " + teacherId + " not found.");
        }
    }
    
    public void updateTeacher(Teacher teacher) {
    	UserRepository.getInstance().updateUser(teacher);
    }
}


