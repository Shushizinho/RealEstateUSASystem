package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.DateTime;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;
import pt.ipp.isep.dei.esoft.project.dto.DateTimeDTO;
import pt.ipp.isep.dei.esoft.project.dto.PropertyDTO;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.repository.VisitRequestRepository;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;
import java.util.ArrayList;

import java.util.List;

/**
 * The type Create visit request controller.
 */
public class CreateVisitRequestController {
    private PropertyRepository propertyRepository = null;
    private UserSession currentSession = null;

    private VisitRequestRepository visitRequestRepository = null;


    /**
     * Instantiates a new Create visit request controller.
     */
    public CreateVisitRequestController(){
        getPropertyRepository();
        getClientFromSession();
        getVisitRequestRepository();
    }

    /**
     * Constructs a new CreateVisitRequestController with the given PropertyRepository and UserSession.
     *
     * @param propertyRepository the PropertyRepository instance
     * @param currentSession     the UserSession instance
     */
    public CreateVisitRequestController(PropertyRepository propertyRepository, UserSession currentSession) {
        this.propertyRepository = propertyRepository;
        this.currentSession = currentSession;
    }


    /**
     * Retrieves the list of properties based on announcements.
     *
     * @return the list of properties based on announcements
     */
    public List<Property> getPropertieListByAnnouncemnent(){
        return Repositories.getInstance().getAnnouncementRepository().getPropertyListByAnnouncements();
    }

    /**
     * Retrieves the PropertyRepository instance.
     *
     * @return the PropertyRepository instance
     */
    public PropertyRepository getPropertyRepository() {
        if (propertyRepository == null) {
            Repositories property = Repositories.getInstance();

            //Get the PropertyTypeRepository
            propertyRepository = property.getPropertyRepository();
        }
        return propertyRepository;
    }

    /**
     * Retrieves the Client object from the current session.
     *
     * @return the Client object from the current session
     */
    public Client getClientFromSession() {
        if (currentSession == null) {
            ApplicationSession appSession = ApplicationSession.getInstance();

            //Get the current session
            currentSession = appSession.getCurrentSession();
        }
        String email = currentSession.getUserEmail();
        ClientRepository clientRepository =Repositories.getInstance().getClientRepository();
        return clientRepository.getClientByEmail(email);
    }

    /**
     * Retrieves the list of visit requests.
     *
     * @return the list of visit requests
     */
    public List<VisitRequest> getVisitRequests(){
        visitRequestRepository = getVisitRequestRepository();
        List<VisitRequestDTO> visitRequestDTOS = VisitRequestMapper.getVisitRequestsDTO(visitRequestRepository.getVisitRequests());

        List<VisitRequest> visitRequests= new ArrayList<>();
        for (VisitRequestDTO visitRequestDTO : visitRequestDTOS) {
            visitRequests.add(VisitRequestMapper.toEntity(visitRequestDTO));
        }

        return visitRequests;
    }

    /**
     * Retrieves the list of visit requests.
     *
     * @return the list of visit requests
     */
    public VisitRequestRepository getVisitRequestRepository() {
        if (visitRequestRepository == null) {
            Repositories visitRequest = Repositories.getInstance();

            //Get the PropertyTypeRepository
            visitRequestRepository = visitRequest.getVisitRequestRepository();
        }
        return visitRequestRepository;
    }

    /**
     * Validates the visit request.
     *
     * @param visitRequestDTO the VisitRequestDTO to be validated
     * @return true if the visit request was accepted, false otherwise
     */
    public boolean ValidateVisitRequest(VisitRequestDTO visitRequestDTO){
        boolean wasAccepted = getVisitRequestRepository().validateVisitRequest(visitRequestDTO);
        return wasAccepted;
    }

    /**
     * Creates a visit request.
     *
     */
    public VisitRequest createVisitRequest(Property property, DateTime preferredDate, List<DateTime> timeSlot ){
        Client client = getClientFromSession();
        VisitRequestDTO visitRequestDTO = new VisitRequestDTO(new PropertyDTO(property), new DateTimeDTO(preferredDate.getDate()), timeSlot,client.getName(), client.getPhoneNumber(), 0 );
        visitRequestRepository.add(visitRequestDTO);
        return VisitRequestRepository.CreateVisitRequest(visitRequestDTO);

    }

}
