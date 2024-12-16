package users;

import java.util.List;
import java.util.Map;

import enums.ManagerType;
import grading.GradeBook;

public class FinanceManager extends Employee {
	private static final long serialVersionUID = 1L;
	private ManagerType managerType;

    public FinanceManager(String id, String firstName, String lastName, String email, String password, int salary, ManagerType managerType) {
        super(id, firstName, lastName, email, password, salary);
        this.managerType = managerType;
    }

//    public void scholarshipReport(List<Student> students) {
//        for (Student student : students) {
//            Map<String, String> gradeBook = student.getAllGrades(); 
//            double totalMark = gradeBook.getTotalMark();
//
//            if (totalMark >= 70 && student.canHaveScholarship()) {
//                System.out.println(student.getFullName() + " receives a scholarship!");
//            } else {
//                System.out.println(student.getFullName() + " doesnt receive a scholarship.");
//            }
//        }
//    }

    public void generateSalaryReport(List<Employee> employees) {
        for (Employee employee : employees) {
            int baseSalary = employee.getSalary();
            System.out.println(employee.getFullName() + " получает зарплату: " + baseSalary);
        }
    }
    
    public  void displayFunct() {
     System.out.println("Finance Manager can: generate scholarship report and generate salary report");
    }

	public ManagerType getManagerType() {
		return managerType;
	}

	public void setManagerType(ManagerType managerType) {
		this.managerType = managerType;
	}
    
}