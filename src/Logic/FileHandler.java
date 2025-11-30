package Logic;

import Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The FIleHandler class provides utility methods for reading and writing
 * various data entities to and from CSV files. It handles the serialization
 * and deserialization of course modules,student groups, rooms, module hours,
 * teaching assignments, module enrollments, and timetable slots.
 *
 * This class follows a static method pattern and is designed to be used without
 * instantiation . All methods handle their own file I/O exceptions and provide
 * error messages to the console.
 *
 * <p>This class follows a static method pattern and is designed to be used
 * without instantiation. All methods handle their own file I/O exceptions
 * and provide error messages to the console.</p>
 *
 */

public class FileHandler {

    /**
     * Loads course modules from a CSV file.
     *
     * <p>Expected CSV format (with header):<br>
     * {@code column1,column2,column3,column4}<br>
     * {@code moduleCode,semester,credits,moduleName}</p>
     *
     * @param filename the path to the CSV file containing course modules
     * @return a list of CourseModule objects loaded from the file, or
     *         an empty list if an error occurs
     *
     * @see CourseModule
     */

    public static List<CourseModule> loadCourseModules(String filename) {
        List<CourseModule> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 4) {
                    list.add(new CourseModule(
                            p[0].trim(),
                            Integer.parseInt(p[1].trim()),
                            Integer.parseInt(p[2].trim()),
                            p[3].trim()
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading course modules: " + e.getMessage());
        }

        return list;
    }

    /**
     * Loads student groups from a CSV file.
     *
     * <p>Expected CSV format (with header):<br>
     * {@code column1,column2,column3,column4}<br>
     * {@code groupId,year,courseCode,studentCount}</p>
     *
     * @param filename the path to the CSV file containing student groups
     * @return a list of StudentGroup objects loaded from the file, or
     *         an empty list if an error occurs
     *
     * @see StudentGroup
     */

    public static List<StudentGroup> loadStudentGroups(String filename) {
        List<StudentGroup> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 4) {
                    list.add(new StudentGroup(
                            p[0].trim(),
                            Integer.parseInt(p[1].trim()),
                            p[2].trim(),
                            Integer.parseInt(p[3].trim())
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading student groups: " + e.getMessage());
        }

        return list;
    }

    /**
     * Loads Room from a CSV file.
     *
     * <p>Expected CSV format (with header):<br>
     * {@code column1,column2,column3}<br>
     * {@code roomId,roomType,capacity}</p>
     *
     * @param filename the path to the CSV file containing room information
     * @return a list of Room objects loaded from the file, or
     *         an empty list if an error occurs
     *
     * @see Room
     */

    public static List<Room> loadRooms(String filename) {
        List<Room> rooms = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 3) {
                    rooms.add(new Room(
                            p[0].trim(),
                            p[1].trim(),
                            Integer.parseInt(p[2].trim())
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading rooms: " + e.getMessage());
        }

        return rooms;
    }

    /**
     * Loads Module Hours from a CSV file.
     *
     * <p>Expected CSV format (with header):<br>
     * {@code column1,column2,column3,column4}<br>
     * {@code moduleCode,lectureHours,labHours,tutorialHours}</p>
     *
     * @param filename the path to the CSV file containing module hours
     * @return a list of ModuleHours objects loaded from the file, or
     *         an empty list if an error occurs
     *
     * @see ModuleHours
     */

    public static List<ModuleHours> loadModuleHours(String filename) {
        List<ModuleHours> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 4) {
                    list.add(new ModuleHours(
                            p[0].trim(),
                            Integer.parseInt(p[1].trim()),
                            Integer.parseInt(p[2].trim()),
                            Integer.parseInt(p[3].trim())
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading module hours: " + e.getMessage());
        }

        return list;
    }

    /**
     * Loads teaching assignments from a CSV file.
     *
     * <p>Expected CSV format (with header):<br>
     * {@code column1,column2,column3,column4,column5}<br>
     * {@code lecturerId,moduleCode,semester,classType,group}</p>
     *
     * @param filename the path to the CSV file containing teaching assignments
     * @return a list of TeachingAssignment objects loaded from the file, or
     *         an empty list if an error occurs
     *
     * @see TeachingAssignment
     */

    public static List<TeachingAssignment> loadTeachingAssignments(String filename) {
        List<TeachingAssignment> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 5) {
                    list.add(new TeachingAssignment(
                            p[0].trim(),
                            p[1].trim(),
                            Integer.parseInt(p[2].trim()),
                            p[3].trim(),
                            p[4].trim()
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading teaching assignments: " + e.getMessage());
        }

        return list;
    }

    /**
     * Loads module enrollment from a CSV file.
     *
     * <p>Expected CSV format (with header):<br>
     * {@code column1,column2}<br>
     * {@code moduleCode,enrolledStudents}</p>
     *
     * @param filename the path to the CSV file containing module enrollments
     * @return a list of ModuleEnrollment objects loaded from the file, or
     *         an empty list if an error occurs
     *
     * @see ModuleEnrollment
     */

    public static List<ModuleEnrollment> loadEnrollments(String filename) {
        List<ModuleEnrollment> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 2) {
                    list.add(new ModuleEnrollment(
                            p[0].trim(),
                            Integer.parseInt(p[1].trim())
                    ));
                }
            }

        } catch (Exception e) {
            System.out.println("Error loading enrollments: " + e.getMessage());
        }

        return list;
    }

    /**
     * Saves timetable slots to a CSV file with enhanced formatting.
     *
     * <p>The output CSV format includes:<br>
     * {@code Module,Type,Group,Lecturer,Room,Slot,Day,Time}</p>
     *
     * <p>This method automatically converts slot codes (e.g., "A1") to
     * human-readable day and time formats.</p>
     *
     * @param slots the List of TimetableSlot objects to save
     * @param filename the path where the CSV file will be created
     * @see TimetableSlot
     * @see #convertSlotToDay(String)
     * @see #convertSlotToTime(String)
     */


    public static void saveTimetable(List<TimetableSlot> slots, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {

            pw.println("Module,Type,Group,Lecturer,Room,Slot,Day,Time");

            for (TimetableSlot s : slots) {
                String day = convertSlotToDay(s.getSlot());
                String time = convertSlotToTime(s.getSlot());

                pw.println(
                        s.getModuleCode() + "," +
                                s.getClassType() + "," +
                                s.getGroupId() + "," +
                                s.getLecturer() + "," +
                                s.getRoomId() + "," +
                                s.getSlot() + "," +
                                day + "," +
                                time
                );
            }
            System.out.println("Timetable saved to " + filename);

        } catch (Exception e) {
            System.out.println("Error saving timetable: " + e.getMessage());
        }
    }

    /**
     * Converts a slot code to its corresponding day of the week.
     *
     * <p>Slot code mapping:<br>
     * - 'A' → "Monday"<br>
     * - 'B' → "Tuesday"<br>
     * - 'C' → "Wednesday"<br>
     * - 'D' → "Thursday"<br>
     * - Any other character → "Friday"</p>
     *
     * @param slot the slot code (e.g., "A1", "B2")
     * @return the corresponding day name, or empty string if slot is invalid
     */

    private static String convertSlotToDay(String slot) {
        if (slot == null || slot.length() < 1) {
            return "";
        }

        char day = slot.charAt(0);

        if (day == 'A') {
            return "Monday";
        }
        else if (day == 'B') {
            return "Tuesday";
        }
        else if (day == 'C') {
            return "Wednesday";
        }
        else if (day == 'D') {
            return "Thursday";
        }
        else{
            return "Friday";
        }
    }

    /**
     * Converts a slot code to its corresponding time range.
     *
     * <p>Slot number mapping:<br>
     * - "1" → "9:00-10:00"<br>
     * - "2" → "10:00-11:00"<br>
     * - "3" → "11:00-12:00"<br>
     * - "4" → "12:00-13:00"<br>
     * - "5" → "13:00-14:00"<br>
     * - "6" → "14:00-15:00"<br>
     * - "7" → "15:00-16:00"<br>
     * - "8" → "16:00-17:00"<br>
     * - "9" → "17:00-18:00"<br>
     * - Any other number → "Unknown"</p>
     *
     * @param slot the slot code (e.g., "A1", "B2")
     * @return the corresponding time range, or empty string if slot is invalid
     */

    private static String convertSlotToTime(String slot) {
        if (slot == null || slot.length() < 2) {
            return "";
        }
        String num = slot.substring(1);

        if (num.equals("1")) {
            return "9:00-10:00";
        }
        else if (num.equals("2")) {
            return "10:00-11:00";
        }
        else if (num.equals("3")) {
            return "11:00-12:00";
        }
        else if (num.equals("4")) {
            return "12:00-13:00";
        }
        else if (num.equals("5")) {
            return "13:00-14:00";
        }
        else if (num.equals("6")) {
            return "14:00-15:00";
        }
        else if (num.equals("7")) {
            return "15:00-16:00";
        }
        else if (num.equals("8")) {
            return "16:00-17:00";
        }
        else if (num.equals("9")) {
            return "17:00-18:00";
        }
        else{
            return "Unknown";
        }
    }

}
