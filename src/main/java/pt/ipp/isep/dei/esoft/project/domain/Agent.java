package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.*;
import java.util.*;


/**
 * Represents a real estate agent who can create and manage properties and announcements.
 */
public class Agent implements Serializable {

    private Employee employee;


    /**
     * Creates a new instance of Agent with the provided information.
     *
     * @param employee the Employee that plays the role of this Agent
     */
    public Agent(Employee employee) {
        this.employee = employee;

    }

    /**
     * Gets employee.
     *
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Checks if the given object is equal to this Agent object
     * @param obj the object to be compared with this Agent object
     * @return true if the given object is equal to this Agent object, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Agent)) {
            return false;
        }
        Agent other = (Agent) obj;
        return Objects.equals(getEmployee(), other.getEmployee());
    }

    /**
     * Returns a hash code value for the Agent object.
     * The hash code is based on the values of the employee field.
     *
     * @return the hash code value for this Agent object
     */
    public int hashCode() {
        return Objects.hash(getEmployee());
    }

    /**
     *Returns a clone of this Agent. The clone has the same employee as this Agent.
     * @return a clone of this Agent
     */
    public Agent clone() {
        return new Agent(this.getEmployee());
    }




    /**

     Creates and adds a new Land property to the system.
     @param propertyPrice the price of the property
     @param propertyArea the area of the property
     @param propertyAddress the address of the property
     @param propertyDistanceToCentre the distance of the property to the city center
     @param propertyPhotographs a list of photographs of the property
     @param propertyType the type of the property
     @param store the store where the property is listed
     @param creator the employee who created the property
     @return the created Property if it was successfully added to the system, or null otherwise
     */



/*    public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<String> propertyPhotographs,
                                   PropertyType propertyType, Store store, Person creator){

        Property property = new Land(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyType, store, creator);




        return ( addProperty(property) ) ? property : null;
    }*/

    /**

     Creates an apartment property and adds it to the system.
     @param propertyPrice The price of the property.
     @param propertyArea The area of the property.
     @param propertyAddress The address of the property.
     @param propertyDistanceToCentre The distance to the city center in km.
     @param propertyPhotographs A list of URLs to the photographs of the property.
     @param propertyBedroomNumber The number of bedrooms in the property.
     @param propertyBathroomNumber The number of bathrooms in the property.
     @param propertyParkingSpaceNumber The number of parking spaces available.
     @param propertyEquipmentList A list of equipment available in the property.
     @param propertyType The type of the property.
     @param store The store where the property is listed.
     @param creator The employee who creates the property.
     @return The newly created apartment property or null if the creation failed.
     */

 /*   public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<String> propertyPhotographs, Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList,
                                   PropertyType propertyType, Store store, Person creator){

        Property property = new Apartment(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber, propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyType, store, creator);




        return ( addProperty(property) ) ? property : null;
    }
*/

    /**

     Creates a new Property object of type House and adds it to the PropertyRepository.
     @param propertyPrice the price of the property
     @param propertyArea the area of the property
     @param propertyAddress the address of the property
     @param propertyDistanceToCentre the distance from the property to the city center
     @param propertyPhotographs a list of URLs pointing to the photographs of the property
     @param propertyBedroomNumber the number of bedrooms in the property
     @param propertyBathroomNumber the number of bathrooms in the property
     @param propertyParkingSpaceNumber the number of parking spaces in the property
     @param propertyEquipmentList a list of equipment items included in the property
     @param propertyHasBasement whether the property has a basement or not
     @param propertyHasLoft whether the property has a loft or not
     @param propertySunExposure the sun exposure of the property
     @param propertyType the type of the property (e.g., apartment, house)
     @param store the store where the property is located
     @param creator the Person object who creates the property
     @return the created Property object if added successfully to the repository; null otherwise
     */
    /*public Property createProperty(Double propertyPrice, Double propertyArea, Address propertyAddress,
                                   Double propertyDistanceToCentre, List<String> propertyPhotographs, Integer propertyBedroomNumber, Integer propertyBathroomNumber,
                                   Integer propertyParkingSpaceNumber, List<String> propertyEquipmentList, Boolean propertyHasBasement, Boolean propertyHasLoft, String propertySunExposure,
                                   PropertyType propertyType, Store store, Person creator){

        Property property = new House(propertyPrice, propertyArea, propertyAddress, propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber,
                propertyBathroomNumber, propertyParkingSpaceNumber, propertyEquipmentList, propertyHasBasement, propertyHasLoft,
                propertySunExposure, propertyType, store, creator);



        return ( addProperty(property) ) ? property : null;
    }*/





