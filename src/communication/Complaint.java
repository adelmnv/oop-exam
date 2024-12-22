package communication;

import java.io.Serializable;

import enums.UrgencyLevel;
import users.Student;
import users.Teacher;

public class Complaint implements Serializable{
	private static final long serialVersionUID = 1L;
	private final Teacher sender;
	private final UrgencyLevel urgencyLevel;
	private final Student student; 
	private boolean isAnswered;
	
	public Complaint(Teacher sender, UrgencyLevel urgencyLevel, Student student) {
		this.sender = sender;
		this.urgencyLevel = urgencyLevel;
		this.student = student;
		this.isAnswered = false;
		
	}

	public Teacher getSender() {
		return sender;
	}

	public UrgencyLevel getUrgencyLevel() {
		return urgencyLevel;
	}

	public Student getStudent() {
		return student;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	
	@Override
	public String toString() {
	    String answerStatus = isAnswered ? "Answered" : "Unanswered";
	    return String.format("Complaint [Sender: %s, Student: %s, Urgency Level: %s, Status: %s]",
	            sender.getFirstName() + " " + sender.getLastName(),
	            student.getFirstName() + " " + student.getLastName(),
	            urgencyLevel,
	            answerStatus);
	}

	
}
