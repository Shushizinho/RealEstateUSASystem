package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementRequestMapper;
import pt.ipp.isep.dei.esoft.project.mapper.PairMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


/**
 * Repository class for storing and retrieving {@link Announcement} objects.
 */
public class AnnouncementRepository {

    private final List<Announcement> announcements =new ArrayList<>();
//    private final List<SaleAnnouncement> saleAnnouncements= new ArrayList<>();
//
//    private final List <RentAnnouncement> rentAnnouncements= new ArrayList<>();


    /**
     * Adds a new sale announcement to the repository.
     *
     * @param saleAnnouncement the sale announcement to be added to the repository
     * @return an Optional containing the newly added sale announcement if the operation was successful, or an empty Optional otherwise
     */
    public Optional <SaleAnnouncement> add(SaleAnnouncement saleAnnouncement){

        Optional<SaleAnnouncement>  newSaleAnnouncement = Optional.empty();

        boolean operationSuccess = false;

        if (validateSaleAnnouncement(saleAnnouncement)) {
            newSaleAnnouncement = Optional.of(saleAnnouncement.clone());
            operationSuccess = announcements.add(newSaleAnnouncement.get().getAnnouncement());
        }

        if (!operationSuccess) {
            newSaleAnnouncement = Optional.empty();
        }


        return newSaleAnnouncement;

    }


    /**
     * Add optional .
     *
     * @param Announcement the announcement
     * @return the optional
     */
    public Optional <Announcement> add(Announcement Announcement){

        Optional<Announcement>  newAnnouncement = Optional.empty();

        boolean operationSuccess = false;

        if (validateAnnouncement(Announcement)) {
            newAnnouncement = Optional.of(Announcement.clone());
            operationSuccess = announcements.add(newAnnouncement.get());
        }

        if (!operationSuccess) {
            newAnnouncement = Optional.empty();
        }

        return newAnnouncement;

    }

    /**
     * Adds a new rent announcement to the repository.
     *
     * @param rentAnnouncement the rent announcement to add
     * @return an {@code Optional} containing the newly added announcement if the operation was successful, or an empty {@code Optional} if it failed
     */
    public Optional <RentAnnouncement> add(RentAnnouncement rentAnnouncement){

        Optional<RentAnnouncement>  newRentAnnouncement = Optional.empty();

        boolean operationSuccess = false;

        if (validateRentAnnouncement(rentAnnouncement)) {
            newRentAnnouncement = Optional.of(rentAnnouncement.clone());
            operationSuccess = announcements.add(newRentAnnouncement.get().getAnnouncement());

        }

        if (!operationSuccess) {
            newRentAnnouncement = Optional.empty();
        }

        return newRentAnnouncement;

    }

    /**
     * Creates a new sale announcement based on the provided parameters.
     *
     * @param announcementRequestDTO the DTO object containing the announcement request data
     * @param commission             the commission for the announcement
     * @param listedPrice            the listed price for the property
     * @return an Optional containing the new sale announcement if it was successfully created, or an empty Optional if the announcement was not valid
     */
    public Optional<SaleAnnouncement> createSaleAnnouncement(AnnouncementRequestDTO announcementRequestDTO, double commission, double listedPrice){



        SaleAnnouncement saleAnnouncement = AnnouncementRequestMapper.SaleToEntity( announcementRequestDTO,  commission,  listedPrice);

        if (!validateSaleAnnouncement(saleAnnouncement)) {
            return Optional.empty();
        }

        Optional<SaleAnnouncement> newAnnouncement = Optional.of(saleAnnouncement.clone());

        announcements.add(saleAnnouncement.getAnnouncement());

        return newAnnouncement;


    }

    /**
     * Creates a new rent announcement based on the provided parameters.
     *
     * @param announcementRequestDTO the DTO object containing the announcement request data
     * @param commission             the commission for the announcement
     * @return an Optional containing the new rent announcement if it was successfully created, or an empty Optional if the announcement was not valid
     */
    public Optional<RentAnnouncement> createRentAnnouncement(AnnouncementRequestDTO announcementRequestDTO, double commission){

     RentAnnouncement rentAnnouncement= AnnouncementRequestMapper.RentToEntity(announcementRequestDTO,commission);


        if (!validateRentAnnouncement(rentAnnouncement)) {
            return Optional.empty();
        }

        Optional<RentAnnouncement> newAnnouncement = Optional.of(rentAnnouncement.clone());

        announcements.add(rentAnnouncement.getAnnouncement());

        return newAnnouncement;


    }


