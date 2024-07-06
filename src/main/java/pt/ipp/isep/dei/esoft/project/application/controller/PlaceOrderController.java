package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.PairDTO;
import pt.ipp.isep.dei.esoft.project.dto.PropertyDTO;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The PlaceOrderController class is responsible for managing the process of placing orders for properties.
 * It handles interactions between the user interface and the domain.
 */
public class PlaceOrderController {
    private PropertyRepository propertyRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private PurchaseOrderRepository purchaseOrderRepository = null;
    private StoreRepository storeRepository = null;
    private PropertyFiltersRepository propertyFiltersRepository = null;
    private ClientRepository clientRepository = null;
    private AuthenticationRepository authenticationRepository = null;
    private List<Filters> filters = null;
    private UserSession currentSession = null;

    /**
     * Constructs a new PlaceOrderController object.
     * Initializes the required repositories for the controller.
     */
    public PlaceOrderController() {
        getAnnouncementRepository();
        getStoreRepository();
        getPropertyFiltersRepository();
        getPurchaseOrderRepository();
        getPropertyRepository();
    }

    /**
     * Retrieves the list of stores.
     *
     * @return The list of stores.
     */
    public List<Store> getStores() {
        return storeRepository.getStores();
    }

    /**
     * Retrieves the price ranges.
     *
     * @return A Pair os integers representing the floor and roof of the price range.
     */
    public List<PairDTO<Integer, Integer>> getPriceRanges() {
        return propertyFiltersRepository.getPriceRanges();
    }

    /**
     * Retrieves the PurchaseOrderRepository instance. If it is not already initialized,
     * it initializes it by obtaining it from the Repositories singleton.
     *
     * @return The PurchaseOrderRepository instance.
     */

    private PurchaseOrderRepository getPurchaseOrderRepository() {
        if (purchaseOrderRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the StoreRepository
            purchaseOrderRepository = repositories.getPurchaseOrderRepository();
        }
        return purchaseOrderRepository;
    }

    /**
     * Retrieves the StoreRepository instance. If it is not already initialized,
     * it initializes it by obtaining it from the Repositories singleton.
     *
     * @return The StoreRepository instance.
     */
    private StoreRepository getStoreRepository() {
        if (storeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the StoreRepository
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    /**
     * Retrieves the AnnouncementRepository instance. If it is not already initialized,
     * it initializes it by obtaining it from the Repositories singleton.
     *
     * @return The AnnouncementRepository instance.
     */
    AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the StoreRepository
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }
    /**
     * Retrieves the PropertyFiltersRepository instance. If it is not already initialized,
     * it initializes it by obtaining it from the Repositories singleton.
     *
     * @return The PropertyFiltersRepository instance.
     */

    private PropertyFiltersRepository getPropertyFiltersRepository() {
        if (propertyFiltersRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the StoreRepository
            propertyFiltersRepository = repositories.getPropertyFiltersRepository();
        }
        return propertyFiltersRepository;
    }

    /**
     * Prints the price range as a string.
     *
     * @param priceRange The price range as a Pair of integers.
     * @return The string representation of the price range.
     */
    public String printPriceRange(PairDTO<Integer,Integer> priceRange) {
        if (priceRange.getRight() < Integer.MAX_VALUE) return priceRange.toStringDTO();
        else return priceRange.getLeft() + "+";
    }

    /**
     * Retrieves the PropertyRepository instance. If it is not already initialized,
     * it initializes it by obtaining it from the Repositories singleton.
     *
     * @return The PropertyRepository instance.
     */
    PropertyRepository getPropertyRepository() {
        if (propertyRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the StoreRepository
            propertyRepository = repositories.getPropertyRepository();
        }
        return propertyRepository;
    }

    /**
     * Retrieves a list of stores that have associated agents.
     *
     * @return A list of stores with agents.
     */
    public List<StoreDTO> getStoresWithAgents() {
        StoreRepository storeRepository = getStoreRepository();
        return storeRepository.getStoresWithAgentsDTO();
    }

    /**
     * Retrieves a list of available properties based on the provided store and price range.
     *
     * @param store      The store to filter the properties.
     * @param priceRange The range of prices to filter the properties.
     * @return A list of available properties that match the store and price range.
     */
    public List<PropertyDTO> getAvailableProperties(Store store, PairDTO<Integer, Integer> priceRange){
        List<Announcement> announcementList = new ArrayList<>();
        List<PropertyDTO> propertyList = new ArrayList<>();
        announcementList = announcementRepository.getSaleAnnouncements();
//        propertyRepository = getPropertyRepository();
        announcementList = announcementRepository.filterSaleAnnouncements(announcementList, store, priceRange);
        propertyList = propertyRepository.getPropertiesBySaleAnnoucementList(announcementList);
        return propertyList;
    }

    /**
     * Has active order boolean.
     *
     * @param propertyDescription the property description
     * @return the boolean
     */
    public boolean hasActiveOrder(String propertyDescription) {
        Property property = propertyRepository.getPropertyByDescription(propertyDescription);
        Announcement announcement = announcementRepository.getAnnouncementByProperty(property);
        if (currentSession == null) {
            ApplicationSession appSession = ApplicationSession.getInstance();

            //Get the PropertyTypeRepository
            currentSession = appSession.getCurrentSession();
        }
        String email = currentSession.getUserEmail();
        ClientRepository clientRepository = getClientRepository();
        Client client = clientRepository.getClientByEmail(email);
        List<PurchaseOrder> orders = purchaseOrderRepository.getOrdersByAnnouncement(announcement);

        if (orders.size() > 0) {
            for (PurchaseOrder offToCompare : orders) {
                if (offToCompare.getClient().equals(client) && offToCompare.accepted() != -1) {
                    System.out.println("\u001B[31mYou already have an active order.\u001B[0m");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Places a purchase order with the specified order amount for specific property.
     * It retrieves the property, announcement, and client based on the provided information.
     *
     * @param orderAmount         The amount of the purchase order.
     * @param propertyDescription The description of the property.
     * @return The created PurchaseOrder object.
     */
    public PurchaseOrder placeOrder(Double orderAmount, String propertyDescription) {
        System.out.println(propertyDescription);
        Property property = propertyRepository.getPropertyByDescription(propertyDescription);
        Announcement announcement = announcementRepository.getAnnouncementByProperty(property);
        if (currentSession == null) {
            ApplicationSession appSession = ApplicationSession.getInstance();

            //Get the PropertyTypeRepository
            currentSession = appSession.getCurrentSession();
        }
        String email = currentSession.getUserEmail();
        ClientRepository clientRepository = getClientRepository();
        Client client = clientRepository.getClientByEmail(email);

        return purchaseOrderRepository.placeOrder(orderAmount, announcement, client);
    }
    /**
     * Retrieves the ClientRepository instance. If it is not already initialized,
     * it initializes it by obtaining it from the Repositories singleton.
     *
     * @return The ClientRepository instance.
     */
    private ClientRepository getClientRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the ClientRepository
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }


    /**
     * Returns the AuthenticationRepository instance for this system.
     * If it hasn't been initialized yet, the method initializes it by retrieving the repository from the global Repositories instance.
     * @return the AuthenticationRepository instance for this system.
     */

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }
}
