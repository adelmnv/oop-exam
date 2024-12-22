package test;
import communication.Complaint;
import enums.Faculty;
import enums.TeacherTitle;
import enums.UrgencyLevel;
import repositories.ComplaintRepository;
import users.Student;
import users.Teacher;

public class ComplaintTest {

    public static void main(String[] args) {
        // Создание учителей и студентов для отправки жалоб
        Teacher teacher1 = new Teacher("Mr.", "Smith", "smith@example.com", "password123",50000,Faculty.BS,TeacherTitle.LECTOR);
        Student student1 = new Student("John", "Doe", "john.doe@example.com", "studentpassword",1,Faculty.KMA,true);
        
        Teacher teacher2 = new Teacher( "Mrs.", "Johnson", "johnson@example.com", "password456",60000,Faculty.ISE,TeacherTitle.TUTOR);
        Student student2 = new Student("Jane", "Doe", "jane.doe@example.com", "studentpassword",1,Faculty.ISE,true);

        // Создание жалоб с разными уровнями срочности
        Complaint complaint1 = new Complaint(teacher1, UrgencyLevel.HIGH, student1);
        Complaint complaint2 = new Complaint(teacher2, UrgencyLevel.LOW, student2);
        
        // Добавление жалоб в репозиторий
        int complaintId1 = ComplaintRepository.getInstance().addComplaint(complaint1);
        int complaintId2 = ComplaintRepository.getInstance().addComplaint(complaint2);

        // Отображение всех жалоб
        System.out.println("Displaying all complaints (including answered and unanswered):");
        for (Complaint complaint : ComplaintRepository.getInstance().getUnansweredComplaints()) {
            System.out.println("Complaint ID: " + complaintId1);
            System.out.println("Sender: " + complaint.getSender().getFullName());
            System.out.println("Urgency Level: " + complaint.getUrgencyLevel());
            System.out.println("Student: " + complaint.getStudent().getFullName());
            System.out.println("Is Answered: " + complaint.isAnswered());
            System.out.println();
        }

        // Ответ на жалобу
        complaint1.setAnswered(true);
        System.out.println("Complaint " + complaintId1 + " is now answered.");

        // Отображение всех непринятых жалоб после изменения
        System.out.println("\nDisplaying unanswered complaints after marking one as answered:");
        for (Complaint complaint : ComplaintRepository.getInstance().getUnansweredComplaints()) {
            System.out.println("Complaint ID: " + complaintId2);
            System.out.println("Sender: " + complaint.getSender().getFullName());
            System.out.println("Urgency Level: " + complaint.getUrgencyLevel());
            System.out.println("Student: " + complaint.getStudent().getFullName());
            System.out.println("Is Answered: " + complaint.isAnswered());
            System.out.println();
        }
    }
}
