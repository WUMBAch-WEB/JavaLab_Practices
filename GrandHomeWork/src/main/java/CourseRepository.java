import models.Course;

import java.util.Optional;

public interface CourseRepository {
    public Optional<Course> findById(Long id);
    public Optional<Course> findByName(String name);
    public void save(Course course);
    public void update(Course course, Long id);
}
