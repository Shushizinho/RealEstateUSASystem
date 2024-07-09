package pt.ipp.isep.dei.esoft.project.repository;

//import org.apache.commons.lang3.NotImplementedException;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;
import pt.ipp.isep.dei.esoft.project.mapper.PropertyMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A repository class for managing all Properties.
 */
public class PropertyRepository {
    private final String APPROVED = "yes";

    private final String REJECTED = "no";

    private final String RENT = "Rent";

    private final String SALE = "Sale";

    private final String HOUSE = "House";

    private final String APPARTMENT = "Apartment";

    private final List<Property> properties = new ArrayList<>();
    private final List<Land> lands = new ArrayList<>();
    private static final List<Inhabitable> inhabitableProperties = new ArrayList<>();
    private final List<Apartment> apartments = new ArrayList<>();
    private final List<House> houses = new ArrayList<>();

    /**
     * This method returns an existing list of properties, sorted by a filter.
     *
     * @param choosenFilter the choosen filter
     * @param properties    the properties
     * @return list of properties.
     */
    public List<Property> sortPropertyList (String choosenFilter, List<Property> properties){
        List<Property> newProperty = properties;
        List<Filters> filters = Repositories.getInstance().propertyFiltersRepository.getFilters();
        int j = 0;
        if (choosenFilter.equals(filters.get(j).getDescription()) || choosenFilter.equals(filters.get(j + 1).getDescription())){
            newProperty = ComparatorPrice(properties, choosenFilter);
        }
        j+=2;
        if (choosenFilter.equals(filters.get(j).getDescription()) || choosenFilter.equals(filters.get(j + 1).getDescription())){
            newProperty = ComparatorCity(properties, choosenFilter);
        }
        j+=2;
        if (choosenFilter.equals(filters.get(j).getDescription()) || choosenFilter.equals(filters.get(j + 1).getDescription())){
            newProperty = ComparatorState(properties, choosenFilter);
        }

        return newProperty;
    }


    /**
     * This method returns an existing list of properties by businessType, propertyType and bedroomNo.
     *
     * @param properties   the properties
     * @return list of properties.
     */
    public List<Property> getPropertyList (List<Property> properties, ArrayList<String> list){
        List<Property> newProperty =properties;
        if (list.get(0).equals(SALE)){
            newProperty = getPropertiesBySaleAnnoucement(list.get(0));
        } else if (list.get(0).equals(RENT)) {
            newProperty = getPropertiesByRentAnnoucement(list.get(0));
        }
        newProperty = getPropertiesByPropertyType(list.get(1), newProperty);

        if ((list.get(1).equals(HOUSE)|| list.get(1).equals(APPARTMENT) && !newProperty.isEmpty())){
            if (!list.get(2).equals(REJECTED)) newProperty = getPropertiesByBedroomNo(Integer.parseInt(list.get(2)),newProperty);
        }


        return newProperty;
    }

    /**
     * This method sort a list of properties by price.
     *
     * @param properties    the properties
     * @param choosenFilter the choosen filter
     * @return list of properties
     */
    public List<Property> ComparatorPrice(List<Property> properties, String choosenFilter){
        List<Filters> filters = Repositories.getInstance().propertyFiltersRepository.getFilters();
        int j = 0;
        if(!properties.isEmpty()){
            if (choosenFilter.equals(filters.get(j).getDescription())){
                Collections.sort(properties, new PriceComparator());
            } else Collections.sort(properties, Collections.reverseOrder(new PriceComparator()));
        }

        return properties;
    }


    /**
     * This method sort a list of properties by city.
     *
     * @param properties    the properties
     * @param choosenFilter the choosen filter
     * @return list of properties
     */
    public List<Property> ComparatorCity(List<Property> properties, String choosenFilter){
        List<Filters> filters = Repositories.getInstance().propertyFiltersRepository.getFilters();

        int j = 2;
        if(!properties.isEmpty()){
            if (choosenFilter.equals(filters.get(j).getDescription())){
                Collections.sort(properties, new CityComparator());
            } else Collections.sort(properties, Collections.reverseOrder(new CityComparator()));
        }
        return properties;
    }

