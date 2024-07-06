package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DivideStoresController;
import pt.ipp.isep.dei.esoft.project.domain.Pair;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Divide stores ui.
 */
public class DivideStoresUI implements Runnable {

    private final DivideStoresController controller = new DivideStoresController();

    private DivideStoresController getController() {
        return controller;
    }

    private StoreRepository storeRepository = new StoreRepository();

    /**
     * The Store dto.
     */
    List<StoreDTO> storeDTO = getController().getStores();
    /**
     * The N.
     */
    int n=0;

    public void run() {

        int number = numberStores();

        displayStore();

        List<StoreDTO> selectedStoresList = selectStore(storeDTO,number);

        List<Integer> numberPropertiesList = getPropertyNumberInEach(selectedStoresList);
        getController().divideStores(selectedStoresList,numberPropertiesList);
    }



    private List<Integer> getStoreIDsList(List<StoreDTO> selectedStoresList) {
        return controller.getStoreIDsList(selectedStoresList);
    }

    private int numberStores(){

        boolean validInput = false;

        while (!validInput){
            try {
                n = Integer.parseInt(Utils.readLineFromConsole("Select how many stores: "));

                if(n > storeDTO.size() || n <= 0){
                    throw new IllegalArgumentException("Invalid selection. Please try again.");
                } else {
                    validInput = true;
                }

            }catch (IllegalArgumentException e) {
                System.out.println("Invalid selection. " + e.getMessage() + " Please try again.");
            }
        }
        return n;
    }

    private void displayStore(){

        int i = 1;

        System.out.println("=== List ===");
        System.out.println("=======================");

        for (StoreDTO store : storeDTO){
            System.out.println(i + " - " + store.getDesignation());
            System.out.println("----------------------");
            i++;

//            if (i > n) {
//                break;
//            }
        }
        System.out.println("=======================");

    }
    private List<Integer> getPropertyNumberInEach(List<StoreDTO> selectedStores) {
        return controller.getPropertyNumberInEach(selectedStores);
    }
    private List<StoreDTO> selectStore(List<StoreDTO> store, int n){
        StoreDTO storeDTO = null;
        List<StoreDTO> selectedStoresList = new ArrayList<>();
        boolean validInput;

        storeRepository = Repositories.getInstance().getStoreRepository();
        while (selectedStoresList.size() < n) {
            validInput = false;
            while (!validInput) {

                try {
                    int input = Integer.parseInt(Utils.readLineFromConsole("Select Store: "));

                    if (input >= 1 && input <= store.size() && !selectedStoresList.contains(store.get(input - 1))) {
                        storeDTO = store.get(input - 1);

                        validInput = true;
                    } else {
                        throw new IllegalArgumentException("Invalid selection. Please try again.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid selection. " + e.getMessage() + " Please try again.");
                }
            }
            selectedStoresList.add(storeDTO);
        }
        return selectedStoresList;
    }
}
