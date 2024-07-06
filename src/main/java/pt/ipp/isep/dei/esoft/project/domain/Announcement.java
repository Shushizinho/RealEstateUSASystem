package pt.ipp.isep.dei.esoft.project.domain;


import java.io.Serializable;
import java.util.Objects;

/**
 * A class representing a real estate announcement.
 */

public class Announcement implements Serializable {


   private double listedPrice = 0.0;
   private Integer rentDuration = 0;

    private Agent agent;

    /**
     * The date of the announcement.
     */
    private DateTime date;

    /**
     * The commission for the real estate agent.
     */
    private double commission;


    /**
     * The business type of the property.
     */
    private BusinessType businessType;



    /**
     * The property being advertised.
     */
    private Property property;




/*public Announcement(String title, double price, DateTime date, double commission, PropertyType propertyType, BusinessType businessType, Property property, Employee agent){
    this.title=title;
    this.price=price;
    this.date=date;
    this.commission=commission;
    this.propertyType=propertyType;
    this.businessType=businessType;
    this.property= property;
    this.agent=agent;
}*/

    /**
     * Constructs a new Announcement object.
     *
     * @param agent        the employee responsible for the announcement
     * @param date         the formatted date of the announcement
     * @param commission   the commission for the announcement
     * @param businessType the business type of the announcement
     * @param property     the property associated with the announcement
     */
    public Announcement(Agent agent, DateTime date, double commission, BusinessType businessType, Property property ) {
        this.agent = agent;
        this.date = date;
        this.commission = commission;
        this.businessType = businessType;
        this.property = property;

    }


    public Announcement(Agent agent, DateTime date, double commission, BusinessType businessType, Property property, Integer rentDuration, double listedPrice) {
        this.agent = agent;
        this.date = date;
        this.commission = commission;
        this.businessType = businessType;
        this.property = property;
        this.rentDuration = rentDuration;
        this.listedPrice = listedPrice;
    }

    public Announcement(Agent agent, DateTime date, double commission, BusinessType businessType, Property property, double listedPrice) {
        this.agent = agent;
        this.date = date;
        this.commission = commission;
        this.businessType = businessType;
        this.property = property;
        this.listedPrice = listedPrice;

    }



  /*  public Announcement( AnnouncementRequest announcementRequest, double commission){
        this.listedPrice = announcementRequest.getListedPrice();
        this.rentDuration = announcementRequest.getRentDuration();

        this.businessType= announcementRequest.getBusinessTypeObject();
        this.agent = announcementRequest.getAgent();
        this.date = announcementRequest.getDate();
        this.commission = commission;
        this.property = announcementRequest.getProperty();


    }*/

    public Announcement ( AnnouncementRequest announcementRequest, double commission) {
        this.commission=commission;
        this.businessType = announcementRequest.getBusinessTypeObject();
        this.property = announcementRequest.getProperty();
        this.rentDuration = announcementRequest.getRentDuration();
        this.agent = announcementRequest.getAgent();
        this.date = new DateTime();
        this.listedPrice = announcementRequest.getListedPrice();

    }







    /* public Announcement(String title, long phoneNumber, DateTime date, double commission, BusinessType businessType, PropertyType propertyType, Property property) {
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.commission = commission;
        this.businessType = businessType;
        this.propertyType = propertyType;
        this.property = property;
    }*/

    public Announcement() {

    }


    public double getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(double listedPrice) {
        this.listedPrice = listedPrice;
    }

    public int getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(int rentDuration) {
        this.rentDuration = rentDuration;
    }

    public Property getProperty(){

        return property;
    }


    public BusinessType getBusinessType(){

        return businessType;

    }




    /**
     * Returns the date of the announcement.
     *
     * @return the date of the announcement
     */

    public DateTime getDate() {
        return date;
    }

    /**
     * Sets the date of the announcement.
     *
     * @param date the date of the announcement
     */

    public void setDate(DateTime date) {
        this.date = date;
    }

    /**
     * Returns the commission for the real estate agent.
     *
     * @return the commission for the real estate agent
     */
    public double getCommission() {
        return commission;
    }

    /**
     * Sets the commission for the real estate agent.
     *
     * @param commission the commission for the real estate agent
     */

    public void setCommission(double commission) {
        this.commission = commission;

    }


    /**
     * Returns the property object associated with this announcement.
     *
     * @return the property object associated with this announcement
     */


  /*  public Property getProperty() {
        return property;
    }
*/

    /**
     * Returns the business type of the property in the announcement.
     *
     * @return the business type of the property
     */

  /*  public BusinessType getBusinessType() {
        return businessType;
    }
*/

/*

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }





   /* public Employee getAgent() {
        return agent;
    }*/


    public Agent getAgent(){



        return agent;
    }

    /**
     * Creates and returns a new Announcement object that is a copy of this object.
     *
     * @return A new Announcement object that is a copy of this object
     */

    public Announcement clone() {
        return new Announcement(this.agent, this.date, this.commission, this.businessType, this.property, this.rentDuration, this.listedPrice);
    }


        /**
         * Checks if the given object is equal to this Announcement object
         * @param o the object to be compared with this Announcement object
         * @return true if the given object is equal to this Announcement object, false otherwise
         */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Announcement other = (Announcement) o;
        return Double.compare(other.commission, commission) == 0  &&
                Objects.equals(agent, other.agent) &&
                Objects.equals(date, other.date) &&
                Objects.equals(businessType, other.businessType) &&
                Objects.equals(property, other.property);
    }

        /**
         * Returns a hash code value for the Announcement object. The hash code is computed
         * based on the values of the agent, date, commission, businessType, property fields.
         *
         * @return the hash code value for this Announcement object
         */




    @Override
        public int hashCode () {
            return Objects.hash( agent, date, commission, businessType, property);
        }



    @Override
    public String toString() {
        String result = "Announcement{\n";

        if (businessType.getDescriptionAnnoucement().equals("Rent")) {
            result += String.format("rentDuration=%d, listedPrice=%f", rentDuration, listedPrice);
        } else if (businessType.getDescriptionAnnoucement().equals("Buy")) {
            result += String.format("listedPrice=%f", listedPrice);
        }

        result += String.format(", agent=%s, date=%s, commission=%f, businessType=%s, property=%s}",
                agent, date, commission, businessType, property);

        return result;
    }








}


