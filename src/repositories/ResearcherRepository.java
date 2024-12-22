package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import research.EmployeeResearcher;
import research.Researcher;
import research.StudentResearcher;
import research.TeacherResearcher;
import users.Employee;
import users.Student;
import users.Teacher;
import users.User;


public class ResearcherRepository {
    private static ResearcherRepository instance;
    private Map<User, Researcher> researchers;
    
    private ResearcherRepository() {
    	researchers = new HashMap<>();
    }

    public static ResearcherRepository getInstance() {
        if (instance == null) {
            instance = new ResearcherRepository();
        }
        return instance;
    }

    public void addResearcher(User user) {
    	if (user instanceof Student) {
    		researchers.put(user, new StudentResearcher((Student)user));
        } else if (user instanceof Teacher) {
        	researchers.put(user, new TeacherResearcher((Teacher)user));
        }
        else if (user instanceof Employee) {
        	researchers.put(user, new EmployeeResearcher((Employee)user));
        }
    }

    public Researcher getResearcherByUser(User user) {
        return researchers.get(user);
    }
    
    public boolean exists(User user) {
    	return researchers.containsKey(user);
    }

    public List<Researcher> getAllResearchers() {
        return new ArrayList<>(researchers.values());
    }
    
    public List<Researcher> getResearcherSupervisors() {
        List<Researcher> supervisors = new ArrayList<>();
        for (Researcher researcher : researchers.values()) {
            if (researcher.calculateHIndex() >= 3) {
                supervisors.add(researcher);
            }
        }
        return supervisors;
    }
    
}
