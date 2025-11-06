package Login;

public class Lecturer extends User{
        /** Makes a Lecturer object
         *
         * @param userId the Lecturer's ID
         * @param name the Lecturer's name
         * @param password the Lecturer's password
         */

        public Lecturer(int userId, String name,String password) {
            super(userId, name, "Lecturer", password);
        }
}
