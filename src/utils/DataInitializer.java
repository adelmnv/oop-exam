package utils;

import enums.Faculty;
import enums.LessonType;
import enums.ManagerType;
import enums.NewsTopic;
import enums.TeacherTitle;
import news.Journal;
import news.News;
import news.NewsManager;
import repositories.CourseRepository;
import repositories.JournalRepository;
import repositories.ResearchProjectRepository;
import repositories.ResearcherRepository;
import repositories.StudentOrganizationRepository;
import repositories.UserRepository;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;
import studying.Course;
import studying.Lesson;
import users.Admin;
import users.Dean;
import users.FinanceManager;
import users.Manager;
import users.Student;
import users.StudentOrganization;
import users.Teacher;

public class DataInitializer {
    public static void initialize() {
        UserRepository userRepository = UserRepository.getInstance();
        CourseRepository courseRepository = CourseRepository.getInstance();
        ResearcherRepository researcherRepository = ResearcherRepository.getInstance();
        JournalRepository journalRepository = JournalRepository.getInstance();
        NewsManager newsManager = NewsManager.getInstance();
        
        Student student1 = new Student("Alexander", "Ivanov", "alex.ivanov@university.com", "password1", 2, Faculty.SITE, true);
        Student student2 = new Student("Maria", "Petrova", "maria.petrova@university.com", "password2", 3, Faculty.BS, false);
        Student student3 = new Student("Dmitry", "Sokolov", "dmitry.sokolov@university.com", "password3", 1, Faculty.KMA, true);
        Student student4 = new Student("Anna", "Smirnova", "anna.smirnova@university.com", "password4", 4, Faculty.SEPI, false);
        Student student5 = new Student("Igor", "Kuznetsov", "igor.kuznetsov@university.com", "password5", 2, Faculty.SG, true);
        Student student6 = new Student("Elena", "Vasileva", "elena.vasileva@university.com", "password6", 3, Faculty.ISE, false);
        Student student7 = new Student("Olga", "Popova", "olga.popova@university.com", "password7", 1, Faculty.SAM, true);

        userRepository.addUser(student1);
        userRepository.addUser(student2);
        userRepository.addUser(student3);
        userRepository.addUser(student4);
        userRepository.addUser(student5);
        userRepository.addUser(student6);
        userRepository.addUser(student7);

        Teacher teacher1 = new Teacher("Sergey", "Ivanov", "sergey.ivanov@university.com", "password8", 75000, Faculty.SITE, TeacherTitle.SENIOR_LECTOR);
        Teacher teacher2 = new Teacher("Natalia", "Smirnova", "natalia.smirnova@university.com", "password9", 80000, Faculty.BS, TeacherTitle.PROFFESOR);
        Teacher teacher3 = new Teacher("Vladimir", "Petrov", "vladimir.petrov@university.com", "password10", 72000, Faculty.KMA, TeacherTitle.TUTOR);
        Teacher teacher4 = new Teacher("Svetlana", "Sokolova", "svetlana.sokolova@university.com", "password11", 73000, Faculty.SEPI, TeacherTitle.LECTOR);
        Teacher teacher5 = new Teacher("Andrey", "Kuznetsov", "andrey.kuznetsov@university.com", "password12", 85000, Faculty.SG, TeacherTitle.PROFFESOR);

        userRepository.addUser(teacher1);
        userRepository.addUser(teacher2);
        userRepository.addUser(teacher3);
        userRepository.addUser(teacher4);
        userRepository.addUser(teacher5);

        userRepository.addUser(new Manager("Oleg", "Sidorov", "oleg.sidorov@university.com", "password15", 90000, ManagerType.OR));
        userRepository.addUser(new FinanceManager("Irina", "Petrova", "irina.petrova@university.com", "password16", 95000));
        userRepository.addUser(new Admin("Andrei", "Smirnov", "andrei.smirnov@university.com", "password17", 98000));
        userRepository.addUser(new Dean("Boris", "Ivanov", "boris.ivanov@university.com", "password18", 100000));


        Course course1 = new Course("Introduction to Programming", "CS101", Faculty.SITE, 4, 1, 50);
        Course course2 = new Course("Discrete Mathematics", "CS102", Faculty.SITE, 3, 1, 50);
        Course course3 = new Course("Linear Algebra", "MA201", Faculty.BS, 3, 2, 40);
        Course course4 = new Course("Operating Systems", "CS301", Faculty.SITE, 4, 3, 30);
        Course course5 = new Course("Data Structures", "CS202", Faculty.SITE, 4, 2, 40);
        
        teacher1.addCourse("CS101");
        teacher3.addCourse("CS101");
        teacher2.addCourse("CS102");
        teacher4.addCourse("CS102");
        teacher1.addCourse("MA201");
        teacher3.addCourse("MA201");
        teacher2.addCourse("CS301");
        teacher4.addCourse("CS301");
        teacher5.addCourse("CS202");
        teacher3.addCourse("CS202");

        course1.getLessons().add(new Lesson("Room 101", "Monday", "10:00", LessonType.LECTURE, teacher1));
        course1.getLessons().add(new Lesson("Room 101", "Wednesday", "10:00", LessonType.LECTURE, teacher1));
        course1.getLessons().add(new Lesson("Lab A", "Friday", "14:00", LessonType.PRACTICE, teacher3));

        course2.getLessons().add(new Lesson("Room 102", "Tuesday", "12:00", LessonType.LECTURE, teacher2));
        course2.getLessons().add(new Lesson("Room 102", "Thursday", "12:00", LessonType.LECTURE, teacher2));
        course2.getLessons().add(new Lesson("Lab B", "Friday", "16:00", LessonType.PRACTICE, teacher4));

        course3.getLessons().add(new Lesson("Room 103", "Monday", "14:00", LessonType.LECTURE, teacher3));
        course3.getLessons().add(new Lesson("Room 103", "Wednesday", "14:00", LessonType.LECTURE, teacher3));
        course3.getLessons().add(new Lesson("Lab C", "Friday", "10:00", LessonType.PRACTICE, teacher1));

        course4.getLessons().add(new Lesson("Room 104", "Tuesday", "10:00", LessonType.LECTURE, teacher4));
        course4.getLessons().add(new Lesson("Room 104", "Thursday", "10:00", LessonType.LECTURE, teacher4));
        course4.getLessons().add(new Lesson("Lab D", "Friday", "12:00", LessonType.PRACTICE, teacher2));

        course5.getLessons().add(new Lesson("Room 105", "Monday", "08:00", LessonType.LECTURE, teacher5));
        course5.getLessons().add(new Lesson("Room 105", "Wednesday", "08:00", LessonType.LECTURE, teacher5));
        course5.getLessons().add(new Lesson("Lab E", "Friday", "18:00", LessonType.PRACTICE, teacher3));

        courseRepository.addCourse(course1);
        courseRepository.addCourse(course2);
        courseRepository.addCourse(course3);
        courseRepository.addCourse(course4);
        courseRepository.addCourse(course5);
        
        researcherRepository.addResearcher(student1);
        researcherRepository.addResearcher(teacher1);
        researcherRepository.addResearcher(teacher2);

        Researcher researcher1 = researcherRepository.getResearcherByUser(student1);
        Researcher researcher2 = researcherRepository.getResearcherByUser(teacher1);
        Researcher researcher3 = researcherRepository.getResearcherByUser(teacher2);

        ResearchProject project1 = new ResearchProject("AI in Education", researcher1);
        project1.addParticipant(researcher2);
        project1.addParticipant(researcher3);

        ResearchProject project2 = new ResearchProject("Sustainable Computing", researcher2);
        project2.addParticipant(researcher1);

        ResearchProject project3 = new ResearchProject("Quantum Algorithms", researcher3);

        project1.addPaper(new ResearchPaper("Innovative AI Models", researcher1,2024,5,3));
        project1.addPaper(new ResearchPaper("Collaborative Learning Systems", researcher2,2023,8,4));

        project2.addPaper(new ResearchPaper("Green Computing Strategies", researcher1,2024,8,1));
        project2.addPaper(new ResearchPaper("Energy Efficient Software", researcher2,2024,9,4));

        project3.addPaper(new ResearchPaper("Quantum Search Techniques", researcher3,2024,7,2));
        
        ResearchProjectRepository.getInstance().addProject(project1);
        ResearchProjectRepository.getInstance().addProject(project2);
        ResearchProjectRepository.getInstance().addProject(project3);

        Journal journal1 = new Journal("AI and Future Technologies");
        journal1.getPublishedProjects().add(project1);
        journal1.getPublishedProjects().add(project2);

        Journal journal2 = new Journal("Advanced Computing Research");
        journal2.getPublishedProjects().add(project3);

        journalRepository.addJournal(journal1);
        journalRepository.addJournal(journal2);
        
        student1.subscribeToJournal(journal1);
        student2.subscribeToJournal(journal2);
        student1.subscribeToJournal(journal2);
        student2.subscribeToJournal(journal1);
        
        newsManager.addNews(new News("AI models in education are being tested.", "AI in Education Research", NewsTopic.RESEARCH));
        newsManager.addNews(new News("Quantum computing for algorithms is making significant progress.", "Quantum Algorithms Research", NewsTopic.RESEARCH));
        
        newsManager.addNews(new News("University annual conference on technology innovations.", "Tech Innovations Conference", NewsTopic.EVENTS));
        newsManager.addNews(new News("Semester finals start next week.", "Semester Finals", NewsTopic.EVENTS));
        
        newsManager.addNews(new News("The university is introducing new research grants for students.", "New Research Grants Announced", NewsTopic.ANNOUNCEMENT));
        newsManager.addNews(new News("New internship opportunities available for computer science students.", "Internship Opportunities", NewsTopic.ANNOUNCEMENT));
        
        StudentOrganization codingClub = new StudentOrganization("Coding Club");
        
        codingClub.addMember(student1, "President");
        codingClub.addMember(student2, "SMM");

        codingClub.getEvents().add("Hackathon");
        codingClub.getEvents().add("Coding Bootcamp");
        
        StudentOrganizationRepository.getInstance().addOrganization(codingClub);

        
        userRepository.saveAllUsers();
        courseRepository.saveCoursesToFile();
        journalRepository.saveJournalsToFile();
        

        System.out.println("Data successfully initialized and saved.");
    }
}


