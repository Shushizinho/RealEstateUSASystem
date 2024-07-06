package pt.ipp.isep.dei.esoft.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.service.DataIntegrityChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.BusinessTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyTypeRepository;
import pt.ipp.isep.dei.esoft.project.service.DataIntegrityChecker;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Data integrity checker test.
 */
public class DataIntegrityCheckerTest {




        private DataIntegrityChecker checker;
        private ClientRepository clientRepository;
        private StoreRepository storeRepository;

    /**
     * Sets up.
     */
    @BeforeEach
        void setUp() {
            checker = new DataIntegrityChecker();
            clientRepository = new ClientRepository();
            storeRepository = new StoreRepository();
        }

    /**
     * Test put in address type.
     */
//    @Test
        void testPutInAddressType() {
            String[] address = {"Street", "1", "City", "State", "Zip"};
            String[] expected = {"Street1City", "State", "Zip", "", ""};

            String[] result = checker.putInAddressType(address);

            assertArrayEquals(expected, result);
        }

    /**
     * Test check all data.
     */
    @Test
        void testCheckAllData() {
            String[] headers = {"Name", "Age", "Email"};
            String[] values = {"John Doe", "912339076", "john.doe@example.com"};

            String result = checker.checkAllData("Age", headers, values);

            assertEquals("912339076", result);
        }

    /**
     * Test submit client data valid data returns true.
     */
//    @Test
        void testSubmitClientData_ValidData_ReturnsTrue() {
            String[] clientData = {"John Doe", "912339076", "123-45-6789", "AliceCARNEGIE2155@hotmail.com","teste"};
            String[] address = {"Street", "1", "City", "State", "Zip"};

            boolean result = checker.submitClientData(clientData, address, clientRepository);

            assertTrue(result);
        }

    /**
     * Test submit client data invalid data returns false.
     */
//    @Test
        void testSubmitClientData_InvalidData_ReturnsFalse() {
            String[] clientData = {"John Doe", "912339076", "123-45-6789", "invalidemail","TESTE"};
            String[] address = {"Street", "1", "City", "State", "Zip"};

            boolean result = checker.submitClientData(clientData, address, clientRepository);

            assertFalse(result);
        }

    /**
     * Test submit store data valid data returns true.
     */
//    @Test
        void testSubmitStoreData_ValidData_ReturnsTrue() {
            String[] storeValues = {"1", "Store Name", "Street,1,City,State,Zip", "123-45-6789"};
            String[] address = {"Street", "1", "City", "State", "Zip"};

            boolean result = checker.submitStoreData(storeValues, storeRepository);

            assertTrue(result);
        }

    /**
     * Test submit store data invalid data returns false.
     */
//    @Test
        void testSubmitStoreData_InvalidData_ReturnsFalse() {
            String[] storeValues = {"1", "Store Name", "Street,1,City,State,Zip", "invalidphone"};
            String[] address = {"Street", "1", "City", "State", "Zip"};

            boolean result = checker.submitStoreData(storeValues, storeRepository);

            assertFalse(result);
        }
//
//        @Test
//        void testSubmitPropertyData_ValidData_ReturnsTrue() {
//            String[] propertyValues = {"1", "Store Name", "Business Type", "Property Type", "Address", "Property Name", "1000"};
//            String[] address = {"Street", "1", "City", "State", "Zip"};
//
//            boolean result = checker.submitPropertyData(propertyValues, address, storeRepository);
//
//            assertTrue(result);
//        }
//
//    @Test
//    void testSubmitPropertyData_InvalidData_ReturnsFalse() {
//        String[] propertyValues = {"1", "Store Name", "Business Type", "Property Type", "Address", "Property Name", "invalidsize"};
//        String[] address = {"Street", "1", "City", "State", "Zip"};
//
//        boolean result = checker.submitPropertyData(propertyValues, address, storeRepository);
//
//        assertFalse(result);
//    }
//
//    @Test
//    void testSubmitAnnouncementData_ValidData_ReturnsTrue() {
//        String[] announcementValues = {"1", "Store Name", "Business Type", "Property Type", "Address", "Property Name",
//                "1000", "Description", "2022-01-01", "2022-02-01", "Payment Terms", "Payment Details"};
//        String[] address = {"Street", "1", "City", "State", "Zip"};
//
//        boolean result = checker.submitAnnouncementData(announcementValues, address, storeRepository);
//
//        assertTrue(result);
//    }
//
//    @Test
//    void testSubmitAnnouncementData_InvalidData_ReturnsFalse() {
//        String[] announcementValues = {"1", "Store Name", "Business Type", "Property Type", "Address", "Property Name",
//                "1000", "Description", "2022-01-01", "2022-02-01", "Payment Terms", "invalidpayment"};
//        String[] address = {"Street", "1", "City", "State", "Zip"};
//
//        boolean result = checker.submitAnnouncementData(announcementValues, address, storeRepository);
//
//        assertFalse(result);
//    }
}
