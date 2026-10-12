package service;

import exception.ParkingException;
import model.ParkingSpace;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final String name;
    private final List<ParkingSpace> spaces = new ArrayList<>();

    public ParkingLot(String name) {
        this.name = name;
    }

    public void addSpace(ParkingSpace space) throws ParkingException {
        if (findByNumber(space.getNumber()) != null) {
            throw new ParkingException("Орын №" + space.getNumber() + " бұрын қосылған!");
        }
        spaces.add(space);
    }

    public ParkingSpace findByNumber(int number) {
        for (ParkingSpace space : spaces) {
            if (space.getNumber() == number) {
                return space;
            }
        }
        return null;
    }

    public List<ParkingSpace> getFreeSpaces() {
        List<ParkingSpace> free = new ArrayList<>();
        for (ParkingSpace space : spaces) {
            if (!space.isOccupied()) {
                free.add(space);
            }
        }
        return free;
    }

    public List<ParkingSpace> getAllSpaces() {
        return new ArrayList<>(spaces);
    }

    public int getNextNumber() {
        return spaces.size() + 1;
    }

    public String getName() { return name; }
}
