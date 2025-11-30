package Logic;

import Model.Room;
import Model.TimetableSlot;
import java.util.*;

/**
 * Manages room availability and suitability information for timetable generation
 *
 * This class provides two main features
 * 1. Filtering rooms based on required type and capacity
 * 2. Checking if a specific room is free for a chosen timetable slot
 */

public class RoomData{

    /** A list of all the rooms loaded from the system. */
    private List<Room> rooms;

    /** A list of all timetable slots currently assigned*/
    private List<TimetableSlot> slots;

    /**
     * Constructs a RoomData object with the rooms and schedules slots.
     *
     * @param rooms the list of all rooms stored in the system
     * @param slots the list of  all timetable slots currently assigned
     */
    public RoomData(List<Room> rooms, List<TimetableSlot> slots){
        this.rooms = rooms;
        this.slots = slots;
    }

    /**
     * Retrieves all rooms that match the specified type and have at least the given capacity.
     *
     * @param roomType the required room type (e.g. "teaching", "CSlab")
     * @param minCapacity the minimum number of seats needed
     *
     * @return a list of rooms that match the type and have sufficient capacity
     */
    public List<Room> getSuitableRooms(String roomType, int minCapacity) {
        List<Room> suitableRooms = new ArrayList<>();
        for(Room r : rooms){
            if(r.getType().equalsIgnoreCase(roomType) && r.getCapacity() >= minCapacity) {
                suitableRooms.add(r);
            }
        }
        return suitableRooms;
    }

    /**
     * Checks whether a given room is available for a specified timetable slot.
     *
     *A room is considered unavailable if:
     * 1. A scheduled TimetableSlot already uses the same room ID
     * 2. That scheduled slot has the same slot ID (e.g., "A1", "B3")
     *
     * @param roomId the ID of the room (e.g., "CS3004b")
     * @param slot the slot ID representing the day/time block (e.g., "C2")
     *
     * @return true if the room is free at that time; otherwise false
     */
    public boolean isRoomFree(String roomId, String slot){
        for(TimetableSlot ts : slots){
            if(ts.getRoomId().equals(roomId) && ts.getSlot().equals(slot)){
                return false;
            }
        }
        return true;
    }
}