import Login.*;
import Logic.*;
import Model.*;
import Menu.Menu;

import java.util.List;
import java.util.Scanner;

/**
 * Main entry point for the University Timetabling System.
 * Handles user authentication, data loading, timetable generation, and menu navigation.
 * Provides a complete workflow from login to timetable access for students, lecturers, and administrators.
 */
public class Main {

    /**
     * Starts the timetabling system application.
     * <p>Workflow:
     * <ol>
     *   <li>Authenticates user credentials</li>
     *   <li>Loads all university data from CSV files</li>
     *   <li>Generates a complete timetable</li>
     *   <li>Presents role-specific menu options</li>
     * </ol>
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== TIMETABLE SYSTEM LOGIN ===");
        System.out.print("Enter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Password: ");
        String pw = sc.nextLine();

        // Authenticate user
        LoginManager login = new LoginManager();
        User user = login.authenticateUser(id, pw);

        if (user == null) {
            System.out.println("Invalid login.");
            return;
        }

        // Display student group information for student users
        if (user.getRole().equalsIgnoreCase("student")) {
            Student student = (Student) user;
            System.out.println("Student Group: " + student.getGroupId());
        }

        // Load all university data from CSV files
        List<Room> rooms = FileHandler.loadRooms("resources/rooms.csv");
        List<CourseModule> courseModules = FileHandler.loadCourseModules("resources/course_modules.csv");
        List<ModuleHours> moduleHours = FileHandler.loadModuleHours("resources/module_hours.csv");
        List<TeachingAssignment> teachingAssignments = FileHandler.loadTeachingAssignments("resources/teaching_assignments.csv");
        List<StudentGroup> studentGroups = FileHandler.loadStudentGroups("resources/student_groups.csv");
        List<ModuleEnrollment> enrollments = FileHandler.loadEnrollments("resources/module_enrollment.csv");

        // Generate timetable using loaded data
        TimetableManager scheduler = new TimetableManager(
                courseModules,
                moduleHours,
                teachingAssignments,
                enrollments,
                studentGroups,
                rooms
        );

        List<TimetableSlot> generatedTimetable = scheduler.makeTimetable();

        // Save generated timetable to file
        FileHandler.saveTimetable(generatedTimetable, "resources/timetable.csv");

        List<TimetableSlot> timetable = generatedTimetable;

        // Initialize timetable data for menu system
        TimetableData timetableData = new TimetableData(timetable);

        // Launch appropriate menu based on user role
        Menu menu = new Menu(user, timetableData);
        menu.showMenu();
    }
}