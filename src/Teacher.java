import java.util.List;
import java.util.Scanner;

public class Teacher extends User {
    public Teacher(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayMenu(Scanner scanner, Attendance attendance, List<User> users) {
        while (true) {
            System.out.println("\n--- Teacher Menu ---");
            System.out.println("1. Add Attendance");
            System.out.println("2. View Attendance");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Present? (true/false): ");
                    boolean isPresent = Boolean.parseBoolean(scanner.nextLine());
                    attendance.addAttendance(subject, studentId, isPresent);
                    System.out.println("Attendance added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Subject: ");
                    String subjectToView = scanner.nextLine();
                    List<AttendanceRecord> records = attendance.getAttendance(subjectToView);
                    System.out.println("\n--- Attendance Records for " + subjectToView + " ---");
                    for (AttendanceRecord record : records) {
                        System.out.println(record);
                    }
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
