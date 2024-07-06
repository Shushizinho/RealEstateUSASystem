package pt.ipp.isep.dei.esoft.project.repository;

import org.apache.commons.math.stat.regression.SimpleRegression;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A repository class for managing all Purchase Orders.
 */
public class PurchaseOrderRepository {

//    private final ArrayList<Pair<Announcement, ArrayList<PurchaseOrder>>> ordersByAnnouncement = new ArrayList<>();
    private final ArrayList<PurchaseOrder> allOrders = new ArrayList<>();

    /**
     * Place order purchase order.
     *
     * @param orderAmount  the order amount
     * @param announcement the announcement
     * @param cLient       the c lient
     * @return the purchase order
     */
    public PurchaseOrder placeOrder(Double orderAmount, Announcement announcement, Client cLient) {
        PurchaseOrder order = new PurchaseOrder(orderAmount, announcement, cLient);
        if (addOrder(order)) return order;
        return null;

    }

    /**
     * Place order purchase order.
     *
     * @param orderAmount  the order amount
     * @param announcement the announcement
     * @param cLient       the c lient
     * @param wasAccepted  the was accepted
     * @param date         the date
     * @return the purchase order
     */
    public PurchaseOrder placeOrder(Double orderAmount, Announcement announcement, Client cLient, int wasAccepted, DateTime date) {
        PurchaseOrder order = new PurchaseOrder(orderAmount, announcement, cLient,wasAccepted,date);
        if (addOrder(order)) return order;
        return null;

    }

    /**
     * Gets all orders.
     *
     * @return the all orders
     */
    public ArrayList<PurchaseOrder> getAllOrders() {
        return allOrders;
    }

    /**
     * Adds a PurchaseOrder to the ordersByAnnouncement list if it passes the validation.
     *
     * @param order The PurchaseOrder to add.
     * @return true if the order is added successfully, false otherwise.
     */
    public Boolean addOrder(PurchaseOrder order) {
//        int index = getIndexOf(order.getAnnouncement());
//        if (index != -1) {
//            if (ordersByAnnouncement.get(index).getRight().add(order)) return true;
//        }
        if (!validateOrder(order)) return false;
//        ArrayList<PurchaseOrder> newList = new ArrayList<PurchaseOrder>();
//        newList.add(order);
//        return (ordersByAnnouncement.add(new Pair<>(order.getAnnouncement(), newList)));
        allOrders.add(order);
        return true;
    }
    /**
     * Validates a PurchaseOrder by performing various checks.
     * Checks include:
     * - Ensuring the order amount is not greater than the property price.
     * - Checking if the client already has an active order for the same announcement.
     *
     * @param order The PurchaseOrder to validate.
     * @return true if the order is valid, false otherwise.
     */
    private Boolean validateOrder(PurchaseOrder order) {
//        int index = getIndexOf(order.getAnnouncement());
        List<PurchaseOrder> orders = getOrdersByAnnouncement(order.getAnnouncement());
        if (order.getOrderAmount() > order.getAnnouncement().getListedPrice()) return false;
        if (orders.size() > 0) {
            for (PurchaseOrder offToCompare : orders) {
                if (offToCompare.equals(order))
                    System.out.println("There are other orders with this value, they will be considered first when selling the property.");
            }
        }
        return true;
    }
    /**
     * Returns the index of the specified Announcement in the ordersByAnnouncement list.
     *
     * @param announcement The Announcement object to find the index of.
     * @return The index of the Announcement in the ordersByAnnouncement list, or -1 if not found.
     */
//    public int getIndexOf(Announcement announcement) {
//        for (Pair<Announcement, ArrayList<PurchaseOrder>> currPair: ordersByAnnouncement) {
//            if (currPair.getLeft().equals(announcement)) return ordersByAnnouncement.indexOf(currPair);
//        }
//        return -1;
//    }

    /**
     * This method returns a defensive (immutable) copy of the list of PurchaseOrders and their respective Announcement.
     *
     * @param announcement the announcement
     * @return The list of PurchaseOrders and their respective Announcement.
     */
    public List<PurchaseOrder> getOrdersByAnnouncement(Announcement announcement) {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        ArrayList<PurchaseOrder> ordersByAnnouncement = new ArrayList<>();
        for (PurchaseOrder a:allOrders) {
            if (a.getAnnouncement().equals(announcement)) ordersByAnnouncement.add(a);
        }
        return List.copyOf(ordersByAnnouncement);
    }

    /**
     * This method returns a defensive copy of the list of PurchaseOrders and their respective Announcement.
     *
     * @param announcement the announcement
     * @return The list of PurchaseOrders and their respective Announcement.
     */
    public List<PurchaseOrder> getOrderByAnnouncement(Announcement announcement) {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        ArrayList<PurchaseOrder> ordersByAnnouncement = new ArrayList<>();
        for (PurchaseOrder a:allOrders) {
            if (a.getAnnouncement().equals(announcement)) ordersByAnnouncement.add(a);
        }
        return ordersByAnnouncement;
    }

