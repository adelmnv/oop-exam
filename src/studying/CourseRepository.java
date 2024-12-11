package studying;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class CourseRepository {
    private static final Map<String, Course> courses = new HashMap<>();

    public static void addCourse(Course course) {
        if (course != null && course.getCourseCode() != null) {
            courses.put(course.getCourseCode(), course);
        }
    }

    public static Course getCourseByCode(String courseCode) {
        return courses.get(courseCode);
    }

    public static List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    public static boolean removeCourseByCode(String courseCode) {
        return courses.remove(courseCode) != null;
    }

    public static boolean courseExists(String courseCode) {
        return courses.containsKey(courseCode);
    }
}

