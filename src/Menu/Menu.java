package Menu;
 /* Menu class handles user interface interactions
  * Provides different menus based on users input **/

import Logic.TimetableData;
import Login.User;
import Login.Student;
import Model.TimetableSlot;

import java.util.List;
import java.util.Scanner;

public class Menu {

    /* Constructors initialised */

    /* @param user the currently logged-in user **/

    private User user;
    private TimetableData timetableData;


    public Menu(User user, TimetableData timetableData) {
        this.user = user;
        this.timetableData = timetableData;
    }

    /**
     * This menu will log the user in based on whether its staff, admin or student
     **/
    public void showMenu() {
        /*
        Ensure input is changed to lower case to avoid errors
         */
        String role = user.getRole().toLowerCase();

        if (role.equals("admin")) {
            showAdminMenu();
        } else if (role.equals("lecturer")) {
            showLecturerMenu();
        } else if (role.equals("student")) {
            showStudentMenu();
        } else {
            System.out.println("Invalid role.");
        }
    }

    private void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean exit = false;

        System.out.println("\n --- ADMIN MENU ---");
        System.out.println("\nHi  " + user.getName() + " (" + user.getUserId() + ")");

        System.out.println("1. View Timetable");
        System.out.println("2. View Timetable For Student Group");
        System.out.println("3. View Room Timetable");
        System.out.println("4. View Lecturer Timetable");
        System.out.println("5. Modify Timetable Slot.");
        System.out.println("6. Logout");

        choice = scanner.nextInt();
        /* While loop until user exits **/
        while (!exit) {
            if (choice == 1) {
                System.out.println("Generating timetable...");

            } else if (choice == 2) {
                System.out.println("Enter group ID: ");
                String g = scanner.nextLine();
                printSlots(timetableData.getTimetableForGroup(g));

            } else if (choice == 3) {
                System.out.println("Enter room ID: ");
                String r = scanner.nextLine();
                printSlots(timetableData.getTimetableForRoom(r));

            } else if (choice == 4) {
                System.out.println("Enter lecturer ID: ");
                String lec = scanner.nextLine();
                printSlots(timetableData.getTimetableForLecturer(lec));

            } else if (choice == 5) {
                modifyTimetableSlot();

            } else if (choice == 6) {
                exit = true;
            }
        }
    }

    private void showLecturerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("\n --- LECTURER MENU ---");
        System.out.println("\n 1. View Timetable");
        System.out.println("2. Logout");

        int choice = scanner.nextInt();

        while (!exit) {
            if (choice == 1) {
                printSlots(timetableData.getTimetableForLecturer(user.getName()));
            } else {
                exit = true;
            }
        }
    }

    private void showStudentMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("\n --- STUDENT MENU ---");
        System.out.println("\n 1. View Timetable");
        System.out.println("2. Logout");

        int choice = scanner.nextInt();

        while (!exit) {
            if (choice == 1) {
                Student s = (Student) user;
                printSlots(timetableData.getTimetableForGroup(s.getGroupId()));
            } else {
                exit = true;
            }
        }
    }

    private void modifyTimetableSlot() {
        Scanner scanner = new Scanner(System.in);
        List<TimetableSlot> slots = timetableData.getAllSlots();

        if (slots.isEmpty()) {
            System.out.println("Slot is empty.");
            return;
        }

        System.out.println("\n --- MODIFY TIME TABLE ---");

        for (int i = 0; i < slots.size(); i++) {
            TimetableSlot s = slots.get(i);
            System.out.println((i + 1) + ". "
                    + s.getModuleCode() + "(" + s.getClassType() + ") "
                    + "Group: " + s.getGroupId()
                    + " | Slot: " + s.getSlot()
                    + " | Room: " + s.getRoomId()
                    + " | Lecturer: " + s.getLecturer());
        }

        System.out.println("\nChoose slot number to modify: ");
        int index = scanner.nextInt();

        if (index < 1 || index > slots.size()) {
            System.out.println("Invalid slot number.");
            return;
        }

        TimetableSlot slot = slots.get(index - 1);

        System.out.println("\nWhat would you like to modify?");
        System.out.println("1. Change Slot (A1-E9)");
        System.out.println("2. Change Room");
        System.out.println("3. Change Lecturer");
        System.out.println("4. Change Group");
        System.out.println("5. Cancel");

        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Enter slot number (A1-E9): ");
            String newSlot = scanner.nextLine().toUpperCase();

            if (newSlot.matches("[A-E][1-9]")) {
                slot.setSlot(newSlot);
            } else {
                System.out.println("Invalid slot.");
                return;
            }
        } else if (choice == 2) {
            System.out.println("Enter new room: ");
            slot.setRoomId(scanner.nextLine());

        } else if (choice == 3) {
            System.out.println("Enter new lecturer: ");
            slot.setLecturer(scanner.nextLine());

        } else if (choice == 4) {
            System.out.println("Enter new group: ");
            slot.setGroupId(scanner.nextLine());

        } else {
            System.out.println("No changes made.");
            return;
        }

        fileHandler.saveTimetable(slots, "resources/timetable.csv");
        System.out.println("No timetable entries found.");
    }

    private void printSlots(List<TimetableSlot> slots) {
        if (slots == null || slots.isEmpty()) {
            System.out.println("No timetable entries found.");
            return;
        }

        System.out.println("\n--- TIMETABLE ---");

        for (TimetableSlot s : slots) {
            System.out.println(
                    "Slot: " + s.getSlot() +
                            " | " + s.getModuleCode() +
                            " (" + s.getClassType() + ")" +
                            " | Group: " + s.getGroupId() +
                            " | Room: " + s.getRoomId() +
                            " | Lecturer: " + s.getLecturer()
            );
        }
    }
}

