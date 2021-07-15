package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import repositories.*;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfig {

    public DataSource getDataSource() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src//main//resources//application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));

        DataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    public LessonRepository getLessonRep(){
        LessonRepository lessonRepository = new LessonRepositoryJDBCTemplateImpl(getDataSource());
        return lessonRepository;
    }

    public AccountRepository getAccountRep(){
        AccountRepository accountRepository = new AccountRepositoryJDBCTemplateImpl(getDataSource());
        return accountRepository;
    }

    public CourseRepository getCourseRep(){
        CourseRepository courseRepository = new CourseRepositoryJDBCTemplateImpl(getDataSource());
        return courseRepository;
    }

    public StudentRepository getStudentRep(){
        StudentRepository studentRepository = new StudentRepositoryJDBCTemplateImpl(getDataSource());
        return studentRepository;
    }

    public TeacherRepository getTeacherRep(){
        TeacherRepository teacherRepository = new TeacherRepositoryJDBCTemplateImpl(getDataSource());
        return teacherRepository;
    }
}
