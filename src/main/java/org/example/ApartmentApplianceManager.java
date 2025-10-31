package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages a collection of ElectricAppliance objects in an apartment.
 * Provides methods for power calculation, sorting, and searching.
 */
public class ApartmentApplianceManager {
    /** The list holding all ElectricAppliance objects in the apartment. */
    private final List<ElectricAppliance> appliances;

    /**
     * Initializes the manager with an empty list of appliances.
     */
    public ApartmentApplianceManager() {
        this.appliances = new ArrayList<>();
    }

    /**
     * Adds an appliance to the apartment list.
     * @param appliance The ElectricAppliance object to add.
     * @throws NullPointerException if the appliance object is null.
     */
    public void addAppliance(ElectricAppliance appliance) {
        if (appliance == null) {
            throw new NullPointerException("Cannot add a null appliance.");
        }
        appliances.add(appliance);
    }

    /**
     * Calculates the total power consumed by all appliances that are currently plugged in.
     * @return The total power consumption in Watts.
     */
    public int calculateTotalPluggedInPower() {
        if (appliances.isEmpty()) {
            System.out.println("Warning: The apartment list is empty.");
            return 0;
        }

        int totalPower;
        // Use Java Stream API to filter and sum
        totalPower = appliances.stream()
                .filter(ElectricAppliance::isPluggedIn)
                .mapToInt(ElectricAppliance::getPowerConsumptionW)
                .sum();

        return totalPower;
    }

    /**
     * Sorts the appliances list in place based on their power consumption (ascending).
     * @return The sorted list of appliances.
     */
    public List<ElectricAppliance> sortByPower() {
        if (appliances.isEmpty()) {
            System.out.println("Warning: Cannot sort an empty list.");
            return new ArrayList<>();
        }

        // Use Comparator to sort by power consumption
        appliances.sort(Comparator.comparingInt(ElectricAppliance::getPowerConsumptionW));
        return new ArrayList<>(appliances);
    }

    /**
     * Finds appliances that fall within a specified range of electromagnetic radiation level.
     * @param minLevel The minimum radiation level (inclusive).
     * @param maxLevel The maximum radiation level (inclusive).
     * @return A list of appliances that match the radiation range.
     * @throws IllegalArgumentException if the minimum level is greater than the maximum level or levels are negative.
     */
    public List<ElectricAppliance> findByRadiationRange(double minLevel, double maxLevel) {
        if (minLevel < 0 || maxLevel < 0 || minLevel > maxLevel) {
            throw new IllegalArgumentException("Invalid radiation range provided. Min must be <= Max and non-negative.");
        }
        if (appliances.isEmpty()) {
            System.out.println("Warning: Appliance list is empty, no search performed.");
            return new ArrayList<>();
        }

        // Use Java Stream API to filter based on the EMR level
        List<ElectricAppliance> results = appliances.stream()
                .filter(a -> a.getElectromagneticRadiationLevel() >= minLevel &&
                        a.getElectromagneticRadiationLevel() <= maxLevel)
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No appliances found in range [" + minLevel + " - " + maxLevel + "]");
        }

        return results;
    }

    /**
     * Gets the current list of all appliances.
     * @return The list of all appliances currently managed.
     */
    public List<ElectricAppliance> getAllAppliances() {
        return new ArrayList<>(appliances);
    }
}
