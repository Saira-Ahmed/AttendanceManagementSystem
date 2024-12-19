import java.util.*;

// Attendance class
class Attendance {
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    public void addAttendance(String subject, String studentId, boolean isPresent) {
        AttendanceRecord record = new AttendanceRecord(studentId, subject, isPresent);
        attendanceRecords.add(record);
    }

    public List<AttendanceRecord> getAttendance(String subject) {
        List<AttendanceRecord> records = new ArrayList<>();
        for (AttendanceRecord record : attendanceRecords) {
            if (record.getSubject().equals(subject)) {
                records.add(record);
            }
        }
        return records;
    }
}