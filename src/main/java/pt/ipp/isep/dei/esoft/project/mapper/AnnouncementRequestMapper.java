package pt.ipp.isep.dei.esoft.project.mapper;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;


import java.util.ArrayList;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.mapper.PropertyMapper.ClientToDTO;


/**
 * The type Announcement request mapper.
 */
public class AnnouncementRequestMapper {

    /**
     * To dto announcement request dto.
     *
     * @param announcementRequest the announcement request
     * @return the announcement request dto
     */
    public static AnnouncementRequestDTO toDTO(AnnouncementRequest announcementRequest) {

        BusinessType businessType= announcementRequest.getBusinessTypeObject();
        Property property= announcementRequest.getProperty();
        AddressDTO addressDTO = addressToAddressDTO(property.getAddress());
        DateTimeDTO dateTimeDTO = DateTimeToDateformattedDTO(property.getDate());



        PropertyDTO propertyDTO= new PropertyDTO(property.getPrice(), property.getArea(), addressDTO, property.getDistanceToCentre(), PhotographToDTO(property.getPhotographs()) ,PropertyTypeToDTO(property.getPropertyType()),StoreToDTO(property.getStore()),ClientToDTO(property.getClient()), dateTimeDTO);
        ClientDTO clientDTO = clientToClientDTO(announcementRequest.getClient());

        AgentDTO agentDTO= employeeToEmployeeDTO(announcementRequest.getAgent());

        BusinessTypeDTO businessTypeDTO= new BusinessTypeDTO(businessType.getDescriptionProperty(), businessType.getDescriptionAnnoucement());

        DateTimeDTO dateFormattedTimeDTO = convertDateTimeToDateformattedDTO(announcementRequest.getDate());

        AnnouncementRequestDTO dto;
        if( announcementRequest.getBusinessType().equalsIgnoreCase("Rent")){

            dto = new AnnouncementRequestDTO(
                    announcementRequest.getRentDuration(),announcementRequest.getListedPrice(),
                    businessTypeDTO,
                    propertyDTO,
                    agentDTO,
                    clientDTO,
                    dateFormattedTimeDTO
            );

        }else{

            dto = new AnnouncementRequestDTO(
                    announcementRequest.getListedPrice(),
                    businessTypeDTO,
                    propertyDTO,
                    agentDTO,
                    clientDTO,
                    dateFormattedTimeDTO
            );

        }


        return dto;
    }


    /**
     * To entity announcement request.
     *
     * @param dto the dto
     * @return the announcement request
     */
    public static AnnouncementRequest toEntity(AnnouncementRequestDTO dto) {

        Property property= convertPropertyDTOToProperty(dto.getProperty());

        BusinessType businessType= businessTypeDTOToBusinessType(dto.getBusinessType());
        Agent employee= employeeDTOToEmployee( dto.getAgent());
        Client client= clientDTOToClient(dto.getClient());



        AnnouncementRequest entity = new AnnouncementRequest(dto.getRentDuration(),dto.getListedPrice(),businessType,property,employee,client);

        return entity;
    }

    /**
     * Sale to entity sale announcement.
     *
     * @param dto         the dto
     * @param commission  the commission
     * @param listedPrice the listed price
     * @return the sale announcement
     */
    public static SaleAnnouncement SaleToEntity(AnnouncementRequestDTO dto, double commission, double listedPrice) {

        AnnouncementRequest announcement = toEntity(dto);

        SaleAnnouncement entity = new SaleAnnouncement(announcement.getAgent(),announcement.getDate() ,commission,announcement.getBusinessTypeObject(), announcement.getProperty(),listedPrice);

        return entity;
    }

    /**
     * Rent to entity rent announcement.
     *
     * @param dto        the dto
     * @param commission the commission
     * @return the rent announcement
     */
    public static RentAnnouncement RentToEntity(AnnouncementRequestDTO dto, double commission) {

        AnnouncementRequest announcement = toEntity(dto);

        RentAnnouncement entity = new RentAnnouncement(announcement.getAgent(),announcement.getDate() ,commission,announcement.getBusinessTypeObject(), announcement.getProperty(), announcement.getRentDuration());

        return entity;
    }

