package ui;

import exception.ParkingException;
import model.Admin;
import model.ElectricSpace;
import model.ParkingSpace;
import model.ParkingTicket;
import model.StandardSpace;
import model.VipSpace;
import service.ParkingService;

import java.util.List;

public class AdminMenu extends UserMenu {
    private final Admin admin;

    public AdminMenu(Admin admin, ConsoleInput input, ParkingService parkingService) {
        super(input, parkingService);
        this.admin = admin;
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = input.readInt("Таңдау: ");
            switch (choice) {
                case 1:
                    showAllSpaces();
                    break;
                case 2:
                    showAllTickets();
                    break;
                case 3:
                    System.out.printf("Жалпы түсім: %.0f тг%n", parkingService.getTotalIncome());
                    break;
                case 4:
                    addSpace();
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
        System.out.println("--- Әкімші мәзірі (" + admin.getFullName() + ") ---");
        System.out.println("1. Барлық орындар");
        System.out.println("2. Барлық билеттер");
        System.out.println("3. Жалпы түсім");
        System.out.println("4. Жаңа орын қосу");
        System.out.println("0. Шығу (аккаунттан)");
    }

    private void showAllSpaces() {
        System.out.println("Барлық орындар:");
        for (ParkingSpace space : parkingService.getAllSpaces()) {
            System.out.println("  " + space);
        }
    }

    private void showAllTickets() {
        List<ParkingTicket> all = parkingService.getAllTickets();
        if (all.isEmpty()) {
            System.out.println("Билеттер жоқ.");
            return;
        }
        for (ParkingTicket ticket : all) {
            System.out.println("  " + ticket + " | " + ticket.getDriver().getFullName());
        }
    }

    private void addSpace() {
        System.out.println("Орын түрі: 1 - Стандарт, 2 - VIP, 3 - Электро");
        int type = input.readInt("Таңдау: ");
        int number = parkingService.getNextSpaceNumber();
        try {
            ParkingSpace space;
            if (type == 1) {
                space = new StandardSpace(number);
            } else if (type == 2) {
                space = new VipSpace(number);
            } else if (type == 3) {
                space = new ElectricSpace(number);
            } else {
                throw new ParkingException("Орын түрі қате!");
            }
            parkingService.addSpace(space);
            System.out.println("Орын қосылды: " + space);
        } catch (ParkingException e) {
            System.out.println("Қате: " + e.getMessage());
        }
    }
}
