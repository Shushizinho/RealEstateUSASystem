package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Client mapper.
 */
public class ClientMapper {
    /**
     * To dto client dto.
     *
     * @param client the client
     * @return the client dto
     */
    public static ClientDTO toDTO(Client client) {
        Address address = client.getAddress();
        AddressDTO addressDTO = AddressToAddressDTO(address);

        return new ClientDTO(client.getName(),client.getEmail(),client.getPassportNumber(),client.getPhoneNumber(),client.getTaxNumber(),addressDTO);
    }

    /**
     * To entity client.
     *
     * @param clientDTO the client dto
     * @return the client
     */
    public static Client toEntity(ClientDTO clientDTO) {
        AddressDTO addressDTO = clientDTO.getAddress();

        Address address = AdressDTOtoAdress(addressDTO);

        return new Client(clientDTO.getName(),clientDTO.getEmail(),clientDTO.getPassportNumber(),clientDTO.getTaxNumber(),address,clientDTO.getPhoneNumber());
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
     * Get clients dto list.
     *
     * @param clients the clients
     * @return the list
     */
    public static List<ClientDTO> getClientsDTO(List<Client> clients){

        List<ClientDTO> clientDTOs = new ArrayList<>();
        for (Client client : clients) {
            ClientDTO clientDTO = ClientMapper.toDTO(client);
            clientDTOs.add(clientDTO);
        }
        return clientDTOs;
    }

}
