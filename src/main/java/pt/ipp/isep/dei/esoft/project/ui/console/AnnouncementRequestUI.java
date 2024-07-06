package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.AnnouncementRequestDTO;
import pt.ipp.isep.dei.esoft.project.dto.DateTimeDTO;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.*;

/**
 * The type Announcement request ui.
 */
public class AnnouncementRequestUI implements Runnable {

    private final AnnouncementRequestController controller = new AnnouncementRequestController();
    /**
     * The Scanner.
     */
    static Scanner scanner = new Scanner(System.in);
  //  List<AnnouncementRequest> announcementList = Repositories.getInstance().getAnnouncementRequestRepository().getAnnouncementRequests();

    /**
     * The Authentication.
     */
    AuthenticationRepository authentication = Repositories.getInstance().getAuthenticationRepository();

    /**
     * The Agent 1.
     */
    Agent agent1= getController().getAgentFromSession();


    private AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();

    /**
     * The Commission.
     */
    double commission = 0.0;
    /**
     * The Listed price.
     */
    double listedPrice = 0.0;

    /**
     * The Date.
     */
    DateTimeDTO date;

    /**
     * The Announcement list dto.
     */
    List<AnnouncementRequestDTO> announcementListDTO = getController().getAnnouncementsRequestsByAgent(agent1.getEmail());

    /**
     * The Business type description.
     */
    String BusinessTypeDescription;

    private AnnouncementRequestController getController() {
        return controller;
    }



    public void run() {

        System.out.println("List of all announcement requests:");

            displayAnnouncementRequests(agent1.getEmail());




            selectAnnouncement(announcementListDTO);



    }


    private void displayAnnouncementRequests(String email) {
        int index =1;

        AnnouncementRepository announcementRepository = Repositories.getInstance().getAnnouncementRepository();

        for (AnnouncementRequestDTO announcementRequest : announcementListDTO) {

                if(!announcementRequest.isAccepted() ){
                    System.out.println("[" + index + "] " + announcementRequest.toStringDTO());
                    System.out.println("------------------");
                    index++;

                }

        }

         getController().getAnnouncementsRequestsAgent(email);


    }


