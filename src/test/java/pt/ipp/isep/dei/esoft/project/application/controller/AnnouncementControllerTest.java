package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

/**
 * The type Announcement controller test.
 */
public class AnnouncementControllerTest {
    private final AnnouncementController controller = new AnnouncementController();
    /**
     * The Property type.
     */
    PropertyType propertyType= new PropertyType("House");
    /**
     * The Business type.
     */
    BusinessType businessType= new BusinessType("Sale","Venda");

    /**
     * The Property.
     */
    Property property = new Property( 1.2f, 5.3f, new Address("streetAddress", "city", "state", "zipCode"),
            13.2f, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")),new PropertyType("House"),
            new Client("Person", "person@this.app", 2, "811212312",new Address("teste","teste","teste","teste"), 811212312), new DateTime());

//    SaleAnnouncement saleAnnouncement1=new SaleAnnouncement("Casa",23000,91111,new DateTime(),23,propertyType,businessType,property,500000);

    /**
     * The Store.
     */
    Store store = new Store(1,"Loja","loja@store.ola",new Address("258 Rua das flores", "Porot", "Porto", "4455-014"),912234444);
    /**
     * The Store 2.
     */
    Store store2 = new Store(2, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste") , 000000000);


    /**
     * Create sale announcement.
     */
    @Test
    @DisplayName("Register a new Sale Announcement that already exists")
    void createSaleAnnouncement(){



        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();

//       announcementRepository.add(saleAnnouncement1);
//        Optional<SaleAnnouncement> result = controller.createSaleAnnouncement("Casa",23000,91111, new DateTime(),23, businessType, propertyType,property,500000);

//        assertTrue(result.isEmpty());
    }

/*
    @Test
    @DisplayName("Register a new Sale Announcement")
    void createIfNotExists(){


        Optional<SaleAnnouncement> result = controller.createSaleAnnouncement("Casa",23000,91111,2023,23, businessType, propertyType,property,500000);

        assertTrue(result.isPresent());

    }
*/



}
