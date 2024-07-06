package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ReplyToAgentAboutVisitRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Reply to agent about visit request ui.
 */
public class ReplyToAgentAboutVisitRequestUI implements Runnable{

    private final ReplyToAgentAboutVisitRequestController controller = new ReplyToAgentAboutVisitRequestController();

    private static final String filterGmail = "filter.term.gmail";
    /**
     * The Visit requests.
     */
    List<VisitRequest> visitRequests = new ArrayList<>();


    public void run() {
        visitRequests = getVisitRequests();
        if (!visitRequests.isEmpty()){
            displayVisitRequest();

            VisitRequest visitRequest = requestVisitRequestToReply();

            sendEmailToAgent(visitRequest);

            if (visitRequest != null){
                if(replyToVisitRequest(visitRequest).equalsIgnoreCase("Decline" )&& !(replyToVisitRequest(visitRequest).equalsIgnoreCase("0"))){
                    WriteReasonToDecline(visitRequest);
                }
            }
        }else System.out.println("u001B[31mThere are no visit request accepted by the agent \u001B[0m");
    }

    /**
     * Get visit requests list.
     *
     * @return the list
     */
    public List<VisitRequest> getVisitRequests(){
        return controller.getVisitRequest();
    }

    /**
     * Display visit request.
     */
    public void displayVisitRequest(){
        System.out.println("\u001B[36m#=======Visit Request List=======#");
        for (int i = 0; i < visitRequests.size(); i++) {
            System.out.printf("\u001B[0m%d. %s%n", i+1, visitRequests.get(i).toStringComplete());
        }
    }

    /**
     * Request visit request to reply visit request.
     *
     * @return the visit request
     */
    public VisitRequest requestVisitRequestToReply(){
        while (true) {
            try {
                System.out.println("\u001B[33mWhich one of the previous visit request you wish to reply?");
                System.out.println("\u001B[0m 0. Cancel");

                int answer = Utils.readIntegerFromConsole("Enter Visit Request: ");
                if (answer == 0) return null;
                return controller.getVisitRequest().get(answer - 1);
            } catch (Exception e) {
                System.out.println("\u001B[41mError choose one of the visit request\u001B[0m");
            }
        }
    }

    /**
     * Send email to agent.
     *
     * @param visitRequest the visit request
     */
    public void sendEmailToAgent(VisitRequest visitRequest){
        String clientEmail = null;
        List<Client> clients = controller.getClientList();
        for (Client client : clients){
            if (client.getPhoneNumber() == visitRequest.getClientPhoneNumber()){
                clientEmail = client.getEmail();
            }
        }
        String subject = "This visit request was seen by client";

        String mesage = "This visit request" + visitRequest.toString() + "was displayed for the client";

        controller.createEmail(filterGmail,visitRequest.getAgent().getEmail(), clientEmail, subject, mesage);

    }

    /**
     * Reply to visit request string.
     *
     * @param visitRequest the visit request
     * @return the string
     */
    public String replyToVisitRequest(VisitRequest visitRequest) {
        while (true) {
            try {
                System.out.println("\u001B[33m Do you with to accept or decline this visit request?(Accept/Decline)");
                System.out.println("\u001B[0m 0. Cancel");

                String answer = Utils.readLineFromConsole("Enter reply: ");
                if (answer.equals("0")) return null;
                return answer;
            } catch (Exception e) {
                System.out.println("\u001B[41mError choose Accept or Decline\u001B[0m");
            }
        }
    }

    /**
     * Write reason to decline.
     *
     * @param visitRequest the visit request
     */
    public void WriteReasonToDecline(VisitRequest visitRequest){
        while (true) {
            try {
                System.out.println("\u001B[33m Why did you decline this visit request");
                System.out.println("\u001B[0m 0. Cancel");

                String answer = Utils.readLineFromConsole("Enter reply: ");
                assert answer != null;
                if (answer.equals("0")) return;
                controller.setVisitRequestClientReply(visitRequest, answer);
            } catch (Exception e) {
                System.out.println("\u001B[41mError choose Accept or Decline\u001B[0m");
            }
        }
    }
}
