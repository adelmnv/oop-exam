package users;

import java.util.List;
import java.util.Scanner;
import enums.ManagerType;
import grading.GradeBook;
import studying.Course;

public class FinanceManager extends Employee {
    private static final long serialVersionUID = 1L;
    private ManagerType managerType;

    public FinanceManager(String id, String firstName, String lastName, String email, String password, int salary, ManagerType managerType) {
        super(id, firstName, lastName, email, password, salary);
        this.managerType = managerType;
    }

    public void displayFunct() {
        System.out.println("Select an option by entering the corresponding number:");
        System.out.println("1. Generate salary report");
        System.out.println("2. Generate scholarship report");
        System.out.println("3. Exit");
    }

    public void chooseFunction(int choice, List<Employee> employees, List<Student> students, List<Course> courses) {
        switch (choice) {
            case 1:
                generateSalaryReport(employees);
                break;
            case 2:
  //              generateScholarshipReport(courses, students); // Передаем оба списка
                break;
            case 3:
                System.out.println("Exiting the program.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void startMenu(List<Employee> employees, List<Student> students, List<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayFunct();
            int choice = scanner.nextInt();
            chooseFunction(choice, employees, students, courses); // Передаем список курсов
            if (choice == 3) {
                break;
            }
        }
        scanner.close();
    }

    public void generateSalaryReport(List<Employee> employees) {
        for (Employee employee : employees) {
            int baseSalary = employee.getSalary();
            System.out.println(employee.getFullName() + " получает зарплату: " + baseSalary);
        }
    }

//  public void scholarshipReport(List<Student> students) {
//  for (Student student : students) {
//      Map<String, String> gradeBook = student.getAllGrades(); 
//      double totalMark = gradeBook.getTotalMark();
//
//      if (totalMark >= 70 && student.canHaveScholarship()) {
//          System.out.println(student.getFullName() + " receives a scholarship!");
//      } else {
//          System.out.println(student.getFullName() + " doesnt receive a scholarship.");
//      }
//  }
//}

    @Override
    public String toString() {
        return "FinanceManager{" +
                "managerType=" + managerType +
                ", id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", salary=" + getSalary() +
                '}';
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

}
