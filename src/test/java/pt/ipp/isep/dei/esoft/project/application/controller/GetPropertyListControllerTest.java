package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Get property list controller test.
 */
class GetPropertyListControllerTest {




    private final GetPropertyListController controller = new GetPropertyListController();

    private final PropertyFiltersRepository propertyFiltersRepository = Repositories.getInstance().getPropertyFiltersRepository();

    private final BusinessTypeRepository businessTypeRepository = Repositories.getInstance().getBusinessTypeRepository();

    private final PropertyTypeRepository propertyTypeRepository = Repositories.getInstance().getPropertyTypeRepository();

    private final PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();

    private final AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();

    /**
     * The Propertyfilters 1.
     */
    Filters propertyfilters_1 = new Filters("Price Ascendent");

    /**
     * The Propertyfilters 2.
     */
    Filters propertyfilters_2 = new Filters("Price Descendent");

    /**
     * The Business type 1.
     */
    static BusinessType businessType_1 = new BusinessType("Sale", "Buy");
    /**
     * The Business type 2.
     */
    BusinessType businessType_2 = new BusinessType("Rent", "Rent");

    /**
     * The Property type 1.
     */
    static PropertyType propertyType_1 = new PropertyType( "House");

    /**
     * The Property type 2.
     */
    PropertyType propertyType_2= new PropertyType( "Land");

    /**
     * The Property 1.
     */
    static Property property_1 = (new House(1.2, 5.3, new Address("streetAddress", "city", "state", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
            List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000),
            new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime())).getInhabitable().getProperty();

    /**
     * The Property 2.
     */
    static Property property_2 = (new Land(3.1, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
            new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000),
            new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime())).getProperty();

    /**
     * The Address.
     */
    static Address address = new Address("teste","teste","teste","teste");
    /**
     * The An 1.
     */
    static Announcement an1 = new Announcement(new Agent(new Employee("aaa","agent@this.app",1111,"222",new Address("teste","teste","teste","teste" ),11111,
            new Store(10, "PPROG", "pprog@this.app",address,323232332),List.of(new Role(2,AuthenticationController.ROLE_AGENT)))),new DateTime(), 25d, businessType_1, property_1);

    /**
     * The Sale announcement.
     */
    SaleAnnouncement saleAnnouncement = new SaleAnnouncement(an1, 1.2d);

    /**
     * The Rent announcement.
     */
    RentAnnouncement rentAnnouncement = new RentAnnouncement(an1, 10);

    /**
     * The An 2.
     */
    static Announcement an2 = new Announcement(new Agent(new Employee("aaa","agent@this.app",1111,"222",new Address("teste","teste","teste","teste" ),11111,
            new Store(10, "PPROG", "pprog@this.app",address,323232332),List.of(new Role(2,AuthenticationController.ROLE_AGENT)))),new DateTime(), 25d, businessType_1, property_2);

    /**
     * The Sale announcement 1.
     */
    SaleAnnouncement saleAnnouncement1 = new SaleAnnouncement(an2, 1.2d);

    /**
     * The Rent announcement 1.
     */
    RentAnnouncement rentAnnouncement1 = new RentAnnouncement(an2, 10);

    /**
     * Gets business types.
     */
    @Test
    @DisplayName("Get a list of businessTypes added")
    void getBusinessTypes() {
        
        businessTypeRepository.add(businessType_1);
        businessTypeRepository.add(businessType_2);


        List<BusinessType> expected = controller.getBusinessTypes();

        List<BusinessType> result = new ArrayList<>();
        result.add(new BusinessType("Sale", "Buy"));
        result.add(new BusinessType("Rent", "Rent"));

        assertEquals(expected,result);

    }

    /**
     * Gets business type repository test.
     */
    @Test
    @DisplayName("Test if the list of business type in repository is not null ")
    public void getBusinessTypeRepositoryTest() {
        assertNotNull(businessTypeRepository);
    }

    /**
     * Gets property filters.
     */
    @Test
    @DisplayName("Get a list of property filters added")
    void getPropertyFilters() {

        propertyFiltersRepository.add(propertyfilters_1);
        propertyFiltersRepository.add(propertyfilters_2);


        List<Filters> expected = controller.getPropertyFilters();

        List<Filters> result = new ArrayList<>();
        result.add( new Filters("Price Ascendent"));
        result.add( new Filters("Price Descendent"));

        assertEquals(expected,result);

    }

    /**
     * Gets property filters repository test.
     */
    @Test
    @DisplayName("Test if the list of property filters in repository is not null")
    public void getPropertyFiltersRepositoryTest() {
        assertNotNull(propertyFiltersRepository);
    }

    /**
     * Gets property type.
     */
    @Test
    @DisplayName("Get a list of property type added ")
    void getPropertyType() {
        propertyTypeRepository.add(propertyType_1);
        propertyTypeRepository.add(propertyType_2);


        List<PropertyType> expected = controller.getPropertyType();

        List<PropertyType> result = new ArrayList<>();
        result.add( new PropertyType("House"));
        result.add( new PropertyType("Land"));

        assertEquals(expected,result);

    }

    /**
     * Gets property type repository test.
     */
    @Test
    @DisplayName("Test if the list of property type in repository is not null")
    public void getPropertyTypeRepositoryTest() {
        assertNotNull(propertyTypeRepository);
    }

    /**
     * Gets property.
     */
    @Test
    @DisplayName("Get a list of property added ")
    void getProperty() {

        propertyRepository.add(property_1);
        propertyRepository.add(property_2);


        List<Property> expected = controller.getProperty();

        List<Property> result = new ArrayList<>();
        result.add( (new House(1.2, 5.3, new Address("streetAddress", "city", "state", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
                new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime())).getInhabitable().getProperty());
        result.add( (new Land(3.1, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
                new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime())).getProperty());

        assertEquals(expected,result);

    }

    /**
     * Gets property repository test.
     */
    @Test
    @DisplayName("Test if the list of properties in repository is not null")
    public void getPropertyRepositoryTest() {
        assertNotNull(propertyRepository);
    }

//    @Test
//    void TestGetPropertiesByBusinessTypeRent() {
//        List<Property> expectedProperties = new ArrayList<>();
//        expectedProperties.add(property_1);
//        expectedProperties.add(property_2);
//
//
//        announcementRepository.add(rentAnnouncement);
//        announcementRepository.add(rentAnnouncement1);
//
//
//        List<Property> actualProperties = controller.getPropertiesByBusinessType("Rent");
//
//        Assertions.assertEquals(expectedProperties.size(), actualProperties.size());
//        Assertions.assertEquals(expectedProperties, actualProperties);
//    }

    /**
     * Test get properties by business type sale.
     */
    @Test
    void TestGetPropertiesByBusinessTypeSale() {
        List<Property> expectedProperties = new ArrayList<>();
        expectedProperties.add(property_1);
        expectedProperties.add(property_2);


        announcementRepository.add(saleAnnouncement);
        announcementRepository.add(saleAnnouncement1);


        List<Property> actualProperties = controller.getPropertiesByBusinessType("Sale");

        Assertions.assertEquals(expectedProperties.size(), actualProperties.size());
        Assertions.assertEquals(expectedProperties, actualProperties);
    }


    /**
     * Gets announcement repository test.
     */
    @Test
    @DisplayName("Test if the list of announcements in repository is not null")
    public void getAnnouncementRepositoryTest() {
        assertNotNull(announcementRepository);
    }


}