package transcript;



import java.util.*;
import java.io.Serializable;



public class TranscriptHelper implements Serializable{

    private Date date = new Date();


    private String courseCode;


    private int credits;


    private String courseName;


    private int totalGrade;


    private double digitalGrade;


    private String grade;


    public static double generateDigitalGrade(int credits, int totalGrade) {
        return 0;
    }
    
    public static String generateGrade(int totalGrade) {
        return null;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(int totalGrade) {
		this.totalGrade = totalGrade;
	}

	public double getDigitalGrade() {
		return digitalGrade;
	}

	public void setDigitalGrade(double digitalGrade) {
		this.digitalGrade = digitalGrade;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseCode == null) ? 0 : courseCode.hashCode());
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + credits;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(digitalGrade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + totalGrade;
		return result;
	}

	
	

	@Override
	public String toString() {
		return "TranscriptHelper [date=" + date + ", courseCode=" + courseCode + ", credits=" + credits
				+ ", courseName=" + courseName + ", totalGrade=" + totalGrade + ", digitalGrade=" + digitalGrade
				+ ", grade=" + grade + "]";
	}

	public TranscriptHelper(Date date, String courseCode, int credits, String courseName, int totalGrade,
			double digitalGrade, String grade) {
		super();
		this.date = date;
		this.courseCode = courseCode;
		this.credits = credits;
		this.courseName = courseName;
		this.totalGrade = totalGrade;
		this.digitalGrade = digitalGrade;
		this.grade = grade;
	}
    
}
