package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type Property type test.
 */
class PropertyTypeTest {

    /**
     * Test equals same object.
     */
//Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        assertEquals(propertyType , propertyType );
    }

    /**
     * Test equals different class.
     */
    @Test
    void testEqualsDifferentClass() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        assertNotEquals("", propertyType );
    }

    /**
     * Test equals null.
     */
    @Test
    void testEqualsNull() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        assertNotEquals(null, propertyType );
    }

    /**
     * Test equals different object.
     */
    @Test
    void testEqualsDifferentObject() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        PropertyType  propertyType1 = new PropertyType ("Property Type Descreption 1");
        assertNotEquals(propertyType , propertyType1);
    }

    /**
     * Test equals same object different description.
     */
    @Test
    void testEqualsSameObjectDifferentDescription() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        PropertyType  propertyType1 = new PropertyType ("Property Type Descreption 1");
        assertNotEquals(propertyType , propertyType1);
    }

    /**
     * Test equals same object same description.
     */
    @Test
    void testEqualsSameObjectSameDescription() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        PropertyType  propertyType1 = new PropertyType ("Property Type Descreption");
        assertEquals(propertyType , propertyType1);
    }

    /**
     * Test hash code same object.
     */
    @Test
    void testHashCodeSameObject() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        assertEquals(propertyType .hashCode(), propertyType .hashCode());
    }

    /**
     * Test hash code different object.
     */
    @Test
    void testHashCodeDifferentObject() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        PropertyType  propertyType1 = new PropertyType ("Property Type Descreption 1");
        assertNotEquals(propertyType .hashCode(), propertyType1.hashCode());
    }

    /**
     * Test hash code same object different description.
     */
    @Test
    void testHashCodeSameObjectDifferentDescription() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        PropertyType  propertyType1 = new PropertyType ("Property Type Descreption 1");
        assertNotEquals(propertyType .hashCode(), propertyType1.hashCode());
    }

    /**
     * Test hash code same object same description.
     */
    @Test
    void testHashCodeSameObjectSameDescription() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        PropertyType  propertyType1 = new PropertyType ("Property Type Descreption");
        assertEquals(propertyType .hashCode(), propertyType1.hashCode());
    }

    /**
     * Test equals for different object type.
     */
    @Test
    void testEqualsForDifferentObjectType() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        assertNotEquals(propertyType , new Object());
    }

    /**
     * Ensure get description works.
     */
    @Test
    void ensureGetDescriptionWorks() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");

        assertEquals("Property Type Descreption", propertyType .getDescription());
    }


    /**
     * Ensure clone works.
     */
    @Test
    void ensureCloneWorks() {
        PropertyType  propertyType  = new PropertyType ("Property Type Descreption");
        PropertyType  clone = propertyType .clone();
        assertEquals(propertyType , clone);
    }

}