    /**
     * This method get the property associated to the purchase orders that where accepted.
     *
     * @return property of the purchase orders accepted.
     */
    public List<Property> getDealsFromOrder(){
        List<Property> properties= new ArrayList<>();
        for (PurchaseOrder order: allOrders){
            if (order.accepted() == 1){
                properties.add(order.getAnnouncement().getProperty());
            }
       }
        return properties;
    }

    /**
     * This method saves in a list all the purchase orders that were accepted.
     *
     * @return accepted purchase orders.
     */
    public List<PurchaseOrder> getDealsPropertiesAndApartments(){
        List<PurchaseOrder> p = new ArrayList<>();
        for (PurchaseOrder a : allOrders) {
            if(a.accepted() == 1 && ((a.getAnnouncement().getProperty().getPropertyType().getDescription().equals("House")) ||
                    (a.getAnnouncement().getProperty().getPropertyType().getDescription().equals("Apartment")) ) ){
                p.add(a);
            }
        }
        return p;
    }

//    public List<String> getBedroomNumberFromDeals(){
//        List<String> a = new ArrayList<>();
//        List<PurchaseOrder> p = getDealsPropertiesAndApartments();
//        for (PurchaseOrder order: p) {
//            if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null){
//                a.add(String.valueOf(PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()).getBedroomNumber()));
//            }
//        }
//        return a;
//    }

    /**
     * This method creates a SimpleRegression with the area data the listed price of all deals.
     *
     * @return SimpleRegression based on area and listed prices.
     */
    public SimpleRegression getSimpleRegressionByArea(){
        SimpleRegression s = new SimpleRegression();
        List<PurchaseOrder> p = getDealsPropertiesAndApartments();
        for (PurchaseOrder order: p){
            if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null) {
                s.addData(order.getAnnouncement().getProperty().getArea(), order.getAnnouncement().getListedPrice());
            }
        }

        return s;
    }

    /**
     * Get simple regression by distance to center simple regression.
     *
     * @return the simple regression
     */
    public SimpleRegression getSimpleRegressionByDistanceToCenter(){
        SimpleRegression s = new SimpleRegression();
        List<PurchaseOrder> p = getDealsPropertiesAndApartments();
        for (PurchaseOrder order: p){
            if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null) {
                s.addData(order.getAnnouncement().getProperty().getDistanceToCentre(), order.getAnnouncement().getListedPrice());
            }
        }

        return s;
    }


    /**
     * Get simple regression by number of bedrooms simple regression.
     *
     * @return the simple regression
     */
    public SimpleRegression getSimpleRegressionByNumberOfBedrooms(){
        SimpleRegression s = new SimpleRegression();
        List<PurchaseOrder> p = getDealsPropertiesAndApartments();
        for (PurchaseOrder order: p) {
            if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null) {
                s.addData(Objects.requireNonNull(PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty())).getBedroomNumber(), order.getAnnouncement().getListedPrice());
            }
        }

        return s;
    }

    /**
     * Get simple regression by number of bathrooms simple regression.
     *
     * @return the simple regression
     */
    public SimpleRegression getSimpleRegressionByNumberOfBathrooms(){
        SimpleRegression s = new SimpleRegression();
        List<PurchaseOrder> p = getDealsPropertiesAndApartments();
        for (PurchaseOrder order: p){
            if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null) {
                s.addData(Objects.requireNonNull(PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty())).getBathroomNumber(), order.getAnnouncement().getListedPrice());
            }
        }
        return s;
    }

    /**
     * Get simple regression by number of parking spaces simple regression.
     *
     * @return the simple regression
     */
    public SimpleRegression getSimpleRegressionByNumberOfParkingSpaces(){
        SimpleRegression s = new SimpleRegression();
        List<PurchaseOrder> p = getDealsPropertiesAndApartments();
        for (PurchaseOrder order: p) {
            if (PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty()) != null) {
                s.addData(Objects.requireNonNull(PropertyRepository.getInhabitableByProperty(order.getAnnouncement().getProperty())).getParkingSpaceNumber(), order.getAnnouncement().getListedPrice());
            }
        }
        return s;
    }


    /**
     * Create email.
     *
     * @param sender    the sender
     * @param recipient the recipient
     * @param subject   the subject
     * @param message   the message
     */
    public void createEmail(String sender, String recipient, String subject, String message) {
        String fileName = sender + "_" + recipient + ".txt";
        String fileContent = "From: " + sender + "\n" + "Recipient: " + recipient + "\n" + "Subject: " + subject + "\n\n" + message;

        String directory = "src\\main\\resources\\passwords\\";

        try {
            File file = new File(directory, fileName);

            FileWriter writer;
            if (file.exists()) {
                writer = new FileWriter(file, true);
            } else {
                writer = new FileWriter(file);
            }

            writer.append("\n\n");
            writer.write(fileContent);
            writer.close();
            System.out.println("=====================");
            System.out.println("E-mail was sent successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the e-mail.");
            e.printStackTrace();
        }
    }
}
