package repositories;

import java.util.List;

import users.Student;

public class StudentRepository {
    private static StudentRepository instance;

    private StudentRepository() {}

    public static StudentRepository getInstance() {
        if (instance == null) {
            synchronized (StudentRepository.class) {
                if (instance == null) {
                    instance = new StudentRepository();
                }
            }
        }
        return instance;
    }

    public void addStudent(Student student) {
    	 UserRepository.getInstance().addUser(student);
    }

    public void removeStudent(Student student) {
    	UserRepository.getInstance().removeUser(student);
    }

    public Student findStudentById(String studentId) {
    	return UserRepository.getInstance().getUsersForType(Student.class).stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAllStudents() {
    	return UserRepository.getInstance().getUsersForType(Student.class);
    }
    
    public void updateStudent(Student student) {
    	UserRepository.getInstance().updateUser(student);
    }
}

