package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;

import java.util.List;

/**
 * The CreatePropertyController class manages the business logic for the CreatePropertyUI
 */
public class CreatePropertyController {

    private PropertyTypeRepository propertyTypeRepository = null;
    private BusinessTypeRepository businessTypeRepository = null;
    private StoreRepository storeRepository = null;
    private EmployeeRepository employeeRepository = null;
    private ClientRepository clientRepository = null;
    private UserSession currentSession = null;
    private PropertyRepository propertyRepository = null;
    private AnnouncementRequestRepository announcementRequestRepository = null;

    private AuthenticationRepository authenticationRepository = null;


    /**
     * Instantiates a new Create property controller.
     */
//Repository instances are obtained from the Repositories class
    public CreatePropertyController() {
//        Property test = new Land(1, 1, new Address("1", "1", "1", "1"), 1, new ArrayList<>(1),
//                        new PropertyType("1"), new Store(1, "test", "a@this.app",new Address("teste","teste","teste","teste"), 1), new Client("1", "1",1, 1, new Address("1","1","1","1"),1));
        getPropertyTypeRepository();
        getBusinessTypeRepository();
        getStoreRepository();
        getEmployeeRepository();
        getAuthenticationRepository();
        getPropertyRepository();
        getAnnouncementRequestRepository();
    }

    private AnnouncementRequestRepository getAnnouncementRequestRepository() {
        if (announcementRequestRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the PropertyTypeRepository
            announcementRequestRepository = repositories.getAnnouncementRequestRepository();
        }
        return announcementRequestRepository;
    }

    private PropertyRepository getPropertyRepository() {
        if (propertyRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the PropertyTypeRepository
            propertyRepository = repositories.getPropertyRepository();
        }
        return propertyRepository;
    }

    /**
     * Instantiates a new Create property controller.
     *
     * @param propertyTypeRepository        the property type repository
     * @param businessTypeRepository        the business type repository
     * @param storeRepository               the store repository
     * @param employeeRepository            the employee repository
     * @param authenticationRepository      the authentication repository
     * @param propertyRepository            the property repository
     * @param announcementRequestRepository the announcement request repository
     */
//Allows receiving the repositories as parameters for testing purposes
    public CreatePropertyController(PropertyTypeRepository propertyTypeRepository,
                                    BusinessTypeRepository businessTypeRepository, StoreRepository storeRepository,
                                    EmployeeRepository employeeRepository, AuthenticationRepository authenticationRepository,
                                    PropertyRepository propertyRepository, AnnouncementRequestRepository announcementRequestRepository) {
        this.propertyTypeRepository = propertyTypeRepository;
        this.businessTypeRepository = businessTypeRepository;
        this.storeRepository = storeRepository;
        this.employeeRepository = employeeRepository;
        this.authenticationRepository = authenticationRepository;
        this.propertyRepository = propertyRepository;
        this.announcementRequestRepository = announcementRequestRepository;
    }

