package grading;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GradeBook implements Serializable{
	private static final long serialVersionUID = 1L;
	private Mark mark;
	private List<Date> attendanceList = new ArrayList<>();
	
	public GradeBook() {
		this.mark = new Mark();
	}
	
	public GradeBook(Mark mark) {
        this.mark = mark;
    }
	
	public Mark getMark() {
		return mark;
	}
	
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
	public List<Date> getAttendanceList() {
		return attendanceList;
	}
	
	public void setAttendanceList(List<Date> attendanceList) {
		this.attendanceList = attendanceList;
	}

    public void addAttendance(Date date) {
        attendanceList.add(date);
    }
    
    public void setFirstAttestationMark(double firstAttestation) {
		mark.setFirstAttestation(firstAttestation);
	}
	
	public void setSecondAttestationMark(double secondAttestation) {
		mark.setSecondAttestation(secondAttestation);
	}
	
	public void setFinalExamMark(double finalExam) {
		mark.setFinalExam(finalExam);
	}
	
	public Double getFirstAttestationMark() {
		return mark.getFirstAttestation();
	}
	
	public Double getSecondAttestationMark() {
		return mark.getSecondAttestation();
	}
	
	public Double getFinalExamMark() {
		return mark.getFinalExam();
	}

    public double getTotalMark() {
        return mark.getTotalMark();
    }

    public double getGpaNumeric() {
        return mark.getGpaNumeric();
    }

    public String getGpaLetter() {
        return mark.getGpaLetter();
    }

    public int getAttendanceCount() {
        return attendanceList.size();
    }

    public String getGradeDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Total Mark: ").append(getTotalMark()).append("\n");
        details.append("GPA (Numeric): ").append(getGpaNumeric()).append("\n");
        details.append("GPA (Letter): ").append(getGpaLetter()).append("\n");
        details.append("Attendance Count: ").append(getAttendanceCount()).append("\n");
        return details.toString();
    }
}
