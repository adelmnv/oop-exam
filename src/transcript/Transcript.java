package transcript;


import java.io.Serializable;
import java.util.Vector;


public class Transcript implements Serializable{

    private String studentName;


    private String studentId;


    private int studentCourse;
    

    private String studentFaculty;


    private String studentSpecialization;


    private Vector<TranscriptHelper> listOfGrades = new Vector<TranscriptHelper>();

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(int studentCourse) {
		this.studentCourse = studentCourse;
	}

	public String getStudentFaculty() {
		return studentFaculty;
	}

	public void setStudentFaculty(String studentFaculty) {
		this.studentFaculty = studentFaculty;
	}

	public String getStudentSpecialization() {
		return studentSpecialization;
	}

	public void setStudentSpecialization(String studentSpecialization) {
		this.studentSpecialization = studentSpecialization;
	}

	public Vector<TranscriptHelper> getListOfGrades() {
		return listOfGrades;
	}

	public void setListOfGrades(Vector<TranscriptHelper> listOfGrades) {
		this.listOfGrades = listOfGrades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listOfGrades == null) ? 0 : listOfGrades.hashCode());
		result = prime * result + studentCourse;
		result = prime * result + ((studentFaculty == null) ? 0 : studentFaculty.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		result = prime * result + ((studentSpecialization == null) ? 0 : studentSpecialization.hashCode());
		return result;
	}

	

	@Override
	public String toString() {
		return "Transcript [studentName=" + studentName + ", studentId=" + studentId + ", studentCourse="
				+ studentCourse + ", studentFaculty=" + studentFaculty + ", studentSpecialization="
				+ studentSpecialization + ", listOfGrades=" + listOfGrades + "]";
	}

	public Transcript(String studentName, String studentId, int studentCourse, String studentFaculty,
			String studentSpecialization, Vector<TranscriptHelper> listOfGrades) {
		super();
		this.studentName = studentName;
		this.studentId = studentId;
		this.studentCourse = studentCourse;
		this.studentFaculty = studentFaculty;
		this.studentSpecialization = studentSpecialization;
		this.listOfGrades = listOfGrades;
	}
    
}





