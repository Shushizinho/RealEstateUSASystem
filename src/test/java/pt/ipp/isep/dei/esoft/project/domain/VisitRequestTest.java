package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type Visit request test.
 */
public class VisitRequestTest {

    private final Property p1 =  new Property(1.0f, 5.3f, new Address("streetAddress", "New York", "Texas", "zipCode"),
            13.2f, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")),
            new PropertyType("House"),
            new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime());

    private final DateTime date1 = new DateTime();

    private  final List<DateTime> timeSlot1 = Arrays.asList(new DateTime(10, 0), new DateTime(11, 0));

    private final String clientName1 = "João";

    private final String clientName2 = "Ana";

    private final Integer clientPhoneNumber1 = 1;


    /**
     * Test get time slot works.
     */
    @Test
    void testGetTimeSlotWorks(){
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);
        assertEquals(VisitRequest.getTimeSlot(0), timeSlot1.get(0));
    }


    /**
     * Test equals same object.
     */
//Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
         VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);     
        assertEquals(VisitRequest, VisitRequest);
    }

    /**
     * Test equals different class.
     */
    @Test
    void testEqualsDifferentClass() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);
        assertNotEquals("", VisitRequest);
    }

    /**
     * Test equals null.
     */
    @Test
    void testEqualsNull() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);
        assertNotEquals(null, VisitRequest);
    }

    /**
     * Test equals different object.
     */
    @Test
    void testEqualsDifferentObject() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);

        VisitRequest VisitRequest1 = new VisitRequest(p1,date1, timeSlot1, clientName2, clientPhoneNumber1);

        assertNotEquals(VisitRequest, VisitRequest1);
    }

    /**
     * Test equals same object different description.
     */
    @Test
    void testEqualsSameObjectDifferentDescription() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);

        VisitRequest VisitRequest1 = new VisitRequest(p1,date1, timeSlot1, clientName2, clientPhoneNumber1);

        assertNotEquals(VisitRequest, VisitRequest1);
    }

    /**
     * Test equals same object same description.
     */
    @Test
    void testEqualsSameObjectSameDescription() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);

        VisitRequest VisitRequest1 = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);

        assertEquals(VisitRequest, VisitRequest1);
    }

    /**
     * Test hash code same object.
     */
    @Test
    void testHashCodeSameObject() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);

        assertEquals(VisitRequest.hashCode(), VisitRequest.hashCode());
    }

    /**
     * Test hash code different object.
     */
    @Test
    void testHashCodeDifferentObject() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);

        VisitRequest VisitRequest1 = new VisitRequest(p1,date1, timeSlot1, clientName2, clientPhoneNumber1);

        assertNotEquals(VisitRequest.hashCode(), VisitRequest1.hashCode());
    }


    /**
     * Test hash code same object same description.
     */
    @Test
    void testHashCodeSameObjectSameDescription() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);

        VisitRequest VisitRequest1 = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);

        assertEquals(VisitRequest.hashCode(), VisitRequest1.hashCode());
    }

    /**
     * Test equals for different object type.
     */
    @Test
    void testEqualsForDifferentObjectType() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);
        assertNotEquals(VisitRequest, new Object());
    }

    /**
     * Ensure clone works.
     */
    @Test
    void ensureCloneWorks() {
        VisitRequest VisitRequest = new VisitRequest(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);
        VisitRequest clone = VisitRequest.clone();
        assertEquals(VisitRequest, clone);
    }
}

