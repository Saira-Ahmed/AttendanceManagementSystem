public class AttendanceRecord {
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
}
