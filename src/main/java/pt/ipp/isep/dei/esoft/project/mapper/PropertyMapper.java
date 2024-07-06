package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Property mapper.
 */
public class PropertyMapper {


    /**
     * To dto property dto.
     *
     * @param property the property
     * @return the property dto
     */
    public static PropertyDTO toDTO(Property property) {
        double price = property.getPrice();
         double area = property.getArea();
         AddressDTO address = addressToAddressDTO(property.getAddress());
         double distanceToCentre = property.getDistanceToCentre();
         List<PhotographDTO> photographs = PhotographToDTO(property.getPhotographs());
         PropertyTypeDTO propertyType = PropertyTypeToDTO(property.getPropertyType());
         StoreDTO store = StoreToDTO(property.getStore()) ;
         ClientDTO client = ClientToDTO(property.getClient());

         DateTimeDTO date = DateTimeToDateformattedDTO(property.getDate());



      return new PropertyDTO(price, area, address, distanceToCentre, photographs, propertyType, store, client,date);
    }


    /**
     * To entity property.
     *
     * @param property the property
     * @return the property
     */
    public static Property toEntity(PropertyDTO property){
        double price = property.getPrice();
        double area = property.getArea();
        Address address = addressDTOtoAddress(property.getAddress());
        double distanceToCentre = property.getDistanceToCentre();
        List<Photograph> photographs = PhotographToEntity(property.getPhotographs());
        PropertyType propertyType = PropertyTypeToEntity(property.getPropertyType());
        Store store = StoreToEntity(property.getStore()) ;
        Client client = ClientToEntity(property.getClient());

        DateTime date = DateTimeDTOToDateformatted(property.getDate());

        return new Property(price, area, address, distanceToCentre, photographs, propertyType, store ,client ,date);



    }


    /**
     * Land to entity land.
     *
     * @param landDTO the land dto
     * @return the land
     */
    public static Land landToEntity (LandDTO landDTO){
        Property property = toEntity(landDTO.getProperty());

        return  new Land(property);
    }


    /**
     * Date time to dateformatted dto date time dto.
     *
     * @param dateTime the date time
     * @return the date time dto
     */
    public static DateTimeDTO DateTimeToDateformattedDTO (DateTime dateTime){
        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonth();
        int year = dateTime.getYear();

        return new DateTimeDTO(day, month, year);
    }

    /**
     * Date time dto to dateformatted date time.
     *
     * @param dateTimeDTO the date time dto
     * @return the date time
     */
    public static DateTime DateTimeDTOToDateformatted (DateTimeDTO dateTimeDTO){
        int day = dateTimeDTO.getDayOfMonth();
        int month = dateTimeDTO.getMonth();
        int year = dateTimeDTO.getYear();

        DateTime dateTime = new DateTime(day, month, year);
        return dateTime;
    }


    /**
     * Address to address dto address dto.
     *
     * @param address the address
     * @return the address dto
     */
    public static AddressDTO addressToAddressDTO  (Address address){
        String street = address.getStreetAddress();
        String city = address.getCity();
        String state = address.getState();
        String zipCode = address.getZipCode();
        AddressDTO addressDTO = new AddressDTO(street, city, state, zipCode);

        return addressDTO;

    }


    /**
     * Photograph to dto list.
     *
     * @param entity the entity
     * @return the list
     */
    public static List<PhotographDTO> PhotographToDTO(List<Photograph> entity) {
        List<PhotographDTO> dto = new ArrayList<>();
        for (Photograph photo: entity) {
            dto.add(new PhotographDTO(photo.getUri()));
        }

        return dto;
    }


    /**
     * Property type to dto property type dto.
     *
     * @param entity the entity
     * @return the property type dto
     */
    public static PropertyTypeDTO PropertyTypeToDTO(PropertyType entity) {

        PropertyTypeDTO dto = new PropertyTypeDTO(entity.getDescription());

        return dto;
    }

    /**
     * Store to dto store dto.
     *
     * @param entity the entity
     * @return the store dto
     */
    public static StoreDTO StoreToDTO(Store entity) {

        StoreDTO dto = new StoreDTO((int) entity.getId(), entity.getDesignation(), new AddressDTO(entity.getAddress().getStreetAddress(), entity.getAddress().getCity(), entity.getAddress().getState(), entity.getAddress().getZipCode()), entity.getEmail(), entity.getPhoneNumber());

        return dto;
    }


    /**
     * Client to dto client dto.
     *
     * @param entity the entity
     * @return the client dto
     */
    public static ClientDTO ClientToDTO(Client entity) {

        ClientDTO dto = new ClientDTO(entity.getName(), entity.getEmail(), entity.getPassportNumber(), entity.getPhoneNumber(), entity.getTaxNumber(), new AddressDTO(entity.getAddress().getStreetAddress(), entity.getAddress().getCity(), entity.getAddress().getState(), entity.getAddress().getZipCode()));

        return dto;
    }

    /**
     * Address dt oto address address.
     *
     * @param addressDTO the address dto
     * @return the address
     */
    public static Address addressDTOtoAddress (AddressDTO addressDTO){
        String street = addressDTO.getStreetAddress();
        String city = addressDTO.getCity();
        String state = addressDTO.getState();
        String zipCode = addressDTO.getZipCode();
        Address address = new Address(street, city, state, zipCode);

        return address;

    }

