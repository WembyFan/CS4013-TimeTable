package Model;

public class CourseModule {
    private String course;
    private int year;
    private int semester;
    private String moduleCode;

    public CourseModule(String course, int year, int semester, String moduleCode) {
        this.course = course;
        this.year = year;
        this.semester = semester;
        this.moduleCode = moduleCode;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public String getModuleCode() {
        return moduleCode;
    }
}