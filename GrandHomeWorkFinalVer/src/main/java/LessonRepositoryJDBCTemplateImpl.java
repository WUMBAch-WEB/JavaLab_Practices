import models.Course;
import models.Lesson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Optional;

public class LessonRepositoryJDBCTemplateImpl implements LessonRepository {

    //language=SQL
    private static final String FIND_BY_ID = "select * from lesson where lesson_id = ?";

    //language=SQL
    private static final String FIND_BY_NAME = "select  * from lesson where name = ?";

    //language=SQL
    private static final String FIND_COURSES_BY_LESSON_ID =
            "select  course_id from lesson right join courses_lessons_relation clr on lesson.lesson_id = clr.lesson_id" +
                    " where lesson.lesson_id = ?";

    //language=SQL
    private static final String FIND_COURSE_ID_BY_NAME =
            "select id as course_id from course where name = ?";

    //language=SQL
    private static final String FIND_LESSON_ID_BY_NAME =
            "select lesson_id from lesson where name = ?";

    //language=SQL
    private static final String SQL_INSERT =
            "insert into lesson(name, day_week, time)" +
                    "values (?, ?, ?)";

    //language=SQL
    private static final String SQL_MAKE_RELATION =
            "insert into courses_lessons_relation(lesson_id, course_id)" +
                    "values (?, ?)";

    //language=SQL
    private static final String SQL_UPDATE =
            "update lesson set name = ?, day_week = ?, time = ? where lesson_id = ?";

    private DataSource dataSource;
    private CourseRepository courseRepository;
    private JdbcTemplate jdbcTemplate;


    public LessonRepositoryJDBCTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.courseRepository = new CourseRepositoryJDBCTemplateImpl(dataSource);
    }


   private final RowMapper<Long> courseIdMapper = (rs, rowNum) ->{
     return rs.getLong("course_id");
   };

    private final RowMapper<Long> lessonsIdMapper = (rs, rowNum) ->{
        return rs.getLong("lesson_id");
    };



    private ResultSetExtractor<Lesson> lessonResultSetExtractor = rs -> {
        rs.next();
        return new Lesson(rs.getLong("lesson_id"),
                rs.getString("name"),
                rs.getString("day_week"),
                rs.getString("time"));
    };

    @Override
    public Optional<Lesson> findById(Long id) {
        Lesson lesson = jdbcTemplate.query(FIND_BY_ID, lessonResultSetExtractor, id);
        if (lesson != null){
            lesson.setCourseList(new ArrayList<Course>());
            var coursesList = jdbcTemplate.query(FIND_COURSES_BY_LESSON_ID, courseIdMapper, id);
            coursesList.forEach(x -> lesson.getCourseList().add(courseRepository.findById(x).get()));
            return Optional.of(lesson);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Lesson> findByName(String name) {
        Lesson lesson = jdbcTemplate.query(FIND_BY_NAME, lessonResultSetExtractor, name);
        if (lesson != null){
            lesson.setCourseList(new ArrayList<Course>());
            var coursesList = jdbcTemplate.query(FIND_COURSES_BY_LESSON_ID, courseIdMapper, lesson.getId());
            coursesList.forEach(x -> lesson.getCourseList().add(courseRepository.findById(x).get()));
            return Optional.of(lesson);
        }
        return Optional.empty();
    }

    @Override
    public void save(Lesson lesson) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, new String[] {"lesson_id"});
            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getDayWeek());
            statement.setString(3, lesson.getTime());
            return statement;
        }, keyHolder);

        lesson.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void makeRelation(String lessonName, String courseName) {

        var lessonsList = jdbcTemplate.query(FIND_LESSON_ID_BY_NAME, lessonsIdMapper, lessonName);
        var coursesList = jdbcTemplate.query(FIND_COURSE_ID_BY_NAME, courseIdMapper, courseName);
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_MAKE_RELATION);
            statement.setLong(1, lessonsList.get(0));
            statement.setLong(2, coursesList.get(0));
            return statement;
        });
    }

    @Override
    public void update(Lesson lesson, Long id) {
        jdbcTemplate.update(
                x -> {
                    PreparedStatement statement = x.prepareStatement(SQL_UPDATE);
                    statement.setString(1, lesson.getName());
                    statement.setString(2, lesson.getDayWeek());
                    statement.setString(3, lesson.getTime());
                    statement.setLong(4, id);
                    return statement;
                });
    }
}