    /**
     * Announcement to entity announcement.
     *
     * @param dto        the dto
     * @param commission the commission
     * @return the announcement
     */
    public static Announcement AnnouncementToEntity(AnnouncementRequestDTO dto, double commission) {

        AnnouncementRequest announcementRequest = toEntity(dto);

        Announcement entity = new Announcement(announcementRequest,commission);


        return entity;
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
     * Cliento dto client dto.
     *
     * @param entity the entity
     * @return the client dto
     */
    public static ClientDTO ClientoDTO(Client entity) {

        ClientDTO dto = new ClientDTO(entity.getName(), entity.getEmail(), entity.getPassportNumber(), entity.getPhoneNumber(), entity.getTaxNumber(), new AddressDTO(entity.getAddress().getStreetAddress(), entity.getAddress().getCity(), entity.getAddress().getState(), entity.getAddress().getZipCode()));

        return dto;
    }


    /**
     * Convert date time to dateformatted dto date time dto.
     *
     * @param dateTime the date time
     * @return the date time dto
     */
    public static DateTimeDTO convertDateTimeToDateformattedDTO (DateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonth();
        int year = dateTime.getYear();

        return new DateTimeDTO(day, month, year);
    }


        private static Property convertPropertyDTOToProperty(PropertyDTO propertyDTO) {
        double price = propertyDTO.getPrice();
        double area = propertyDTO.getArea();
        Address address = addressDTOtoAddress(propertyDTO.getAddress());
        double distanceToCentre = propertyDTO.getDistanceToCentre();
        List<Photograph> photographs = PhotographToEntity(propertyDTO.getPhotographs());
        PropertyType propertyType = PropertyTypeToEntity(propertyDTO.getPropertyType());
        Store store = StoreToEntity(propertyDTO.getStore());
        Client creator = ClientToEntity(propertyDTO.getClient());
        DateTime date = DateTimeDTOToDateformatted(propertyDTO.getDate());



        Property property = new Property(price, area, address , distanceToCentre, photographs, propertyType,store, creator, date);

        return property;
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
     * Role to role dto list.
     *
     * @param roles the roles
     * @return the list
     */
    public static List<RoleDTO> roleToRoleDTO(List<Role> roles) {
        List<RoleDTO> roleDTOs = new ArrayList<>();

        for (Role role : roles) {
            Integer id = role.getId();
            String description = role.getDescription();
            RoleDTO roleDTO = new RoleDTO(id, description);
            roleDTOs.add(roleDTO);
        }

        return roleDTOs;
    }


    /**
     * Role dto to role list.
     *
     * @param roleDTOList the role dto list
     * @return the list
     */
    public static List<Role> roleDTOToRole(List<RoleDTO> roleDTOList) {
        List<Role> roleList = new ArrayList<>();

        for (RoleDTO roleDTO : roleDTOList) {
            Integer id = roleDTO.getId();
            String description = roleDTO.getDescription();
            Role role = new Role(id, description);
            roleList.add(role);
        }

        return roleList;
    }


    /**
     * Client to client dto client dto.
     *
     * @param client the client
     * @return the client dto
     */
    public static  ClientDTO clientToClientDTO (Client client){

        String email= client.getEmail();
        String name= client.getName();
        Integer passportNumber= client.getPassportNumber();
        long phoneNumber= client.getPhoneNumber();
        String taxNumber= client.getTaxNumber();
        AddressDTO address= addressToAddressDTO(client.getAddress());



        return new ClientDTO(email, name, passportNumber, phoneNumber,taxNumber,address);
    }


    /**
     * Employee to employee dto agent dto.
     *
     * @param agent the agent
     * @return the agent dto
     */
    public static AgentDTO employeeToEmployeeDTO (Agent agent){
        String email= agent.getEmployee().getEmail();
        String name= agent.getEmployee().getName();
        Integer passportNumber= agent.getEmployee().getPassportNumber();;
        long phoneNumber= agent.getEmployee().getPhoneNumber();
        String taxNumber= agent.getEmployee().getTaxNumber();
        AddressDTO address= addressToAddressDTO(agent.getEmployee().getAddress());
        List <RoleDTO> role=roleToRoleDTO(agent.getEmployee().getRole());
        StoreDTO store=storeToStoreDTO(agent.getEmployee().getStore());
        EmployeeDTO employeeDTO= new EmployeeDTO(email, name, passportNumber, phoneNumber,taxNumber,address,role,store);

        return new AgentDTO(employeeDTO);

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
     * Store to store dto store dto.
     *
     * @param store the store
     * @return the store dto
     */
    public static StoreDTO storeToStoreDTO  (Store store){
         Integer id= Math.toIntExact(store.getId());
         String designation= store.getDesignation();
         String email=store.getEmail();
         long phoneNumber= store.getPhoneNumber();
        AddressDTO addressDTO = addressToAddressDTO(store.getAddress());

        return new StoreDTO(id,designation,addressDTO,email,phoneNumber);

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
     * Business type dto to business type business type.
     *
     * @param businessTypeDTO the business type dto
     * @return the business type
     */
    public static BusinessType businessTypeDTOToBusinessType( BusinessTypeDTO businessTypeDTO){
        String descriptionProperty= businessTypeDTO.getDescriptionProperty();
         String descriptionAnnouncement=businessTypeDTO.getDescriptionAnnouncement();

        return new BusinessType(descriptionProperty,descriptionAnnouncement);

    }

    /**
     * Employee dto to employee agent.
     *
     * @param agentDTO the agent dto
     * @return the agent
     */
    public static Agent employeeDTOToEmployee (AgentDTO agentDTO){
         String email= agentDTO.getEmployee().getEmail();
         String name=agentDTO.getEmployee().getName();
         Integer passportNumber= agentDTO.getEmployee().getPassportNumber();
         long phoneNumber=agentDTO.getEmployee().getPhoneNumber();
         String taxNumber=agentDTO.getEmployee().getTaxNumber();
         Address address= addressDTOtoAddress(agentDTO.getEmployee().getAddress());
         Store store= storeDTOToStore(agentDTO.getEmployee().getStore());
         List<Role> role=roleDTOToRole(agentDTO.getEmployee().getRole());

         Employee employee =new Employee(name, email,passportNumber,taxNumber,address,phoneNumber,store,role);


         return new Agent(employee);
    }


    /**
     * Store dto to store store.
     *
     * @param storeDTO the store dto
     * @return the store
     */
    public static Store storeDTOToStore (StoreDTO storeDTO){
        Integer id= storeDTO.getId();
        String designation= storeDTO.getDesignation();
        String email=storeDTO.getEmail();
        long phoneNumber= storeDTO.getPhoneNumber();
        Address address = addressDTOtoAddress(storeDTO.getAddress());

        return new Store(id,designation,email,address,phoneNumber);

    }


    /**
     * Client dto to client client.
     *
     * @param clientDTO the client dto
     * @return the client
     */
    public static  Client clientDTOToClient (ClientDTO clientDTO){

        String email= clientDTO.getEmail();
        String name= clientDTO.getName();
        Integer passportNumber= clientDTO.getPassportNumber();
        long phoneNumber= clientDTO.getPhoneNumber();
        String taxNumber= clientDTO.getTaxNumber();
        Address address= addressDTOtoAddress(clientDTO.getAddress());



        return new Client(email, name, passportNumber, taxNumber, address, phoneNumber);
    }

    /**
     * Get announcement requests dto list.
     *
     * @param announcementRequests the announcement requests
     * @return the list
     */
    public static List<AnnouncementRequestDTO> getAnnouncementRequestsDTO(List<AnnouncementRequest> announcementRequests ){

        List<AnnouncementRequestDTO> announcementRequestDTOS = new ArrayList<>();
        for (AnnouncementRequest announcementRequest : announcementRequests) {
            AnnouncementRequestDTO announcementRequestDTO = AnnouncementRequestMapper.toDTO(announcementRequest);
           announcementRequestDTOS.add(announcementRequestDTO);
        }
        return announcementRequestDTOS;
    }


    /**
     * Get announcement requests list.
     *
     * @param announcementRequests the announcement requests
     * @return the list
     */
    public static List<AnnouncementRequest> getAnnouncementRequests(List<AnnouncementRequestDTO> announcementRequests ){

        List<AnnouncementRequest> announcementRequestsEntity = new ArrayList<>();

        for (AnnouncementRequestDTO announcementRequest : announcementRequests) {
            AnnouncementRequest announcementRequests2 = AnnouncementRequestMapper.toEntity(announcementRequest);
            announcementRequestsEntity.add(announcementRequests2);
        }
        return announcementRequestsEntity;
    }







}
