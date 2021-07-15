package models;

public class Account {
    private Long accountId;
    private String nickname;
    private String password;
    private String status;
    public Long getAccountId() {
        return accountId;
    }

    public Account(Long accountId, String nickname, String password, String status) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.password = password;
        this.status = status;
    }

    public Account(String nickname, String password, String status) {
        this.nickname = nickname;
        this.password = password;
        this.status = status;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