    /**
     * Sends the parameters to the PropertyRepository object to create a new Apartment object with those specified parameters.
     *
     * @param propertyPrice            the Land's price.
     * @param propertyArea             the Land's area.
     * @param propertyAddress          the Land's address.
     * @param propertyDistanceToCentre the Land's distance to the city centre.
     * @param propertyPhotographs      the Land's list of photographs.
     * @param propertyType             the type of the Property, should always be 'Land' in this case
     * @param store                    the Store where the Land will be listed.
     * @param client                   the Client that registered the Land.
     * @param date                     the date that the Land was registered in the system.
     * @return a Land object.
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs,
                                   PropertyType propertyType, Store store, Client client, DateTime date){
        return propertyRepository.createProperty(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyType, store, client, date);
    }

    /**
     * Sends the parameters to the PropertyRepository object to create a new Apartment object with those specified parameters.
     *
     * @param propertyPrice              the Apartment's price.
     * @param propertyArea               the Apartment's area.
     * @param propertyAddress            the Apartment's address.
     * @param propertyDistanceToCentre   the Apartment's distance to the city centre.
     * @param propertyPhotographs        the Apartment's list of photographs.
     * @param propertyBedroomNumber      the number of bedrooms in the Apartment.
     * @param propertyBathroomNumber     the number of bathrooms in the Apartment.
     * @param propertyParkingSpaceNumber the number of parking spaces in the Apartment.
     * @param propertyEquipmentList      the Apartment's list of available equipment.
     * @param propertyType               the type of the Property, should always be 'Apartment' in this case
     * @param store                      the Store where the Apartment will be listed.
     * @param client                     the Client that registered the Apartment.
     * @param date                       the date that the Land was registered in the system.
     * @return a Apartment object.
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs, Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList,
                                   PropertyType propertyType, Store store, Client client, DateTime date){
        return propertyRepository.createProperty(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber, propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyType, store, client, date);
    }

    /**
     * Sends the parameters to the PropertyRepository object to create a new House object with those specified parameters.
     *
     * @param propertyPrice              the House's price.
     * @param propertyArea               the House's area.
     * @param propertyAddress            the House's address.
     * @param propertyDistanceToCentre   the House's distance to the city centre.
     * @param propertyPhotographs        the House's list of photographs.
     * @param propertyBedroomNumber      the number of bedrooms in the House.
     * @param propertyBathroomNumber     the number of bathrooms in the House.
     * @param propertyParkingSpaceNumber the number of parking spaces in the House.
     * @param propertyEquipmentList      the House's list of available equipment.
     * @param propertyHasBasement        if the House has a basement.
     * @param propertyHasLoft            if the House has a loft.
     * @param propertySunExposure        the sun exposure of the house.
     * @param propertyType               the type of the Property, should always be 'House' in this case
     * @param store                      the Store where the House will be listed.
     * @param client                     the Client that registered the House.
     * @param date                       the date that the Land was registered in the system.
     * @return a House object.
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs, Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList, Boolean propertyHasBasement, Boolean propertyHasLoft, String propertySunExposure,
                                   PropertyType propertyType, Store store, Client client, DateTime date){
//        throw new NotImplementedException();
        return propertyRepository.createProperty(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber,
                                    propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyHasBasement, propertyHasLoft,
                                    propertySunExposure, propertyType, store, client, date);
    }

    /**
     * Sends the parameters to the Store object to create a new announcement request for a given rent duration, business type, property, agent, and client.
     *
     * @param rentDuration the duration of the rent in months
     * @param businessType the type of business associated with the announcement request
     * @param property     the property associated with the announcement request
     * @param agent        the agent associated with the announcement request
     * @param client       the client associated with the announcement request
     * @return the created announcement request if it was successfully added to the announcement request repository, null otherwise
     */
    public AnnouncementRequest createAnnouncementRequest(Integer rentDuration,BusinessType businessType, Property property, Agent agent, Client client){
//        throw new NotImplementedException();
        return announcementRequestRepository.createAnnouncementRequest(rentDuration, businessType, property, agent, client);
    }

    /**
     * Sends the parameters to the Store object to create a new announcement request for a given business type, property, agent, and client.
     *
     * @param businessType the type of business associated with the announcement request
     * @param property     the property associated with the announcement request
     * @param agent        the agent associated with the announcement request
     * @param client       the client associated with the announcement request
     * @return the created announcement request if it was successfully added to the announcement request repository, null otherwise
     */
    public AnnouncementRequest createAnnouncementRequest(BusinessType businessType, Property property, Agent agent, Client client){
//        throw new NotImplementedException();
        return announcementRequestRepository.createAnnouncementRequest(businessType, property, agent, client);
    }
    /**
     * Returns the PropertyTypeRepository instance for this system.
     * If it hasn't been initialized yet, the method initializes it by retrieving the repository from the global Repositories instance.
     * @return the PropertyTypeRepository instance for this system.
     */
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the PropertyTypeRepository
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }
    /**
     * Returns the ClientRepository instance for this system.
     * If it hasn't been initialized yet, the method initializes it by retrieving the repository from the global Repositories instance.
     * @return the ClientRepository instance for this system.
     */

    private ClientRepository getClientRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the ClientRepository
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }
    /**
     * Returns the BusinessTypeRepository instance for this system.
     * If it hasn't been initialized yet, the method initializes it by retrieving the repository from the global Repositories instance.
     * @return the BusinessTypeRepository instance for this system.
     */

    private BusinessTypeRepository getBusinessTypeRepository() {
        if (businessTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the BusinessTypeRepository
            businessTypeRepository = repositories.getBusinessTypeRepository();
        }
        return businessTypeRepository;
    }
    /**
     * Returns the StoreRepository instance for this system.
     * If it hasn't been initialized yet, the method initializes it by retrieving the repository from the global Repositories instance.
     * @return the StoreRepository instance for this system.
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
     * Returns the EmployeeRepository instance for this system.
     * If it hasn't been initialized yet, the method initializes it by retrieving the repository from the global Repositories instance.
     * @return the EmployeeRepository instance for this system.
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
     * Returns the AuthenticationRepository instance for this system.
     * If it hasn't been initialized yet, the method initializes it by retrieving the repository from the global Repositories instance.
     * @return the AuthenticationRepository instance for this system.
     */

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }


