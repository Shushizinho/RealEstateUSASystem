package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;
import java.util.Objects;

/**
 * A class representing an announcement for a rental property.
 */

public class RentAnnouncement implements Serializable {

    /**
     * The duration of the rent
     */
    private Integer rentDuration;
    private Announcement announcement;


    public RentAnnouncement(Agent agent, DateTime date, double commission, BusinessType businessType, Property property, Integer rentDuration) {
        this.announcement = new Announcement(agent, date, commission, businessType,  property);
        this.rentDuration = rentDuration;
    }

    public RentAnnouncement(Announcement announcement, Integer rentDuration) {
        this.announcement = announcement;
        this.rentDuration = rentDuration;
    }

    /**
     * Creates a default RentAnnouncement object.
     */

    public RentAnnouncement() {

    }

    /**
     * Returns the duration of the rent.
     *
     * @return The duration of the rent
     */
    public Integer getRentDuration() {
        return rentDuration;
    }


    /**
     * Sets the duration of the rent.
     *
     * @param rentDuration The duration of the rent to set
     */
    public void setRentDuration(Integer rentDuration) {
        this.rentDuration = rentDuration;
    }

    /**
     * Compares this RentAnnouncement object with another object for equality.
     * Two RentAnnouncement objects are considered equal if they have the same rent duration and all their properties from the superclass are equal.
     *
     * @param obj The object to compare with
     * @return true if this RentAnnouncement is equal to the specified object, false otherwise
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RentAnnouncement)) return false;
        if (!super.equals(obj)) return false;
        RentAnnouncement that = (RentAnnouncement) obj;
        return Objects.equals(rentDuration, that.rentDuration);


    }

    /**
     * Returns a hash code value for this RentAnnouncement object.
     *
     * @return A hash code value for this object
     */

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rentDuration);
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    /**
     * Creates and returns a new RentAnnouncement object that is a copy of this object.
     *
     * @return A new RentAnnouncement object that is a copy of this object
     */


    public RentAnnouncement clone() {
        return new RentAnnouncement(this.getAnnouncement(), this.rentDuration);


    }

}
