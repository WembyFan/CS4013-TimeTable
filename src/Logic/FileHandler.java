package Logic;

import Model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

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

    public static List<TimetableSlot> loadTimetable(String filename) {
        List<TimetableSlot> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String header = br.readLine();

            boolean TFormat = header != null && header.contains("Day") && header.contains("Time");

            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");

                if (TFormat && p.length >= 8) {
                    list.add(new TimetableSlot(
                            p[0].trim(),
                            p[1].trim(),
                            p[2].trim(),
                            p[3].trim(),
                            p[4].trim(),
                            p[5].trim()
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("No existing timetable found or error loading: " + e.getMessage());
        }

        return list;
    }

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
