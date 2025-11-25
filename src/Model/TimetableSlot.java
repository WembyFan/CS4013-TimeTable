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

    public TimetableSlot(String slot, String day, String time, String moduleCode, String groupId, String classType, String lecturer) {
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




}