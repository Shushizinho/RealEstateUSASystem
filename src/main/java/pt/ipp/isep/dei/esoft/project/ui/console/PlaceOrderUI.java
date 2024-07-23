package pt.ipp.isep.dei.esoft.project.ui.console;

import org.apache.commons.lang3.NotImplementedException;
import pt.ipp.isep.dei.esoft.project.application.controller.PlaceOrderController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.PairDTO;
import pt.ipp.isep.dei.esoft.project.dto.PropertyDTO;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;
import pt.ipp.isep.dei.esoft.project.repository.PropertyFiltersRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.File;
import java.util.*;

/**
 * Place Order UI (console).
 */
public class PlaceOrderUI implements Runnable  {

    private final PlaceOrderController controller = new PlaceOrderController();
    private String storeDescription;
    private String propertyDescription;
    private PairDTO<Integer, Integer> priceRange;
    private Double orderAmount;

    private PlaceOrderController getController() {
        return controller;
    }
    /**
     * Executes the process of placing an order on a property.
     */
    public void run() {
//        throw new NotImplementedException();
        System.out.println("\u001B[36m#=======Place Order On Property=======#");

        storeDescription = displayAndSelectStore();
        if (Objects.equals(storeDescription, "\\Cancel")) return;
        priceRange = displayAndSelectPriceRange();
        Pair<Integer, Integer> compair = new Pair<>(-1,-1);
        boolean equal = Objects.equals(priceRange, compair);
        if (equal) return;
        propertyDescription = displayAndSelectProperty();
        if (propertyDescription != null){
            if (propertyDescription.compareTo("\\Cancel") == 0 || controller.hasActiveOrder(propertyDescription)) return;
            requestData();
            if (orderAmount == 0) return;
            PurchaseOrder order = submitData();
        }
    }
    /**
     * This method submits the property data provided by the user and creates a new PurchaseOrder object
     * It returns the created PurchaseOrder object or null if it fails to create the property.
     * @return The created PurchaseOrder object or null if the creation fails.
     */
    private PurchaseOrder submitData() {
//        throw new NotImplementedException();
        return controller.placeOrder(orderAmount, propertyDescription);
    }

    /**
     * Requests and collects the necessary data for creating a PurchaseOrder object
     * i.e. the Order Amount
     */
    private void requestData() {
        orderAmount = requestOrderAmount();
    }

    /**
     * Requests the user to enter the order amount for a property.
     *
     * @return The order amount entered by the user.
     */
    private double requestOrderAmount() {
        double amount = 0d;
        double temp = Repositories.getInstance().getPropertyRepository().getPropertyByDescription(propertyDescription).getPrice();
        while (true) {
            try {
                boolean positive = false;
                boolean valid = false;
                while(!positive || !valid) {
                    amount = Utils.readDoubleFromConsole("Order Amount:");
                    positive = (amount >= 0);
                    valid = (amount < temp);
                    if(!positive){
                        System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
                    }
                    if(!valid){
                        System.out.println("\u001B[31mCannot be Higher than Listed Price.\u001B[0m");
                    }
                }
                return amount;
            } catch (InputMismatchException | IllegalArgumentException e) {
//                e.printStackTrace();
                System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
            }
        }
    }

    /**
     * Displays the list of price ranges and prompts the user to select one by entering its corresponding number.
     * @return The description of the selected price range.
     */
    private PairDTO<Integer, Integer> displayAndSelectPriceRange() {
        while (true){
            try{
                List<PairDTO<Integer, Integer>> priceRangeList = controller.getPriceRanges();
                int listSize =priceRangeList.size();
                int answer = -2;

                Scanner input = new Scanner(System.in);

                while (answer < -1 || answer > listSize) {
                    displayPriceRangeList(priceRangeList);
                    System.out.println("\u001B[35m\nType your option: \u001B[0m");
                    answer = input.nextInt();
                    if ( answer < -1 || answer > listSize ) {
                        System.out.println("\u001B[31mMust be one of the provided options.\u001B[0m");
                    }
                }
                if (answer == 0) return new PairDTO<>(0,Integer.MAX_VALUE);
                if (answer == -1) return new PairDTO<>(-1,-1);
                else return priceRangeList.get(answer - 1).clone();


            }catch (Exception e){
                System.out.println("\"\\u001B[31mMust be one of the provided options.\\u001B[0m\"");
            }
        }


    }

