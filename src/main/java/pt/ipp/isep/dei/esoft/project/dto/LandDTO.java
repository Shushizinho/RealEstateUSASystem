package pt.ipp.isep.dei.esoft.project.dto;

import java.util.List;

/**
 * The type Land dto.
 */
public class LandDTO {


    private PropertyDTO property;

    /**
     * Instantiates a new Land dto.
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
    public LandDTO(double price, double area, AddressDTO address, double distanceToCentre, List<PhotographDTO> photographs,
                PropertyTypeDTO propertyType, StoreDTO store, ClientDTO client, DateTimeDTO date) {
        this.property = new PropertyDTO(price, area, address, distanceToCentre, photographs, propertyType, store, client, date);
    }

    /**
     * Instantiates a new Land dto.
     *
     * @param property the property
     */
    public LandDTO(PropertyDTO property) {
        this.property = property;
    }


    /**
     * Gets property.
     *
     * @return the property
     */
    public PropertyDTO getProperty() {
        return property;
    }





}
