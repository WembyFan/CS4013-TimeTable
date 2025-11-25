package Login;

public class User {

    /** The unique identifier for the user */
    private int userId;

    /** The user's name */
    private String name;

    /** The user's role (student, lecturer, admin) */
    private String role;

    /** The user's password */
    private String password;


    /**
     * Creates a new user with the given details.
     *
     * @param userId    the user's unique ID
     * @param name      the user's name
     * @param role      the user's role
     * @param password  the user's password
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

    /** Sets the user's ID */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** @return the user's name */
    public String getName() {
        return name;
    }

    /** Sets the user's name */
    public void setName(String name) {
        this.name = name;
    }

    /** @return the user's role */
    public String getRole() {
        return role;
    }

    /** Sets the user's role */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Checks if the password provided by the user matches the stored password.
     *
     * @param inputPassword the password entered during login
     * @return true if it matches, false otherwise
     */
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}