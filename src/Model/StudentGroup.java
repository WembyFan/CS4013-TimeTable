package Model;

public class StudentGroup {
    private String groudId;
    private int year;

    public StudentGroup(String groupId, int year) {
        this.groudId = groupId;
        this.year = year;
    }

    public String getGroupId() {
        return groudId;
    }

    public int getYear() {
        return year;
    }

}
