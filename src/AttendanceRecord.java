import java.io.Serializable;

public class AttendanceRecord implements Serializable {
    private String studentId;
    private boolean isPresent;

    public AttendanceRecord(String studentId, boolean isPresent) {
        this.studentId = studentId;
        this.isPresent = isPresent;
    }

    public String getStudentId() {
        return studentId;
    }

    public boolean isPresent() {
        return isPresent;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Present: " + isPresent;
    }
}
