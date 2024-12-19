import java.util.List;
import java.util.Scanner;

// Student class
class Student extends User {
    public Student(String id, String name, String password) {
        super(id, name, password);
    }

    public void studentMenu(Scanner scanner, Attendance attendance) {
        while (true) {
            System.out.println("Student Menu:");
            System.out.println("1. View Attendance");
            System.out.println("0. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();
                    List<AttendanceRecord> records = attendance.getAttendance(subject);
                    System.out.println("\nAttendance for " + subject);
                    for (AttendanceRecord record : records) {
                        if (record.getStudentId().equals(this.id)) {
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