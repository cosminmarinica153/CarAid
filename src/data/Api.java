package data;

import obj.*;

import java.util.ArrayList;

public final class Api {
    public static Car[] cars = Data.loadCars();
    public static Owner[] owners = Data.loadOwners();

    // GET REQUESTS
    public static Car[] getOwnerCars(int ownerId) {
        ArrayList<Car> query = new ArrayList<>();

        for (Car car : cars) {
            if (car.getOwnerId() == ownerId)
                query.add(car);
        }

        return query.toArray(new Car[query.size()]);
    }

    public static Car getCar(int id) {
        for (Car car : cars) {
            if (car.getId() == id)
                return car;
        }

        return null;
    }

    // POST REQUESTS
    public static boolean addOwner(Owner owner) {
        Owner[] newOwners = new Owner[owners.length + 1];

        System.arraycopy(owners, 0, newOwners, 0, owners.length);
        newOwners[newOwners.length - 1] = owner;

        owners = newOwners;
        return Data.saveOwnerData(owner);
    }

    public static boolean addCar(Car car) {
        return Data.saveCarData(car);
    }

    // PUT REQUESTS
    public static boolean updateOwner(Owner owner) {
        for (int i = 0; i < owners.length; i++) {
            if (owner.getId() == owners[i].getId())
                owners[i] = owner;
        }

        return Data.updateOwnerData(owners);
    }

    public static boolean updateCar(Car car) {
        for (int i = 0; i < cars.length; i++) {
            if (car.getId() == cars[i].getId())
                cars[i] = car;
        }

        return Data.updateCarData(cars);
    }

    // DELETE REQUESTS
    public static boolean deleteOwner(int ownerId) {
        Owner[] newOwners = new Owner[owners.length - 1];


        for (int i = 0; i < owners.length; i++) {
            if (ownerId != owners[i].getId()) {
                newOwners[i] = owners[i];
                owners[i] = null;
            }
        }

        owners = newOwners;

        return Data.updateOwnerData(owners);
    }

    public static boolean deleteCar(int carId) {
        Car[] newCars = new Car[cars.length - 1];

        for (int i = 0; i < cars.length; i++) {
            if (carId != cars[i].getId()) {
                newCars[i] = cars[i];
                cars[i] = null;
            }
        }

        cars = newCars;

        return Data.updateCarData(cars);
    }
}
