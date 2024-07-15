package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SignController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sign UI (console). This option is only available for administrators for demonstration purposes.
 */
public class SignUI implements Runnable {

    private final SignController controller = new SignController();
    private String name;
    private String email;
    private Integer passportNumber;
    private String taxNumber;
    private Integer phoneNumber;
    private  Address address;

    private String password;

    private final String APPROVED = "yes";

    private final String REJECTED = "no";

    private SignController getController() {
        return controller;
    }


    /**
     * Runs the Register Client application, requesting and submitting the
     * necessary data.
     */

    public void run() {
        System.out.println("\u001B[36m#=======Register=======#");
//        System.out.println("\u001B[33mEnter 0 in any field to cancel.");

        requestData();

        submitData();
    }


    /**
     * Submits the client data to the system by calling the create method in the controller,
     * and prints a message indicating whether the client was successfully created or not.
     */

    private void submitData() {

        System.out.println("\u001B[36m#=======Confirm Information=======#");

        confirmSubmit();

        String answer = "false";
        while (!answer.equalsIgnoreCase(APPROVED) && !answer.equalsIgnoreCase(REJECTED)){
            answer = Utils.readLineFromConsole("\nDo you confirm the displayed information??" + APPROVED + "/" + REJECTED +"):");
        }


        if (answer.compareTo(REJECTED) == 0 || answer.compareTo("No") == 0) run();

        Optional<Client> client = controller.create(name,email,passportNumber,taxNumber,address,phoneNumber,password);

        if (client.isPresent()) {
            System.out.println("\u001B[32mClient successfully created!\u001B[0m");
        } else {
            System.out.println("\u001B[31mClient not created!\u001B[0m");
        }
    }

    private void confirmSubmit() {
        labelInfo("Name: ");
        userInput(name);
        labelInfo("Email: ");
        userInput(email);
        labelInfo("Passport Number: ");
        userInput(String.valueOf(passportNumber));
        labelInfo("Tax Number: ");
        userInput(taxNumber);
        labelInfo("Phone Number: ");
        userInput(String.valueOf(phoneNumber));
        labelInfo("Street Address: ");
        userInput(address.getStreetAddress());
        labelInfo("City: ");
        userInput(address.getCity());
        labelInfo("State: ");
        userInput(address.getState());
        labelInfo("Zip Code: ");
        userInput(address.getZipCode());

    }

    private void userInput(String input) {
        System.out.printf("\u001B[0m%s\n\u001B[0m", input);
    }

    private void labelInfo(String s) {
        System.out.printf("\u001B[35m%s\u001B[0m", s);
    }

    /**
     * Requests the necessary data from the console to create a new client.
     * Prompts the user to enter the client's name, email, password ,passport number,
     * tax number, phone number and address.
     */

    private void requestData() {
        //Request the Name from the console
        name = requestName();

        //Request the Email from the console
        email = requestEmail();

        //Request the Email from the console
        password = requestPassword();

        //Request the Passport Number from the console
        passportNumber = requestPassportNumber();

        //Request the Tax Number from the console
        taxNumber = requestTaxNumber();

        //Request the Phone Number from the console
        phoneNumber = requestPhoneNumber();

        //Request the Address from the console
        address = requestAddress();

    }

    /**
     Requests the password from the user through the console.
     @return the password inputted by the user as a String.
     */

    private String requestPassword() {
        return Utils.readLineFromConsole("Password: ");
    }

    /**
     Requests the email from the user through the console.
     @return the email inputted by the user as a String.
     */
    private String requestEmail() {
        String email=null;

        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();

        while(true){
            try{
                boolean point = false;
                while(!point){

                    email = Utils.readLineFromConsole("Email: ");

                    point = isValidEmail(email);
                    if(!point){
                        System.out.println("\u001B[31mYour email format is not valid\u001B[0m");
                    }

                    for (Employee employee: employeeRepository.getEmployees()) {
                        if(employee.hasEmail(email)){
                            System.out.println("\u001B[31mThis email already exists\u001B[0m");

                            point = false;
                        }
                    }

                    for (Client client: clientRepository.getClients()) {
                        if(client.hasEmail(email)){
                            System.out.println("\u001B[31mThis email already exists\u001B[0m");

                            point = false;
                        }
                    }

                }
                return email;

            }
            catch (Exception e) {
                System.out.println("\u001B[31mError...your email format is not valid \u001B[0m");

            }

        }
    }

