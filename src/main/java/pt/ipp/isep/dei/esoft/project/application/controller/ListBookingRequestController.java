package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type List booking request controller.
 */
public class ListBookingRequestController {

    private VisitRequestRepository visitRequestRepository = null;
    private UserSession currentSession = null;
    private AuthenticationRepository authenticationRepository = null;
    private EmployeeRepository employeeRepository = null;
    private ClientRepository clientRepository = null;

    private static final String filterGmail = "filter.term.gmail";
    private static final String filterHotmail = "filter.term.hotmail";
    private static final String filterYahoo = "filter.term.yahoo";



    /**
     * Instantiates a new List booking request controller.
     */
    public ListBookingRequestController(){
        getVisitRequestRepository();
        getEmployeeRepository();
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

    private ClientRepository getClientRepository(){
        if(clientRepository == null){
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }

    /**
     * Gets visit request repository.
     *
     * @return the visit request repository
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
     * Gets agent from session.
     *
     * @return the agent from session
     */
    public Agent getAgentFromSession() {
        if (currentSession == null) {
            ApplicationSession appSession = ApplicationSession.getInstance();

            //Get the current session
            currentSession = appSession.getCurrentSession();
        }
        String email = currentSession.getUserEmail();

        return getEmployeeRepository().getEmployeeByEmail(email).getAssociatedAgent();

    }


    private EmployeeRepository getEmployeeRepository(){
        if(employeeRepository == null){
            Repositories repositories = Repositories.getInstance();
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
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
     * Get visit requests list.
     *
     * @param email the email
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    public List<VisitRequest> getVisitRequests(String email, DateTime begin, DateTime end){

        return getVisitRequestRepository().getVisitRequestsByAgentInPeriod(email,begin,end);
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
     * Get visit requests by agent list.
     *
     * @param visitRequestsListByAgent the visit requests list by agent
     * @param begin                    the begin
     * @param end                      the end
     * @return the list
     */
    public List<VisitRequestDTO> getVisitRequestsByAgent(String visitRequestsListByAgent, DateTime begin, DateTime end ){


        List <VisitRequest> visitRequestList= getVisitRequests(visitRequestsListByAgent, begin, end);

        return VisitRequestMapper.getVisitRequestsDTO(visitRequestList);

    }

    /**
     * Set accepted visit request.
     *
     * @param visitRequestDTO the visit request
     */
    public void setAcceptedVisitRequest(VisitRequestDTO visitRequestDTO){
        visitRequestDTO.acceptVisitRequest();
    }

    /**
     * Set decline visit request.
     *
     * @param visitRequestDTO the visit request
     */
    public void setDeclineVisitRequest(VisitRequestDTO visitRequestDTO){
        visitRequestDTO.declineVisitRequest();
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

        String[] partEmail = receiver.split("@");
        if (partEmail[1].equals("gmail.com")) {
            EmailFilter gmailFilter = new GmailFilter(filterGmail);
            gmailFilter.createEmail(sender, receiver , subject, message);
        } else if (partEmail[1].equals("hotmail.com")) {
            EmailFilter hotmailFilter = new HotmailFilter(filterHotmail);
            hotmailFilter.createEmail(sender, receiver , subject, message);
        } else if (partEmail[1].equals("yahoo.com")) {
            EmailFilter yahooFilter = new YahooFilter(filterYahoo);
            yahooFilter.createEmail(sender, receiver , subject, message);
        }

    }


    /**
     * Get date date time.
     *
     * @param datePicker the date picker
     * @param myLabel    the my label
     * @return the date time
     */
    @FXML
    public DateTime getDate(DatePicker datePicker, Label myLabel){

        LocalDate date = datePicker.getValue();
        String myFormattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String[] dateParts = myFormattedDate.split("-");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        int day1 = Integer.parseInt(day);
        int month1 = Integer.parseInt(month);
        int year1 = Integer.parseInt(year);

        DateTime dateTime = new DateTime(day1, month1, year1);

        myLabel.setText("Begin date: " + myFormattedDate);

        return dateTime;

    }

    /**
     * Get date end date time.
     *
     * @param datePicker the date picker
     * @param myLabel    the my label
     * @return the date time
     */
    @FXML
    public DateTime getDateEnd(DatePicker datePicker, Label myLabel){

        LocalDate date = datePicker.getValue();
        String myFormattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        String[] dateParts = myFormattedDate.split("-");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];



        int day1 = Integer.parseInt(day);
        int month1 = Integer.parseInt(month);
        int year1 = Integer.parseInt(year);

        DateTime dateTime = new DateTime(day1, month1, year1);

        myLabel.setText("End date: " + myFormattedDate);
        return dateTime;

    }



}
