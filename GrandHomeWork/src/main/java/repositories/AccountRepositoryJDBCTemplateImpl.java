package repositories;

import models.Account;
import models.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.Optional;

public class AccountRepositoryJDBCTemplateImpl implements AccountRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    public AccountRepositoryJDBCTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String FIND_BY_ID =
            "select * from account where account_id = ?";

    //language=SQL
    private static final String FIND_BY_NICKNAME =
            "select * from account where nickname = ?";

    //language=SQL
    private static final String FIND_ACCOUNT_FOR_LOG_IN =
            "select * from account where nickname = ? and password = ?";



//    private RowMapper<Account> accountRowMapper = (rs, rowNum) ->{
//        Long id = rs.getLong("account_id");
//        String nickname = rs.getString("nickname");
//        String password = rs.getString("password");
//        String status = rs.getString("status");
//        return new Account(id, nickname, password, status);
//    };


    private ResultSetExtractor<Account> accountResultSetExtractor = rs -> {
        rs.next();
        return new Account(rs.getLong("account_id"),
                rs.getString("nickname"),
                rs.getString("password"),
                rs.getString("status"));
    };

    @Override
    public Optional<Account> findById(Long id) {
        Account account;
        try {
            account = jdbcTemplate.query(FIND_BY_ID, accountResultSetExtractor, id);
            return Optional.of(account);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Account> findByNickname(String nickname) {
        Account account;
        try {
            account = jdbcTemplate.query(FIND_BY_NICKNAME, accountResultSetExtractor, nickname);
            return Optional.of(account);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean logIn(String nickname, String password) {
        Account account;
        try {
            account = jdbcTemplate.query(FIND_ACCOUNT_FOR_LOG_IN, accountResultSetExtractor, nickname, password);
            if (account.getStatus().equals("admin")) { System.out.println("Вы вошли в аккаунт администратора"); }
            if (account.getStatus().equals("student")) { System.out.println("Вы вошли в аккаунт студента"); }
            if (account.getStatus().equals("teacher")) { System.out.println("Вы вошли в аккаунт преподователя"); }
            if (account.getStatus().equals("banned")) { System.out.println("Судя по всему вы забанены :С \n " +
                                                                        "Повторите попытку"); }
            return true;
        } catch (Exception e) {
            System.out.println("Аккаунт не найден, проверьте правильность введенных данных.");
            return false;
        }
    }

    @Override
    public Optional<Student> findStudentByNickname(String nickname) {
        return Optional.empty();
    }
}
