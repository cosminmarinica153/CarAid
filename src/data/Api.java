package data;

import obj.*;

import java.util.ArrayList;

// This class is responsible for different querying methods that
// allow us to manipulate the data
public final class Api {
    // We start by loading the data to the api
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
        // Before we save the object data we want to update the api object array
        Owner[] newOwners = new Owner[owners.length + 1];

        System.arraycopy(owners, 0, newOwners, 0, owners.length);
        newOwners[newOwners.length - 1] = owner;

        owners = newOwners;

        return Data.saveOwnerData(owner);
    }

    public static boolean addCar(Car car) {
        // Before we save the object data we want to update the api object array
        Car[] newCars = new Car[cars.length + 1];

        System.arraycopy(cars, 0, newCars, 0, cars.length);
        newCars[newCars.length - 1] = car;

        cars = newCars;

        return Data.saveCarData(car);
    }

    // PUT REQUESTS
    public static boolean updateOwner(Owner updatedOwner) {
        // When we find the Owner that has the same id as the updated owner
        // we overwrite that position so we can update the owners array
        for (int i = 0; i < owners.length; i++) {
            if (updatedOwner.getId() == owners[i].getId())
                owners[i] = updatedOwner;
        }

        return Data.updateOwnerData(owners);
    }

    public static boolean updateCar(Car updatedCar) {
        // When we find the Car that has the same id as the updated car
        // we overwrite that position so we can update the cars array
        for (int i = 0; i < cars.length; i++) {
            if (updatedCar.getId() == cars[i].getId())
                cars[i] = updatedCar;
        }

        return Data.updateCarData(cars);
    }

    // DELETE REQUESTS
    public static boolean deleteOwner(int ownerId) {
        Owner[] newOwners = new Owner[owners.length - 1];

        // We copy the owners array into the newOwners, and we skip
        // the owner that we want to delete
        for (int i = 0; i < owners.length; i++) {
            if (ownerId != owners[i].getId()) {
                newOwners[i] = owners[i];
            }
        }

        // We need to update the owners array
        owners = newOwners;

        return Data.updateOwnerData(owners);
    }

    public static boolean deleteCar(int carId) {
        Car[] newCars = new Car[cars.length - 1];

        // We copy the owners array into the newCars, and we skip
        // the car that we want to delete
        for (int i = 0; i < cars.length; i++) {
            if (carId != cars[i].getId()) {
                newCars[i] = cars[i];
                cars[i] = null;
            }
        }

        // We need to update the cars array
        cars = newCars;

        return Data.updateCarData(cars);
    }
}
