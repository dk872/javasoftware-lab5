package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ApartmentApplianceManager class functionality.
 */
class ApartmentApplianceManagerTest {

    private ApartmentApplianceManager manager;
    private Refrigerator fridge;
    private Laptop laptop;
    private HairDryer dryer;

    @BeforeEach
    void setUp() {
        // Initialize manager and appliances before each test
        manager = new ApartmentApplianceManager();
        fridge = new Refrigerator("Fridge_A", 150, 0.5, true);
        laptop = new Laptop("Laptop_B", 60, 0.1, 16);
        dryer = new HairDryer("Dryer_C", 1800, 5.0, 3);

        manager.addAppliance(fridge);
        manager.addAppliance(laptop);
        manager.addAppliance(dryer);
    }

    // --- Test: Power Calculation ---

    @Test
    void testCalculateTotalPluggedInPower_NonePlugged() {
        // By default, all are unplugged (0W)
        assertEquals(0, manager.calculateTotalPluggedInPower());
    }

    @Test
    void testCalculateTotalPluggedInPower_AllPlugged() {
        fridge.plugIn(); // 150W
        laptop.plugIn(); // 60W
        dryer.plugIn(); // 1800W

        int expectedPower = 150 + 60 + 1800;
        assertEquals(expectedPower, manager.calculateTotalPluggedInPower());
    }

    @Test
    void testCalculateTotalPluggedInPower_MixedState() {
        fridge.plugIn(); // 150W
        // laptop remains unplugged
        dryer.plugIn(); // 1800W

        int expectedPower = 150 + 1800;
        assertEquals(expectedPower, manager.calculateTotalPluggedInPower());
    }

    // --- Test: Sorting ---

    @Test
    void testSortByPowerAscending() {
        // Initial order: 150W, 60W, 1800W
        List<ElectricAppliance> sortedList = manager.sortByPower();

        // Expected order: 60W, 150W, 1800W
        assertEquals(laptop, sortedList.get(0));
        assertEquals(fridge, sortedList.get(1));
        assertEquals(dryer, sortedList.get(2));

        assertEquals(60, sortedList.get(0).getPowerConsumptionW());
        assertEquals(150, sortedList.get(1).getPowerConsumptionW());
        assertEquals(1800, sortedList.get(2).getPowerConsumptionW());
    }

    // --- Test: Searching by EMR Range ---

    @Test
    void testFindByRadiationRange_SuccessfulSearch() {
        // Appliances EMR: Fridge (0.5), Laptop (0.1), Dryer (5.0)

        // Search for low EMR
        List<ElectricAppliance> lowEmi = manager.findByRadiationRange(0.0, 0.5);
        assertEquals(2, lowEmi.size());
        assertTrue(lowEmi.contains(laptop));
        assertTrue(lowEmi.contains(fridge));

        // Search for high EMR
        List<ElectricAppliance> highEmi = manager.findByRadiationRange(4.0, 6.0);
        assertEquals(1, highEmi.size());
        assertTrue(highEmi.contains(dryer));
    }

    @Test
    void testFindByRadiationRange_RangeNotFoundThrowsRuntimeException() {
        // Appliances EMR: 0.5, 0.1, 5.0

        // Search for a range where no appliances exist
        assertThrows(RuntimeException.class, () -> {
            manager.findByRadiationRange(2.0, 4.0);
        }, "Should throw RuntimeException if no appliances are found in the range.");
    }

    @Test
    void testFindByRadiationRange_InvalidRangeThrowsIllegalArgumentException() {
        // Min > Max
        assertThrows(IllegalArgumentException.class, () -> {
            manager.findByRadiationRange(5.0, 1.0);
        }, "Should throw IllegalArgumentException if minLevel > maxLevel.");

        // Negative range limit
        assertThrows(IllegalArgumentException.class, () -> {
            manager.findByRadiationRange(-1.0, 5.0);
        }, "Should throw IllegalArgumentException if any level is negative.");
    }

    @Test
    void testAddNullApplianceThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            manager.addAppliance(null);
        }, "Adding null should throw NullPointerException.");
    }
}
