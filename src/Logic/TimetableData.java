package Logic;

import Model.TimetableSlot;
import java.util.ArrayList;
import java.util.List;

public class TimetableData {

    private List<TimetableSlot> slots;

    public TimetableData(List<TimetableSlot> slots) {
        this.slots = slots;
    }

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

    public List<TimetableSlot> getAllSlots() {
        if (slots == null) {
            System.out.println("Warning: slots list is null in getAllSlots");
            return new ArrayList<>();
        }
        return slots;
    }
}
