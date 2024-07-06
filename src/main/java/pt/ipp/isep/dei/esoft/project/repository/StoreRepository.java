package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.domain.StoreNameComparator;
import pt.ipp.isep.dei.esoft.project.dto.AddressDTO;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;
import pt.ipp.isep.dei.esoft.project.mapper.StoreMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A repository class for managing all Stores.
 */
public class StoreRepository {

    private final List<Store> stores = new ArrayList<>();


    /**
     * Returns a defensive (immutable) copy of the list of Stores.
     *
     * @return a read-only list of all stores in the system.
     */
    public List<Store> getStores(){
        return List.copyOf(stores);

    }

    /**
     * Returns a defensive (immutable) copy of the list of Stores.
     *
     * @return a read-only list of all stores in the system.
     */
    public List<Store> getStoresOrderByProperty(){

        StoreNameComparator storeComparator = new StoreNameComparator();

        List<Store> storeListSorted = new ArrayList<>(List.copyOf(stores));
        storeListSorted.sort(storeComparator);



        return storeListSorted;

    }


    /**
     * Returns a defensive (immutable) copy of the list of Stores with Agents.
     *
     * @return a read-only list of all stores in the system with agents.
     */
    public List<Store> getStoresWithAgents(){
        List<Store> storesWithAgents = new ArrayList<>();
        for ( Store store: stores ) {
            if ( store.hasAgents() ) storesWithAgents.add(store);
        }
        return List.copyOf(storesWithAgents);

    }

    /**
     * Get stores with agents dto list.
     *
     * @return the list
     */
    public List<StoreDTO> getStoresWithAgentsDTO(){
        List<Store> storesWithAgents = new ArrayList<>();
        for ( Store store: stores ) {
            if ( store.hasAgents() ) storesWithAgents.add(store);
        }
        return List.copyOf(StoreMapper.toDTO(storesWithAgents));

    }

    /**
     * Gets store i ds list.
     *
     * @param selectedStoresList the selected stores list
     * @return the store i ds list
     */
    public List<Integer> getStoreIDsList(List<StoreDTO> selectedStoresList) {
        List<Integer> storeIDsList = new ArrayList<>();
        for (StoreDTO store: selectedStoresList) {
            storeIDsList.add(store.getId());
        }
        return storeIDsList;
    }

    /**
     * Gets property number in each.
     *
     * @param selectedStoresList the selected stores list
     * @return the property number in each
     */
    public List<Integer> getPropertyNumberInEach(List<StoreDTO> selectedStoresList) {
        List<Integer> numberPropertiesList = new ArrayList<>();
        for (StoreDTO store:selectedStoresList) {
            numberPropertiesList.add(getNumberPropertiesFromStore(StoreMapper.toEntity(store)));
        }
        return numberPropertiesList;
    }

    /**
     * Get number properties from store integer.
     *
     * @param store the store
     * @return the integer
     */
    public Integer getNumberPropertiesFromStore(Store store){
        return Repositories.getInstance().getAnnouncementRepository().getAnnouncementsByStore(store).size();
    }

    /**
     * Retrieves the store with the given description from the list of stores.
     *
     * @param storeDescription the description of the store to retrieve
     * @return the store with the given description
     * @throws IllegalArgumentException if the store with the given description does not exist
     */
    public Store getStoreByDescription(String storeDescription) {
        if (storeDescription == null) return null;
        Store store = null;
        for (Store storeCheck : List.copyOf(stores)) {
            if (storeCheck.compareDescription(storeDescription) == 0) {
                store = storeCheck;
            }
        }
        if (store == null) {
            throw new IllegalArgumentException(
                    "Store requested for [" + storeDescription + "] does not exist.");
        }
        return store;
    }

    /**
     * Adds a new store to the collection of stores managed by this repository.
     *
     * @param store the store to be added to the collection.
     * @return an Optional containing the added store, or an empty Optional if the operation fails
     */
    public Optional<Store> add(Store store) {

        Optional<Store> newStore = Optional.empty();
        boolean operationSuccess = false;

        if (validateStore(store)) {
            newStore = Optional.of(store.clone());
            operationSuccess = stores.add(newStore.get());
        }

        if (!operationSuccess) {
            newStore = Optional.empty();
        }

        return newStore;
    }
    /**
     * Validates if a given store is valid to be added to the repository.
     *
     * @param store the store to be validated
     * @return true if the store is valid for addition, false otherwise
     */
    private boolean validateStore(Store store) {
        boolean isValid = !stores.contains(store);
        return isValid;
    }

    /**
     * Removes the specified store from the list of stores.
     *
     * @param str The store to be removed.
     */
    public void clear(Store str) {
        stores.remove(str);
    }

    /**
     * Retrieves the store with the specified store ID.
     *
     * @param storeId The ID of the store to retrieve.
     * @return The store with the specified ID.
     * @throws IllegalArgumentException If the store with the specified ID does not exist.
     */
    public Store getStoreByID(int storeId) {

//        if (storeId == null) return null;

        Store store = null;
        for (Store storeCheck : List.copyOf(stores)) {
            if (storeCheck.getId() == storeId) {
                store = storeCheck;
            }
        }
        if (store == null) {
            throw new IllegalArgumentException(
                    "Store requested for [" + storeId + "] does not exist.");
        }
        return store;

    }


    /**
     * Create store optional.
     *
     * @param id          the id
     * @param designation the designation
     * @param email       the email
     * @param address     the address
     * @param phoneNumber the phone number
     * @return the optional
     */
    public Optional<Store> createStore(Integer id, String designation, String email, Address address, long phoneNumber) {

        Store StoreClone = new Store(id,designation,email,address,phoneNumber);

        Optional<Store> newStore = Optional.of(StoreClone.clone());

        stores.add(StoreClone);

//        storeManager.getRole().add(rolesRepository.getRoleByDescription(AuthenticationController.ROLE_STORE_MANAGER));
        //AuthenticationController.ROLE_STORE_MANAGER);


        return newStore;

    }

    /**
     * Create store optional.
     *
     * @param storeDTO the store dto
     * @return the optional
     */
    public Optional<Store> createStore(StoreDTO storeDTO) {

        Store StoreClone = StoreMapper.toEntity(storeDTO);

        Optional<Store> newStore = Optional.of(StoreClone.clone());

        stores.add(StoreClone);

//        storeManager.getRole().add(rolesRepository.getRoleByDescription(AuthenticationController.ROLE_STORE_MANAGER));
        //AuthenticationController.ROLE_STORE_MANAGER);


        return newStore;

    }

    /**
     * Gets store.
     *
     * @return the store
     */
    public List<Store> getStore() {
        try {
            if (stores.isEmpty()){
                throw new IllegalArgumentException("There are no stores available.");
            }
        } catch (IllegalStateException e) {
            System.out.println (e.getMessage());
        }

        List<Store> stores1 = new ArrayList<>();
            try {
                for (Store store :stores) {
                    stores1.add(store);
                }
            }catch (Exception e) {
                throw new IllegalStateException("Error processing store: " + e.getMessage());
            }
        return stores1;
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public Store getLast() {
        return stores.get(stores.size()-1);
    }
}
