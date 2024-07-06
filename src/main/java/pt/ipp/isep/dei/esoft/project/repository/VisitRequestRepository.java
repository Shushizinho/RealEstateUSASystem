package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * The type Visit request repository.
 */
public class VisitRequestRepository {

    private final List<VisitRequest> visitRequests = new ArrayList<>();
    private SortingAlgorithm sortingAlgorithm;


    /**
     * This method allow to add a visit request to the visit request list.
     *
     * @param visitRequestDTO to add at the list.
     * @return an Optional containing the new visit request if the addition is successful.
     */
    public Optional<VisitRequest> add(VisitRequestDTO visitRequestDTO) {

        Optional<VisitRequest> newVisitRequest = Optional.empty();
        boolean operationSuccess = false;


        if (validateVisitRequest(visitRequestDTO)) {
            newVisitRequest = Optional.of(VisitRequestMapper.toEntity(visitRequestDTO.clone()));
            VisitRequest v = newVisitRequest.get();
            operationSuccess = visitRequests.add(v);
        }

        if (!operationSuccess) {
            newVisitRequest = Optional.empty();
        }

        return newVisitRequest;
    }

    /**
     * This method verify if the visit request was already add.
     *
     * @param visitRequestDTO to validate.
     * @return true if the visitRequest isn't in the list of visitRequests.
     */
    public boolean validateVisitRequest(VisitRequestDTO visitRequestDTO) {
        boolean wasAccepted = !visitRequests.contains(VisitRequestMapper.toEntity(visitRequestDTO));
        return wasAccepted;
    }

    /**
     * Get visit request accepted list.
     *
     * @return the list
     */
    public List<VisitRequest> getVisitRequestAccepted(){
        List<VisitRequest> visitRequestList = new ArrayList<>();
        for (VisitRequest v: visitRequests) {
            if (v.accepted() == 1){
                visitRequestList.add(v);
            }
        }
        return visitRequestList;
    }

    /**
     * Create visit request.
     *
     * @param visitRequestDTO the visit request dto
     */
    public static void CreateVisitRequest(VisitRequestDTO visitRequestDTO){
        VisitRequest visitRequest = VisitRequestMapper.toEntity(visitRequestDTO);
    }

    /**
     * This method returns a defensive (immutable) copy of the list of visit requests.     *
     *
     * @return The list of visitRequest objects.
     */
    public List<VisitRequest> getVisitRequests() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(visitRequests);
    }


/*
   public List<VisitRequest> getVisitRequestsByAgent(String email){
       EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
       Employee employee;
       Agent agent;

       try {
           employee = employeeRepository.getEmployeeByEmail(email);
           agent = employeeRepository.getAgentObject(employee);

       } catch (Exception e) {
           throw new IllegalArgumentException("Error getting agent information: " + e.getMessage());
       }

       try {
           if (visitRequests.isEmpty()){

               throw new IllegalStateException("There are no visit requests available.");
           }
       } catch (IllegalStateException e) {

           System.out.println (e.getMessage());

       }

       List<VisitRequest> agentVisitRequests = new ArrayList<>();
       try {
           for (VisitRequest visitRequest : visitRequests) {
               if (visitRequest.getAgent().equals(agent) ) {
                   agentVisitRequests.add(visitRequest);
               }
           }


       }
       catch (Exception e) {
           throw new IllegalStateException("Error processing visit requests: " + e.getMessage());
       }




       return agentVisitRequests;
   }



public  List<VisitRequest> getVisitRequestsByAgentInPeriod(String email, DateTime begin, DateTime end){
        List <VisitRequest> visitRequestList = getVisitRequestsByAgent(email);
        List<VisitRequest> visitRequestsInPeriod =  new ArrayList<>();

        for( VisitRequest visitRequest : visitRequestList){
            if(visitRequest.getPreferredDate().isAfter(begin) && visitRequest.getPreferredDate().isBefore(end)|| visitRequest.getPreferredDate().isEqual(begin) || visitRequest.getPreferredDate().isEqual(end) ){

                visitRequestsInPeriod.add(visitRequest);
            }



        }


return  visitRequestsInPeriod;
*/


    /**
     * Gets visit requests by agent in period.
     *
     * @param email the email
     * @param begin the begin
     * @param end   the end
     * @return the visit requests by agent in period
     */
    public List<VisitRequest> getVisitRequestsByAgentInPeriod(String email, DateTime begin, DateTime end) {
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        Employee employee;
        Agent agent;
        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();

        List<Announcement> announcements = announcementRepository.getAnnouncements();

        try {
            employee = employeeRepository.getEmployeeByEmail(email);
            agent = employeeRepository.getAgentObject(employee);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error getting agent information: " + e.getMessage());
        }

        List<VisitRequest> agentVisitRequests = new ArrayList<>();
        List<VisitRequest> visitRequestList = getVisitRequests();
        try {
            for (VisitRequest visitRequest : visitRequestList) {
                for (Announcement announcement : announcements) {

                    if (visitRequest.getProperty().equals(announcement.getProperty())) {

                         Agent agent1 = announcement.getAgent();

                        if (agent.equals(agent1) && (
                                (visitRequest.getPreferredDate().isAfterOrEqual(begin) && visitRequest.getPreferredDate().isBeforeOrEqual(end)) ||
                                visitRequest.getPreferredDate().isEqual(begin) ||
                                visitRequest.getPreferredDate().isEqual(end)) ) {
                            {
                                agentVisitRequests.add(visitRequest);
                            }
                        }
                    }
                }
            }



        } catch (Exception e) {
            throw new IllegalStateException("Error processing visit requests: " + e.getMessage());
        }
    try {
        if (agentVisitRequests.isEmpty()) {
            throw new IllegalStateException("There are no visit requests available.");
        }
    }catch (IllegalStateException e){
        System.out.println(("There are no visit requests available."));
    }
    configureSortingAlgorithm();
    sortingAlgorithm.sort(agentVisitRequests);
        return agentVisitRequests;
    }

    /**
     * Create email.
     *
     * @param sender    the sender
     * @param recipient the recipient
     * @param subject   the subject
     * @param message   the message
     */
    public void createEmail(String sender, String recipient, String subject, String message) {
        String fileName = sender + "_" + recipient + ".txt";
        String fileContent = "From: " + sender + "\n" + "Recipient: " + recipient + "\n" + "Subject: " + subject + "\n\n" + message;

        String directory = "src\\main\\resources\\passwords\\";

        try {
            File file = new File(directory, fileName);

            FileWriter writer;
            if (file.exists()) {
                writer = new FileWriter(file, true);
            } else {
                writer = new FileWriter(file);
            }

            writer.append("\n\n");
            writer.write(fileContent);
            writer.close();
            System.out.println("=====================");
            System.out.println("E-mail was sent successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the e-mail.");
            e.printStackTrace();
        }
    }


    /**
     * Configure sorting algorithm.
     */
    public void configureSortingAlgorithm() {
        final String CONFIGURATION_FILENAME = "config.properties";
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(CONFIGURATION_FILENAME);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String sortingAlgorithmName = properties.getProperty("SortingAlgorithm.Default");
    try {
        if (sortingAlgorithmName.equals("InsertionSort")) {
            sortingAlgorithm = new InsertionSort();
        } else if (sortingAlgorithmName.equals("BubbleSort")) {
            sortingAlgorithm = new BubbleSort();
        } else {

            throw new IllegalArgumentException("Invalid sorting algorithm specified in configuration.");
        }

    }catch (IllegalArgumentException e) {
        throw new IllegalStateException("Error configuring sorting algorithm.", e);
    }
    }







}

































