package Model;

public class TeachingAssignment {
    private String moduleCode;
    private String classType;
    private int maxCapacity;
    private String roomType;
    private String lecturerName;

    public TeachingAssignment(String moduleCode, String classType, int maxCapacity, String roomType, String lecturerName) {
        this.moduleCode = moduleCode;
        this.classType = classType;
        this.maxCapacity = maxCapacity;
        this.roomType = roomType;
        this.lecturerName = lecturerName;

    }

    public String getModuleCode() { return moduleCode; }
    public String getClassType() { return classType; }
    public int getMaxCapacity() { return maxCapacity; }
    public String getRoomType() { return roomType; }
    public String getLecturerName() { return lecturerName; }
    public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode; }

}
