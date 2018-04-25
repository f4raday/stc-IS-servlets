package model.classes;

public class User {
    private long id;
    private String login;
    private String password;

    private String name;

    public User(long id, String login, String password, String name) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public User(String login, String password, String name) {
        this(0, login, password, name);
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }


    public static User Empty() {
        return new User(-1, "","","");
    }

    @Override
    public boolean equals(Object obj) {
        User that = (User) obj;
        if(this.id == that.id
                && this.login == that.login)
            return true;

        return false;
    }
}
