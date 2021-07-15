package repositories;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TeacherRepositoryJDBCTemplateImpl implements TeacherRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    public TeacherRepositoryJDBCTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
