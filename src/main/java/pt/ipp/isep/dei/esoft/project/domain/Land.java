package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a Land-type Property.
 */
public class Land implements Serializable {
    private Property property;

    /**
     * Instantiates a new Land.
     *
     * @param price            the price
     * @param area             the area
     * @param address          the address
     * @param distanceToCentre the distance to centre
     * @param photographs      the photographs
     * @param propertyType     the property type
     * @param store            the store
     * @param client           the client
     * @param date             the date
     */
    public Land(double price,double area, Address address, double distanceToCentre, List<Photograph> photographs,
                PropertyType propertyType,Store store, Client client, DateTime date) {
        this.property = new Property(price, area, address, distanceToCentre, photographs, propertyType, store, client, date);
    }

    /**
     * Instantiates a new Land.
     *
     * @param property the property
     */
    public Land(Property property) {
        this.property = property;
    }

    /**
     * Gets property.
     *
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current Inhabitable Property.
     */

    @Override
    public Land clone() {
        return new Land(
                this.getProperty()
        );
//        this.agent = agent,
//        this.client = client);
    }


}
