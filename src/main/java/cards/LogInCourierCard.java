package cards;

public class LogInCourierCard {
    private String login;
    private String password;

    public LogInCourierCard(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LogInCourierCard(String password) {
        this.password = password;
    }

    public LogInCourierCard() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


}
