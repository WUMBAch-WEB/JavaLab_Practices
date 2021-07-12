import models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Optional;

public class CourseRepositoryJDBCTemplateImpl implements CourseRepository{
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;


    public CourseRepositoryJDBCTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String FIND_BY_ID = "select * from course where id = ?";

    //language=SQL
    private static final String FIND_TEACHERS_BY_COURSE_ID =
            "select * from teacher right join courses_teachers_relation ctr on teacher.teacher_id = ctr.teacher_id" +
                    " where course_id = ?";

    //language=SQL
    private static final String FIND_STUDENTS_BY_COURSE_ID =
            "select * from student right join courses_students_relation csr on student.student_id = csr.student_id" +
                    " where course_id = ?";

    //language=SQL
    private static final String FIND_LESSONS_BY_COURSE_ID =
            "select * from lesson right join courses_lessons_relation clr on lesson.lesson_id = clr.lesson_id" +
                    " where course_id = ?";

    //language=SQL
    private static final String FIND_COURSE_BY_NAME =
            "select * from course where name = ?";




    private RowMapper<Teacher> teacherRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("teacher_id");
        String name = rs.getString("first_name");
        String surname = rs.getString("last_name");
        String experience = rs.getString("experience");
        return new Teacher(id, name, surname, experience);
    };


    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        return new Student(rs.getLong("student_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("group_number"));

    };


    private RowMapper<Lesson> lessonRowMapper = (rs, rowNum) -> {
        return new Lesson(
                rs.getLong("lesson_id"),
                rs.getString("name"),
                rs.getString("day_week"),
                rs.getString("time"));
    };



    private ResultSetExtractor<Course> courseResultSetExtractor = rs -> {
        rs.next();
        return new Course(rs.getLong("id"),
                rs.getString("name"),
                rs.getString("date_start"),
                rs.getString("date_end"));
    };

    @Override
    public Optional<Course> findById(Long id) {
        Course course;
        try {
            course = jdbcTemplate.query(FIND_BY_ID, courseResultSetExtractor, id);
        } catch (Exception e) {
            return Optional.empty();
        }
        course.setTeacherList(jdbcTemplate.query(FIND_TEACHERS_BY_COURSE_ID,
                teacherRowMapper,
                id));
        course.setStudentList(jdbcTemplate.query(
                FIND_STUDENTS_BY_COURSE_ID,
                studentRowMapper,
                id));

        course.setLessonList(jdbcTemplate.query(
                FIND_LESSONS_BY_COURSE_ID,
                lessonRowMapper,
                id));

        return Optional.of(course);
    }

    @Override
    public Optional<Course> findByName(String name) {
        Course course;
        try {
            course = jdbcTemplate.query(FIND_COURSE_BY_NAME, courseResultSetExtractor, name);
        } catch (Exception e) {
            return Optional.empty();
        }
        var list = jdbcTemplate.query(FIND_TEACHERS_BY_COURSE_ID,
                teacherRowMapper,
                course.getId());
        course.setTeacherList(list);
        course.setStudentList(jdbcTemplate.query(
                FIND_STUDENTS_BY_COURSE_ID,
                studentRowMapper,
                course.getId()));

        course.setLessonList(jdbcTemplate.query(
                FIND_LESSONS_BY_COURSE_ID,
                lessonRowMapper,
                course.getId()));

        return Optional.of(course);
    }


}
