package Model;

/**
 * Represents a student group with course information, academic year, and group size.
 * This class models a group of students taking the same course in the same academic year.
 *
 */
public class StudentGroup {
    private String course;
    private int year;
    private String groupId;
    private int size;

    /**
     * Constructs a new StudentGroup with specified parameters.
     *
     * @param course the course code or name
     * @param year the academic year of the group
     * @param groupId the unique identifier for the group
     * @param size the number of students in the group
     */
    public StudentGroup(String course, int year, String groupId, int size) {
       this.course = course;
       this.year = year;
       this.groupId = groupId;
       this.size = size;
    }

    /**
     * @return the course associated with this group
     */
    public String getCourse() {
        return course;
    }

    /**
     * @return the unique group identifier
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @return the academic year of the group
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the number of students in the group
     */
    public int getSize() {
        return size;
    }

    /**
     * Updates the size of the student group
     *
     * @return the new size of the group (note: method name suggests setter but returns value)
     */
    public int setSize() {
        return size;
    }
}
