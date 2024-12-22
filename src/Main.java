import java.io.*;
import java.util.*;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static final Attendance attendance = new Attendance();
    private static final String USERS_FILE = "users.ser";

    public static void main(String[] args) {
        loadUsersFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n=== Attendance Management System ===");
                System.out.println("1. Add User (Admin/Teacher/Student)");
                System.out.println("2. Login");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addUser(scanner);
                        break;
                    case 2:
                        login(scanner);
                        break;
                    case 0:
                        saveUsersToFile();
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    default:
                        throw new InvalidInputException("Invalid option. Please choose 0, 1, or 2.");
                }
            } catch (InvalidInputException | NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addUser(Scanner scanner) throws InvalidInputException {
        System.out.println("\n--- Add User ---");
        System.out.println("1. Admin");
        System.out.println("2. Teacher");
        System.out.println("3. Student");
        System.out.print("Choose user type: ");

        int userType = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user;
        switch (userType) {
            case 1:
                user = new Admin(id, name, password);
                break;
            case 2:
                user = new Teacher(id, name, password);
                break;
            case 3:
                user = new Student(id, name, password);
                break;
            default:
                throw new InvalidInputException("Invalid user type. Choose between 1, 2, or 3.");
        }
        users.add(user);
        System.out.println(user.getClass().getSimpleName() + " added successfully!");
    }

    private static void login(Scanner scanner) throws InvalidInputException {
        System.out.print("\nEnter User ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = authenticate(id, password);
        if (user != null) {
            user.displayMenu(scanner, attendance, users);
        } else {
            throw new InvalidInputException("Invalid ID or Password.");
        }
    }

    private static User authenticate(String id, String password) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("User data file not found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading user data: " + e.getMessage());
        }
    }

    private static void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
        }
    }
}
