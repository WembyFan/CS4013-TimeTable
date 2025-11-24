package Logic;

import Model.TimetableSlot;
import java.util.ArrayList;
import java.util.List;

public class TimetableData {
    private List<TimetableSlot> timetableslots;
    private FileHandler fileHandler;

    public TimetableData() {
        this.timetableslots = new ArrayList<>();
        this.fileHandler = new FileHandler();
        loadTimetableData();
    }

    private void loadTimetableData() {
        timetableSlots = fileHandler.readTimetableSlots();

    }

    public List<TimetableSlot> getAllTimetableSlots() {
        return new ArrayList<>(timetableSlots);
    }

    public List<TimetableSlot> getTimetableForGroup(String groupId) {
        List<TimetableSlot> result = new ArrayList<>();

        for (TimetableSlot slot : timetableslots) {
            if (slot.getGroupId().equals(groupId)) {
                result.add(slot);
            }
        }
        return result;
    }

    public List<TimetableSlot> getTimetableForLecturer(int lecturerId) {
        List<TimetableSlot> result = new ArrayList<>();
        String lecturerIdStr = String.valueOf(lecturerId);

        for (TimetableSlot slot : timetableslots) {
            if (slot.getLecturer().equals(lecturerIdStr)){
                result.add(slot);
            }
        }
        return result;
    }


    public List<TimetableSlot> getTimetableForCourse(String courseId) {

        List<TimetableSlot> result = new ArrayList<>();

        for (TimetableSlot slot : timetableslots) {

            if (slot.getGroupId().equals(courseId)) {
                result.add(slot);
            }
        }
        return result;
    }

    public List<TimetableSlot> getTimetableForModule(String moduleCode) {
        List<TimetableSlot> result = new ArrayList<>();

        for (TimetableSlot slot : timetableslots) {

            if (slot.getModuleCode().equals(moduleCode)) {
                result.add(slot);
            }
        }
        return result;
    }

    public List<TimetableSlot> getTimetableForRoom(String roomId) {
        List<TimetableSlot> result = new ArrayList<>();

        for (TimetableSlot slot : timetableslots) {
            if (slot.getRoomId().equals(roomId)) {
                result.add(slot);
            }
        }
        return result;
    }
}
