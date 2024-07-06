//package pt.ipp.isep.dei.esoft.project.repository;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
//import pt.ipp.isep.dei.esoft.project.domain.*;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
///**
// * The type Purchase order repository test.
// */
//class PurchaseOrderRepositoryTest {
//
//
//    @Test
//    @DisplayName("Should return all orders in the repository")
//    void getAllOrders() {// Create a PurchaseOrderRepository object
//        PurchaseOrderRepository purchaseOrderRepository = new PurchaseOrderRepository();
//
//        // Create some PurchaseOrder objects
//        PurchaseOrder order1 = new PurchaseOrder(1000.0, new Announcement(new Property("123 Main St", "New York", "NY", "USA", new PropertyType("House"), 200.0, 3, 2, 1), 900.0), new Client("John Doe", "john.doe@example.com"));
//        PurchaseOrder order2 = new PurchaseOrder(2000.0, new Announcement(new Property("456 Main St", "Los Angeles", "CA", "USA", new PropertyType("Apartment"), 100.0, 2, 1, 0), 1800.0), new Client("Jane Doe", "jane.doe@example.com"));
//
//        // Add the PurchaseOrder objects to the repository
//        purchaseOrderRepository.addOrder(order1);
//        purchaseOrderRepository.addOrder(order2);
//
//        // Get all the orders from the repository
//        List<PurchaseOrder> allOrders = purchaseOrderRepository.getAllOrders();
//
//        // Check that the size of the list is correct
//        assertEquals(2, allOrders.size());
//
//        // Check that the list contains the correct orders
//        assertTrue(allOrders.contains(order1));
//        assertTrue(allOrders.contains(order2));
//    }
//
//    /**
//     * Add order.
//     */
//    @Test
//    void addOrder() {
//
//        Repositories.getInstance().getAnnouncementRepository().add(new SaleAnnouncement(new Agent(new Employee("aaa", "aaaa", 1111, "222", new Address("teste", "teste", "teste", "teste"), 11111, new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 323232332), List.of(new Role(2, AuthenticationController.ROLE_AGENT)))), new DateTime(12, 2, 2001), 8.9, new BusinessType("Sale", "Buy"),
//                (new House(4.2, 5.3, new Address("streetAddress", "Acity", "Astate", "zipCode"),
//                        13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
//                        List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
//                        new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 000000000),
//                        new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004)
//                        , new DateTime())).getInhabitable().getProperty(), 2.3));
//
//        Client client = new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        PurchaseOrder order = new PurchaseOrder(2.5, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), client);
//        Boolean orderAdded = Repositories.getInstance().getPurchaseOrderRepository().addOrder(order);
//        assertEquals(true, orderAdded);
//    }
//
//    /**
//     * Add order higher price.
//     */
//    @Test
//    void addOrderHigherPrice() {
//        Repositories.getInstance().getAnnouncementRepository().add(
//                new SaleAnnouncement(
//                        new Agent(new Employee("aaa", "aaaa", 1111, "222", new Address("teste", "teste", "teste", "teste"), 11111, new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 323232332), List.of(new Role(2, AuthenticationController.ROLE_AGENT)))), new DateTime(12, 2, 2001), 8.9, new BusinessType("Sale", "Buy"),
//                        (new House(4.2, 5.3, new Address("streetAddress", "Acity", "Astate", "zipCode"),
//                                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
//                                List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
//                                new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 000000000),
//                                new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004)
//                                , new DateTime())).getInhabitable().getProperty()
//                        , 2.3));
//        Client client = new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        PurchaseOrder order = new PurchaseOrder(7.5, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), client);
//        Boolean orderAdded = Repositories.getInstance().getPurchaseOrderRepository().addOrder(order);
//        assertEquals(false, orderAdded);
//    }
//
//    /**
//     * Add order same price as another order.
//     */
//    @Test
//    void addOrderSamePriceAsAnotherOrder() {
//        Repositories.getInstance().getAnnouncementRepository().add(new SaleAnnouncement(new Agent(new Employee("aaa", "aaaa", 1111, "222", new Address("teste", "teste", "teste", "teste"), 11111, new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 323232332), List.of(new Role(2, AuthenticationController.ROLE_AGENT)))), new DateTime(12, 2, 2001), 8.9, new BusinessType("Sale", "Buy"),
//                (new House(4.2, 5.3, new Address("streetAddress", "Acity", "Astate", "zipCode"),
//                        13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
//                        List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
//                        new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 000000000),
//                        new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004)
//                        , new DateTime())).getInhabitable().getProperty(), 2.3));
//        Client clientA = new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        Client clientB = new Client("Persona", "persona@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        Repositories.getInstance().getPurchaseOrderRepository().addOrder(new PurchaseOrder(2.3, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), clientA));
//        PurchaseOrder order = new PurchaseOrder(2.3, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), clientB);
//        Boolean orderAdded = Repositories.getInstance().getPurchaseOrderRepository().addOrder(order);
//        assertEquals(true, orderAdded);
//    }
//
//    /**
//     * Add order has previous order.
//     */
//    @Test
//    void addOrderHasPreviousOrder() {
//        Repositories.getInstance().getAnnouncementRepository().add(new SaleAnnouncement(new Agent(new Employee("aaa", "aaaa", 1111, "222", new Address("teste", "teste", "teste", "teste"), 11111, new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 323232332), List.of(new Role(2, AuthenticationController.ROLE_AGENT)))), new DateTime(12, 2, 2001), 8.9, new BusinessType("Sale", "Buy"),
//                (new House(4.2, 5.3, new Address("streetsAddress", "Acity", "Astate", "zipCode"),
//                        13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
//                        List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
//                        new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 000000000),
//                        new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004)
//                        , new DateTime())).getInhabitable().getProperty(), 2.3));
//        Client cliente = new Client("PersonE", "persone@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        Repositories.getInstance().getPurchaseOrderRepository().addOrder(new PurchaseOrder(2.5, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), cliente));
//        PurchaseOrder order = new PurchaseOrder(2.5, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), cliente);
//        Boolean orderAdded = Repositories.getInstance().getPurchaseOrderRepository().addOrder(order);
//        assertEquals(false, orderAdded);
//    }
//
//    /**
//     * Add order lower price than another order.
//     */
//    @Test
//    void addOrderLowerPriceThanAnotherOrder() {
//        Repositories.getInstance().getAnnouncementRepository().add(new SaleAnnouncement(new Agent(new Employee("aaa", "aaaa", 1111, "222", new Address("teste", "teste", "teste", "teste"), 11111, new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 323232332), List.of(new Role(2, AuthenticationController.ROLE_AGENT)))), new DateTime(12, 2, 2001), 8.9, new BusinessType("Sale", "Buy"),
//                (new House(4.2, 5.3, new Address("streetAddress", "Acity", "Astate", "zipCode"),
//                        13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
//                        List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
//                        new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 000000000),
//                        new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004)
//                        , new DateTime())).getInhabitable().getProperty(), 2.3));
//        Client clientA = new Client("Personb", "personb@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        Client clientB = new Client("Personc", "personc@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        Repositories.getInstance().getPurchaseOrderRepository().addOrder(new PurchaseOrder(2.6, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), clientA));
//        PurchaseOrder order = new PurchaseOrder(1.5, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), clientB);
//        Boolean orderAdded = Repositories.getInstance().getPurchaseOrderRepository().addOrder(order);
//        assertEquals(true, orderAdded);
//    }
//
//    /**
//     * Add order higher price than another order.
//     */
//    @Test
//    void addOrderHigherPriceThanAnotherOrder() {
//        Repositories.getInstance().getAnnouncementRepository().add(new SaleAnnouncement(new Agent(new Employee("aaa", "aaaa", 1111, "222", new Address("teste", "teste", "teste", "teste"), 11111, new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 323232332), List.of(new Role(2, AuthenticationController.ROLE_AGENT)))), new DateTime(12, 2, 2001), 8.9, new BusinessType("Sale", "Buy"),
//                (new House(4.2, 5.3, new Address("streetAddress", "Acity", "Astate", "zipCode"),
//                        13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 1, 2, 3,
//                        List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
//                        new Store(1, "PPROG", "pprog@this.app", new Address("teste", "teste", "teste", "teste"), 000000000),
//                        new Client("Person", "person@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004)
//                        , new DateTime())).getInhabitable().getProperty(), 2.3));
//        Client clientA = new Client("Persodn", "persodn@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        Client clientB = new Client("Persone", "persone@this.app", 2, "00000004", new Address("teste", "teste", "teste", "teste"), 00000004);
//        Repositories.getInstance().getPurchaseOrderRepository().addOrder(new PurchaseOrder(2.7, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), clientA));
//        PurchaseOrder order = new PurchaseOrder(3.5, Repositories.getInstance().getAnnouncementRepository().getSaleAnnouncements().get(0), clientB);
//        Boolean orderAdded = Repositories.getInstance().getPurchaseOrderRepository().addOrder(order);
//        assertEquals(true, orderAdded);
//    }
//}