    /**
     * This method sort a list of properties by state.
     *
     * @param properties    the properties
     * @param choosenFilter the choosen filter
     * @return list of properties.
     */
    public List<Property> ComparatorState(List<Property> properties, String choosenFilter){
        List<Filters> filters = Repositories.getInstance().propertyFiltersRepository.getFilters();

        int j = 4;
        if(!properties.isEmpty()){
            if (choosenFilter.equals(filters.get(j).getDescription())){
                Collections.sort(properties, new StateComparator());
            } else Collections.sort(properties, Collections.reverseOrder(new StateComparator()));
        }

        return properties;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of properties.
     *
     * @return The list of properties.
     */
    public List<Property> getProperties(){
        return List.copyOf(properties);
    }

    /**
     * Gets apartments.
     *
     * @return the apartments
     */
    public List<Apartment> getApartments() {
        return apartments;
    }

    /**
     * Gets houses.
     *
     * @return the houses
     */
    public List<House> getHouses() {
        return houses;
    }

    /**
     * Gets inhabitable properties.
     *
     * @return the inhabitable properties
     */
    public List<Inhabitable> getInhabitableProperties() {
        return inhabitableProperties;
    }

    /**
     * Gets lands.
     *
     * @return the lands
     */
    public List<Land> getLands() {
        return lands;
    }

    /**
     * This method returns a list of properties by its Store.
     *
     * @param store the store
     * @return The property list
     */
    public List<Property> getPropertiesByStore(Store store){
        List<Property> propertiesCopy = List.copyOf(properties);
        List<Property> propertiesByStore = new ArrayList<>();
        for (Property property : propertiesCopy) {
            if (property.compareStore(store)) {
                propertiesByStore.add(property);
            }
        }
        return propertiesByStore;
    }

    /**
     * This method returns a list of properties that are on sale announcements.
     *
     * @param businessType the business type
     * @return the property list
     */
    public List<Property> getPropertiesBySaleAnnoucement(String businessType) {
        List<Announcement> an = Repositories.getInstance().announcementRepository.getSaleAnnouncementByBusinessType(businessType);

        List<Property> pr= new ArrayList<>();

        for (Announcement an1: an) {
            pr.add(an1.getProperty());
        }
        return pr;
    }

    /**
     * This method returns a list of properties that associated with announcements on a list.
     *
     * @param saleAnnouncementList the sale announcement list
     * @return the property list
     */
    public List<PropertyDTO> getPropertiesBySaleAnnoucementList(List<Announcement> saleAnnouncementList) {
        List<PropertyDTO> pr= new ArrayList<>();

        for (Announcement anouncement: saleAnnouncementList) {
//            System.out.println(anouncement.getProperty());
            pr.add(PropertyMapper.toDTO(anouncement.getProperty()));
        }
        return pr;
    }


    /**
     * This method returns a list of properties that are on rent announcements.
     *
     * @param businessType the business type
     * @return the property list
     */
    public List<Property> getPropertiesByRentAnnoucement(String businessType){
        List<Announcement> an = Repositories.getInstance().announcementRepository.getRentAnnouncementByProperty(businessType);

        List<Property> pr= new ArrayList<>();

        for (Announcement an1: an) {
            pr.add(an1.getProperty());
        }
        return pr;
    }

    /**
     * This method returns a list of properties by a chosen property type and a given list of properties.
     *
     * @param propertyType the property type
     * @param properties   the properties
     * @return the list of properties
     */
    public List<Property> getPropertiesByPropertyType(String propertyType, List<Property> properties){
        List<Property> p = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getPropertyType().getDescription().equals(propertyType)){
                p.add(properties.get(i));
            }
        }
        return p;
    }

    /**
     * This method returns a list of properties by a chosen bedroom number and a given list of properties.
     *
     * @param bedroomNo  the bedroom no
     * @param properties the properties
     * @return list list
     */
    public List<Property> getPropertiesByBedroomNo(int bedroomNo, List<Property> properties){

        List<Property> p = new ArrayList<>();
        for (Property property : properties) {
            Inhabitable in = getInhabitableByProperty(property);
            if (in != null){
                if ((in).getBedroomNumber() == bedroomNo){
                    p.add(property);
                }
            }

        }
        return p;
    }

    /**
     * Gets inhabitable by property.
     *
     * @param property the property
     * @return the inhabitable by property
     */
    public static Inhabitable getInhabitableByProperty(Property property) {
        for (Inhabitable inhabitable: inhabitableProperties) {
            if (inhabitable.getProperty().equals(property)) return inhabitable;
        }
        return null;
    }

    /**
     * Retrieves a property with the specified description.
     *
     * @param propertyDescription the description of the property
     * @return the Property object matching the description, or null if not found
     */
    public Property getPropertyByDescription(String propertyDescription) {
        for (Property property: properties) {
            String descriptionToCompare = property.toString();
            if (propertyDescription.compareTo(descriptionToCompare) == 0) return property;
        }
        return null;
    }


