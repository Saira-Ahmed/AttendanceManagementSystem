import java.util.List;
import java.util.Scanner;

public class Teacher extends User {
    public Teacher(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayMenu(Scanner scanner, Attendance attendance, List<User> users) {
        while (true) {
            System.out.println("\nTeacher Menu:");
            System.out.println("1. Take Attendance");
            System.out.println("2. View Attendance Report");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Is Present (true/false): ");
                    boolean present = scanner.nextBoolean();
                    attendance.addAttendance(subject, studentId, present);
                    System.out.println("Attendance recorded successfully.");
                    break;
                case 2:
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
