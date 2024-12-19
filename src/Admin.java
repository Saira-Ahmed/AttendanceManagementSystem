import java.util.List;
import java.util.Scanner;

// Admin class
class Admin extends User {
    public Admin(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayMenu(Scanner scanner, Attendance attendance, List<User> users) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Register Teacher");
            System.out.println("2. Register Student");
            System.out.println("3. Add Attendance");
            System.out.println("4. View Attendance Report");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Teacher ID: ");
                    String teacherId = scanner.nextLine();
                    System.out.print("Enter Teacher Name: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("Enter Teacher Password: ");
                    String teacherPassword = scanner.nextLine();
                    users.add(new Teacher(teacherId, teacherName, teacherPassword));
                    System.out.println("Teacher registered successfully.");
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter Student Password: ");
                    String studentPassword = scanner.nextLine();
                    users.add(new Student(studentId, studentName, studentPassword));
                    System.out.println("Student registered successfully.");
                    break;
                case 3:
                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    String attStudentId = scanner.nextLine();
                    System.out.print("Is Present (true/false): ");
                    boolean isPresent = scanner.nextBoolean();
                    attendance.addAttendance(subject, attStudentId, isPresent);
                    System.out.println("Attendance recorded successfully.");
                    break;
                case 4:
                    System.out.print("Enter Subject for report: ");
                    String reportSubject = scanner.nextLine();
                    List<AttendanceRecord> records = attendance.getAttendance(reportSubject);
                    System.out.println("\nAttendance Report for " + reportSubject);
                    System.out.println("Student ID\tPresent");
                    for (AttendanceRecord record : records) {
                        System.out.printf("%s\t\t%s\n", record.getStudentId(), record.isPresent() ? "Yes" : "No");
                    }
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
