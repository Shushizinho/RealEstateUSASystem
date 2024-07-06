package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Client who can interact with the system, creating requests for listing properties anc checking available listings.
 */
public class Client implements Serializable {

    private Person person;

    /**
     * Instantiates a new Client.
     *
     * @param name           the name
     * @param email          the email
     * @param passportNumber the passport number
     * @param taxNumber      the tax number
     * @param address        the address
     * @param phoneNumber    the phone number
     */
    public Client(String name, String email, Integer passportNumber, String taxNumber, Address address, long phoneNumber) {
        this.person = new Person(name,email,passportNumber,taxNumber,address,phoneNumber);

    }


    /**
     * Checks if the given object is equal to this Client object
     * @param o the object to be compared with this Client object
     * @return true if the given object is equal to this Client object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return getEmail().equals(client.getEmail());
    }

    /**
     * Returns a hash code value for the Client object.
     *  The hash code is based on the value of the email field.
     *
     * @return the hash code value for this Client object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getEmail());
    }

    /**
     * Returns true if the Client has the specified email address.
     *
     * @param email the email address to check
     * @return true if the Client has the specified email address, false otherwise
     */
    public boolean hasEmail(String email) {
        return this.getEmail().equals(email);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Client clone() {
        return new Client(this.getName(),this.getEmail(),this.getPassportNumber(),this.getTaxNumber(), this.getAddress(),this.getPhoneNumber());
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.person.getName();
    }

    /**
     * This method returns the email of the current instance.
     *
     * @return the Client's email.
     */
    public String getEmail(){
        return this.person.getEmail();
    }

    /**
     * This method returns the passport number of the current instance.
     *
     * @return the Client's passport number.
     */
    public Integer getPassportNumber() {
        return this.person.getPassportNumber();
    }

    /**
     * This method returns the phone number of the current instance.
     *
     * @return the Client's phone number.
     */
    public long getPhoneNumber() {
        return this.person.getPhoneNumber();
    }

    /**
     * This method returns the Address of the current instance.
     *
     * @return the Client's address.
     */
    public Address getAddress() {
        return this.person.getAddress();
    }

    /**
     * This method returns the tax number of the current instance.
     *
     * @return the Client's tax number.
     */
    public String getTaxNumber() {
        return this.person.getTaxNumber();
    }

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
            return " Name: "+ getName() + " - Email: "+ getEmail();
    }
}
