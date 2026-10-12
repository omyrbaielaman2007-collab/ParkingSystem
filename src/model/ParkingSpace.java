package model;

public abstract class ParkingSpace {
    private final int number;
    private boolean occupied;

    public ParkingSpace(int number) {
        this.number = number;
    }

    public abstract double getHourlyRate();

    public abstract String getTypeName();

    public void occupy() { occupied = true; }

    public void release() { occupied = false; }

    public int getNumber() { return number; }

    public boolean isOccupied() { return occupied; }

    @Override
    public String toString() {
        return String.format("№%d | %-9s | %.0f тг/сағ | %s",
                number, getTypeName(), getHourlyRate(), occupied ? "бос емес" : "бос");
    }
}
