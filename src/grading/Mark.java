package grading;

import java.io.Serializable;

public class Mark implements Serializable{
	private static final long serialVersionUID = 1L;
	private Double firstAttestation;
    private Double secondAttestation;
    private Double finalExam;

    public Mark() {
        this.firstAttestation = null;
        this.secondAttestation = null;
        this.finalExam = null;
    }

    public double getFirstAttestation() {
        return firstAttestation == null ? 0 : firstAttestation;
    }

    public void setFirstAttestation(double firstAttestation) {
        this.firstAttestation = firstAttestation;
    }

    public double getSecondAttestation() {
        return secondAttestation == null ? 0 : secondAttestation;
    }

    public void setSecondAttestation(double secondAttestation) {
        this.secondAttestation = secondAttestation;
    }

    public double getFinalExam() {
        return finalExam == null ? 0 : finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    public double getTotalMark() {
        return (firstAttestation == null ? 0 : firstAttestation) + (secondAttestation == null ? 0 : secondAttestation)
                + (finalExam == null ? 0 : finalExam);
    }

    public double getGpaNumeric() {
        double totalMark = getTotalMark();
        if (totalMark >= 95)
            return 4.0;
        if (totalMark >= 90)
            return 3.67;
        if (totalMark >= 85)
            return 3.33;
        if (totalMark >= 80)
            return 3.0;
        if (totalMark >= 75)
            return 2.67;
        if (totalMark >= 70)
            return 2.33;
        if (totalMark >= 65)
            return 2.0;
        if (totalMark >= 60)
            return 1.67;
        if (totalMark >= 55)
            return 1.33;
        if (totalMark >= 50)
            return 1.0;
        return 0.0;
    }

    public String getGpaLetter() {
        double totalMark = getTotalMark();
        if (totalMark >= 95)
            return "A+";
        if (totalMark >= 90)
            return "A";
        if (totalMark >= 85)
            return "A-";
        if (totalMark >= 80)
            return "B+";
        if (totalMark >= 75)
            return "B";
        if (totalMark >= 70)
            return "B-";
        if (totalMark >= 65)
            return "C+";
        if (totalMark >= 60)
            return "C";
        if (totalMark >= 55)
            return "C-";
        if (totalMark >= 50)
            return "D";
        return "F";
    }
}
