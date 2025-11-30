package Model;

/**
 * Represents the required teaching hours for a university module.
 * Tracks how many hours are needed for lectures, labs, and tutorials.
 */
public class ModuleHours {
    private String moduleCode;
    private int lectureHours;
    private int labHours;
    private int tutorialHours;

    /**
     * Creates a new ModuleHours record with specified hour requirements.
     *
     * @param moduleCode the unique identifier for the module
     * @param lectureHours number of lecture hours required
     * @param labHours number of laboratory hours required
     * @param tutorialHours number of tutorial hours required
     */
    public ModuleHours(String moduleCode, int lectureHours, int labHours, int tutorialHours) {
        this.moduleCode = moduleCode;
        this.lectureHours = lectureHours;
        this.labHours = labHours;
        this.tutorialHours = tutorialHours;
    }

    /**
     * @return the module code
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * @return number of lecture hours required
     */
    public int getLectureHours() {
        return lectureHours;
    }

    /**
     * @return number of lab hours required
     */
    public int getLabHours() {
        return labHours;
    }

    /**
     * @return number of tutorial hours required
     */
    public int getTutorialHours() {
        return tutorialHours;
    }

    /**
     * Calculates the total teaching hours for this module.
     * @return sum of lecture, lab, and tutorial hours
     */
    public int getTotalHours(){
        return(lectureHours + labHours + tutorialHours);
    }
}