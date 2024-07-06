package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Represents an Apartment-type Inhabitable Property.
 */
public class Apartment implements Serializable {
    private Inhabitable inhabitable;
    public Apartment(double price, double area, Address address, double distanceToCentre, List<Photograph> photographs, int bedroomNumber, int bathroomNumber, int parkingSpaceNumber, List<String> equipmentList,
                     PropertyType propertyType, Store store, Client client, DateTime dateTime) {
        this.inhabitable= new Inhabitable(price, area, address, distanceToCentre, photographs, bedroomNumber, bathroomNumber, parkingSpaceNumber, equipmentList, propertyType, store, client, dateTime);
    }
    public Apartment(Inhabitable inhabitable) {
        this.inhabitable = inhabitable;
    }

    public Inhabitable getInhabitable() {
        return inhabitable;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current Apartment Property.
     */
    public Apartment clone() {
        return new Apartment(this.getInhabitable());
//        this.agent = agent,
//        this.client = client);
    }
}
