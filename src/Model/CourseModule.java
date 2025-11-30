package Model;

/**
 * Links a module to a specific course, year, and semester.
 * Defines which modules are part of each academic programme.
 */
public class CourseModule {
    private String course;
    private int year;
    private int semester;
    private String moduleCode;

    /**
     * Creates a course-module relationship.
     *
     * @param course the academic programme
     * @param year the academic year (1-4)
     * @param semester the semester (1 or 2)
     * @param moduleCode the module being offered
     */
    public CourseModule(String course, int year, int semester, String moduleCode) {
        this.course = course;
        this.year = year;
        this.semester = semester;
        this.moduleCode = moduleCode;
    }

    /**
     * @return the academic programme code
     */
    public String getCourse() {
        return course;
    }

    /**
     * @return the academic year (1-4)
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the semester (1 or 2)
     */
    public int getSemester() {
        return semester;
    }

    /**
     * @return the module code
     */
    public String getModuleCode() {
        return moduleCode;
    }
}