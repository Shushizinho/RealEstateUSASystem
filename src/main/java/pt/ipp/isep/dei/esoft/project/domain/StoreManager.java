package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
/**
 * Represents an Employee whose function is to manage a Store.
 */
public class StoreManager  {

    private Employee employee;

    public StoreManager(String name, String email, Integer passportNumber, String taxNumber, Address address, long phoneNumber,  Store store, List<Role> role) {
        this.employee = new Employee(name, email, passportNumber, taxNumber, address, phoneNumber, store, role);
    }

    /**
     * Checks if the given object is equal to this Store Manager object
     * @param o the object to be compared with this Store Manager object
     * @return true if the given object is equal to this Store Manager object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StoreManager)) {
            return false;
        }
        StoreManager storeManager = (StoreManager) o;
        return employee.getEmail().equals(storeManager.getEmployee().getEmail());
    }

    /**
     * Returns a hash code value for the Store Manager object.
     *  The hash code is based on the value of the email field.
     *
     * @return the hash code value for this Store Manager object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getEmployee().getEmail());
    }
    /**
     * Returns true if the Store Manager has the specified email address.
     *
     * @param email the email address to check
     * @return true if the Store Manager has the specified email address, false otherwise
     */

//    public boolean hasEmail(String email) {
//        return this.getEmail().equals(email);
//    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public StoreManager clone() {
        return new StoreManager(this.getEmployee().getName(),this.getEmployee().getEmail(),this.getEmployee().getPassportNumber(),this.getEmployee().getTaxNumber(), this.getEmployee().getAddress(),this.getEmployee().getPhoneNumber(),this.getEmployee().getStore(),this.getEmployee().getRole());
    }

    public Employee getEmployee() {
        return employee;
    }

//    List<Store> stores = new ArrayList<>();
//
//
//    /**
//     * Creates a new Store object with the specified parameters and adds it to the list of stores.
//     * If the store is successfully added, returns an Optional containing the newly created Store object, otherwise returns an empty Optional.
//     *
//     * @param id the unique identifier for the store
//     * @param designation the name or designation of the store
//     * @param address the address of the store
//     * @param email the email address of the store
//     * @param phoneNumber the phone number of the store
//     * @return an Optional containing the newly created Store object if the store was successfully added, otherwise an empty Optional
//     */
//    public Optional<Store> createStore(Integer id, String designation, String email,Address address, Integer phoneNumber,StoreManager storeManager) {
//
//        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
//
//        Store StoreClone = new Store(id,designation,email,address,phoneNumber,storeManager);
//
//        Optional<Store> newStore = Optional.of(StoreClone.clone());
//
//        storeRepository.add(StoreClone);
//
//        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
//
//        return newStore;
//
//    }
//    /**
//     * Adds a new store to the list of stores.
//     *
//     * @param store the store to add
//     * @return true if the store was added successfully, false otherwise
//     */
//    public boolean addStore(Store store) {
//        boolean success = false;
//        if (validate(store)) {
//            success = stores.add(store.clone());
//        }
//        return success;
//    }
//
//    /**
//     * Checks if a given store is valid to be added to the list of stores.
//     * A store is considered valid if it is not already in the list of stores.
//     * @param store the store to be validated
//     * @return true if the store is valid, false otherwise
//     */
//    public boolean validate(Store store) {
//        boolean isValid = !stores.contains(store);
//
//        return isValid;
//    }
}
