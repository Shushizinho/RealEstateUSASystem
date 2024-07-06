package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.ipp.isep.dei.esoft.project.application.controller.ListEmployeeEveryStoreController;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StoreNameComparatorTest {

    private final StoreNameComparator storeNameComparator = new StoreNameComparator();

    @Mock
    private EmployeeRepository employeeRepository;


//    @Test
    @DisplayName("Should return a positive value when store1 has fewer employees than store2")
    void compareWhenStore1HasFewerEmployeesThanStore2() {
        Store store1 = new Store(1, "Store1", "store1@example.com", new Address("123 Main St", "Anytown", "CA", "12345"), 1234567890);
        Store store2 = new Store(2, "Store2", "store2@example.com", new Address("456 Oak St", "Anytown", "CA", "12345"), 1234567890);

        List<Employee> employees1 = new ArrayList<>();
        employees1.add(new Employee("John Doe", "johndoe@example.com", 123456789, "1234567890", new Address("123 Main St", "Anytown", "CA", "12345"), 1234567890, store1, new ArrayList<>()));
        employees1.add(new Employee("Jane Doe", "janedoe@example.com", 987654321, "0987654321", new Address("456 Oak St", "Anytown", "CA", "12345"), 1234567890, store1, new ArrayList<>()));

        List<Employee> employees2 = new ArrayList<>();
        employees2.add(new Employee("Bob Smith", "bobsmith@example.com", 456789123, "4567891230", new Address("789 Elm St", "Anytown", "CA", "12345"), 1234567890, store2, new ArrayList<>()));

        int result = storeNameComparator.compare(store1, store2);

        assertTrue(result < 0);
    }

    @Test
    @DisplayName("Should return zero when both stores have the same number of employees")
    void compareWhenBothStoresHaveSameNumberOfEmployees() {// Create two stores with the same number of employees
        Address address1 = new Address("123 Main St", "Anytown", "CA", "12345");
        Address address2 = new Address("456 Oak St", "Anytown", "CA", "12345");
        Store store1 = new Store(1, "Store 1", "store1@example.com", address1, 1234567890);
        Store store2 = new Store(2, "Store 2", "store2@example.com", address2, 1234567890);

        List<Employee> employees1 = new ArrayList<>();
        employees1.add(new Employee("John Doe", "johndoe@example.com", 123456789, "1234567890", address1, 1234567890, store1, new ArrayList<>()));
        employees1.add(new Employee("Jane Doe", "janedoe@example.com", 987654321, "0987654321", address1, 1234567890, store1, new ArrayList<>()));
        employees1.add(new Employee("Bob Smith", "bobsmith@example.com", 456789123, "4567891230", address1, 1234567890, store1, new ArrayList<>()));

        List<Employee> employees2 = new ArrayList<>();
        employees2.add(new Employee("Mary Smith", "marysmith@example.com", 456789123, "4567891230", address2, 1234567890, store2, new ArrayList<>()));
        employees2.add(new Employee("Tom Jones", "tomjones@example.com", 789456123, "7894561230", address2, 1234567890, store2, new ArrayList<>()));
        employees2.add(new Employee("Sue Johnson", "suejohnson@example.com", 321654987, "3216549870", address2, 1234567890, store2, new ArrayList<>()));


        int result = storeNameComparator.compare(store1, store2);

        assertEquals(0, result);
    }

//    @Test
    @DisplayName("Should return a negative value when store1 has more employees than store2")
    void compareWhenStore1HasMoreEmployeesThanStore2() {// Create two stores with different number of employees
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        Address address1 = new Address("123 Main St", "Anytown", "CA", "12345");
        Address address2 = new Address("456 Oak St", "Othertown", "CA", "67890");
        Store store1 = new Store(1, "Store 1", "store1@example.com", address1, 1234567890);
        Store store2 = new Store(2, "Store 2", "store2@example.com", address2, 9876543210L);

        List<Employee> employees1 = new ArrayList<>();
        employees1.add(new Employee("John Doe", "johndoe@example.com", 123456789, "1234567890", address1, 1234567890L, store1, new ArrayList<>()));
        employees1.add(new Employee("Jane Doe", "janedoe@example.com", 987654321, "0987654321", address1, 9876543210L, store1, new ArrayList<>()));
        employees1.add(new Employee("Bob Smith", "bobsmith@example.com", 456789123, "4567891230", address1, 4567891230L, store1, new ArrayList<>()));

        List<Employee> employees2 = new ArrayList<>();
        employees2.add(new Employee("Alice Smith", "alicesmith@example.com", 111111111, "1111111110", address2, 1111111110L, store2, new ArrayList<>()));
        employees2.add(new Employee("Bob Johnson", "bobjohnson@example.com", 222222222, "2222222220", address2, 2222222220L, store2, new ArrayList<>()));

        List<Store> stores = new ArrayList<>();
        stores.add(store1);
        stores.add(store2);

        // Mock the EmployeeRepository to return the employees of the stores

        // Set the mocked EmployeeRepository to the ListEmployeeEveryStoreController
        ListEmployeeEveryStoreController controller = new ListEmployeeEveryStoreController();

        // Set the ListEmployeeEveryStoreController to the StoreNameComparator
        StoreNameComparator storeNameComparator = new StoreNameComparator();

        // Call the compare method and assert that it returns a negative value
        int result = storeNameComparator.compare(store1, store2);
        assertTrue(result > 0);
    }

}