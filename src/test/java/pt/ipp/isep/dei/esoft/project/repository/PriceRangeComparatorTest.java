package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceRangeComparatorTest {

    @Test
    @DisplayName("Should return 0 when the property price is greater than or equal to the maximum price range")
    void compareWhenPropertyPriceIsGreaterThanOrEqualToMax() {
        Address address = new Address("Rua da Constituição", "Porto", "Portugal", "4200-192");
        Store store = new Store(1, "Store1", "store1@example.com", address, 123456789);
        Property property = new Property(100, 100, address, 100, (List<Photograph>) null, (PropertyType) null, store, (Client) null, null);
        PriceRangeComparator comparator = new PriceRangeComparator(0, 100);
        assertEquals(0, comparator.compare(property));

    }

}