    public Optional<AnnouncementDTO> createAnnouncement(DateTime date, double commission, BusinessType businessType, Property property, Integer rentDuration, Agent agent){

        Announcement announcement = new Announcement(agent, date, commission, businessType, property, rentDuration);
        if (!validateAnnouncement(announcement)) {
            return Optional.empty();
        }

        announcements.add(announcement);

        AnnouncementDTO announcementDTO = AnnouncementMapper.toDTO(announcement);

        return Optional.of(announcementDTO);
    }

    public Optional<AnnouncementDTO> createAnnouncement(DateTime date, double commission, BusinessType businessType, Property property, double listedPrice, Agent agent){

        Announcement announcement = new Announcement(agent, date, commission, businessType, property, listedPrice);
        if (!validateAnnouncement(announcement)) {
            return Optional.empty();
        }

        announcements.add(announcement);

        AnnouncementDTO announcementDTO = AnnouncementMapper.toDTO(announcement);

        return Optional.of(announcementDTO);


    }

    /**
     * Create announcement optional.
     *
     * @param announcementRequestDTO the announcement request dto
     * @param commission             the commission
     * @return the optional
     */
    public Optional<Announcement> createAnnouncement( AnnouncementRequestDTO announcementRequestDTO, double commission){



        Announcement announcement = AnnouncementRequestMapper.AnnouncementToEntity(announcementRequestDTO,commission);

        if (!validateAnnouncement(announcement)) {
            return Optional.empty();
        }

        Optional<Announcement> newAnnouncement = Optional.of(announcement);

        announcements.add(announcement);
        System.out.println(newAnnouncement.get().getAgent().getEmployee().getName());

        return newAnnouncement;


    }


    /**
     * Create announcement optional.
     *
     * @param announcementDTO the announcement dto
     * @return the optional
     */
    public Optional<Announcement> createAnnouncement(AnnouncementDTO announcementDTO){



        Announcement announcement = AnnouncementMapper.toEntity(announcementDTO);

        if (!validateAnnouncement(announcement)) {
            return Optional.empty();
        }

        Optional<Announcement> newAnnouncement = Optional.of(announcement.clone());

        announcements.add(announcement);
        return newAnnouncement;


    }


//    /**
//     * Creates a new sale announcement based on the provided parameters.
//     *
//     * @param date         the date of the announcement
//     * @param commission   the commission for the announcement
//     * @param businessType the business type of the announcement
//     * @param property     the property associated with the announcement
//     * @param listedPrice  the listed price for the property
//     * @param agent        the employee responsible for the announcement
//     * @return an Optional containing the new sale announcement if it was successfully created, or an empty Optional if the announcement was not valid
//     */
//    public Optional<SaleAnnouncement> createSaleAnnouncement2(DateTime date, double commission, BusinessType businessType, Property property, double listedPrice, Agent agent){
//
//
//
//        SaleAnnouncement saleAnnouncement = new SaleAnnouncement(agent,date,commission,businessType,property,listedPrice);
//
//        if (!validateSaleAnnouncement(saleAnnouncement)) {
//            return Optional.empty();
//        }
//
//        Optional<SaleAnnouncement> newAnnouncement = Optional.of(saleAnnouncement.clone());
//
//        announcements.add(saleAnnouncement.getAnnouncement());
//
//        return newAnnouncement;
//
//
//    }
//
//    /**
//     * Creates a new rent announcement based on the provided parameters.
//     *
//     * @param date         the date of the announcement
//     * @param commission   the commission for the announcement
//     * @param businessType the business type of the announcement
//     * @param property     the property associated with the announcement
//     * @param rentDuration the duration of the rent for the property
//     * @param agent        the employee responsible for the announcement
//     * @return an Optional containing the new rent announcement if it was successfully created, or an empty Optional if the announcement was not valid
//     */
//    public Optional<RentAnnouncement> createRentAnnouncement2(DateTime date, double commission, BusinessType businessType, Property property, Integer rentDuration, Agent agent){
//
//        RentAnnouncement rentAnnouncement = new RentAnnouncement(agent,date,commission,businessType,property,rentDuration);
//        if (!validateRentAnnouncement(rentAnnouncement)) {
//            return Optional.empty();
//        }
//
//        Optional<RentAnnouncement> newAnnouncement = Optional.of(rentAnnouncement.clone());
//
//        announcements.add(rentAnnouncement.getAnnouncement());
//
//        return newAnnouncement;
//
//
//    }

