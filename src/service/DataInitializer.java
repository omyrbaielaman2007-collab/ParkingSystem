package service;

import exception.ParkingException;
import model.ElectricSpace;
import model.StandardSpace;
import model.VipSpace;

public class DataInitializer {
    public static ParkingLot createParkingLot() {
        ParkingLot lot = new ParkingLot("Орталық автотұрақ");
        try {
            for (int i = 1; i <= 6; i++) {
                lot.addSpace(new StandardSpace(i));
            }
            lot.addSpace(new VipSpace(7));
            lot.addSpace(new VipSpace(8));
            lot.addSpace(new ElectricSpace(9));
            lot.addSpace(new ElectricSpace(10));
        } catch (ParkingException e) {
            throw new IllegalStateException("Бастапқы деректер қате: " + e.getMessage());
        }
        return lot;
    }
}
