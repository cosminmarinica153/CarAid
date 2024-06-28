package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import obj.*;

// This class consists of methods that allow direct access to the data
// We will use the Data class to read and write to the files that hold the app data
// in ways that allow us to load, save, or update the data
public final class Data {
    // Load methods
    public static Owner[] loadOwners() {
        ArrayList<Owner> owners = new ArrayList<>();

        try {
            // Initiate the File and Scanner objects so we can access the contents of the file
            File myObj = new File("data/owners.txt");
            Scanner myReader = new Scanner(myObj);

            // Adding the contents of the file in data, separated by a new line
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                data += "\n";
            }
            myReader.close();

            // If the file is empty we can return an empty owner array
            if (data.isEmpty())
                return new Owner[0];

            // We will transfer the data to an array where each index holds an object
            String[] formatted = data.split("\n");

            for (String line : formatted) {
                // We will call the Owner object .toOwner method to parse it from a string to a valid object
                owners.add(Owner.toOwner(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            return new Owner[0];
        }

        return owners.toArray(new Owner[owners.size()]);
    }

    public static Car[] loadCars() {
        ArrayList<Car> cars = new ArrayList<>();

        try {
            // Initiate the File and Scanner objects so we can access the contents of the file
            File myObj = new File("data/cars.txt");
            Scanner myReader = new Scanner(myObj);

            // Adding the contents of the file in data, separated by a new line
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
                data += "\n";
            }
            myReader.close();

            // If the file is empty we can return an empty car array
            if (data.isEmpty())
                return new Car[0];

            // We will transfer the data to an array where each index holds an object
            String[] formatted = data.split("\n");

            for (String line : formatted) {
                // We will call the Car object .toCar method to parse it from a string to a valid object
                cars.add(Car.toCar(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        return cars.toArray(new Car[cars.size()]);
    }

    // Save methods
    public static boolean saveOwnerData(Owner owner) {
        try {
            // Initiate the FileWriter object, so we can write to the file
            // To save owner data we don't want to overwrite the whole file, so we set the optional
            // parameter 'append' to true
            FileWriter myWriter = new FileWriter("data/owners.txt", true);

            // Write the owner object to the file and insert a new line
            // Note that we call the .toString method before writing
            myWriter.write(owner.toString());
            myWriter.write("\n");

            myWriter.close();
        } catch (IOException e) {
            // If we fail to write to the file we will print the error and return false
            // This way we can tell if the process was successful or not
            System.out.println("An error occurred while saving owner data.");
            return false;
        }

        return true;
    }

    public static boolean saveCarData(Car car) {
        try {
            // Initiate the FileWriter object, so we can write to the file
            // To save car data we don't want to overwrite the whole file, so we set the optional
            // parameter 'append' to true
            FileWriter myWriter = new FileWriter("data/cars.txt", true);

            // Write the car object to the file and insert a new line
            // Note that we call the .toString method before writing
            myWriter.write(car.toString());
            myWriter.write("\n");

            myWriter.close();
        } catch (IOException e) {
            // If we fail to write to the file we will print the error and return false
            // This way we can tell if the process was successful or not
            System.out.println("An error occurred while saving owner data.");
            return false;
        }

        return true;
    }

    // Update methods
    public static boolean updateOwnerData(Owner[] owners) {
        try {
            // Initiate the FileWriter object, so we can write to the file
            // To update owner data we want to overwrite the whole file with an
            // array containing the updated values, to overwrite we leave
            // the parameter 'append' to default (false)
            FileWriter myWriter = new FileWriter("data/owners.txt");

            // Rewrite the object array to file
            for (Owner owner : owners) {
                myWriter.write(owner.toString());
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            // If we fail to write to the file we will print the error and return false
            // This way we can tell if the process was successful or not
            System.out.println("An error occurred while saving owner data.");
            return false;
        }

        return true;
    }

    public static boolean updateCarData(Car[] cars) {
        try {
            // Initiate the FileWriter object, so we can write to the file
            // To update car data we want to overwrite the whole file with an
            // array containing the updated values, to overwrite we leave
            // the parameter 'append' to default (false)
            FileWriter myWriter = new FileWriter("data/cars.txt");

            // Rewrite the object array to file
            for (Car car : cars) {
                myWriter.write(car.toString());
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving owner data.");
            // If we fail to write to the file we will print the error and return false
            // This way we can tell if the process was successful or not
            return false;
        }

        return true;
    }
}