    /**
     * Retrieves a copy of the list of sale announcements.
     *
     * @return a list of sale announcements
     */
    public List<Announcement> getSaleAnnouncements() {
        List<Announcement> saleAnnouncements = new ArrayList<>();
        for (Announcement announcement:announcements) {
            if (announcement.getBusinessType().equals(new BusinessType("Sale", "Buy"))) {
                saleAnnouncements.add(announcement);
            }
        }
//        saleAnnouncementsCopy.addAll(saleAnnouncements);
        return saleAnnouncements;
    }
//
//    /**
//     * Gets rent announcements.
//     *
//     * @return the rent announcements
//     */
//    public List<RentAnnouncement> getRentAnnouncements() {
//        List<RentAnnouncement> rentAnnouncementsCopy = new ArrayList<>();
//        rentAnnouncementsCopy.addAll(rentAnnouncements);
//        return rentAnnouncementsCopy;
//    }


    /**
     * Validate announcement boolean.
     *
     * @param announcement the announcement
     * @return the boolean
     */
    public boolean validateAnnouncement(Announcement announcement) {
        for(Announcement sa : announcements){
            if(sa.equals(announcement)){
                return false;
            }
        }
        return true;
    }


    /**
     * Validates if a SaleAnnouncement is valid to be added to the list of SaleAnnouncements.
     * A RentAnnouncement is considered valid if it does not already exist in the list of SaleAnnouncements.
     *
     * @param saleAnnouncement the SaleAnnouncement to be validated
     * @return true if the SaleAnnouncement is valid, false otherwise
     */
    public boolean validateSaleAnnouncement(SaleAnnouncement saleAnnouncement) {
      for(Announcement sa : announcements){
          if(sa.equals(saleAnnouncement.getAnnouncement())){
              return false;
          }
      }
      return true;
    }

    /**
     * Validates if a RentAnnouncement is valid to be added to the list of RentAnnouncements.
     * A RentAnnouncement is considered valid if it does not already exist in the list of RentAnnouncements.
     *
     * @param rentAnnouncement the RentAnnouncement to be validated
     * @return true if the RentAnnouncement is valid, false otherwise
     */
    public boolean validateRentAnnouncement(RentAnnouncement rentAnnouncement) {
        for(Announcement ra : announcements){

            if(ra.equals(rentAnnouncement.getAnnouncement())){
                return false;
            }

        }
        return true;
    }


    /**
     * Removes the specified SaleAnnouncement from the list of announcements.
     *
     * @param saleAnnouncement the SaleAnnouncement to remove
     * @return true if the SaleAnnouncement was removed successfully, false otherwise
     */
    public boolean removeSaleAnnouncement(SaleAnnouncement saleAnnouncement){

        return announcements.remove(saleAnnouncement.getAnnouncement());
    }

    /**

     Returns a list of all announcements stored in the system.
     @return a list of all announcements stored in the system
     */
    private List<Announcement> getAllAnnouncements() {
        return announcements;
    }

    /**
     * Returns an immutable copy of the list of all announcements in the system.
     *
     * @return an immutable copy of the list of all announcements in the system.
     */
    public List<Announcement> getAnnouncements(){
        return List.copyOf(announcements);
    }


    /**
     * Returns a list of all properties that are current with a given businessType.
     *
     * @param businessType the business type
     * @return a list of Property objects that are with a given businessType.
     */
    public List<Property> getAllPropertiesByBusinessType(String businessType){
        List<Property> properties = new ArrayList<>();
        for (Announcement announcement : announcements) {
            if (announcement.getBusinessType().getDescriptionProperty().equals(businessType)) {
                properties.add(announcement.getProperty());
            }
        }
        return properties;
    }


    /**
     * This method makes a list of all properties with an announcement.
     *
     * @return list of properties.
     */
    public List<Property> getPropertyListByAnnouncements(){
        List<Property> properties= getAllPropertiesByBusinessType("Rent");
        List<Property> properties1 = getAllPropertiesByBusinessType("Sale");
        properties.addAll(properties1);

        Collections.sort(properties, new DateComparator());

        return properties;
    }


    /**
     * Returns the sale announcement that has the given property.
     *
     * @param businessType the business type
     * @return the announcement that has the given property
     * @throws IllegalArgumentException if no announcement with the given property exists
     */
    public List<Announcement> getSaleAnnouncementByBusinessType(String businessType) {
        List<Announcement> an= new ArrayList<>();
        BusinessTypeRepository businessTypeRepository = Repositories.getInstance().getBusinessTypeRepository();


        for (Announcement announcement : announcements) {
            BusinessType businessType1 = announcement.getBusinessType();
            BusinessType businessType2 = businessTypeRepository.getBusinessTypeByDescription(businessType, false);
            if (businessType1.equals(businessType2)) {
                an.add(announcement);
            }
        }
        return an;
    }


