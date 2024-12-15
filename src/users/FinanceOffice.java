package users;

import java.util.*;

public class FinanceOffice {
    private Map<Student, Double> studentBalances = new HashMap<>();
    private Map<Employee, Double> employeeSalaries = new HashMap<>();
    private List<String> transactionLog = new ArrayList<>();
    private List<FinanceManager> workers = new ArrayList<>();

    public void addStudent(Student student, double balance) {
        studentBalances.put(student, balance);
        transactionLog.add("Added student: " + student.getFullName() + " with balance: " + balance);
    }

    public void addEmployee(Employee employee, double salary) {
        employeeSalaries.put(employee, salary);
        transactionLog.add("Added employee: " + employee.getFullName() + " with salary: " + salary);
    }

    public void processTuitionPayment(Student student, double amount) {
        if (studentBalances.containsKey(student)) {
            double currentBalance = studentBalances.get(student);
            studentBalances.put(student, currentBalance + amount);
            transactionLog.add("Processed tuition payment for " + student.getFullName() + ": " + amount);
        } else {
            System.out.println("Student not found!");
        }
    }

    public void processSalaryPayment(Employee employee) {
        if (employeeSalaries.containsKey(employee)) {
            double salary = employeeSalaries.get(employee);
            transactionLog.add("Paid salary to " + employee.getFullName() + ": " + salary);
        } else {
            System.out.println("Employee not found!");
        }
    }

    public void allocateScholarship(Student student, double amount) {
        if (studentBalances.containsKey(student) && student.canHaveScholarship()) {
            double currentBalance = studentBalances.get(student);
            studentBalances.put(student, currentBalance + amount);
            transactionLog.add("Allocated scholarship to " + student.getFullName() + ": " + amount);
        } else {
            System.out.println("Student not eligible or not found!");
        }
    }

    public void generateReport() {
        System.out.println("Transaction Log:");
        for (String log : transactionLog) {
            System.out.println(log);
        }
    }
}