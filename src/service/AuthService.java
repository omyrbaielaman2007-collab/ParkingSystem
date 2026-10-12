package service;

import exception.AuthException;
import model.Admin;
import model.Driver;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final List<User> users = new ArrayList<>();

    public AuthService() {
        users.add(new Admin("admin", "admin123", "Әкімші"));
    }

    public Driver registerDriver(String username, String password, String fullName) throws AuthException {
        if (fullName.isEmpty()) {
            throw new AuthException("Аты-жөні бос болмауы керек!");
        }
        if (username.length() < 3) {
            throw new AuthException("Логин кемінде 3 символ болуы керек!");
        }
        if (password.length() < 4) {
            throw new AuthException("Құпиясөз кемінде 4 символ болуы керек!");
        }
        if (findByUsername(username) != null) {
            throw new AuthException("Бұл логин бос емес: " + username);
        }
        Driver driver = new Driver(username, password, fullName);
        users.add(driver);
        return driver;
    }

    public User login(String username, String password) throws AuthException {
        User user = findByUsername(username);
        if (user == null || !user.checkPassword(password)) {
            throw new AuthException("Логин немесе құпиясөз қате!");
        }
        return user;
    }

    private User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
