package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.AnalyseDealsUI;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Store manager ui.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class StoreManagerUI implements Runnable {
    /**
     * Instantiates a new Store manager ui.
     */
    public StoreManagerUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        //options.add(new MenuItem("Create Task", new CreateTaskUI()));
        options.add(new MenuItem("Analyse Deals", new AnalyseDealsUI()));


        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "Store Manager Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
