package repositories;

import java.io.*;
import java.util.*;

import users.Admin;
import users.Dean;
import users.Employee;
import users.FinanceManager;
import users.Manager;
import users.Student;
import users.Teacher;
import users.User;

public class UserRepository {
    private static final String STUDENT_FILE = "src/data/students.dat";
    private static final String TEACHER_FILE = "src/data/teachers.dat";
    private static final String MANAGER_FILE = "src/data/managers.dat";
    private static final String FINANCE_MANAGER_FILE = "src/data/finance_managers.dat";
    private static final String ADMIN_FILE = "src/data/admins.dat";
    private static final String DEAN_FILE = "src/data/deans.dat";

    private static UserRepository instance;

    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();
    private List<FinanceManager> financeManagers = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Dean> deans = new ArrayList<>();

    private UserRepository() {
    	loadAllUsers();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void addUser(User user) {
        if (user instanceof Student) {
        	if(!students.contains((Student) user)) {
        		students.add((Student) user);
                saveUsersToFile(students, STUDENT_FILE);
        	}
        } else if (user instanceof Teacher) {
        	if(!teachers.contains((Teacher) user)) {
        		teachers.add((Teacher) user);
                saveUsersToFile(teachers, TEACHER_FILE);
        	}
        } else if (user instanceof Manager) {
        	if(!managers.contains((Manager) user)) {
        		managers.add((Manager) user);
                saveUsersToFile(managers, MANAGER_FILE);
        	}
        } else if (user instanceof FinanceManager) {
        	if(!financeManagers.contains((FinanceManager) user)) {
        		financeManagers.add((FinanceManager) user);
                saveUsersToFile(financeManagers, FINANCE_MANAGER_FILE);
        	}
        } else if (user instanceof Admin) {
        	if(!admins.contains((Admin) user)) {
        		admins.add((Admin) user);
                saveUsersToFile(admins, ADMIN_FILE);
        	}
        } else if (user instanceof Dean) {
        	if(!deans.contains((Dean) user)) {
        		deans.add((Dean) user);
                saveUsersToFile(deans, DEAN_FILE);
        	}
        }
    }

    public void removeUser(User user) {
        if (user instanceof Student) {
            students.remove(user);
            saveUsersToFile(students, STUDENT_FILE);
        } else if (user instanceof Teacher) {
            teachers.remove(user);
            saveUsersToFile(teachers, TEACHER_FILE);
        } else if (user instanceof Manager) {
            managers.remove(user);
            saveUsersToFile(managers, MANAGER_FILE);
        } else if (user instanceof FinanceManager) {
            financeManagers.remove(user);
            saveUsersToFile(financeManagers, FINANCE_MANAGER_FILE);
        } else if (user instanceof Admin) {
            admins.remove(user);
            saveUsersToFile(admins, ADMIN_FILE);
        } else if (user instanceof Dean) {
            deans.remove(user);
            saveUsersToFile(deans, DEAN_FILE);
        }
    }

    public <T extends User> List<T> getUsersForType(Class<T> userType) {
        if (userType == Student.class) {
            return (List<T>) students;
        } else if (userType == Teacher.class) {
            return (List<T>) teachers;
        } else if (userType == Manager.class) {
            return (List<T>) managers;
        } else if (userType == FinanceManager.class) {
            return (List<T>) financeManagers;
        } else if (userType == Admin.class) {
            return (List<T>) admins;
        } else if (userType == Dean.class) {
            return (List<T>) deans;
        }else if (userType == Employee.class) {
            List<Employee> allEmployees = new ArrayList<>();
            allEmployees.addAll(managers);
            allEmployees.addAll(financeManagers);
            allEmployees.addAll(teachers);
            allEmployees.addAll(deans);
            allEmployees.addAll(admins);
            return (List<T>) allEmployees;
        }
        return new ArrayList<>();
    }


    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        allUsers.addAll(students);
        allUsers.addAll(teachers);
        allUsers.addAll(managers);
        allUsers.addAll(financeManagers);
        allUsers.addAll(admins);
        allUsers.addAll(deans);
        return allUsers;
    }

    private <T extends User> void saveUsersToFile(List<T> users, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T extends User> List<T> loadUsersFromFile(String fileName) {
        List<T> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            users = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void loadAllUsers() {
        students = loadUsersFromFile(STUDENT_FILE);
        teachers = loadUsersFromFile(TEACHER_FILE);
        managers = loadUsersFromFile(MANAGER_FILE);
        financeManagers = loadUsersFromFile(FINANCE_MANAGER_FILE);
        admins = loadUsersFromFile(ADMIN_FILE);
        deans = loadUsersFromFile(DEAN_FILE);
    }

    public void saveAllUsers() {
        saveUsersToFile(students, STUDENT_FILE);
        saveUsersToFile(teachers, TEACHER_FILE);
        saveUsersToFile(managers, MANAGER_FILE);
        saveUsersToFile(financeManagers, FINANCE_MANAGER_FILE);
        saveUsersToFile(admins, ADMIN_FILE);
        saveUsersToFile(deans, DEAN_FILE);
    }
    
    public void updateUser(User updatedUser) {
        if (updatedUser instanceof Student) {
            updateUserInList(updatedUser, students, STUDENT_FILE);
        } else if (updatedUser instanceof Teacher) {
            updateUserInList(updatedUser, teachers, TEACHER_FILE);
        } else if (updatedUser instanceof Manager) {
            updateUserInList(updatedUser, managers, MANAGER_FILE);
        } else if (updatedUser instanceof FinanceManager) {
            updateUserInList(updatedUser, financeManagers, FINANCE_MANAGER_FILE);
        } else if (updatedUser instanceof Admin) {
            updateUserInList(updatedUser, admins, ADMIN_FILE);
        } else if (updatedUser instanceof Dean) {
            updateUserInList(updatedUser, deans, DEAN_FILE);
        }
    }

    private <T extends User> void updateUserInList(User updatedUser, List<T> userList, String fileName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(updatedUser.getId())) {
                userList.set(i, (T) updatedUser);
                saveUsersToFile(userList, fileName);
                return;
            }
        }
    }

	public User getUserById(String userId) {
		List<User> allUsers = getAllUsers();
		return allUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
	}
	
	public User authenticate(String email, String password) {
        List<User> allUsers = getAllUsers();
        for (User user : allUsers) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                user.setLogged(true);
                return user;
            }
        }
        throw new RuntimeException("Invalid login credentials. Please try again.");
    }
}