  /*  public Optional<SaleAnnouncement> createSaleAnnouncement(String title, double price, DateTime date, double commission, BusinessType businessType, PropertyType propertyType, Property property, double listedPrice,Employee agent){




        SaleAnnouncement saleAnnouncement = new SaleAnnouncement(title, price, date, commission, propertyType,businessType, property,agent, listedPrice);

        if (announcementRepository.validateSaleAnnouncement(saleAnnouncement)) {
            return Optional.empty();
        }

        Optional<SaleAnnouncement> newAnnouncement = Optional.of(saleAnnouncement.clone());

        announcementRepository.add(saleAnnouncement);

        return newAnnouncement;

        //return ( addSaleAnnouncement(saleAnnouncement ));
    }


    *//**

     Creates a new rent announcement with the specified parameters and adds it to the sale announcement repository.
     @param title the title of the rent announcement
     @param price the price of the rent announcement
     @param date the date of the rent announcement
     @param commission the commission of the rent announcement
     @param propertyType the type of the property associated with the rent announcement
     @param businessType the type of the business associated with the rent announcement
     @param property the property associated with the rent announcement
     @param rentDuration the duration of the rent associated with the rent announcement
     @return an optional containing the created rent announcement if it was added successfully to the repository, or an empty optional if not
     */
  /*  public Optional<RentAnnouncement> createRentAnnouncement(String title, double price, DateTime date, double commission, PropertyType propertyType, BusinessType businessType, Property property, Integer rentDuration,Employee agent){

        RentAnnouncement rentAnnouncement = new RentAnnouncement(title, price, date, commission,propertyType,businessType,property,rentDuration,agent);

        if (announcementRepository.validateRentAnnouncement(rentAnnouncement)) {
            return Optional.empty();
        }

        Optional<RentAnnouncement> newAnnouncement = Optional.of(rentAnnouncement.clone());

        announcementRepository.add(rentAnnouncement);

        return newAnnouncement;

        //return ( addRentAnnouncement(rentAnnouncement ));
    }*/








    /**

     Adds a property to the list of properties of the agent, if it's valid.
     @param property The property to be added.
     @return true if the property was successfully added, false otherwise.
     */
    /*private boolean addProperty(Property property) {
        boolean success = false;
        if (validate(property)) {
            success = properties.add(property.clone());
        }
        return success;
    }*/

    /**
     * Adds a property to the property repository.
     *
     * @param property The property to be added.
     * @return {@code true} if the property was successfully added, {@code false} otherwise.
     */

    private Boolean addProperty(Property property) {
//        if ( !validateProperty(property) ) return false;
        return Repositories.getInstance().getPropertyRepository().add(property);
    }



//    public void createEmail(String sender, String recipient, String subject, String message) {
//        String fileName = sender + "_" + recipient + ".txt";
//        String fileContent = "From: " + sender + "\n" + "Recipient: " + recipient + "\n" + "Subject: " + subject + "\n\n" + message;
//
//        String directory = "src\\main\\resources\\passwords\\";
//
//        try {
//            File file = new File(directory, fileName);
//
//            FileWriter writer;
//            if (file.exists()) {
//                writer = new FileWriter(file, true);
//            } else {
//                writer = new FileWriter(file);
//            }
//
//            writer.append("\n\n");
//            writer.write(fileContent);
//            writer.close();
//            System.out.println("=====================");
//            System.out.println("E-mail was sent successfully.");
//        } catch (IOException e) {
//            System.out.println("An error occurred while creating the e-mail.");
//            e.printStackTrace();
//        }
//    }


    /*public String toString() {
        return employee.toString();
    }
*/


    /**
     * Retrieves the Store associated with this object.*
     *
     * @return The Store associated with this object.
     */
    public Store getStore() {
        return this.employee.getStore();
    }

    /**
     * This method returns the name of the current instance.
     *
     * @return the Employee's name.
     */
    public String getName() {
        return this.employee.getName();
    }

    /**
     * This method returns the email of the current instance.
     *
     * @return the Employee's email.
     */
    public String getEmail(){
        return this.employee.getEmail();
    }

    /**
     * This method returns the passport number of the current instance.
     *
     * @return the Employee's passport number.
     */
    public Integer getPassportNumber() {
        return this.employee.getPassportNumber();
    }

    /**
     * This method returns the phone number of the current instance.
     *
     * @return the Employee's phone number.
     */
    public long getPhoneNumber() {
        return this.employee.getPhoneNumber();
    }

    /**
     * This method returns the Address of the current instance.
     *
     * @return the Employee's address.
     */
    public Address getAddress() {
        return this.employee.getAddress();
    }

    /**
     * This method returns the tax number of the current instance.
     *
     * @return the Employee's tax number.
     */
    public String getTaxNumber() {
        return this.employee.getTaxNumber();
    }

    






}
