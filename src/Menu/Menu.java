package Menu;
 /** Menu class handles user interface interactions
  * Provides different menus based on users input **/

import Login.User;
import Logic.TimetableManger;
import Model.RoomManager;
import java.util.Scanner;

public class Menu {

    /**
     * Constructors initialised
     **/
    private Scanner scanner;
    private TimetableManager timetableManager


    public class Menu() {
    this.scanner =new

        Scanner(System.in);
    this.timetableManager =new

        TimetableManager();
    }

    /**
     * @param user the currently logged in user
     *             This menu will log the user in based on whether its staff, admin or student
     **/
    public void showMenu(User user) {
        boolean exit = false;

        /** While loop until user exits **/
        while (!exit) {

            System.out.println("\nMAIN MENU");
            System.out.println("\nHi  " + user.getName() + " ("user.getUserId() + ")");

            if (user.getRole().equals("Admin")) {
                System.out.println("1. View Student Timetable");
                System.out.println("2. View Staff Timetable");
                System.out.println("3. Modify Timetable");
                System.out.println("4. Manage Rooms");
            }

            if (user.getRole().equals("Student")) {
                System.out.println("1. View Student Timetable");
            }

            if (user.getRole().equals("Staff")) {
                System.out.println("2. View Staff Timetable");
            }

            System.out.println("0. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            /* Timetable only viewable by students and admin **/
            if (choice == 1) {
                System.out.println("\n STUDENT TIMETABLE FOR " + user.getName() + " (" + user.getUserId() + ").");
                timetableManager.viewStudentTimetable(Student);
            }

            /* Staff timetable, only viewable by staff and admin **/
            if (choice == 2) {
                System.out.println("\n STAFF TIMETABLE FOR, " + user.getName() + " (" + user.getUserId() + ").");
                timetableManager.viewStaffTimetable(Staff);
            }

            /* Admin only screen, add/remove lectures/labs/tutorials for students and staff timetables **/
            if (choice == 3) {
                System.out.println("\n LOGGED IN ADMIN: " + user.getName() + " (" + user.getUserId() + ").");
                System.out.println("\n--- MODIFY TIMETABLE ---");
                timetableManager.viewModiyTimetable(Admin);
            }

            /* Admin only screen, assign/remove lectures, labs, tutorials to/from different rooms **/
            if (choice == 4) {
                System.out.println("\n LOGGED IN ADMIN: " + user.getName() + " (" + user.getUserId() + ").");
                System.out.println("\n --- ROOM MANAGER ---");
                timetableManager.viewRoomManager(Admin);


                /** LOG OUT FEATURE **/
                if (choice == 0) {
                    exit = true;

                    System.out.println("Logged Out.");

                }
            }
        }

        private void manageRoomsMenu() {
            boolean back = false;

            /* Room management menu */
            while (!back) {
                System.out.println("\n=== ROOM MANAGEMENT ===");
                System.out.println("1. View All Rooms");
                System.out.println("2. Add Room");
                System.out.println("3. Edit Room");
                System.out.println("4. Delete Room");
                System.out.println("0. Back to Main Menu");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                /* All rooms */
                if (choice == 1) {
                    RoomManager.getAllRooms().forEach(System.out::println);
                }

                /* Add room menu */
                if (choice == 2) {
                    System.out.println("Enter room ID: ");
                    String roomId = scanner.nextLine();
                    System.out.println("Enter room type (Classroom/lab)");

                    String roomType = scanner.nextLine();
                    System.out.println("Enter capacity: ");
                    int capacity = scanner.nextInt();
                    RoomManager.addRoom(roomId, roomType, capacity);
                }

                /* Room edit */
                if (choice == 3) {
                    System.out.print("Enter Room ID: ");
                    String editRoomId = scanner.nextLine();
                    System.out.print("Enter New Room Type: ");
                    String newType = scanner.nextLine();
                    System.out.print("Enter New Capacity: ");
                    int newCapacity = scanner.nextInt();
                    scanner.nextLine();
                    RoomManager.editRoom(editRoomId, newType, newCapacity);
                }

                if (choice == 4) {
                    System.out.print("Enter Room ID to delete: ");
                    String deleteRoomId = scanner.nextLine();
                    System.out.print("/nAre you sure you want to delete room " + deleteRoomId + "?");
                    System.out.print("\n1 = YES. 2 = NO");
                    int question = scanner.nextInt();

                    if (question == 1) {
                        System.out.println("Room " + deleteRoomId + " has been successfully deleted.");
                        RoomManager.deleteRoom(deleteRoomId);
                    } else if (question == 2) {
                        System.out.print("Room: " + deleteRoomId + " has NOT been deleted.");
                    }
                }

                if (choice == 0) {
                    System.out.print("Returning to main menu...");
                    back = true;
                }
            }
        }

        private void manageTimetableMenu() {
            boolean back = false;

            while (!back) {
                System.out.println("\n--- TIMETABLE MANAGEMENT ---");
                System.out.println("1. Add Timetable Slot");
                System.out.println("2. Remove Timetable Slot");
                System.out.println("3. Update Timetable Slot");
                System.out.println("0. Back to Main Menu");
                System.out.print("Choose an option: ");
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Add Timetable Slot");
            }

            if (choice == 2) {
                System.out.println("Remove Timetable Slot");
            }

            if (choice == 3) {
                System.out.print("Update Timetable Slot");
            }

            if (choice == 0) {
                System.out.print("Returning to main menu...");
                back = true;
            }
        }
    }
}