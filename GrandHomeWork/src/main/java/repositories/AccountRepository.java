package repositories;

import models.Account;
import models.Student;

import java.util.Optional;

public interface AccountRepository {
    public Optional<Account> findById(Long id);
    public Optional<Account> findByNickname(String nickname);
    public boolean logIn(String nickname, String password);
    public Optional<Student> findStudentByNickname(String nickname);
}
