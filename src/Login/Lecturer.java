package Login;

import java.util.List;

public class Lecturer extends User{

    private List<String> moduleCodes;

    /** Makes a Lecturer object
     *
     * @param userId the Lecturer's ID
     * @param name the Lecturer's name
     * @param password the Lecturer's password
     */
    public Lecturer(int userId, String name,String password, List<String> moduleCodes) {
        super(userId, name, "Lecturer", password);
        this.moduleCodes = moduleCodes;
    }

    public List<String> getModuleCodes() {
        return moduleCodes;
    }
}
