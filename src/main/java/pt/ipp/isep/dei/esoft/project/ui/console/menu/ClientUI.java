package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.CreateVisitRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.PlaceOrderUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ReplyToAgentAboutVisitRequestUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.console.CreatePropertyUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.ReplyVisitRequestUI;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Client ui.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class ClientUI implements Runnable {
    /**
     * Instantiates a new Client ui.
     */
    public ClientUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        //options.add(new MenuItem("Create Task", new CreateTaskUI()));
//        options.add(new MenuItem("Register a new Employee ", new RegisterEmployeeUI()));

//        options.add(new MenuItem("Show Pending Requests", new GetPropertyListUI()));
        options.add(new MenuItem("Create a request for listing a Property", new CreatePropertyUI()));
        options.add(new MenuItem("Create a visit request for listed Property", new CreateVisitRequestUI()));
        options.add(new MenuItem("Create an Order for a Property listed to be Sold", new PlaceOrderUI()));
        options.add(new MenuItem("Reply to accepted visit requests", new ReplyVisitRequestUI()));
//        options.add(new MenuItem("Option 3 ", new ShowTextUI("You have chosen Option C.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "Client Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
