package model;

public class Classroom {

    public int classroom_id;
    public String room_code;
    public String room_name;
    public String note;

    public Classroom() {
    }

    public Classroom(int classroom_id, String room_code, String room_name, String note) {
        this.classroom_id = classroom_id;
        this.room_code = room_code;
        this.room_name = room_name;
        this.note = note;
    }
}