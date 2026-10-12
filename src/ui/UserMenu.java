package ui;

import service.ParkingService;

public abstract class UserMenu {
    protected final ConsoleInput input;
    protected final ParkingService parkingService;

    public UserMenu(ConsoleInput input, ParkingService parkingService) {
        this.input = input;
        this.parkingService = parkingService;
    }

    public abstract void show();
}
