package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Get deals list controller test.
 */
public class GetDealsListControllerTest {
    private GetDealsListController controller;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        controller = new GetDealsListController();
    }


    @Test
    @DisplayName("Should return a sorted array using Bubble Sort algorithm")
    void getBubbleSort() {
        double[] areaProperty = {5.0, 2.0, 8.0, 1.0, 9.0};
        double[] expectedSortedArray = {1.0, 2.0, 5.0, 8.0, 9.0};
        double[] actualSortedArray = controller.getBubbleSort(areaProperty);
        Assertions.assertArrayEquals(expectedSortedArray, actualSortedArray);
    }

    @Test
    @DisplayName("Should return a sorted array using Insertion Sort algorithm")
    void getInsertionSort() {
        double[] areaProperty = {10.0, 5.0, 8.0, 3.0, 6.0};
        double[] expectedSortedArray = {3.0, 5.0, 6.0, 8.0, 10.0};

        double[] sortedArray = controller.getInsetionSort(areaProperty);

        Assertions.assertArrayEquals(expectedSortedArray, sortedArray);
    }

    /**
     * Test get deals order.
     */
    @Test
    public void testGetDealsOrder() {
        List<Property> deals = controller.getDealsOrder();
        Assertions.assertNotNull(deals);
    }

    /**
     * Test get bubble sort.
     */
    @Test
    public void testGetBubbleSort() {
        double[] areaProperty = {5.2, 3.7, 1.9, 4.5};
        double[] sortedAreaProperty = controller.getBubbleSort(areaProperty);
        Assertions.assertArrayEquals(sortedAreaProperty, new double[]{1.9, 3.7, 4.5, 5.2});
    }

    /**
     * Test get insetion sort.
     */
    @Test
    public void testGetInsetionSort() {
        double[] areaProperty = {5.2, 3.7, 1.9, 4.5};
        double[] sortedAreaProperty = controller.getInsetionSort(areaProperty);
        Assertions.assertArrayEquals(sortedAreaProperty, new double[]{1.9, 3.7, 4.5, 5.2});
    }

    /**
     * Test get deals.
     */
    @Test
    public void testGetDeals() {
        List<PurchaseOrder> deals = controller.getDeals();
        Assertions.assertNotNull(deals);
    }

    @Test
    void testGetPropertySorted() {
        // Setup
        final List<Property> propertyList = List.of(
                new Property(0.0, 0.0, new Address("streetAddress", "city", "state", "zipCode"), 0.0,
                        List.of(new Photograph("uri")), new PropertyType("description"),
                        new Store(0, "designation", "email", new Address("streetAddress", "city", "state", "zipCode"),
                                0L), new Client("name", "email", 0, "taxNumber",
                        new Address("streetAddress", "city", "state", "zipCode"), 0L), new DateTime(1, 1, 2020)));
        final List<Property> expectedResult = List.of(
                new Property(0.0, 0.0, new Address("streetAddress", "city", "state", "zipCode"), 0.0,
                        List.of(new Photograph("uri")), new PropertyType("description"),
                        new Store(0, "designation", "email", new Address("streetAddress", "city", "state", "zipCode"),
                                0L), new Client("name", "email", 0, "taxNumber",
                        new Address("streetAddress", "city", "state", "zipCode"), 0L), new DateTime(1, 1, 2020)));

        // Run the test
        final List<Property> result = controller.getPropertySorted(propertyList, new double[]{0.0});

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSortDealsBubble() {
        // Setup
        final List<Property> properties = List.of(
                new Property(0.0, 0.0, new Address("streetAddress", "city", "state", "zipCode"), 0.0,
                        List.of(new Photograph("uri")), new PropertyType("description"),
                        new Store(0, "designation", "email", new Address("streetAddress", "city", "state", "zipCode"),
                                0L), new Client("name", "email", 0, "taxNumber",
                        new Address("streetAddress", "city", "state", "zipCode"), 0L), new DateTime(1, 1, 2020)));

        // Run the test
        final double[] result = controller.sortDeals(properties, 1);

        // Verify the results
        assertArrayEquals(new double[]{0.0}, result);
    }

    @Test
    void testSortDealsInsetion() {
        // Setup
        final List<Property> properties = List.of(
                new Property(0.0, 0.0, new Address("streetAddress", "city", "state", "zipCode"), 0.0,
                        List.of(new Photograph("uri")), new PropertyType("description"),
                        new Store(0, "designation", "email", new Address("streetAddress", "city", "state", "zipCode"),
                                0L), new Client("name", "email", 0, "taxNumber",
                        new Address("streetAddress", "city", "state", "zipCode"), 0L), new DateTime(1, 1, 2020)));

        // Run the test
        final double[] result = controller.sortDeals(properties, 2);

        // Verify the results
        assertArrayEquals(new double[]{0.0}, result);
    }

}
