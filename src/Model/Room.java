package Model;

public class Room {
    private String roomId;
    private String roomType;
    private int capacity;

    public Room(String roomId, String roomType, int capacity) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.capacity = capacity;
    }

    public String toCSV() {
        return roomId + "," + roomType + "," + capacity;
    }

    @Override
    public String toString() {
        return String.format("Room ID: %s, Type: %s, Capacity: %d",
                roomId, roomType, capacity);
    }

    // Getters and setters
    public String getRoomId() { return roomId; }
    public String getRoomType() { return roomType; }
    public int getCapacity() { return capacity; }

    public void setRoomId(String roomId) { this.roomId = roomId; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}