package repositories;

import models.Lesson;

import java.util.Optional;

public interface LessonRepository {
    public Optional<Lesson> findById(Long id);
    public Optional<Lesson> findByName(String name);
    public void save(Lesson lesson);
    public void makeRelation(String lessonName, String courseName);
    public void update(Lesson lesson, Long id);
}
