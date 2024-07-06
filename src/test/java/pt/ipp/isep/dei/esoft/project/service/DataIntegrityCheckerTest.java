package pt.ipp.isep.dei.esoft.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataIntegrityCheckerTest {

//    @Test
    void testCheckAllData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();
        assertEquals("result",
                dataIntegrityCheckerUnderTest.checkAllData("search", new String[]{"headers"}, new String[]{"values"}));
    }

    @Test
    void testCountDigits() {
        assertEquals(0, DataIntegrityChecker.countDigits(0L));
    }

    @Test
    void testIsAlpha() {
        assertTrue(DataIntegrityChecker.isAlpha("str"));
    }

    @Test
    void testIsValidEmail() {
        assertFalse(DataIntegrityChecker.isValidEmail("email"));
    }

    @Test
    void testIsValidTax() {
        assertFalse(DataIntegrityChecker.isValidTax("tax"));
    }

//    @Test
    void testPutInAddressType() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();
        assertArrayEquals(new String[]{"result"},
                dataIntegrityCheckerUnderTest.putInAddressType(new String[]{"address"}));
        assertArrayEquals(new String[]{}, dataIntegrityCheckerUnderTest.putInAddressType(new String[]{"address"}));
    }

//    @Test
    void testSubmitAnnouncementData() {
        // Setup
        final Property property = new Property(0.0, 0.0, new Address("streetAddress", "city", "state", "zipCode"), 0.0,
                List.of(new Photograph("uri")), new PropertyType("description"),
                new Store(0, "designation", "email", new Address("streetAddress", "city", "state", "zipCode"), 0L),
                new Client("name", "email", 0, "taxNumber", new Address("streetAddress", "city", "state", "zipCode"),
                        0L), new DateTime(1, 1, 2020));
        final AnnouncementRepository announcementRepository = new AnnouncementRepository();
        final BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();

        // Run the test
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();
        final boolean result = dataIntegrityCheckerUnderTest.submitAnnouncementData(new String[]{"announcementValues"},
                property, announcementRepository, businessTypeRepository);

        // Verify the results
        assertFalse(result);
    }

//    @Test
    void testSubmitClientData() {
        // Setup
        final ClientRepository clientRepository = new ClientRepository();

        // Run the test
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();
        final boolean result = dataIntegrityCheckerUnderTest.submitClientData(new String[]{"clientData"},
                new String[]{"address"}, clientRepository);

        // Verify the results
        assertFalse(result);
    }

//    @Test
    void testSubmitPropertyData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        // Setup
        final Store store = new Store(0, "designation", "email",
                new Address("streetAddress", "city", "state", "zipCode"), 0L);
        final Client owner = new Client("name", "email", 0, "taxNumber",
                new Address("streetAddress", "city", "state", "zipCode"), 0L);
        final PropertyRepository propertyRepository = new PropertyRepository();
        final PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();

        // Run the test
        final boolean result = dataIntegrityCheckerUnderTest.submitPropertyData(new String[]{"propertyValues"},
                new String[]{"address"}, store, owner, propertyRepository, propertyTypeRepository);

        // Verify the results
        assertFalse(result);
    }

//    @Test
    void testSubmitPurchaseOrderData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        // Setup
        final PurchaseOrderRepository purchaseOrderRepository = new PurchaseOrderRepository();
        final Announcement announcement = new Announcement(new Agent(
                new Employee("name", "email", 0, "taxNumber", new Address("streetAddress", "city", "state", "zipCode"),
                        0L,
                        new Store(0, "designation", "email", new Address("streetAddress", "city", "state", "zipCode"),
                                0L), List.of(new Role(0, "description")))), new DateTime(1, 1, 2020), 0.0,
                new BusinessType("descriptionProperty", "descriptionAnnoucement"),
                new Property(0.0, 0.0, new Address("streetAddress", "city", "state", "zipCode"), 0.0,
                        List.of(new Photograph("uri")), new PropertyType("description"),
                        new Store(0, "designation", "email", new Address("streetAddress", "city", "state", "zipCode"),
                                0L), new Client("name", "email", 0, "taxNumber",
                        new Address("streetAddress", "city", "state", "zipCode"), 0L), new DateTime(1, 1, 2020)), 0,
                0.0);

        // Run the test
        final boolean result = dataIntegrityCheckerUnderTest.submitPurchaseOrderData(
                new String[]{"purchaseOrderValues"}, purchaseOrderRepository, announcement, new String[]{"address"});

        // Verify the results
        assertFalse(result);
    }

//    @Test
    void testSubmitStoreData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        // Setup
        final StoreRepository storeRepository = new StoreRepository();

        // Run the test
        final boolean result = dataIntegrityCheckerUnderTest.submitStoreData(new String[]{"storeValues"},
                storeRepository);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testValidataPurchaseOrderData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        assertTrue(dataIntegrityCheckerUnderTest.validataPurchaseOrderData(new String[]{"purchaseOrderValue"}));
    }

    @Test
    void testValidateAnnouncementData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        assertTrue(dataIntegrityCheckerUnderTest.validateAnnouncementData(new String[]{"announcementValues"}));
    }

    @Test
    void testValidateClientData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        assertFalse(
                dataIntegrityCheckerUnderTest.validateClientData(new String[]{"clientData"}, new String[]{"address"}));
    }

