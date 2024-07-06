package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * The type Property.
 */
/* Represents a general Property.
 */
public class Property implements Serializable {
    private double price;
    private double area;
    private Address address;
    private double distanceToCentre;
    private List<Photograph> photographs;
    private PropertyType propertyType;
    private Store store;
    private DateTime date;

    private Client client;

//    private Agent agent;
//    private Client client;


    /**
     * Instantiates a new Property.
     *
     * @param price            the price
     * @param area             the area
     * @param address          the address
     * @param distanceToCentre the distance to centre
     * @param photographs      the photographs
     * @param propertyType     the property type
     * @param store            the store
     * @param client           the client
     * @param date             the date
     */
    public Property (double price, double area, Address address, double distanceToCentre, List<Photograph> photographs,
                     PropertyType propertyType, Store store, Client client, DateTime date) {
        this.price = price;
        this.area = area;
        this.address = address;
        this.distanceToCentre = distanceToCentre;
        this.photographs = photographs;
        this.propertyType = propertyType;
        this.store = store;
        this.client = client;
        this.date = date;
//        this.agent = agent;
//        this.client = client;
    }

    /**
     * Instantiates a new Property.
     *
     * @param price            the price
     * @param area             the area
     * @param address          the address
     * @param distanceToCentre the distance to centre
     * @param photographs      the photographs
     * @param propertyType     the property type
     * @param client           the client
     * @param date             the date
     */
    public Property(float price, float area, Address address, float distanceToCentre, List<Photograph> photographs,
                    PropertyType propertyType, Client client, DateTime date) {
        this.price = price;
        this.area = area;
        this.address = address;
        this.distanceToCentre = distanceToCentre;
        this.photographs = photographs;
        this.propertyType = propertyType;
        this.client = client;
        this.date = date;
    }


    /**
     * Instantiates a new Property.
     *
     * @param price            the price
     * @param area             the area
     * @param address          the address
     * @param distanceToCentre the distance to centre
     * @param photographs      the photographs
     * @param propertyType     the property type
     * @param store            the store
     * @param date             the date
     * @param client           the client
     */
    public Property(double price, double area, Address address, double distanceToCentre, List<Photograph> photographs, PropertyType propertyType, Store store, DateTime date, Client client) {
        this.price = price;
        this.area = area;
        this.address = address;
        this.distanceToCentre = distanceToCentre;
        this.photographs = photographs;
        this.propertyType = propertyType;
        this.store = store;
        this.date = date;
        this.client = client;
    }

    /**
     * This method returns the State property of the Address associated with the current Property.
     *
     * @return the Property's Address's State.
     */
    public String getState(){
        return this.address.getState();
    }

    /**
     * This method returns the City property of the Address associated with the current Property.
     *
     * @return the Property's Address's City.
     */
    public String getCity(){
        return this.address.getCity();
    }

    /**
     * This method returns the date property of the created date with the current Property.
     *
     * @return the Property's date.
     */
    public DateTime getDate(){
         return date;
     }




     /**
    /**
     * This method returns the Price of the current Property.
     *
     * @return      the Property's Price.
     */

    public double getPrice() {
        return price;
    }

    /**
     * This method returns the Area of the current Property.
     *
     * @return      the Property's Area.
     */

    public double getArea() {
        return area;
    }

    /**
     * This method returns the complete Address of the current Property.
     *
     * @return      the Property's Address.
     */

    public Address getAddress() {
        return address;
    }

    /**
     * This method returns the distance of the current Property to the City Centre.
     *
     * @return      the Property's Distance to the City Centre.
     */

    public double getDistanceToCentre() {
        return distanceToCentre;
    }

    /**
     * This method returns the list of Photographs associated with the current Property.
     *
     * @return      the Property's List of Photographs.
     */

    public List<Photograph> getPhotographs() {
        return photographs;
    }

    /**
     * This method returns the type of the current Property.
     *
     * @return      the Property's Property Type.
     */

    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * This method returns the Store in which the current Property is listed.
     *
     * @return      the Property's Store.
     */

    public Store getStore() {
        return store;
    }

    /**
     * This method returns the client of the current Property.
     *
     * @return      the Property's client.
     */

    public Client getClient() {
        return client;
    }

    /**
     * This method compares the Store in which the current Property is listed with a specific Store object.
     *
     * @param store a Store Object to be compared with the Property's Store
     * @return      the result of the comparison between the Property's Store and the wanted Store
     */
    public boolean compareStore(Store store) {
        return this.store.equals(store);
    }

    public Boolean priceInRange(Pair<Integer,Integer> priceRange) {
        if (priceRange == null) {
            return false;
        }
        return (this.price > priceRange.getLeft() && this.price < priceRange.getRight());
    }





    /**
     * Checks if the given object is equal to this Property object
     * @param o the object to be compared with this Property object
     * @return true if the given object is equal to this Property object, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Property)) {
            return false;
        }
        Property property = (Property) o;
        Boolean areaEqual = area == property.area;
        Boolean addressEqual = property.address.equals(address);
        Boolean distanceToCentreEqual = distanceToCentre == property.distanceToCentre;
        Boolean propertyTypeEqual = property.propertyType.equals(propertyType);
//        Boolean dateEqual = date.equalsDate(property.date);
        return ( areaEqual && addressEqual && distanceToCentreEqual && propertyTypeEqual);
    }
    /**
     * Returns a hash code value for the Property object.
     *  The hash code is based on the values of the area, address, distanceToCentre and propertyType fields.
     *
     * @return the hash code value for this Property object
     */
    @Override
    public int hashCode() {
        return Objects.hash(area, address, distanceToCentre, propertyType);
    }
    /**
     * Clone method.
     *
     * @return A clone of the current Property.
     */
    public Property clone() {
        return new Property(
        this.price,
        this.area,
        this.address,
        this.distanceToCentre,
        this.photographs,
        this.propertyType,
        this.store,
        this.client,
        this.date);
//        this.agent = agent,
//        this.client = client);
    }

    /**
     * This method returns a description of the Property attributes.
     *
     * @return A formated string composed of the Property's attributes
     */
    @Override
    public String toString() {
         return String.format("%s - $%.2f - %.2f ft\u00b2 - %s - %s", this.propertyType.toString(), this.price, this.area, this.address.toString(), this.store.getDesignation());
    }

    public String toStringAll() {
        return String.format("\n Price: %.2f;" + "\n Area: %.2f;"+ "\n Address: %s;"+ "\n Distance to Center: %.2f;" +
                        " %s;" + "\n Store: %s"+ "\n Client: %s" + "\n Date: %s",this.price, this.area, address.toString(), this.distanceToCentre,
                propertyType.toString(), store.toString() , client.toString(), this.date.toString());
    }


}
