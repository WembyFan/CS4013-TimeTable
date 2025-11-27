package Login;

public class Student extends User{

    private String courseName;
    private int year;
    private String groupId;

    /**
     * Makes a student object
     *
     * @param id   the student's unique ID
     * @param name     the student's name
     * @param password the student's password
     * @param year
     */
    public Student(int id, String name, String password,String courseName, int year, String groupId) {
        super(id, name, "student", password);
        this.courseName = courseName;
        this.year = year;
        this.groupId = groupId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getYear() {
        return year;
    }

    public String getGroupId() {
        return groupId;
    }
}
