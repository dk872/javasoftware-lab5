package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for checking object creation and IllegalArgumentException handling.
 */
class ApplianceCreationTest {

    // --- Tests for ElectricAppliance base class validation ---

    @Test
    void testNegativePowerConsumptionThrowsException() {
        // Power consumption must be positive
        assertThrows(IllegalArgumentException.class, () -> {
            new Refrigerator("Bad Fridge", -10, 0.5, true);
        }, "Should throw IllegalArgumentException for negative power.");
    }

    @Test
    void testZeroPowerConsumptionThrowsException() {
        // Power consumption must be positive (i.e., > 0)
        assertThrows(IllegalArgumentException.class, () -> {
            new Laptop("Zero Power", 0, 0.1, 13);
        }, "Should throw IllegalArgumentException for zero power.");
    }

    @Test
    void testNegativeRadiationLevelThrowsException() {
        // Radiation level cannot be negative
        assertThrows(IllegalArgumentException.class, () -> {
            new HairDryer("Bad Dryer", 1200, -2.0, 2);
        }, "Should throw IllegalArgumentException for negative radiation level.");
    }

    @Test
    void testSuccessfulCreation() {
        // Test successful creation of all derived classes
        assertDoesNotThrow(() -> {
            new Refrigerator("Fridge", 150, 0.8, true);
        });
        assertDoesNotThrow(() -> {
            new Laptop("Laptop", 60, 0.2, 16);
        });
        assertDoesNotThrow(() -> {
            new HairDryer("Dryer", 1800, 5.5, 3);
        });
    }

    // --- Tests for derived class fields and getters ---

    @Test
    void testRefrigeratorSpecificFields() {
        Refrigerator fridgeWithFreezer = new Refrigerator("Big Fridge", 200, 0.9, true);
        assertTrue(fridgeWithFreezer.isHasFreezer());

        Refrigerator miniFridge = new Refrigerator("Small Fridge", 70, 0.4, false);
        assertFalse(miniFridge.isHasFreezer());
    }

    @Test
    void testLaptopSpecificFields() {
        Laptop bigLaptop = new Laptop("17-inch", 90, 0.3, 17);
        assertEquals(17, bigLaptop.getScreenSizeInches());
    }

    @Test
    void testHairDryerSpecificFields() {
        HairDryer proDryer = new HairDryer("Pro Model", 2000, 6.0, 5);
        assertEquals(5, proDryer.getSpeedSettings());
    }
}
