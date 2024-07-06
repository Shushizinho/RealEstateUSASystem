package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrder implements Serializable {

    private final double orderAmount;
    private final Announcement announcement;
    private final Client client;
    private int wasAccepted;
    private DateTime creationDate;

    public PurchaseOrder(double orderAmount, Announcement announcement, Client client) {
        this.orderAmount = orderAmount;
        this.announcement = announcement;
        this.client = client;
        this.wasAccepted = 0;
        this.creationDate = new DateTime();
    }

    public PurchaseOrder(double orderAmount, Announcement announcement, Client client, int wasAccepted, DateTime creationDate) {
        this.orderAmount = orderAmount;
        this.announcement = announcement;
        this.client = client;
        this.wasAccepted = wasAccepted;
        this.creationDate = creationDate;
    }

//    public PropertyType(){
//
//    }
//    wasAccepted values - 1 for Accepted, 0 for Stand By, -1 for Rejected
    public void acceptOrder(){
        this.wasAccepted = 1;
    }
    public void declineOrder(){
        this.wasAccepted = -1;
    }
    public void rejectOrder(){
        this.wasAccepted = -1;
    }
    public double getOrderAmount() {
        return orderAmount;
    }
    public int accepted(){
        return wasAccepted;
    }

    public Client getClient() {
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
        if (!(o instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder that = (PurchaseOrder) o;
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

    public Announcement getAnnouncement() {
        return announcement;
    }

    public int getWasAccepted() { return wasAccepted; }

    /**
     * Clone method.
     *
     * @return A clone of the current Property Type.
     */
    public PurchaseOrder clone() {
        return new PurchaseOrder(this.orderAmount, this.announcement, this.client, this.wasAccepted, this.creationDate);
    }

    @Override
    public String toString() {
        return String.format("\nOrder of $%.2f by %s (%s) made to %s", this.orderAmount,this.client.getName(), this.client.getEmail(), this.announcement.getProperty().getAddress());
    }
}
