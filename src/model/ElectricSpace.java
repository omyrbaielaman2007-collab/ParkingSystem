package model;

public class ElectricSpace extends ParkingSpace {
    public ElectricSpace(int number) {
        super(number);
    }

    @Override
    public double getHourlyRate() {
        return 350;
    }

    @Override
    public String getTypeName() {
        return "Электро";
    }
}
