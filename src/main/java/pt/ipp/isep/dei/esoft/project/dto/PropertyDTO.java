package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Property dto.
 */
public class PropertyDTO {

    private double price;
    private double area;
    private AddressDTO address;
    private double distanceToCentre;
    private List<PhotographDTO> photographs;
    private PropertyTypeDTO propertyType;
    private StoreDTO store;
    private ClientDTO client;

    private DateTimeDTO date;

    /**
     * Instantiates a new Property dto.
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
    public PropertyDTO(double price, double area, AddressDTO address, double distanceToCentre,
                       List<PhotographDTO> photographs, PropertyTypeDTO propertyType, StoreDTO store, ClientDTO client, DateTimeDTO date) {
        this.price = price;
        this.area = area;
        this.address = address;
        this.distanceToCentre = distanceToCentre;
        this.photographs = photographs;
        this.propertyType = propertyType;
        this.store = store;
        this.client = client;
        this.date = date;
    }

    /**
     * Instantiates a new Property dto.
     *
     * @param property the property
     */
    public PropertyDTO(Property property) {
        this.price = property.getPrice();
        this.area = property.getArea();
        this.address = new AddressDTO(property.getAddress().getStreetAddress(),property.getAddress().getCity(),property.getAddress().getState(),property.getAddress().getZipCode());
        this.distanceToCentre = property.getDistanceToCentre();
        this.photographs = new ArrayList<>();
        this.propertyType = new PropertyTypeDTO(property.getPropertyType().getDescription());
        this.store = new StoreDTO((int) property.getStore().getId(),property.getStore().getDesignation(),new AddressDTO(property.getStore().getAddress().getStreetAddress(),
                property.getStore().getAddress().getCity(), property.getStore().getAddress().getState(), property.getStore().getAddress().getZipCode()),property.getStore().getEmail(),property.getStore().getPhoneNumber());
        this.client = client;
        this.date = date;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Gets distance to centre.
     *
     * @return the distance to centre
     */
    public double getDistanceToCentre() {
        return distanceToCentre;
    }

    /**
     * Gets photographs.
     *
     * @return the photographs
     */
    public List<PhotographDTO> getPhotographs() {
        return photographs;
    }

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public PropertyTypeDTO getPropertyType() {
        return propertyType;
    }

    /**
     * Gets store.
     *
     * @return the store
     */
    public StoreDTO getStore() {
        return store;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public ClientDTO getClient() {
        return client;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public DateTimeDTO getDate() { return date; }

    /**
     * To string dto string.
     *
     * @return the string
     */
    public String toStringDTOFull() {
        return String.format("\n Price: %.2f;" + "\n Area: %.2f;"+ "\n Address: %s;"+ "\n Distance to Center: %.2f;" +
                        " %s;" + "\n Store: %s;"+ "\n client: %s;" + "\n Date: %s",this.price, this.area, address.toString(), this.distanceToCentre,
                propertyType.toString(), store.toString() , client.toString(), this.date.toStringDTO());
    }

    /**
     * This method returns a description of the Property attributes.
     *
     * @return A formated string composed of the Property's attributes
     */
    public String toStringDTO() {
        return String.format("%s - $%.2f - %.2f ft\u00b2 - %s - %s", this.propertyType.toString(), this.price, this.area, this.address.toString(), this.store.getDesignation());
    }



}
