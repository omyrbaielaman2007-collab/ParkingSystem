package model;

import exception.ParkingException;

public enum PaymentMethod {
    CASH("Қолма-қол"),
    CARD("Карта");

    private final String label;

    PaymentMethod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static PaymentMethod fromChoice(int choice) throws ParkingException {
        if (choice == 1) return CASH;
        if (choice == 2) return CARD;
        throw new ParkingException("Төлем түрі қате! 1 немесе 2 енгізіңіз.");
    }
}
