package service;

import exception.ParkingException;
import model.Car;
import model.Driver;
import model.ParkingSpace;
import model.ParkingTicket;
import model.Payment;
import model.PaymentMethod;

import java.util.ArrayList;
import java.util.List;


public class ParkingService {
    private final ParkingLot lot;
    private final List<ParkingTicket> tickets = new ArrayList<>();

    public ParkingService(ParkingLot lot) {
        this.lot = lot;
    }

    public ParkingTicket parkCar(Driver driver, Car car, int spaceNumber) throws ParkingException {
        ParkingSpace space = lot.findByNumber(spaceNumber);
        if (space == null) {
            throw new ParkingException("Орын №" + spaceNumber + " табылмады!");
        }
        if (space.isOccupied()) {
            throw new ParkingException("Орын №" + spaceNumber + " бос емес!");
        }
        if (isCarParked(car)) {
            throw new ParkingException("Бұл көлік қазір тұрақта тұр: " + car.getPlateNumber());
        }
        ParkingTicket ticket = new ParkingTicket(driver, car, space);
        space.occupy();
        tickets.add(ticket);
        return ticket;
    }

    public Payment checkout(Driver driver, int ticketId, int hours, PaymentMethod method) throws ParkingException {
        ParkingTicket ticket = findActiveTicket(driver, ticketId);
        Payment payment = ticket.pay(hours, method);
        ticket.getSpace().release();
        return payment;
    }

    public List<ParkingSpace> getFreeSpaces() {
        return lot.getFreeSpaces();
    }

    public List<ParkingSpace> getAllSpaces() {
        return lot.getAllSpaces();
    }

    public void addSpace(ParkingSpace space) throws ParkingException {
        lot.addSpace(space);
    }

    public int getNextSpaceNumber() {
        return lot.getNextNumber();
    }

    public List<ParkingTicket> getTicketsOf(Driver driver) {
        List<ParkingTicket> result = new ArrayList<>();
        for (ParkingTicket ticket : tickets) {
            if (ticket.getDriver().getId() == driver.getId()) {
                result.add(ticket);
            }
        }
        return result;
    }

    public List<ParkingTicket> getActiveTicketsOf(Driver driver) {
        List<ParkingTicket> result = new ArrayList<>();
        for (ParkingTicket ticket : getTicketsOf(driver)) {
            if (ticket.isActive()) {
                result.add(ticket);
            }
        }
        return result;
    }

    public List<ParkingTicket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public double getTotalIncome() {
        double total = 0;
        for (ParkingTicket ticket : tickets) {
            if (!ticket.isActive()) {
                total += ticket.getPayment().getAmount();
            }
        }
        return total;
    }

    private boolean isCarParked(Car car) {
        for (ParkingTicket ticket : tickets) {
            if (ticket.isActive() && ticket.getCar().getPlateNumber().equals(car.getPlateNumber())) {
                return true;
            }
        }
        return false;
    }

    private ParkingTicket findActiveTicket(Driver driver, int ticketId) throws ParkingException {
        for (ParkingTicket ticket : getActiveTicketsOf(driver)) {
            if (ticket.getId() == ticketId) {
                return ticket;
            }
        }
        throw new ParkingException("Белсенді билет №" + ticketId + " табылмады!");
    }
}
