package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;


/**
 * The type Register store controller.
 */
public class RegisterStoreController {

    private EmployeeRepository employeeRepository = null;
    private StoreRepository storeRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    private UserSession currentSession = null;

    /**
     * Instantiates a new RegisterStoreController.
     */
    public RegisterStoreController(){
        getEmployeeRepository();
        getStoreRepository();
        getAuthenticationRepository();
    }

    /**
     * Instantiates a new RegisterStoreController with the given employee and authentication repositories.
     *
     * @param employeeRepository       the employee repository
     * @param authenticationRepository the authentication repository
     */
    public RegisterStoreController(EmployeeRepository employeeRepository, AuthenticationRepository authenticationRepository){

        this.employeeRepository = employeeRepository;
        this.authenticationRepository = this.authenticationRepository;

    }

    /**
     * Retrieves the EmployeeRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The EmployeeRepository instance.
     */
    private EmployeeRepository getEmployeeRepository(){
        if(employeeRepository == null){
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    /**
     * Retrieves the StoreRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The StoreRepository instance.
     */
    private StoreRepository getStoreRepository(){
        if(storeRepository == null){
            Repositories repositories = Repositories.getInstance();
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }
    /**
     * Retrieves the AuthenticationRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The AuthenticationRepository instance.
     */

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Registers a new Store with the provided information.
     *
     * @param id                  the id of the Store
     * @param designation         the designation of the Store
     * @param email               the email of the Store
     * @param storeAddress        the address of the store
     * @param phoneNumber         the phone number of the Store
     * @param emailToStoreManager the role of the Store Manager (email que será do novo Store Manager)
     * @return an Optional containing the new Store, or empty if the registration fails
     */
    public Optional<Store> registerStore(int id, String designation, String email, Address storeAddress, long phoneNumber, String emailToStoreManager){

//        Employee employee = getEmployeByEmail(email);
        int br = 0;
//
//        for ( Store store2 : storeRepository.getStores()) {
//            if(store2.hasEmail(email)){
//                br = 1;
//            }
//        }

        Optional<Store> store = Optional.empty();

        if (br == 0) {   store = storeRepository.createStore( id,  designation,  email, storeAddress, phoneNumber); }

//        employee.setStore(store);


        return store;
    }

    /**
     * Gets a list of employees without the store manager role.
     *
     * @return the list of employees without the store manager role.
     */
    public List<Employee> getEmployeeToStoreManager() {
        EmployeeRepository employeeRepository = getEmployeeRepository();

        return employeeRepository.getEmployeeToStoreManagers();
    }

    /**
     * Gets a list of employees with the store manager role.
     *
     * @param email the email
     * @return the list of employees with the store manager role.
     */
//    public List<Employee> getStoreManagers() {
//        EmployeeRepository employeeRepository = getEmployeeRepository();
//        List<Employee> storeManagers = getEmployeeRepository().getStoreManagers();
//        return storeManagers;
//    }
    public Employee getEmployeByEmail(String email) {
        EmployeeRepository employeeRepository = getEmployeeRepository();

        return employeeRepository.getEmployeeByEmail(email);
    }



    /**
     * Gets the Employee associated with the current session.
     *
     * @return the Employee associated with the current session
     */
    private Employee getEmployeeFromSession() {
        if (currentSession == null) {
            ApplicationSession appSession = ApplicationSession.getInstance();

            currentSession = appSession.getCurrentSession();
        }
        String email = currentSession.getUserEmail();
        EmployeeRepository employeeRepository = getEmployeeRepository();
        return employeeRepository.getEmployeeByEmail(email);
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
