package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type Business type test.
 */
class BusinessTypeTest {

    /**
     * Test equals same object.
     */
//Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        assertEquals(businessType , businessType );
    }

    /**
     * Test equals different class.
     */
    @Test
    void testEqualsDifferentClass() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        assertNotEquals("", businessType );
    }

    /**
     * Test equals null.
     */
    @Test
    void testEqualsNull() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        assertNotEquals(null, businessType );
    }

    /**
     * Test equals different object.
     */
    @Test
    void testEqualsDifferentObject() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        BusinessType  businessType1 = new BusinessType ("Property Type Descreption 1","Property Type Descreption");
        assertNotEquals(businessType , businessType1);
    }

    /**
     * Test equals same object different description.
     */
    @Test
    void testEqualsSameObjectDifferentDescription() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        BusinessType  businessType1 = new BusinessType ("Property Type Descreption 1","Property Type Descreption");
        assertNotEquals(businessType , businessType1);
    }

    /**
     * Test equals same object same description.
     */
    @Test
    void testEqualsSameObjectSameDescription() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        BusinessType  businessType1 = new BusinessType ("Property Type Descreption","Property Type Descreption");
        assertEquals(businessType , businessType1);
    }

    /**
     * Test hash code same object.
     */
    @Test
    void testHashCodeSameObject() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        assertEquals(businessType .hashCode(), businessType .hashCode());
    }

    /**
     * Test hash code different object.
     */
    @Test
    void testHashCodeDifferentObject() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        BusinessType  businessType1 = new BusinessType ("Property Type Descreption 1","Property Type Descreption");
        assertNotEquals(businessType .hashCode(), businessType1.hashCode());
    }

    /**
     * Test hash code same object different description.
     */
    @Test
    void testHashCodeSameObjectDifferentDescription() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        BusinessType  businessType1 = new BusinessType ("Property Type Descreption 1","Property Type Descreption");
        assertNotEquals(businessType .hashCode(), businessType1.hashCode());
    }

    /**
     * Test hash code same object same description.
     */
    @Test
    void testHashCodeSameObjectSameDescription() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        BusinessType  businessType1 = new BusinessType ("Property Type Descreption","Property Type Descreption");
        assertEquals(businessType .hashCode(), businessType1.hashCode());
    }

    /**
     * Test equals for different object type.
     */
    @Test
    void testEqualsForDifferentObjectType() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        assertNotEquals(businessType , new Object());
    }

    /**
     * Ensure get description works.
     */
    @Test
    void ensureGetDescriptionWorks() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");

        assertEquals("Property Type Descreption", businessType.getDescriptionProperty());
    }

    /**
     * Ensure clone works.
     */
    @Test
    void ensureCloneWorks() {
        BusinessType  businessType  = new BusinessType ("Property Type Descreption","Property Type Descreption");
        BusinessType  clone = businessType .clone();
        assertEquals(businessType , clone);
    }
    
}