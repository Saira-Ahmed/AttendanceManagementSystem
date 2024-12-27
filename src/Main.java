

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    public static Map<String, User> users = new HashMap<>();
    public static List<Course> courses = new ArrayList<>();

    private static final String USERS_FILE = "users.ser";
    private static final String COURSES_FILE = "courses.ser";

    public static void main(String[] args) {
        loadUsersFromFile();
        loadCoursesFromFile();
        initializeUsers();
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Attendance Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblRole = new JLabel("Login as:");
        String[] roles = {"Admin", "Teacher", "Student"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        JLabel lblId = new JLabel("User ID:");
        JTextField txtId = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");
        JButton btnExit = new JButton("Exit");

        panel.add(lblRole);
        panel.add(roleComboBox);
        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnExit);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleComboBox.getSelectedItem();
                String id = txtId.getText();
                String password = new String(txtPassword.getPassword());
                User user = users.get(id);

                if (user != null && user.getPassword().equals(password)) {
                    if (selectedRole.equals("Admin") && user instanceof Admin) {
                        user.displayMenu();
                    } else if (selectedRole.equals("Teacher") && user instanceof Teacher) {
                        user.displayMenu();
                    } else if (selectedRole.equals("Student") && user instanceof Student) {
                        user.displayMenu();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid role selected.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid ID or Password");
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveUsersToFile();
                saveCoursesToFile();
                System.exit(0);
            }
        });
    }

    private static void initializeUsers() {
        if (users.isEmpty()) {
            users.put("admin", new Admin("admin", "Admin", "admin123"));
        }
    }

    private static void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (Map<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous users data found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadCoursesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSES_FILE))) {
            courses = (List<Course>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous courses data found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveCoursesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSES_FILE))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}