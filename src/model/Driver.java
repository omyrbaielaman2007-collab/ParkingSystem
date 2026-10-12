package model;

import exception.ParkingException;

import java.util.ArrayList;
import java.util.List;

public class Driver extends User {
    private final List<Car> cars = new ArrayList<>();

    public Driver(String username, String password, String fullName) {
        super(username, password, fullName);
    }

    public void addCar(Car car) throws ParkingException {
        for (Car existing : cars) {
            if (existing.getPlateNumber().equalsIgnoreCase(car.getPlateNumber())) {
                throw new ParkingException("Бұл нөмірлі көлік бұрын қосылған: " + car.getPlateNumber());
            }
        }
        cars.add(car);
    }

    public Car getCar(int index) throws ParkingException {
        if (index < 1 || index > cars.size()) {
            throw new ParkingException("Көлік нөмірі қате: " + index);
        }
        return cars.get(index - 1);
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    @Override
    public String getRole() {
        return "Жүргізуші";
    }
}
