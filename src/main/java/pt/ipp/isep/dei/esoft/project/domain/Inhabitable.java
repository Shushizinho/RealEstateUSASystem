package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Inhabitable Property.
 */
public class Inhabitable implements Serializable {
    private int bedroomNumber;
    private int bathroomNumber;
    private int parkingSpaceNumber;

    private Property property;
    private List<String> equipmentList = new ArrayList<>();

    public Inhabitable(double price, double area, Address address, double distanceToCentre,
                       List<Photograph> photographs, int bedroomNumber, int bathroomNumber,
                       int parkingSpaceNumber, List<String> equipmentList,
                       PropertyType propertyType, Store store, Client client, DateTime date) {
        this.property = new Property(price, area, address, distanceToCentre, photographs, propertyType, store, client, date);
        this.bedroomNumber = bedroomNumber;
        this.bathroomNumber = bathroomNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
        this.equipmentList = equipmentList;
    }
    public Inhabitable(Property property, int bedroomNumber, int bathroomNumber, int parkingSpaceNumber, List<String> equipmentList) {
        this.property = property;
        this.bedroomNumber = bedroomNumber;
        this.bathroomNumber = bathroomNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
        this.equipmentList = equipmentList;
    }

    public Property getProperty() {
        return property;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current Inhabitable Property.
     */

    @Override
    public Inhabitable clone() {
        return new Inhabitable(
                this.getProperty(),
                this.bedroomNumber,
                this.bathroomNumber,
                this.parkingSpaceNumber,
                this.equipmentList
        );
//        this.agent = agent,
//        this.client = client);
    }

    /**
     * This method returns the number of Bedrooms of the current Property.
     *
     * @return      the Property's number of Bedrooms.
     */

    public int getBedroomNumber() {
        return bedroomNumber;
    }

    /**
     * This method returns the number of Bathrooms of the current Property.
     *
     * @return      the Property's number of Bathrooms.
     */

    public int getBathroomNumber() {
        return bathroomNumber;
    }

    /**
     * This method returns the number of Parking Spaces of the current Property.
     *
     * @return      the Property's number of Parking Spaces.
     */
    public int getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public List<String> getEquipmentList() {
        return equipmentList;
    }


}
