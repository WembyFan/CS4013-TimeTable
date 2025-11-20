package Login;

public class Student extends User{

    private String groupId;
    /** Makes a student object
     *
     * @param userId the student's unique ID
     * @param name the student's name
     * @param password the student's password
     */

    public Student(int userId, String name,String password, String groupId) {
        super(userId, name, "student", password);
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
