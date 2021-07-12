import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import models.Course;
import models.Lesson;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args){
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
        CourseRepository courseRepository = new CourseRepositoryJDBCTemplateImpl(dataSource);
        LessonRepository lessonRepository = new LessonRepositoryJDBCTemplateImpl(dataSource);
        //Test for findById in CourseRepository
//        System.out.println(courseRepository.findById(2l));

        //Test for findByName in CourseRepository
//        System.out.println(courseRepository.findByName("ToadLab"));

        //Test for findById in LessonRepository
//        System.out.println(lessonRepository.findById(1l));

        //Test for findByName in LessonRepository
//        System.out.println(lessonRepository.findByName("JDBC"));

        //Test for save in CourseRepository
//        Course someCourse = new Course("AnyCourse", "12.07.21", "19.07.21");
//        courseRepository.save(someCourse);

        //Test for save and makeRelation in LessonRepository
//        Lesson someLesson = new Lesson("SomeLesson", "Monday", "14:00" );
//        lessonRepository.save(someLesson);
//        lessonRepository.makeRelation("SomeLesson", "SomeCourse");

        //Test for update in CourseRepository
//        Course updatedCourse = new Course("updatedCourse", "20.07.21", "30.07.21");
//        courseRepository.update(updatedCourse, 3l);

        //Test for update in LessonRepository
        Lesson updatedLesson =  new Lesson("UpdatedLesson", "Tuesday", "20:00");
        lessonRepository.update(updatedLesson, 7l);

    }
}