    /**
     * Photograph to entity list.
     *
     * @param dto the dto
     * @return the list
     */
    public static List<Photograph> PhotographToEntity(List<PhotographDTO> dto) {
        List<Photograph> entity = new ArrayList<>();
        for (PhotographDTO photo: dto) {
            entity.add(new Photograph(photo.getUri()));
        }

        return entity;
    }

    /**
     * Property type to entity property type.
     *
     * @param dto the dto
     * @return the property type
     */
    public static PropertyType PropertyTypeToEntity(PropertyTypeDTO dto) {

        PropertyType entity = new PropertyType(dto.getDescription());

        return entity;


    }

    /**
     * Store to entity store.
     *
     * @param dto the dto
     * @return the store
     */
    public static Store StoreToEntity(StoreDTO dto) {

        Store entity = new Store(dto.getId(), dto.getDesignation(), dto.getEmail(), new Address(dto.getAddress().getStreetAddress(), dto.getAddress().getCity(), dto.getAddress().getState(), dto.getAddress().getZipCode()), dto.getPhoneNumber());

        return entity;
    }

    /**
     * Client to entity client.
     *
     * @param dto the dto
     * @return the client
     */
    public static Client ClientToEntity(ClientDTO dto) {

        Client entity = new Client(dto.getName(), dto.getEmail(), dto.getPassportNumber(), dto.getTaxNumber(), new Address(dto.getAddress().getStreetAddress(), dto.getAddress().getCity(), dto.getAddress().getState(), dto.getAddress().getZipCode()), dto.getPhoneNumber());

        return entity;
    }

    /**
     * To entity inhabitable.
     *
     * @param inhabitableDTO the inhabitable dto
     * @return the inhabitable
     */
    public static Inhabitable toEntity(InhabitableDTO inhabitableDTO){
        double price = inhabitableDTO.getProperty().getPrice();
        double area = inhabitableDTO.getProperty().getArea();
        Address address = addressDTOtoAddress(inhabitableDTO.getProperty().getAddress());
        double distanceToCentre = inhabitableDTO.getProperty().getDistanceToCentre();
        List<Photograph> photographs = PhotographToEntity(inhabitableDTO.getProperty().getPhotographs());
        PropertyType propertyType = PropertyTypeToEntity(inhabitableDTO.getProperty().getPropertyType());
        Store store = StoreToEntity(inhabitableDTO.getProperty().getStore()) ;
        Client client = ClientToEntity(inhabitableDTO.getProperty().getClient());

        DateTime date = DateTimeDTOToDateformatted(inhabitableDTO.getProperty().getDate());

        int bedroomNumber = inhabitableDTO.getBedroomNumber();
        int bathroomNumber = inhabitableDTO.getBathroomNumber();
        int parkingSpaceNumber = inhabitableDTO.getParkingSpaceNumber();
        List<String> equipmentList = inhabitableDTO.getEquipmentList();

        return new Inhabitable(price, area, address, distanceToCentre, photographs, bedroomNumber, bathroomNumber, parkingSpaceNumber,  equipmentList,propertyType, store, client, date);
    }


    /**
     * To dto inhabitable dto.
     *
     * @param inhabitable the inhabitable
     * @return the inhabitable dto
     */
    public static InhabitableDTO toDTO(Inhabitable inhabitable) {
        PropertyDTO propertyDTO = new PropertyDTO(
                inhabitable.getProperty().getPrice(),
                inhabitable.getProperty().getArea(),
                addressToAddressDTO(inhabitable.getProperty().getAddress()),
                inhabitable.getProperty().getDistanceToCentre(),
                PhotographToDTO(inhabitable.getProperty().getPhotographs()),
                PropertyTypeToDTO(inhabitable.getProperty().getPropertyType()),
               StoreToDTO(inhabitable.getProperty().getStore()),
                ClientToDTO(inhabitable.getProperty().getClient()),
               DateTimeToDateformattedDTO(inhabitable.getProperty().getDate())
        );

        InhabitableDTO inhabitableDTO = new InhabitableDTO(
                propertyDTO,
                inhabitable.getBedroomNumber(),
                inhabitable.getBathroomNumber(),
                inhabitable.getParkingSpaceNumber(),
                inhabitable.getEquipmentList()
        );

        return inhabitableDTO;
    }


    /**
     * House to entity house.
     *
     * @param houseDTO the house dto
     * @return the house
     */
    public static House houseToEntity(HouseDTO houseDTO) {

        Inhabitable inhabitable = toEntity(houseDTO.getInhabitable());

        return new House(inhabitable,houseDTO.getHasBasement(), houseDTO.getHasLoft(), houseDTO.getSunExposure());
    }

    /**
     * Apartment to entity apartment.
     *
     * @param apartmentDTO the apartment dto
     * @return the apartment
     */
    public static Apartment apartmentToEntity(ApartmentDTO apartmentDTO) {

        Inhabitable inhabitable = toEntity(apartmentDTO.getInhabitable());

        return new Apartment(inhabitable);
    }




}
