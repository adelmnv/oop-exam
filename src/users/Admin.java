package users;

import java.util.ArrayList;
import java.util.List;

import research.EmployeeResearcher;
import research.Researcher;

//доделать (фабрика пользователей, полностью управляет всеми пользователями)
public class Admin extends Employee {
    private static final long serialVersionUID = 1L;
    private List<User> users;

    public Admin(String id, String firstName, String lastName, String email, String password, int salary) {
        super(id, firstName, lastName, email, password, salary);
        this.users = new ArrayList<>();
    }
    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
            System.out.println("add user: " + user.getFirstName() + " " + user.getLastName());
        } else {
            System.out.println("Error,user cant be null.");
        }
    }

    public void deleteUser(User user) {
        if (user != null ) {
            users.remove(user);
            System.out.println("remove user: " + user.getFirstName() + " " + user.getLastName());
        } else {
            System.out.println("error,user doesnt found.");
        }
    }

    public void updateUser(User oldUser, User newUser) {
        if (users.contains(oldUser)) {
            users.remove(oldUser);
            users.add(newUser);
            System.out.println("update a user: " + newUser.getFirstName() + " " + newUser.getLastName());
        } else {
            System.out.println("Error: user doesnt found.");
        }
    }

    public void viewLogs() {
        System.out.println("admin view the log");
    }

    @Override
    public void displayFunct() {
        System.out.println("admin can: add a user, remove a user, update a user,view the log ");
    }
}
