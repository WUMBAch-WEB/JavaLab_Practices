package managment;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import models.Account;
import repositories.*;
import config.ApplicationConfig;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

public class MainMenuImpl implements MainMenu {
    @Override
    public void start() {
        Scanner in = new Scanner(System.in);

        AccountRepository accountRepository = new ApplicationConfig().getAccountRep();


        System.out.println("Добро пожаловать на нашу образовательную платформу." +
                "\n Вам необходимо авторизоваться или зарегистрироваться." +
                "\n Выберите для продолжения:" +
                "\n [1]Авторизоваться    [2]Зарегистрироваться    [3]Покинуть платформу");

        while (true){
            String choice = in.next();
            switch (choice){
                case "1" :
                    System.out.println("Введите nickname: ");
                    String nickname = in.next();
                    System.out.println("Введите password: ");
                    String password = in.next();
                    if (accountRepository.logIn(nickname, password)){
                        Optional<Account> account = accountRepository.findByNickname(nickname);
                        if (account.get().getStatus().equals("admin")){
                            adminMenu();
                        }
                        if (account.get().getStatus().equals("student")){
                            studentMenu(nickname, password);
                        }
                        if (account.get().getStatus().equals("teacher")){
                            teacherMenu();
                        }
                    }
                    else {
                        System.out.println("Выберите для продолжения:" +
                                "\n [1]Авторизоваться    [2]Зарегистрироваться    [3]Покинуть платформу");
                        continue;
                    }
                    break;
                case "2" :
                    System.out.println("in dev");
                    break;
                case "3" :
                    System.out.println("Хорошего дня!");
                    System.exit(0);
                default :
                    System.out.println("Неверные входные данные!" +
                            "\n Выберите для продолжения:" +
                    "\n [1]Авторизоваться    [2]Зарегистрироваться    [3]Покинуть платформу");
            }
        }
    }

    @Override
    public void studentMenu(String nickname, String password) {
        StudentRepository studentRepository = new ApplicationConfig().getStudentRep();
        Scanner in = new Scanner(System.in);
        System.out.println("in dev");
        while (true){
            System.out.println("Для продолжения выберите: " +
                    "\n [1]Сменить данные аккаунта    [2]Записаться на курс" +
                    "\n [3]Мои курсы                  [4]Узнать расписание" +
                    "\n [5]Выйти из системы");
            String choice = in.next();
            switch (choice) {
                case "1" :
                    studentRepository.update(nickname);
                    break;
                case "2" :
                    System.out.println("in dev");
                    break;
                case "3" :
                    System.out.println("in dev");
                    break;
                case "4" :
                    System.out.println("in dev");
                    break;
                case "5" :
                    System.out.println("Хорошего дня!");
                    System.exit(0);
                    break;
                default  :
                    System.out.println("Неверные входные данные." +
                            "\n Повторите попытку.");
                    break;
            }
        }
    }

    @Override
    public void teacherMenu() {
        System.out.println("in dev");
        System.out.println("Для продолжения выберите: " +
                "\n [1]Сменить данные аккаунта    [2]Мои курсы" +
                "\n [3]Оставить заявку на запуск нового курса" +
                "\n [4]Изменить расписание курса  [5]Выйти из системы");
    }

    @Override
    public void adminMenu() {
        System.out.println("in dev");
    }


}
