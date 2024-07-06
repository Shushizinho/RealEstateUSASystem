package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Agent ui.
 */
public class AgentUI implements Runnable {
    /**
     * Instantiates a new Agent ui.
     */
    public AgentUI() {
        }

        public void run() {
            List<MenuItem> options = new ArrayList<MenuItem>();
            //options.add(new MenuItem("Create Task", new CreateTaskUI()));
            options.add(new MenuItem("Create new Announcement ", new CreateSaleAnnouncementUI()));

            options.add(new MenuItem("List of all announcement requests ", new AnnouncementRequestUI()));

            options.add(new MenuItem("List of Buy orders ", new PurchaseOrdersUI()));

            options.add(new MenuItem("List of booking requests ", new ListBookingRequestsUI()));



            int option = 0;
            do {
                option = Utils.showAndSelectIndex(options, "Agent Menu");

                if ((option >= 0) && (option < options.size())) {
                    options.get(option).run();
                }
            } while (option != -1);
        }
    }

