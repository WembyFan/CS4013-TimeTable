package Model;

/**
 * Represents a physical room in the timetable system.
 * A room has:
 * 1. A unique ID (e.g., "CSG001")
 * 2. A room type (e.g., "teaching", "CSlab")
 * 3. A maximum capacity (number of seats)
 * This class is used for verifying suitable rooms for classes and detecting timetable clashes
 */

public class Room{
    /**The unique identifier of the room*/
    private String roomId;
    /**The type of room*/
    private String type;
    /**THe maximum seating capacity of the room*/
    private int capacity;

    /**
     *Creates a new Room object with the given attributes.
     *
     * @param roomId the unique ID of the room
     * @param type the type of room
     * @param capacity the maximum number of occupants the room can hold
     */
    public Room(String roomId, String type, int capacity) {
        this.roomId = roomId;
        this.type = type;
        this.capacity = capacity;
    }

    /**
     * Returns the unique ID of the room
     * @return the room ID
     */
    public String getRoomId() {
        return roomId;
    }


    /**
     * Returns the type of room
     * @return the room type
     */
    public String getType() {
        return type;
    }

    /**
     * Updates the type of the room
     * @param type the room type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the maximum seating capacity of the room.
     * @return the room capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Updates the room's capacity
     * @param capacity the room capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns a readable string describing the room
     * @return a string representation of the room
     */
    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", type=" + type + ", capacity=" + capacity + '}';
    }
}