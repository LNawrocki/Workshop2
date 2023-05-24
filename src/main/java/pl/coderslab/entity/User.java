package pl.coderslab.entity;

public class User {
    private int id;
    private String email;
    private  String userName;
    private String password;

    public User() {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", email='" + this.email + '\'' +
                ", userName='" + this.userName + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }
}
