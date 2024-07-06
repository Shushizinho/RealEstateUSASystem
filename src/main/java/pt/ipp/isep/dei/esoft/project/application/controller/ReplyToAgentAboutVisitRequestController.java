package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.EmailFilter;
import pt.ipp.isep.dei.esoft.project.domain.GmailFilter;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VisitRequestRepository;

import java.util.List;

/**
 * The type Reply to agent about visit request controller.
 */
public class ReplyToAgentAboutVisitRequestController {

    private static final String filterGmail = "filter.term.gmail";

    private VisitRequestRepository visitRequestRepository = null;

    private ClientRepository clientRepository = null;

    /**
     * Instantiates a new Reply to agent about visit request controller.
     */
    public ReplyToAgentAboutVisitRequestController() {
        getvisitRequestRepository();
        getVisitRequest();
        getClientList();
    }

    /**
     * Retrieves the VisitRequestRepository instance.
     *
     * @return the VisitRequestRepository instance.
     */
    public VisitRequestRepository getvisitRequestRepository() {
        if (visitRequestRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the VisitRequestRepository
            visitRequestRepository = repositories.getVisitRequestRepository();
        }
        return visitRequestRepository;
    }


    /**
     * Retrieves a list of accepted visit requests.
     *
     * @return The list of accepted visit requests.
     */
    public List<VisitRequest> getVisitRequest(){
        return getvisitRequestRepository().getVisitRequestAccepted();
    }

    /**
     * Creates and sends an email using the specified Gmail filter.
     *
     * @param filterGmail The Gmail filter to be used for creating the email.
     * @param sender      The email address of the sender.
     * @param receiver    The email address of the receiver.
     * @param subject     The subject of the email.
     * @param message     The content of the email.
     */
    public void createEmail (String filterGmail, String sender, String receiver, String subject, String message){
        EmailFilter gmailFilter = new GmailFilter(filterGmail);
        gmailFilter.createEmail(sender, receiver, subject, message);
    }

    /**
     * Retrieves the client repository instance.
     *
     * @return The client repository.
     */
    public ClientRepository getClientRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }

    /**
     * Retrieves the list of clients from the client repository.
     *
     * @return The list of clients.
     */
    public List<Client> getClientList() {
        ClientRepository clientRepository = getClientRepository();
        return clientRepository.getClients();
    }

    /**
     * Sets the client reply for the specified visit request.
     *
     * @param visitRequest The visit request for which the client reply is to be set.
     * @param answer       The client's reply.
     */
    public void setVisitRequestClientReply(VisitRequest visitRequest, String answer) {
        visitRequest.setClientReply(answer);
    }


    /**
     * Lookup element by id t.
     *
     * @param <T>    the type parameter
     * @param parent the parent
     * @param id     the id
     * @return the t
     */
    public <T extends Node> T lookupElementById(Parent parent, String id) {
        return (T) parent.lookup("#" + id);
    }

    /**
     * Send email to agent.
     *
     * @param visitRequest the visit request
     */
    public void sendEmailToAgent(VisitRequest visitRequest) {

        String clientEmail = null;
        List<Client> clients = getClientList();
        for (Client client : clients){
            if (client.getPhoneNumber() == visitRequest.getClientPhoneNumber()){
                clientEmail = client.getEmail();
            }
        }
        String subject = "This visit request was seen by client";

        String mesage = "This visit request" + visitRequest.toString() + "was displayed for the client";

        createEmail(filterGmail,visitRequest.getAgent().getEmail(), clientEmail, subject, mesage);

    }
}


