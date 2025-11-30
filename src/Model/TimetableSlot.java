package Model;

public class TimetableSlot {


    /**
     *
     *
     *
     *
     *
     *
     *
     *
     * **/

    private String slot;
    private String day;
    private String time;
    private String moduleCode;
    private String groupId;
    private String classType;
    private String lecturer;
    private String roomId;

    public TimetableSlot(String slot, String day, String time, String moduleCode, String groupId, String classType, String lecturer, String roomId) {
        this.moduleCode = moduleCode;
        this.classType = classType;
        this.groupId = groupId;
        this.lecturer = lecturer;
        this.roomId = roomId;
        this.slot = slot;
    }
    /** @return slot id for timetable **/
    public String getSlot(){
        return slot;
    }

    public void setSlot(String slot){
        this.slot = slot;
    }

    /** @return day **/
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

    /** @return group id **/
    public String getGroupId(){
        return groupId;
    }

    public void setGroupId(String groupId){
        this.groupId = groupId;
    }

    public String getClassType(){
        return classType;
    }

    public void setClassType(String classType){
        this.classType = classType;
    }

    public String getLecturer(){
        return lecturer;
    }

    public void setLecturer(String lecturer){
        this.lecturer = lecturer;
    }

    public String getRoomId(){
        return roomId;
    }

    public void setRoomId(String roomId){
        this.roomId = roomId;
    }

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