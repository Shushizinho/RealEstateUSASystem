package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Collections;
import java.util.List;

/**
 * The type Purchase orders controller.
 */
public class PurchaseOrdersController {

    private PropertyRepository propertyRepository = null;
    private AnnouncementRepository announcementRepository = null;
    private PurchaseOrderRepository purchaseOrderRepository = null;
    private AuthenticationRepository authenticationRepository = null;

    private PurchaseOrder purchaseOrder = null;

    /**
     * The Address.
     */
    Address address= new Address("test","test","teste","teste");
    /**
     * The Employee.
     */
    Employee employee = new Employee("Diogo", "agent@this.app", 12321, "3213123", address ,121212, new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000), List.of(new Role(2, AuthenticationController.ROLE_AGENT)));
    /**
     * The Agent.
     */
//Agent agent = employee.getAssociatedAgent();
    Agent agent = new Agent(employee);


    /**
     * Instantiates a new Purchase orders controller.
     */
    public PurchaseOrdersController() {
        getAnnouncementRepository();
        getPurchaseOrderRepository();
        getAuthenticationRepository();
    }
    /**
     * Retrieves the AnnouncementRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The AnnouncementRepository instance.
     */
    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository == null) {
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }
    /**
     * Retrieves the PurchaseOrderRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The PurchaseOrderRepository instance.
     */
    private PurchaseOrderRepository getPurchaseOrderRepository() {
        if (purchaseOrderRepository == null) {
            Repositories repositories = Repositories.getInstance();
            purchaseOrderRepository = repositories.getPurchaseOrderRepository();
        }
        return purchaseOrderRepository;
    }
    /**
     * Retrieves the AuthenticationRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The AuthenticationRepository instance.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Retrieves the list of SaleAnnouncements from the AnnouncementRepository.
     *
     * @return The list of SaleAnnouncements.
     */
    public List<Announcement> getSaleAnnouncemets() {
        AnnouncementRepository announcementRepository = getAnnouncementRepository();
        return announcementRepository.getSaleAnnouncements();
    }

    /**
     * Removes the specified SaleAnnouncement from the AnnouncementRepository.
     *
     * @param saleAnnouncement The SaleAnnouncement to be removed.
     * @return True if the SaleAnnouncement was successfully removed, false otherwise.
     */
    public boolean removeSaleAnnouncement(SaleAnnouncement saleAnnouncement) {
        AnnouncementRepository announcementRepository = getAnnouncementRepository();
        return announcementRepository.removeSaleAnnouncement(saleAnnouncement);
    }

    /**
     * Retrieves a list of PurchaseOrders associated with the specified SaleAnnouncement.
     *
     * @param saleAnnouncement The SaleAnnouncement for which to retrieve the PurchaseOrders.
     * @return A list of PurchaseOrders associated with the SaleAnnouncement.
     */
    public List<PurchaseOrder> getPurchas(Announcement saleAnnouncement) {
        PurchaseOrderRepository purchaseOrderRepository = getPurchaseOrderRepository();
        return purchaseOrderRepository.getOrdersByAnnouncement(saleAnnouncement);
    }

    /**
     * Retrieves the index of the specified SaleAnnouncement in the PurchaseOrderRepository.
     *
     * @param saleAnnouncement The SaleAnnouncement for which to retrieve the index.
     * @return The index of the SaleAnnouncement in the PurchaseOrderRepository, or -1 if not found.
     */
    public List<PurchaseOrder> getOrdersByAnnouncement(Announcement saleAnnouncement) {
        return purchaseOrderRepository.getOrderByAnnouncement(saleAnnouncement);
    }

    /**
     * Creates and sends an email using the specified details.
     *
     * @param sender   The email address of the sender.
     * @param receiver The email address of the recipient.
     * @param subject  The subject of the email.
     * @param message  The content of the email.
     */
    public void createEmail(String sender, String receiver, String subject, String message){
        getPurchaseOrderRepository().createEmail(sender, receiver, subject, message);
    }
//    AuthenticationRepository authentication = Repositories.getInstance().getAuthenticationRepository();
//    String CurrentUser = String.valueOf(getAuthenticationRepository().getCurrentUserSession().getUserId());

    /**
     * Set accepted purchase order.
     *
     * @param purchaseOrder the purchase order
     */
    public void setAcceptedPurchaseOrder(PurchaseOrder purchaseOrder){
        purchaseOrder.acceptOrder();
    }

    /**
     * Set decline purchase order.
     *
     * @param purchaseOrder the purchase order
     */
    public void setDeclinePurchaseOrder(PurchaseOrder purchaseOrder){
        purchaseOrder.declineOrder();
    }

    /**
     * Gets list oldest to newest.
     *
     * @param announcements          the announcements
     * @param announcementComparator the announcement comparator
     */
    public void getListOldestToNewest(List<Announcement> announcements, AnnouncementComparator announcementComparator) {
        Collections.sort(announcements, announcementComparator);
    }

    /**
     * Gets list highest to lowest.
     *
     * @param orders                  the orders
     * @param purchaseOrderComparator the purchase order comparator
     */
    public void getListHighestToLowest(List<PurchaseOrder> orders, PurchaseOrderComparator purchaseOrderComparator) {
        Collections.sort(orders, purchaseOrderComparator);
    }
}
