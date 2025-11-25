package Model;

public class StudentGroup {
    private String course;
    private String groudId;
    private int year;
    private int size;

    public StudentGroup(String groupId, int year, int size, String course) {
        this.groudId = groupId;
        this.year = year;
        this.size = size;
        this.course = course;

    }

    public String getCourse() { return course; }

    public String getGroupId() { return groudId; }

    public int getYear() { return year; }

}
