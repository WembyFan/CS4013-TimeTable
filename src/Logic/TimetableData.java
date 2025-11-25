package Logic;

import Model.TimetableSlot;
import java.util.ArrayList;
import java.util.List;

public class TimetableData {
    private List<TimetableSlot> slots;

    public TimetableData(List<TimetableSlot> slots) {
        this.slots = slots;
        System.out.println("TimetableData initialised with " + (slots != null ? slots.size() : 0) + " slots");
    }

    public List<TimetableSlot> getTimetableForGroup(String groupId) {
        List<TimetableSlot> result = new ArrayList<>();
        if (slots != null) {
            System.out.println("Warning: Slots list is null in getTimetableForGroup");
            return result;
        }
        System.out.println("Searching for group: " + groupId + " in " + slots.size() + " total slots");

        int matchCount = 0;
        for (TimetableSlot slot : slots) {
            if (slot.getGroupId() != null) {
                if (slot.getGroupId().equalsIgnoreCase(groupId)) {
                    result.add(slot);
                    matchCount++;
                }
            }
        }
        System.out.println("Found " + matchCount + " matches in " + slots.size() + " slots");

        if (matchCount > 0) {
            System.out.println("First few matching slots:");
            for (int i = 0; i < Math.min(3, result.size()); i++) {
                TimetableSlot slot = result.get(i);
                System.out.println("  - " + slot.getModuleCode() + " " + slot.getClassType() + " in " + slot.getRoomId());

            }
        }
        return result;
    }

    public List<TimetableSlot> getTimetableForLecturer(String lecturerId) {
        List<TimetableSlot> result = new ArrayList<>();
        if (slots == null) {
            System.out.println("Warning: Slots list is null in getTimetableForLecturer");
            return result;
        }

        for (TimetableSlot slot : slots) {
            if (slot.getLecturer() != null && slot.getLecturer().equalsIgnoreCase(lecturerId)) {
                result.add(slot);
            }
        }
        System.out.println("Found " + result.size() + " slots for lecturer: " + lecturerId);
        return result;
    }

    public List<TimetableSlot> getTimetableForRoom(String roomId) {
        List<TimetableSlot> result = new ArrayList<>();

        for (TimetableSlot slot : slots) {
            if (slot.getRoomId() != null && slot.getRoomId().equalsIgnoreCase(roomId)) {
                result.add(slot);
            }
        }
        System.out.println("Found " + result.size() + " slots for room: " + roomId);
        return result;
    }

    public List<TimetableSlot> getAllSlots() {
        if (slots == null) {
            System.out.println("Warning: Slots list is null in getAllSlots");
            return new ArrayList<>();
        }
        return slots;
    }
}