    /**
     * Create sms.
     *
     * @param senderNumber    the sender number
     * @param agentName       the agent name
     * @param dateTime        the date time
     * @param address         the address
     */
    public boolean createSMS(long senderNumber,String agentName, DateTimeDTO dateTime, AddressDTO address) {
        boolean flag = false;
        String fileName = "Sms.txt";


        String fileContent = "Number: " + senderNumber + "\n"
                + "Address: " + address.toString() + "\n"
                + "Date and Time: " + dateTime.toStringDTO() + "\n"
                + "Agent: " + agentName + "\n"
                + "Dear owner,\n\n"
                + "We are pleased to inform you that the advertisement for your property has been published.\n"
                + "Please check our website or contact your agent for further details.\n\n";

        String directory = "src\\main\\resources\\passwords\\";

        try {
            File file = new File(directory, fileName);

            FileWriter writer;
            if (file.exists()) {
                writer = new FileWriter(file, true);
            } else {
                writer = new FileWriter(file);
            }

            writer.append("\n\n");
            writer.write(fileContent);
            writer.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }


    /**
     * Returns the rent announcement that has the given property.
     *
     * @param businessType the business type
     * @return the announcement that has the given property
     * @throws IllegalArgumentException if no announcement with the given property exists
     */
    public List<Announcement> getRentAnnouncementByProperty(String businessType) {
        List<Announcement> an= new ArrayList<>();
        BusinessTypeRepository businessTypeRepository = Repositories.getInstance().getBusinessTypeRepository();
        for (Announcement announcement : announcements) {
            BusinessType businessType1 = announcement.getBusinessType();
            BusinessType businessType2 = businessTypeRepository.getBusinessTypeByDescription(businessType, true);
            if (businessType1.equals(businessType2)) {
                an.add(announcement);
            }
        }
        return an;
    }

    /**
     * Retrieves the sale announcement associated with a given property.
     *
     * @param property the property to find the sale announcement for
     * @return the sale announcement associated with the property, or null if not found
     */
    public Announcement getAnnouncementByProperty(Property property) {
        for (Announcement announcement : announcements) {
            //announcement.getBusinessType().equals(new BusinessType("Sale", "Buy")) &&
            if (announcement.getProperty().equals(property)) {
                return announcement;
            }
        }
        return null;
    }

    /**
     * Gets announcements by store.
     *
     * @param store the store
     * @return the announcements by store
     */
    public List<Announcement> getAnnouncementsByStore(Store store) {
        List<Announcement> announcementList = new ArrayList<>();
        for (Announcement announcement : announcements) {
            if (announcement.getProperty().getStore().equals(store)) {
                announcementList.add(announcement);
            }
        }
        return announcementList;
    }


    /**
     * Returns the announcement that matches the given {@code BusinessType}.
     *
     * @param businessType the business type of the announcement to be retrieved
     * @return the announcement that matches the given business type
     * @throws IllegalArgumentException if no announcement with the given business type is found
     */
    public Announcement getAnnouncementByBusinessType(BusinessType businessType) {


        for ( Announcement announcement : announcements) {
            if (announcement.getBusinessType().equals(businessType)) {
                return announcement;
            }
        }
        throw new IllegalArgumentException("Announcement with business type [" + businessType + "] does not exist.");
    }

    /**
     * Filters a list of sale announcements based on store and price range criteria.
     *
     * @param announcementList the list of sale announcements to be filtered
     * @param store            the store to compare with (null if it is to not be filtered by store)
     * @param priceRange       the price range to compare with (null if it is to not be filtered by priceRange)
     * @return the filtered list of sale announcements
     */
    public List<Announcement> filterSaleAnnouncements(List<Announcement> announcementList, Store store, PairDTO<Integer, Integer> priceRange){
        List<Announcement> filteredAnnouncementList = new ArrayList<>();
        for (Announcement saleAnnouncement: announcementList) {
            if (checkStoreAndPrice(saleAnnouncement, store, PairMapper.toEntity(priceRange))) filteredAnnouncementList.add(saleAnnouncement);
        }
        return filteredAnnouncementList;
    }
    /**
     * Checks if a sale announcement satisfies the store and price range criteria.
     *
     * @param saleAnnouncement the sale announcement to be checked
     * @param store the store to compare with (null if it is to not be filtered by store)
     * @param priceRange the price range to compare with (null if it is to not be filtered by priceRange)
     * @return true if the sale announcement satisfies the criteria, false otherwise
     */
    private Boolean checkStoreAndPrice(Announcement saleAnnouncement, Store store, Pair<Integer, Integer> priceRange){
//        System.out.println(saleAnnouncement.getProperty());
        return ((saleAnnouncement.getProperty().getStore().equals(store) || store == null) && (saleAnnouncement.getProperty().priceInRange(priceRange) || priceRange == null));
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public Announcement getLast() {
        return announcements.get(announcements.size()-1);
    }






}


