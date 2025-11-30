package Model;

/**
 * Tracks how many students are enrolled in a specific module.
 * Used for capacity planning and room assignment.
 */
public class ModuleEnrollment {
    private String moduleCode;
    private int studentCount;

    /**
     * Creates a new enrollment record for a module.
     *
     * @param moduleCode the module students are enrolled in
     * @param studentCount number of students taking the module
     */
    public ModuleEnrollment(String moduleCode, int studentCount) {
        this.moduleCode = moduleCode;
        this.studentCount = studentCount;
    }

    /**
     * @return the module code
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * @return number of students enrolled in the module
     */
    public int getStudentCount() {
        return studentCount;
    }
}