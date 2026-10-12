package model;

public class StandardSpace extends ParkingSpace {
    public StandardSpace(int number) {
        super(number);
    }

    @Override
    public double getHourlyRate() {
        return 200;
    }

    @Override
    public String getTypeName() {
        return "Стандарт";
    }
}
