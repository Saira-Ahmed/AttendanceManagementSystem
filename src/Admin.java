import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends User {
    private static final long serialVersionUID = 1L;

    public Admin(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayMenu() {
        JFrame adminFrame = new JFrame("Admin Menu");
        adminFrame.setSize(300, 300);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLayout(new GridLayout(5, 1));

        JButton btnRegisterTeacher = new JButton("Register Teacher");
        JButton btnRegisterStudent = new JButton("Register Students in Course");
        JButton btnAddCourse = new JButton("Add Course");
        JButton btnLogout = new JButton("Logout");

        adminFrame.add(btnRegisterTeacher);
        adminFrame.add(btnRegisterStudent);
        adminFrame.add(btnAddCourse);
        adminFrame.add(btnLogout);

        adminFrame.setVisible(true);

        btnRegisterTeacher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerTeacher(adminFrame);
            }
        });

        btnRegisterStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerStudents(adminFrame);
            }
        });

        btnAddCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse(adminFrame);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose();
            }
        });
    }

    private void registerTeacher(JFrame adminFrame) {
        String teacherId = JOptionPane.showInputDialog("Enter Teacher ID:");
        if (teacherId == null || Main.users.containsKey(teacherId)) {
            JOptionPane.showMessageDialog(adminFrame, "Teacher ID must be unique or valid.");
            return; // Cancel pressed or ID already exists
        }

        String teacherName = JOptionPane.showInputDialog("Enter Teacher Name:");
        if (teacherName == null) return; // Cancel pressed

        String teacherPassword = JOptionPane.showInputDialog("Enter Teacher Password:");
        if (teacherPassword == null) return; // Cancel pressed

        Main.users.put(teacherId, new Teacher(teacherId, teacherName, teacherPassword));
        JOptionPane.showMessageDialog(adminFrame, "Teacher registered successfully!");
    }

    private void registerStudents(JFrame adminFrame) {
        String courseId = JOptionPane.showInputDialog("Enter Course ID to register students:");
        Course course = Main.findCourseById(courseId);
        if (course == null) {
            JOptionPane.showMessageDialog(adminFrame, "Course ID not found!");
            return; // Invalid course ID
        }

        String numStudentsStr = JOptionPane.showInputDialog("How many students do you want to register?");
        if (numStudentsStr == null) return; // Cancel pressed
        int numStudents = Integer.parseInt(numStudentsStr);

        for (int i = 0; i < numStudents; i++) {
            String studentId = JOptionPane.showInputDialog("Enter Student ID for student " + (i + 1) + ":");
            if (studentId == null || Main.users.containsKey(studentId)) {
                JOptionPane.showMessageDialog(adminFrame, "Student ID must be unique or valid. Registration for this student canceled.");
                i--; // Decrement to ask again for this student
                continue; // Skip to next iteration
            }

            String studentName = JOptionPane.showInputDialog("Enter Student Name for student " + (i + 1) + ":");
            if (studentName == null) return; // Cancel pressed

            String studentPassword = JOptionPane.showInputDialog("Enter Student Password for student " + (i + 1) + ":");
            if (studentPassword == null) return; // Cancel pressed

            Student student = new Student(studentId, studentName, studentPassword);
            course.addStudent(student);
            Main.users.put(studentId, student);
        }
        JOptionPane.showMessageDialog(adminFrame, "Students registered in course successfully!");
    }

    private void addCourse(JFrame adminFrame) {
        String courseId = JOptionPane.showInputDialog("Enter Course ID:");
        if (courseId == null || Main.findCourseById(courseId) != null) {
            JOptionPane.showMessageDialog(adminFrame, "Course ID must be unique or valid.");
            return; // Cancel pressed or ID already exists
        }

        String courseName = JOptionPane.showInputDialog("Enter Course Name:");
        if (courseName == null) return; // Cancel pressed

        Main.courses.add(new Course(courseId, courseName));
        JOptionPane.showMessageDialog(adminFrame, "Course added successfully!");
    }
}