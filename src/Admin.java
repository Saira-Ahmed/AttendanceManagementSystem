import java.util.Scanner;
import java.util.List;

public class Admin extends User {
    public Admin(String id, String name, String password) {
        super(id, name, password);
    }

    @Override
    public void displayMenu(Scanner scanner, Attendance attendance, List<User> users) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Users");
            System.out.println("2. Add Attendance");
            System.out.println("0. Logout");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("\n--- All Users ---");
                    for (User user : users) {
                        System.out.println(user.getId() + " - " + user.getClass().getSimpleName());
                    }
                    break;
                case 2:
                    System.out.print("Enter Subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Present? (true/false): ");
                    boolean isPresent = Boolean.parseBoolean(scanner.nextLine());
                    attendance.addAttendance(subject, studentId, isPresent);
                    System.out.println("Attendance added successfully!");
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
