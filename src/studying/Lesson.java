package studying;

import java.io.Serializable;

import enums.LessonType;
import users.Teacher;

public class Lesson implements Serializable{
	private static final long serialVersionUID = 1L;
	private String room;
	public String dayOfWeek;
	private String time;
	private LessonType lessonType;
	private Teacher instructor;
	
	public Lesson(String room, String dayOfWeek, String time, LessonType lessonType, Teacher instructor) {
		this.room = room;
		this.dayOfWeek = dayOfWeek;
		this.time = time;
		this.lessonType = lessonType;
		this.instructor = instructor;
	}
	
	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public LessonType getLessonType() {
		return lessonType;
	}
	
	public void setLessonType(LessonType lessonType) {
		this.lessonType = lessonType;
	}
	
	public Teacher getInstructor() {
		return instructor;
	}
	
	public void setInstructor(Teacher instructor) {
		this.instructor = instructor;
	}
	
	public String getLessonDetails() {
		StringBuilder details = new StringBuilder();
	    details.append("Room: ").append(room != null ? room : "Not assigned").append("\n");
	    details.append("Day of week: ").append(dayOfWeek != null ? dayOfWeek : "Not assigned").append("\n");
	    details.append("Time: ").append(time != null ? time : "Not assigned").append("\n");
	    details.append("Lesson Type: ").append(lessonType != null ? lessonType.toString() : "Not assigned").append("\n");
	    details.append("Instructor: ").append(instructor != null ? instructor.getFullName() : "Not assigned").append("\n");
	    return details.toString();
	}
	
	@Override
	public String toString() {
		return getLessonDetails();
	}
	
}
