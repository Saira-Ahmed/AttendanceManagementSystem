import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Teacher extends User {
    private static final long serialVersionUID = 1L;

    public Teacher(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayMenu() {
        JFrame teacherFrame = new JFrame("Teacher Menu");
        teacherFrame.setSize(300, 300);
        teacherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        teacherFrame.setLayout(new GridLayout(5, 1));

        JButton btnMarkAttendance = new JButton("Mark Attendance");
        JButton btnGenerateReport = new JButton("Generate Course-wise Attendance Report");
        JButton btnLogout = new JButton("Logout");

        teacherFrame.add(btnMarkAttendance);
        teacherFrame.add(btnGenerateReport);
        teacherFrame.add(btnLogout);

        teacherFrame.setVisible(true);

        btnMarkAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseId = JOptionPane.showInputDialog("Enter Course ID:");
                Course course = Main.findCourseById(courseId);

                if (course != null) {
                    for (Student student : course.getStudents()) {
                        String[] options = {"Present", "Absent"};
                        int selection = JOptionPane.showOptionDialog(
                                teacherFrame,
                                "Is student " + student.getName() + " (ID: " + student.getId() + ") present?",
                                "Mark Attendance",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]
                        );

                        boolean present = selection == 0; // 0 is "Present", 1 is "Absent"
                        course.markAttendance(student.getId(), present);
                    }
                    JOptionPane.showMessageDialog(teacherFrame, "Attendance marked successfully for all students in the course!");
                } else {
                    JOptionPane.showMessageDialog(teacherFrame, "Course ID not found!");
                }
            }
        });


        btnGenerateReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseId = JOptionPane.showInputDialog("Enter Course ID for attendance report:");
                Course course = Main.findCourseById(courseId);

                if (course != null) {
                    StringBuilder report = new StringBuilder("Attendance Report for Course: " + course.getName() + "\n");
                    for (Student student : course.getStudents()) {
                        Boolean attendance = course.getAttendance(student.getId());
                        report.append("Student ID: ").append(student.getId()).append(", Name: ").append(student.getName())
                                .append(", Attendance: ").append(attendance != null ? (attendance ? "Present" : "Absent") : "No Record").append("\n");
                    }
                    JOptionPane.showMessageDialog(teacherFrame, report.toString());
                } else {
                    JOptionPane.showMessageDialog(teacherFrame, "Course ID not found!");
                }
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teacherFrame.dispose();
            }
        });
    }
}