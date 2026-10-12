package ui;

import exception.ParkingException;
import model.Car;
import model.Driver;
import model.ParkingSpace;
import model.ParkingTicket;
import model.Payment;
import model.PaymentMethod;
import service.ParkingService;

import java.util.List;

public class DriverMenu extends UserMenu {
    private final Driver driver;

    public DriverMenu(Driver driver, ConsoleInput input, ParkingService parkingService) {
        super(input, parkingService);
        this.driver = driver;
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = input.readInt("Таңдау: ");
            switch (choice) {
                case 1:
                    showFreeSpaces();
                    break;
                case 2:
                    showMyCars();
                    break;
                case 3:
                    addCar();
                    break;
                case 4:
                    parkCar();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    showHistory();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Қате: мәзірде мұндай нөмір жоқ!");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("--- Жүргізуші мәзірі ---");
        System.out.println("1. Бос орындарды көрсету");
        System.out.println("2. Менің көліктерім");
        System.out.println("3. Көлік қосу");
        System.out.println("4. Көлікті тұраққа қою");
        System.out.println("5. Тұрақтан шығу және төлеу");
        System.out.println("6. Менің билеттерім (тарих)");
        System.out.println("0. Шығу (аккаунттан)");
    }

    private void showFreeSpaces() {
        List<ParkingSpace> free = parkingService.getFreeSpaces();
        if (free.isEmpty()) {
            System.out.println("Қазір бос орын жоқ.");
            return;
        }
        System.out.println("Бос орындар:");
        for (ParkingSpace space : free) {
            System.out.println("  " + space);
        }
    }

    private void showMyCars() {
        List<Car> cars = driver.getCars();
        if (cars.isEmpty()) {
            System.out.println("Сізде көлік жоқ. 3-тармақ арқылы қосыңыз.");
            return;
        }
        System.out.println("Менің көліктерім:");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + cars.get(i));
        }
    }

    private void addCar() {
        try {
            String plate = input.readString("Көлік нөмірі: ");
            String brand = input.readString("Марка: ");
            String color = input.readString("Түсі: ");
            driver.addCar(new Car(plate, brand, color));
            System.out.println("Көлік қосылды!");
        } catch (ParkingException e) {
            System.out.println("Қате: " + e.getMessage());
        }
    }

    private void parkCar() {
        if (driver.getCars().isEmpty()) {
            System.out.println("Алдымен көлік қосыңыз (3-тармақ).");
            return;
        }
        try {
            showMyCars();
            Car car = driver.getCar(input.readInt("Көлік нөмірін таңдаңыз: "));
            showFreeSpaces();
            int spaceNumber = input.readInt("Орын нөмірін таңдаңыз: ");
            ParkingTicket ticket = parkingService.parkCar(driver, car, spaceNumber);
            System.out.println("Көлік тұраққа қойылды!");
            System.out.println(ticket);
        } catch (ParkingException e) {
            System.out.println("Қате: " + e.getMessage());
        }
    }

    private void checkout() {
        List<ParkingTicket> active = parkingService.getActiveTicketsOf(driver);
        if (active.isEmpty()) {
            System.out.println("Белсенді билет жоқ.");
            return;
        }
        System.out.println("Белсенді билеттер:");
        for (ParkingTicket ticket : active) {
            System.out.println("  " + ticket);
        }
        try {
            int ticketId = input.readInt("Билет нөмірі: ");
            int hours = input.readInt("Неше сағат тұрды: ");
            System.out.println("Төлем түрі: 1 - Қолма-қол, 2 - Карта");
            PaymentMethod method = PaymentMethod.fromChoice(input.readInt("Таңдау: "));
            Payment payment = parkingService.checkout(driver, ticketId, hours, method);
            System.out.println("Төлем сәтті өтті! Жақсы жол!");
            System.out.println(payment);
        } catch (ParkingException e) {
            System.out.println("Қате: " + e.getMessage());
        }
    }

    private void showHistory() {
        List<ParkingTicket> history = parkingService.getTicketsOf(driver);
        if (history.isEmpty()) {
            System.out.println("Тарих бос.");
            return;
        }
        System.out.println("Менің билеттерім:");
        for (ParkingTicket ticket : history) {
            System.out.println("  " + ticket);
        }
    }
}
