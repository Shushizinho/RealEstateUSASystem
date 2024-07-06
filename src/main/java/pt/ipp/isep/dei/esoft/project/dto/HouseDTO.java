package pt.ipp.isep.dei.esoft.project.dto;

import java.util.List;

/**
 * The type House dto.
 */
public class HouseDTO  {
    private boolean hasBasement;
    private boolean hasLoft;
    private String sunExposure;
    private InhabitableDTO inhabitable;


    /**
     * Instantiates a new House dto.
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
    public HouseDTO(double price, double area, AddressDTO address, double distanceToCentre, List<PhotographDTO> photographs, int bedroomNumber, int bathroomNumber, int parkingSpaceNumber, List<String> equipmentList, boolean hasBasement, boolean hasLoft, String sunExposure,
                    PropertyTypeDTO propertyType, StoreDTO store, ClientDTO client, DateTimeDTO date) {

        this.inhabitable = new InhabitableDTO(price, area, address, distanceToCentre, photographs, bedroomNumber, bathroomNumber, parkingSpaceNumber, equipmentList, propertyType, store, client, date);
        this.hasBasement = hasBasement;
        this.hasLoft = hasLoft;
        this.sunExposure = sunExposure;

    }

    /**
     * Instantiates a new House dto.
     *
     * @param hasBasement the has basement
     * @param hasLoft     the has loft
     * @param sunExposure the sun exposure
     * @param inhabitable the inhabitable
     */
    public HouseDTO(boolean hasBasement, boolean hasLoft, String sunExposure, InhabitableDTO inhabitable) {
        this.hasBasement = hasBasement;
        this.hasLoft = hasLoft;
        this.sunExposure = sunExposure;
        this.inhabitable = inhabitable;
    }

    /**
     * Is has basement boolean.
     *
     * @return the boolean
     */
    public boolean getHasBasement() {
        return hasBasement;
    }

    /**
     * Sets has basement.
     *
     * @param hasBasement the has basement
     */
    public void setHasBasement(boolean hasBasement) {
        this.hasBasement = hasBasement;
    }

    /**
     * Is has loft boolean.
     *
     * @return the boolean
     */
    public boolean getHasLoft() {
        return hasLoft;
    }

    /**
     * Sets has loft.
     *
     * @param hasLoft the has loft
     */
    public void setHasLoft(boolean hasLoft) {
        this.hasLoft = hasLoft;
    }

    /**
     * Gets sun exposure.
     *
     * @return the sun exposure
     */
    public String getSunExposure() {
        return sunExposure;
    }

    /**
     * Sets sun exposure.
     *
     * @param sunExposure the sun exposure
     */
    public void setSunExposure(String sunExposure) {
        this.sunExposure = sunExposure;
    }

    /**
     * Gets inhabitable.
     *
     * @return the inhabitable
     */
    public InhabitableDTO getInhabitable() {
        return inhabitable;
    }

    /**
     * Sets inhabitable.
     *
     * @param inhabitable the inhabitable
     */
    public void setInhabitable(InhabitableDTO inhabitable) {
        this.inhabitable = inhabitable;
    }



}

