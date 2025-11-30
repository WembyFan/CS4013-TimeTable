package Model;

public class ModuleEnrollment {
    private String moduleCode;
    private int studentCount;

    public ModuleEnrollment(String moduleCode, int studentCount) {
        this.moduleCode = moduleCode;
        this.studentCount = studentCount;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
