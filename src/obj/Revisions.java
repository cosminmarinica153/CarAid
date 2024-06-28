package obj;

// This class is meant to hold a record of a car maintenance
public class Revisions {
    // Changes recommended at km, represented as constants
    public static final int ENGINE_OIL = 20000;
    public static final int TRANSMISSION_OIL = 60000;
    public static final int BRAKE_PADS = 30000;
    public static final int BRAKE_FLUID = 60000;

    // Attributes
    private int[] engineOil;
    private int[] transmissionOil;
    private int[] brakePads;
    private int[] brakeFluid;

    // Constructors
    public Revisions(int[] engineOil, int[] transmissionOil, int[] brakePads, int[] brakeFluid) {
        this.engineOil = engineOil;
        this.transmissionOil = transmissionOil;
        this.brakePads = brakePads;
        this.brakeFluid = brakeFluid;
    }

    public Revisions() {
        engineOil = new int[1];
        transmissionOil = new int[1];
        brakePads = new int[1];
        brakeFluid = new int[1];
    }

    // Getters and Setters
    public int[] getEngineOil() {
        return engineOil;
    }

    public void setEngineOil(int[] engineOil) {
        this.engineOil = engineOil;
    }

    public int[] getTransmissionOil() {
        return transmissionOil;
    }

    public void setTransmissionOil(int[] transmissionOil) {
        this.transmissionOil = transmissionOil;
    }

    public int[] getBrakePads() {
        return brakePads;
    }

    public void setBrakePads(int[] brakePads) {
        this.brakePads = brakePads;
    }

    public int[] getBrakeFluid() {
        return brakeFluid;
    }

    public void setBrakeFluid(int[] brakeFluid) {
        this.brakeFluid = brakeFluid;
    }

    // Overwritten .toString() method that returns the revisions in the following format:
    // {engineOilChanges='[]', transmissionOilChanges='[]', brakePadsChanges='[]', brakeFluidChanges='[]'}
    @Override
    public String toString() {
        String format = "{";

        // Add engine oil changes
        format += "engineOilChanges='[";
        for (int i = 0; i < engineOil.length - 1; i++) {
            format += engineOil[i];
            format += ", ";
        }
        format += engineOil[engineOil.length - 1] + "]', ";

        // Add transmission oil changes
        format += "transmissionOilChanges='[";
        for (int i = 0; i < transmissionOil.length - 1; i++) {
            format += transmissionOil[i];
            format += ", ";
        }
        format += transmissionOil[transmissionOil.length - 1] + "]', ";

        // Add brake pads changes
        format += "breakPadsChanges='[";
        for (int i = 0; i < brakePads.length - 1; i++) {
            format += brakePads[i];
            format += ", ";
        }
        format += brakePads[brakePads.length - 1] + "]', ";

        // Add brake fluid changes
        format += "brakeFluidChanges='[";
        for (int i = 0; i < brakeFluid.length - 1; i++) {
            format += brakeFluid[i];
            format += ", ";
        }
        format += brakeFluid[brakeFluid.length - 1] + "]'}";

        return format;
    }
}
