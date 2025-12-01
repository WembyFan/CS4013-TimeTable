package Logic;

import Model.TimetableSlot;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages timetable data and provides methods to retrieve timetable information
 * for different entities (groups, lecturers, rooms).
 * This class serves as the main data access layer for timetable operations.
 *
 */
public class TimetableData {
    /**
     * Constructs a TimetableData object with the provided list of timetable slots.
     *
     * @param slots the list of TimetableSlot objects to initialize the data with
     */

    private List<TimetableSlot> slots;

    public TimetableData(List<TimetableSlot> slots) {
        this.slots = slots;
    }

    /**
     * Retrieves the timetable for a specific student group.
     *
     * @param groupId the ID of the student group to search for
     * @return a list of TimetableSlot objects belonging to the specified group
     */
    public List<TimetableSlot> getTimetableForGroup(String groupId) {
        List<TimetableSlot> result = new ArrayList<>();
        if (slots == null) {
            System.out.println("Warning: slots list is null in getTimetableForGroup");
            return result;
        }

        for (TimetableSlot slot : slots) {
            if (slot.getGroupId() != null) {
                if (slot.getGroupId().equalsIgnoreCase(groupId)) {
                    result.add(slot);
                }
                else if (slot.getGroupId().startsWith("ALL_")) {
                    String sharedGroupId = slot.getGroupId();
                    String[] parts = sharedGroupId.split("_");
                    if (parts.length >= 3) {
                        String course = parts[1];
                        int year = Integer.parseInt(parts[2].substring(1)); // Remove "Y" prefix

                        if (isGroupInCourseYear(groupId, course, year)) {
                            result.add(slot);
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean isGroupInCourseYear(String groupId, String course, int year) {
        if (groupId == null || groupId.isEmpty()) return false;

        String groupCourse = extractCourseFromGroupId(groupId);
        if (groupCourse == null || !groupCourse.equals(course)) {
            return false; // Different course - no match
        }

        try {
            String yearPart = groupId.replaceAll("[^0-9]", "").substring(0, 1);
            int groupYear = Integer.parseInt(yearPart);
            return groupYear == year;
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return false;
        }
    }

    private String extractCourseFromGroupId(String groupId) {
        if (groupId.startsWith("CS")) return "CS";
        if (groupId.startsWith("MA")) return "MA";
        if (groupId.startsWith("EE")) return "EE";
        if (groupId.startsWith("BI")) return "BI";
        return null;
    }


    /**
     * Retrieves the timetable for a specific lecturer.
     *
     * @param lecturer the ID of the lecturer to search for
     * @return a list of TimetableSlot objects assigned to the specified lecturer
     */
    public List<TimetableSlot> getTimetableForLecturer(String lecturer) {
        List<TimetableSlot> result = new ArrayList<>();
        if (slots == null) {
            System.out.println("Warning: slots list is null in getTimetableForLecturer");
            return result;
        }

        for (TimetableSlot slot : slots) {
            if (slot.getLecturer() != null && slot.getLecturer().equalsIgnoreCase(lecturer)) {
                result.add(slot);
            }
        }
        return result;
    }

    /**
     * Retrieves the timetable for a specific room.
     *
     * @param roomId the ID of the room to search for
     * @return a list of TimetableSlot objects scheduled in the specified room
     */
    public List<TimetableSlot> getTimetableForRoom(String roomId) {
        List<TimetableSlot> result = new ArrayList<>();
        if (slots == null) {
            System.out.println("Warning: slots list is null in getTimetableForRoom");
            return result;
        }

        for (TimetableSlot slot : slots) {
            if (slot.getRoomId() != null && slot.getRoomId().equalsIgnoreCase(roomId)) {
                result.add(slot);
            }
        }
        return result;
    }
    /**
     * Retrieves timetable for specific module code
     *
     * @return a list of module timetable objects
     */
    public List<TimetableSlot> getTimetableForModule(String moduleCode) {
        List<TimetableSlot> result = new ArrayList<>();
        if (slots == null) {
            System.out.println("Warning: slots list is null in getTimetableForModule");
            return result;
        }

        for (TimetableSlot slot : slots) {
            if (slot.getModuleCode() != null && slot.getModuleCode().equalsIgnoreCase(moduleCode)) {
                result.add(slot);
            }
        }
        return result;
    }
    /**
     * Retrieves all timetable slots in the system.
     *
     * @return a list of all TimetableSlot objects
     */
    public List<TimetableSlot> getAllSlots() {
        if (slots == null) {
            System.out.println("Warning: slots list is null in getAllSlots");
            return new ArrayList<>();
        }
        return slots;
    }
}
