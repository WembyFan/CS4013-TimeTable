package Model;

public class StudentGroup {
    private String course;
    private int year;
    private String groupId;
    private int size;

    public StudentGroup(String course, int year, String groupId, int size) {
       this.course = course;
       this.year = year;
       this.groupId = groupId;
       this.size = size;
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
