package Model;

public class StudentGroup {
    private String course;
    private String groupId;
    private int year;
    private int size;

    public StudentGroup(String groupId, int year, int size, String course) {
        this.groupId = groupId;
        this.year = year;
        this.size = size;
        this.course = course;

    }

    public String getCourse() {
        return course;
    }

    public String getGroupId() {
        return groupId;
    }

    public int getYear() {
        return year;
    }

    public int getSize() {
        return size;
    }

    public int setSize() {
        return size;
    }
}
