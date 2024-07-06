package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VisitRequestRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Respond visit request controller.
 */
public class RespondVisitRequestController {

    private AuthenticationRepository authenticationRepository = null;
    private VisitRequestRepository visitRequestRepository = null;
    private ClientRepository clientRepository = null;

    /**
     * Instantiates a new Respond visit request controller.
     */
    public RespondVisitRequestController() {
        getVisitRequestRepository();
        getAuthenticationRepository();
        getClientRepository();
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The AuthenticationRepository instance.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private VisitRequestRepository getVisitRequestRepository(){
        if(visitRequestRepository == null){
            Repositories repositories = Repositories.getInstance();
            visitRequestRepository = repositories.getVisitRequestRepository();
        }
        return visitRequestRepository;
    }

    private ClientRepository getClientRepository(){
        if(clientRepository == null){
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }

    /**
     * Get visit requests list.
     *
     * @return the list
     */
    public List<VisitRequest> getVisitRequests(){
        visitRequestRepository = getVisitRequestRepository();
        List<VisitRequest> v = visitRequestRepository.getVisitRequests();
        List<VisitRequestDTO> visitRequestDTOS = VisitRequestMapper.getVisitRequestsDTO(v);

        List<VisitRequest> visitRequests= new ArrayList<>();
        for (VisitRequestDTO visitRequestDTO : visitRequestDTOS) {
            visitRequests.add(VisitRequestMapper.toEntity(visitRequestDTO));
        }

        return visitRequests;

    }

    /**
     * Get client list list.
     *
     * @return the list
     */
    public List<Client> getClientList(){
        ClientRepository clientRepository = getClientRepository();
        return clientRepository.getClients();
    }

    /**
     * Creates and sends an email using the specified details.
     *
     * @param sender   The email address of the sender.
     * @param receiver The email address of the recipient.
     * @param subject  The subject of the email.
     * @param message  The content of the email.
     */
    public void createEmail(String sender, String receiver, String subject, String message){
        getVisitRequestRepository().createEmail(sender, receiver, subject, message);
    }

}
