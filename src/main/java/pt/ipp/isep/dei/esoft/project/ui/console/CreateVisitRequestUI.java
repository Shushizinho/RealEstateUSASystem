package pt.ipp.isep.dei.esoft.project.ui.console;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateVisitRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The type Create visit request ui.
 */
public class CreateVisitRequestUI implements Runnable{
    
    private final CreateVisitRequestController controller = new CreateVisitRequestController();

    private Property property;

    private DateTime preferredDate;

    private List<DateTime> timeSlot = new ArrayList<>();



    private CreateVisitRequestController getController() {
        return controller;
    }
    
    public void run(){
        System.out.println("\u001B[36m#=======Create Visit Request=======#");

        displayListOfProperties();

        if (requestData() != null) {
            boolean point = creatVisitRequest();

            if (point){
                chooseAnotherTimeSlot();
                chooseAnotherDate();
            }
        }
    }

    /**
     * This method display the list of all properties in the system
     */
    public void displayListOfProperties(){
        List<Property> properties = getController().getPropertieListByAnnouncemnent();
        System.out.println("\u001B[36m#=======Property List=======#\u001B[0m");
        for (int i = 0; i < properties.size(); i++) {
            System.out.println((i+ 1) +" : "+ properties.get(i).toString());
        }if (properties.isEmpty()){
            System.out.println("\u001B[31mThere are no properties in the system\u001B[0m");
        }
    }

    /**
     * This method saves and request all data necessary to creat a visit request
     *
     * @return the string
     */
    public String requestData(){
        property = requestProperty();
        if (property == null) return null;


        preferredDate = requestPreferredDate();
       if(preferredDate == null) return null;


       if (requestTimeSlot() == null) return null;

       return "true";
    }

    /**
     * This method request the Visit request's property.
     *
     * @return Visit request's property.
     */
    public Property requestProperty(){
        while (true){
            try{
                System.out.println();
                System.out.println("\u001B[36m#=======Property=======#");
               System.out.println("\u001B[33mWhich one of the previous properties you wish to visit?");
               System.out.println();
               System.out.println("\u001B[0m0. Cancel");

               int answer = Utils.readIntegerFromConsole("Enter Property: ");
               if (answer == -1) return null;
               return getController().getPropertieListByAnnouncemnent().get(answer -1);

            }catch ( Exception e){
                System.out.println("\u001B[41mError choose one of the following properties\u001B[0m");
            }
        }
    }

