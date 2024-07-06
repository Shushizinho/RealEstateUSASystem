package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;
import java.util.Objects;


/**
 * A class representing a sale announcement for a property.
 */

public class SaleAnnouncement implements Serializable {

    private double listedPrice;
    private Announcement announcement;



    public SaleAnnouncement(Agent agent, DateTime date, double commission, BusinessType businessType, Property property, double listedPrice) {
        this.announcement = new Announcement(agent,date,commission,businessType,property);
        this.listedPrice = listedPrice;
    }

    public SaleAnnouncement(Announcement announcement, double listedPrice) {
        this.announcement = announcement;
        this.listedPrice = listedPrice;
    }



    /**
     * Returns the price at which the property is being listed for sale.
     *
     * @return the listed price
     */

    public double getListedPrice() {
        return listedPrice;
    }

    /**
     * Sets the price at which the property is being listed for sale.
     *
     * @param listedPrice the new listed price
     */


    public void setListedPrice(double listedPrice) {
        this.listedPrice = listedPrice;
    }

    /**
     * Checks if this SaleAnnouncement object is equal to the specified object.
     * Two SaleAnnouncement objects are considered equal if they have the same listed price and other Announcement fields.
     *
     * @param obj the object to compare
     * @return true if this object is equal to the specified object, false otherwise
     */


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SaleAnnouncement)) {
            return false;
        }
        SaleAnnouncement other = (SaleAnnouncement) obj;
        return Double.compare(other.listedPrice, listedPrice) == 0 && super.equals(obj);
    }

    /**
     * Returns the hash code of this SaleAnnouncement object.
     *
     * @return the hash code
     */


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listedPrice);
    }

    /**
     * Returns a clone of this SaleAnnouncement object.
     *
     * @return a clone of this object
     */

    public Announcement getAnnouncement() {
        return announcement;
    }
    public SaleAnnouncement clone() {
        return new SaleAnnouncement(this.getAnnouncement(),this.listedPrice);
    }




    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AnnouncementRequestDTO {\n");
        sb.append("  businessType: ").append(getAnnouncement().getBusinessType()).append("\n");
        sb.append("  property: ").append(getAnnouncement().getProperty()).append("\n");
        sb.append("  agent: ").append(getAnnouncement().getAgent()).append("\n");
        sb.append("  client: ").append(getAnnouncement().getProperty().getClient()).append("\n");
        sb.append("  date: ").append(getAnnouncement().getDate()).append("\n");
        sb.append("}");

        return sb.toString();
    }


}
