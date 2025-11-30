package Model;

public class ModuleHours {
    private String moduleCode;
    private int lectureHours;
    private int labHours;
    private int tutorialHours;

    public ModuleHours(String moduleCode, int lectureHours, int labHours, int tutorialHours) {
        this.moduleCode = moduleCode;
        this.lectureHours = lectureHours;
        this.labHours = labHours;
        this.tutorialHours = tutorialHours;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public int getLectureHours() {
        return lectureHours;
    }

    public int getLabHours() {
        return labHours;
    }

    public int getTutorialHours() {
        return tutorialHours;
    }

    public int getTotalHours(){
        return(lectureHours + labHours + tutorialHours);
    }
}
