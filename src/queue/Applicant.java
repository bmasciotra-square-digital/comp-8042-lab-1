package queue;

public class Applicant {
    int applicantNumber;
    float mark;

    public Applicant(int applicantNumber, int mark) {
        this.applicantNumber = applicantNumber;
        this.mark = mark;
    }

    public String toString() {
        return "Applicant " + applicantNumber + " got a mark of " + mark;
    }

    private void validateMark(float mark) throws IllegalArgumentException {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Mark must be between 0 and 100");
        }
    }

    public void setMark(float mark) {
        validateMark(mark);
        this.mark = mark;
    }
}
