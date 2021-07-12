package models;

import java.util.List;

public class Lesson {
    private String name;
    private String dayWeek;
    private String time;
    private Long id;
    private List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Lesson(Long id, String name, String dayWeek, String time) {
        this.id = id;
        this.name = name;
        this.dayWeek = dayWeek;
        this.time = time;
    }

    public Lesson(String name, String dayWeek, String time) {
        this.name = name;
        this.dayWeek = dayWeek;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", day_week='" + dayWeek + '\'' +
                ", time='" + time + '\'' +
                ", id=" + id +
                ", courseList=" + courseList +
                '}';
    }
}
