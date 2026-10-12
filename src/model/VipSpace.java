package model;

public class VipSpace extends ParkingSpace {
    public VipSpace(int number) {
        super(number);
    }

    @Override
    public double getHourlyRate() {
        return 500;
    }

    @Override
    public String getTypeName() {
        return "VIP";
    }
}
