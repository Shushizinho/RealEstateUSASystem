package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;

/**
 * The type Announcement repository test.
 */
class AnnouncementRepositoryTest {


    /**
     * Ensure sale announcement successfully added.
     */
    @Test
    void ensureSaleAnnouncementSuccessfullyAdded() {
        AnnouncementRepository AnnouncementRepository = new AnnouncementRepository();
        PropertyType propertyType= new PropertyType("House");
        BusinessType businessType= new BusinessType("House","Sale Announcement");
        Property property =  new Property(1.0f, 5.3f, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2f, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")),
                new PropertyType("House"),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime());

//        SaleAnnouncement announcement = new SaleAnnouncement("Casa",20,9111000,new DateTime(),23,propertyType,businessType,property,230000);

//         AnnouncementRepository.add(announcement);




    }

    /**
     * Ensure rent announcement successfully added.
     */
    @Test
    void ensureRentAnnouncementSuccessfullyAdded(){
        AnnouncementRepository AnnouncementRepository = new AnnouncementRepository();
        PropertyType propertyType= new PropertyType("House");
        BusinessType businessType= new BusinessType("House","Sale Announcement");
        Property property =  new Property(1.0f, 5.3f, new Address("streetAddress", "New York", "Texas", "zipCode"),
                13.2f, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")),
                new PropertyType("House"),
                new Client("Person", "person@this.app", 2, "00000004",new Address("teste","teste","teste","teste"), 00000004), new DateTime());
//        RentAnnouncement rentAnnouncement= new RentAnnouncement("Casa",350,9111,new DateTime(),23,propertyType,businessType,property,12);
//        AnnouncementRepository.add(rentAnnouncement);



    }










}