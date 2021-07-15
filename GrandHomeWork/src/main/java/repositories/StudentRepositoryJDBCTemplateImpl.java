package repositories;

import models.Course;
import models.Lesson;
import models.Student;
import models.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class StudentRepositoryJDBCTemplateImpl implements StudentRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public StudentRepositoryJDBCTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //language=SQL
    private static final String FIND_STUDENT_ID_BY_NICKNAME =
            "select student_id from account, accounts_students_relation" +
                    " where account.account_id =" +
                    " accounts_students_relation.account_id" +
                    " and account.nickname = ?";

    //language=SQL
    private static final String FIND_BY_ID =
            "select * from student where student_id = ? ";

    //language=SQL
    private static final String SQL_UPDATE_NAME =
            "update student set first_name = ?, last_name = ? where student_id = ?";

    //language=SQL
    private static final String SQL_UPDATE_ACCOUNT =
            "update account set nickname = ?, password = ? where nickname = ?";

    private final RowMapper<Long> studentsIdMapper = (rs, rowNum) -> {
        return rs.getLong("student_id");
    };

    private RowMapper<Teacher> teacherRowMapper = (rs, rowNum) -> {
        Long id = rs.getLong("teacher_id");
        String name = rs.getString("first_name");
        String surname = rs.getString("last_name");
        String experience = rs.getString("experience");
        return new Teacher(id, name, surname, experience);
    };


//    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
//        return new Student(rs.getLong("student_id"),
//                rs.getString("first_name"),
//                rs.getString("last_name"),
//                rs.getString("group_number"));
//
//    };


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

    private ResultSetExtractor<Student> studentResultSetExtractor = rs -> {
        rs.next();
        return new Student(rs.getLong("student_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("group_number"));
    };

    @Override
    public void update(String nickname) {
        Scanner in = new Scanner(System.in);
        var studentsList = jdbcTemplate.query(FIND_STUDENT_ID_BY_NICKNAME, studentsIdMapper, nickname);
        Student student = jdbcTemplate.query(FIND_BY_ID, studentResultSetExtractor, studentsList.get(0));
        if (student != null) {
            System.out.println("Ваши актуальные данные: \n" +
                    student);
            boolean flag = true;
            while (flag) {
                System.out.println("Выберите для продолжения: " +
                        "\n [1]Изменить ФИ   [2]Изменить nickname/password");
                String choice = in.next();
                switch (choice) {
                    case "1":
                        System.out.println("Введите новые данные.");
                        System.out.println("Фамилия: "); String lastName = in.next();
                        System.out.println("Имя: "); String firstName = in.next();
                        jdbcTemplate.update(
                                x -> {
                                    PreparedStatement statement = x.prepareStatement(SQL_UPDATE_NAME);
                                    statement.setString(1, firstName);
                                    statement.setString(2, lastName);
                                    statement.setLong(3, studentsList.get(0));
                                    return statement;
                                });
                        System.out.println("Вы успешно изменили свои данные");
                        flag = false;
                        break;
                    case "2":
                        System.out.println("Введите новые данные.");
                        System.out.println("Nickname: "); String newNickname = in.next();
                        System.out.println("Password: "); String password = in.next();
                        jdbcTemplate.update(
                                x -> {
                                    PreparedStatement statement = x.prepareStatement(SQL_UPDATE_ACCOUNT);
                                    statement.setString(1, newNickname);
                                    statement.setString(2, password);
                                    statement.setString(3, nickname);
                                    return statement;
                                });
                        System.out.println("Вы успешно изменили свои данные");
                        flag = false;
                        break;
                    default:
                        System.out.println("Неверные входные данные." +
                                "\n Повторите попытку.");
                        break;

                }
            }
        } else {
            System.out.println("Error");
        }
    }
}
