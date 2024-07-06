package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Filters;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Property filters repository test.
 */
class PropertyFiltersRepositoryTest {


    /**
     * Ensure new property filters successfully added.
     */
    @Test
    void ensureNewPropertyFiltersSuccessfullyAdded() {
        PropertyFiltersRepository PropertyFiltersRepository = new PropertyFiltersRepository();
        String PropertyFiltersDescription = "Filters Description";
        Filters PropertyFilters = new Filters (PropertyFiltersDescription);
        PropertyFiltersRepository.add(PropertyFilters);
    }


    /**
     * Ensure get property filters returns an immutable list.
     */
    @Test
    void ensureGetPropertyFiltersReturnsAnImmutableList() {
        PropertyFiltersRepository PropertyFiltersRepository = new PropertyFiltersRepository();
        String PropertyFiltersDescription = "Filters Description";
        Filters PropertyFilters = new Filters(PropertyFiltersDescription);
        PropertyFiltersRepository.add(PropertyFilters);

        assertThrows(UnsupportedOperationException.class,
                () -> PropertyFiltersRepository.getFilters().add(new Filters("Filters Description 1")));

    }

    /**
     * Ensure get property filters returns the correct list.
     */
    @Test
    void ensureGetPropertyFiltersReturnsTheCorrectList() {
        //Arrange
        PropertyFiltersRepository PropertyFiltersRepository = new PropertyFiltersRepository();
        String PropertyFiltersDescription = "Filters Description";
        Filters  PropertyFilters = new Filters(PropertyFiltersDescription);
        PropertyFiltersRepository.add(PropertyFilters);
        int expectedSize = 1;

        //Act
        int size = PropertyFiltersRepository.getFilters().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(PropertyFilters, PropertyFiltersRepository.getFilters().get(size - 1));
    }

    /**
     * Ensure adding duplicate property filters fails.
     */
    @Test
    void ensureAddingDuplicatePropertyFiltersFails() {
        //Arrange
        PropertyFiltersRepository PropertyFiltersRepository = new PropertyFiltersRepository();
        Filters PropertyFilters = new Filters("Filters Description");
        //Add the first task
        PropertyFiltersRepository.add(PropertyFilters);

        //Act
        Optional<Filters> duplicatePropertyFilters = PropertyFiltersRepository.add(PropertyFilters);

        //Assert
        assertTrue(duplicatePropertyFilters.isEmpty());
    }

    /**
     * Ensure adding different property filters works.
     */
    @Test
    void ensureAddingDifferentPropertyFiltersWorks() {
        //Arrange
        PropertyFiltersRepository PropertyFiltersRepository = new PropertyFiltersRepository();
        Filters PropertyFiltersOne = new Filters("Filters Description One");
        Filters PropertyFiltersTwo = new Filters("Filters Description Two");
        //Add the first task
        PropertyFiltersRepository.add(PropertyFiltersOne);

        //Act
        Optional<Filters> result = PropertyFiltersRepository.add(PropertyFiltersTwo);

        //Assert
        assertEquals(PropertyFiltersTwo, result.get());
    }
}