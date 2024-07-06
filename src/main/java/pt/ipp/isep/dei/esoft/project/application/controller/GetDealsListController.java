package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import pt.ipp.isep.dei.esoft.project.domain.InsertionSortAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.BubbleSortAlgorithm;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PurchaseOrder;
import pt.ipp.isep.dei.esoft.project.repository.PurchaseOrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Get deals list controller.
 */
public class GetDealsListController {
    private final PurchaseOrderRepository purchaseOrderRepository = Repositories.getInstance().getPurchaseOrderRepository();

    /**
     * Constructs a new instance of the GetDealsListController class and retrieves the deals list.
     */
    public GetDealsListController(){
        getDealsOrder();
    }

    /**
     * Retrieves the list of deals from the purchase order repository.
     *
     * @return The list of properties representing the deals.
     */
    public List<Property> getDealsOrder(){
        return purchaseOrderRepository.getDealsFromOrder();
    }

    /**
     * Sorts the given array of property areas using the Bubble Sort algorithm.
     *
     * @param areaProperty The array of property areas to be sorted.
     * @return The sorted array of property areas.
     */
    public double[] getBubbleSort(double[] areaProperty){
         return BubbleSortAlgorithm.sort(areaProperty);
    }

    /**
     * Sorts the given array of property areas using the Insertion Sort algorithm.
     *
     * @param areaProperty The array of property areas to be sorted.
     * @return The sorted array of property areas.
     */
    public double[] getInsetionSort(double[] areaProperty){
        return InsertionSortAlgorithm.sort(areaProperty);
    }

    /**
     * Retrieves the list of deals properties and apartments from the purchase order repository.
     *
     * @return The list of purchase orders representing the deals.
     */
    public List<PurchaseOrder> getDeals(){
        return purchaseOrderRepository.getDealsPropertiesAndApartments();
    }

    /**
     * Lookup element by id t.
     *
     * @param <T>    the type parameter
     * @param parent the parent
     * @param id     the id
     * @return the t
     */
    public <T extends Node> T lookupElementById(Parent parent, String id) {
        return (T) parent.lookup("#" + id);
    }


    /**
     * Sort deals double [ ].
     *
     * @param properties the properties
     * @param method     the method
     * @return the double [ ]
     */
    public double[] sortDeals(List<Property> properties, int method){
        double[] areaProperty = new double[properties.size()];

        int i = 0;
        for (Property p: properties) {
            areaProperty[i] = p.getArea();
            i++;
        }

        if (method == 1){
            areaProperty = getBubbleSort(areaProperty);
        }else if (method == 2){
            areaProperty = getInsetionSort(areaProperty);
        }

        return areaProperty;
    }

    /**
     * Get property sorted list.
     *
     * @param propertyList the property list
     * @param areaProperty the area property
     * @return the list
     */
    public List<Property> getPropertySorted (List<Property> propertyList ,double[] areaProperty){
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

}
