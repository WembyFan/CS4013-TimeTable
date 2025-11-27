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

    /** @return the user's name */
    public String getName() {
        return name;
    }

    /** @return the user's role */
    public String getRole() {
        return role;
    }

}