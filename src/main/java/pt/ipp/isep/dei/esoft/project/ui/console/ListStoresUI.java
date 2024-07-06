package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterStoreController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;


/**
 * The type List stores ui.
 */
public class ListStoresUI implements Runnable {

    private final RegisterStoreController controller = new RegisterStoreController();
    private Integer id;
    private String designation;
    private Address address;
    private String email;
    private Integer phoneNumber;

    private String role;

    private RegisterStoreController getController() {
        return controller;
    }

    public void run() {
        System.out.println("=== List ===");

        displayList();
    }

    private void displayList() {
        //display the task categories as a menu with number options to select
        StoreRepository storeRep = Repositories.getInstance().getStoreRepository();
        int i = 1;
        System.out.println("=======================");
        for (Store store : storeRep.getStores()) {
            System.out.println(i + " - " + store.getDesignation());
            System.out.println("----------------------");
            i++;
        }
        System.out.println("=======================");
    }


}
