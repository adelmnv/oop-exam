package repositories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studying.Course;

public class CourseRepository {
    private Map<String, Course> courses = new HashMap<>();
    private static final CourseRepository INSTANCE = new CourseRepository();
    private static final String FILE_PATH = "src/data/courses.dat";  

    private CourseRepository() {
    	loadCoursesFromFile();
    }

    public static CourseRepository getInstance() {
        return INSTANCE;
    }

    public void addCourse(Course course) {
        if (course != null && course.getCourseCode() != null) {
            if (!courses.containsKey(course.getCourseCode())) {
                courses.put(course.getCourseCode(), course);
                System.out.println("Course added: " + course.getCourseName());
                saveCoursesToFile();
            } else {
                System.out.println("Course with code " + course.getCourseCode() + " already exists.");
            }
        } else {
            System.out.println("Invalid course or course code.");
        }
    }

    public Course getCourseByCode(String courseCode) {
        return courses.get(courseCode);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    public boolean removeCourseByCode(String courseCode) {
        if (courses.containsKey(courseCode)) {
            courses.remove(courseCode);
            System.out.println("Course with code " + courseCode + " removed.");
            saveCoursesToFile();
            return true;
        } else {
            System.out.println("No course found with code " + courseCode);
            return false;
        }
    }

    public boolean courseExists(String courseCode) {
        return courses.containsKey(courseCode);
    }

    public void printAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses in the repository.");
        } else {
            System.out.println("Courses in the repository:");
            for (Course course : courses.values()) {
                System.out.println("- " + course.getCourseDetails());
            }
        }
    }
    
    public void saveCoursesToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(courses);
            System.out.println("Courses saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving courses to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
	public void loadCoursesFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = in.readObject();
            if (obj instanceof Map<?, ?>) {
                courses.clear();
                courses.putAll((Map<String, Course>) obj);
                System.out.println("Courses loaded from file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved courses found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading courses from file: " + e.getMessage());
        }
    }
}

