package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Store.
 */
public class Store implements Serializable {

    private Integer id;
    private String designation;
    private Address address;
    private String email;
    private long phoneNumber;


    /**
     * Instantiates a new Store.
     *
     * @param id          the id
     * @param designation the designation
     * @param email       the email
     * @param address     the address
     * @param phoneNumber the phone number
     */
    public Store(Integer id, String designation, String email, Address address, long phoneNumber) {
        this.id = id;
        this.designation = designation;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method returns the id of the current instance.
     *
     * @return the Store's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id for this Store.
     *
     * @param id the designation to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Checks if the given designation is equal to this Store's designation
     *
     * @param designation the designation to be compared with this Store's designation
     * @return 0 if the given designation is equal to this Store's designation, any other number otherwise
     */
    public Integer compareDescription(String designation) {
        return this.designation.compareTo(designation);
    }

    /**
     * This method returns the designation of the current instance.
     *
     * @return the Store's designation.
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the designation for this Store.
     *
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * This method returns the address of the current instance.
     *
     * @return address the Store's Address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the designation for this Store.
     *
     * @param address the designation to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * This method checks if the current instance has any associated agents.
     *
     * @return true if it has at least one associated agents, otherwise returns false.
     */
    public Boolean hasAgents() {
        if (!Repositories.getInstance().getEmployeeRepository().getAgentsFromStore(this).isEmpty()) return true;
        return false;

    }

    /**
     * This method returns the email of the current instance.
     *
     * @return email the Store's Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for this Store.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * This method returns the phone number of the current instance.
     *
     * @return the Store's phoneNumber.
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number for this Store.
     *
     * @param phoneNumber the phone number to set
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Checks if the given object is equal to this Store object
     *
     * @param o the object to be compared with this Store object
     * @return true if the given object is equal to this Store object, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Store)) {
            return false;
        }
        Store store = (Store) o;
        return (Objects.equals(id, store.id)
                && Objects.equals(designation, store.designation)
                && Objects.equals(address, store.address)
                && Objects.equals(email, store.email)
                && phoneNumber == store.phoneNumber);
    }

    /**
     * Returns a hash code value for the Property object.
     * The hash code is based on the values of the id, designation, address, email and phoneNumber fields.
     *
     * @return the hash code value for this Property object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, designation, address, email, phoneNumber);
    }

    /**
     * Returns true if the Store has the specified email address.
     *
     * @param email the email address to check
     * @return true if the Store has the specified email address, false otherwise
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Store clone() {
        return new Store(this.id = id, this.designation = designation, this.email = email, this.address = address, this.phoneNumber = phoneNumber);
    }

//    /**
//     * This method creates and returns a new Land object.
//     *
//     * @param propertyPrice            the Land's price.
//     * @param propertyArea             the Land's area.
//     * @param propertyAddress          the Land's address.
//     * @param propertyDistanceToCentre the Land's distance to the city centre.
//     * @param propertyPhotographs      the Land's list of photographs.
//     * @param propertyType             the type of the Property, should always be 'Land' in this case
//     * @param store                    the Store where the Land will be listed.
//     * @param creator                  the Person that registered the Land.
//     * @return a Land object.
//     */
//    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
//                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs,
//                                   PropertyType propertyType, Store store, Person creator, DateTime date) {
////        throw new NotImplementedException();
//        Property property = new Land(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyType, store, creator, date);
//
//
////        return ( addProperty(property) ) ? property : Repositories.getInstance().getPropertyRepository().getPropertyByData(property);
//
//        return (addProperty(property)) ? property : null;
//    }

//    /**
//     * This method creates and returns a new Apartment object.
//     *
//     * @param propertyPrice              the Apartment's price.
//     * @param propertyArea               the Apartment's area.
//     * @param propertyAddress            the Apartment's address.
//     * @param propertyDistanceToCentre   the Apartment's distance to the city centre.
//     * @param propertyPhotographs        the Apartment's list of photographs.
//     * @param propertyBedroomNumber      the number of bedrooms in the Apartment.
//     * @param propertyBathroomNumber     the number of bathrooms in the Apartment.
//     * @param propertyParkingSpaceNumber the number of parking spaces in the Apartment.
//     * @param propertyEquipmentList      the Apartment's list of available equipment.
//     * @param propertyType               the type of the Property, should always be 'Apartment' in this case
//     * @param store                      the Store where the Apartment will be listed.
//     * @param creator                    the Person that registered the Apartment.
//     * @param date
//     * @return a Apartment object.
//     */
//    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
//                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs, Integer propertyBedroomNumber, Integer propertyBathroomNumber,
//                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList,
//                                   PropertyType propertyType, Store store, Person creator, DateTime date) {
////        throw new NotImplementedException();
//        Property property = new Apartment(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber, propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyType, store, creator, new DateTime());
//
//
////        return ( addProperty(property) ) ? property : Repositories.getInstance().getPropertyRepository().getPropertyByData(property);
//
//        return (addProperty(property)) ? property : null;
//    }

//    /**
//     * This method creates and returns a new House object.
//     *
//     * @param propertyPrice              the House's price.
//     * @param propertyArea               the House's area.
//     * @param propertyAddress            the House's address.
//     * @param propertyDistanceToCentre   the House's distance to the city centre.
//     * @param propertyPhotographs        the House's list of photographs.
//     * @param propertyBedroomNumber      the number of bedrooms in the House.
//     * @param propertyBathroomNumber     the number of bathrooms in the House.
//     * @param propertyParkingSpaceNumber the number of parking spaces in the House.
//     * @param propertyEquipmentList      the House's list of available equipment.
//     * @param propertyHasBasement        if the House has a basement.
//     * @param propertyHasLoft            if the House has a loft.
//     * @param propertySunExposure        the sun exposure of the house.
//     * @param propertyType               the type of the Property, should always be 'House' in this case
//     * @param store                      the Store where the House will be listed.
//     * @param creator                    the Person that registered the House.
//     * @return a House object.
//     */
//    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
//                                   Double propertyDistanceToCentre, List<Photograph> propertyPhotographs, Integer propertyBedroomNumber, Integer propertyBathroomNumber,
//                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList, Boolean propertyHasBasement, Boolean propertyHasLoft, String propertySunExposure,
//                                   PropertyType propertyType, Store store, Person creator, DateTime date) {
////        throw new NotImplementedException();
//        Property property = new House(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber,
//                propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyHasBasement, propertyHasLoft,
//                propertySunExposure, propertyType, store, creator, date);
//
////        return ( addProperty(property) ) ? property : Repositories.getInstance().getPropertyRepository().getPropertyByData(property);
//
//        return (addProperty(property)) ? property : null;
//    }
//
//    /**
//     * Adds a property to the system through the propertyRepository.
//     *
//     * @param property the Property to be added
//     * @return true if the Property was successfully added to the repository, false otherwise
//     */
//    private Boolean addProperty(Property property) {
////        if ( !validateProperty(property) ) return false;
//        return Repositories.getInstance().getPropertyRepository().add(property);
//    }
//
//    /**
//     * Creates an announcement request for a given rent duration, business type, property, agent, and client.
//     *
//     * @param rentDuration the duration of the rent in months
//     * @param businessType the type of business associated with the announcement request
//     * @param property     the property associated with the announcement request
//     * @param agent        the agent associated with the announcement request
//     * @param client       the client associated with the announcement request
//     * @return the created announcement request if it was successfully added to the announcement request repository, null otherwise
//     */
//    public AnnouncementRequest createAnnouncementRequest(Integer rentDuration, BusinessType businessType, Property property, Agent agent, Client client) {
////        throw new NotImplementedException();
//        AnnouncementRequest announcementRequest = new AnnouncementRequest(rentDuration, businessType, property, agent, client);
//        return (addAnnouncementRequest(announcementRequest)) ? announcementRequest : null;
//    }
//
//    /**
//     * Creates an announcement request for a given business type, property, agent, and client.
//     *
//     * @param businessType the type of business associated with the announcement request
//     * @param property     the property associated with the announcement request
//     * @param agent        the agent associated with the announcement request
//     * @param client       the client associated with the announcement request
//     * @return the created announcement request if it was successfully added to the announcement request repository, null otherwise
//     */
//    public AnnouncementRequest createAnnouncementRequest(BusinessType businessType, Property property, Agent agent, Client client) {
////        throw new NotImplementedException();
//        AnnouncementRequest announcementRequest = new AnnouncementRequest(businessType, property, agent, client);
//        return (addAnnouncementRequest(announcementRequest)) ? announcementRequest : null;
//    }
//
//    /**
//     * Adds a property to the system through the announcementRequestRepository.
//     *
//     * @param announcementRequest the announcementRequest to be added
//     * @return true if the announcementRequest was successfully added to the repository, false otherwise
//     */
//    private Boolean addAnnouncementRequest(AnnouncementRequest announcementRequest) {
//        return Repositories.getInstance().getAnnouncementRequestRepository().add(announcementRequest);
//    }
//
////        private Boolean validateAnnouncementRequest(AnnouncementRequest announcementRequest) {throw new NotImplementedException();}


    /**
     * This method returns a description of the Store attributes.
     *
     * @return A formated string composed of the Store's attributes
     */

    @Override
    public String toString() {
        return String.format("StoreAddress: (%s)  |  StoreEmail: %s; ", address.toString(), this.email);
    }
}



