package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.SerializationController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Bootstrap.
 */
public class Bootstrap implements Runnable {

    private final SerializationController controller = new SerializationController();

    //Add some task categories to the repository as bootstrap
    public void run() {

        String caminho = "src/main/resources/arquivo.ser";
        DateTime currDate = new DateTime();
        String backup = "src/main/resources/backup/arquivo.ser."+currDate.getYear()+currDate.getMonth()+currDate.getDayOfMonth()+currDate.getHour()+currDate.getMinute()+currDate.getSecond()+".bak";
//        System.out.println(backup);
        File file = new File(caminho);

            addUsers();
            addBusinessType();
            addPropertyType();
            addFilters();

        if (!file.exists()) {
            addStores();
            addEmployee();
            addClients();
            addProperty();
            addAnnouncement();
            addAnnouncementRequests();
            addPurchaseOrder();
            addVisitRequests();

        }

//        addStoreManagers();

        //Do the serialize method
        controller.serializeCode(caminho, backup);


    }


    private void addUsers() {

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
        roleRepository.add(new Role(1, AuthenticationController.ROLE_ADMIN));
        roleRepository.add(new Role(2,AuthenticationController.ROLE_AGENT));
        roleRepository.add(new Role(3,AuthenticationController.ROLE_STORE_MANAGER));
        roleRepository.add(new Role(4,AuthenticationController.ROLE_STORE_NETWORK_MANAGER));
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,AuthenticationController.ROLE_EMPLOYEE);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT,AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT,AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_MANAGER,AuthenticationController.ROLE_STORE_MANAGER);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_STORE_NETWORK_MANAGER,AuthenticationController.ROLE_STORE_NETWORK_MANAGER);


        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Agent", "agent@this.app", "pwd",
                AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Joao", "joao1@this.app", "pwd",
                AuthenticationController.ROLE_AGENT);

        authenticationRepository.addUserWithRole("Client", "client@this.app", "123",
                AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("Store Manager", "sm@this.app", "sm",
                AuthenticationController.ROLE_STORE_MANAGER);
        authenticationRepository.addUserWithRole("Store Network Manager", "net@this.app", "net",
                AuthenticationController.ROLE_STORE_NETWORK_MANAGER);
    }

    private void addStoreManagers() {
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        List<Role> list = new ArrayList<>();
        list.add(new Role(4,AuthenticationController.ROLE_STORE_MANAGER));
        Address address = new Address("teste","teste","teste","teste");
        employeeRepository.add(new Employee("Joao", "joao@this.app", 45, "111-11-0439", address ,47357, null, list ));
        employeeRepository.add(new Employee("Joao2", "joao2@this.app", 46, "111-11-0439", address ,47357, null, list ));
        employeeRepository.add(new Employee("Joao3", "joao3@this.app", 47, "111-11-0439", address ,47357, null, list ));
        employeeRepository.add(new Employee("Joao4", "joao4@this.app", 48, "111-11-0439", address ,47357, null, list ));
    }


    private void addEmployee() {
        //get store and employee repository
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
        List<Role> list = new ArrayList<>();

        Address address = new Address("teste","teste","teste","teste");

        list.add(roleRepository.getRoles().get(1));
//        employeeRepository.add(new Employee("AgentAD", "employee@this.app", 2, "111-11-0439", address ,0000002, storeRepository.getStores().get(1), list ));
        employeeRepository.add(new Employee("Agent", "agent@this.app", 2, "111-11-0439", address ,910532123, storeRepository.getStores().get(1), list ));


        employeeRepository.add(new Employee("Joao", "joao1@this.app", 45, "111-11-0439", address ,47357, storeRepository.getStores().get(2), list ));
        list.add(roleRepository.getRoles().get(2));
        employeeRepository.add(new Employee("Alberto", "joao2@this.app", 45, "111-11-0439", address ,47357, storeRepository.getStores().get(1), list ));
    }

    private void addTaskCategories() {

        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }


    private void addVisitRequests(){
        VisitRequestRepository visitRequestRepository = Repositories.getInstance().getVisitRequestRepository();
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
        List<Role> list = new ArrayList<>();

        list.add(roleRepository.getRoles().get(1));

        Property property= (new House(100.2, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("PPROG"),
                new Client("client", "client@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime(12, 5, 2022))).getInhabitable().getProperty();
        DateTime dateTime = new DateTime(12, 7, 2023);
        DateTime dateTime14 = new DateTime(23,9,2023);
        DateTime dateTime3 = new DateTime(14,8,2023);
        DateTime dateTime1 = new DateTime(10,12);
        DateTime dateTime2 = new DateTime(13,12);
        List<DateTime> timeSlots = new ArrayList<>();
        timeSlots.add(dateTime);
        timeSlots.add(dateTime1);
        Address address= new Address("aaaaaa","aaaaaaa","aaaaaaaaa","aaaaaaaa");

        Employee employee = new Employee("AgentAD", "agent@this.app", 12321, "3213123", address ,121212, storeRepository.getStoreByDescription("PPROG"), list);
        Agent agent = new Agent(employee);

        VisitRequest visitRequest = new VisitRequest(property, dateTime, timeSlots,"LALALA", 1212121221);

        VisitRequestDTO visitRequestDTO = VisitRequestMapper.toDTO(visitRequest);

        VisitRequest visitRequest1 = new VisitRequest(property,dateTime14, timeSlots, "Diogo", 912312121, 1);
        VisitRequestDTO visitRequestDTO1 = VisitRequestMapper.toDTO(visitRequest1);

        VisitRequest visitRequest2 = new VisitRequest(property, dateTime3, timeSlots, "Afonso",919191);

        VisitRequestDTO visitRequestDTO2 = VisitRequestMapper.toDTO(visitRequest2);


        visitRequestRepository.add(visitRequestDTO1);
        visitRequestRepository.add(visitRequestDTO2);
        visitRequestRepository.add(visitRequestDTO);

    }







    private void addFilters(){
        PropertyFiltersRepository filtersRepository = Repositories.getInstance().getPropertyFiltersRepository();
        filtersRepository.add(new Filters(("Price Ascendent")));
        filtersRepository.add(new Filters(("Price Descendent")));
        filtersRepository.add(new Filters(("City Ascendent")));
        filtersRepository.add(new Filters(("City Descendent")));
        filtersRepository.add(new Filters(("State Ascendent")));
        filtersRepository.add(new Filters(("State Descendent")));
        filtersRepository.add(new Filters(("Price Range")));
        filtersRepository.addPriceRange(new Pair<Integer, Integer>(0,50));
        filtersRepository.addPriceRange(new Pair<Integer, Integer>(50,200));

    }

    private void addAnnouncementRequests(){
//        (alteração a ser feita) PropertyRepository propertyRepository= Repositories.getInstance().getPropertyRepository();
        AnnouncementRequestRepository announcementRequestRepository= Repositories.getInstance().getAnnouncementRequestRepository();
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
        List<Role> list = new ArrayList<>();

        list.add(roleRepository.getRoles().get(1));

        BusinessType businessType= new BusinessType("Sale", "Buy");
        BusinessType businessType2= new BusinessType("Rent", "Rent");
        PropertyType propertyType= new  PropertyType("House");
        Property property= (new House(100.2, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("PPROG"),
                new Client("client", "client@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime(12, 5, 2022))).getInhabitable().getProperty();

        Address address= new Address("aaaaaa","aaaaaaa","aaaaaaaaa","aaaaaaaa");
        Employee employee = new Employee("AgentAD", "agent@this.app", 12321, "3213123", address ,121212, storeRepository.getStoreByDescription("PPROG"), list);
        Agent agent = new Agent(employee);

        Employee employee2 = new Employee("AgentAD", "agenta@this.app", 12321, "3213123", address ,121212, storeRepository.getStoreByDescription("PPROG"), list);
        Agent agent2 = new Agent(employee2);
        Employee agent1= new Employee("Diogo","lalala",12121,"222",address,212121,storeRepository.getStoreByDescription("PPROG"),list);
        Client client= new Client("Client", "client@this.app",3, "00000003",new Address("teste","teste","teste","teste"),0000003);
        DateTime dateTime = new DateTime(12,2,2023);

        AnnouncementRequest announcementRequest= new AnnouncementRequest(businessType,property,agent, client);
        AnnouncementRequest announcementRequest1= new AnnouncementRequest(12,businessType2,property,agent,client);
        AnnouncementRequest announcementRequest2= new AnnouncementRequest(24,businessType2,property,agent,client, dateTime);
        AnnouncementRequest announcementRequest3= new AnnouncementRequest(40, businessType2, property, agent, client, dateTime);
        AnnouncementRequest announcementRequest4= new AnnouncementRequest(40, businessType2, property, agent2, client, dateTime);
        AnnouncementRequest announcementRequest5= new AnnouncementRequest(80, businessType2, property, agent, client, dateTime);
        AnnouncementRequest announcementRequest6= new AnnouncementRequest(55, businessType2, property, agent, client, dateTime);


        announcementRequestRepository.add(announcementRequest);
        announcementRequestRepository.add(announcementRequest1);
        announcementRequestRepository.add(announcementRequest2);
        announcementRequestRepository.add(announcementRequest3);
        announcementRequestRepository.add(announcementRequest4);
        announcementRequestRepository.add(announcementRequest5);
        announcementRequestRepository.add(announcementRequest6);
//        System.out.println(announcementRequest2.getDate());


    }

    private void addBusinessType(){
        BusinessTypeRepository businessTypeRepository =Repositories.getInstance().getBusinessTypeRepository();;
        businessTypeRepository.add(new BusinessType("Sale", "Buy"));
        businessTypeRepository.add(new BusinessType("Rent", "Rent"));
    }

    private void addPropertyType(){
        PropertyTypeRepository propertyTypeRepository =Repositories.getInstance().getPropertyTypeRepository();;
        propertyTypeRepository.add(new PropertyType("House"));
        propertyTypeRepository.add(new PropertyType("Apartment"));
        propertyTypeRepository.add(new PropertyType("Land"));
    }

    private void addStores(){
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();

        storeRepository.add(new Store(0, "HeadQuarters", "head@this.app", new Address("central","central","central","444-233"),  000000000));
        storeRepository.add(new Store(100, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000));
        storeRepository.add(new Store(101, "ESOFT","esoft@this.app", new Address("teste","teste","teste","teste"),  000000001));
        storeRepository.add(new Store(102,"Stark Industry", "stark@this.store", new Address("teste","teste","teste","teste"),911199922));
        storeRepository.add(new Store(103,"Remax", "remax@this.store", new Address("teste","teste","teste","teste"),900023123));
    }

    private void addClients(){
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        clientRepository.add(new Client("Client", "client@this.app",3, "00000003",new Address("teste","teste","teste","teste"),0000003));
        clientRepository.add(new Client("Client1", "client@gmail.com",3, "00000004",new Address("teste","teste","teste","teste"),1212121221));
        clientRepository.add(new Client("Client2", "client@hotmail.com",3, "00000005",new Address("teste","teste","teste","teste"),912312121));
        clientRepository.add(new Client("Client3", "client@yahoo.com",3, "00000006",new Address("teste","teste","teste","teste"),919191));

    }

    private void addProperty() {
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
        Address addressProperty2 = new Address("streetAddress", "Acity", "Astate", "zipCode");
        Address addressProperty = new Address("streetAddress", "New York", "Texas", "zipCode");
        Client client = new Client("client", "client@this.app", 2, "00000004" ,new Address("teste","teste","teste","teste"), 00000004);

        propertyRepository.add(new House(100.2, 5.3, addressProperty,
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("ESOFT"),
                client, new DateTime(24, 4, 2023)));

        propertyRepository.add(new House(90.2, 5.0, addressProperty,
                9.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 0,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("ESOFT"),
                client, new DateTime(4, 4, 2023)));


        propertyRepository.add(new House(150.1, 5.3, addressProperty,
                10.4, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("Stark Industry"),
                client, new DateTime(12,10,2001)));

        propertyRepository.add(new House(200.0, 4.0, addressProperty,
                10.0, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 4, 2, 2,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("PPROG"),
                client, new DateTime(8, 6, 2023)));


        propertyRepository.add(new Land(300.1, 3.3, addressProperty,
                7.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
                storeRepository.getStoreByDescription("PPROG"),
                client, new DateTime(1, 6, 2023)));


        propertyRepository.add(new Land(125.20, 2.3, addressProperty,
                8.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
                storeRepository.getStoreByDescription("Stark Industry"),
                client, new DateTime(27, 5, 2023)));


        propertyRepository.add(new Apartment(200.0, 3.5, addressProperty,
                6.4, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 4, List.of("equipment", "equipment1"),
                new PropertyType("House"),
                storeRepository.getStoreByDescription("PPROG"),
                client, new DateTime(24, 5, 2023)));

        propertyRepository.add(new House(120.2, 4.5, addressProperty2,
                11.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 1,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("PPROG"),
                client, new DateTime(22, 5, 2023)
        ));
        propertyRepository.add(new House(900.2, 6.3, addressProperty,
                9.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 1, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("ESOFT"),
                client, new DateTime(12, 5, 2023)
        ));

        propertyRepository.add(new Apartment(200.0, 4.6, addressProperty,
                6.4, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 5, 4, List.of("equipment", "equipment1"),
                new PropertyType("House"),
                storeRepository.getStoreByDescription("Remax"),
                client, new DateTime(24, 5, 2023)));

        propertyRepository.add(new House(120.2, 5.6, addressProperty2,
                12.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 0, 2, 1,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("PPROG"),
                client, new DateTime(22, 5, 2023)
        ));
        propertyRepository.add(new House(90.2, 3.4, addressProperty,
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 0, 0,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("Remax"),
                client, new DateTime(12, 5, 2023)
        ));

        propertyRepository.add(new House(120.2, 12.3, addressProperty,
                10.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 3, 3, 2,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                storeRepository.getStoreByDescription("Remax"),
                client, new DateTime(12, 5, 2023)
        ));
   }

    /**
     * Add announcement.
     */
    public void addAnnouncement(){

        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
       EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
       StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
       PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();


       DateTime date = new DateTime(12,5,2023);
       DateTime date1 = new DateTime(24, 5, 2023);
       DateTime date2 = new DateTime(1, 6, 2023);
       DateTime date3 = new DateTime(8, 6, 2023);

       Client client = new Client("client", "client@this.app", 2, "00000004" ,new Address("teste","teste","teste","teste"), 00000004);
       Employee employeeTest = employeeRepository.getEmployees().get(0);
       announcementRepository.add(new Announcement(employeeTest.getAssociatedAgent(), new DateTime(7,5,1995), 8.9,  new BusinessType("Sale", "Buy"),
               propertyRepository.getProperties().get(0), 100.5 ));

       announcementRepository.add(new Announcement(employeeRepository.getEmployees().get(1).getAssociatedAgent(), new DateTime(12,10,2001), 8.9,  new BusinessType("Sale", "Buy"),
               propertyRepository.getProperties().get(1),100 ));

        announcementRepository.add(new Announcement(employeeTest.getAssociatedAgent(), new DateTime(9,5,2000), 8.9,  new BusinessType("Sale", "Buy"),
                propertyRepository.getProperties().get(2), 200.5 ));

        announcementRepository.add(new Announcement(employeeRepository.getEmployees().get(1).getAssociatedAgent(), new DateTime(12,10,2001), 8.9,  new BusinessType("Sale", "Buy"),
                propertyRepository.getProperties().get(3),300 ));

        announcementRepository.add(new Announcement(employeeRepository.getEmployees().get(1).getAssociatedAgent(), new DateTime(12,10,2001), 8.9,  new BusinessType("Sale", "Buy"),
                propertyRepository.getProperties().get(4),300 ));

        announcementRepository.add(new Announcement(employeeRepository.getEmployees().get(1).getAssociatedAgent(), new DateTime(12,10,2001), 8.9,  new BusinessType("Sale", "Buy"),
                propertyRepository.getProperties().get(7),300 ));

        announcementRepository.add(new Announcement(employeeRepository.getEmployees().get(1).getAssociatedAgent(), new DateTime(12,10,2001), 8.9,  new BusinessType("Sale", "Buy"),
                propertyRepository.getProperties().get(6),300 ));

        announcementRepository.add(new Announcement(employeeRepository.getEmployees().get(1).getAssociatedAgent(), new DateTime(12,10,2001), 8.9,  new BusinessType("Sale", "Buy"),
                propertyRepository.getProperties().get(8),300 ));

        announcementRepository.add(new Announcement(employeeRepository.getEmployees().get(1).getAssociatedAgent(), new DateTime(12,10,2001), 8.9,  new BusinessType("Sale", "Buy"),
                propertyRepository.getProperties().get(9),200 ));

       announcementRepository.add(new Announcement( employeeTest.getAssociatedAgent() , new DateTime(17,5,2000), 8.9,  new BusinessType("Rent", "Rent"),
               propertyRepository.getProperties().get(9),200 ));

       announcementRepository.add(new Announcement(employeeTest.getAssociatedAgent(), new DateTime(12,10,2017), 8.9,  new BusinessType("Rent", "Rent"),
               propertyRepository.getProperties().get(10),100 ));
   }

    /**
     * Add purchase order.
     */
    public void addPurchaseOrder(){
       EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
       PurchaseOrderRepository purchaseOrderRepository = Repositories.getInstance().getPurchaseOrderRepository();
       AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();
       StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
       PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();
       Employee employeeTest = employeeRepository.getEmployees().get(0);
       DateTime date = new DateTime();

       Address address = new Address("streetAddress", "New York", "Texas", "zipCode");

       purchaseOrderRepository.addOrder(new PurchaseOrder(95, announcementRepository.getSaleAnnouncements().get(0), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004)));
       purchaseOrderRepository.addOrder(new PurchaseOrder(100, announcementRepository.getSaleAnnouncements().get(1), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(1).getDate()));
       purchaseOrderRepository.addOrder(new PurchaseOrder(125, announcementRepository.getSaleAnnouncements().get(2), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(2).getDate()));
       purchaseOrderRepository.addOrder(new PurchaseOrder(195, announcementRepository.getSaleAnnouncements().get(3), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(3).getDate()));
        purchaseOrderRepository.addOrder(new PurchaseOrder(105, announcementRepository.getSaleAnnouncements().get(4), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(4).getDate()));
        purchaseOrderRepository.addOrder(new PurchaseOrder(120, announcementRepository.getSaleAnnouncements().get(5), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(5).getDate()));
        purchaseOrderRepository.addOrder(new PurchaseOrder(100, announcementRepository.getSaleAnnouncements().get(6), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(6).getDate()));
        purchaseOrderRepository.addOrder(new PurchaseOrder(180, announcementRepository.getSaleAnnouncements().get(7), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(7).getDate()));
        purchaseOrderRepository.addOrder(new PurchaseOrder(93, announcementRepository.getSaleAnnouncements().get(8), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(8).getDate()));

//       purchaseOrderRepository.addOrder(new PurchaseOrder(125, announcementRepository.getSaleAnnouncements().get(2).getAnnouncement(), new Client("client", "client@this.app", 2, "00000004" ,address, 00000004), 1,  announcementRepository.getSaleAnnouncements().get(1).getAnnouncement().getDate()));


        //
//       SaleAnnouncement saleAnnouncement= new SaleAnnouncement(new Employee("aaa","agent@this.app",1111,"222",new Address("teste","teste","teste","teste" ),11111,
//               storeRepository.getStoreByDescription("PPROG"),
//               (new House(3.1, 5.3, addressProperty3,
//                       13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
//                       List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
//                       storeRepository.getStoreByDescription("PPROG"),
//                       new client("client", "client@this.app", 2, "00000004",address, 00000004),date)).getInhabitable().getProperty(),2.5 );
//
//
//       purchaseOrderRepository.addOrder(new PurchaseOrder(2.5, saleAnnouncement, new Client("client", "client@this.app", 2, "00000004" ,address, 00000004))) ;

    }

}