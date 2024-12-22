package users;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import enums.Faculty;
import grading.GradeBook;
import grading.Transcript;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import research.StudentResearcher;
import studying.Course;
import repositories.CourseRepository;
import repositories.ResearchProjectRepository;
import repositories.ResearcherRepository;
import repositories.StudentOrganizationRepository;
import repositories.StudentRepository;
import studying.Lesson;
import studying.LessonComparator;
import utils.IdGenerator;

import research.ComparatorByCitation;
import research.ComparatorByDate;
import research.ComparatorByPages;

public class Student extends User {
    private static final long serialVersionUID = 1L;
    private int yearOfStudy;
    private Faculty faculty;
    private boolean canHaveScholarship;
    private Researcher supervisor;
    private Map<Course, List<Lesson>> registeredCourses = new HashMap<>();

    public Student(String firstName, String lastName, String email, String password, int yearOfStudy, Faculty faculty,
            boolean canHaveScholarship) {
        super(IdGenerator.generateUniqueId("B"), firstName, lastName, email, password);
        this.yearOfStudy = yearOfStudy;
        this.faculty = faculty;
        this.canHaveScholarship = canHaveScholarship;
        this.supervisor = null;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public boolean canHaveScholarship() {
        return canHaveScholarship;
    }

    public void setCanHaveScholarship(boolean canHaveScholarship) {
        this.canHaveScholarship = canHaveScholarship;
    }

    public String getFullName() {
        return super.getFirstName() + " " + super.getLastName();
    }

    public List<Course> viewCourses() {
        return CourseRepository.getInstance().getAllCourses();
    }

    public String viewCourseDetails(Course course) {
        return course.getCourseDetails();
    }

    public String viewCourseDetails(String courseCode) {
        return CourseRepository.getInstance().getCourseByCode(courseCode).getCourseDetails();
    }

    public List<Lesson> getRegisteredLessons(String courseCode) {
        Course course = CourseRepository.getInstance().getCourseByCode(courseCode);
        return course != null ? registeredCourses.getOrDefault(course, new ArrayList<>()) : new ArrayList<>();
    }

    public Map<Course, List<Lesson>> getRegisteredCourses() {
        return registeredCourses;
    }

    private boolean isStudentLimitAvailable(Course course) {
        if (course.getLimit() <= course.getRegisteredStudentCount()) {
            System.out.println("Cannot register for course: Student limit reached.");
            return false;
        }
        return true;
    }

    private boolean isAlreadyRegistered(Course course) {
        if (registeredCourses.containsKey(course)) {
            System.out.println("Already registered for this course.");
            return true;
        }
        return false;
    }

    private void addCourseRegistration(Course course, List<Lesson> selectedLessons) {
        registeredCourses.put(course, new ArrayList<>(selectedLessons));
        course.registerStudent(this);
        System.out.println("Successfully registered for course: " + course.getCourseName());
    }

    public boolean registerForCourse(String courseCode, List<Lesson> selectedLessons) {
        Course course = CourseRepository.getInstance().getCourseByCode(courseCode);
        if (course == null) {
            return false;
        }
        if (!isStudentLimitAvailable(course)) {
            return false;
        }
        if (isAlreadyRegistered(course)) {
            return false;
        }
        addCourseRegistration(course, selectedLessons);
        return true;
    }

    public void viewSchedule() {
        Map<Lesson, Course> lessonToCourseMap = new HashMap<>();
        for (Map.Entry<Course, List<Lesson>> entry : registeredCourses.entrySet()) {
            Course course = entry.getKey();
            List<Lesson> lessons = entry.getValue();
            for (Lesson lesson : lessons) {
                lessonToCourseMap.put(lesson, course);
            }
        }
        List<Lesson> schedule = new ArrayList<>(lessonToCourseMap.keySet());
        schedule.sort(new LessonComparator());

        if (schedule.isEmpty()) {
            System.out.println("No lessons registered.");
            return;
        }

        for (Lesson lesson : schedule) {
            Course course = lessonToCourseMap.get(lesson);
            System.out.println("Course: " + course.getCourseName() + " " + lesson.getLessonDetails());
        }
    }

    public boolean setSupervisor(Researcher supervisor) {
        if (getYearOfStudy() != 4) {
            System.out.println("Only 4th-year students can have a supervisor.");
            return false;
        }
        if (supervisor.calculateHIndex() < 3) {
            System.out.println(supervisor.getResearcherName() + " does not have enough h-index to be a supervisor.");
            return false;
        }
        this.supervisor = supervisor;
        System.out.println(supervisor.getResearcherName() + " is now the supervisor of " + getFullName());
        return true;
    }

    public Researcher getSupervisor() {
        return supervisor;
    }

    public void becomeResearcher() {
        ResearcherRepository.getInstance().addResearcher(this);
        System.out.println(getFullName() + " is now a researcher.");
    }

    public void joinStudentOrganization(StudentOrganization so, String role) {
        so.addMember(this, role);
    }

    public GradeBook getGradeForCourse(Course course) {
        return course.getGradebook().get(this);
    }

    Map<String, String> getAllGrades() {
        Map<String, String> grades = new HashMap<>();
        for (Map.Entry<Course, List<Lesson>> entry : registeredCourses.entrySet()) {
            Course course = entry.getKey();
            GradeBook gradeBookEntry = getGradeForCourse(course);
            if (gradeBookEntry != null) {
                grades.put(course.getCourseCode(), gradeBookEntry.getGradeDetails());
            } else {
                grades.put(course.getCourseCode(), "No grades recorded yet.");
            }
        }
        return grades;
    }

    List<Double> getAllTotalMarks() {
        List<Double> totalMarks = new ArrayList<>();
        for (Course course : registeredCourses.keySet()) {
            GradeBook gradeBookEntry = getGradeForCourse(course);
            if (gradeBookEntry != null) {
                totalMarks.add(gradeBookEntry.getTotalMark());
            }
        }
        return totalMarks;
    }

    public String getGradeForSpecificCourse(String courseCode) {
        Course course = CourseRepository.getInstance().getCourseByCode(courseCode);
        if (course == null || !registeredCourses.containsKey(course)) {
            return null;
        }
        GradeBook gradeBookEntry = getGradeForCourse(course);
        return (gradeBookEntry != null) ? gradeBookEntry.getGradeDetails() : "No grades recorded yet.";
    }

    public void displayGradesForAllCourses() {
        Map<String, String> grades = getAllGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades available to display.");
        } else {
            grades.forEach((key, value) -> {
                System.out.println("Course Code: " + key + " | Grade: " + value);
            });
        }
    }

