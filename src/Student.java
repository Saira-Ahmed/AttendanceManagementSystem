import java.util.List;
import java.util.Scanner;

public class Student extends User {
    public Student(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayMenu(Scanner scanner, Attendance attendance, List<User> users) {
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View Attendance");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();
                    List<AttendanceRecord> records = attendance.getAttendance(subject);
                    System.out.println("\nAttendance for " + subject);
                    for (AttendanceRecord record : records) {
                        if (record.getStudentId().equals(this.getId())) {
                            System.out.printf("Present: %s\n", record.isPresent() ? "Yes" : "No");
                        }
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
