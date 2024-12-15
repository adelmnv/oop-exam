package users;

import java.util.*;

public class FinanceOffice {
    private Map<Student, Double> studentBalances = new HashMap<>();
    private Map<Employee, Double> employeeSalaries = new HashMap<>();
    private List<String> transactionLog = new ArrayList<>();
    private List<FinanceManager> workers = new ArrayList<>();

    public void addStudent(Student student, double balance) {
        if (!studentBalances.containsKey(student)) {
            studentBalances.put(student, balance);
            transactionLog.add("Added student: " + student.getFullName() + " with balance: " + balance);
        } else {
            System.out.println("Student " + student.getFullName() + " already exists!");
        }
    }

    public void addEmployee(Employee employee, double salary) {
        if (!employeeSalaries.containsKey(employee)) {
            employeeSalaries.put(employee, salary);
            transactionLog.add("Added employee: " + employee.getFullName() + " with salary: " + salary);
        } else {
            System.out.println("Employee " + employee.getFullName() + " already exists!");
        }
    }

    public void processTuitionPayment(Student student, double amount) {
        if (studentBalances.containsKey(student)) {
            double currentBalance = studentBalances.get(student);
            studentBalances.put(student, currentBalance + amount);
            transactionLog.add("Processed tuition payment for " + student.getFullName() + ": " + amount);
        } else {
            System.out.println("Student " + student.getFullName() + " not found!");
        }
    }

    public void processSalaryPayment(Employee employee) {
        if (employeeSalaries.containsKey(employee)) {
            double salary = employeeSalaries.get(employee);
            transactionLog.add("Paid salary to " + employee.getFullName() + ": " + salary);
        } else {
            System.out.println("Employee " + employee.getFullName() + " not found!");
        }
    }

    public void allocateScholarship(Student student, double amount) {
        if (studentBalances.containsKey(student) && student.canHaveScholarship()) {
            double currentBalance = studentBalances.get(student);
            studentBalances.put(student, currentBalance + amount);
            transactionLog.add("Allocated scholarship to " + student.getFullName() + ": " + amount);
        } else {
            System.out.println("Student " + student.getFullName() + " is not eligible or not found!");
        }
    }

//    public void processFinancialOperations() {
//        for (Student student : studentBalances.keySet()) {
//            System.out.println("Processing financial operations for student: " + student.getFullName());
//        }
//        for (Employee employee : employeeSalaries.keySet()) {
//            System.out.println("Processing salary for employee: " + employee.getFullName());
//        }
//    }

    public void generateReport() {
        System.out.println("Transaction Log:");
        if (transactionLog.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            for (String log : transactionLog) {
                System.out.println(log);
            }
        }
    }

    public FinanceManager findFinanceManagerByName(String name) {
        for (FinanceManager manager : workers) {
            if (manager.getFullName().equalsIgnoreCase(name)) {
                return manager;
            }
        }
        System.out.println("Finance Manager not found!");
        return null;
    }

    public void addFinanceManager(FinanceManager manager) {
        workers.add(manager);
        transactionLog.add("Added finance manager: " + manager.getFullName());
    }

    public double getStudentBalance(Student student) {
        return studentBalances.getOrDefault(student, 0.0);
    }

    public double getEmployeeSalary(Employee employee) {
        return employeeSalaries.getOrDefault(employee, 0.0);
    }
}
