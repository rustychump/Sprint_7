package cards;

public class CreateCourierCard {
    private String login;
    private String password;
    private String firstName;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }



    public CreateCourierCard(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public CreateCourierCard(String login, String firstName) {
        this.login = login;
        this.firstName = firstName;
    }

    public CreateCourierCard(String password) {
        this.password = password;
    }

    public CreateCourierCard() {}
}
