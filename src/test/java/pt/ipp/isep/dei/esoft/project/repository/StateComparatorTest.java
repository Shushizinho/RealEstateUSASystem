package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.Assert;
import org.junit.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

/**
 * The type State comparator test.
 */
public class StateComparatorTest {
    private final Property p1 =  new Property(1.0d, 5.3d, new Address("streetAddress", "teste", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
            new Client( "Person","person@this.app", 2,"111-11-0439", new Address("teste","teste","teste","teste"), parseLong("00000004") ), new DateTime());

    private final Property p2 =  new Property(1.0d, 5.3d, new Address("streetAddress", "New York", "state", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
            new Client( "Person","person@this.app", 2,"111-11-0439", new Address("teste","teste","teste","teste"), parseLong("00000004") ), new DateTime());

    private final Property p3 =  new Property(1.0d, 5.3d, new Address("streetAddress", "New York", "state", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
            new Client( "Person","person@this.app", 2,"111-11-0439", new Address("teste","teste","teste","teste"), parseLong("00000004") ), new DateTime());

    private final Property p4 =  new Property(1.0d, 5.3d, new Address("streetAddress", "uuuu", "uuu", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000),
            new Client( "Person","person@this.app", 2,"111-11-0439", new Address("teste","teste","teste","teste"), parseLong("00000004") ), new DateTime());


    /**
     * Ensure state comparator returns null.
     */
    @Test
    public void ensureStateComparatorReturnsNull() {
        List<Property> properties = new ArrayList<>();
        properties.add(p2);
        properties.add(p3);

        properties.sort(new CityComparator());

        Assert.assertEquals(p2, properties.get(0));
        Assert.assertEquals(p3, properties.get(1));
    }


    /**
     * Ensure state comparator returns positive.
     */
    @Test
    public void ensureStateComparatorReturnsPositive() {
        List<Property> properties = new ArrayList<>();
        properties.add(p2);
        properties.add(p1);

        properties.sort(new CityComparator());

        Assert.assertEquals(p2, properties.get(0));
        Assert.assertEquals(p1, properties.get(1));
    }


    /**
     * Ensure state comparator returns negative.
     */
    @Test
    public void ensureStateComparatorReturnsNegative() {
        List<Property> properties = new ArrayList<>();
        properties.add(p1);
        properties.add(p4);

        properties.sort(new CityComparator());

        Assert.assertEquals(p1, properties.get(0));
        Assert.assertEquals(p4, properties.get(1));
    }
}


