package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a general Person who can interact with the system.
 */
public class Person implements Serializable {
    private String email;
    private String name;
    private Integer passportNumber;
    private long phoneNumber;
    private String taxNumber;
    private Address address;

    /**
     * Instantiates a new Person.
     *
     * @param name           the name
     * @param email          the email
     * @param passportNumber the passport number
     * @param taxNumber      the tax number
     * @param address        the address
     * @param phoneNumber    the phone number
     */
    public Person(String name, String email, Integer passportNumber, String taxNumber, Address address, long phoneNumber) {

        this.name = name;
        this.email = email;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
        this.address = address;
    }


    /**
     * Instantiates a new Person.
     */
    public Person() {
        // Construtor sem argumentos
    }


    /**
     * This method returns the name of the current instance.
     *
     * @return the Person's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the email of the current instance.
     *
     * @return the Person's email.
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * This method returns the passport number of the current instance.
     *
     * @return the Person's passport number.
     */
    public Integer getPassportNumber() {
        return this.passportNumber;
    }

    /**
     * This method returns the phone number of the current instance.
     *
     * @return the Person's phone number.
     */
    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * This method returns the Address of the current instance.
     *
     * @return the Person's address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * This method returns the tax number of the current instance.
     *
     * @return the Person's tax number.
     */
    public String getTaxNumber() {
        return this.taxNumber;
    }
    /**
     * Checks if the given object is equal to this Person object
     * @param o the object to be compared with this Person object
     * @return true if the given object is equal to this Person object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person client = (Person) o;
        return email.equals(client.email);
    }
    /**
     * Returns a hash code value for the Person object. The hash code is computed
     * based on the values of the email field.
     *
     * @return the hash code value for this Person object
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    /**
     * This method checks if a person has a certain email.
     *
     * @param email the email to compare to.
     * @return True, if it has the specified email, false otherwise.
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Person clone() {
        return new Person(this.name,this.email,this.passportNumber,this.taxNumber,this.address,this.phoneNumber);
    }

    /**
     * This method returns a description of the Person attributes.
     *
     * @return A formated string composed of the Person's attributes
     */
    @Override
public String toString(){
        return String.format(" ClientName: %s |  ClientEmail: %s  |  ClientPhoneNumber: %d", this.name, this.email, this.phoneNumber);
    }
}
