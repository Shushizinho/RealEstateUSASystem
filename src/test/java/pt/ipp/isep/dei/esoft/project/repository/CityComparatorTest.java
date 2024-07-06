package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.Assert;
import org.junit.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;


import java.util.ArrayList;
import java.util.List;


import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type City comparator test.
 */
public class CityComparatorTest {
    private final Property p1 =  new Property(1.0d, 5.3d, new Address("streetAddress", "teste", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
            new Client( "Person","person@this.app", 2,"111-11-0439", new Address("teste","teste","teste","teste"), parseLong("00000004") ), new DateTime());

    private final Property p2 =  new Property(1.0d, 5.3d, new Address("streetAddress", "New York", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
            new Client( "Person","person@this.app", 2,"111-11-0439", new Address("teste","teste","teste","teste"), parseLong("00000004") ), new DateTime());

    private final Property p3 =  new Property(1.0d, 5.3d, new Address("streetAddress", "New York", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
            new Client( "Person","person@this.app", 2,"111-11-0439", new Address("teste","teste","teste","teste"), parseLong("00000004") ), new DateTime());

    private final Property p4 =  new Property(1.0d, 5.3d, new Address("streetAddress", "uuuu", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
            new Client( "Person","person@this.app", 2,"111-11-0439", new Address("teste","teste","teste","teste"), parseLong("00000004") ), new DateTime());


    /**
     * Ensure city comparator returns null.
     */
    @Test
    public void ensureCityComparatorReturnsNull() {
        List<Property> properties = new ArrayList<>();
        properties.add(p2);
        properties.add(p3);

        properties.sort(new CityComparator());

        Assert.assertEquals(p2, properties.get(0));
        Assert.assertEquals(p3, properties.get(1));
    }


    /**
     * Ensure city comparator returns positive.
     */
    @Test
    public void ensureCityComparatorReturnsPositive() {
        List<Property> properties = new ArrayList<>();
        properties.add(p2);
        properties.add(p1);

        properties.sort(new CityComparator());

        Assert.assertEquals(p2, properties.get(0));
        Assert.assertEquals(p1, properties.get(1));
    }


    /**
     * Ensure city comparator returns negative.
     */
    @Test
    public void ensureCityComparatorReturnsNegative() {
        List<Property> properties = new ArrayList<>();
        properties.add(p1);
        properties.add(p4);

        properties.sort(new CityComparator());

        Assert.assertEquals(p1, properties.get(0));
        Assert.assertEquals(p4, properties.get(1));
    }
}
