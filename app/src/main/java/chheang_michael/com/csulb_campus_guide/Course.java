package chheang_michael.com.csulb_campus_guide;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Course {
    private String courseSubject;
    private String courseNumber;
    private String building;
    private String room;
    private String startTime;
    private String endTime;
    private String daysOfClass;
    private String notificationFlag;
    private String notifyTime;
    private String timeType;

    // Default Constructor
    public Course() {
        this.courseSubject = "Course";
        this.courseNumber = "Course #";
        this.building = "Building";
        this.room = "Room #";
        this.startTime = "8:00 AM";
        this.endTime = "10:00 AM";
        this.daysOfClass = "Monday, Wednesday";
        this.notificationFlag = "false";
        this.notifyTime = "30";
        this.timeType = "minute(s)";
    }

    public Course(String courseSubject, String courseNumber, String building,
                  String room, String startTime, String endTime, String daysOfClass, String notificationFlag, String notifyTime) {
        this.courseSubject = courseSubject;
        this.courseNumber = courseNumber;
        this.building = building;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.daysOfClass = daysOfClass;
        this.notificationFlag = notificationFlag;
        this.notifyTime = notifyTime;
        this.timeType = timeType;
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

    public String getNotificationFlag() { return notificationFlag; }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setNotificationFlag(String notificationFlag) {
        if (Objects.equals(notificationFlag, "true")) {
            this.notificationFlag = "true";
        }
        else{
            this.notificationFlag = "false";
        }
    }

    public String getNotifyTime() { return notifyTime; }

    public void setNotifyTime(String notifyTime) { this.notifyTime = notifyTime; }

    public String getTimeType() { return this.timeType; }

    public void setTimeType(String timeType) { this.timeType = timeType; }

    @Override
    public String toString() {
        return  courseSubject + ";" +
                courseNumber  + ";" +
                building      + ";" +
                room          + ";" +
                startTime     + ";" +
                endTime       + ";" +
                daysOfClass   + ";" +
                notificationFlag + ";" +
                notifyTime    + ";" +
                timeType      + ";";
    }
}