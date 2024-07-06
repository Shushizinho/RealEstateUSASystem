package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Register Employee UI (console). This option is only available for administrators for demonstration purposes.
 */
public class RegisterEmployeeUI implements Runnable {

    private final RegisterEmployeeController controller = new RegisterEmployeeController();
    private String name;
    private String email;
    private Integer passportNumber;
    private String taxNumber;
    private long phoneNumber;
    private String store;
    private  Address address;
    private List<Role> role;

    private RegisterEmployeeController getController() {
        return controller;
    }


    /**
     * Runs the Register Employee application, which prompts the user to select a role and a store,
     * then requests employee data, and finally submits the employee data to the system.
     */

    public void run() {
        System.out.println("\u001B[36m#=======Register Employee=======#");

        boolean validate = false;
        role = displayAndSelectRole();
        if (role != null) {
            for (Role eachRole:role) {
                if(eachRole.getDescription().compareTo("ADMINISTRATOR") == 0 || eachRole.getDescription().compareTo("STORE NETWORK MANAGER") == 0  ){
                    validate=true;
                    break;
                }
            }

            store = displayAndSelectStore(validate);

            if (store.compareTo("\\Cancel") != 0) {
                requestData();
                if (address != null) submitData();
            }
        }

    }


    /**
     * Submits the employee data to the system by calling the create method in the controller,
     * and prints a message indicating whether the employee was successfully created or not.
     */
    private void submitData() {


        System.out.println("\u001B[36m#=======Confirm Information=======#");

        confirmSubmit();

        String answer = "false";
        while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")){
            answer = Utils.readLineFromConsole("\nDo you confirm the displayed information?(Y/N): ");
        }

        if (answer.compareTo("n") == 0 || answer.compareTo("N") == 0) run();

        Optional<Employee> employee = getController().create(name,email,passportNumber,taxNumber,address,phoneNumber,store,role);

