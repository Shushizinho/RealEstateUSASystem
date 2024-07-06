package pt.ipp.isep.dei.esoft.project.dto;



import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Person;
import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.dto.PersonDTO;
import pt.ipp.isep.dei.esoft.project.dto.AddressDTO;


import java.util.List;

/**
 * The type Employee dto.
 */
public class EmployeeDTO {

    private PersonDTO person;

    private List<RoleDTO> role;
    private StoreDTO store;


    /**
     * Constructor of the EmployeeDTO class.
     *
     * @param email          The employee's email address.
     * @param name           The employee's name.
     * @param passportNumber The employee's passport number.
     * @param phoneNumber    The employee's phone number.
     * @param taxNumber      The employee's tax identification number.
     * @param address        The employee's address.
     * @param role           The list of roles assigned to the employee.
     * @param store          The store associated with the employee.
     */
    public EmployeeDTO(String name, String email, Integer passportNumber, long phoneNumber, String taxNumber, AddressDTO address, List<RoleDTO> role, StoreDTO store) {
        this.person = new PersonDTO(name,email,passportNumber,phoneNumber,taxNumber,address);
        this.role = role;
        this.store = store;
    }


    /**
     * Retrieves the list of roles assigned to the employee.
     *
     * @return The list of roles assigned to the employee.
     */
    public List<RoleDTO> getRole() {
        return role;
    }


    /**
     * Sets the roles of the person.
     *
     * @param role The list of roles to be set.
     */
    public void setRole(List<RoleDTO> role) {
        this.role = role;
    }


    /**
     * Retrieves the store associated with the person.
     *
     * @return The store associated with the person.
     */
    public StoreDTO getStore() {
        return store;
    }


    /**
     * Sets the store associated with the person.
     *
     * @param store The store to be set.
     */
    public void setStore(StoreDTO store) {
        this.store = store;
    }

    /**
     * This method returns the name of the current instance.
     *
     * @return the Employee's name.
     */
    public String getName() {
        return this.person.getName();
    }

    /**
     * This method returns the email of the current instance.
     *
     * @return the Employee's email.
     */
    public String getEmail(){
        return this.person.getEmail();
    }

    /**
     * This method returns the passport number of the current instance.
     *
     * @return the Employee's passport number.
     */
    public Integer getPassportNumber() {
        return this.person.getPassportNumber();
    }

    /**
     * This method returns the phone number of the current instance.
     *
     * @return the Employee's phone number.
     */
    public long getPhoneNumber() {
        return this.person.getPhoneNumber();
    }

    /**
     * This method returns the Address of the current instance.
     *
     * @return the Employee's address.
     */
    public AddressDTO getAddress() {
        return this.person.getAddress();
    }

    /**
     * This method returns the tax number of the current instance.
     *
     * @return the Employee's tax number.
     */
    public String getTaxNumber() {
        return this.person.getTaxNumber();
    }


    @Override
    public String toString() {
        return " Name: "+ getName() + " - Email: "+ getEmail();
    }


}
