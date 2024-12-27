import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student extends User {
    private static final long serialVersionUID = 1L;

    public Student(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayMenu() {
        JFrame studentFrame = new JFrame("Student Menu");
        studentFrame.setSize(300, 200);
        studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        studentFrame.setLayout(new GridLayout(3, 1));

        JButton btnViewAttendance = new JButton("View Attendance");
        JButton btnViewMarks = new JButton("View Marks");
        JButton btnLogout = new JButton("Logout");

        studentFrame.add(btnViewAttendance);
        studentFrame.add(btnViewMarks);
        studentFrame.add(btnLogout);

        studentFrame.setVisible(true);

        btnViewAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course course = Main.courses.get(0); // Get the first course for simplicity
                Boolean attendance = course.getAttendance(getId());
                String message = "Attendance: " + (attendance != null ? (attendance ? "Present" : "Absent") : "No Record");
                JOptionPane.showMessageDialog(studentFrame, message);
            }
        });

        btnViewMarks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course course = Main.courses.get(0); // Get the first course for simplicity
                Integer marks = course.getMarks(getId());
                String message = "Marks: " + (marks != null ? marks : "No Marks");
                JOptionPane.showMessageDialog(studentFrame, message);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentFrame.dispose();
            }
        });
    }
}