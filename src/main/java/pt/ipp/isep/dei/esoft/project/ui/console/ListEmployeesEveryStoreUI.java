package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListEmployeeEveryStoreController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.EmployeeDTO;
import pt.ipp.isep.dei.esoft.project.dto.RoleDTO;
import pt.ipp.isep.dei.esoft.project.dto.StoreDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.*;

/**
 * Register Employee UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListEmployeesEveryStoreUI implements Runnable {

    private final ListEmployeeEveryStoreController controller = new ListEmployeeEveryStoreController();
    private ListEmployeeEveryStoreController getController() {
        return controller;
    }


    public void run() {

        System.out.println("\u001B[36m#=======List of All Stores=======#\n\u001B[0m");

        displayList();
    }

    private void displayList() {
        //display the task categories as a menu with number options to select

        List<StoreDTO> storeListSorted = controller.getStoresOrderByProperty();




        for (StoreDTO storeDTO : storeListSorted ) {

            if (storeDTO.getId() == 0) continue;

            int i = 1;

            List<EmployeeDTO> employeesList = controller.getEmployeeFromSpecificStore(storeDTO);
            int numberOfProperties  = controller.getNumberPropertiesFromSpecificStore(storeDTO);



//            if(employeesList.size() >= 0){
//            if(propertyList.size() >= 0){
                System.out.println("\u001B[34m== "+ storeDTO.getId() + " - " + storeDTO.getDesignation() + " -- Total of Propertys: " + numberOfProperties+ " ==\u001B[0m" );

                for (EmployeeDTO empl: employeesList ) {
                    String rolestext="";
                    for (RoleDTO role: empl.getRole() ) {
                        rolestext+="| "+role.getDescription() + " ";
                    }
                    System.out.println("\u001B[0m" + i + "."+empl.toString() + "- Roles:" + rolestext + "|" + " - Store: "+ empl.getStore().getDesignation() + "\u001B[0m");
                    i++;
                }

                System.out.println();
//            }

        }

//        System.out.print("\u001B[36m#" + "=".repeat(30) + "#\n\u001B[0m");

    }
}
