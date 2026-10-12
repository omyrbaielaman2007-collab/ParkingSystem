package model;

public abstract class User {
    private static int counter = 1;

    private final int id;
    private String username;
    private String password;
    private String fullName;

    public User(String username, String password, String fullName) {
        this.id = counter++;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public abstract String getRole();

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public int getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return fullName + " (" + getRole() + ")";
    }
}
