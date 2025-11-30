import Login.*;
import Logic.*;
import Model.*;
import Menu.Menu;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== TIMETABLE SYSTEM LOGIN ===");
        System.out.print("Enter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Password: ");
        String pw = sc.nextLine();

        LoginManager login = new LoginManager();
        User user = login.authenticateUser(id, pw);

        if (user == null) {
            System.out.println("Invalid login.");
            return;
        }

        if (user.getRole().equalsIgnoreCase("student")) {
            Student student = (Student) user;
            System.out.println("Student Group: " + student.getGroupId());
        }

        List<Room> rooms = FileHandler.loadRooms("resources/rooms.csv");
        List<CourseModule> courseModules = FileHandler.loadCourseModules("resources/course_modules.csv");
        List<ModuleHours> moduleHours = FileHandler.loadModuleHours("resources/module_hours.csv");
        List<TeachingAssignment> teachingAssignments = FileHandler.loadTeachingAssignments("resources/teaching_assignments.csv");
        List<StudentGroup> studentGroups = FileHandler.loadStudentGroups("resources/student_groups.csv");
        List<ModuleEnrollment> enrollments = FileHandler.loadEnrollments("resources/module_enrollment.csv");

        TimetableManager scheduler = new TimetableManager(
                courseModules,
                moduleHours,
                teachingAssignments,
                enrollments,
                studentGroups,
                rooms
        );


        List<TimetableSlot> generatedTimetable = scheduler.makeTimetable();

        FileHandler.saveTimetable(generatedTimetable, "resources/timetable.csv");

        List<TimetableSlot> timetable = generatedTimetable;

        TimetableData timetableData = new TimetableData(timetable);

        Menu menu = new Menu(user, timetableData);
        menu.showMenu();
    }
}