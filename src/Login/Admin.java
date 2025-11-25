package Login;

public class Admin extends User {
    /** Makes an admin object
     *
     * @param userId the admins ID
     * @param name the admins name
     * @param password the admins password
     */
    public Admin(int userId, String name,String password) {
        super(userId, name, "admin", password);
    }
}
