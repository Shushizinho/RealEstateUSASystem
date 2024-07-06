package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.dto.AddressDTO;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.dto.PersonDTO;

/**
 * The type Client dto.
 */
public class ClientDTO  {

    private PersonDTO personDTO;

    /**
     * Constructor for creating a public ClientDTO object.
     *
     * @param email          The email address of the client.
     * @param name           The name of the client.
     * @param passportNumber The passport number of the client.
     * @param phoneNumber    The phone number of the client.
     * @param taxNumber      The tax number of the client.
     * @param address        The address of the client.
     */


    public ClientDTO(String name, String email, Integer passportNumber, long phoneNumber, String taxNumber, AddressDTO address) {
        this.personDTO = new PersonDTO(name,email,passportNumber,phoneNumber,taxNumber,address);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.personDTO.getName();
    }

    /**
     * This method returns the email of the current instance.
     *
     * @return the Client's email.
     */
    public String getEmail(){
        return this.personDTO.getEmail();
    }

    /**
     * This method returns the passport number of the current instance.
     *
     * @return the Client's passport number.
     */
    public Integer getPassportNumber() {
        return this.personDTO.getPassportNumber();
    }

    /**
     * This method returns the phone number of the current instance.
     *
     * @return the Client's phone number.
     */
    public long getPhoneNumber() {
        return this.personDTO.getPhoneNumber();
    }

    /**
     * This method returns the Address of the current instance.
     *
     * @return the Client's address.
     */
    public AddressDTO getAddress() {
        return this.personDTO.getAddress();
    }

    /**
     * This method returns the tax number of the current instance.
     *
     * @return the Client's tax number.
     */
    public String getTaxNumber() {
        return this.personDTO.getTaxNumber();
    }

    /**
     * Gets personDTO.
     *
     * @return the personDTO
     */
    public PersonDTO getPerson() {
        return personDTO;
    }

    @Override
    public String toString() {
        return " Name: "+ getName() + " - Email: "+ getEmail();
    }










}
