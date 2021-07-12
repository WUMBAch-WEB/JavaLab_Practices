import models.Course;

import java.util.Optional;

public interface CourseRepository {
    public Optional<Course> findById(Long id);
    public Optional<Course> findByName(String name);
}
