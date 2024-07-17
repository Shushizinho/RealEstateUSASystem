//package pt.ipp.isep.dei.esoft.project.ui.console;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import pt.ipp.isep.dei.esoft.project.application.controller.GetPropertyListController;
//import pt.ipp.isep.dei.esoft.project.domain.*;
//import pt.ipp.isep.dei.esoft.project.ui.console.GetPropertyListUI;
//import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class GetPropertyListUITest {
//
//    @Mock
//    private GetPropertyListController controller  = new GetPropertyListController();
//    @InjectMocks
//    private GetPropertyListUI getPropertyListUI;
//
//    private House property =new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
//            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
//            List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
//            new Store(10, "PPROG",  "pprog@this.app", new Address("teste","teste","teste","teste"),000000000),
//            new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),  new DateTime());
//    private Land property1 = new Land(3.1, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
//            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
//            new Store(10, "PPROG","pprog@this.app",  new Address("teste","teste","teste","teste"), 000000000),
//            new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),new DateTime());
//
//    private BusinessType businessType1 = new BusinessType("Sale", "Buy");
//    private BusinessType businessType2 = new BusinessType("Rent", "Rent");
//
//    private PropertyType propertyType1 = new PropertyType("House");
//    private PropertyType propertyType2 = new PropertyType("Land");
//
//    @BeforeEach
//    void setUp() {
//        getPropertyListUI = new GetPropertyListUI();
//    }
//
//    @Test
//    void testGetPropertyListByAnnouncement() {
//        List<Property> mockProperties = Arrays.asList(property.getInhabitable().getProperty(), property1.getProperty());
//        when(controller.getPropertieListByAnnouncemnent()).thenReturn(mockProperties);
//
//        List<Property> properties = getPropertyListUI.getPropertyListByAnnouncement();
//
//        assertEquals(2, properties.size());
//        verify(controller, times(1)).getPropertieListByAnnouncemnent();
//    }
//
//    @Test
//    void testDisplayConfirmationToFilterYes() {
//        Utils.setInputStream("yes\n");  // Mock user input
//
//        Boolean result = getPropertyListUI.displayConfirmationToFilter();
//
//        assertTrue(result);
//    }
//
//    @Test
//    void testDisplayConfirmationToFilterNo() {
//        Utils.setInputStream("no\n");  // Mock user input
//
//        Boolean result = getPropertyListUI.displayConfirmationToFilter();
//
//        assertFalse(result);
//    }
//
//    @Test
//    void testVerifyAnswersValid() {
//        when(controller.getBusinessTypes()).thenReturn(Arrays.asList(businessType1, businessType2));
//        when(controller.getPropertyType()).thenReturn(Arrays.asList(propertyType1, propertyType2));
//
//        ArrayList<String> filters = new ArrayList<>(3);
//        filters.add("3");
//        filters.add("3");
//        filters.add("no");
//
//        boolean result = getPropertyListUI.verifyAnswers(filters);
//
//        assertTrue(result);
//    }
//
//    @Test
//    void testVerifyAnswersInvalid() {
//        when(controller.getBusinessTypes()).thenReturn(Arrays.asList(businessType1, businessType2));
//        when(controller.getPropertyType()).thenReturn(Arrays.asList(propertyType1, propertyType2));
//
//        ArrayList<String> filters = new ArrayList<>(3);
//        filters.add("1");
//        filters.add("2");
//        filters.add("yes");
//
//
//        boolean result = getPropertyListUI.verifyAnswers(filters);
//
//        assertFalse(result);
//    }
//
//    @Test
//    void testDisplayAndSelectBusinessType() {
//
//        when(controller.getBusinessTypes()).thenReturn(Arrays.asList(businessType1, businessType2));
//
//        Utils.setInputStream("1\n");  // Mock user input
//
//        String result = getPropertyListUI.displayAndSelectBusinessType();
//
//        assertEquals("Sale", result);
//    }
//
//    @Test
//    void testDisplayAndSelectPropertyType() {
//        PropertyType propertyType1 = new PropertyType("Apartment");
//        PropertyType propertyType2 = new PropertyType("House");
//        when(controller.getPropertyType()).thenReturn(Arrays.asList(propertyType1, propertyType2));
//
//        Utils.setInputStream("2\n");  // Mock user input
//
//        String result = getPropertyListUI.displayAndSelectPropertyType();
//
//        assertEquals("House", result);
//    }
//
//    @Test
//    void testSelectNumberOfBedrooms() {
//        Utils.setInputStream("yes\n3\n");  // Mock user input
//
//        String result = getPropertyListUI.selectNumberOfBedrooms();
//
//        assertEquals("3", result);
//    }
//
//    @Test
//    void testDisplayPropertyFiltersList() {
//        Filters filter1 = new Filters("Price");
//        Filters filter2 = new Filters("Area");
//        when(controller.getPropertyFilters()).thenReturn(Arrays.asList(filter1, filter2));
//
//        Utils.setInputStream("1\n");  // Mock user input
//
//        String result = getPropertyListUI.displayPropertyFiltersList();
//
//        assertEquals("Price", result);
//    }
//
//    @Test
//    void testDisplayListOfProperties() {
//        Property property1 = mock(Property.class);
//        Property property2 = mock(Property.class);
//        when(property1.toString()).thenReturn("Property 1");
//        when(property2.toString()).thenReturn("Property 2");
//
//        List<Property> properties = Arrays.asList(property1, property2);
//
//        // Redirect System.out to capture output
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        getPropertyListUI.displayListOfProperties(properties);
//
//        String expectedOutput = "\u001B[36m#=======Property List=======#\n\u001B[0m1. Property 1\n\u001B[0m2. Property 2\n";
//        assertEquals(expectedOutput, outContent.toString());
//    }
//}
