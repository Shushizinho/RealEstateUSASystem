package pt.ipp.isep.dei.esoft.project.dto;
import pt.ipp.isep.dei.esoft.project.dto.AddressDTO;

/**
 * The type Store dto.
 */
public class StoreDTO {
    private Integer id;
    private String designation;
    private AddressDTO address;
    private String email;
    private long phoneNumber;


    /**
     * Constructor for creating a StoreDTO object.
     *
     * @param id          The ID of the store.
     * @param designation The designation of the store.
     * @param address     The address of the store.
     * @param email       The email address of the store.
     * @param phoneNumber The phone number of the store.
     */
    public StoreDTO(Integer id, String designation, AddressDTO address, String email, long phoneNumber) {
        this.id = id;
        this.designation = designation;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    /**
     * Retrieves the ID of the store.
     *
     * @return The ID of the store.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the store.
     *
     * @param id The ID to be set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the designation of the store.
     *
     * @return The designation of the store.
     */
    public String getDesignation() {
        return designation;
    }


    /**
     * Sets designation.
     *
     * @param designation the designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
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
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

