package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;
import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class PropertyRepositoryTest {

    private PropertyRepository propertyRepository;

    private Repositories repositories;

    @BeforeEach
    void setUp() {
        propertyRepository = new PropertyRepository();
        repositories = Repositories.getInstance();

    }

    @Test
    void testAddHouse() {
        House house = createMockHouse(); // Create a mock House object for testing
        assertTrue(propertyRepository.add(house)); // Test adding a house
        assertEquals(1, propertyRepository.getHouses().size()); // Check if the house was added
        assertEquals(1, propertyRepository.getProperties().size()); // Check if the property list contains the house
    }

//    @Test
//    void testGetFilters() {
//        List<Filters> filters = repositories.propertyFiltersRepository.getFilters();
//        // Assert that the number of filters retrieved is as expected
//        assertEquals(1, filters.size()); // Adjust the expected size based on your test data
//    }
//    @Test
//    void testSortPropertyListByPrice() {
//        // Create mock properties for testing
//        List<Property> properties = createMockProperties();
//        String filter = "Price"; // Example filter
//
//        List<Property> sortedProperties = propertyRepository.sortPropertyList(filter, properties);
//
//
//
//        // Assert that sortedProperties are sorted correctly based on the chosen filter
//        // Implement your assertion logic here
//    }

    @Test
    void testGetPropertiesByPropertyType() {
        // Create mock properties for testing
        List<Property> properties = createMockProperties();
        String propertyType = "House"; // Example property type

        List<Property> filteredProperties = propertyRepository.getPropertiesByPropertyType(propertyType, properties);

        // Assert that filteredProperties contain only properties of the specified type
        // Implement your assertion logic here
    }

    @Test
    void testRemoveProperty() {
        House house = createMockHouse(); // Create a mock House object for testing
        propertyRepository.add(house); // Add the house to the repository

        propertyRepository.remove(house.getInhabitable().getProperty()); // Remove the house

        assertFalse(propertyRepository.getHouses().contains(house)); // Assert that the house is removed
        assertFalse(propertyRepository.getProperties().contains(house.getInhabitable().getProperty())); // Assert that the property list doesn't contain the house
    }

    // Helper methods for creating mock objects
    private House createMockHouse() {
        // Implement logic to create a mock House object
        return new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
            List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
            new Store(10, "PPROG",  "pprog@this.app", new Address("teste","teste","teste","teste"),000000000),
            new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),  new DateTime());

    }

    private List<Property> createMockProperties() {

        House house = new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG",  "pprog@this.app", new Address("teste","teste","teste","teste"),000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),  new DateTime());
        Land property1 = new Land(3.1, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
            new Store(10, "PPROG","pprog@this.app",  new Address("teste","teste","teste","teste"), 000000000),
            new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        ArrayList<Property> list = new ArrayList<>();
        list.add(house.getInhabitable().getProperty());
        list.add(property1.getProperty());
        return list;
    }
}
