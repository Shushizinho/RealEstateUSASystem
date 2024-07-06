package pt.ipp.isep.dei.esoft.project.ui.console.menu;
import pt.ipp.isep.dei.esoft.project.ui.gui.DivideStoresGUI;


import pt.ipp.isep.dei.esoft.project.ui.console.DivideStoresUI;
import pt.ipp.isep.dei.esoft.project.ui.console.GetDealsListUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ListEmployeesEveryStoreUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.ListAllDealsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Store network manager ui.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class StoreNetworkManagerUI implements Runnable {
    /**
     * Instantiates a new Store network manager ui.
     */
    public StoreNetworkManagerUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("List all employees working in every store\n" + "of the network", new ListEmployeesEveryStoreUI()));

        options.add(new MenuItem("List of all deals", new ListAllDealsUI()));

        options.add(new MenuItem("Divide stores", new DivideStoresGUI()));
//        options.add(new MenuItem("Divide Stores \u001B[34m[Console]", new DivideStoresUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "Network Manager Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
