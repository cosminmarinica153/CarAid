package util;

import data.Api;
import obj.*;

import java.util.Scanner;

public final class UI {
    private static Scanner input = new Scanner(System.in);

    private static Owner currentOwner;
    private static Car currentCar;

    public static String mainMenu() {
        while (true) {
            System.out.println("Welcome to CarAid!");
            System.out.println("1. Choose owner");
            System.out.println("2. Create new owner");
            System.out.println("3. Exit");

            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    return "choose_owner";
                case 2:
                    return "create_owner";
                case 3:
                    return "exit";
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public static String ownerMenu() {
        while (true) {
            System.out.println("Welcome " + currentOwner.getFirst_name() + "!");
            System.out.println("What would you like to do?");

            System.out.println("1. View my details");
            System.out.println("2. View my cars");
            System.out.println("3. View alerts");
            System.out.println("4. Drive");
            System.out.println("5. Maintenance");
            System.out.println("6. Dealership");
            System.out.println("7. Return to main menu");
            System.out.println("8. Delete owner");
            System.out.println("9. Update owner");

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
                    showMaintenance();
                    break;
                case 6:
                    dealerShipMenu();
                    break;
                case 7:
                    return "main_menu";
                case 8:
                    if(deleteOwner().equals("main_menu"))
                        return "main_menu";
                    break;
                case 9:
                    updateOwner();
                default:
                    System.out.println("Invalid choice");
                    return ownerMenu();
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

    // Dialog for interacting with the owner
    public static String chooseOwner() {
        if (Api.owners.length == 0) {
            System.out.println("There are no owners!\n");
            return "main_menu";
        }

        int idx = 1;
        for (Owner owner : Api.owners) {
            System.out.println(idx++ + ". " + owner.getFirst_name() + " " + owner.getLast_name());
        }

        int id = input.nextInt();

        if (id >= idx) {
            System.out.println("The user selected does not exist!");
            return chooseOwner();
        }
        currentOwner = Api.getOwner(id);

        return "owner_menu";
    }

    public static String createNewOwner() {
        System.out.println("Hello newcomer!\n");

        System.out.println("Please provide your first name: ");
        String firstName = input.nextLine();

        System.out.println("Please provide your last name: ");
        String lastName = input.nextLine();

        System.out.println("Please provide your age: ");
        int age = input.nextInt();

        Owner newOwner = new Owner(firstName, lastName, age);

        if (!Api.addOwner(newOwner))
            System.out.println("Something went wrong!");

        input.nextLine();
        return "main_menu";
    }

    public static void updateOwner() {
        while(true){
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
                    if(!Api.updateOwner(currentOwner))
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

        if (ans.equalsIgnoreCase("y")) {
            if(!Api.deleteOwner(currentOwner.getId()))
                System.out.println("Something went wrong!");

            return "main_menu";
        }else
            return "";
    }

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

        int next;
        for (Car car : ownerCars) {
            boolean alert = false;
            System.out.println("For your " + car.getMake() + " " + car.getModel() + " " + car.getYear() + " ");

            next = car.getNextOilChange();
            if (next < 2000) {
                alert = true;
                System.out.println("Next engine oil change due in " + next + " km");
            }

            next = car.getNextTransmissionOilChange();
            if (next < 2000) {
                alert = true;
                System.out.println("Next transmission oil change due in " + next + " km");
            }

            next = car.getNextBreakPadsChange();
            if (next < 2000) {
                alert = true;
                System.out.println("Next brake pad change due in " + next + " km");
            }

            next = car.getNextBrakeFluidChange();
            if (next < 2000) {
                alert = true;
                System.out.println("Next brake fluid change due in " + next + " km");
            }

            if (!alert)
                System.out.println("You do not have any alerts\n");
        }

    }

    // Dialog for interacting with the car
    public static void chooseCar() {
        if (Api.owners.length == 0)
            System.out.println("You don't own any cars!\n");

        Car[] ownerCars = Api.getOwnerCars(currentOwner.getId());

        int idx = 1;
        for (Car car : ownerCars) {
            System.out.println(idx++ + ". " + car.getMake() + " " + car.getModel() + " " + car.getYear());
        }

        int id = input.nextInt();

        if (id >= idx) {
            System.out.println("The car selected does not exist!");
            chooseCar();
        }

        currentCar = Api.getOwnerCars(currentOwner.getId())[id - 1];
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

        Revisions revisions = new Revisions();

        Car car = new Car(0 ,make, model, year, kilometers, revisions);

        Api.addCar(car);
    }

    public static void deleteCar() {
        System.out.println("Which care would you like to delete?");

        int idx = 1;

        for(Car car : Api.cars)
            System.out.println(idx++ + ". " + car.getMake() + " " + car.getModel() + " " + car.getYear());

        int id = input.nextInt();
        if (id >= idx) {
            System.out.println("The car selected does not exist!");
        }

        if(!Api.deleteCar(id))
            System.out.println("Something went wrong!");
    }

    // Dialog for interacting with user cars
    public static void buyCar() {
        System.out.println("Which car would you like to see?");

        int idx = 1;
        for (Car car : Api.cars)
            System.out.println(idx++ + ". " + car.getMake() + " " + car.getModel() + " " + car.getYear());

        int id = input.nextInt();

        if (id >= idx) {
            System.out.println("The user selected does not exist!");
        }

        currentCar = Api.getCar(id);
        showCarRevisions();

        System.out.println("\n Would you like to buy this one? (Y/n)");

        String ans = input.nextLine();

        if (ans.equalsIgnoreCase("y")) {
            currentCar.setOwnerId(currentOwner.getId());
            if(!Api.updateCar(currentCar))
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
            if(!Api.deleteCar(currentCar.getId()))
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

        currentCar.setKilometers(currentCar.getKilometers() + km);

        if (!Api.updateCar(currentCar))
            System.out.println("Your car could not start");
    }

    public static void showMaintenance() {
        if (Api.getOwnerCars(currentOwner.getId()).length == 0) {
            System.out.println("You don't have any cars");
            return;
        }

        chooseCar();

        Revisions revisions = currentCar.getRevisions();

        System.out.println("For your " + currentCar.getMake() + " " + currentCar.getModel() + " " + currentCar.getYear());

        System.out.println("Your last oil change was " + revisions.getEngineOil()[revisions.getEngineOil().length - 1]);
        System.out.println("Your last transmission oil change was " + revisions.getTransmissionOil()[revisions.getTransmissionOil().length - 1]);
        System.out.println("Your last break pads change was " + revisions.getBrakePads()[revisions.getBrakePads().length - 1]);
        System.out.println("Your last break fluid change was " + revisions.getBrakeFluid()[revisions.getBrakeFluid().length - 1]);

        System.out.println();
        System.out.println("Would you like more details? (Y/n)");
        String ans = input.nextLine();

        if (ans.equalsIgnoreCase("y"))
            showCarRevisions();
    }

    // Dialog for interacting with the car revisions
    public static void showCarRevisions() {
        Revisions revisions = currentCar.getRevisions();
        int[] changes;

        changes = currentCar.getRevisions().getEngineOil();
        System.out.print("Engine oil was changed at: ");
        for (int i = 0; i < changes.length; i++)
            System.out.print(changes[i] + " km, ");
        System.out.println();

        changes = currentCar.getRevisions().getTransmissionOil();
        System.out.print("Transmission oil was changed at: ");
        for (int i = 0; i < changes.length; i++)
            System.out.print(changes[i] + " km, ");
        System.out.println();

        changes = currentCar.getRevisions().getBrakePads();
        System.out.print("Brake pads were changed at: ");
        for (int i = 0; i < changes.length; i++)
            System.out.print(changes[i] + " km, ");
        System.out.println();

        changes = currentCar.getRevisions().getBrakeFluid();
        System.out.print("Brake fluid was changed at: ");
        for (int i = 0; i < changes.length; i++)
            System.out.print(changes[i] + " km, ");
        System.out.println();
    }
}
