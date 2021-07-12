package models;

import java.util.List;

public class Course {

    private String name;
    private String dateStart;
    private String dateEnd;
    private Long id;
    private List<Teacher> teacherList;
    private List<Lesson> lessonList;
    private List<Student> studentList;



    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public Course(Long id, String name, String date_start, String date_end) {
        this.name = name;
        this.dateStart = date_start;
        this.dateEnd = date_end;
        this.id = id;
    }

    public Course(String name, String date_start, String date_end) {
        this.name = name;
        this.dateStart = date_start;
        this.dateEnd = date_end;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", id=" + id +
                ", teacherList=" + teacherList +
                ", lessonList=" + lessonList +
                ", studentList=" + studentList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String date_start) {
        this.dateStart = date_start;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
