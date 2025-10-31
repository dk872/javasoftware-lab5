package org.example;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Main class to demonstrate the functionality of the ElectricAppliance hierarchy
 * and the ApartmentApplianceManager. Includes demonstration of exception handling.
 */
public class Main {
    /**
     * The main entry point for the application.
     * Initializes the appliance manager, creates sample appliances, 
     * performs operations (plugging in, calculating power, sorting, searching), 
     * and demonstrates exception handling.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize the manager
        ApartmentApplianceManager manager = new ApartmentApplianceManager();

        System.out.println("--- 1. Initializing Appliances ---");

        try {
            // Create appliances
            Refrigerator fridge = new Refrigerator("Samsung Fridge", 150, 0.8, true);
            Laptop laptop = new Laptop("MacBook Pro", 60, 0.2, 16);
            HairDryer dryer = new HairDryer("Philips Hair Dryer", 1800, 5.5, 3);
            Refrigerator miniFridge = new Refrigerator("Mini-Bar", 80, 0.5, false);
            Laptop workLaptop = new Laptop("Dell Latitude", 45, 0.3, 14);

            // Add appliances to the manager
            manager.addAppliance(fridge);
            manager.addAppliance(laptop);
            manager.addAppliance(dryer);
            manager.addAppliance(miniFridge);
            manager.addAppliance(workLaptop);

            System.out.println("All appliances added successfully.");

        } catch (IllegalArgumentException e) {
            System.err.println("Configuration Error (Handled): " + e.getMessage());
            return; // Stop if setup fails
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during initialization (Handled): " + e.getMessage());
            return;
        }

        // Print initial state
        System.out.println("\n--- 2. Initial State of Appliances ---");
        manager.getAllAppliances().forEach(System.out::println);

        // We use streams and filter by type to safely retrieve one instance of each subclass
        try {
            Refrigerator fridgeInstance = manager.getAllAppliances().stream()
                    .filter(a -> a instanceof Refrigerator)
                    .map(a -> (Refrigerator) a)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Refrigerator not found for demonstration."));

            Laptop laptopInstance = manager.getAllAppliances().stream()
                    .filter(a -> a instanceof Laptop)
                    .map(a -> (Laptop) a)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Laptop not found for demonstration."));

            HairDryer dryerInstance = manager.getAllAppliances().stream()
                    .filter(a -> a instanceof HairDryer)
                    .map(a -> (HairDryer) a)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("HairDryer not found for demonstration."));

            System.out.println("\n--- 2.1 Appliance Details Check (Safe Casting) ---");
            System.out.println(fridgeInstance.getName() + " check: Has freezer? " + fridgeInstance.isHasFreezer());
            System.out.println(laptopInstance.getName() + " check: Screen size is " +
                    laptopInstance.getScreenSizeInches() + " inches.");
            System.out.println(dryerInstance.getName() + " check: Has " + dryerInstance.getSpeedSettings()
                    + " speed settings.");

        } catch (NoSuchElementException e) {
            System.err.println("Error demonstrating unique getters (Handled): " + e.getMessage());
        }

        // --- Operation 1: Plug in appliances ---
        System.out.println("\n--- 3. Plugging in Appliances ---");
        manager.getAllAppliances().get(0).plugIn(); // Fridge (continuous use)
        manager.getAllAppliances().get(1).plugIn(); // Laptop (charging)
        manager.getAllAppliances().get(2).unplug(); // Dryer (off) - just to show unplug is the default state
        manager.getAllAppliances().get(3).plugIn(); // Mini-Bar
        // workLaptop remains unplugged

        // --- Operation 2: Calculate Total Power ---
        System.out.println("\n--- 4. Calculating Total Consumed Power ---");
        int totalPower = manager.calculateTotalPluggedInPower();
        System.out.println("Total power consumed by plugged-in appliances: " + totalPower + "W");

        // --- Operation 3: Sort Appliances by Power ---
        System.out.println("\n--- 5. Sorting Appliances by Power Consumption (Ascending) ---");
        List<ElectricAppliance> sortedList = manager.sortByPower();
        sortedList.forEach(System.out::println);

        // --- Operation 4: Find Appliance by EMR Range (Success/Failure) ---
        System.out.println("\n--- 6. Searching for Appliance by Electromagnetic Radiation Range ---");

        try {
            double minEMR = 0.5;
            double maxEMR = 4.0;
            System.out.printf("Searching for EMR between %.2f and %.2f\n", minEMR, maxEMR);
            List<ElectricAppliance> found = manager.findByRadiationRange(minEMR, maxEMR);
            found.forEach(System.out::println);

            // Successful search demonstration (High EMR)
            double highMinEMR = 5.0;
            double highMaxEMR = 10.0;
            System.out.printf("\nSearching for EMR between %.2f and %.2f\n", highMinEMR, highMaxEMR);
            List<ElectricAppliance> highEMRFound = manager.findByRadiationRange(highMinEMR, highMaxEMR);
            highEMRFound.forEach(System.out::println);

            // Intentional NoSuchElementException (Search Failure)
            double failureMinEMR = 6.0;
            double failureMaxEMR = 7.0;
            System.out.printf("\nSearching for EMR between %.2f and %.2f (expected to fail)\n",
                    failureMinEMR, failureMaxEMR);
            manager.findByRadiationRange(failureMinEMR, failureMaxEMR);

        } catch (NoSuchElementException e) {
            System.err.println("No appliance found in the given EMR range: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid EMR range provided: " + e.getMessage());
        }

        // --- Exception Handling Demonstration (Invalid Input) ---
        System.out.println("\n--- 7. Exception Handling Test (Intentional Invalid Input) ---");
        double invalidMinEMR = 5.0;
        double invalidMaxEMR = 0.1; // Min > Max is an invalid range

        try {
            System.out.printf("Attempting search with invalid range: Min=%.2f, Max=%.2f\n", invalidMinEMR, invalidMaxEMR);
            // This call is expected to fail with IllegalArgumentException
            manager.findByRadiationRange(invalidMinEMR, invalidMaxEMR);
        } catch (IllegalArgumentException e) {
            System.err.println("Successfully handled IllegalArgumentException: " + e.getMessage());
        }
    }
}
