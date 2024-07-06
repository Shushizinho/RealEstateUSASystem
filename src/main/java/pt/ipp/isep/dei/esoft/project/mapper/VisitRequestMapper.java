package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.dto.*;
import pt.ipp.isep.dei.esoft.project.domain.*;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Visit request mapper.
 */
public class VisitRequestMapper {
    /**
     * To dto visit request dto.
     *
     * @param visitRequest the visit request
     * @return the visit request dto
     */
    public static VisitRequestDTO toDTO(VisitRequest visitRequest) {
        Property property = visitRequest.getProperty();
        DateTime preferredDate = visitRequest.getPreferredDate();
        List<DateTime> timeSlot = visitRequest.getTimeSlot();

        String clientName = visitRequest.getClientName();
        long clientPhoneNumber = visitRequest.getClientPhoneNumber();

        AddressDTO addressDTO = AddressToAddressDTO(property.getAddress());

        DateTimeDTO dateTimeDTO = DateTimeToDateformattedDTO(property.getDate());

        PropertyDTO propertyDTO = new PropertyDTO(property.getPrice(), property.getArea(), addressDTO,
                property.getDistanceToCentre(), PhotographToDTO(property.getPhotographs()), PropertyTypeToDTO(property.getPropertyType()),
                StoreToDTO(property.getStore()), ClientToDTO(property.getClient()), dateTimeDTO);

        DateTimeDTO date =DateTimeToDateformattedDTO(preferredDate);

        if (visitRequest.accepted() == 0){
            return new VisitRequestDTO(propertyDTO, date, timeSlot, clientName, clientPhoneNumber);
        }else return new VisitRequestDTO(propertyDTO, date, timeSlot, clientName, clientPhoneNumber, visitRequest.accepted());


    }

    /**
     * To entity visit request.
     *
     * @param visitRequestDTO the visit request dto
     * @return the visit request
     */
    public static VisitRequest toEntity(VisitRequestDTO visitRequestDTO) {
        PropertyDTO propertyDTO = visitRequestDTO.getProperty();
        DateTimeDTO preferredDate = visitRequestDTO.getPreferredDate();
        DateTime preferredDate1 = DateTimeDTOToDateformatted(preferredDate);

        List<DateTime> timeSlot = visitRequestDTO.getTimeSlot();

        String clientName = visitRequestDTO.getClientName();
        long clientPhoneNumber = visitRequestDTO.getClientPhoneNumber();

        Property property = convertPropertyDTOToProperty(propertyDTO);

        if (visitRequestDTO.accepted() == 0){
            return new VisitRequest(property, preferredDate1, timeSlot, clientName, clientPhoneNumber);
        }else return new VisitRequest(property, preferredDate1, timeSlot, clientName, clientPhoneNumber, visitRequestDTO.accepted());

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
     * Convert property dto to property property.
     *
     * @param propertyDTO the property dto
     * @return the property
     */
    public static Property convertPropertyDTOToProperty(PropertyDTO propertyDTO) {
        double price = propertyDTO.getPrice();
        double area = propertyDTO.getArea();
        AddressDTO addressDTO = propertyDTO.getAddress();
        double distanceToCentre = propertyDTO.getDistanceToCentre();
        List<Photograph> photographs = PhotographToEntity(propertyDTO.getPhotographs());
        PropertyType propertyType = PropertyTypeToEntity(propertyDTO.getPropertyType());
        Store store = StoreToEntity(propertyDTO.getStore());
        Client client = ClientToEntity(propertyDTO.getClient());
        DateTimeDTO dateFormatted = propertyDTO.getDate();

        DateTime date = DateTimeDTOToDateformatted(dateFormatted);

        Address address = AdressDTOtoAdress(addressDTO);


        Property property = new Property(price, area, address , distanceToCentre, photographs, propertyType,store, client, date);

        return property;
    }


    /**
     * Adress dt oto adress address.
     *
     * @param addressDTO the address dto
     * @return the address
     */
    public static Address AdressDTOtoAdress (AddressDTO addressDTO){
        String street = addressDTO.getStreetAddress();
        String city = addressDTO.getCity();
        String state = addressDTO.getState();
        String zipCode = addressDTO.getZipCode();
        Address address = new Address(street, city, state, zipCode);

        return address;

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

        return  new DateTime(day, month, year);

    }
//    public static DateTimeDTO DateTimeTimeToDateformattedDTO (DateTime dateFormatted){
//        int hour = dateFormatted.getHour();
//        int min = dateFormatted.getMinute();
//
//        return new DateTimeDTO(hour, min);
//    }
//    public static DateTime DateTimeDTOTimeToDateformatted (DateTimeDTO dateFormattedDTO){
//        int hour = dateFormattedDTO.getHour();
//        int min = dateFormattedDTO.getMinute();
//
//        return new DateTime(hour, min);
//    }


    /**
     * Address to address dto address dto.
     *
     * @param address the address
     * @return the address dto
     */
    public static AddressDTO AddressToAddressDTO(Address address) {
        String street = address.getStreetAddress();
        String city = address.getCity();
        String state = address.getState();
        String zipCode = address.getZipCode();

        AddressDTO addressDTO = new AddressDTO(street, city, state, zipCode);

        return addressDTO;
    }

    /**
     * Get visit requests dto list.
     *
     * @param visitRequests the visit requests
     * @return the list
     */
    public static List<VisitRequestDTO> getVisitRequestsDTO(List<VisitRequest> visitRequests){

        List<VisitRequestDTO> visitRequestDTOs = new ArrayList<>();
        for (VisitRequest visitRequest : visitRequests) {
            VisitRequestDTO visitRequestDTO = VisitRequestMapper.toDTO(visitRequest);
            visitRequestDTOs.add(visitRequestDTO);
        }
        return visitRequestDTOs;
    }














}
