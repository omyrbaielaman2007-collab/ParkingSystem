package model;

import exception.ParkingException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ParkingTicket {
    private static int counter = 1;

    private final int id;
    private final Driver driver;
    private final Car car;
    private final ParkingSpace space;
    private final LocalDateTime entryTime;
    private int hoursParked;
    private Payment payment;

    public ParkingTicket(Driver driver, Car car, ParkingSpace space) {
        this.id = counter++;
        this.driver = driver;
        this.car = car;
        this.space = space;
        this.entryTime = LocalDateTime.now();
    }

    /** Билет төленбегенше белсенді болып саналады. */
    public boolean isActive() {
        return payment == null;
    }

    public Payment pay(int hours, PaymentMethod method) throws ParkingException {
        if (!isActive()) {
            throw new ParkingException("Бұл билет бұрын төленген!");
        }
        if (hours <= 0) {
            throw new ParkingException("Сағат саны 0-ден үлкен болуы керек!");
        }
        this.hoursParked = hours;
        this.payment = new Payment(hours * space.getHourlyRate(), method);
        return payment;
    }

    public int getId() { return id; }
    public Driver getDriver() { return driver; }
    public Car getCar() { return car; }
    public ParkingSpace getSpace() { return space; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public int getHoursParked() { return hoursParked; }
    public Payment getPayment() { return payment; }

    @Override
    public String toString() {
        String time = entryTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        String status = isActive()
                ? "белсенді"
                : "төленді: " + String.format("%.0f", payment.getAmount()) + " тг (" + hoursParked + " сағ)";
        return "Билет №" + id + " | " + car.getPlateNumber() + " | орын №" + space.getNumber()
                + " | кіру: " + time + " | " + status;
    }
}
