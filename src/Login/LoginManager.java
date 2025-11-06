package Login;

import java.io.*;

/**
 * Handles user login and authentication for the Timetabling System.
 * Reads user data from a CSV file and returns a User object if credentials are valid.
 */
public class LoginManager {

    /** The path to the CSV file containing all user information. */
    private static final String USER_FILE = "resources/users.csv";

    /**
     * Allows a user to log in by checking their numeric ID and password.
     *
     * @param userId   the numeric ID entered by the user
     * @param password the password entered by the user
     * @return a User object if successful, or null if invalid
     */
    public User authenticate(int userId, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length < 4) continue;

                // Read and clean up the data from each line
                int id = Integer.parseInt(columns[0].trim());
                String name = columns[1].trim();
                String role = columns[2].trim().toLowerCase();
                String pass = columns[3].trim();

                // Compare numeric IDs and password
                if (id == userId && pass.equals(password.trim())) {
                    switch (role) {
                        case "student":
                            return new Student(id, name, pass);
                        case "lecturer":
                            return new Lecturer(id, name, pass);
                        case "admin":
                            return new Admin(id, name, pass);
                        default:
                            System.err.println("Invalid role: " + role);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID format in CSV file: " + e.getMessage());
        }

        return null;
    }
}