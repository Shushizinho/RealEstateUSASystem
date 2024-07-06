package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an Address that can be used across various Classes.
 */
public class Address implements Serializable {

    private final String streetAddress;

    private final String city;
    private final String state;
    private final String zipCode;

    /**
     * Instantiates a new Address.
     *
     * @param streetAddress the street address
     * @param city          the city
     * @param state         the state
     * @param zipCode       the zip code
     */
    public Address(String streetAddress, String city, String state, String zipCode) {

        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }


    /**
     * Instantiates a new Address.
     *
     * @param address the address
     */
    public Address(String[] address) {
        this.streetAddress = address[0];
        this.city = address[1];
        this.state = address[2];
        this.zipCode = address[3];
    }


    /**
     * This method returns a description of the Adress attributes.
     *
     * @return A formated string composed of the Adress's attributes
     */
    @Override
public String toString(){
        return String.format("%s, %s, %s", this.streetAddress, this.city, this.state);
    }


    /**
     * To path string.
     *
     * @return the string
     */
    public String toPath(){
        return String.format("%s_%s_%s_%s",this.streetAddress.replace(' ', '_'),this.city.replace(' ', '_'),this.state.replace(' ', '_'),this.zipCode.replace(' ', '_'));
    }

    /**
     * Returns the city associated with this address.
     *
     * @return the city associated with this address
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the state associated with this address.
     *
     * @return the state associated with this address
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the streetAdress associated with this address.
     *
     * @return the streetAdress associated with this address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Returns the zipCode associated with this address.
     *
     * @return the zipCode associated with this address
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Checks if the given object is equal to this Address object
     * @param o the object to be compared with this Address object
     * @return true if the given object is equal to this Address object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        Address other = (Address) o;
        return Objects.equals(streetAddress, other.streetAddress)
                && Objects.equals(city, other.city)
                && Objects.equals(state, other.state)
                && Objects.equals(zipCode, other.zipCode);
    }
    /**
     * Returns a hash code value for the Address object. The hash code is computed
     * based on the values of the streetAddress, city, state, and zipCode fields.
     *
     * @return the hash code value for this Address object
     */
    @Override
    public int hashCode() {
        return Objects.hash(streetAddress, city, state, zipCode);
    }
//
//    /**
//     * This method returns the description of the task category.
//     *
//     * @return The description of the task category.
//     */
//    public String getDescription() {
//        return description;
//    }
//
//    /**
//     * Clone method.
//     *
//     * @return A clone of the current task.
//     */
//    public Address clone() {
//        return new Address(this.description);
//    }


}
