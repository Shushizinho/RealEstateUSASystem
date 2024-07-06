package pt.ipp.isep.dei.esoft.project.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * The type Purchase order dto.
 */
public class PurchaseOrderDTO implements Serializable {

    private final double orderAmount;
    private final AnnouncementDTO announcement;
    private final ClientDTO client;
    private int wasAccepted;
    private DateTimeDTO creationDate;

    /**
     * Instantiates a new Purchase order dto.
     *
     * @param orderAmount  the order amount
     * @param announcement the announcement
     * @param client       the client
     */
    public PurchaseOrderDTO(double orderAmount, AnnouncementDTO announcement, ClientDTO client) {
        this.orderAmount = orderAmount;
        this.announcement = announcement;
        this.client = client;
        this.wasAccepted = 0;
        this.creationDate = new DateTimeDTO();

    }

    /**
     * Instantiates a new Purchase order dto.
     *
     * @param orderAmount  the order amount
     * @param announcement the announcement
     * @param client       the client
     * @param wasAccepted  the was accepted
     * @param creationDate the creation date
     */
    public PurchaseOrderDTO(double orderAmount, AnnouncementDTO announcement, ClientDTO client, int wasAccepted, DateTimeDTO creationDate) {
        this.orderAmount = orderAmount;
        this.announcement = announcement;
        this.client = client;
        this.wasAccepted = wasAccepted;
        this.creationDate = creationDate;
    }

    /**
     * Accept order.
     */
//    public PropertyType(){
//
//    }
//    wasAccepted values - 1 for Accepted, 0 for Stand By, -1 for Rejected
    public void acceptOrder(){
        this.wasAccepted = 1;
    }

    /**
     * Decline order.
     */
    public void declineOrder(){
        this.wasAccepted = -1;
    }

    /**
     * Reject order.
     */
    public void rejectOrder(){
        this.wasAccepted = -1;
    }

    /**
     * Gets order amount.
     *
     * @return the order amount
     */
    public double getOrderAmount() {
        return orderAmount;
    }

    /**
     * Accepted int.
     *
     * @return the int
     */
    public int accepted(){
        return wasAccepted;
    }

    /**
     * Gets client dto.
     *
     * @return the client dto
     */
    public ClientDTO getClientDTO() {
        return client;
    }

    /**
     * Checks if the given object is equal to this PropertyType object
     * @param o the object to be compared with this PropertyType object
     * @return true if the given object is equal to this PropertyType object, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderDTO)) {
            return false;
        }
        PurchaseOrderDTO that = (PurchaseOrderDTO) o;
        return (orderAmount == that.orderAmount && announcement.equals(that.announcement) && client.equals(that.client));
    }

    /**
     * Returns a hash code value for the PropertyType object.
     * The hash code is based on the value of the description field.
     *
     * @return the hash code value for this PropertyType object
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderAmount,announcement,client);
    }

    /**
     * Gets announcement dto.
     *
     * @return the announcement dto
     */
    public AnnouncementDTO getAnnouncementDTO() {
        return announcement;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current Property Type.
     */
    public PurchaseOrderDTO clone() {
        return new PurchaseOrderDTO(this.orderAmount, this.announcement, this.client, this.wasAccepted, this.creationDate);
    }

    @Override
    public String toString() {
        return String.format("\nOrder of $%.2f by %s (%s) made to %s", this.orderAmount,this.client.getName(), this.client.getEmail(), this.announcement.getProperty().getAddress());
    }
}
