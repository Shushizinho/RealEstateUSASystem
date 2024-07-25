package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PurchaseOrdersController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import static jdk.internal.logger.DefaultLoggerFinder.SharedLoggers.system;


/**
 * The type Buy orders ui.
 */
public class PurchaseOrdersUI implements Runnable {

    private final PurchaseOrdersController controller = new PurchaseOrdersController()  ;

    private PurchaseOrdersController getController() {
        return controller;
    }

    /**
     * The Announcement.
     */
    Announcement announcement = null;

    /**
     * The Employee.
     */
    Employee employee = null;

    /**
     * The Client.
     */
    Client client = null;
    /**
     * The Announcements.
     */
    List<Announcement> announcements = controller.getPropertiesAndOffers(new AnnouncementComparator());

    public void run() {
        System.out.println("=".repeat(13) + " List of Buy Orders " + "=".repeat(14) + "\n");
        displayList();

        selectBuyOrder();

    }

    private void displayList() {

        AuthenticationRepository authentication = Repositories.getInstance().getAuthenticationRepository();

        int i=1;
        int j=1;

        String CurrentUser = String.valueOf(authentication.getCurrentUserSession().getUserId());


        if(!announcements.isEmpty()) {
            for (Announcement saleAnnouncement : announcements) {

                String PropUser = String.valueOf(saleAnnouncement.getAgent().getEmployee().getEmail());
                if (PropUser.equalsIgnoreCase(CurrentUser)) {

                    System.out.println("=".repeat(12) + " " + i + " - " + saleAnnouncement.getProperty().getPropertyType().getDescription() + " - " + saleAnnouncement.getDate() + " " + "=".repeat(12));
                    List<PurchaseOrder> orders =  controller.getOrders(saleAnnouncement, new PurchaseOrderComparator());

                    if (orders.size() > 0) {
                       // controller.getListHighestToLowest(orders, new PurchaseOrderComparator());
                        for (PurchaseOrder purchaseOrder : orders) {
                            if ( purchaseOrder.getWasAccepted() != 1 && purchaseOrder.getWasAccepted() != -1) {
                                System.out.println(j + " - " + purchaseOrder.getOrderAmount());
                            }
                        }
                    } else {
                        System.out.println("- No purchase orders");
                    }
                    System.out.println("-".repeat(47));

                }

                i++;
            }
        }else {
            System.out.println("- Without announcements");
        }
        System.out.println("0 - Cancel");
        System.out.println("=".repeat(47));

    }

    private void selectBuyOrder(){

        boolean validInput = false;
        String subject = null;
        String message = null;
        String numProp = null;
        String numOffe = null;


        while(!validInput) {

            try {
                numProp = Utils.readLineFromConsole("Select the property: ");

                if (Integer.parseInt(numProp)==0){
                    break;
                }
                numOffe = Utils.readLineFromConsole("Select the offer: ");

                if (Integer.parseInt(numOffe)==0){
                    break;
                }


                if (isValidInput(numProp) && isValidInput(numOffe)) {

                    Announcement saleAnnouncement = announcements.get(Integer.parseInt(numProp) - 1);

                    List<PurchaseOrder> orders = controller.getPurchas(saleAnnouncement);
                    PurchaseOrder purchaseOrder = orders.get(Integer.parseInt(numOffe) - 1);
                    System.out.println(purchaseOrder);

                    validInput = true;
                    if (askAnnouncement().equalsIgnoreCase("Yes")) {

                        subject = "Your offer was Accepted";
                        message = "Dear " + purchaseOrder.getClient().getName() + " your offer for the announcement " + saleAnnouncement.getProperty().getPropertyType().getDescription() + " - " + saleAnnouncement.getDate() + " was accepted";

                        controller.createEmail(saleAnnouncement.getAgent().getEmployee().getEmail(), purchaseOrder.getClient().getEmail(), subject, message);
//                        announcements.remove(saleAnnouncement);
                        controller.setAcceptedPurchaseOrder(purchaseOrder);
                    } else {

                        subject = "Your offer was declined";
                        message = "Dear " + purchaseOrder.getClient().getName() + " your offer for the announcement " + saleAnnouncement.getProperty().getPropertyType().getDescription() + " - " + saleAnnouncement.getDate() + " was declined";

                        controller.createEmail(saleAnnouncement.getAgent().getEmployee().getEmail(), purchaseOrder.getClient().getEmail(), subject, message);
//                        orders.remove(purchaseOrder);
                        controller.setDeclinePurchaseOrder(purchaseOrder);

                    }

                } else {
                    throw new IllegalArgumentException("Must be in the right format.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid selection. " + e.getMessage() + " Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid input.");

            }
        }
    }

    /**
     * Ask announcement string.
     *
     * @return the string
     */
    public String askAnnouncement() {

        String answer = Utils.readLineFromConsole("Do you want to accept or decline the offer?(Yes/No): ");

        while (answer.trim().isEmpty() || (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No"))) {
            System.out.println("Please enter Yes or No");
            answer = Utils.readLineFromConsole("Do you want to accept or decline the offer?(Yes/No): ");
        }
        return answer;
    }

    /**
     * Is valid input boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean isValidInput(String input) {
        String numRegex = "^[1-9]{1}$";
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
