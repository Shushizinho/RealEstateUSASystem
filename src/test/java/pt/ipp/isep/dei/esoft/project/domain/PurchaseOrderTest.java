package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.Test;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PurchaseOrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PurchaseOrderTest {
    private final Address address = new Address("teste","teste","teste","teste");

    private final Client client = new Client("Agent", "agent@this.app", 2, "111-11-0439", address ,910532123);

    private final List<Role> list = new ArrayList<>();
    private final Store store = new Store(0, "HeadQuarters", "head@this.app", new Address("central","central","central","444-233"),  000000000);
    private final Employee employee = new Employee("Agent", "agent@this.app", 2, "111-11-0439", address ,910532123, store, list);

    private final House house = new House(1.0, 5.3, new Address("streetAddress", "New York", "Texas", "zipCode"),
            13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), 2, 2, 3,
            List.of("equipment", "equipment1"), true, false, "norte", new PropertyType("House"),
            new Store(10, "PPROG",  "pprog@this.app", new Address("teste","teste","teste","teste"),000000000),
            new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"),  00000004),  new DateTime());


    private final Announcement announcement = new Announcement(employee.getAssociatedAgent(), new DateTime(7,5,1995), 8.9,  new BusinessType("Sale", "Buy"),
            house.getInhabitable().getProperty(), 100.5);
    private final PurchaseOrderRepository purchaseOrderRepository = Repositories.getInstance().getPurchaseOrderRepository();


    @Test
    public void savePurchaseOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder(105.0, announcement, client);
        purchaseOrderRepository.addOrder(purchaseOrder);
        assertNotNull(purchaseOrderRepository.getOrdersByAnnouncement(announcement));
    }

}
