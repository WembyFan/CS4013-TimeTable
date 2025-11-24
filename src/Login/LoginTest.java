package Login;

import Logic.LoginManager;

import java.util.Scanner;

public class LoginTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginManager login = new LoginManager();

        System.out.println("---UL Timetable---");
        System.out.print("Enter User ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        User user = login.authenticate(id, pass);

        if (user != null) {
            System.out.println("\nLogin successful!");
            System.out.println("Welcome, " + user.getName());
        } else {
            System.out.println("\nInvalid ID or password. Please try again.");
        }

        sc.close();
    }
}
