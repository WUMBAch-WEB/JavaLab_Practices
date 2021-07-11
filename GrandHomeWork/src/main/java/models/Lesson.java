package models;

public class Lesson {
    private String name;
    private String day_week;
    private String time;
    private Integer id;
    private Course course;

    public Lesson(String name, String day_week, String time, Integer id) {
        this.name = name;
        this.day_week = day_week;
        this.time = time;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay_week() {
        return day_week;
    }

    public void setDay_week(String day_week) {
        this.day_week = day_week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", day_week='" + day_week + '\'' +
                ", time='" + time + '\'' +
                ", course=" + course +
                '}';
    }
}
