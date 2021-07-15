package managment;

import javax.sql.DataSource;

public interface MainMenu {
    public void start();
    public void studentMenu(String nickname, String password);
    public void teacherMenu();
    public void adminMenu();
}
