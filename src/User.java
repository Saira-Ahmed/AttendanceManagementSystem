import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements Serializable {
    private String id;
    private String name;
    private String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public abstract void displayMenu(Scanner scanner, Attendance attendance, java.util.List<User> users);
}
