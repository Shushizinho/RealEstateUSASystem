package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an employee in the company.
 */
public class Employee implements Serializable {

    private Person person;
    private List<Role> role;
    private Store store;

    /**
     * The Employee repository.
     */
    EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();


    /**
     * Returns a list of roles associated with this object.
     *
     * @return a list of roles
     */
    public List<Role> getRole() {
        return role;
    }

    /**
     * Returns whether a role with the specified description exists in the list of roles associated with this object.
     *
     * @param description the description of the role to search for
     * @return true if a role with the specified description exists, false otherwise
     */
    public boolean getRolesbyDescription(String description) {

        for (Role role : this.role) {
            if(role.getDescription().equals(description)){
                return true;
            };
        }
        return false;
    }

    /**
     * Constructs a new Employee object with the specified properties.
     *
     * @param name           the name of the employee
     * @param email          the email address of the employee
     * @param passportNumber the passport number of the employee
     * @param taxNumber      the tax number of the employee
     * @param address        the address of the employee
     * @param phoneNumber    the phone number of the employee
     * @param store          the store that the employee works in
     * @param role           a list of roles associated with the employee
     */
    public Employee(String name, String email, Integer passportNumber, String taxNumber,Address address, long  phoneNumber, Store store, List<Role> role ) {
        this.person = new Person(name,email,passportNumber,taxNumber,address,phoneNumber);
        this.store = store;
        this.role = role;
        if (role.contains(new Role(2,AuthenticationController.ROLE_AGENT))) {
            Agent agent = new Agent(this);
            employeeRepository.addAgent(agent);
        }
    }

    /**
     * Instantiates a new Employee.
     */
    public Employee() {

        // Construtor sem argumentos
    }

    /**
     * Returns the Agent object associated with this instance.
     *
     * @return the Agent object.
     */
    public Agent getAssociatedAgent() {
        return employeeRepository.getAgentObject(this);
    }

    /**
     * Compares the specified Role object with the Role objects associated with this Employee object for equality.
     *
     * @param role the Role object to compare
     * @return true if the Role object is equal to any of the Role objects associated with this Employee object, false otherwise
     */
    public boolean compareRole(Role role) {
        return this.role.contains(role);
    }

    /**
     * Compares the specified Store object with the Store object associated with this Employee object for equality.
     *
     * @param store the Store object to compare
     * @return true if the Store object is equal to the Store object associated with this Employee object, false otherwise
     */
    public boolean compareStore(Store store) {
        return this.store.equals(store);
    }

    /**
     * Compares this Employee object with the specified Object for equality. Returns true if the specified object is an
     * instance of the Employee class and has the same email address as this Employee object.
     *
     * @param o the object to compare with this Employee object
     * @return true if the specified object is equal to this Employee object, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return this.getEmail().equals(employee.getEmail());
    }

    /**
     * Returns a hash code value for this Employee object. The hash code is based on the email address of the Employee.
     *
     * @return a hash code value for this Employee object
     */

    @Override
    public int hashCode() {
        return Objects.hash(this.getEmail());
    }

    /**
     * Checks whether this Employee object has the specified email address.
     *
     * @param email the email address to check
     * @return true if this Employee object has the specified email address, false otherwise
     */
    public boolean hasEmail(String email) {
        return this.getEmail().equals(email);
    }

    /**
     * Creates and returns a new Employee object that is a copy of this Employee object. The new object has the same name,
     * email address, passport number, tax number, address, phone number, store, and roles as this Employee object.
     *
     * @return a new Employee object that is a copy of this Employee object
     */

    public Employee clone() {
        return new Employee(this.getName(),this.getEmail(),this.getPassportNumber(),this.getTaxNumber(),this.getAddress(),this.getPhoneNumber(),this.store,this.role);
    }


    @Override
    public String toString() {
        return " Name: "+ getName() + " - Email: "+ getEmail();
    }

    /**
     * Sets the role(s) for this object.
     *
     * @param role The list of Role objects to be assigned.
     */
    public void setRole(List<Role> role) {
        this.role = role;
    }

    /**
     * Retrieves the Store associated with this object.*
     *
     * @return The Store associated with this object.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets the Store associated with this object.*
     *
     * @param store The Store to be associated with this object.
     */
    public void setStore(Store store) {
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
    public Address getAddress() {
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

    /**
     * Gets person.
     *
     * @return the person
     */
    public Person getPerson() {
        return person;
    }

}


