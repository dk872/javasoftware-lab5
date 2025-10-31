package org.example;

import java.util.Locale;

/**
 * Abstract base class representing a generic electric appliance.
 * Defines common properties such as name, power consumption, plug-in status,
 * and electromagnetic radiation level.
 */
public abstract class ElectricAppliance {
    /** The name of the appliance (e.g., "Bosch Fridge"). */
    private final String name;
    /** The power consumed by the appliance in Watts (W). */
    private final int powerConsumptionW;
    /** Flag indicating whether the appliance is currently plugged into the socket. */
    private boolean isPluggedIn;
    /** The level of electromagnetic radiation emitted (Unitless scale, e.g., 0.0 to 10.0). */
    private final double electromagneticRadiationLevel;

    /**
     * Constructor for the ElectricAppliance class.
     * @param name The name of the appliance (e.g., "Bosch Fridge").
     * @param powerConsumptionW The power consumed by the appliance in Watts.
     * @param electromagneticRadiationLevel The level of electromagnetic radiation emitted.
     * @throws IllegalArgumentException if powerConsumptionW is not positive or radiation level is negative.
     */
    public ElectricAppliance(String name, int powerConsumptionW, double electromagneticRadiationLevel) {
        // Input validation for safety and logic
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }
        if (powerConsumptionW <= 0) {
            throw new IllegalArgumentException("Power consumption must be a positive value.");
        }
        if (electromagneticRadiationLevel < 0) {
            throw new IllegalArgumentException("Radiation level cannot be negative.");
        }

        this.name = name;
        this.powerConsumptionW = powerConsumptionW;
        this.isPluggedIn = false; // By default, appliances are unplugged
        this.electromagneticRadiationLevel = electromagneticRadiationLevel;
    }

    /**
     * Attempts to plug the appliance into the socket.
     */
    public void plugIn() {
        if (!isPluggedIn) {
            isPluggedIn = true;
            System.out.println(name + " is now plugged in.");
        } else {
            System.out.println(name + " is already plugged in.");
        }
    }

    /**
     * Unplugs the appliance from the socket.
     */
    public void unplug() {
        if (isPluggedIn) {
            isPluggedIn = false;
            System.out.println(name + " is now unplugged.");
        } else {
            System.out.println(name + " is already unplugged.");
        }
    }

    /**
     * Retrieves the name of the appliance.
     * @return The name of the appliance.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the power consumption of the appliance.
     * @return The power consumption in Watts (W).
     */
    public int getPowerConsumptionW() {
        return powerConsumptionW;
    }

    /**
     * Checks if the appliance is currently plugged in.
     * @return {@code true} if the appliance is plugged in, {@code false} otherwise.
     */
    public boolean isPluggedIn() {
        return isPluggedIn;
    }

    /**
     * Retrieves the level of electromagnetic radiation emitted by the appliance.
     * @return The electromagnetic radiation level.
     */
    public double getElectromagneticRadiationLevel() {
        return electromagneticRadiationLevel;
    }

    /**
     * Provides a string representation of the appliance's state.
     * @return A formatted string with appliance details.
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "%-15s | Power: %4dW | Plugged: %-5s | EMR Level: %.2f",
                name, powerConsumptionW, (isPluggedIn ? "Yes" : "No"), electromagneticRadiationLevel);
    }
}
