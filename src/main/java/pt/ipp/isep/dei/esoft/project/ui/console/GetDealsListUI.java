package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GetDealsListController;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Get deals list ui.
 */
public class GetDealsListUI implements Runnable{

    private List<Property> propertyList = new ArrayList<>();

    private int order = -1;

    private int method = -1;

    private List<String> orders = new ArrayList<>();

    private List<String> SortingMethods = new ArrayList<>();



    private final GetDealsListController controller = new GetDealsListController();

    @Override
    public void run() {
        if(orders.isEmpty()){
            orders.add("Ascendant");
            orders.add("Descendant");
        }

       if (SortingMethods.isEmpty()) {
           SortingMethods.add("Bubble sort");
           SortingMethods.add("Insertion sort");
       }



        propertyList = getPropertyDealsList();
        displayListOfProperties(propertyList);
        if (propertyList.size() == 0) {
            System.out.println("\u001B[33mThere are no deals to list.\u001B[0m");
        }else {
            order =displayAndRequestOrder();
            if (order != 0){
                method = displayAndRequestMethod();
            }if (method != 0) {

                double[] areaProperties = sortDeals(propertyList, method);

                List<Property> sortedProperties = GetPropertySorted(areaProperties);

                displaySortedDeals(sortedProperties, order);
            }
        }
    }


    /**
     * Display list of properties.
     *
     * @param properties the properties
     */
    public void displayListOfProperties(List<Property> properties){
        System.out.println("\u001B[36m#=======Property deals List=======#\u001B[0m");

        for (int i = 0; i < properties.size(); i++) {
            System.out.printf("\u001B[0m%d. %s%n", i+1, properties.get(i).toString());
        }

    }

    /**
     * Display and request order int.
     *
     * @return the int
     */
    public int displayAndRequestOrder() {
        while (true) {
            try {
                System.out.println("\u001B[36m#=======Order=======#\u001B[0m");

                for (int i = 0; i < orders.size(); i++){
                    System.out.printf("\u001B[0m%d. %s%n", i+1, orders.get(i));
                }

                System.out.println("\u001B[0m0. Cancel");
                System.out.println();
                return Utils.readIntegerFromConsole("Enter order:");


            } catch (Exception e) {
                System.out.println("\n\u001B[41 Error please chose one of the options\u001B[0m");
            }
        }
    }

    /**
     * Display and request method int.
     *
     * @return the int
     */
    public int displayAndRequestMethod() {
        while (true) {
            try {
                System.out.println("\u001B[36m#=======Sort Method=======#\u001B[0m");
                String answer = Utils.readLineFromConsole("Do you wish to sort the properties using a sort method?(y/n)");
                assert answer != null;
                if (answer.equals("y")){
                    for (int i = 0; i < SortingMethods.size(); i++){
                        System.out.printf("\u001B[0m%d. %s%n", i+1, SortingMethods.get(i));
                    }
                    System.out.println("\u001B[0m0. Cancel");
                    System.out.println();
                    return Utils.readIntegerFromConsole("Enter method:");
                } else return 0;

            } catch (Exception e) {
                System.out.println("\n\u001B[41Error please chose one of the options\u001B[0m");
            }
        }
    }

    /**
     * Get property deals list.
     *
     * @return the list
     */
    public List<Property > getPropertyDealsList(){
        return controller.getDealsOrder();
    }

    /**
     * Sort deals list.
     *
     * @param properties the properties
     * @param method     the method
     * @return the list
     */
    public double[] sortDeals(List<Property> properties, int method){
        double[] areaProperty = new double[properties.size()];

        int i = 0;
        for (Property p: properties) {
            areaProperty[i] = p.getArea();
            i++;
        }

        if (method == 1){
            areaProperty = controller.getBubbleSort(areaProperty);
        }else if (method == 2){
            areaProperty = controller.getInsetionSort(areaProperty);
        }

        return areaProperty;
    }

    /**
     * This method gets the sorted array of property area and create the corresponding list of properties.
     *
     * @param areaProperty sorted property area array.
     * @return sorted property list.
     */
    public List<Property> GetPropertySorted (double[] areaProperty){
        int i = 0;
        List<Property> sortedProperties = new ArrayList<>();
        for (Double a: areaProperty) {
            for (Property p: propertyList) {
                if(a.equals(p.getArea()) && (!sortedProperties.contains(p)) ){
                    sortedProperties.add(p);
                    i++;
                }
            }
        }
        return sortedProperties;
    }

    /**
     * Display sorted deals.
     *
     * @param sortedProperties the sorted properties
     * @param order            the order
     */
    public void  displaySortedDeals(List<Property> sortedProperties, int order){
        System.out.println("\u001B[36m#=======Property List=======#");
        if (order == 1){
            for (int i = 0; i < sortedProperties.size(); i++) {
                System.out.printf("\u001B[0m%d. %s%n", i+1, sortedProperties.get(i).toString());
            }
        }else if (order == 2){
            for (int j = sortedProperties.size() -1; j >= 0 ; j--) {
                System.out.printf("\u001B[0m%d. %s%n", j+1, sortedProperties.get(j).toString());
            }
        }
    }
}