    public void displayGradeForSpecificCourse(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
            System.out.println("Invalid course code provided.");
            return;
        }
        String grade = getGradeForSpecificCourse(courseCode);
        if (grade == null) {
            System.out.println("You are not registered for the course with code: " + courseCode);
        } else {
            System.out.println("Course Code: " + courseCode + " | Grade: " + grade);
        }
    }

    public void viewTranscript() {
        Transcript transcript = new Transcript(this);
        System.out.println(transcript.generateTranscript());
        transcript.printStatistics();
    }

    @Override
    public String toString() {
        return String.format("+---------------------------------------------+\n" +
                "| %-15s | %-28s |\n" +
                "+---------------------------------------------+\n" +
                "| %-15s | %-28s |\n" +
                "| %-15s | %-28s |\n" +
                "| %-15s | %-28s |\n" +
                "| %-15s | %-28s |\n" +
                "| %-15s | %-28s |\n" +
                "+---------------------------------------------+\n",
                "Student ID", getId(),
                "Full Name", getFullName(),
                "Year of Study", yearOfStudy,
                "Faculty", faculty,
                "Scholarship", canHaveScholarship ? "Yes" : "No",
                "Supervisor", (supervisor != null ? supervisor.getResearcherName() : "No supervisor"));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student student = (Student) obj;
        return Objects.equals(getId(), student.getId()) &&
                Objects.equals(getFullName(), student.getFullName()) &&
                yearOfStudy == student.yearOfStudy &&
                faculty == student.faculty &&
                canHaveScholarship == student.canHaveScholarship;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFullName(), yearOfStudy, faculty, canHaveScholarship, supervisor);
    }

    @Override
    public void displayFunct() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                displayMainMenu();
                int choice = getChoice(scanner);
                scanner.nextLine();
                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        StudentRepository.getInstance().updateStudent(this);
                        super.logout();
                        return;
                    case 1:
                        viewSchedule();
                        break;
                    case 2:
                        viewCourses();
                        break;
                    case 3:
                        registerForCourseMenu(scanner);
                        break;
                    case 4:
                        viewCourseDetailsMenu(scanner);
                        break;
                    case 5:
                        displayGradesForAllCourses();
                        break;
                    case 6:
                        displayGradeForSpecificCourseMenu(scanner);
                        break;
                    case 7:
                        setSupervisorMenu(scanner);
                        break;
                    case 8:
                        joinStudentOrganizationMenu(scanner);
                        break;
                    case 9:
                        viewTranscript();
                        break;
                    case 10:
                        super.viewNews();
                        break;
                    case 11:
                        super.viewSubscribedJournals();
                        break;
                    case 12:
                        super.viewNotifications();
                        break;
                    case 13:
                        super.subscribeToJournalMenu(scanner);
                        break;
                    case 14:
                        becomeResearcher();
                        break;
                    case 15:
                        publishResearchPaperMenu(scanner);
                        break;
                    case 16:
                        sortPublicationsMenu(scanner);
                        break;
                    case 17:
                        proposeResearchProjectMenu(scanner);
                        break;
                    case 18:
                        joinResearchProjectMenu(scanner);
                        break;
                    case 19:
                        leadResearchProjectMenu(scanner);
                        break;
                    case 20:
                        viewResearchProjects();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void displayMainMenu() {
        System.out.println("Student Functions:");
        System.out.println("0. Exit");
        System.out.println("1. View Schedule");
        System.out.println("2. View Courses");
        System.out.println("3. Register for Course");
        System.out.println("4. View Course Details");
        System.out.println("5. View Grades for All Courses");
        System.out.println("6. View Grade for Specific Course");
        System.out.println("7. Set Supervisor");
        System.out.println("8. Join Student Organization");
        System.out.println("9. View Transcript");
        System.out.println("10. View News");
        System.out.println("11. View Subscribed Journals");
        System.out.println("12. View Notifications");
        System.out.println("13. Subscribe to Journal");
        if (!ResearcherRepository.getInstance().exists(this)) {
            System.out.println("14. Become a Researcher");
        } else {
            System.out.println("Available Researcher Actions:");
            System.out.println("15. Publish a research paper");
            System.out.println("16. View publications");
            System.out.println("17. Propose a new research project");
            System.out.println("18. Join an existing project");
            System.out.println("19. Become project leader");
            System.out.println("20. View research projects");
        }
    }

    private int getChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void registerForCourseMenu(Scanner scanner) {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = CourseRepository.getInstance().getCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.println("Available lessons for " + course.getCourseName() + ":");
        List<Lesson> lessons = course.getLessons();
        if (lessons.isEmpty()) {
            System.out.println("No lessons available for this course.");
            return;
        }

        for (int i = 0; i < lessons.size(); i++) {
            System.out.println((i + 1) + ". " + lessons.get(i));
        }

        System.out.print("Enter the numbers of the lessons you want to register for (comma-separated): ");
        String lessonNumbersInput = scanner.nextLine();
        List<Lesson> selectedLessons = getSelectedLessons(lessons, lessonNumbersInput);

        if (selectedLessons.isEmpty()) {
            System.out.println("No valid lessons selected. Registration failed.");
        } else {
            boolean registrationSuccessful = registerForCourse(courseCode, selectedLessons);
            if (registrationSuccessful) {
                System.out.println("Successfully registered for selected lessons.");
            } else {
                System.out.println("Failed to register for the course.");
            }
        }
    }

    private List<Lesson> getSelectedLessons(List<Lesson> lessons, String lessonNumbersInput) {
        List<Lesson> selectedLessons = new ArrayList<>();
        String[] lessonNumbers = lessonNumbersInput.split(",");

        for (String lessonNumber : lessonNumbers) {
            try {
                int lessonIndex = Integer.parseInt(lessonNumber.trim()) - 1; // Convert to 0-based index
                if (lessonIndex >= 0 && lessonIndex < lessons.size()) {
                    selectedLessons.add(lessons.get(lessonIndex));
                } else {
                    System.out.println("Invalid lesson number: " + (lessonIndex + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers separated by commas.");
            }
        }

        return selectedLessons;
    }

    private void viewCourseDetailsMenu(Scanner scanner) {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.println(viewCourseDetails(courseCode));
    }

    private void displayGradeForSpecificCourseMenu(Scanner scanner) {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        displayGradeForSpecificCourse(courseCode);
    }

    private void setSupervisorMenu(Scanner scanner) {
        if (getYearOfStudy() != 4) {
            System.out.println("Only 4th-year students can have a supervisor.");
        } else {
            List<Researcher> validSupervisors = ResearcherRepository.getInstance().getResearcherSupervisors();
            if (validSupervisors.isEmpty()) {
                System.out.println("No valid supervisors available.");
            } else {
                System.out.println("Available supervisors (h-index >= 3):");
                for (int i = 0; i < validSupervisors.size(); i++) {
                    Researcher supervisor = validSupervisors.get(i);
                    System.out.println(i + 1 + ". " + supervisor.getResearcherName() + " (h-index: "
                            + supervisor.calculateHIndex() + ")");
                }
                System.out.print("Enter the number of the supervisor you want to select: ");
                int researcherNum = scanner.nextInt();
                scanner.nextLine();
                if (researcherNum >= 1 && researcherNum <= validSupervisors.size()) {
                    Researcher selectedSupervisor = validSupervisors.get(researcherNum - 1);
                    setSupervisor(selectedSupervisor);
                    System.out.println("Supervisor " + selectedSupervisor.getResearcherName() + " has been selected.");
                } else {
                    System.out.println("Invalid selection. Supervisor not selected.");
                }
            }
        }
    }

    private void joinStudentOrganizationMenu(Scanner scanner) {
        System.out.print("Enter student organization name: ");
        String orgName = scanner.nextLine();
        System.out.print("Enter your role in the organization: ");
        String role = scanner.nextLine();
        StudentOrganization so = StudentOrganizationRepository.getInstance().getOrganizationByName(orgName);
        if (so != null) {
            joinStudentOrganization(so, role);
        } else {
            System.out.println("Organization not found.");
        }
    }

    private void publishResearchPaperMenu(Scanner scanner) {
        StudentResearcher researcher = (StudentResearcher) ResearcherRepository.getInstance().getResearcherByUser(this);
        System.out.print("Enter paper title: ");
        String paperTitle = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int publicationYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number of pages: ");
        int numberOfPages = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter citation number: ");
        int citationNumber = Integer.parseInt(scanner.nextLine());
        ResearchPaper paper = new ResearchPaper(paperTitle, researcher, publicationYear, numberOfPages, citationNumber);
        researcher.publishPaper(paper);

        System.out.println("Research paper published: " + paper.getTitle());
    }

    private void sortPublicationsMenu(Scanner scanner) {
        StudentResearcher researcher = (StudentResearcher) ResearcherRepository.getInstance().getResearcherByUser(this);
        System.out.println("Available Sorting Options for Publications:");
        System.out.println("1. Sort by Citations");
        System.out.println("2. Sort by Publication Date");
        System.out.println("3. Sort by Number of Pages");

        System.out.print("Enter choice: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine();

        Comparator<ResearchPaper> comparator = null;

        switch (sortChoice) {
            case 1:
                comparator = new ComparatorByCitation();
                break;
            case 2:
                comparator = new ComparatorByDate();
                break;
            case 3:
                comparator = new ComparatorByPages();
                break;
            default:
                System.out.println("Invalid choice. Using default sorting.");
                comparator = new ComparatorByCitation();
                break;
        }

        System.out.println("Your Publications (sorted by chosen option):");
        researcher.printPapers(comparator);
    }

    private void proposeResearchProjectMenu(Scanner scanner) {
        StudentResearcher researcher = (StudentResearcher) ResearcherRepository.getInstance().getResearcherByUser(this);
        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();
        researcher.proposeProject(projectName);
    }

    private void joinResearchProjectMenu(Scanner scanner) {
        StudentResearcher researcher = (StudentResearcher) ResearcherRepository.getInstance().getResearcherByUser(this);
        System.out.print("Enter project name to join: ");
        String joinProjectName = scanner.nextLine();
        ResearchProject projectToJoin = ResearchProjectRepository.getInstance().getProjectByName(joinProjectName);
        if (projectToJoin != null) {
            researcher.joinProject(projectToJoin);
        } else {
            System.out.println("Project not found.");
        }
    }

    private void leadResearchProjectMenu(Scanner scanner) {
        StudentResearcher researcher = (StudentResearcher) ResearcherRepository.getInstance().getResearcherByUser(this);
        System.out.print("Enter project name to lead: ");
        String leadProjectName = scanner.nextLine();
        ResearchProject projectToLead = ResearchProjectRepository.getInstance().getProjectByName(leadProjectName);
        if (projectToLead != null) {
            researcher.becomeProjectLeader(projectToLead);
        } else {
            System.out.println("Project not found.");
        }
    }

    private void viewResearchProjects() {
        StudentResearcher researcher = (StudentResearcher) ResearcherRepository.getInstance().getResearcherByUser(this);
        System.out.print("Research Projects where student participating: ");
        ResearchProjectRepository.getInstance().getProjectsByResearcher(researcher);
    }

}
