package data;
import obj.*;

import java.util.ArrayList;

public final class Api {
    public static Car[] cars = Data.loadCars();
    public static Owner[] owners = Data.loadOwners();

    // GET REQUESTS
    public static Owner getOwner(int id) {
        return owners[id - 1];
    }

    public static Car[] getOwnerCars(int ownerId){
        ArrayList<Car> query = new ArrayList<Car>();

        for(Car car : cars){
            if(car.getOwnerId() == ownerId)
                query.add(car);
        }

        return query.toArray(new Car[query.size()]);
    }

    public static Car getCar(int id) {
        return cars[id - 1];
    }

    public static Revisions getCarRevisions(int carId) {
        return cars[carId - 1].getRevisions();
    }

    // POST REQUESTS
    public static boolean addOwner(Owner owner){
        Owner[] newOwners = new Owner[owners.length + 1];

        System.arraycopy(owners, 0, newOwners, 0, owners.length);
        newOwners[newOwners.length - 1] = owner;

        owners = newOwners;
        return Data.saveOwnerData(owner);
    }

    public static boolean addCar(Car car){
        return Data.saveCarData(car);
    }

    // PUT REQUESTS
    public static boolean updateOwner(Owner owner){
        return Data.updateOwnerData(owner);
    }

    public static boolean updateCar(Car car){
        return Data.updateCarData(car);
    }

    // DELETE REQUESTS
    public static boolean deleteOwner(int ownerId){
        return Data.deleteOwnerData(getOwner(ownerId));
    }

    public static boolean deleteCar(int carId){
        return Data.deleteCarData(getCar(carId));
    }
}
