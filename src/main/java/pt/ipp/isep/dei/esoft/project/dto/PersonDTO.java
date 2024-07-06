package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.dto.AddressDTO;
import pt.ipp.isep.dei.esoft.project.domain.Address;

/**
 * The type Person dto.
 */
public class PersonDTO {

    private String email;
    private String name;
    private Integer passportNumber;
    private long phoneNumber;
    private String taxNumber;
    private AddressDTO address;


    /**
     * Constructor for creating a PersonDTO object.
     *
     * @param email          The email address of the person.
     * @param name           The name of the person.
     * @param passportNumber The passport number of the person.
     * @param phoneNumber    The phone number of the person.
     * @param taxNumber      The tax number of the person.
     * @param address        The address of the person.
     */
    public PersonDTO(String email, String name, Integer passportNumber, long phoneNumber, String taxNumber, AddressDTO address) {
        this.email = email;
        this.name = name;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.address = address;
    }

    /**
     * Retrieves the email address of the person.
     *
     * @return The email address of the person.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the person.
     *
     * @param email The email address to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Retrieves the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Retrieves the passport number of the person.
     *
     * @return The passport number of the person.
     */
    public Integer getPassportNumber() {
        return passportNumber;
    }

    /**
     * Sets the passport number of the person.
     *
     * @param passportNumber The passport number to be set.
     */
    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * Retrieves the phone number of the person.
     *
     * @return The phone number of the person.
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber The phone number to be set.
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Retrieves the tax number of the person.
     *
     * @return The tax number of the person.
     */
    public String getTaxNumber() {
        return taxNumber;
    }

    /**
     * Sets the tax number of the person.
     *
     * @param taxNumber The tax number to be set.
     */
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     * Retrieves the address of the person.
     *
     * @return The address of the person.
     */
    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Sets the address of the person.
     *
     * @param address The address to be set.
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }



}
