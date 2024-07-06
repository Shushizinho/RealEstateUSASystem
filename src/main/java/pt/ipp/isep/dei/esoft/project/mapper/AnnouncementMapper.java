package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Announcement mapper.
 */
public class AnnouncementMapper {


    /**
     * To dto announcement dto.
     *
     * @param announcement the announcement
     * @return the announcement dto
     */
    public static AnnouncementDTO toDTO(Announcement announcement) {


        BusinessType businessType= announcement.getBusinessType();


        BusinessTypeDTO businessTypeDTO = new BusinessTypeDTO(businessType.getDescriptionProperty(), businessType.getDescriptionAnnoucement());


        AgentDTO agentDTO = employeeToEmployeeDTO(announcement.getAgent());

        DateTimeDTO dateTimeDTO = DateTimeToDateformattedDTO(announcement.getDate());

      PropertyDTO propertyDTO = propertyToPropertyDTO(announcement.getProperty());

      AnnouncementDTO dto;

    if (announcement.getBusinessType().getDescriptionAnnoucement().equalsIgnoreCase("Rent")){

      dto = new AnnouncementDTO(agentDTO, dateTimeDTO, announcement.getCommission(),businessTypeDTO,propertyDTO, announcement.getRentDuration(), announcement.getListedPrice());

    } else{

        dto = new AnnouncementDTO(agentDTO, dateTimeDTO, announcement.getCommission(),businessTypeDTO,propertyDTO, announcement.getListedPrice());
    }




        return dto;
    }


    /**
     * To entity announcement.
     *
     * @param announcementDTO the announcement dto
     * @return the announcement
     */
    public static Announcement toEntity(AnnouncementDTO announcementDTO){

        BusinessTypeDTO businessTypeDTO = announcementDTO.getBusinessType();
        BusinessType businessType = new BusinessType(businessTypeDTO.getDescriptionProperty(), businessTypeDTO.getDescriptionAnnouncement());
        Agent agent = employeeDTOToEmployee(announcementDTO.getAgent());
        DateTime dateTime = DateTimeDTOToDateformatted(announcementDTO.getDate());
        Property property = convertPropertyDTOToProperty(announcementDTO.getProperty());
        Announcement entity;


        if (announcementDTO.getBusinessType().getDescriptionAnnouncement().equalsIgnoreCase("Rent")){

           entity = new Announcement(agent, dateTime, announcementDTO.getCommission(),businessType,property, announcementDTO.getRentDuration(), announcementDTO.getListedPrice());

        } else{

            entity = new Announcement(agent, dateTime, announcementDTO.getCommission(),businessType,property, announcementDTO.getListedPrice());
        }






        return entity;




    }


    private static Property convertPropertyDTOToProperty(PropertyDTO propertyDTO) {
        double price = propertyDTO.getPrice();
        double area = propertyDTO.getArea();
        Address address = addressDTOtoAddress(propertyDTO.getAddress());
        double distanceToCentre = propertyDTO.getDistanceToCentre();
        List<Photograph> photographs = PhotographToEntity(propertyDTO.getPhotographs());
        PropertyType propertyType = PropertyTypeToEntity(propertyDTO.getPropertyType());
        Store store = storeDTOToStore(propertyDTO.getStore());
        Client client = CliemtToEntaty(propertyDTO.getClient());
        DateTime date = DateTimeDTOToDateformatted(propertyDTO.getDate());



        Property property = new Property(price, area, address , distanceToCentre, photographs, propertyType,store, client, date);

        return property;
    }


    /**
     * Cliemt to entaty client.
     *
     * @param dto the dto
     * @return the client
     */
    public static Client CliemtToEntaty(ClientDTO dto) {

        Client entity = new Client(dto.getName(), dto.getEmail(), dto.getPassportNumber(), dto.getTaxNumber(), new Address(dto.getAddress().getStreetAddress(), dto.getAddress().getCity(), dto.getAddress().getState(), dto.getAddress().getZipCode()), dto.getPhoneNumber());

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
        List<RoleDTO> role=roleToRoleDTO(agent.getEmployee().getRole());
        StoreDTO store=storeToStoreDTO(agent.getEmployee().getStore());
        EmployeeDTO employeeDTO= new EmployeeDTO(email, name, passportNumber, phoneNumber,taxNumber,address,role,store);

        return new AgentDTO(employeeDTO);

    }


    /**
     * Property to property dto property dto.
     *
     * @param property the property
     * @return the property dto
     */
    public static PropertyDTO propertyToPropertyDTO(Property property){
         double price = property.getPrice();
         double area = property.getArea();
         AddressDTO address = addressToAddressDTO(property.getAddress());
         double distanceToCentre = property.getDistanceToCentre();
         List<PhotographDTO> photographs = photographToPhotographDTOList(property.getPhotographs());
         PropertyTypeDTO propertyType = new PropertyTypeDTO(property.getPropertyType().getDescription());
         StoreDTO store = storeToStoreDTO(property.getStore());
         ClientDTO client = ClientToClientDTO(property.getClient());

         DateTimeDTO date = DateTimeToDateformattedDTO(property.getDate());

       return new PropertyDTO(price, area, address, distanceToCentre, photographs, propertyType, store, client, date);


    }

    /**
     * Photograph to photograph dto list list.
     *
     * @param photographs the photographs
     * @return the list
     */
    public static List<PhotographDTO> photographToPhotographDTOList(List<Photograph> photographs) {
        List<PhotographDTO> photographDTOList = new ArrayList<>();

        for (Photograph photograph : photographs) {
            PhotographDTO photographDTO = new PhotographDTO(photograph.getUri());
            photographDTOList.add(photographDTO);
        }

        return photographDTOList;
    }


    /**
     * Client to client dto client dto.
     *
     * @param Client the client
     * @return the client dto
     */
    public static ClientDTO ClientToClientDTO (Client Client){
         String email = Client.getEmail();
         String name = Client.getName();
         Integer passportNumber = Client.getPassportNumber();
         long phoneNumber = Client.getPhoneNumber();
         String taxNumber = Client.getTaxNumber();
         AddressDTO address = addressToAddressDTO(Client.getAddress());

         return new ClientDTO(email,name,passportNumber,phoneNumber,taxNumber,address);


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
     * Business type to business type dto business type dto.
     *
     * @param bussinessTypeSub the bussiness type sub
     * @return the business type dto
     */
    public static BusinessTypeDTO businessTypeToBusinessTypeDTO(BusinessType bussinessTypeSub) {
        String descriptionAnnoucement= bussinessTypeSub.getDescriptionAnnoucement();
        String descriptionProperty=bussinessTypeSub.getDescriptionProperty();

        return new BusinessTypeDTO(descriptionProperty,descriptionAnnoucement);
    }
}
