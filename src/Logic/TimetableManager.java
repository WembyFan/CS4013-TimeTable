package Logic;

import Login.User;
import Login.Student;
import Login.Lecturer;
import Model.TimetableSlot;
import java.util.ArrayList;
import java.util.List;
/* Timetable manager handles all timetable related operations.
Acts as controller between menu and TimetableData */

public class TimetableManager {
    private TimetableData timetableData;
    private FileHandler fileHandler;


    /* Constructor initialises TimetableManager with dependencies */
    public TimetableManager() {
        this.timetableData = new TimetableData();
        this.fileHandler = new FileHandler();
        }
/*
 * Displays personal timetable for current user
 * Shows different timetable based on user role (student, staff, admin)
 *
 * @param user the user requesting timetable
 */
        public void viewPersonalTimetable (User user){
            System.out.println("\n --- PERSONAL TIMETABLE ---");
            System.out.println("Name: " + user.getName());

            List<TimetableSlot> slots = new ArrayList<>();

            /* Select correct timetable based on user role
             */
            if (user.getRole().equals("student")) {
                Student student = (Student) user;
                slots = timetableData.getTimetableForGroup(student.getGroupId());
            } else if (user.getRole().equals("lecturer")) {
                Lecturer lecturer = (Lecturer) user;
                slots = timetableData.getTimetableForLecturer(lecturer.getUserId());
            } else if (user.getRole().equals("admin")) {
                slots = timetableData.getAllTimetableSlots();

            }

            if (slots.isEmpty()) {
                System.out.println("No timetable found.");
            } else {
                slots.forEach(System.out::println);
            }
        }

        /** Displays timetable for specific course
         *
         * @param courseCode the program code to view timetable for
         */
        public void viewCourseSchedule (String courseCode){
            System.out.println("\n --- COURSE SCHEDULE: " + courseCode + " ---");
            List<TimetableSlot> slots = timetableData.getTimetableForCourse(courseCode);

            if (slots.isEmpty()) {
                System.out.println("No timetable found.");
            } else {
                slots.forEach(System.out::println);
            }
        }
    }


