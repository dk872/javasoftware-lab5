package org.example;

/**
 * Represents a Refrigerator, a major appliance typically requiring continuous power.
 */
public class Refrigerator extends ElectricAppliance {
    /** Flag indicating if the refrigerator has a separate freezer compartment. */
    private final boolean hasFreezer;

    /**
     * Constructor for Refrigerator.
     * @param name The name of the refrigerator.
     * @param powerConsumptionW The power consumed in Watts.
     * @param electromagneticRadiationLevel The EMR level.
     * @param hasFreezer True if the refrigerator has a separate freezer compartment.
     */
    public Refrigerator(String name, int powerConsumptionW, double electromagneticRadiationLevel, boolean hasFreezer) {
        super(name, powerConsumptionW, electromagneticRadiationLevel);
        this.hasFreezer = hasFreezer;
    }

    /**
     * Checks if the refrigerator has a separate freezer.
     * @return {@code true} if it has a freezer, {@code false} otherwise.
     */
    public boolean isFreezerPresent() {
        return hasFreezer;
    }

    /**
     * Provides a string representation of the Refrigerator, including freezer status.
     * @return A formatted string with appliance and Refrigerator details.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Refrigerator (Freezer: %s)", hasFreezer ? "Yes" : "No");
    }
}