    /**
     * Displays the list of properties and prompts the user to select one by entering its corresponding number.
     * @return The description of the selected property.
     */
    private String displayAndSelectProperty() {
        //Display the list of property types
        List<PropertyDTO> propertyList = controller.getAvailableProperties(Repositories.getInstance().getStoreRepository().getStoreByDescription(storeDescription), priceRange);

        int listSize = propertyList.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 0 || answer > listSize) {
            displayPropertyOptions(propertyList);
            if (listSize != 0) {
                answer = Utils.readIntegerFromConsole("Select a property:");
            }
            else return null;
            if ( answer < 0 || answer > listSize ) {
                System.out.println("\u001B[31mMust be one of the provided options.\u001B[0m");
            }
        }

        if (answer != 0) return propertyList.get(answer - 1).toStringDTO();
        else return null;

    }

    /**
     * Displays the list of stores and prompts the user to select one by entering its corresponding number.
     * @return The description of the selected store.
     */

    private String displayAndSelectStore() {
        //Display the list of property types
        List<StoreDTO> storeDTOList = controller.getStoresWithAgents();

        int listSize = storeDTOList.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        if (listSize>=1) {
            while (answer < 1 || answer > listSize) {
                displayStoreOptions(storeDTOList);
                System.out.println("\u001B[35m\nType your option: \u001B[0m");
                answer = input.nextInt();
                if (answer < -1 || answer > listSize) {
                    System.out.println("\u001B[31mMust be one of the provided options.\u001B[0m");
                }
                if (answer == 0) return null;
                if (answer == -1) return "\\Cancel";
            }
        } else {
            answer = 1;
        }
        return storeDTOList.get(answer - 1).getDesignation();

    }

    /**
     * Displays the list of property as a menu with number options to select.
     * @param properties A List of Property objects to be displayed as menu options.
     */
    private void displayPropertyOptions(List<PropertyDTO> properties) {
        if (properties.isEmpty()) {
            System.out.println("\u001B[33mThe are no properties that fit the provided filters.\u001B[0m");
            System.out.println();
        } else {
            int i = 1;
            for (PropertyDTO property : properties) {
                System.out.println("\u001B[0m" + i + ". " + property.toStringDTO());
                i++;
            }

            System.out.println("\u001B[0m0. Cancel\u001B[0m");
            System.out.println();
        }
    }
    /**
     * Displays the list of price ranges as a menu with number options to select.
     * @parampriceRangeList A List ofpriceRangeList to be displayed as menu options.
     */
    private void displayPriceRangeList(List<PairDTO<Integer, Integer>>priceRangeList) {
        //display the property types as a menu with number options to select
        System.out.println("\u001B[36m#=======Price Range=======#");

        int i = 1;
        for (PairDTO<Integer, Integer> priceRange :priceRangeList) {
            System.out.println("\u001B[0m"+i + ". " + controller.printPriceRange(priceRange));
            i++;
        }
        System.out.println("\u001B[33m"+i+". Do not use this filter\u001B[0m");
        System.out.println("\n\u001B[0m0. Cancel\u001B[0m");
    }

    /**
     * Displays the list of stores as a menu with number options to select.
     * @param storeList A List of Store objects to be displayed as menu options.
     */
    private void displayStoreOptions(List<StoreDTO> storeList) {
        //display the property types as a menu with number options to select
        int i = 1;
        System.out.println("\u001B[36m#=======Select Store=======#");
        for (StoreDTO store : storeList) {
            System.out.println("\u001B[0m"+i + ". " + store.getDesignation());
            i++;
        }
        System.out.println("\n\u001B[0m"+i+". Do not use this filter\u001B[0m");
        System.out.println("\n\u001B[0m0. Cancel\u001B[0m");

    }

//    /**
//     * Displays the list of stores as a menu with number options to select.
//     * @parampriceRangeList A List of PriceRange objects to be displayed as menu options.
//     */
//    private void displayPriceRangeOptions(List<Pair<Integer, Integer>>priceRangeList) {
//        //display the property types as a menu with number options to select
//        int i = 1;
//        for (Pair<Integer, Integer> priceRange :priceRangeList) {
//            System.out.println(i + " - " + priceRange.toString());
//            i++;
//        }
//        System.out.println("0 - I don't use this filter");
//    }


//    /**
//     * Displays the list of agents as a menu with number options to select.
//     * @param agents A List of Employee objects to be displayed as menu options.
//     */
//    private void displayAgentOptions(List<Employee> agents) {
//        //display the property types as a menu with number options to select
//        int i = 1;
//        for (Employee agent : agents) {
//            System.out.println(i + " - " + agent.getName());
//            i++;
//        }
//    }

    /**
     * Is alpha boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isAlpha(String str) {
        String regex = "[a-zA-Z]+";
        return str.matches(regex);
    }
}
