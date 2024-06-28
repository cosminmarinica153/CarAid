import data.Api;
import obj.*;

import java.util.Scanner;

public final class App {
    private final static Scanner input = new Scanner(System.in);

    // Set the current owner and car to a default value
    // This way we will initiate the Api.owners and Api.cars
    private static Owner currentOwner = (Api.owners.length) > 0 ? Api.owners[0] : null;
    private static Car currentCar = (Api.cars.length) > 0 ? Api.cars[0] : null;

    public static void run(){
        mainMenu();
    }

    // Menu dialogs
    public static void mainMenu() {
        while (true) {
            System.out.println("Welcome to CarAid!");
            System.out.println("1. Choose owner");
            System.out.println("2. Create new owner");
            System.out.println("3. Exit");

            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    chooseOwner();
                    break;
                case 2:
                    createNewOwner();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void ownerMenu() {
        while (true) {
            System.out.println("Welcome " + currentOwner.getFirst_name() + "!");
            System.out.println("What would you like to do?");

            System.out.println("1. View my details");
            System.out.println("2. View my cars");
            System.out.println("3. View alerts");
            System.out.println("4. Drive");
            System.out.println("5. Maintenance");
            System.out.println("6. Dealership");
            System.out.println("7. Mechanic");
            System.out.println("8. Return to main menu");
            System.out.println("9. Delete owner");
            System.out.println("10. Update owner");

            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    viewOwner();
                    break;
                case 2:
                    viewOwnerCars();
                    break;
                case 3:
                    viewAlerts();
                    break;
                case 4:
                    driveCar();
                    break;
                case 5:
                    viewMaintenance();
                    break;
                case 6:
                    dealerShipMenu();
                    break;
                case 7:
                    mechanicMenu();
                    break;
                case 8:
                    return;
                case 9:
                    if (deleteOwner().equals("main_menu"))
                        return;
                    break;
                case 10:
                    updateOwner();
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }

    public static void dealerShipMenu() {
        while (true) {
            System.out.println("Welcome to best-cars.ro!");
            System.out.println("What are you looking for?");
            System.out.println("1. I would like to buy a car");
            System.out.println("2. I would like to sell a car");
            System.out.println("3. I would like to leave");
            System.out.println("4. Create car");
            System.out.println("5. Delete car");

            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    buyCar();
                    break;
                case 2:
                    sellCar();
                    break;
                case 3:
                    return;
                case 4:
                    createNewCar();
                    break;
                case 5:
                    deleteCar();
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static void mechanicMenu(){
        System.out.println("Welcome to SecretWeapon!");
        System.out.println("Which car would you like to work on?");

        chooseCar();
        while (true) {
            System.out.println("What would you like to do to your " + currentCar.getMake() + " " +
                    currentCar.getModel() + " " + currentCar.getYear() + "?");
            System.out.println("1. Engine oil change");
            System.out.println("2. Transmission oil change");
            System.out.println("3. Brake pads change");
            System.out.println("4. Brake fluid change");
            System.out.println("5. Choose another car");
            System.out.println("6. Leave");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    engineOilChange();
                    break;
                case 2:
                    transmissionOilChange();
                    break;
                case 3:
                    brakePadsChange();
                    break;
                case 4:
                    brakeFluidChange();
                    break;
                case 5:
                    mechanicMenu();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    // Data manipulation dialogs
    public static void createNewOwner() {
        System.out.println("Hello newcomer!\n");

        System.out.println("Please provide your first name: ");
        String firstName = input.nextLine();

        System.out.println("Please provide your last name: ");
        String lastName = input.nextLine();

        System.out.println("Please provide your age: ");
        int age = input.nextInt();
        input.nextLine();

        Owner newOwner = new Owner(firstName, lastName, age);

        if (!Api.addOwner(newOwner))
            System.out.println("Something went wrong!");
    }

    public static void createNewCar() {
        System.out.println("Welcome to new car creation, please follow the instructions.");

        System.out.println("Provide the make of the car: ");
        String make = input.nextLine();

        System.out.println("Provide the model of the car: ");
        String model = input.nextLine();

        System.out.println("Provide the year of the car: ");
        int year = input.nextInt();

        System.out.println("Provide the kilometers of the car: ");
        int kilometers = input.nextInt();
        input.nextLine();

        Revisions revisions = new Revisions();

        Car car = new Car(0, make, model, year, kilometers, revisions);

        if (!Api.addCar(car))
            System.out.println("Something went wrong!");
    }

    public static void updateOwner() {
        while (true) {
            System.out.println("What would you like to update?");
            System.out.println("1. My first name");
            System.out.println("2. My last name");
            System.out.println("3. My age");
            System.out.println("4. Go back");

            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Please provide your new first name: ");
                    currentOwner.setFirst_name(input.nextLine());
                    break;
                case 2:
                    System.out.println("Please provide your new last name: ");
                    currentOwner.setLast_name(input.nextLine());
                    break;
                case 3:
                    System.out.println("Please provide your new age: ");
                    currentOwner.setAge(input.nextInt());
                    break;
                case 4:
                    if (!Api.updateOwner(currentOwner))
                        System.out.println("Something went wrong! Please try again!");
                    else
                        return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static String deleteOwner() {
        System.out.println("Are you certain you want to delete your profile? (Y/n)");

        String ans = input.nextLine();

        // If we delete the user we return to main manu to choose another one
        if (ans.equalsIgnoreCase("y")) {
            if (!Api.deleteOwner(currentOwner.getId()))
                System.out.println("Something went wrong!");

            return "main_menu";
        } else
            return "";
    }

    public static void deleteCar() {
        System.out.println("Which care would you like to delete?");

        int i;
        for (i = 0; i < Api.cars.length; i++) {
            System.out.println((i + 1) + ". " + Api.cars[i].getMake() + " " + Api.cars[i].getModel() + " " + Api.cars[i].getYear());
        }

        int id = input.nextInt();
        input.nextLine();

        if (id > Api.cars.length) {
            System.out.println("The car selected does not exist!");
            deleteCar();
        }

        if (!Api.deleteCar(id))
            System.out.println("Something went wrong!");
    }

    // Set Current dialogs
    public static void chooseOwner() {
        if (Api.owners.length == 0) {
            System.out.println("There are no owners!\n");
            return;
        }

        int i;
        for (i = 0; i < Api.owners.length; i++) {
            System.out.println((i + 1) + ". " + Api.owners[i].getFirst_name() + " " + Api.owners[i].getLast_name());
        }

        int id = input.nextInt();
        input.nextLine();

        if (id > Api.owners.length) {
            System.out.println("The user selected does not exist!");
            chooseOwner();
        }
        currentOwner = Api.owners[id - 1];

        ownerMenu();
    }

    public static void chooseCar() {
        if (Api.owners.length == 0)
            System.out.println("You don't own any cars!\n");

        Car[] ownerCars = Api.getOwnerCars(currentOwner.getId());

        int i;
        for (i = 0; i < ownerCars.length; i++) {
            System.out.println((i + 1) + ". " + ownerCars[i].getMake() + " " + ownerCars[i].getModel() + " " + ownerCars[i].getYear());
        }

        int id = input.nextInt();
        input.nextLine();

        if (id > ownerCars.length) {
            System.out.println("The car selected does not exist!");
            chooseCar();
        }

        currentCar = ownerCars[id - 1];
    }

    // Views dialogs
    public static void viewOwner() {
        System.out.println("Name: " + currentOwner.getFirst_name() + " " + currentOwner.getLast_name());
        System.out.println("Age: " + currentOwner.getAge());
    }

    public static void viewOwnerCars() {
        if (Api.getOwnerCars(currentOwner.getId()).length == 0) {
            System.out.println("You don't have any cars");
            return;
        }

        Car[] ownerCars = Api.getOwnerCars(currentOwner.getId());

        for (Car car : ownerCars)
            System.out.println(car.getMake() + " " + car.getModel() + " " + car.getYear());
    }

    public static void viewAlerts() {
        if (Api.getOwnerCars(currentOwner.getId()).length == 0) {
            System.out.println("You don't have any alerts");
            return;
        }

        Car[] ownerCars = Api.getOwnerCars(currentOwner.getId());

        int last;
        for (Car car : ownerCars) {
            int carKm = car.getKilometers();
            boolean alert = false;
            System.out.println("For your " + car.getMake() + " " + car.getModel() + " " + car.getYear() + " with " +
                    car.getKilometers() + " km");

            last = car.getLastOilChange();
            if (carKm - last < 2000) {
                alert = true;
                System.out.println("Next engine oil change due in " + (carKm - last) + " km");
            } else if (carKm - last > Revisions.ENGINE_OIL) {
                alert = true;
                System.out.println("!!! --- You missed your engine oil change by " +
                        (carKm - last + Revisions.ENGINE_OIL) + " km --- !!!");
            }

            last = car.getLastTransmissionOilChange();
            if (carKm - last < 2000) {
                alert = true;
                System.out.println("Next transmission oil change due in " + (carKm - last) + " km");
            } else if (carKm - last > Revisions.TRANSMISSION_OIL) {
                alert = true;
                System.out.println("!!! --- You missed your transmission oil change by " +
                        (carKm - last + Revisions.TRANSMISSION_OIL) + " km --- !!!");
            }

            last = car.getLastBreakPadsChange();
            if (carKm - last < 2000) {
                alert = true;
                System.out.println("Next break pads change due in " + (carKm - last) + " km");
            } else if (carKm - last > Revisions.BRAKE_PADS) {
                alert = true;
                System.out.println("!!! --- You missed your brake pads change by " +
                        (carKm - last + Revisions.BRAKE_PADS) + " km --- !!!");
            }

            last = car.getLastBrakeFluidChange();
            if (carKm - last < 2000) {
                alert = true;
                System.out.println("Next brake fluid change due in " + (carKm - last) + " km");
            } else if (carKm - last > Revisions.BRAKE_FLUID) {
                alert = true;
                System.out.println("!!! --- You missed your brake fluid change by " +
                        (carKm - last + Revisions.BRAKE_FLUID) + " km --- !!!");
            }

            if (!alert)
                System.out.println("You do not have any alerts\n");
        }

    }

    public static void viewMaintenance() {
        if (Api.getOwnerCars(currentOwner.getId()).length == 0) {
            System.out.println("You don't have any cars");
            return;
        }

        System.out.println("Please choose the car you want to check.");

        chooseCar();

        Revisions revisions = currentCar.getRevisions();

        System.out.println("For your " + currentCar.getMake() + " " + currentCar.getModel() + " " +
                currentCar.getYear() + " that has " + currentCar.getKilometers() + " km");

        System.out.println("Your last oil change was " +
                revisions.getEngineOil()[revisions.getEngineOil().length - 1]);
        System.out.println("Your last transmission oil change was " +
                revisions.getTransmissionOil()[revisions.getTransmissionOil().length - 1]);
        System.out.println("Your last break pads change was " +
                revisions.getBrakePads()[revisions.getBrakePads().length - 1]);
        System.out.println("Your last break fluid change was " +
                revisions.getBrakeFluid()[revisions.getBrakeFluid().length - 1]);

        System.out.println();
        System.out.println("Would you like more details? (Y/n)");
        String ans = input.nextLine();

        if (ans.equalsIgnoreCase("y"))
            viewCarRevisions();
    }

    public static void viewCarRevisions() {
        Revisions revisions = currentCar.getRevisions();
        int[] changes;

        changes = revisions.getEngineOil();
        System.out.print("Engine oil was changed at: ");
        for (int change : changes)
            System.out.print(change + " km, ");
        System.out.println();

        changes = revisions.getTransmissionOil();
        System.out.print("Transmission oil was changed at: ");
        for (int change : changes)
            System.out.print(change + " km, ");
        System.out.println();

        changes = revisions.getBrakePads();
        System.out.print("Brake pads were changed at: ");
        for (int change : changes)
            System.out.print(change + " km, ");
        System.out.println();

        changes = revisions.getBrakeFluid();
        System.out.print("Brake fluid was changed at: ");
        for (int change : changes)
            System.out.print(change + " km, ");
        System.out.println();
    }

    // DealerShip related dialogs
    public static void buyCar() {
        System.out.println("Which car would you like to see?");

        int i;
        for (i = 0; i < Api.cars.length; i++) {
            System.out.println((i + 1) + ". " + Api.cars[i].getMake() + " " + Api.cars[i].getModel() + " " + Api.cars[i].getYear());
        }

        int id = input.nextInt();
        input.nextLine();

        if (id > Api.cars.length) {
            System.out.println("The car selected does not exist!");
            deleteCar();
        }

        currentCar = Api.getCar(id);
        viewCarRevisions();

        System.out.println("\n Would you like to buy this one? (Y/n)");

        String ans = input.nextLine();

        if (ans.equalsIgnoreCase("y")) {
            currentCar.setOwnerId(currentOwner.getId());
            if (!Api.updateCar(currentCar))
                System.out.println("Something went wrong!");
        }
    }

    public static void sellCar() {
        System.out.println("Which care would you like to sell?");

        chooseCar();

        System.out.println("You are about to sell your " + currentCar.getMake() + " " + currentCar.getModel() + " " + currentCar.getYear());
        System.out.println("Are you certain you want to proceed? (Y/n)");

        String ans = input.nextLine();

        if (ans.equalsIgnoreCase("y")) {
            if (!Api.deleteCar(currentCar.getId()))
                System.out.println("Something went wrong!");
        }
    }

    public static void driveCar() {
        if (Api.getOwnerCars(currentOwner.getId()).length == 0) {
            System.out.println("You don't have any cars");
            return;
        }

        chooseCar();

        System.out.println("How much would you like to drive? (km)");
        int km = input.nextInt();
        input.nextLine();

        currentCar.setKilometers(currentCar.getKilometers() + km);

        if (!Api.updateCar(currentCar))
            System.out.println("Your car could not start");
    }

    // Mechanic related dialog
    public static void engineOilChange(){
        currentCar.addEngineOilChange(currentCar.getKilometers());

        System.out.println("The revision was successful");
    }

    public static void transmissionOilChange(){
        currentCar.addTransmissionOilChange(currentCar.getKilometers());

        System.out.println("The revision was successful");
    }

    public static void brakePadsChange(){
        currentCar.addBrakePadsChange(currentCar.getKilometers());

        System.out.println("The revision was successful");
    }

    public static void brakeFluidChange(){
        currentCar.addBrakeFluidChange(currentCar.getKilometers());

        System.out.println("The revision was successful");
    }
}
