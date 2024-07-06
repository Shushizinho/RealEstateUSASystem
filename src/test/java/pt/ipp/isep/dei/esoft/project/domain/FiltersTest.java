package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type Filters test.
 */
class FiltersTest {

    /**
     * Test equals same object.
     */
//Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
        Filters filter = new Filters("Filters Descreption");
        assertEquals(filter, filter);
    }

    /**
     * Test equals different class.
     */
    @Test
    void testEqualsDifferentClass() {
        Filters filter = new Filters("Filters Descreption");
        assertNotEquals("", filter);
    }

    /**
     * Test equals null.
     */
    @Test
    void testEqualsNull() {
        Filters filter = new Filters("Filters Descreption");
        assertNotEquals(null, filter);
    }

    /**
     * Test equals different object.
     */
    @Test
    void testEqualsDifferentObject() {
        Filters filter = new Filters("Filters Descreption");
        Filters filter1 = new Filters("Filters Descreption 1");
        assertNotEquals(filter, filter1);
    }

    /**
     * Test equals same object different description.
     */
    @Test
    void testEqualsSameObjectDifferentDescription() {
        Filters filter = new Filters("Filters Descreption");
        Filters filter1 = new Filters("Filters Descreption 1");
        assertNotEquals(filter, filter1);
    }

    /**
     * Test equals same object same description.
     */
    @Test
    void testEqualsSameObjectSameDescription() {
        Filters filter = new Filters("Filters Descreption");
        Filters filter1 = new Filters("Filters Descreption");
        assertEquals(filter, filter1);
    }

    /**
     * Test hash code same object.
     */
    @Test
    void testHashCodeSameObject() {
        Filters filter = new Filters("Filters Descreption");
        assertEquals(filter.hashCode(), filter.hashCode());
    }

    /**
     * Test hash code different object.
     */
    @Test
    void testHashCodeDifferentObject() {
        Filters filter = new Filters("Filters Descreption");
        Filters filter1 = new Filters("Filters Descreption 1");
        assertNotEquals(filter.hashCode(), filter1.hashCode());
    }

    /**
     * Test hash code same object different description.
     */
    @Test
    void testHashCodeSameObjectDifferentDescription() {
        Filters filter = new Filters("Filters Descreption");
        Filters filter1 = new Filters("Filters Descreption 1");
        assertNotEquals(filter.hashCode(), filter1.hashCode());
    }

    /**
     * Test hash code same object same description.
     */
    @Test
    void testHashCodeSameObjectSameDescription() {
        Filters filter = new Filters("Filters Descreption");
        Filters filter1 = new Filters("Filters Descreption");
        assertEquals(filter.hashCode(), filter1.hashCode());
    }

    /**
     * Test equals for different object type.
     */
    @Test
    void testEqualsForDifferentObjectType() {
        Filters filter = new Filters("Filters Descreption");
        assertNotEquals(filter, new Object());
    }

    /**
     * Ensure get description works.
     */
    @Test
    void ensureGetDescriptionWorks() {
        Filters filter = new Filters("Filters Descreption");

        assertEquals("Filters Descreption", filter.getDescription());
    }


    /**
     * Ensure clone works.
     */
    @Test
    void ensureCloneWorks() {
        Filters filter = new Filters("Filters Descreption");
        Filters clone = filter.clone();
        assertEquals(filter, clone);
    }


}