//    @Test
    void testValidateExistenceClient() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        assertFalse(dataIntegrityCheckerUnderTest.validateExistenceClient("email"));
    }

    @Test
    void testValidateIfExist() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        assertFalse(dataIntegrityCheckerUnderTest.validateIfExist(new String[]{"propertyValues"}, 0));
    }

    @Test
    void testValidatePropertyData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        assertTrue(dataIntegrityCheckerUnderTest.validatePropertyData(new String[]{"propertyValues"},
                new String[]{"address"}));
    }

//    @Test
    void testValidateStoreData() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        assertFalse(
                dataIntegrityCheckerUnderTest.validateStoreData(new String[]{"storeValues"}, new String[]{"address"}));
    }

//    @Test
    void testValidateStoreID() {
        DataIntegrityChecker dataIntegrityCheckerUnderTest = new DataIntegrityChecker();

        assertFalse(dataIntegrityCheckerUnderTest.validateStoreID("storeV"));
    }

    @Test
    @DisplayName("Should return null when the search does not match any header")
    void checkAllDataWhenSearchDoesNotMatchHeader() {
        String[] headers = {"Name", "Email", "Phone"};
        String[] values = {"John Doe", "john.doe@example.com", "123456789"};

        DataIntegrityChecker dataIntegrityChecker = new DataIntegrityChecker();
        String result = dataIntegrityChecker.checkAllData("Address", headers, values);

        assertEquals(null, result);
    }

    @Test
    @DisplayName("Should return the correct value when the search matches a header")
    void checkAllDataWhenSearchMatchesHeader() {
        String[] headers = {"Name", "Email", "Phone"};
        String[] values = {"John Doe", "john.doe@example.com", "123456789"};

        DataIntegrityChecker dataIntegrityChecker = new DataIntegrityChecker();

        String result = dataIntegrityChecker.checkAllData("Email", headers, values);

        assertEquals("john.doe@example.com", result);
    }

//    @Test
    @DisplayName("Should return the input address unchanged when the input address length is less than 5")
    void putInAddressTypeWhenAddressLengthIsLessThanFive() {
        String[] address = {"123 Main St", "Apt 4", "Anytown", "CA", "12345"};
        String[] expected = {"123 Main St", "Apt 4", "Anytown", "CA", "12345"};

        DataIntegrityChecker dataIntegrityChecker = new DataIntegrityChecker();
        String[] actual = dataIntegrityChecker.putInAddressType(address);

        assertEquals(expected, actual);
    }

//    @Test
    @DisplayName("Should return the correct address type when the input address length is greater than or equal to 7")
    void putInAddressTypeWhenAddressLengthIsGreaterThanOrEqualToSeven() {
        String[] address = {"123", "Main", "St", "Apt", "4B", "New", "York"};
        String[] expectedAddress = {"123", "MainStApt", "4B", "", "", "New", "York"};

        DataIntegrityChecker dataIntegrityChecker = new DataIntegrityChecker();
        String[] actualAddress = dataIntegrityChecker.putInAddressType(address);

        assertEquals(expectedAddress.length, actualAddress.length);
        for (int i = 0; i < expectedAddress.length; i++) {
            assertEquals(expectedAddress[i], actualAddress[i]);
        }
    }

//    @Test
    @DisplayName("Should return the correct address type when the input address length is between 5 and 7 (excluding 7)")
    void putInAddressTypeWhenAddressLengthIsBetweenFiveAndSeven() {
        String[] address = {"123", "Main", "St", "Apt", "4B", "New", "York"};
        String[] expectedAddress = {"123MainStApt", "4B", "NewYork", "", "", ""};

        DataIntegrityChecker dataIntegrityChecker = new DataIntegrityChecker();
        String[] actualAddress = dataIntegrityChecker.putInAddressType(address);

        assertEquals(expectedAddress.length, actualAddress.length);
        for (int i = 0; i < expectedAddress.length; i++) {
            assertEquals(expectedAddress[i], actualAddress[i]);
        }
    }

    @Test
    @DisplayName("Should return the correct number of digits for a given negative long number")
    void countDigitsForNegativeLongNumber() {
        long num = -123456789;
        int expectedCount = 9;

        int actualCount = DataIntegrityChecker.countDigits(num);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Should return 0 when the input number is 0")
    void countDigitsForZero() {
        long num = 0;
        int expectedCount = 0;

        int actualCount = DataIntegrityChecker.countDigits(num);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Should return the correct number of digits for a given positive long number")
    void countDigitsForPositiveLongNumber() {
        long num = 123456789;
        int expectedCount = 9;

        int actualCount = DataIntegrityChecker.countDigits(num);

        assertEquals(expectedCount, actualCount);
    }
}