//    /**
//     * Returns the property in this repository that matches the given property data.
//     * @param property the property to be matched
//     * @return the property in this repository that matches the given property data, or null if no such property is found
//     */
//    public Property getPropertyByData(Property property) {
//        for (Property prop: properties) {
//            if (property.equals(prop)) return prop;
//        }
//        return null;
//    }

    /**
     * Adds a new property to the collection of properties managed by this repository.
     *
     * @param property the property to be added to the collection.
     * @return true if the property was successfully added, false otherwise.
     */
    public boolean add(Property property) {

        Optional<Property> newProperty;
        boolean operationSuccess = false;

        if (validateProperty(property)) {
            newProperty = Optional.of(property.clone());
            operationSuccess = properties.add(newProperty.get());
        }


        return operationSuccess;
    }

    /**
     * Add boolean.
     *
     * @param land the land
     * @return the boolean
     */
    public boolean add(Land land) {

        Optional<Property> newProperty;
        Optional<Land> newLand;
        boolean operationSuccess = false;

        if (validateProperty(land.getProperty())) {
            newProperty = Optional.of(land.getProperty().clone());
            newLand = Optional.of(land);
            operationSuccess = (lands.add(newLand.get()) && properties.add(newProperty.get()));
        }


        return operationSuccess;
    }

    /**
     * Add boolean.
     *
     * @param apartment the apartment
     * @return the boolean
     */
    public boolean add(Apartment apartment) {

        Optional<Property> newProperty;
        Optional<Apartment> newApartment;
        boolean operationSuccess = false;

        if (validateProperty(apartment.getInhabitable().getProperty())) {
            newProperty = Optional.of(apartment.getInhabitable().getProperty().clone());
            newApartment = Optional.of(apartment);
            operationSuccess = (apartments.add(newApartment.get()) && inhabitableProperties.add(newApartment.get().getInhabitable()) && properties.add(newProperty.get()));
        }


        return operationSuccess;
    }

    /**
     * Add boolean.
     *
     * @param house the house
     * @return the boolean
     */
    public boolean add(House house) {

        Optional<Property> newProperty;
        Optional<House> newHouse;
        boolean operationSuccess = false;

        if (validateProperty(house.getInhabitable().getProperty())) {
            newProperty = Optional.of(house.getInhabitable().getProperty().clone());
            newHouse = Optional.of(house);
            operationSuccess = (houses.add(newHouse.get()) && inhabitableProperties.add(newHouse.get().getInhabitable()) && properties.add(newProperty.get()));
        }


        return operationSuccess;
    }

    /**
     * Validates if a given property is valid to be added to the repository.
     *
     * @param property The property to be validated.
     * @return Returns true if the property is valid to be added, false otherwise.
     */
    public boolean validateProperty(Property property) {
        List<Property> propertiesByStore = getPropertiesByStore(property.getStore());
        boolean isValid = !propertiesByStore.contains(property);
        return isValid;
    }


    /**
     * Create property property.
     *
     * @param land the land
     * @return the property
     */
    public Property createProperty(LandDTO land){



        PropertyDTO property = land.getProperty();

        Property property1 = PropertyMapper.toEntity(property);
        Land land1 = PropertyMapper.landToEntity(land);



        return ( addProperty(property1) && addLand(land1) ) ? property1 : null;
    }

    /**
     * Create property property.
     *
     * @param propertyDTO                the property dto
     * @param propertyBedroomNumber      the property bedroom number
     * @param propertyBathroomNumber     the property bathroom number
     * @param propertyParkingSpaceNumber the property parking space number
     * @param propertyEquipmentList      the property equipment list
     * @param propertyHasBasement        the property has basement
     * @param propertyHasLoft            the property has loft
     * @param propertySunExposure        the property sun exposure
     * @return the property
     */
    public Property createProperty(PropertyDTO propertyDTO,Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList,
                                   Boolean propertyHasBasement, Boolean propertyHasLoft, String propertySunExposure){

        HouseDTO house = new HouseDTO(propertyDTO.getPrice(), propertyDTO.getArea(), propertyDTO.getAddress(), propertyDTO.getDistanceToCentre(),propertyDTO.getPhotographs(), propertyBedroomNumber,
                propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyHasBasement, propertyHasLoft,
                propertySunExposure,propertyDTO.getPropertyType(),propertyDTO.getStore(),propertyDTO.getClient(),propertyDTO.getDate());

        PropertyDTO property = house.getInhabitable().getProperty();

        Property property1 = PropertyMapper.toEntity(property);
        House house1 = PropertyMapper.houseToEntity(house);




        return ( addProperty(property1) && addHouse(house1) ) ? property1 : null;


    }

    /**
     * Create property property.
     *
     * @param propertyDTO                the property dto
     * @param propertyBedroomNumber      the property bedroom number
     * @param propertyBathroomNumber     the property bathroom number
     * @param propertyParkingSpaceNumber the property parking space number
     * @param propertyEquipmentList      the property equipment list
     * @return the property
     */
    public Property createProperty(PropertyDTO propertyDTO,Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList){

        ApartmentDTO apartmentDTO = new ApartmentDTO(propertyDTO.getPrice(), propertyDTO.getArea(), propertyDTO.getAddress(), propertyDTO.getDistanceToCentre(),propertyDTO.getPhotographs(), propertyBedroomNumber,
                propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList,propertyDTO.getPropertyType(),propertyDTO.getStore(),propertyDTO.getClient(),propertyDTO.getDate());

        PropertyDTO property = apartmentDTO.getInhabitable().getProperty();

        Property property1 = PropertyMapper.toEntity(property);
        Apartment apartment1 = PropertyMapper.apartmentToEntity(apartmentDTO);




        return ( addProperty(property1) && addApartment(apartment1) ) ? property1 : null;


    }


    /**
     * Create property property.
     *
     * @param apartmentDTO the apartment dto
     * @return the property
     */
    public Property createProperty(ApartmentDTO apartmentDTO) {

        //ApartmentDTO apartmentDTO = new ApartmentDTO(inhabitableDTO.getPrice(), propertyDTO.getArea(), propertyDTO.getAddress(), propertyDTO.getDistanceToCentre(),propertyDTO.getPhotographs(), propertyBedroomNumber,
          //      propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList,propertyDTO.getPropertyType(),propertyDTO.getStore(),propertyDTO.getclient(),propertyDTO.getDate());

        PropertyDTO property = apartmentDTO.getInhabitable().getProperty();

        Property property1 = PropertyMapper.toEntity(property);
        Apartment apartment1 = PropertyMapper.apartmentToEntity(apartmentDTO);




        return ( addProperty(property1) && addApartment(apartment1) ) ? property1 : null;


    }


    /**
     * Create property property.
     *
     * @param houseDTO the house dto
     * @return the property
     */
    public Property createProperty(HouseDTO houseDTO) {


        PropertyDTO property = houseDTO.getInhabitable().getProperty();

        Property property1 = PropertyMapper.toEntity(property);
        House house = PropertyMapper.houseToEntity(houseDTO);




        return ( addProperty(property1) && addHouse(house) ) ? property1 : null;


    }








 /*   public Property createProperty(HouseDTO houseDTO){


        PropertyDTO property = houseDTO.getInhabitable().getProperty();
        Property property1 = PropertyMapper.toEntity(property);
        House house =




       // return ( addProperty(property1) && addHouse(house) ) ? property : null;

    }*/


    /**
     * Creates a new property with the specified details.
     *
     * @param propertyPrice            the price of the property
     * @param propertyArea             the area of the property
     * @param propertyAddress          the address of the property
     * @param propertyDistanceToCentre the distance to the city center
     * @param propertyPhotographs      the list of photographs for the property
     * @param propertyType             the type of the property
     * @param store                    the store associated with the property
     * @param client                   the Client creating the property
     * @param date                     the date
     * @return the created Property object, or null if creation failed
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs,
                                   PropertyType propertyType, Store store, Client client, DateTime date){

        Land land = new Land(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyType, store, client, date);
        Property property = land.getProperty();



        return ( addProperty(property) && addLand(land) ) ? property : null;
    }

    /**
     * Creates a new property with the specified details.
     *
     * @param propertyPrice              the price of the property
     * @param propertyArea               the area of the property
     * @param propertyAddress            the address of the property
     * @param propertyDistanceToCentre   the distance to the city center
     * @param propertyPhotographs        the list of photographs for the property
     * @param propertyBedroomNumber      the number of bedrooms in the property
     * @param propertyBathroomNumber     the number of bathrooms in the property
     * @param propertyParkingSpaceNumber the number of parking spaces available
     * @param propertyEquipmentList      the list of equipment included in the property
     * @param propertyHasBasement        indicates if the property has a basement
     * @param propertyHasLoft            indicates if the property has a loft
     * @param propertySunExposure        the sun exposure of the property
     * @param propertyType               the type of the property
     * @param store                      the store associated with the property
     * @param client                     the Client creating the property
     * @param date                       the date
     * @return the created Property object, or null if creation failed
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs,
                                   Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList,
                                   Boolean propertyHasBasement, Boolean propertyHasLoft, String propertySunExposure,
                                   PropertyType propertyType, Store store, Client client, DateTime date){

        House house = new House(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber,
                propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyHasBasement, propertyHasLoft,
                propertySunExposure, propertyType, store, client, date);
        Property property = house.getInhabitable().getProperty();



        return ( addProperty(property) && addHouse(house) ) ? property : null;

    }

    /**
     * Creates a new property with the specified details.
     *
     * @param propertyPrice              the price of the property
     * @param propertyArea               the area of the property
     * @param propertyAddress            the address of the property
     * @param propertyDistanceToCentre   the distance of the property to the city centre
     * @param propertyPhotographs        the list of photographs of the property
     * @param propertyBedroomNumber      the number of bedrooms in the property
     * @param propertyBathroomNumber     the number of bathrooms in the property
     * @param propertyParkingSpaceNumber the number of parking spaces available in the property
     * @param propertyEquipmentList      the list of equipment available in the property
     * @param propertyType               the type of the property
     * @param store                      the store associated with the property
     * @param client                     the client of the property
     * @param date                       the date
     * @return the created property if it was successfully added to the repository, or null otherwise
     */
    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs,
                                   Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList,
                                   PropertyType propertyType, Store store, Client client, DateTime date){

        Apartment apartment = new Apartment(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber, propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyType, store, client, date);
        Property property = apartment.getInhabitable().getProperty();




        return ( addProperty(property) && addApartment(apartment) ) ? property : null;
    }

    /**
     * Adds a property to the repository.
     *
     * @param property the property to be added
     * @return true if the property was successfully added, false otherwise
     */
    private Boolean addProperty(Property property) {

        return properties.add(property);
    }
    private Boolean addLand(Land land) {

        return lands.add(land);
    }
    private Boolean addInhabitable(Inhabitable inhabitable) {

        return inhabitableProperties.add(inhabitable);
    }
    private Boolean addApartment(Apartment apartment) {
        if (addInhabitable(apartment.getInhabitable()))return apartments.add(apartment);
        return false;
    }
    private Boolean addHouse(House house) {
        if (addInhabitable(house.getInhabitable()))return houses.add(house);
        return false;
    }

    /**
     * Retrieves the last property in the list of properties.
     *
     * @return The last property in the list.
     */
    public Property getLast() {
        return properties.get(properties.size()-1);
    }

    /**
     * Remove.
     *
     * @param property the property
     */
    public void remove(Property property) {
        properties.removeIf(prop -> prop.equals(property));
        houses.removeIf(house -> house.getInhabitable().getProperty().equals(property));
        apartments.removeIf(apt -> apt.getInhabitable().getProperty().equals(property));
        inhabitableProperties.removeIf(inhab -> inhab.getProperty().equals(property));
        lands.removeIf(land -> land.getProperty().equals(property));
    }


//    /**
//     * Returns an ArrayList of properties that match the given BusinessType by iterating through all properties in the properties list.
//     *
//     * @param businessType the BusinessType to filter by
//     * @return an ArrayList of properties that match the given BusinessType
//     */
//    public ArrayList<Property> addPropertyToListByBusinessType(BusinessType businessType) {
//        ArrayList<Property> newProperty = new ArrayList<>();
//        int i = 0;
//        for (Property property : properties) {
//            if(businessType.equals(getAnnouncement(property))){
//                newProperty.add(property);
//            }
//            i++;
//        }
//       return newProperty;
//    }

}