//    public Optional<Task> createTask(String reference, String description, String informalDescription,
//                                     String technicalDescription, Integer duration, Double cost,
//                                     String propertyTypeDescription) {
//
//        PropertyType propertyType = getPropertyTypeByDescription(propertyTypeDescription);
//
//        Employee employee = getEmployeeFromSession();
//        Optional<Organization> organization = getOrganizationRepository().getOrganizationByEmployee(employee);
//
//        Optional<Task> newTask = Optional.empty();
//
//        if (organization.isPresent()) {
//            newTask = organization.get()
//                    .createTask(reference, description, informalDescription, technicalDescription, duration, cost,
//                            propertyType, employee);
//        }
//        return newTask;
//    }

//    private Employee getEmployeeFromSession() {
//        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
//        return new Employee(email.getEmail());
//    }

    /**
     * Returns the PropertyType with the given description.
     *
     * @param propertyTypeDescription the description of the PropertyType to be retrieved
     * @return the PropertyType with the given description, or null if it doesn't exist
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
//        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();

        //Get the PropertyType by its description
        return getPropertyTypeRepository().getPropertyTypeByDescription(propertyTypeDescription);

    }

    /**
     * Returns the BusinessType with the given description.
     *
     * @param businessTypeDescription the description of the BusinessType to be retrieved
     * @return the BusinessType with the given description, or null if it doesn't exist
     */
    public BusinessType getBusinessTypeByDescription(String businessTypeDescription) {
//        BusinessTypeRepository propertyTypeRepository = getBusinessTypeRepository();

        //Get the PropertyType by its description
        return getBusinessTypeRepository().getBusinessTypeByDescription(businessTypeDescription, false);

    }

    /**
     * Returns the Store with the given description.
     *
     * @param storeDescription the description of the Store to be retrieved
     * @return the Store with the given description, or null if it doesn't exist
     */
    public Store getStoreByDescription(String storeDescription) {
//        StoreRepository storeRepository = getStoreRepository();

        //Get the PropertyType by its description
        return getStoreRepository().getStoreByDescription(storeDescription);

    }

    /**
     * Returns the Agent with the given description.
     *
     * @param agentDescription the description of the Agent to be retrieved
     * @return the Agent with the given description, or null if it doesn't exist
     */
    public Employee getAgentByDescription(String agentDescription) {
//        StoreRepository storeRepository = getStoreRepository();

        //Get the PropertyType by its description
        return getEmployeeRepository().getAgentByDescription(agentDescription);

    }

    /**
     * Returns a list of all agents associated with a given store.
     *
     * @param store the store whose agents to retrieve
     * @return a list of all agents associated with the given store
     */
    public List<Employee> getAgentsFromStore(Store store) { //Change to Agent upon creation
//        StoreRepository storeRepository = getStoreRepository();

        //Get the PropertyType by its description
        return getEmployeeRepository().getAgentsFromStore(store);

    }


    /**
     * Returns a list of all available property types.
     *
     * @return a list of PropertyType objects representing all available property types.
     */
    public List<PropertyType> getPropertyTypes() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }

    /**
     * Returns a list of all available stores.
     *
     * @return a list of Store objects representing all available stores.
     */
    public List<Store> getStores() {
        StoreRepository storeRepository = getStoreRepository();
        return storeRepository.getStores();
    }

    /**
     * Gets stores with agents.
     *
     * @return the stores with agents
     */
    public List<Store> getStoresWithAgents() {
        StoreRepository storeRepository = getStoreRepository();
        return storeRepository.getStoresWithAgents();
    }

    /**
     * Returns a list of all available business types.
     *
     * @return a list of BusinessType objects representing all business property types.
     */
    public List<BusinessType> getBusinessTypes() {
        BusinessTypeRepository businessTypeRepository = getBusinessTypeRepository();
        return businessTypeRepository.getBusinessTypes();
    }

    /**
     * Gets the client associated with the current user session.
     *
     * @return the client associated with the current user session
     */
    public Client getClientFromSession() {
        if (currentSession == null) {
            ApplicationSession appSession = ApplicationSession.getInstance();

            //Get the PropertyTypeRepository
            currentSession = appSession.getCurrentSession();
        }
        String email = currentSession.getUserEmail();
        ClientRepository clientRepository = getClientRepository();
        return clientRepository.getClientByEmail(email);
    }

    /**
     * Remove property.
     *
     * @param property the property
     */
    public void removeProperty(Property property) {
        PropertyRepository propertyRepository = getPropertyRepository();
        propertyRepository.remove(property);
    }
}
