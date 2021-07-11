import models.Course;

import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findById(Integer id);
}
