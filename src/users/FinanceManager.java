package users;

import java.util.List;
import enums.ManagerType;

public class FinanceManager extends Employee {
	private static final long serialVersionUID = 1L;
	private ManagerType managerType;

    public FinanceManager(String id, String firstName, String lastName, String email, String password, int salary, ManagerType managerType) {
        super(id, firstName, lastName, email, password, salary);
        this.managerType = managerType;
    }

	@Override
	public void displayFunct() {
		// TODO Auto-generated method stub
		
	}

//    public void scholarshipReport(List<Student> students) {
//        for (Student student : students) {
//            boolean isEligibleForScholarship = true;
//            
//            for (SubjectScore score : student.getSubjectScores()) {
//                if (score.getScore() < 70) {  
//                    isEligibleForScholarship = false;
//                    System.out.println(student.getFullName() + " Doesnt not receive a scholarship: " + score.getSubject());
//                    break;
//                }
//            }
//
//            if (isEligibleForScholarship) {
//                System.out.println(student.getFullName() + " receive a scholarship!");
//            }
//        }
//    }
//    
//    public void generateSalaryReport(List<Employee> employees) {
//        for (Employee employee : employees) {
//            int baseSalary = employee.getSalary();
//            System.out.println(employee.getFullName() + " получает зарплату: " + baseSalary);
//        }
//    }
//
//    public  void displayFunct() {
//     System.out.println("Finance Manager can: generate scholarship report and generate salary report");
//    }
    
}