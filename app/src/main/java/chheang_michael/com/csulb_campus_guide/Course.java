package chheang_michael.com.csulb_campus_guide;

public class Course {
    private String courseSubject;
    private String courseNumber;
    private String building;
    private String room;
    private String startTime;
    private String endTime;
    private String daysOfClass;

    // Default Constructor
    public Course() {
        this.courseSubject = "Course";
        this.courseNumber = "Course #";
        this.building = "Building";
        this.room = "Room #";
        this.startTime = "8:00 AM";
        this.endTime = "10:00 AM";
        this.daysOfClass = "Monday, Wednesday";
    }

    public Course(String courseSubject, String courseNumber, String building,
                  String room, String startTime, String endTime, String daysOfClass) {
        this.courseSubject = courseSubject;
        this.courseNumber = courseNumber;
        this.building = building;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.daysOfClass = daysOfClass;
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDaysOfClass() {
        return daysOfClass;
    }

    public void setDaysOfClass(String daysOfClass) {
        this.daysOfClass = daysOfClass;
    }
}
