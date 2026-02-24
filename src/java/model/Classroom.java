package model;

public class Classroom {

    public int classroomId;
    public String classroomName;
    public String location;

    // Constructor không tham số
    public Classroom() {
    }

    // Constructor đầy đủ
    public Classroom(int classroomId, String classroomName, String location) {
        this.classroomId = classroomId;
        this.classroomName = classroomName;
        this.location = location;
    }

    // Getter & Setter
    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}