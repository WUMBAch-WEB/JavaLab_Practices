package models;

import java.util.List;

public class Course {

    private String name;
    private String dateStart;
    private String dateEnd;
    private Integer id;

    private List<Lesson> lessonList;
    private List<Student> studentList;

    public Course(String name, String date_start, String date_end, Integer id) {
        this.name = name;
        this.dateStart = date_start;
        this.dateEnd = date_end;
        this.id = id;
    }

    public Course(Integer id, String name, String date_start, String date_end, Teacher teacher) {
        this.name = name;
        this.dateStart = date_start;
        this.dateEnd = date_end;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", date_start='" + dateStart + '\'' +
                ", date_end='" + dateEnd + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_start() {
        return dateStart;
    }

    public void setDate_start(String date_start) {
        this.dateStart = date_start;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
