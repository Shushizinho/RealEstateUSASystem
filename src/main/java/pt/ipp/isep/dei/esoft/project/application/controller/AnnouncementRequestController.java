package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.dto.AnnouncementRequestDTO;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The type Announcement request controller.
 */
public class AnnouncementRequestController {


    private AnnouncementRequestRepository announcementRequestRepository=null;
    private BusinessTypeRepository businessTypeRepository = null;


    private  AnnouncementRepository announcementRepository;
    private UserSession currentSession = null;
    private EmployeeRepository employeeRepository = null;

    private AnnouncementRequestDTO announcementRequestDTO;

    private PropertyRepository propertyRepository = null;

    private AuthenticationRepository authenticationRepository = null;
    /**
     * The Address.
     */
    Address address= new Address("test","test","teste","teste");
    /**
     * The Employee.
     */
    Employee employee = new Employee("Diogo", "agent@this.app", 12321, "3213123", address ,121212, new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000), List.of(new Role(2, AuthenticationController.ROLE_AGENT)));


    /**
     * Constructs an AnnouncementRequestController with the specified AnnouncementRequestRepository.
     *
     * @param announcementRequestRepository the AnnouncementRequestRepository to be used by the controller
     * @param announcementRepository        the announcement repository
     * @param authenticationRepository      the authentication repository
     * @param currentSession                the current session
     */
    public AnnouncementRequestController(AnnouncementRequestRepository announcementRequestRepository, AnnouncementRepository announcementRepository, AuthenticationRepository authenticationRepository, UserSession currentSession) {
        this.announcementRequestRepository=announcementRequestRepository;
        this.announcementRepository=announcementRepository;
        this.authenticationRepository=authenticationRepository;
        this.currentSession=currentSession;
    }


    /**
     * Constructs an AnnouncementRequestController.
     * This constructor is used to initialize the controller without providing a specific AnnouncementRequestRepository.
     * It calls the method getAnnouncementRequestRepository() to obtain the repository instance.
     */
    public AnnouncementRequestController() {

       getAnnouncementRequestRepository();
       getAnnouncementRepository();
        getAuthenticationRepository();
        getAgentFromSession();


    }


    /**
     * Retrieves the AnnouncementRequestRepository instance.
     * @return The AnnouncementRequestRepository instance.
     */

    private AnnouncementRequestRepository getAnnouncementRequestRepository() {
        if (announcementRequestRepository== null) {
            Repositories repositories = Repositories.getInstance();


            announcementRequestRepository = repositories.getAnnouncementRequestRepository();
        }
        return announcementRequestRepository;
    }


    /**
     * Retrieves the AuthenticationRepository instance.
     * @return The AuthenticationRepository instance.
     */

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }


    /**
     * Retrieves the AnnouncementRepository instance.
     * @return The AnnouncementRepository instance.
     */
    private AnnouncementRepository getAnnouncementRepository() {
        if (announcementRepository== null) {
            Repositories repositories = Repositories.getInstance();


            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }

    /**
     * Retrieves and displays all the announcement requests.
     *
     * @param email the email
     * @return the list
     */
    public List <AnnouncementRequest> getAnnouncementsRequestsAgent(String email){


        return getAnnouncementRequestRepository().getAnnouncementRequestsByAgent(email);

    }

    /**
     * Get announcements requests by agent list.
     *
     * @return the list
     */
    public List<AnnouncementRequestDTO> getAnnouncementsRequestsByAgent(){

        String announcementRequestsListByAgent = getAgentFromSession().getEmail();

        List <AnnouncementRequest> announcementRequests= getAnnouncementsRequestsAgent(announcementRequestsListByAgent);

        return AnnouncementRequestMapper.getAnnouncementRequestsDTO(announcementRequests);

    }


    /**
     * Announcement request dto list to announcement request list list.
     *
     * @return the list
     */
    public List<AnnouncementRequest> announcementRequestDTOListToAnnouncementRequestList(){
        List <AnnouncementRequestDTO> announcementRequests= getAnnouncementsRequestsByAgent();

        return AnnouncementRequestMapper.getAnnouncementRequests(announcementRequests);

    }


    /**
     * Create announcement optional.
     *
     * @param announcementRequestDTO the announcement request dto
     * @param commission             the commission
     * @return the optional
     */
    public Optional<Announcement> createAnnouncement(AnnouncementRequestDTO announcementRequestDTO, double commission){





        return getAnnouncementRepository().createAnnouncement(announcementRequestDTO,commission);
    }

    /**
     * Set accepted announcement request.
     *
     * @param announcementRequest the announcement request
     * @param a                   the a
     */
    public void setAcceptedAnnouncementRequest(AnnouncementRequestDTO announcementRequest , boolean a){
        announcementRequest.setAccepted(a);
    }


    /**
     * Creates a new rent announcement based on the provided announcement request DTO, commission, and rent duration.
     *
     * @param announcementRequestDTO The announcement request DTO containing the necessary information for the rent announcement.
     * @param commission             The commission for the rent announcement.
     * @return An optional containing the created rent announcement if it is successfully created, or an empty optional if the creation fails.
     */
    public Optional<RentAnnouncement> createRentAnnouncement(AnnouncementRequestDTO announcementRequestDTO, double commission){





        return getAnnouncementRepository().createRentAnnouncement(announcementRequestDTO,commission);
    }

    /**
     * Creates a new sale announcement based on the provided announcement request DTO, commission, and listed price.
     *
     * @param announcementRequestDTO The announcement request DTO containing the necessary information for the sale announcement.
     * @param commission             The commission for the sale announcement.
     * @param listedPrice            The listed price for the property.
     * @return An optional containing the created sale announcement if it is successfully created, or an empty optional if the creation fails.
     */
    public Optional<SaleAnnouncement> createSaleAnnouncement(AnnouncementRequestDTO announcementRequestDTO,double commission, double listedPrice){

        return getAnnouncementRepository().createSaleAnnouncement(announcementRequestDTO,commission,listedPrice);
    }


    /**
     * Removes the specified announcement request from the repository.
     *
     * @param announcementRequest The announcement request to be removed.
     * @param email               the email
     * @return {@code true} if the announcement request is successfully removed, {@code false} otherwise.
     */
    public  boolean remove(AnnouncementRequestDTO announcementRequest, String email) {
        return getAnnouncementRequestRepository().remove(announcementRequest,email);
    }


    /**
     * Creates an email with the specified sender, recipient, subject, and message.
     *
     * @param sender    The email address of the sender.
     * @param recipient The email address of the recipient.
     * @param subject   The subject of the email.
     * @param message   The content of the email.
     */
    public void createEmail(String sender, String recipient, String subject, String message){
     getAnnouncementRequestRepository().createEmail(sender, recipient, subject, message);
    }


    /**
     * Retrieves the agent (employee) associated with the current session.
     *
     * @return The agent retrieved from the current session, or null if no session is available or the agent is not found.
     */
    public Agent getAgentFromSession() {
        if (currentSession == null) {
            ApplicationSession appSession = ApplicationSession.getInstance();

            //Get the current session
            currentSession = appSession.getCurrentSession();
        }
        String email = currentSession.getUserEmail();
        //EmployeeRepository employeeRepository =Repositories.getInstance().getEmployeeRepository();
        return getEmployeeRepository().getEmployeeByEmail(email).getAssociatedAgent();

    }

    private void getAnnouncementRequestsByAgentSorted(List<AnnouncementRequest> announcementRequestsList, AnnouncementRequestComparator announcementRequestComparator){
        Collections.sort(announcementRequestsList,announcementRequestComparator);

    }



    private EmployeeRepository getEmployeeRepository(){
        if(employeeRepository == null){
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

//    /**
//     * Creates a new SaleAnnouncement with the specified details and adds it to the announcement repository.
//     *
//     * @param date         The formatted date of the announcement.
//     * @param commission   The commission amount for the sale.
//     * @param businessType The type of business associated with the sale.
//     * @param property     The property being sold.
//     * @param listedPrice  The listed price of the property.
//     * @param agent        The employee representing the agent associated with the sale.
//     * @return An Optional containing the newly created SaleAnnouncement, or an empty Optional if the creation failed.
//     */
//
//
//    public Optional<SaleAnnouncement> createSaleAnnouncement2(DateTime date, double commission, BusinessType businessType, Property property, double listedPrice, Agent agent){
//
//
//
//
//        return getAnnouncementRepository().createSaleAnnouncement2(date,commission,businessType,property,listedPrice,agent);
//
//
//    }
//
//    /**
//     * Creates a new RentAnnouncement with the specified details and adds it to the announcement repository.
//     *
//     * @param date         The formatted date of the announcement.
//     * @param commission   The commission amount for the rent.
//     * @param businessType The type of business associated with the rent.
//     * @param property     The property being rented.
//     * @param rentDuration The duration of the rent in months.
//     * @param agent        The employee representing the agent associated with the rent.
//     * @return An Optional containing the newly created RentAnnouncement, or an empty Optional if the creation failed.
//     */
//
//
//    public Optional<RentAnnouncement> createRentAnnouncement2(DateTime date, double commission, BusinessType businessType, Property property, Integer rentDuration, Agent agent){
//
//
//
//        return getAnnouncementRepository().createRentAnnouncement2(date,commission,businessType,property,rentDuration,agent);
//
//
//    }






}