    /**
     * Select announcement.
     *
     * @param announcementList the announcement list
     */
    public void selectAnnouncement(List<AnnouncementRequestDTO> announcementList) {
        int selectedAnnouncementIndex;
        boolean valid = false;
        String subject = "Announcement Request was declined";


     announcementRequestRepository = Repositories.getInstance().getAnnouncementRequestRepository();



        if(options() == 1) {


            while (!valid) {


                try {

                    System.out.print("Select an announcement (enter the corresponding number): ");

                    int input = scanner.nextInt();
                    scanner.nextLine();


                    selectedAnnouncementIndex = input;

                    if (selectedAnnouncementIndex >= 1 && selectedAnnouncementIndex <= announcementList.size()) {
                        AnnouncementRequestDTO selectedAnnouncement = announcementList.get(selectedAnnouncementIndex - 1);


                        valid = true;

                        if (askAnnouncement().equalsIgnoreCase("Yes")) {

                            createAnnouncement(selectedAnnouncement);
                            getController().setAcceptedAnnouncementRequest(selectedAnnouncement, true);






                        } else {
                            System.out.print("Insert the message: ");
                            String message = scanner.nextLine();
                            getController().createEmail(selectedAnnouncement.getAgent().getEmployee().getEmail(), selectedAnnouncement.getClient().getEmail(), subject, message);
                            getController().setAcceptedAnnouncementRequest(selectedAnnouncement, true);


                        }
                    } else {
                        throw new IllegalArgumentException("Invalid selection. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid index. Please try again.");
                }catch (InputMismatchException e){
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                }
            }

        }




    }


    /**
     * Ask announcement string.
     *
     * @return the string
     */
    public String askAnnouncement() {

        System.out.print("Do you want to publish the announcement request?(Yes/No): ");

        String answer = scanner.next();

        while (answer.trim().isEmpty() || (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No"))) {
            System.out.println("Please enter yes or no");
            System.out.print("Do you want to publish the announcement request?(Yes/No): ");
            answer = scanner.next();
        }

        return answer;


    }




    private void createAnnouncement(AnnouncementRequestDTO announcementRequestDTO) {
        System.out.println("=== Create Announcement ===");

        Optional<Announcement> announcement = null;

        String option = askCommission();
        double commission = requestAnnouncementCommission(option);


                //commission = requestAnnouncementCommission();





            if (option.equalsIgnoreCase("Percentage")) {
                double percentage = commission / 100;
                listedPrice = announcementRequestDTO.getProperty().getPrice() * percentage + announcementRequestDTO.getProperty().getPrice();


            } else {
                listedPrice = announcementRequestDTO.getProperty().getPrice() + commission;


            }
        announcementRequestDTO.setListedPrice(listedPrice);


        date = new DateTimeDTO();

        announcementRequestDTO.setDate(date);



        try {

            boolean commissionConfirmed = confirmCommission(commission);
            if (commissionConfirmed) {
                announcement = getController().createAnnouncement(announcementRequestDTO,commission);
                if (announcement.isPresent()) {
                    System.out.println("Announcement was created.");



                } else {
                    System.out.println("Announcement was not created.");
                }
            } else {
                commission= requestAnnouncementCommission(option);
                announcement = getController().createAnnouncement(announcementRequestDTO,commission);

                if (announcement.isPresent()) {
                    System.out.println("Announcement was created.");
                } else {
                    System.out.println("Announcement was not created.");
                }

            }
        } catch (Exception e) {
            System.out.println("\nError!");
            System.out.print(e.getMessage());
        }



        }










    private boolean confirmCommission(double commission) {
        System.out.print("Are you sure you want to set the commission to " + commission + "? (Yes/No): ");
        Scanner input = new Scanner(System.in);
        String confirmation = input.next();

        if (confirmation.equalsIgnoreCase("Yes")) {
            return true;
        } else if (confirmation.equalsIgnoreCase("No")) {
            return false;
        } else {
            throw new IllegalArgumentException("Invalid confirmation input. Please enter 'Yes' or 'No'.");
        }
    }


    /**
     * Options int.
     *
     * @return the int
     */
    public int options(){

        int answer;
        System.out.println("\u001B[0m1. Select the announcement");
        System.out.println("\u001B[0m0. Cancel\u001B[0m");


        while(true){

        try {
            System.out.print("\u001B[35m\nType your option: \u001B[0m");
            answer = scanner.nextInt();




            if (answer == 1 || answer == 0) {

                break;

            } else{
                System.out.println("\u001B[31mInvalid input. Please enter '1' or '0'.\u001B[0m");
            }

        }catch (InputMismatchException e){

            System.out.println("Invalid input. Please enter '1' or '0'. ");
            scanner.nextLine();
        }



        }

        return answer;


    }


    /**
     * Ask commission string.
     *
     * @return the string
     */
    public String askCommission() {
        String option;
        while (true) {


            System.out.print("Do you want to set a fixed value or a percentage for the commission? (Fixed/Percentage): ");
            option = scanner.next();

            if (!option.equalsIgnoreCase("Fixed") && !option.equalsIgnoreCase("Percentage")) {
                System.out.println("Invalid input Please enter 'Fixed' or 'Percentage'.");
            } else {

                break;
            }

        }

        return option;
    }

    /**
     * Request announcement commission double.
     *
     * @param option the option
     * @return the double
     */
    public double requestAnnouncementCommission(String option) {
        double commission = 0.0;

        if (option.equalsIgnoreCase("Fixed")) {
            System.out.print("Enter the fixed commission value: ");
            commission = scanner.nextDouble();
        } else if (option.equalsIgnoreCase("Percentage")) {
            System.out.print("Enter the commission percentage between 1 and 50: ");
            commission = scanner.nextDouble();
            if (commission < 1 || commission > 50) {
                System.out.println("Commission percentage must be between 1 and 50");
                commission = 0.0;
            }
        } else {
            System.out.println("Invalid input. Please enter 'Fixed' or 'Percentage'.");
        }

        return commission;
    }









}