        if (employee.isPresent()) {
            System.out.println("\u001B[32mEmployee successfully created!\u001B[0m");
        } else {
            System.out.println("\u001B[31mEmployee not created!\u001B[0m");
        }
    }



    private void confirmSubmit() {
        labelInfo("Name: ");
        userInput(name);
        labelInfo("Email: ");
        userInput(email);
        labelInfo("Roles:");
        for (Role roles: role) {
            System.out.printf("\u001B[0m %s;\u001B[0m", roles.getDescription().substring(0,1).toUpperCase() + roles.getDescription().substring(1).toLowerCase());
        }
        labelInfo("\nStore: ");
        userInput(store);
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
        System.out.printf("\u001B[0m%s\u001B[0m", s);
    }

    /**
     * Requests the necessary data from the console to create a new employee.
     * Prompts the user to enter the employee's name, email, passport number,
     * tax number, phone number and address.
     */

    private void requestData() {
        System.out.println("\u001B[36m#=======Employee Info=======#");
        System.out.println("\u001B[33mEnter 0 into any field to cancel.");


        //Request the Name from the console
        name = requestName();
        if (name.compareTo("0") == 0) return;
        //Request the Email from the console
        email = requestEmail();
        if (email.compareTo("0") == 0) return;
        //Request the Passport Number from the console
        passportNumber = requestPassportNumber();
        if (passportNumber == 0) return;
        //Request the Tax Number from the console
        taxNumber = requestTaxNumber();
        if (taxNumber.compareTo("0") == 0) return;

        //Request the Phone Number from the console
        phoneNumber = requestPhoneNumber();
        if (phoneNumber == 0) return;

        //Request the Address from the console
        address = requestAddress();

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

                        Scanner input = new Scanner(System.in);

                         email = Utils.readLineFromConsole("Email: ");
                        if (email.compareTo("0") == 0) return email;
                        point = isValidEmail(email);
                        if(!point){
                            System.out.println("\u001B[31mYour email format is not valid\u001B[0m");
                        }
                        else{

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

                    Scanner input = new Scanner(System.in);


                     tax = Utils.readLineFromConsole("Tax Number (Format: 111-11-1111) : ");
                    if (tax == "0") return tax;
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
     * Requests the user to input the street address, city, state, and zip code of the employee's address
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
                    if(point){
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

     Requests the name of an employee from the console.
     @return a string with the name of the employee.
     */

    private String requestName() {
        return Utils.readLineFromConsole("Name:");
    }


    /**
     Displays a list of all available roles and prompts the user to select one or more roles for the employee being registered. The selected roles are then returned as a List of Role objects.
     @return a List of Role objects representing the roles selected by the user
     */


    private List<Role> displayAndSelectRole() {
        List<Role> allroles = controller.getRole();
        List<Role> newroles = new ArrayList<>();



        int listSize = allroles.size();
        int answer = -2;
        int point = 0;

        System.out.println("\u001B[36m#=======Select Employee Roles=======#");
        while (((answer < 1 || answer > listSize) || !(newroles.size() == listSize) )) {
            Scanner input = new Scanner(System.in);
            if(newroles.size()!=0){
                System.out.println();
            }
            displayRoleOptions(allroles,newroles);
            if(newroles.size()!=0){
                point=1;
                System.out.println("\u001B[0m0. Stop Selecting" + "\u001B[0m");
            }
            System.out.println("\u001B[0m0. Cancel\u001B[0m");
                try {
                    answer = Utils.readIntegerFromConsole("Select a role:");
                    if((answer==0 && point==1) || answer ==-1){
                        break;
                    }

                    if(!(answer < 1 || answer > listSize) && !(newroles.contains(allroles.get(answer-1))) ){
                        newroles.add(allroles.get(answer-1));
                    }
                    if(newroles.size()!=0){
                        point=0;
                    }

                }
                catch (Exception e){
                    System.out.println("\u001B[31mMust be an Integer cannot be a String\u001B[0m");
                }


        }
        if (answer == -1) return null;

        return newroles;

    }

    /**

     Displays the list of available roles for selection and don't indicates the roles that have already been selected.
     @param roles the list of available roles to be displayed
     @param newroles the list of roles that have already been selected
     */

    private void displayRoleOptions(List<Role> roles, List<Role> newroles) {

        int i = 1;
        for (Role role : roles) {
            if(!(newroles.contains(role))){
                System.out.println("\u001B[0m" + i + ". " + role.getDescription() + "\u001B[0m");
            }
            i++;
        }

    }

    /**
     * Displays a list of available stores and allows the user to select one by its index.
     *
     * @return The designation of the selected store as a String.
     */

    private String displayAndSelectStore(boolean validate) {

        String description;

        List<Store> store = controller.getStore();
        if (!validate) {

            int listSize = store.size();
            int answer = -1;
            System.out.println("\u001B[36m#=======Select Store=======#");


            while (answer < 0 || answer > listSize) {
                Scanner input = new Scanner(System.in);

                displayStoreOptions(store);
            try {
                answer = Utils.readIntegerFromConsole("Select a role:");

                }
                catch (Exception e){
                    System.out.println("\u001B[31mMust be an Integer cannot be a String\u001B[0m");
                }
            }
            if (answer == 0) return "\\Cancel";

            description = store.get(answer).getDesignation();
        }
        else{
            description = store.get(0).getDesignation();
        }

        return description;

    }

    /**
     * Displays the available store options for selection.
     *
     * @param stores a List of Store objects containing the available store options
     */

    private void displayStoreOptions(List<Store> stores) {

        int i = 1;
        System.out.println();
        for (Store store : stores) {
            if(store.getId()==0) continue;
            System.out.println("\u001B[0m" + i + ". " + store.getDesignation() + "\u001B[0m");
            i++;
        }
        System.out.println("\u001B[0m0. Cancel\u001B[0m");
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
                    Scanner input = new Scanner(System.in);

                    result = Integer.parseInt(Utils.readLineFromConsole(header+":"));
                    if (result == 0) return result;
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
