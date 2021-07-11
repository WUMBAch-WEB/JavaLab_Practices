import models.Course;
import models.Student;
import models.Teacher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryJDBCTemplateImpl implements CourseRepository{
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;


    public CourseRepositoryJDBCTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_FIND_BY_ID = "select *, " +
            " from course as c" +
            "left join courses_students_relation csr on c.id = csr.course_id " +
            "left join courses_teachers_relation as сtr on ctr.teacher_id = c.teacher_id where c.id = ? order by c.id ";

    private final RowMapper<Teacher> teacherRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("teacher_id");
        String firstName = row.getString("teacher_first_name");
        String lastname = row.getString("teacher_last_name");
        String exp = row.getString("experience");

        return new Teacher(id, firstName, lastname, exp);
    };

    private final RowMapper<Course> courseRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("id");
        String name = row.getString("name");
        String dateStart = row.getString("start_date");
        String dateEnd = row.getString("end_date");
        Teacher teacher = teacherRowMapper.mapRow(row, rowNumber);

        Course course = new Course(id, name, dateStart, dateEnd, teacher);
        course.setStudentList(new ArrayList<>());
        return course;
    };

    private final RowMapper<Student> studentRowMapper = (row, rowNumber) -> {
        Integer id = row.getInt("student_id");
        String firstName = row.getString("student_first_name");
        String lastName = row.getString("student_last_name");
        String groupNumber = row.getString("group_number");

        return new Student(id, firstName, lastName, groupNumber);
    };

    private final ResultSetExtractor<Course> courseExtractor2 = resultSet -> {
        Course course = null;
        if (resultSet.next()) {
            course = courseRowMapper.mapRow(resultSet, resultSet.getRow());
            course.setStudentList(new ArrayList<>());

            do {
                Integer id = resultSet.getObject("student_id", Integer.class);
                if (id != null) {
                    Student student = studentRowMapper.mapRow(resultSet, resultSet.getRow());
                    course.getStudentList().add(student);
                }
            } while (resultSet.next());
        }
        return course;
    };

    @Override
    public Optional<Course> findById(Integer id) {
        try {
            return Optional.of(jdbcTemplate.query(SQL_FIND_BY_ID, courseExtractor2, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
