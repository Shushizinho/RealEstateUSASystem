package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.ListEmployeeEveryStoreController;

import java.util.Comparator;

//StoreNameComparator = new Comparator<Store>()
public class StoreNameComparator implements Comparator<Store>  {

private final ListEmployeeEveryStoreController controllerComparator = new ListEmployeeEveryStoreController();

    /**
     * The constant StoreNameComparator.
     */
    @Override
    public int compare(Store store1, Store store2) {
        Integer storeName1 = controllerComparator.getPropertyFromSpecificStore(store1).size();
        Integer storeName2 = controllerComparator.getPropertyFromSpecificStore(store2).size();

        //ascending order
        //return storeName1.compareTo(storeName2);

        //descending order
        return storeName2.compareTo(storeName1);
    }

}
