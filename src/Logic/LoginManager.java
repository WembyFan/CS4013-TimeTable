package Logic;

import java.io.*;
import java.util.*;

import Login.*;

/**
 * The LoginManager class handles user authentication by reading user credentials
 * from a CSV file and creating appropriate user objects based on their roles.
 *
 * <p>This class supports three types of users: Students, Lecturers, and Administrators,
 * each with different attributes and capabilities within the system.</p>
 *
 * <p>The user data is expected to be stored in a CSV file with the following format:</p>
 * <ul>
 *   <li><strong>All users:</strong> userId, userName, userRole, password</li>
 *   <li><strong>Students:</strong> userId, userName, "student", password, course, year, group</li>
 *   <li><strong>Lecturers:</strong> userId, userName, "lecturer", password, modules</li>
 *   <li><strong>Admins:</strong> userId, userName, "admin", password</li>
 * </ul>
 */
public class LoginManager {

    /**
     * The file path to the CSV file containing user data.
     */
    private static final String USER_FILE = "resources/users.csv";

    /**
     * Authenticates a user based on the provided user ID and password.
     *
     * <p>This method reads the user data file, searches for a matching user ID and password,
     * and returns an appropriate User object if authentication is successful. The method
     * creates specific user types (Student, Lecturer, or Admin) based on the role specified
     * in the user data file.</p>
     *
     * <p><strong>User Data Format Expectations:</strong></p>
     * <ul>
     *   <li><strong>Student:</strong> userId, name, "student", password, course, year, group</li>
     *   <li><strong>Lecturer:</strong> userId, name, "lecturer", password, modules</li>
     *   <li><strong>Admin:</strong> userId, name, "admin", password</li>
     * </ul>
     *
     * @param userId the unique identifier for the user attempting to authenticate
     * @param password the password provided by the user for authentication
     * @return a User object (Student, Lecturer, or Admin) if authentication is successful,
     *         null if authentication fails or if an error occurs during the process
     *
     * @throws SecurityException if there are issues reading the user data file
     */

    public User authenticateUser(int userId, String password) {
        try {BufferedReader br = new BufferedReader(new FileReader(USER_FILE));
            // Skip header line
            br.readLine();
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] userFields = currentLine.split(",");

                //Skip invalid lines with insufficient fields
                if (userFields.length < 4) {
                    continue;
                }


                int userIdFromFile = Integer.parseInt(userFields[0].trim());
                String userName = userFields[1].trim();
                String userRole = userFields[2].trim().toLowerCase();
                String userPassword = userFields[3].trim();

                if (userIdFromFile == userId && userPassword.equals(password.trim())) {
                    if (userRole.equals("student")) {
                        String studentCourse = "Unknown";
                        int studentYear = 0;
                        String studentGroup = "N/A";
                        if (userFields.length >= 7) {
                            studentCourse = userFields[4].trim();
                            studentYear = Integer.parseInt(userFields[5].trim());
                            studentGroup = userFields[6].trim();
                        }
                        return new Student(userIdFromFile, userName, userPassword,
                                studentCourse, studentYear, studentGroup);
                    }
                    else if (userRole.equals("lecturer")) {
                        List<String> lecturerModules = new ArrayList<>();

                        if (userFields.length >= 5) {
                            String modulesString = userFields[4].trim();
                            if (!modulesString.isEmpty()) {
                                lecturerModules = Arrays.asList(modulesString.split(","));
                            }
                        }

                        return new Lecturer(userIdFromFile, userName, userPassword, lecturerModules);
                    }
                    else if (userRole.equals("admin")) {
                        return new Admin(userIdFromFile, userName, userPassword);
                    }
                    else{
                        System.out.println("Invalid user role");
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in user data: " + e.getMessage());
        }
        return null;
    }
}