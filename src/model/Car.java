package model;

import exception.ParkingException;

public class Car {
    private String plateNumber;
    private String brand;
    private String color;

    public Car(String plateNumber, String brand, String color) throws ParkingException {
        if (plateNumber == null || plateNumber.isEmpty()) {
            throw new ParkingException("Көлік нөмірі бос болмауы керек!");
        }
        if (brand == null || brand.isEmpty()) {
            throw new ParkingException("Көлік маркасы бос болмауы керек!");
        }
        this.plateNumber = plateNumber.toUpperCase();
        this.brand = brand;
        this.color = color;
    }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber.toUpperCase(); }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    @Override
    public String toString() {
        return brand + " | " + plateNumber + " | " + color;
    }
}