    /**
     Requests the tax number from the user through the console.
     @return the tax number entered by the user as an Integer
     */

    private String requestTaxNumber() {
        while(true){
            try{
                String tax=null;
                boolean point = false;
                while(!point){


                    tax = Utils.readLineFromConsole("Tax Number (Format: 111-11-1111): ");


                    point = isValidTax(tax);
                    if(!point){
                        System.out.println("\u001B[31mYour tax number format is not valid\u001B[0m");
                    }

                }

                return tax;

            }
            catch (Exception e) {
                System.out.println("\u001B[31mError...your tax number format is not valid\u001B[0m");


            }

        }

    }

    /**
     Requests the passport number from the user through the console.
     @return the tax number entered by the user as an Integer
     */

    private Integer requestPassportNumber() {
        return validateNumberToPointer(9,"Passport Number");

    }

    /**
     Requests the phone number from the user through the console.
     @return the phone number entered by the user as an Integer
     */

    private Integer requestPhoneNumber() {
        return validateNumberToPointer(9,"Phone Number");

    }

    /**
     * Requests the user to input the street address, city, state, and zip code of the client's address
     * and creates an Address object with the input data.
     *
     * @return the Address object with the input data.
     */

    private Address requestAddress() {
        ArrayList<String> listadrs = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String temp = new String();
        temp = Utils.readLineFromConsole("Street Address: ");
        if (temp.compareTo("0") != 0) listadrs.add(temp); else return null;
        temp = Utils.readLineFromConsole("City: ");
        if (temp.compareTo("0") != 0) listadrs.add(temp); else return null;
        temp = Utils.readLineFromConsole("State: ");
        if (temp.compareTo("0") != 0) listadrs.add(temp); else return null;
        String zip = null;
        while(true){

            try{
                boolean point = true;
                while(point){
                    zip = Utils.readLineFromConsole("Zip Code: ");
                    if (zip.compareTo("0") == 0) return null;
                    point = isAlpha(zip);
                    if(point || zip.isEmpty() ){
                        point = true;
                        System.out.println("\u001B[31mYour zip code cannot have letters\u001B[0m");
                    }
                }

                listadrs.add(zip);
                Address addrs = new Address(listadrs.get(0),listadrs.get(1),listadrs.get(2),listadrs.get(3));

                return addrs;
            }
            catch (Exception e){
                System.out.println("\u001B[31mYour zip format is not valid\u001B[0m");
            }
        }
    }

    /**

     Requests the name of an client from the console.
     @return a string with the name of the client.
     */

    private String requestName() {
        return Utils.readLineFromConsole("Name: ");
    }

    /**
     * Count digits int.
     *
     * @param num the num
     * @return the int
     */
    public static int countDigits(int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    private Integer validateNumberToPointer(int pointer,String header) {

        while (true){
            try {
                int result=0,num=0;

                while (num!=pointer){
                    if (num!=0){
                        System.out.println( "\u001B[31m"+ header +" must have at least "+ pointer+" numbers\u001B[0m");
                    }
                    result = Integer.parseInt(Utils.readLineFromConsole(header+":"));
                    num=countDigits(result);
                }

                return result;
            } catch (Exception e) {
                System.out.println("\u001B[31mMust be an Integer cannot be a String\u001B[0m");
            }

        }
    }

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Is valid tax boolean.
     *
     * @param tax the tax
     * @return the boolean
     */
    public static boolean isValidTax(String tax) {
        String regex = "^\\d{3}-\\d{2}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tax);
        return matcher.matches();
    }

    /**
     * Is alpha boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isAlpha(String str) {
        String regex = "[a-zA-Z]+";
        return str.matches(regex);
    }



}
