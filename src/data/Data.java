package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import obj.*;

public final class Data {
    // Methods to load data
    public static Owner[] loadOwners() {
        ArrayList<Owner> owners = new ArrayList<>();

        try {
            File myObj = new File("data/owners.txt");
            Scanner myReader = new Scanner(myObj);

            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                data += "\n";
            }
            myReader.close();

            // If the file is empty we can return an empty owner array
            if (data.isEmpty())
                return new Owner[0];

            String[] formatted = data.split("\n");

            for (String line : formatted) {
                owners.add(Owner.toOwner(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        return owners.toArray(new Owner[owners.size()]);
    }

    public static Car[] loadCars() {
        ArrayList<Car> cars = new ArrayList<>();

        try {
            File myObj = new File("data/cars.txt");
            Scanner myReader = new Scanner(myObj);

            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                data += "\n";
            }
            myReader.close();

            // If the file is empty we can return an empty car array
            if (data.isEmpty())
                return new Car[0];

            String[] formatted = data.split("\n");

            for (String line : formatted) {
                cars.add(Car.toCar(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        return cars.toArray(new Car[cars.size()]);
    }

    // Methods to save data
    public static boolean saveOwnerData(Owner owner) {
        try {
            FileWriter myWriter = new FileWriter("data/owners.txt", true);
            myWriter.write(owner.toString());
            myWriter.write("\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving owner data.");
            return false;
        }

        return true;
    }

    public static boolean saveCarData(Car car) {
        try {
            FileWriter myWriter = new FileWriter("data/cars.txt", true);
            myWriter.write(car.toString());
            myWriter.write("\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving owner data.");
            return false;
        }

        return true;
    }

    // Methods to update data
    public static boolean updateOwnerData(Owner[] owners) {
        try {
            FileWriter myWriter = new FileWriter("data/owners.txt");
            for (Owner owner : owners) {
                myWriter.write(owner.toString());
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving owner data.");
            return false;
        }

        return true;
    }

    public static boolean updateCarData(Car[] cars) {
        try {
            FileWriter myWriter = new FileWriter("data/cars.txt");
            for (Car car : cars) {
                myWriter.write(car.toString());
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving owner data.");
            return false;
        }

        return true;
    }
}
