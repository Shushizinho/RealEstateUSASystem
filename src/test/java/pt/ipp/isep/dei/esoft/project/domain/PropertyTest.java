package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Property test.
 */
class PropertyTest {

    /**
     * Test equals same object.
     */
//Tests for equals and hashcode
    @Test
    void testEqualsSameObject() {
        House property =new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG",  "pprog@this.app", new Address("teste","teste","teste","teste"),000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),  new DateTime());
        assertEquals(property, property);
    }

    /**
     * Test equals different class.
     */
    @Test
    void testEqualsDifferentClass() {
        House property = new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG",  "pprog@this.app",new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        assertNotEquals("", property);
    }

    /**
     * Test equals null.
     */
    @Test
    void testEqualsNull() {
        House property =new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG",  "pprog@this.app",new Address("teste","teste","teste","teste"),000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        assertNotEquals(null, property);
    }

    /**
     * Test equals different object.
     */
    @Test
    void testEqualsDifferentObject() {
        House property = new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG","pprog@this.app",  new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        Land property1 = new Land(3.1, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
                new Store(10, "PPROG","pprog@this.app",  new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        assertNotEquals(property, property1);
    }

    /**
     * Test equals same object different description.
     */
    @Test
    void testEqualsSameObjectDifferentDescription() {
        House property =new House(2.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG",  "pprog@this.app",new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        Land property1 = new Land(3.1, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
                new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime())
;
        assertNotEquals(property, property1);
    }

    /**
     * Test equals same object same description.
     */
    @Test
    void testNotEqualsSameObjectSameDescription() {
        House property = new House(2.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG",  "pprog@this.app",new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        House property1 = new House(2.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG",  "pprog@this.app", new Address("teste","teste","teste","teste"),000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        assertNotEquals(property, property1);
    }

    /**
     * Test hash code same object.
     */
    @Test
    void testHashCodeSameObject() {
        House property =new House(2.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG","pprog@this.app", new Address("teste","teste","teste","teste"),  000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        assertEquals(property.hashCode(), property.hashCode());
    }

    /**
     * Test hash code different object.
     */
    @Test
    void testHashCodeDifferentObject() {
        House property = new House(2.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG", "pprog@this.app",  new Address("teste","teste","teste","teste"),000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        Land property1 = new Land(3.1, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
                new Store(10, "PPROG",  "pprog@this.app", new Address("teste","teste","teste","teste"),000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        assertNotEquals(property.hashCode(), property1.hashCode());
    }


    /**
     * Test hash code same object same description.
     */
//    @Test
    void testHashCodeSameObjectSameDescription() {
        House property = new House(2.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG","pprog@this.app",  new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        House property1 = new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());

        assertEquals(property.hashCode(), property1.hashCode());
    }

    /**
     * Test equals for different object type.
     */
    @Test
    void testEqualsForDifferentObjectType() {
        House property = new House(2.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG", "pprog@this.app",new Address("teste","teste","teste","teste"), 000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());;
        assertNotEquals(property, new Object());
    }


    /**
     * Ensure clone works.
     */
//    @Test
    void ensureCloneWorks() {
        House property = new House(2.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG", "pprog@this.app",  new Address("teste","teste","teste","teste"),000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());
        House clone = property.clone();
        assertEquals(property, clone);
    }


}