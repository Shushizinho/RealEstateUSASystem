package pt.ipp.isep.dei.esoft.project.dto;

import java.util.List;

/**
 * The type Inhabitable dto.
 */
public class InhabitableDTO {
    private int bedroomNumber;
    private int bathroomNumber;
    private int parkingSpaceNumber;

    private PropertyDTO property;
    private List<String> equipmentList;


    /**
     * Instantiates a new Inhabitable dto.
     *
     * @param price              the price
     * @param area               the area
     * @param addressDTO         the address dto
     * @param distanceToCentre   the distance to centre
     * @param photographs        the photographs
     * @param bedroomNumber      the bedroom number
     * @param bathroomNumber     the bathroom number
     * @param parkingSpaceNumber the parking space number
     * @param equipmentList      the equipment list
     * @param propertyType       the property type
     * @param store              the store
     * @param client             the client
     * @param date               the date
     */
    public InhabitableDTO(double price, double area, AddressDTO addressDTO, double distanceToCentre, List<PhotographDTO> photographs, int bedroomNumber, int bathroomNumber, int parkingSpaceNumber, List<String> equipmentList,
                          PropertyTypeDTO propertyType, StoreDTO store, ClientDTO client, DateTimeDTO date) {
        this.property = new PropertyDTO(price, area, addressDTO, distanceToCentre, photographs, propertyType, store, client, date);
        this.bedroomNumber = bedroomNumber;
        this.bathroomNumber = bathroomNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
        this.equipmentList = equipmentList;


    }


    /**
     * Instantiates a new Inhabitable dto.
     *
     * @param property           the property
     * @param bedroomNumber      the bedroom number
     * @param bathroomNumber     the bathroom number
     * @param parkingSpaceNumber the parking space number
     * @param equipmentList      the equipment list
     */
    public InhabitableDTO(PropertyDTO property, int bedroomNumber, int bathroomNumber, int parkingSpaceNumber, List<String> equipmentList) {
        this.bedroomNumber = bedroomNumber;
        this.bathroomNumber = bathroomNumber;
        this.parkingSpaceNumber = parkingSpaceNumber;
        this.property = property;
        this.equipmentList = equipmentList;
    }

    /**
     * Gets bedroom number.
     *
     * @return the bedroom number
     */
    public int getBedroomNumber() {
        return bedroomNumber;
    }

    /**
     * Sets bedroom number.
     *
     * @param bedroomNumber the bedroom number
     */
    public void setBedroomNumber(int bedroomNumber) {
        this.bedroomNumber = bedroomNumber;
    }

    /**
     * Gets bathroom number.
     *
     * @return the bathroom number
     */
    public int getBathroomNumber() {
        return bathroomNumber;
    }

    /**
     * Sets bathroom number.
     *
     * @param bathroomNumber the bathroom number
     */
    public void setBathroomNumber(int bathroomNumber) {
        this.bathroomNumber = bathroomNumber;
    }

    /**
     * Gets parking space number.
     *
     * @return the parking space number
     */
    public int getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    /**
     * Sets parking space number.
     *
     * @param parkingSpaceNumber the parking space number
     */
    public void setParkingSpaceNumber(int parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    /**
     * Gets property.
     *
     * @return the property
     */
    public PropertyDTO getProperty() {
        return property;
    }

    /**
     * Sets property.
     *
     * @param property the property
     */
    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    /**
     * Gets equipment list.
     *
     * @return the equipment list
     */
    public List<String> getEquipmentList() {
        return equipmentList;
    }

    /**
     * Sets equipment list.
     *
     * @param equipmentList the equipment list
     */
    public void setEquipmentList(List<String> equipmentList) {
        this.equipmentList = equipmentList;
    }







}
