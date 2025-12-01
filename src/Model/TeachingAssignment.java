package Model;

/**
 * Represents a teaching assignment with details about module, class type, capacity, and lecturer.
 * This class stores information about which lecturer is assigned to teach which module,
 * including class type constraints and room requirements.
 *
 */
public class TeachingAssignment {
    private String moduleCode;
    private String classType;
    private int maxCapacity;
    private String roomType;
    private String lecturerName;

    /**
     * Constructs a new TeachingAssignment with specified parameters.
     *
     * @param moduleCode the unique code identifying module
     * @param classType the type of class (Lecture, Tutorial, Lab)
     * @param maxCapacity the maximum number of students the class can accommodate
     * @param roomType the type of room required for this class
     * @param lecturerName the name of the lecturer assigned to teach this class
     */
    public TeachingAssignment(String moduleCode, String classType, int maxCapacity, String roomType, String lecturerName) {
        this.moduleCode = moduleCode;
        this.classType = classType;
        this.maxCapacity = maxCapacity;
        this.roomType = roomType;
        this.lecturerName = lecturerName;

    }
    /**
     * @return the module code for this teaching assignment
     */
    public String getModuleCode() { return moduleCode; }

    /**
     * @return the type of class (Lecture, Tutorial, Lab)
     */
    public String getClassType() { return classType; }

    /**
     * @return the maximum capacity for this class
     */
    public int getMaxCapacity() { return maxCapacity; }

    /**
     * @return the required room type for this class
     */
    public String getRoomType() { return roomType; }

    /**
     * @return the name of the lecturer assigned to this class
     */
    public String getLecturerName() { return lecturerName; }

    /**
     * Updates the module code for this teaching assignment
     *
     * @param moduleCode the new module code to set
     */
    public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode; }

}
