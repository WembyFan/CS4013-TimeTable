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

    private String slotId;
    private String day;
    private String time;
    private String moduleCode;
    private String groupId;
    private String classType;
    private String lecturer;
    private String roomId;

    public TimetableSlot(String slotId, String day, String time, String moduleCode, String groupId, String classType, String lecturer) {
        this.slotId = slotId;
        this.day = day;
        this.time = time;
        this.moduleCode = moduleCode;
        this.groupId = groupId;
        this.classType = classType;
        this.lecturer = lecturer;
    }
    /** @return slot id for timetable **/
    public String getSlotId(){
        return slotId;
    }

    /** @return day **/
    public String getDay(){
        return day;
    }

    /** @return time **/
    public String getTime(){
        return time;
    }
    /** @return module code **/
    public String getModuleCode(){
        return moduleCode;
    }

    /** @return group id **/
    public String getGroupId(){
        return groupId;
    }

    public String getClassType(){
        return classType;
    }

    public String getLecturer(){
        return lecturer;
    }

    public String getRoomId(){
        return roomId;
    }
}