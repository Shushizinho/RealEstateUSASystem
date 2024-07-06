package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.dto.AddressDTO;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Store mapper.
 */
public class StoreMapper {

    /**
     * To dto store dto.
     *
     * @param store the store
     * @return the store dto
     */
    public static StoreDTO toDTO(Store store) {
        Address address = store.getAddress();
        AddressDTO addressDTO = AddressToAddressDTO(address);

        return new StoreDTO((int) store.getId(),store.getDesignation(),addressDTO,store.getEmail(),store.getPhoneNumber());
    }

    /**
     * To dto list.
     *
     * @param lStore the l store
     * @return the list
     */
    public static List<StoreDTO> toDTO(List<Store> lStore) {
        List<StoreDTO> lStoreDTO = new ArrayList<>();

        for (Store store : lStore){

        Address address = store.getAddress();
        AddressDTO addressDTO = AddressToAddressDTO(address);
        lStoreDTO.add(new StoreDTO((int) store.getId(),store.getDesignation(),addressDTO,store.getEmail(),store.getPhoneNumber()));

        }

        return lStoreDTO;
    }

    /**
     * To entity store.
     *
     * @param storeDTO the store dto
     * @return the store
     */
    public static Store toEntity(StoreDTO storeDTO) {
        AddressDTO addressDTO = storeDTO.getAddress();

        Address address = AdressDTOtoAdress(addressDTO);

        return new Store((int) storeDTO.getId(),storeDTO.getDesignation(),storeDTO.getEmail(),address,storeDTO.getPhoneNumber());
    }

    /**
     * To entity list.
     *
     * @param lStoreDTO the l store dto
     * @return the list
     */
    public static List<Store> toEntity(List<StoreDTO> lStoreDTO) {
        List<Store> lStore = new ArrayList<>();

        for (StoreDTO storeDTO : lStoreDTO){

            AddressDTO addressDTO = storeDTO.getAddress();

            Address address = AdressDTOtoAdress(addressDTO);

            lStore.add(new Store((int) storeDTO.getId(),storeDTO.getDesignation(),storeDTO.getEmail(),address,storeDTO.getPhoneNumber()));

        }

        return lStore;
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
     * Get stores dto list.
     *
     * @param stores the stores
     * @return the list
     */
    public static List<StoreDTO> getStoresDTO(List<Store> stores){

        List<StoreDTO> storeDTOs = new ArrayList<>();
        for (Store store : stores) {
            StoreDTO storeDTO = StoreMapper.toDTO(store);
            storeDTOs.add(storeDTO);
        }
        return storeDTOs;
    }

}
