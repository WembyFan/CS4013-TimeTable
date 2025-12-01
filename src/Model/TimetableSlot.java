package Model;

/**
 * Represents a single timeslot in the timetable system.
 * This class contains all details about a scheduled class including timing,
 * module, group, lecturer, and room assignment.
 *
 */
public class TimetableSlot {

    /**
     * Constructs a new TimetableSlot with all necessary details.
     *
     * @param slot the slot identifier (e.g., A1-E9)
     * @param day the day of the week for this slot
     * @param time the time period for this slot
     * @param moduleCode the module being taught in this slot
     * @param groupId the student group attending this class
     * @param classType the type of class (Lecture, Tutorial, Lab)
     * @param lecturer the lecturer teaching this class
     * @param roomId the room where this class takes place
     */
    private String slot;
    private String day;
    private String time;
    private String moduleCode;
    private String groupId;
    private String classType;
    private String lecturer;
    private String roomId;

    public TimetableSlot(String moduleCode, String classType, String groupId, String lecturer, String roomId, String slot) {
        this.moduleCode = moduleCode;
        this.classType = classType;
        this.groupId = groupId;
        this.lecturer = lecturer;
        this.roomId = roomId;
        this.slot = slot;

    }
    /**
     *  @return slot id for timetable *
     */
    public String getSlot(){
        return slot;
    }

    public void setSlot(String slot){
        this.slot = slot;
    }

    /**
     * @return day
     */
    public String getDay(){
        return day;
    }

    public void setDay(String day){
        this.day = day;
    }

    /** @return time **/
    public String getTime(){
        return time;
    }

    /**
     * Updates the time for this slot
     *
     * @param time the new time to set
     */
    public void setTime(String time){
        this.time = time;
    }

    /** @return module code **/
    public String getModuleCode(){
        return moduleCode;
    }

    public void setModuleCode(String moduleCode){
        this.moduleCode = moduleCode;
    }

    /**
     * @return group id
     */
    public String getGroupId(){
        return groupId;
    }

    /**
     * Updates the group ID for this slot
     *
     * @param groupId the new group ID to set
     */
    public void setGroupId(String groupId){
        this.groupId = groupId;
    }

    /**
     * @return the class type for this slot
     */
    public String getClassType(){
        return classType;
    }

    /**
     * Updates the class type for this slot
     *
     * @param classType the new class type to set
     */
    public void setClassType(String classType){
        this.classType = classType;
    }

    /**
     * @return the lecturer
     */
    public String getLecturer(){
        return lecturer;
    }

    /**
     * Updates the lecturer for this slot
     *
     * @param lecturer the new lecturer to set
     */
    public void setLecturer(String lecturer){
        this.lecturer = lecturer;
    }

    /**
     * @return the room ID for this slot
     */
    public String getRoomId(){
        return roomId;
    }

    /**
     * Updates the room ID for this slot
     *
     * @param roomId the new room ID to set
     */
    public void setRoomId(String roomId){
        this.roomId = roomId;
    }


/**
 * Converts a slot identifier to its corresponding day of the week.
 * The slot format should follow the pattern: [A-E][1-9]
 *
 * @return the full day name corresponding to the slot
 */

    public String getDayFromSlot() {
        if (slot == null || slot.isEmpty()) return "";
        char dayChar = slot.charAt(0);
        if (dayChar == 'A') {
            return "Monday";
        } else if (dayChar == 'B') {
            return "Tuesday";
        } else if (dayChar == 'C') {
            return "Wednesday";
        } else if (dayChar == 'D') {
            return "Thursday";
        } else if (dayChar == 'E') {
            return "Friday";
        } else {
            return "";
        }
    }

/**
 * Converts a slot identifier to its corresponding time range.
 * The slot format should follow the pattern: [A-E][1-9]
 *
 * @return the time range
 * */

    public String getTimeFromSlot() {
        if (slot == null || slot.length() < 2) return "";
        String timeSlot = slot.substring(1);
        // Map slot numbers to time ranges
        if (timeSlot.equals("1")) {
            return "09:00-10:00";
        } else if (timeSlot.equals("2")) {
            return "10:00-11:00";
        } else if (timeSlot.equals("3")) {
            return "11:00-12:00";
        } else if (timeSlot.equals("4")) {
            return "12:00-13:00";
        } else if (timeSlot.equals("5")) {
            return "13:00-14:00";
        } else if (timeSlot.equals("6")) {
            return "14:00-15:00";
        } else if (timeSlot.equals("7")) {
            return "15:00-16:00";
        } else if (timeSlot.equals("8")) {
            return "16:00-17:00";
        } else if (timeSlot.equals("9")) {
            return "17:00-18:00";
        } else {
            return "";
        }
    }
}