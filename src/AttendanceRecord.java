// AttendanceRecord class
class AttendanceRecord {
    private String studentId;
    private String subject;
    private boolean isPresent;

    public AttendanceRecord(String studentId, String subject, boolean isPresent) {
        this.studentId = studentId;
        this.subject = subject;
        this.isPresent = isPresent;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isPresent() {
        return isPresent;
    }
}