package Logic;

import Model.Room;

import java.io.*;
import java.util.*;

/**
 * Provides utility methods for managing room data in a CSV file.
 * This class handles reading, writing, and manipulating room information
 * stored in a CSV format.
 */
public class RoomManager {
    private static final String ROOMS_CSV = "resources/rooms.csv";

    /**
     * Retrieves all rooms from the CSV file.
     *
     * @return a List of Room objects containing all rooms from the CSV file
     * @throws IOException if there's an error reading the file
     * @throws NumberFormatException if the capacity field contains invalid data
     */
    public static List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ROOMS_CSV))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                // Skip header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Parse CSV line
                String[] data = line.split(",");
                if (data.length == 3) {
                    String roomId = data[0].trim();
                    String roomType = data[1].trim();
                    int capacity = Integer.parseInt(data[2].trim());

                    rooms.add(new Room(roomId, roomType, capacity));
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading rooms from CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing room capacity: " + e.getMessage());
        }

        return rooms;
    }

    /**
     * Finds a room by its ID.
     *
     * @param roomId the ID of the room to search for
     * @return the Room object if found, null otherwise
     */
    public static Room getRoom(String roomId) {
        List<Room> rooms = getAllRooms();
        for (Room room : rooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Adds a new room to the CSV file.
     *
     * @param roomId   the unique identifier for the new room
     * @param roomType the type of the new room
     * @param capacity the capacity of the new room
     * @throws IOException if there's an error writing to the file
     */
    public static void addRoom(String roomId, String roomType, int capacity) {
        // Check if room already exists
        if (getRoom(roomId) != null) {
            System.out.println("Room ID " + roomId + " already exists!");
            return;
        }

        Room newRoom = new Room(roomId, roomType, capacity);

        try (FileWriter fw = new FileWriter(ROOMS_CSV, true);
             BufferedWriter writer = new BufferedWriter(fw)) {

            writer.newLine();
            writer.write(newRoom.toCSV());
            System.out.println("Room added successfully!");

        } catch (IOException e) {
            System.err.println("Error adding room to CSV file: " + e.getMessage());
        }
    }

    /**
     * Updates an existing room's information.
     *
     * @param roomId the ID of the room to update
     * @param newRoomType the new room type
     * @param newCapacity the new room capacity
     * @return true if the room was updated successfully, false if the room was not found
     */
    public static boolean editRoom(String roomId, String newRoomType, int newCapacity) {
        Room room = getRoom(roomId);
        if (room == null) {
            System.out.println("Room " + roomId + " not found.");
            return false;
        }

        // Update room information
        room.setRoomType(newRoomType);
        room.setCapacity(newCapacity);

        // Update all rooms in CSV
        List<Room> allRooms = getAllRooms();
        for (int i = 0; i < allRooms.size(); i++) {
            if (allRooms.get(i).getRoomId().equals(roomId)) {
                allRooms.set(i, room);
                break;
            }
        }

        writeAllRoomsToCSV(allRooms);
        System.out.println("Room updated successfully!");
        return true;
    }

    /**
     * Removes a room from the CSV file.
     *
     * @param roomId the ID of the room to delete
     * @return true if the room was deleted successfully, false if the room was not found
     */
    public static boolean deleteRoom(String roomId) {
        List<Room> allRooms = getAllRooms();
        boolean found = false;

        // Find and remove the room
        for (int i = 0; i < allRooms.size(); i++) {
            if (allRooms.get(i).getRoomId().equals(roomId)) {
                allRooms.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            writeAllRoomsToCSV(allRooms);
            System.out.println("Room " + roomId + " deleted successfully!");
            return true;
        } else {
            System.out.println("Room " + roomId + " not found.");
            return false;
        }
    }

    /**
     * Writes all rooms to the CSV file.
     * This is a helper method used by editRoom and deleteRoom.
     *
     * @param rooms the list of Room objects to write to the CSV file
     * @throws IOException if there's an error writing to the file
     */
    private static void writeAllRoomsToCSV(List<Room> rooms) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROOMS_CSV))) {
            // Write header
            writer.write("room_id,room_type,room_capacity");

            // Write all rooms
            for (Room room : rooms) {
                writer.newLine();
                writer.write(room.toCSV());
            }

        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
