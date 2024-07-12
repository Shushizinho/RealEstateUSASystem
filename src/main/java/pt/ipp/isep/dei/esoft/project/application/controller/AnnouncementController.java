package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;
import pt.ipp.isep.dei.esoft.project.repository.*;



import java.util.List;
import java.util.Optional;

/**
 * A controller class for managing sale announcements.
 */
public class AnnouncementController {

    private AnnouncementRepository announcementRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;
    private BusinessTypeRepository businessTypeRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    private PropertyRepository propertyRepository=null;
    private StoreRepository storeRepository = null;
    private UserSession currentSession = null;
    private EmployeeRepository employeeRepository = null;
    /**
     * The Address.
     */
    Address address= new Address("test","test","teste","teste");
    /**
     * The Employ.
     */
    Employee employ = new Employee("Diogo", "agent@this.app", 12321, "3213123", address ,121212, new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000), List.of(new Role(2, AuthenticationController.ROLE_AGENT)));
    /**
     * The Agent.
     */
    Agent agent = employ.getAssociatedAgent();


    /**
     * Instantiates a new Announcement controller.
     *
     * @param propertyTypeRepository   the property type repository
     * @param businessTypeRepository   the business type repository
     * @param employeeRepository       the employee repository
     * @param storeRepository          the store repository
     * @param authenticationRepository the authentication repository
     * @param propertyRepository       the property repository
     */
    public AnnouncementController(PropertyTypeRepository propertyTypeRepository,
                                  BusinessTypeRepository businessTypeRepository,
                                  EmployeeRepository employeeRepository, StoreRepository storeRepository, AuthenticationRepository authenticationRepository,PropertyRepository propertyRepository) {
        this.propertyTypeRepository = propertyTypeRepository;
        this.businessTypeRepository = businessTypeRepository;
        this.employeeRepository = employeeRepository;
        this.authenticationRepository = authenticationRepository;
        this.storeRepository = storeRepository;
        this.propertyRepository=propertyRepository;
    }

    /**
     * Constructs a new SaleAnnouncementController with the necessary repositories.
     */
    public AnnouncementController() {

    getAnnouncementRepository();
    getPropertyTypeRepository();
    getBusinessTypeRepository();
    getAuthenticationRepository();

}

    /**

     Returns the AnnouncementRepository instance, creating it if necessary.
     @return the AnnouncementRepository instance.
     */
    private AnnouncementRepository getAnnouncementRepository() {
    if (announcementRepository == null) {
        Repositories repositories = Repositories.getInstance();


        announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }
    /**

     Returns the singleton instance of EmployeeRepository, creating it if it does not exist yet.
     @return the EmployeeRepository instance
     */
    private EmployeeRepository getEmployeeRepository(){
        if(employeeRepository == null){
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }
    /**

     Returns the singleton instance of PropertyTypeRepository, creating it if it does not exist yet.
     @return the PropertyTypeRepository instance
     */


private PropertyTypeRepository getPropertyTypeRepository(){

    if(propertyTypeRepository == null){
        Repositories repositories = Repositories.getInstance();

        propertyTypeRepository= repositories.getPropertyTypeRepository();
    }

    return propertyTypeRepository;
}

    private PropertyRepository getPropertyRepository(){

        if(propertyRepository == null){
            Repositories repositories = Repositories.getInstance();

            propertyRepository= repositories.getPropertyRepository();
        }

        return propertyRepository;
    }

    /**

     Returns the BusinessTypeRepository instance, creating it if necessary.
     @return the BusinessTypeRepository instance
     */

private BusinessTypeRepository getBusinessTypeRepository(){

    if( businessTypeRepository == null){
        Repositories repositories = Repositories.getInstance();


        businessTypeRepository =repositories.getBusinessTypeRepository();
    }

    return businessTypeRepository;
}

    /**
     * Retrieves a list of all property types.
     *
     * @return a list of all property types.
     */
    public List<PropertyType> getPropertyTypes() {
    PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
    return propertyTypeRepository.getPropertyTypes();
    }

    /**
     * Retrieves all available business types from the business type repository.
     *
     * @return a list of all available business types
     */
    public List<BusinessType> getBusinessTypes() {
        BusinessTypeRepository businessTypeRepository = getBusinessTypeRepository();
        return businessTypeRepository.getBusinessTypes();
    }

    /**

     Returns the AuthenticationRepository object. If it has not been initialized yet, it initializes it using the
     Repositories.getInstance() method and returns it.
     @return the AuthenticationRepository object
     */



    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Creates a new property with the given parameters.
     *
     * @param propertyPrice              the price of the property
     * @param propertyArea               the area of the property
     * @param propertyAddress            the address of the property
     * @param propertyDistanceToCentre   the distance of the property to the city centre
     * @param propertyPhotographs        a list of URLs of photographs of the property
     * @param propertyTypeDescription    The type description of the property.
     * @param storeDescription           The store description to which the property is associated.
     * @param date                       the date
     * @return the created property
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs,
                                   String propertyTypeDescription, String storeDescription, Client client, DateTime date){

        Store store = getStoreByDescription(storeDescription);

        PropertyType propertyType = getPropertyTypeByDescription(propertyTypeDescription);

        return getPropertyRepository().createProperty(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyType, store, client, date);
    }



    /**
     * Creates a new Property with the given parameters.
     *
     * @param propertyPrice              The price of the property.
     * @param propertyArea               The area of the property.
     * @param propertyAddress            The address of the property.
     * @param propertyDistanceToCentre   The distance from the property to the city center.
     * @param propertyPhotographs        The list of URLs of photographs of the property.
     * @param propertyBedroomNumber      The number of bedrooms in the property.
     * @param propertyBathroomNumber     The number of bathrooms in the property.
     * @param propertyParkingSpaceNumber The number of parking spaces in the property.
     * @param propertyEquipmentList      The list of equipment available in the property.
     * @param propertyHasBasement        Whether or not the property has a basement.
     * @param propertyHasLoft            Whether or not the property has a loft.
     * @param propertySunExposure        The sun exposure of the property.
     * @param propertyTypeDescription    The type description of the property.
     * @param storeDescription           The store description to which the property is associated.
     * @param client                     The Client who created the Property.
     * @param date                       the date
     * @return The newly created Property.
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs, Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList, Boolean propertyHasBasement, Boolean propertyHasLoft, String propertySunExposure,
                                   String propertyTypeDescription, String storeDescription, Client client, DateTime date){

        Store store = getStoreByDescription(storeDescription);

        PropertyType propertyType = getPropertyTypeByDescription(propertyTypeDescription);


        return getPropertyRepository().createProperty(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber,
                propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyHasBasement, propertyHasLoft,
                propertySunExposure, propertyType, store, client, date);
    }

    /**
     * Creates a new property with the given parameters and returns it.
     *
     * @param propertyPrice              The price of the property.
     * @param propertyArea               The area of the property in square meters.
     * @param propertyAddress            The address of the property.
     * @param propertyDistanceToCentre   The distance to the city center in kilometers.
     * @param propertyPhotographs        A list of URLs of photographs of the property.
     * @param propertyBedroomNumber      The number of bedrooms in the property.
     * @param propertyBathroomNumber     The number of bathrooms in the property.
     * @param propertyParkingSpaceNumber The number of parking spaces available with the property.
     * @param propertyEquipmentList      A list of equipment and amenities available with the property.
     * @param propertyTypeDescription    The type description of the property.
     * @param storeDescription           The store description to which the property is associated.
     * @param client                     The Client who created the property listing.
     * @return The created Property object.
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs, Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList,
                                   String propertyTypeDescription, String storeDescription, Client client){
        Store store = getStoreByDescription(storeDescription);

        PropertyType propertyType = getPropertyTypeByDescription(propertyTypeDescription);

        return getPropertyRepository().createProperty(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber, propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyType, store, client, new DateTime());
    }

    public Optional<AnnouncementDTO> createAnnouncement(DateTime date, double commission, String businessTypeDescription, Property property, Integer rentDuration, Agent agent) {
        BusinessType businessType = getBusinessTypeByDescription(businessTypeDescription);

        return  getAnnouncementRepository().createAnnouncement(date, commission, businessType, property, rentDuration, agent);
    }

    public Optional<AnnouncementDTO> createAnnouncement(DateTime date, double commission, String businessTypeDescription, Property property, double listedPrice, Agent agent) {
        BusinessType businessType = getBusinessTypeByDescription(businessTypeDescription);

        return  getAnnouncementRepository().createAnnouncement(date, commission, businessType, property, listedPrice, agent);
    }


    /**
         * Create announcement optional.
         *
         * @param announcementDTO the announcement dto
         * @return the optional
         */
    public Optional<Announcement> createAnnouncement(AnnouncementDTO announcementDTO){

        return getAnnouncementRepository().createAnnouncement( announcementDTO);
    }

//    /**
//     * Create property property.
//     *
//     * @param landDTO the land dto
//     * @return the property
//     */
//    public Property createProperty(LandDTO landDTO) {
//
//        return getPropertyRepository().createProperty(landDTO);
//    }
//
//    /**
//     * Create property property.
//     *
//     * @param apartmentDTO the apartment dto
//     * @return the property
//     */
//    public Property createProperty(ApartmentDTO apartmentDTO) {
//
//        return getPropertyRepository().createProperty(apartmentDTO);
//    }
//
//    /**
//     * Create property property.
//     *
//     * @param houseDTO the house dto
//     * @return the property
//     */
//    public Property createProperty(HouseDTO houseDTO) {
//
//        return getPropertyRepository().createProperty(houseDTO);
//    }

    /**
     * Create sms.
     *
     * @param senderNumber    the sender number
     * @param agentName       the agent name
     * @param dateTime        the date time
     * @param address         the address
     */
    public boolean createSMS(long senderNumber, String agentName, DateTimeDTO dateTime, AddressDTO address) {

        return getAnnouncementRepository().createSMS(senderNumber, agentName, dateTime, address);

    }



//
//    /**
//     * Creates a new sale announcement with the given parameters.
//     *
//     * @param date         the formatted date of the announcement
//     * @param commission   the commission for the announcement
//     * @param businessType the business type of the announcement
//     * @param property     the property associated with the announcement
//     * @param listedPrice  the listed price of the property
//     * @param agent        the employee responsible for the announcement
//     * @return an optional containing the created sale announcement if successful, or an empty optional otherwise
//     */
//    public Optional<SaleAnnouncement> createSaleAnnouncement(DateTime date, double commission, BusinessType businessType, Property property, double listedPrice, Agent agent){
//
//        return getAnnouncementRepository().createSaleAnnouncement2( date, commission, businessType,property,listedPrice,agent);
//    }
//
//    /**
//     * Creates a new rent announcement with the given parameters.
//     *
//     * @param date         the formatted date of the announcement
//     * @param commission   the commission for the announcement
//     * @param businessType the business type of the announcement
//     * @param property     the property associated with the announcement
//     * @param rentDuration the duration of the rent
//     * @param agent        the employee responsible for the announcement
//     * @return an optional containing the created rent announcement if successful, or an empty optional otherwise
//     */
//    public Optional<RentAnnouncement> createRentAnnouncement(DateTime date, double commission, BusinessType businessType, Property property, Integer rentDuration, Agent agent){
//
//        return getAnnouncementRepository().createRentAnnouncement2(date,commission,businessType,property,rentDuration, agent );
//    }


    /**
     * Returns the PropertyType object that matches the given description.
     *
     * @param propertyTypeDescription a String representing the description of the desired PropertyType
     * @return a PropertyType object that matches the given description, or null if no matching PropertyType is found
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {



        return getPropertyTypeRepository().getPropertyTypeByDescription(propertyTypeDescription);

    }


    /**
     * Returns the {@link BusinessType} object corresponding to the given description.
     *
     * @param businessTypeDescription the description of the business type to be retrieved
     * @return the {@link BusinessType} object with the given description, or null if not found
     */
    public BusinessType getBusinessTypeByDescription(String businessTypeDescription) {


        return getBusinessTypeRepository().getBusinessTypeByDescription(businessTypeDescription, true);

    }

    /**
     * Returns the Store with the given description.
     *
     * @param storeDescription the description of the Store to retrieve
     * @return the Store with the given description, or null if no Store exists with that description
     */
    public Store getStoreByDescription(String storeDescription) {



        return getStoreRepository().getStoreByDescription(storeDescription);

    }

    /**

     Retrieves the {@link StoreRepository} instance and creates it if it does not exist.
     @return The {@link StoreRepository} instance.
     */


    private StoreRepository getStoreRepository() {
        if (storeRepository == null) {
            Repositories repositories = Repositories.getInstance();


            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    /**
     * Retrieves the agent (employee) associated with the current session.
     *
     * @return the agent (employee) associated with the current session
     */
    public Agent getAgentFromSession() {
        if (currentSession == null) {
            ApplicationSession appSession = ApplicationSession.getInstance();

            //Get the PropertyTypeRepository
            currentSession = appSession.getCurrentSession();
        }
        String email = currentSession.getUserEmail();
        EmployeeRepository employeeRepository1 = getEmployeeRepository();
        return employeeRepository1.getEmployeeByEmail(email).getAssociatedAgent();
    }






}
