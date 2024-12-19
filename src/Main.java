import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static Attendance attendance = new Attendance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeUsers();

        while (true) {
            System.out.println("\n=== Welcome to Attendance Management System ===");
            System.out.println("1. Login");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Exiting program...");
                break;
            } else if (choice == 1) {
                System.out.print("Enter User ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                User user = authenticate(id, password);
                if (user != null) {
                    user.displayMenu(scanner, attendance, users);
                } else {
                    System.out.println("Invalid ID or Password. Please try again.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static User authenticate(String id, String password) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void initializeUsers() {
        users.add(new Admin("admin1", "Admin One", "adminpass"));
        users.add(new Teacher("teacher1", "Teacher One", "teacherpass"));
        users.add(new Student("student1", "Student One", "studentpass"));
    }
}
