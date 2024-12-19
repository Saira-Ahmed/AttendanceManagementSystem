import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attendance {
    private Map<String, List<AttendanceRecord>> attendanceData = new HashMap<>();

    public void addAttendance(String subject, String studentId, boolean isPresent) {
        attendanceData.putIfAbsent(subject, new ArrayList<>());
        attendanceData.get(subject).add(new AttendanceRecord(studentId, isPresent));
    }

    public List<AttendanceRecord> getAttendance(String subject) {
        return attendanceData.getOrDefault(subject, new ArrayList<>());
    }
}
