import java.io.*;
import java.util.*;

public class Attendance implements Serializable {
    private Map<String, List<AttendanceRecord>> attendanceData = new HashMap<>();
    private static final String FILE_NAME = "attendance.ser";

    public Attendance() {
        // Attempt to load serialized attendance data
        Attendance loadedAttendance = loadAttendanceFromFile();
        if (loadedAttendance != null) {
            this.attendanceData = loadedAttendance.attendanceData;
        }
    }

    public void addAttendance(String subject, String studentId, boolean isPresent) {
        attendanceData.putIfAbsent(subject, new ArrayList<>());
        attendanceData.get(subject).add(new AttendanceRecord(studentId, isPresent));
        saveAttendanceToFile();
    }

    public List<AttendanceRecord> getAttendance(String subject) {
        return attendanceData.getOrDefault(subject, new ArrayList<>());
    }

    private void saveAttendanceToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving attendance to file: " + e.getMessage());
        }
    }

    private Attendance loadAttendanceFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Attendance) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Attendance data file not found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading attendance data: " + e.getMessage());
        }
        return null;
    }
}
