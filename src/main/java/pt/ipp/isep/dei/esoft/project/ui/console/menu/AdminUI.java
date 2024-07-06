package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Admin ui.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class AdminUI implements Runnable {
    /**
     * Instantiates a new Admin ui.
     */
    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        //options.add(new MenuItem("Create Task", new CreateTaskUI()));
        options.add(new MenuItem("Register a new Employee ", new RegisterEmployeeUI()));

        options.add(new MenuItem("Register a new Store ", new RegisterStoreUI()));

        options.add(new MenuItem("List of employees", new ListEmployeesUI()));

        try {
            options.add(new MenuItem("Import information from a legacy system", new ImportLegacySystemUI()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//        options.add(new MenuItem("Option 3 ", new ShowTextUI("You have chosen Option C.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "Admin Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
