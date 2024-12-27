import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String name;
    private final List<Student> students;
    private final Map<String, Boolean> attendance;
    private final Map<String, Integer> marks;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
        this.attendance = new HashMap<>();
        this.marks = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void markAttendance(String studentId, boolean isPresent) {
        attendance.put(studentId, isPresent);
    }

    public void assignMarks(String studentId, int mark) {
        marks.put(studentId, mark);
    }

    public Boolean getAttendance(String studentId) {
        return attendance.get(studentId);
    }

    public Integer getMarks(String studentId) {
        return marks.get(studentId);
    }
}