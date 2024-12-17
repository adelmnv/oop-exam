package grading;

public class MarkTest {

    public static void main(String[] args) {
        Mark mark = new Mark();
        mark.setFirstAttestation(29);
        mark.setSecondAttestation(30.0);
        mark.setFinalExam(25);

        System.out.println("First Attestation: " + mark.getFirstAttestation()); 
        System.out.println("Second Attestation: " + mark.getSecondAttestation()); 
        System.out.println("Final Exam: " + mark.getFinalExam()); 

        // Тест для расчета общей оценки
        double totalMark = mark.getTotalMark();
        System.out.println("Total Mark: " + totalMark); 

        // Тест для получения GPA (цифровой)
        double gpaNumeric = mark.getGpaNumeric();
        System.out.println("GPA Numeric: " + gpaNumeric); 

        // Тест для получения GPA (буквенный)
        String gpaLetter = mark.getGpaLetter();
        System.out.println("GPA Letter: " + gpaLetter); 
    }
}
