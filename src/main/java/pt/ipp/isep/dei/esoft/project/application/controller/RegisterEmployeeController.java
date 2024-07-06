package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;


/**
 * The RegisterEmployeeController class manages the business logic for the RegisterEmployeeUI
 */
public class RegisterEmployeeController {


    private RoleRepository roleRepository = null;
    private StoreRepository storeRepository = null;
    private EmployeeRepository employeeRepository = null;

    /**
     * Constructs a new RegisterEmployeeController and initializes the necessary repositories.
     */
    public RegisterEmployeeController() {
        getRoleRepository();
        getStoreRepository();
        getEmployeeRepository();
    }

    /**
     * Constructs a new RegisterEmployeeController with the given repositories.
     *
     * @param roleRepository     The repository for managing roles.
     * @param storeRepository    The repository for managing stores.
     * @param employeeRepository The repository for managing employees.
     */
    public RegisterEmployeeController(RoleRepository roleRepository,
                                      StoreRepository storeRepository, EmployeeRepository employeeRepository) {
        this.roleRepository = roleRepository;
        this.storeRepository = storeRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Returns the StoreRepository instance, initializing it if necessary.
     *
     * @return The StoreRepository instance.
     */

    private StoreRepository getStoreRepository() {
        if (storeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the StoreRepository
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    /**
     * Returns the RoleRepository instance, initializing it if necessary.
     *
     * @return The RoleRepository instance.
     */

    private RoleRepository getRoleRepository() {
        if (roleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the RoleRepository
            roleRepository = repositories.getRoleRepository();
        }
        return roleRepository;

    }

    /**
     * Returns the EmployeeRepository instance, initializing it if necessary.
     *
     * @return The EmployeeRepository instance.
     */

    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the EmployeeRepository
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;

    }

    /**
     * Creates a new employee with the given information and adds them to the store.
     *
     * @param name               The name of the employee.
     * @param email              The email of the employee.
     * @param passportNumber     The passport number of the employee.
     * @param taxNumber          The tax number of the employee.
     * @param address            The address of the employee.
     * @param phoneNumber        The phone number of the employee.
     * @param storeByDescription The description of the store where the employee works.
     * @param role               The roles of the employee.
     * @return An Optional containing the created employee, or empty if an employee with the same email already exists.
     */
    public Optional<Employee> create(String name, String email, int passportNumber,String taxNumber, Address address, long phoneNumber, String storeByDescription, List<Role> role) {

        Store store = getStoreByDescription(storeByDescription);

        int br = 0;

        for ( Employee empl : employeeRepository.getEmployees()) {
            if(empl.hasEmail(email)){
                br = 1;
            }
        }


        Optional<Employee> employee = Optional.empty();

        if (br == 0) {   employee = employeeRepository.create( name,  email,  passportNumber, taxNumber, address, phoneNumber, store, role); }



        return employee;
    }


    /**
     * Returns the Role with the given description from the RoleRepository.
     *
     * @param roleDescription The description of the Role to retrieve.
     * @return The Role with the given description, or null if no Role is found.
     */
    public Role getRoleByDescription(String roleDescription) {
        RoleRepository roleRepository = getRoleRepository();

        Role roleByDescription = getRoleRepository().getRoleByDescription(roleDescription);
        return roleByDescription;

    }

    /**
     * Returns the Store with the given description from the StoreRepository.
     *
     * @param storeDescription The description of the Store to retrieve.
     * @return The Store with the given description, or null if no Store is found.
     */

    private Store getStoreByDescription(String storeDescription) {
        StoreRepository storeRepository = getStoreRepository();

        Store storeByDescription = getStoreRepository().getStoreByDescription(storeDescription);
        return storeByDescription;

    }

    /**
     * Returns a list of all the roles in the RoleRepository.
     *
     * @return A list of all the roles in the RoleRepository.
     */
    public List<Role> getRole() {
        RoleRepository roleRepository = getRoleRepository();
        return roleRepository.getRoles();
    }

    /**
     * Returns a list of all the stores in the StoreRepository.
     *
     * @return A list of all the stores in the StoreRepository.
     */
    public List<Store> getStore() {
        StoreRepository storeRepository = getStoreRepository();
        return storeRepository.getStores();
    }
}
