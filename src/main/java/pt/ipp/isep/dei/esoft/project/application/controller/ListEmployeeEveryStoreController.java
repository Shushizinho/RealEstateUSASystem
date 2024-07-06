package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.dto.EmployeeDTO;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;
import pt.ipp.isep.dei.esoft.project.mapper.EmployeeMapper;
import pt.ipp.isep.dei.esoft.project.mapper.StoreMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.*;


/**
 * The type List employee every store controller.
 */
public class ListEmployeeEveryStoreController {

    private EmployeeRepository employeeRepository = null;
    private PropertyRepository propertyRepository = null;
    private StoreRepository  storeRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    private UserSession currentSession = null;

    /**
     * Instantiates a new RegisterStoreController.
     */
    public ListEmployeeEveryStoreController(){
        getEmployeeRepository();
        getAuthenticationRepository();
        getPropertyRepository();
        getStoreRepository();
    }

    /**
     * Instantiates a new RegisterStoreController with the given employee and authentication repositories.
     *
     * @param employeeRepository       the employee repository
     * @param authenticationRepository the authentication repository
     */
    public ListEmployeeEveryStoreController(EmployeeRepository employeeRepository, AuthenticationRepository authenticationRepository){

        this.employeeRepository = employeeRepository;
        this.authenticationRepository = this.authenticationRepository;

    }

    private EmployeeRepository getEmployeeRepository(){
        if(employeeRepository == null){
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    private PropertyRepository getPropertyRepository(){
        if(propertyRepository == null){
            Repositories repositories = Repositories.getInstance();
            propertyRepository = repositories.getPropertyRepository();
        }
        return propertyRepository;
    }

    private StoreRepository getStoreRepository(){
        if(storeRepository == null){
            Repositories repositories = Repositories.getInstance();
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Gets a list of employees with the store manager role.
     *
     * @param <T>        the type parameter
     * @param items      the items
     * @param comparator the comparator
     * @return the list of employees with the store manager role.
     */
    public <T> List<T> getListAscendingOrder(List<T> items, Comparator<T> comparator) {
        List<T> itemList = new ArrayList<>(items);
        itemList.sort(comparator);
        return itemList;
    }

    /**
     * Gets employee from specific store.
     *
     * @param str the str
     * @return the employee from specific store
     */
    public List<Employee> getEmployeeFromSpecificStore(Store str) {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee: employeeRepository.getEmployees()) {
            if (employee.getStore().equals(str)){
                employeeList.add(employee);
            }

        }

        return employeeList;
    }

    /**
     * Gets employee from specific store.
     *
     * @param strdto the strdto
     * @return the employee from specific store
     */
    public List<EmployeeDTO> getEmployeeFromSpecificStore(StoreDTO strdto) {

        StoreMapper storeMapper = new StoreMapper();
        Store str = storeMapper.toEntity(strdto);

        List<EmployeeDTO> employeeListDTO = new ArrayList<>();
        for (Employee employee: employeeRepository.getEmployees()) {
            if (employee.getStore().equals(str)){
                EmployeeMapper employeeMapper = new EmployeeMapper();
                EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
                employeeListDTO.add(employeeDTO);
            }

        }

        return employeeListDTO;
    }

    /**
     * Gets property from specific store.
     *
     * @param str the str
     * @return the property from specific store
     */
    public List<Property> getPropertyFromSpecificStore(Store str) {
        List<Property> propertyList = new ArrayList<>();
        for (Property property: propertyRepository.getProperties()) {
            if (property.getStore().equals(str)){
                propertyList.add(property);
            }

        }

        return propertyList;
    }

    /**
     * Gets property from specific store.
     *
     * @param strdto the strdto
     * @return the property from specific store
     */
    public List<Property> getPropertyFromSpecificStore(StoreDTO strdto) {

        StoreMapper storeMapper = new StoreMapper();
        Store str = storeMapper.toEntity(strdto);

        List<Property> propertyList = new ArrayList<>();
        for (Property property: propertyRepository.getProperties()) {
            if (property.getStore().equals(str)){
                propertyList.add(property);
            }

        }

        return propertyList;
    }

    /**
     * Gets stores order by property.
     *
     * @return the stores order by property
     */
    public List<StoreDTO> getStoresOrderByProperty() {

        ;

        StoreMapper storeMapper = new StoreMapper();

        List<StoreDTO> storeListSorted =  storeMapper.toDTO(storeRepository.getStoresOrderByProperty());


        return storeListSorted;
    }

}
