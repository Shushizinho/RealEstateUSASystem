package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListBookingRequestController;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type List booking requests ui test.
 */
public class ListBookingRequestsUI implements Runnable {

    /**
     * The Controller.
     */
    ListBookingRequestController controller = new ListBookingRequestController();

    /**
     * The Visit requests.
     */
    List<VisitRequest> visitRequests = controller.getVisitRequests();


    /**
     * The Agent.
     */
    Agent agent =  controller.getAgentFromSession();

    /**
     * The Date formatted begin.
     */
    DateTime dateTimeBegin = new DateTime();
    /**
     * The Date formatted end.
     */
    DateTime dateTimeEnd = new DateTime();



    public void run() {
        dateTimeBegin =  requestBeginDate();
        dateTimeEnd = requestEndDate();
        int index = 1;

        List<VisitRequestDTO> visitRequestDTOS = controller.getVisitRequestsByAgent(agent.getEmail(), dateTimeBegin, dateTimeEnd);

        for(VisitRequestDTO visitRequestDTO : visitRequestDTOS){
            if ( visitRequestDTO.accepted() != 1 && visitRequestDTO.accepted() != -1) {
                System.out.println("\n[" + index + "]" + visitRequestDTO);
            }
//            System.out.println("\n[" + index + "]" + visitRequestDTO);
            index++;
        }
        System.out.println("0. Cancel");

        selectRequest();



    }


    /**
     * Request preferred date date formatted.
     *
     * @return the date formatted
     */
    public DateTime requestBeginDate(){
        while (true){
            try{
                DateTime date = null;
                String[] chosenDateString= new String[3];
                int[] chosenDate = new int[3];
                String temp= null;

                System.out.println();
                System.out.println("\u001B[36m#=======Preferred Date=======#");
                System.out.println("\u001B[33mWrite in this format: day/month/year");
                System.out.println("\u001B[33mExample: 12/02/2021  03/08/2022  12/12/2020");
                System.out.println();



                temp = Utils.readLineFromConsole("Enter begin date to see the Booking Requests: ");

                assert temp != null;
                if (temp.equals(String.valueOf(-1))) return null;

                chosenDateString =temp.split("/");

                for(int i = 0; i < chosenDateString.length;  i++){
                    chosenDate[i]= Integer.parseInt(chosenDateString[i]);
                }

                date =  new DateTime(chosenDate[0], chosenDate[1], chosenDate[2]);

                if ((isValidDate(date))){
                    return date;
                }else{
                    System.out.println("\u001B[41mError,you can't choose a date that already pass or that is not in the right format\u001B[0m");
                }

            }catch (Exception e){
                System.out.println("\u001B[41mError, the preferred date format is not valid!\u001B[0m");
            }
        }
    }


    /**
     * Request end date date time.
     *
     * @return the date time
     */
    public DateTime requestEndDate(){
        while (true){
            try{
                DateTime date = null;
                String[] chosenDateString= new String[3];
                int[] chosenDate = new int[3];
                String temp= null;

                System.out.println();
                System.out.println("\u001B[36m#=======Preferred Date=======#");
                System.out.println("\u001B[33mWrite in this format: day/month/year");
                System.out.println("\u001B[33mExample: 12/02/2021  03/08/2022  12/12/2020");
                System.out.println();



                temp = Utils.readLineFromConsole("Enter begin date to see the Booking Requests: ");

                assert temp != null;
                if (temp.equals(String.valueOf(-1))) return null;

                chosenDateString =temp.split("/");

                for(int i = 0; i < chosenDateString.length;  i++){
                    chosenDate[i]= Integer.parseInt(chosenDateString[i]);
                }

                date =  new DateTime(chosenDate[0], chosenDate[1], chosenDate[2]);

                if ((isValidDate(date))){
                    return date;
                }else{
                    System.out.println("\u001B[41mError,you can't choose a date that already pass or that is not in the right format\u001B[0m");
                }

            }catch (Exception e){
                System.out.println("\u001B[41mError, the preferred date format is not valid!\u001B[0m");
            }
        }
    }


    /**
     * Is valid date boolean.
     *
     * @param date the date
     * @return the boolean
     */
    public boolean isValidDate(DateTime date){
        return date.isAfter(new DateTime()) && validateDate(date) && date.getYear() >= Calendar.getInstance().get(Calendar.YEAR);

    }

    /**
     * Validate date boolean.
     *
     * @param date the date
     * @return the boolean
     */
    public boolean validateDate(DateTime date){
        int day = date.getDayOfMonth();
        int month = date.getMonth();

        if (month < 1 || month > 12) {
            return false;
        }

        if (day < 1 || day > 31) {
            return false;
        }

        if (month == 2) {
            return day <= 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        }
        return true;
    }

    private void selectRequest() {

        boolean validInput = false;
        String subject = null;
        String message = null;

        while (!validInput) {

            String numVis = null;
            try {
                numVis = Utils.readLineFromConsole("Select the visit request:");
                if (Integer.parseInt(numVis)==0){
                    break;
                }
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
                    if(askVisit().equalsIgnoreCase("Yes")){
                        visitRequest.acceptVisitRequest();
                        subject = "Your visit request was accepted";
                        message = Utils.readLineFromConsole("Writes the message you want to send to the client: ");

                        controller.createEmail(visitRequest.getAgent().getEmail(), clientEmail , subject, message);

                    }else{
                        visitRequest.declineVisitRequest();
                        subject = "Your visit request was decline";
                        message = Utils.readLineFromConsole("Writes the message you want to send to the client: ");

                        controller.createEmail(visitRequest.getAgent().getEmail(), clientEmail , subject, message);

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
        String answer = Utils.readLineFromConsole("Do you want to accept or decline the visit request?(Yes/No): ");

        while(answer.trim().isEmpty() || (!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No"))){
            System.out.println("Please enter Yes or No");
            answer = Utils.readLineFromConsole("Do you want to accept or decline the visit request?(Yes/No): ");
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
