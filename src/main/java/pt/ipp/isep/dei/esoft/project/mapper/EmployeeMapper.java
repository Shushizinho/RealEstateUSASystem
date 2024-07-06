package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Employee mapper.
 */
public class EmployeeMapper {

    /**
     * To entity employee.
     *
     * @param employeeDTO the employee dto
     * @return the employee
     */
    public static Employee toEntity (EmployeeDTO employeeDTO){
        String email= employeeDTO.getEmail();
        String name=employeeDTO.getName();
        Integer passportNumber= employeeDTO.getPassportNumber();
        long phoneNumber=employeeDTO.getPhoneNumber();
        String taxNumber=employeeDTO.getTaxNumber();
        Address address= addressDTOtoAddress(employeeDTO.getAddress());
        Store store= storeDTOToStore(employeeDTO.getStore());
        List<Role> role=roleDTOToRole(employeeDTO.getRole());

        return new  Employee(name, email,passportNumber,taxNumber,address,phoneNumber,store,role);
    }

    /**
     * To dto employee dto.
     *
     * @param employee the employee
     * @return the employee dto
     */
    public static EmployeeDTO toDTO (Employee employee){
        String email= employee.getEmail();
        String name= employee.getName();
        Integer passportNumber= employee.getPassportNumber();;
        long phoneNumber= employee.getPhoneNumber();
        String taxNumber= employee.getTaxNumber();
        AddressDTO address= addressToAddressDTO(employee.getAddress());
        List<RoleDTO> role=roleToRoleDTO(employee.getRole());
        StoreDTO store=storeToStoreDTO(employee.getStore());

        return new EmployeeDTO(email, name, passportNumber, phoneNumber,taxNumber,address,role,store);

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
     * Addressto address dto address dto.
     *
     * @param address the address
     * @return the address dto
     */
    public static AddressDTO addresstoAddressDTO(Address address) {
        String street = address.getStreetAddress();
        String city = address.getCity();
        String state = address.getState();
        String zipCode = address.getZipCode();

        AddressDTO addressDTO = new AddressDTO(street, city, state, zipCode);

        return addressDTO;
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



}
