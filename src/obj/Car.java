package obj;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Car {
    private static int NEXT_ID = 1;

    // Attributes
    private final int id;
    private int ownerId;
    private final String make;
    private final String model;
    private final int year;
    private int kilometers;
    private final Revisions revisions;

    // Constructor
    public Car(int ownerId, String make, String model, int year, int kilometers, Revisions revisions) {
        this.id = NEXT_ID++;
        this.ownerId = ownerId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.kilometers = kilometers;
        this.revisions = revisions;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public Revisions getRevisions() {
        return revisions;
    }

    // Overwritten .toString() method
    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", kilometers='" + kilometers + '\'' +
                ", revisions='" + revisions.toString() +
                "'}";
    }

    // Method to transform the string from data to a valid owner object
    // This method also helps to keep the id in order
    public static Car toCar(String s) {
        // Trim the string to exclude the userId (it will get incremented automatically)
        // and to also exclude the {}
        s = s.substring(8, s.length() - 1);

        // Separate the string to two main parts
        // Attribute side that contains the 'primitive' attributes
        // Revision side that contains the compound attribute obj.Revisions, which is an object
        String attributesString = "";
        String revisionString = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                attributesString = s.substring(1, i - 13);

                revisionString = s.substring(i, s.length() - 1);
                revisionString = revisionString.substring(1, revisionString.length() - 1);
                break;
            }
        }

        // Regex to get the values of the attributes
        Pattern pattern = Pattern.compile("'([^']*)'");
        ArrayList<String> val;

        // Attribute parsing
        Matcher matcher = pattern.matcher(attributesString);
        val = new ArrayList<>();

        // We will add all the matched values to an array list in the correct order
        while (matcher.find()) {
            val.add(matcher.group(1));
        }

        // Prepare the attribute values
        int ownerId = Integer.parseInt(val.get(0));
        String make = val.get(1);
        String model = val.get(2);
        int year = Integer.parseInt(val.get(3));
        int kilometers = Integer.parseInt(val.get(4));

        // obj.Revisions parsing
        matcher = pattern.matcher(revisionString);
        val = new ArrayList<>();
        ArrayList<Integer> arrList;
        String temp;
        String[] tempArr;

        // We will add all the matched values to an array list in the correct order
        while (matcher.find()) {
            val.add(matcher.group(1));
        }

        // Preparing revisions attributes
        // We will parse the matched values by parsing the value string to an array list
        // that contains the values of the integers, parsed from their string counterpart

        // Engine Oil Changes
        temp = val.getFirst();
        tempArr = temp.substring(1, temp.length() - 1).split(", ");
        arrList = new ArrayList<>();

        for (String string : tempArr)
            arrList.add(Integer.parseInt(string));
        int[] engineOilChanges = arrList.stream().mapToInt(Integer::intValue).toArray();

        // Transmission Oil Changes
        temp = val.get(1);
        tempArr = temp.substring(1, temp.length() - 1).split(", ");
        arrList = new ArrayList<>();

        for (String string : tempArr)
            arrList.add(Integer.parseInt(string));
        int[] transmissionOilChanges = arrList.stream().mapToInt(Integer::intValue).toArray();

        // Brake Pads Changes
        temp = val.get(2);
        tempArr = temp.substring(1, temp.length() - 1).split(", ");
        arrList = new ArrayList<>();

        for (String string : tempArr)
            arrList.add(Integer.parseInt(string));
        int[] brakePadsChanges = arrList.stream().mapToInt(Integer::intValue).toArray();

        // Brake Fluid Changes
        temp = val.get(3);
        tempArr = temp.substring(1, temp.length() - 1).split(", ");
        arrList = new ArrayList<>();

        for (String string : tempArr)
            arrList.add(Integer.parseInt(string));
        int[] brakeFluidChanges = arrList.stream().mapToInt(Integer::intValue).toArray();

        // Initiate the revision object using the values extracted
        Revisions revisions = new Revisions(engineOilChanges, transmissionOilChanges, brakePadsChanges, brakeFluidChanges);

        // Return a new object of type car, created from the string
        return new Car(ownerId, make, model, year, kilometers, revisions);
    }

    // Methods to check the next required changes
    public int getLastOilChange() {
        int[] oilChanges = revisions.getEngineOil();

        return oilChanges[oilChanges.length - 1];
    }

    public int getLastTransmissionOilChange() {
        int[] transmissionOilChanges = revisions.getTransmissionOil();

        return transmissionOilChanges[transmissionOilChanges.length - 1];
    }

    public int getLastBreakPadsChange() {
        int[] breakPadChanges = revisions.getBrakePads();

        return breakPadChanges[breakPadChanges.length - 1];
    }

    public int getLastBrakeFluidChange() {
        int[] brakeFluidChanges = revisions.getBrakeFluid();

        return brakeFluidChanges[brakeFluidChanges.length - 1];
    }

    // Methods to add a revision made
    public void addEngineOilChange(int km) {
        int[] arr = new int[revisions.getEngineOil().length + 1];
        arr[arr.length - 1] = km;

        revisions.setEngineOil(arr);
    }

    public void addTransmissionOilChange(int km) {
        int[] arr = new int[revisions.getTransmissionOil().length + 1];
        arr[arr.length - 1] = km;

        revisions.setTransmissionOil(arr);
    }

    public void addBrakePadsChange(int km) {
        int[] arr = new int[revisions.getBrakePads().length + 1];
        arr[arr.length - 1] = km;

        revisions.setBrakePads(arr);
    }

    public void addBrakeFluidChange(int km) {
        int[] arr = new int[revisions.getBrakeFluid().length + 1];
        arr[arr.length - 1] = km;

        revisions.setBrakeFluid(arr);
    }
}
