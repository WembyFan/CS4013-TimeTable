package Login;

public class Student extends User{
    /** Makes a student object
     *
     * @param userId the student's unique ID
     * @param name the student's name
     * @param password the student's password
     */

    public Student(int userId, String name,String password) {
        super(userId, name, "student", password);
    }
}
