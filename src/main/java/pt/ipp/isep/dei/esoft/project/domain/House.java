package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Represents an House-type Inhabitable Property.
 */
public class House implements Serializable {
    private boolean hasBasement;
    private boolean hasLoft;
    private String sunExposure;
    private Inhabitable inhabitable;

    /**
     * Instantiates a new House.
     *
     * @param price              the price
     * @param area               the area
     * @param address            the address
     * @param distanceToCentre   the distance to centre
     * @param photographs        the photographs
     * @param bedroomNumber      the bedroom number
     * @param bathroomNumber     the bathroom number
     * @param parkingSpaceNumber the parking space number
     * @param equipmentList      the equipment list
     * @param hasBasement        the has basement
     * @param hasLoft            the has loft
     * @param sunExposure        the sun exposure
     * @param propertyType       the property type
     * @param store              the store
     * @param client             the client
     * @param date               the date
     */
    public House(double price, double area, Address address, double distanceToCentre, List<Photograph> photographs,
                 int bedroomNumber, int bathroomNumber, int parkingSpaceNumber, List<String> equipmentList,
                 boolean hasBasement, boolean hasLoft, String sunExposure,
                 PropertyType propertyType, Store store, Client client, DateTime date) {
        this.inhabitable = new Inhabitable(price, area, address, distanceToCentre, photographs, bedroomNumber, bathroomNumber, parkingSpaceNumber, equipmentList, propertyType, store, client, date);
        this.hasBasement = hasBasement;
        this.hasLoft = hasLoft;
        this.sunExposure = sunExposure;
    }

    /**
     * Instantiates a new House.
     *
     * @param inhabitable the inhabitable
     * @param hasBasement the has basement
     * @param hasLoft     the has loft
     * @param sunExposure the sun exposure
     */
    public House(Inhabitable inhabitable, boolean hasBasement, boolean hasLoft, String sunExposure){
        this.inhabitable = inhabitable;
        this.hasBasement = hasBasement;
        this.hasLoft = hasLoft;
        this.sunExposure = sunExposure;
    }

    /**
     * Instantiates a new House.
     *
     * @param property the property
     */
    public House(Property property) {
    }

    /**
     * Gets inhabitable.
     *
     * @return the inhabitable
     */
    public Inhabitable getInhabitable() {
        return inhabitable;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current House Property.
     */
    public House clone() {
        return new House(
                this.getInhabitable(),
                this.hasBasement,
                this.hasLoft,
                this.sunExposure);
//        this.agent = agent,
//        this.client = client);
    }
}