    /**
     * This method request the Visit request's preferred date.
     *
     * @return Visit request's preferred date.
     */
    public DateTime requestPreferredDate(){
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
                System.out.println("\u001B[0m0. Cancel");


                temp = Utils.readLineFromConsole("Enter date to visit the property: ");

                assert temp != null;
                if (temp.equals(String.valueOf(0))) return null;

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
     * This method validate the date.
     *
     * @param date the date
     * @return true if the date is valid.
     */
    public boolean isValidDate(DateTime date){
        return date.isAfter(new DateTime()) && validateDate(date) && date.getYear() >= Calendar.getInstance().get(Calendar.YEAR);

    }

    /**
     * This method validate the date's month and date's day.
     *
     * @param date to be validated.
     * @return true if the date's day and month exist.
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


    /**
     * This method validate the time slot.
     *
     * @param timeSlot to be validated.
     * @return true if the time slot is typed in the format xx:xx.
     */
    public boolean isValidTimeSlot(String timeSlot){
        String regex = "^(0[0-2]|1[0-9]):\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(timeSlot);
        return matcher.matches();
    }

    /**
     * This method request the Visit request's time slot.
     *
     * @return Visit request's time slot list.
     */
    public List<DateTime> requestTimeSlot(){
        boolean point = true;
        while (true){
            try{
                System.out.println();
                System.out.println("\u001B[36m#=======Time Slot=======#");
                System.out.println("\u001B[33mExample: 13:45-15:45, 12:00-13:00 , 19:30-21:20");
                System.out.println();
                System.out.println("\u001B[0m0. Cancel");

                String timeSlotRead = Utils.readLineFromConsole("Enter time slot (24 hour format):");

                assert timeSlotRead != null;
                if(timeSlotRead.equals(String.valueOf(0))) return null;

                String[] t= timeSlotRead.split("-");

                for (String s : t) {
                    point =isValidTimeSlot(s);
                }
                if (point){
                    List<Pair<Integer, Integer>> t1 = new ArrayList<>();

                    String[] temp= t[0].split(":");
                    t1.add(new Pair<>(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
                    temp= t[1].split(":");
                    t1.add(new Pair<>(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));

                    if (t1.get(0).getLeft() <= t1.get(1).getLeft()){
                        timeSlot.add(new DateTime(preferredDate, t1.get(0)));
                        timeSlot.add(new DateTime(preferredDate, t1.get(1)));

                        List<DateTime> timeSlotList = new ArrayList<>();

                        timeSlotList.add(0, new DateTime(preferredDate, t1.get(0)));
                        timeSlotList.add(1, new DateTime(preferredDate, t1.get(1)));
                        if (comparePreviousVisitRequests(timeSlotList) && valideTimeSlot(timeSlotList) ){
                            return timeSlotList;
                        }else{
                            System.out.println("\u001B[41mError, invalide time slot\u001B[0m");
                        }

                    } 
                }else{
                    System.out.println("\u001B[41mError, your time slot is not in the correct format\u001B[0m");
                }
            }catch (Exception e){
                System.out.println("\u001B[41mError, please choose a time slot (in 24 hour format)\u001B[0m");
            }
        }
    }


    /**
     * This method display the visit request information.
     *
     * @param visitRequest object to display the information of.
     */
    public void displayVisitRequestInformation(VisitRequest visitRequest){
        System.out.println(visitRequest.toString());
    }

    /**
     * This method creates the visit request with the chosen data and, if confirmed, add it to the repository
     *
     * @return true if the visit request is successfully sent and added to the repository.
     */
    public boolean creatVisitRequest(){
        VisitRequest visitRequest = controller.createVisitRequest(property, preferredDate,timeSlot);
        displayVisitRequestInformation(visitRequest);

        boolean point = true;
        if (displayOptionYesOrNo("\u001B[33mDo you wish to submit this data?").equals("yes")){
            if (validateVisitRequest(visitRequest)){
                System.out.println("\u001B[32mVisit request send!");

            }
        }else if (displayOptionYesOrNo("\u001B[33mDo you wish to submit this data?").equals("no")){
            System.out.println("\u001B[41mVisit request not send, please insert the data again\u001B[0m");
            point = false;
        }
        return point;
    }

    /**
     * This method validate if the visit request was created.
     *
     * @param visitRequest to be validated.
     * @return true if the visit request is successfully created.
     */
    public boolean validateVisitRequest(VisitRequest visitRequest){
        VisitRequestDTO visitRequestDTO = VisitRequestMapper.toDTO(visitRequest);
        return getController().ValidateVisitRequest(visitRequestDTO);
    }

    /**
     * This method cheeks if the user wants to make a visit request with a different time slot.
     *
     * @return boolean. boolean
     */
    public boolean chooseAnotherTimeSlot(){
        while (true){
            try{
                boolean point = true;
                    String answer = "0";
                    System.out.println();
                    System.out.println("\u001B[36m#=======Another Time Slot=======#");
                    answer = displayOptionYesOrNo("\u001B[33mDo you wish to schedule another time slots for this property?");
                    if (answer.equals("y")) {
                        List<DateTime> TempTimeSlot = requestTimeSlot();
                        point = comparePreviousVisitRequests(TempTimeSlot);
                        if (point) {
                            timeSlot = TempTimeSlot;
                            creatVisitRequest();
                        }
                    } else if (answer.equals("n") || (answer.equals("0"))) {
                        return point = false;
                    } else {
                        point = false;
                        System.out.println("\u001B[41mError, please choose one of the options\u001B[0m");
                    }


            }catch (Exception e){
                System.out.println("\u001B[41mError, please choose one of the options\u001B[0m");
            }
        }

    }

    /**
     * This method compares the visit request's property, preferred date and time slot.
     *
     * @param TempTimeSlot Visit request's time slot chosen by user.
     * @return true if the visit request with that information already exist, false otherwise.
     */
    public boolean comparePreviousVisitRequests(List<DateTime> TempTimeSlot){
        boolean point = true;

        for (VisitRequest v: getController().getVisitRequests()){
            if(v.getPreferredDate().equalsDate(preferredDate) && v.getProperty().equals(property)){
                if ((compareAllTimeSlots(TempTimeSlot, v.getTimeSlot()) )){
                    point = false;
                }
            }
        }

        if (!point) System.out.println("\u001B[41mError,you can't add another time slot that equals to previous time slot in the system\u001B[0m");

        return point;
    }


    /**
     * This method verify if any time slots overlap.
     *
     * @param a list of time slots.
     * @param b list of time slot.
     * @return true if they overlap.
     */
    public boolean compareAllTimeSlots (List<DateTime> a, List<DateTime> b){
        boolean point = true;
        for (int i = 0; i< a.size(); i=i+2){
            List<DateTime> subList = new ArrayList<>(a.subList(i, i= i+2));
            point = (compareTimeSlots(b, subList));
        }

        return  point;
    }

    /**
     * This method validate if the start time is before the end time.
     *
     * @param a list with start time and end time.
     * @return true if end time is after the start time.
     */
    public boolean valideTimeSlot(List<DateTime> a){
        return a.get(1).isAfter(a.get(0));
     }

    /**
     * This method compare the limits of the given time slot and the previous.
     *
     * @param a first time slot to compare.
     * @param b second time slot to compare.
     * @return true if they overlap.
     */
    public boolean compareTimeSlots(List<DateTime> a, List<DateTime> b){

        return ((a.get(0).isBefore(b.get(0)) && a.get(1).isAfter(b.get(0))) ||
                (b.get(0).isBefore(a.get(0)) && b.get(1).isAfter(a.get(0))) ||
                (a.get(1).isBefore(b.get(1)) && a.get(1).isAfter(b.get(1))) ||
                (b.get(1).isBefore(a.get(1)) && b.get(1).isAfter(a.get(1))) );

    }

    /**
     * This method cheeks if the user wants to make a visit request with a different date, and add, for each date
     * one or more time slot.
     */
    public void chooseAnotherDate() {
        while (true) {
            try {
                String answer = "0";
                boolean point = true;
                do {
                    System.out.println();
                    System.out.println("\u001B[36m#=======Another Date=======#");
                    answer = displayOptionYesOrNo("\u001B[33mDo you wish to schedule another date for this property?");
                    if (answer.equals("y")) {

                        preferredDate = requestPreferredDate();

                        List<DateTime> TempTimeSlot = requestTimeSlot();
                        if (comparePreviousVisitRequests(TempTimeSlot)) {

                            timeSlot = TempTimeSlot;
                            point = creatVisitRequest();

                            chooseAnotherTimeSlot();
                        }

                    } else if (answer.equals("n") || (answer.equals("0"))) {
                        return;
                    } else {
                        point = false;
                        System.out.println("\u001B[41mError, please choose one of the options\u001B[0m");
                    }
                } while (answer.equals("y") && point);
            } catch (Exception e) {
                System.out.println("\u001B[41mError, please choose one of the options\u001B[0m");
            }
        }
    }


    /**
     * this method display the yes or no option and saves the chosen option.
     *
     * @param string the string
     * @return the chosen option.
     */
    public String displayOptionYesOrNo(String string){
        while(true){
            try {
                System.out.println();
                return Utils.readLineFromConsole(string + "(y/n):");

            }catch ( Exception e){
                System.out.println("\u001B[41mError, please type y or n\u001B[0m");
            }
        }
    }
}
