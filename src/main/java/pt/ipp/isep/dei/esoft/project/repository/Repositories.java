package pt.ipp.isep.dei.esoft.project.repository;

/**
 * The type Repositories.
 */
public class Repositories {

    private static final Repositories instance = new Repositories();
    /**
     * The Task category repository.
     */
    TaskCategoryRepository taskCategoryRepository = new TaskCategoryRepository();
    /**
     * The Organization repository.
     */
    OrganizationRepository organizationRepository = new OrganizationRepository();
    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    /**
     * The Announcement repository.
     */
    AnnouncementRepository announcementRepository = new AnnouncementRepository();

    /**
     * The Property filters repository.
     */
    PropertyFiltersRepository propertyFiltersRepository = new PropertyFiltersRepository();
    /**
     * The Property type repository.
     */
    PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
    /**
     * The Business type repository.
     */
    BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
    /**
     * The Store repository.
     */
    StoreRepository storeRepository = new StoreRepository();
    /**
     * The Employee repository.
     */
    EmployeeRepository employeeRepository = new EmployeeRepository();
    /**
     * The Purchase order repository.
     */
    PurchaseOrderRepository purchaseOrderRepository = new PurchaseOrderRepository();

    /**
     * The Property repository.
     */
    PropertyRepository propertyRepository = new PropertyRepository();

    /**
     * The Role repository.
     */
    RoleRepository roleRepository = new RoleRepository();
    /**
     * The Client repository.
     */
    ClientRepository clientRepository = new ClientRepository();
    /**
     * The Announcement request repository.
     */
    AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();

    /**
     * The Visit request repository.
     */
    VisitRequestRepository visitRequestRepository = new VisitRequestRepository();



    private Repositories() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Repositories getInstance() {
        return instance;
    }

    /**
     * Gets organization repository.
     *
     * @return the organization repository
     */
    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    /**
     * Gets task category repository.
     *
     * @return the task category repository
     */
    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    /**
     * Gets authentication repository.
     *
     * @return the authentication repository
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Gets property filters repository.
     *
     * @return the property filters repository
     */
    public PropertyFiltersRepository getPropertyFiltersRepository() {
        return propertyFiltersRepository;
    }

    /**
     * Gets property type repository.
     *
     * @return the property type repository
     */
    public PropertyTypeRepository getPropertyTypeRepository() {
        return propertyTypeRepository;
    }

    /**
     * Gets client repository.
     *
     * @return the client repository
     */
    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    /**
     * Gets business type repository.
     *
     * @return the business type repository
     */
    public BusinessTypeRepository getBusinessTypeRepository() {
        return businessTypeRepository;
    }


    /**
     * Gets store repository.
     *
     * @return the store repository
     */
    public StoreRepository getStoreRepository() {
        return storeRepository;
    }


    /**
     * Gets sale announcement repository.
     *
     * @return the sale announcement repository
     */
    public AnnouncementRepository getSaleAnnouncementRepository() {
        return announcementRepository;
    }

    /**
     * Get employee repository employee repository.
     *
     * @return the employee repository
     */
    public EmployeeRepository getEmployeeRepository(){return employeeRepository; }

    /**
     * Get property repository property repository.
     *
     * @return the property repository
     */
    public PropertyRepository getPropertyRepository(){
        return propertyRepository;
    }

    /**
     * Get purchase order repository purchase order repository.
     *
     * @return the purchase order repository
     */
    public PurchaseOrderRepository getPurchaseOrderRepository(){
        return purchaseOrderRepository;
    }

    /**
     * Gets role repository.
     *
     * @return the role repository
     */
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    /**
     * Gets announcement repository.
     *
     * @return the announcement repository
     */
    public AnnouncementRepository getAnnouncementRepository() {return announcementRepository;}

    /**
     * Gets announcement request repository.
     *
     * @return the announcement request repository
     */
    public AnnouncementRequestRepository getAnnouncementRequestRepository() {return announcementRequestRepository;}

    /**
     * Gets visit request repository.
     *
     * @return the visit request repository
     */
    public  VisitRequestRepository getVisitRequestRepository() {
        return  visitRequestRepository;
    }

}
