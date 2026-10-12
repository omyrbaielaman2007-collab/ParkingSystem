package ui;

import exception.AuthException;
import model.Admin;
import model.Driver;
import model.User;
import service.AuthService;
import service.DataInitializer;
import service.ParkingService;

public class ConsoleMenu {
    private final ConsoleInput input = new ConsoleInput();
    private final AuthService authService = new AuthService();
    private final ParkingService parkingService = new ParkingService(DataInitializer.createParkingLot());

    public void start() {
        System.out.println("=== АВТОТҰРАҚ ЖҮЙЕСІ ===");
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1. Тіркелу");
            System.out.println("2. Кіру");
            System.out.println("0. Шығу");
            int choice = input.readInt("Таңдау: ");
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Қате: мәзірде мұндай нөмір жоқ!");
            }
        }
        System.out.println("Сау болыңыз!");
        input.close();
    }

    private void register() {
        try {
            String fullName = input.readString("Аты-жөні: ");
            String username = input.readString("Логин: ");
            String password = input.readString("Құпиясөз: ");
            authService.registerDriver(username, password, fullName);
            System.out.println("Тіркелу сәтті өтті! Енді жүйеге кіріңіз.");
        } catch (AuthException e) {
            System.out.println("Қате: " + e.getMessage());
        }
    }

    private void login() {
        try {
            String username = input.readString("Логин: ");
            String password = input.readString("Құпиясөз: ");
            User user = authService.login(username, password);
            System.out.println("Қош келдіңіз, " + user + "!");

            UserMenu menu;
            if (user instanceof Driver) {
                menu = new DriverMenu((Driver) user, input, parkingService);
            } else {
                menu = new AdminMenu((Admin) user, input, parkingService);
            }
            menu.show();
        } catch (AuthException e) {
            System.out.println("Қате: " + e.getMessage());
        }
    }
}
