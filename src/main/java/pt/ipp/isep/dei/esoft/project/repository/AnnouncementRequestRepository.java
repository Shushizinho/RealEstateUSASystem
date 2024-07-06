package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.AnnouncementRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementRequestMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * A repository class for managing all Announcement Requests.
 */
public class AnnouncementRequestRepository {

    private final List <AnnouncementRequest> announcementRequests =new ArrayList<>();


    /**
     * Adds a new announcementRequest to the collection of announcementRequests managed by this repository.
     *
     * @param announcementRequest the announcementRequest to be added to the collection..
     * @return true if the property was successfully added, false otherwise.
     */
    public boolean add(AnnouncementRequest announcementRequest){

        Optional<AnnouncementRequest>  newSaleAnnouncement = Optional.empty();

        boolean operationSuccess = false;

        if (validateAnnouncementRequest(announcementRequest)) {
            newSaleAnnouncement = Optional.of(announcementRequest.clone());
            operationSuccess = announcementRequests.add(newSaleAnnouncement.get());
        }

        return operationSuccess;
    }
    /**
     * Validates if a given announcementRequest is valid to be added to the repository.
     *
     * @param announcementRequest The announcementRequest to be validated.
     * @return Returns true if the announcementRequest is valid to be added, false otherwise.
     */

    private boolean validateAnnouncementRequest(AnnouncementRequest announcementRequest) {
      for(AnnouncementRequest sa : announcementRequests){

          if(sa.equals(announcementRequest)){
              return false;
          }

      }
      return true;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of announcementRequests.
     *
     * @return The list of announcementRequests Types.
     */


    public List<AnnouncementRequest> getAnnouncementRequests(){
        return List.copyOf(announcementRequests);
    }

/*
    */

    /**
     * Lists all the announcement requests.
     * If there are no announcement requests available, an exception is thrown.
     * Otherwise, the announcement requests are printed to the console.
     *
     * @param email the email
     * @return the announcement requests by agent
     */
/*

    public List <AnnouncementRequest> getAnnouncementRequestsByAgent(String email) {


        try {

            if (announcementRequests.isEmpty()) {
                throw new IllegalStateException("There are no announcement requests available");
            }
            Collections.sort(announcementRequests,new AnnouncementRequestComparator());
            Collections.reverse(announcementRequests);

            int index =1;
            for (AnnouncementRequest announcementRequest : announcementRequests) {
                System.out.println("[" + index + "] " +announcementRequest );
                System.out.println("------------------");
                index++;
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        return announcementRequests;
    }
*/

public List<AnnouncementRequest> getAnnouncementRequestsByAgent(String email) {
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
       if (announcementRequests.isEmpty()){

           throw new IllegalStateException("There are no announcement requests available.");
       }
   } catch (IllegalStateException e) {

        System.out.println (e.getMessage());

    }


    List<AnnouncementRequest> agentAnnouncementRequests = new ArrayList<>();
        try {
            for (AnnouncementRequest announcementRequest : announcementRequests) {
                if (announcementRequest.getAgent().equals(agent)) {
                    agentAnnouncementRequests.add(announcementRequest);
                }
            }
            Collections.sort(agentAnnouncementRequests, new AnnouncementRequestComparator());
            Collections.reverse(agentAnnouncementRequests);

        }
        catch (Exception e) {
            throw new IllegalStateException("Error processing announcement requests: " + e.getMessage());
        }




    return agentAnnouncementRequests;
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
     * Create announcement request announcement request.
     *
     * @param rentDuration the rent duration
     * @param businessType the business type
     * @param property     the property
     * @param agent        the agent
     * @param client       the client
     * @return the announcement request
     */
    public AnnouncementRequest createAnnouncementRequest(Integer rentDuration, BusinessType businessType, Property property, Agent agent, Client client) {
        AnnouncementRequest announcementRequest = new AnnouncementRequest(rentDuration, businessType,property,agent,client);
        return (addAnnouncementRequest(announcementRequest)) ? announcementRequest : null;
}

    private boolean addAnnouncementRequest(AnnouncementRequest announcementRequest) {
        return announcementRequests.add(announcementRequest);
}

    /**
     * Create announcement request announcement request.
     *
     * @param businessType the business type
     * @param property     the property
     * @param agent        the agent
     * @param client       the client
     * @return the announcement request
     */
    public AnnouncementRequest createAnnouncementRequest(BusinessType businessType, Property property, Agent agent, Client client) {
        AnnouncementRequest announcementRequest = new AnnouncementRequest( businessType,property,agent,client);
        return (addAnnouncementRequest(announcementRequest)) ? announcementRequest : null;
}


    /**
     * Remove boolean.
     *
     * @param announcementRequest the announcement request
     * @param email               the email
     * @return the boolean
     */
    public  boolean remove(AnnouncementRequestDTO announcementRequest, String email) {

      AnnouncementRequest announcementRequest1=  AnnouncementRequestMapper.toEntity(announcementRequest);

      List<AnnouncementRequest> announcementRequestList = getAnnouncementRequestsByAgent(email);
        return announcementRequestList.remove(announcementRequest1);


    }



}


