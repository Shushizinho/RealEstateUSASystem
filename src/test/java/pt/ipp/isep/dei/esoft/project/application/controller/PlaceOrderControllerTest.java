//package pt.ipp.isep.dei.esoft.project.application.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import pt.ipp.isep.dei.esoft.project.domain.*;
//import pt.ipp.isep.dei.esoft.project.dto.PairDTO;
//import pt.ipp.isep.dei.esoft.project.dto.PropertyDTO;
//import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;
//import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//public class PlaceOrderControllerTest {
//    private PlaceOrderController placeOrderController = null;
//
//    @BeforeEach
//    void setUp() {
//        placeOrderController = new PlaceOrderController();
//    }
//
//    @Test
//    @DisplayName("Should return a formatted price range string")
//    void printPriceRange() {
//        PairDTO<Integer, Integer> priceRange = new PairDTO<>(100, 200);
//        String expected = "100-200";
//        String result = placeOrderController.printPriceRange(priceRange);
//        assertEquals(expected, result);
//    }
//
//    @Test
//    @DisplayName("Should return a list of stores")
//    void getStores() {// Mock the StoreRepository
//        Store store1 = new Store(1, "Store 1", "store1@example.com",  new Address("Street 1", "City 1", "12345-678","123"), 123456789);
//        Store store2 = new Store(2, "Store 2", "store2@example.com",  new Address("Street 2", "City 2", "23456-789","123"), 234567890);
//        StoreRepository storeRepository = mock(StoreRepository.class);
//        when(storeRepository.getStores()).thenReturn(List.of(store1,store2));
//
//
//        // Call the method under test
//        List<Store> stores = placeOrderController.getStores();
//
//        // Verify the result
//        assertEquals(2, stores.size());
//        assertEquals("Store1", stores.get(0).getDesignation());
//        assertEquals("store2@example.com", stores.get(1).getEmail());
//    }
//
//    @Test
//    @DisplayName("Should return a list of price ranges")
//    void getPriceRanges() {
//        List<PairDTO<Integer, Integer>> expectedPriceRanges = List.of(
//                new PairDTO<>(0, 100),
//                new PairDTO<>(100, 200),
//                new PairDTO<>(200, 300),
//                new PairDTO<>(300, 400),
//                new PairDTO<>(400, 500),
//                new PairDTO<>(500, 600),
//                new PairDTO<>(600, 700),
//                new PairDTO<>(700, 800),
//                new PairDTO<>(800, 900),
//                new PairDTO<>(900, 1000),
//                new PairDTO<>(1000, Integer.MAX_VALUE)
//        );
//
//        List<PairDTO<Integer, Integer>> actualPriceRanges = placeOrderController.getPriceRanges();
//
//        assertEquals(expectedPriceRanges.size(), actualPriceRanges.size());
//        for (int i = 0; i < expectedPriceRanges.size(); i++) {
//            assertEquals(expectedPriceRanges.get(i).getLeft(), actualPriceRanges.get(i).getLeft());
//            assertEquals(expectedPriceRanges.get(i).getRight(), actualPriceRanges.get(i).getRight());
//        }
//    }
//
//    @Test
//    @DisplayName("Should return a list of available properties for a given store and price range")
//    void getAvailableProperties() {
//        Store store = new Store(1, "Store 1", "store1@example.com",  new Address("Street 1", "City 1", "12345-678","123"), 123456789);
//        PairDTO<Integer, Integer> priceRange = new PairDTO<>(100, 200);
//        List<Announcement> announcementList = mock(List.class);
//
//        // Create Address objects
//        Address address1 = new Address("123 Main St", "City1", "State1", "12345");
//        Address address2 = new Address("456 Elm St", "City2", "State2", "67890");
//        Address address3 = new Address("789 Oak St", "City3", "State3", "98765");
//
//// Create PropertyType objects
//        PropertyType propertyType1 = new PropertyType("Land");
//        PropertyType propertyType2 = new PropertyType("Apartment");
//        PropertyType propertyType3 = new PropertyType("House");
//
//// Create Property objects
//        Property property1 = new Property(200000.0f, 1500.0f, address1, 5.0f, new ArrayList<>(), propertyType1, null, null);
//        Property property2 = new Property(300000.0f, 2000.0f, address2, 10.0f, new ArrayList<>(), propertyType2, null, null);
//        Property property3 = new Property(400000.0f, 2500.0f, address3, 15.0f, new ArrayList<>(), propertyType3, null, null);
//
//// Create Announcement objects
//        Announcement announcement1 = new Announcement(0.0, 0, null, null, property1, null, null, null, null);
//        Announcement announcement2 = new Announcement(0.0, 0, null, null, property2, null, null, null, null);
//        Announcement announcement3 = new Announcement(0.0, 0, null, null, property3, null, null, null, null);
//
//// Create a List of Announcements
//        List<Announcement> announcementList = new ArrayList<>();
//        announcementList.add(announcement1);
//        announcementList.add(announcement2);
//        announcementList.add(announcement3);
//        List<PropertyDTO> propertyList = mock(List.class);
//        when(placeOrderController.getAnnouncementRepository().getSaleAnnouncements()).thenReturn(announcementList);
//        when(placeOrderController.getAnnouncementRepository().filterSaleAnnouncements(announcementList, store, priceRange)).thenReturn(announcementList);
//        when(placeOrderController.getPropertyRepository().getPropertiesBySaleAnnoucementList(announcementList)).thenReturn(propertyList);
//
//        List<PropertyDTO> result = placeOrderController.getAvailableProperties(store, priceRange);
//
//        assertNotNull(result);
//        assertEquals(propertyList, result);
//        verify(placeOrderController.getAnnouncementRepository(), times(1)).getSaleAnnouncements();
//        verify(placeOrderController.getAnnouncementRepository(), times(1)).filterSaleAnnouncements(announcementList, store, priceRange);
//        verify(placeOrderController.getPropertyRepository(), times(1)).getPropertiesBySaleAnnoucementList(announcementList);
//    }
//
//}