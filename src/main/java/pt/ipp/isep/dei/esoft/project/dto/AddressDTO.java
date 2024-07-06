package pt.ipp.isep.dei.esoft.project.dto;

/**
 * The type Address dto.
 */
public class AddressDTO {
    private final String streetAddress;

    private final String city;
    private final String state;
    private final String zipCode;

    /**
     * Instantiates a new Address dto.
     *
     * @param streetAddress the street address
     * @param city          the city
     * @param state         the state
     * @param zipCode       the zip code
     */
    public AddressDTO(String streetAddress, String city, String state, String zipCode) {

        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     * Instantiates a new Address dto.
     *
     * @param address the address
     */
    public AddressDTO(String[] address) {
        this.streetAddress = address[0];
        this.city = address[1];
        this.state = address[2];
        this.zipCode = address[3];
    }

    /**
     * Gets street address.
     *
     * @return the street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString(){
        return String.format("%s, %s, %s", this.streetAddress, this.city, this.state);
    }






}
