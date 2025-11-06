package Login;

public class User {
    /** The unique identifiers for the user, users name, users role and users password */
    protected int userId;
    protected String name;
    protected String role;
    protected String password;


    /**
     * Makes a new user with the given details.
     * @param userId the user's unique ID
     * @param name the user's name
     * @param role the user's role
     * @param password the user's password
     */
    public User(int userId, String name, String role, String password) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    /** @return the user's ID */
    public int getUserId() {
        return userId;
    }

    /** @return the user's name */
    public String getName() {
        return name;
    }

    /** @return the user's role */
    public String getRole() {
        return role;
    }

    /** Checks if the password provided by the user matches the stored password.
     *
     * @param inputPassword the password the user entered
     * @return true if it matched, false otherwise
     */

    public boolean  checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
