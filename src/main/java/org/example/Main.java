package org.example;

import java.util.List;

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

        // Since the list maintains the original creation order (or is close to it), 
        // we can safely cast and use the specific getters for demonstration.
        try {
            Refrigerator fridge = (Refrigerator) manager.getAllAppliances().get(0);
            Laptop laptop = (Laptop) manager.getAllAppliances().get(1);
            HairDryer dryer = (HairDryer) manager.getAllAppliances().get(2);

            System.out.println("\n--- 2.1 Appliance Details Check ---");
            System.out.println(fridge.getName() + " check: Has freezer? " + fridge.isHasFreezer());
            System.out.println(laptop.getName() + " check: Screen size is " + laptop.getScreenSizeInches() + " inches.");
            System.out.println(dryer.getName() + " check: Has " + dryer.getSpeedSettings() + " speed settings.");
        } catch (ClassCastException e) {
            System.err.println("Error demonstrating unique getters: List order mismatch.");
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
        double minEMR = 0.5;
        double maxEMR = 4.0;

        try {
            // Successful search demonstration
            System.out.printf("Search Range 1 (Success): EMR between %.2f and %.2f\n", minEMR, maxEMR);
            List<ElectricAppliance> foundAppliances = manager.findByRadiationRange(minEMR, maxEMR);
            System.out.println("Found Appliances:");
            foundAppliances.forEach(System.out::println);

            // Successful search demonstration (High EMR)
            double highMinEMR = 5.0;
            double highMaxEMR = 10.0;
            System.out.printf("\nSearch Range 2 (Success): EMR between %.2f and %.2f\n", highMinEMR, highMaxEMR);
            List<ElectricAppliance> highRadiationAppliances = manager.findByRadiationRange(highMinEMR, highMaxEMR);
            System.out.println("Found High Radiation Appliances:");
            highRadiationAppliances.forEach(System.out::println);

            // Intentional RuntimeException (Search Failure)
            double failureMinEMR = 6.0;
            double failureMaxEMR = 7.0;
            System.out.printf("\nSearch Range 3 (Failure Expected: RuntimeException): EMR between %.2f and %.2f\n",
                    failureMinEMR, failureMaxEMR);
            manager.findByRadiationRange(failureMinEMR, failureMaxEMR);

        } catch (RuntimeException e) {
            // This catches the specific RuntimeException thrown when no appliance is found
            System.err.println("Search Failure Handled: " + e.getMessage());
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
