package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RespondVisitRequestController;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Visit request ui.
 */
public class RespondVisitRequestUI implements Runnable{

    private static final String filterGmail = "filter.term.gmail";

//    Configuration config = new Configuration();
//    config.loadConfig();
//    Configuration config = Configuration.getInstance();
//    Properties props = config.getProperties();
//    if (props.getProperty(Configuration.getFilterTermGmail()).compareTo("@gmail.com") == 0){
//
//    }


        //    List<String> data = Arrays.asList("example@gmail.com", "test@hotmail.com", "user@domain.com", "another@sapo.pt");

//    EmailFilter gmailFilter = new GmailFilter(filterGmail);
//    List<String> filteredGmail = gmailFilter.filter(data);
//    System.out.println("Filtered Gmail: " + filteredGmail);

    private final RespondVisitRequestController controller = new RespondVisitRequestController();

    private RespondVisitRequestController getController() {
        return controller;
    }

    /**
     * The Visit requests.
     */
    List<VisitRequest> visitRequests = controller.getVisitRequests();

    public void run() {

        displayVisitRequests();

        selectRequest();



    }

    /**
     * Display visit requests.
     */
    public void displayVisitRequests(){
        System.out.println("\u001B[36m#=======Visit Request List=======#");
        for (int i = 0; i < visitRequests.size(); i++) {
            System.out.printf("\u001B[0m%d. %s%n", i+1, visitRequests.get(i).toString());
        }
        if (visitRequests.isEmpty()) System.out.println("\u001B[33m There are no visit requests to list");

        System.out.println();
        System.out.println("\u001B[0m 0. Cancel");
        System.out.println();
    }

    private void selectRequest() {

        boolean validInput = false;
        String subject = null;
        String message = null;

        while (!validInput) {

            String numVis = null;
            try {
                numVis = Utils.readLineFromConsole("Select the visit request:");
                assert numVis != null;
                if (!numVis.equals("0")){
                    if(isValidInput(numVis)) {
                        VisitRequest visitRequest = visitRequests.get(Integer.parseInt(numVis)-1);

                        String clientEmail = null;
                        List<Client> clients = controller.getClientList();
                        for (Client client : clients){
                            if (client.getPhoneNumber() == visitRequest.getClientPhoneNumber()){
                                clientEmail = client.getEmail();
                            }
                        }

                        validInput = true;
                        if(askVisit().equalsIgnoreCase("Accept")){
                            visitRequest.acceptVisitRequest();
                            subject = "Your visit request was accepted";
                            message = Utils.readLineFromConsole("Writes the message you want to send to the client: ");

                            getController().createEmail(visitRequest.getAgent().getEmail(), clientEmail , subject, message);

//                            EmailFilter gmailFilter = new GmailFilter(filterGmail);
//                            gmailFilter.createEmail(visitRequest.getAgent().getEmail(), clientEmail , subject, message);
                        } else visitRequest.declineVisitRequest();

                    }
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid selection. " + e.getMessage() + " Please try again. ");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid input.");
            }
        }

    }

    /**
     * Ask visit string.
     *
     * @return the string
     */
    public String askVisit(){
        String answer = Utils.readLineFromConsole("Do you want to accept or decline the visit request?(Accept/Decline): ");

        while(answer.trim().isEmpty() || (!answer.equalsIgnoreCase("Accept") && !answer.equalsIgnoreCase("Decline"))){
            System.out.println("Please enter Accept or Decline");
            answer = Utils.readLineFromConsole("Do you want to accept or decline the visit request?(Accept/Decline): ");
        }
        return answer;
    }

    /**
     * Is valid input boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean isValidInput(String input){
        String numRegex = "^[0-9]$";
        Pattern pattern = Pattern.compile(numRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
