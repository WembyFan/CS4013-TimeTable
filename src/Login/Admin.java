package Login;

public class Admin extends User {
    /** Makes an admin object
     *
     * @param userId the admin's ID
     * @param name the admin's name
     * @param password the admin's password
     */
    public Admin(int userId, String name,String password) {
        super(userId, name, "admin", password);
    }
}
