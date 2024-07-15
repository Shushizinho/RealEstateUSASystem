package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterStoreController;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Register store ui.
 */
public class RegisterStoreUI implements Runnable{

    private final RegisterStoreController controller = new RegisterStoreController();
    private String designation;
    private Integer id;
    private Address storeAddress;
    private String email;
    private Integer phoneNumber;
    private String storeManager;


    /**
     * Returns the controller responsible for registering the store.
     * @return the controller responsible for registering the store
     */
    private RegisterStoreController getController() {
        return controller;
    }

    /**
     * The Scanner.
     */
    static Scanner scanner = new Scanner(System.in);


    /**
     * Starts the user interface for registering a new store.
     */
    public void run(){

        System.out.println("=== Register Store ===");

//        storeManager = displayAndSelectEmployee();

        requestData();

        submitData();

    }

    /**
     * Requests the necessary data from the user.
     */
    private void requestData() {
        id = requestId();

        designation = requestDesignation();
        email = requestEmail();
        phoneNumber = requestPhoneNumber();

        storeAddress = requestStoreAddress();



    }

    /**
     * Requests the store ID from the user.
     * @return the store ID entered by the user
     */
    private Integer requestId() {

        List <Store> stores = controller.getStore();

        Integer id = null;
        boolean validid = false;
        while (!validid){
            try{
                System.out.println("Id: ");
                id = scanner.nextInt();

                for(Store store : stores){
                    if(id==store.getId()){
                       throw new IllegalArgumentException("This ID already exist.");
                    }
                }

                if (id == 0 || id < 0 || id == null){
                    throw new IllegalArgumentException("ID must be integer and positive.");
                }else{
                    validid = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Id. " + e.getMessage() + " Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid Id.");
                scanner.nextLine();
            }
        }
        return id;
    }

    /**
     * Requests the store designation from the user.
     * @return the store designation entered by the user
     */
    private String requestDesignation() {

        Scanner input = new Scanner(System.in);
        String designation = null;
        int cara=1;
        boolean point = true;
        boolean validdesignation = false;
        while (!validdesignation) {
            try {
                System.out.println("Designation:");
                designation = input.nextLine();

                cara = designation.replace(" ","").length();

                point = isLett(designation);
                if (!point) {
                    throw new IllegalArgumentException("Your designation cannot have numbers.");
                }else if (cara == 0) {
                    throw new IllegalArgumentException("Designation must be filled.");
                }else if (cara > 14) {
                    throw new IllegalArgumentException("Designation cannot be longer than 14 characters.");
                }else {
                    validdesignation = true;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid designation. " + e.getMessage() + " Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid designation.");
                scanner.nextLine();
            }
        }
        return designation;
    }

    /**
     * Requests the store address from the user.
     * @return the store address entered by the user
     */
    private Address requestStoreAddress() {
        ArrayList<String> listadrs = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("- Address -");
        System.out.println("Street Address:");
        listadrs.add(input.nextLine());
        System.out.println("City:");
        listadrs.add(input.nextLine());
        System.out.println("State:");
        listadrs.add(input.nextLine());
        String zip = null;
        while(true){

            try{
                boolean point = true;
                while(point){
                    System.out.println("Zip Code:");
                    zip = input.nextLine();
                    point = isLett(zip);
                    if(point){
                        throw new IllegalArgumentException("Your zip code cannot have letters.");
                    }
                }

                listadrs.add(zip);
                Address addrs = new Address(listadrs.get(0),listadrs.get(1),listadrs.get(2),listadrs.get(3));

                return addrs;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Zip Code. " + e.getMessage() + " Please try again.");
            } catch (Exception e){
                System.out.println("Invalid input. Please enter a valid zip code.");
            }
        }
    }

    /**
     * Requests the store email from the user.
     * @return the store email entered by the user
     */
    private String requestEmail() {
        boolean valid = true;

        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();


        try{
        Scanner input = new Scanner(System.in);
        System.out.println("Email:");
        email = input.nextLine();
        valid=isValidEmail(email);
        if(!valid){
            throw new IllegalArgumentException("Please enter a valid format.");
        }

        else{
            for (Store store: storeRepository.getStores()) {
                if(store.hasEmail(email)){
                    valid = false;
                    throw new IllegalArgumentException("This email already exists");
                }
            }
        }


        } catch (IllegalArgumentException e) {
        System.out.println("Invalid Email. " + e.getMessage() + " Please try again.");
        } catch (Exception e){
        System.out.println("Invalid input. Please enter a valid email.");
        }
        return email;
    }

    /**
     * Requests the store phone number from the user.
     * @return the store phone number entered by the user
     */
    private Integer requestPhoneNumber() {

        Integer phone = 0;
        boolean validPhone = false;
        while (!validPhone){
            try{
                int cont = 0;

                Scanner input = new Scanner(System.in);
                System.out.println("phoneNumber:");
                phone=input.nextInt();
                cont=countDigits(phone);

                if (cont == 9){
                    validPhone = true;
//                    throw new IllegalArgumentException("Phone number must have 10 digits.");
                }else{
//                    validPhone = true;
                    throw new IllegalArgumentException("Phone number must have 9 digits.");

                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid phone number. " + e.getMessage() + " Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid phone number.");
                scanner.nextLine();
            }
        }

        return phone;
    }

    /**
     * Displays a list of available employees and allows the user to select one by its index.
     *
     * @return The name of the selected employee as a String.
     */
    private String displayAndSelectEmployee() {
        List<Employee> employee = controller.getEmployeeToStoreManager();

        String description = "";
        int listSize = employee.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            displayEmployeeOptions(employee);
            System.out.println("Select a employee:");
            answer = input.nextInt();

            if(!(answer < 1 || answer > listSize)) {
                description = employee.get(answer - 1).getEmail();
            }

            if(answer==0){
                break;
            }
        }


        return description;

    }

    /**
     * Displays the available employees options for selection.
     *
     * @param employees a List of Employee objects containing the available employee options
     */
    private void displayEmployeeOptions(List<Employee> employees) {
        int i = 1;
        System.out.println("=======================");
        for (Employee employee : employees) {
            System.out.println(i + " - " + employee.getName());
            i++;
        }
        System.out.println(0 + " - Close");
        System.out.println("=======================");
    }

    /**
     * Submits the data entered by the user to the controller for processing.
     * Displays a message indicating whether the store was successfully created or not.
     * */
    private void submitData() {

        String answer = Utils.readLineFromConsole("Is this data correct? (y/n):");
        if (answer.equals("y")){
            Optional<Store> store = controller.registerStore(id, designation, email, storeAddress, phoneNumber, storeManager);

            if (store.isPresent()) {
                System.out.println("Store successfully created!");
            }
        } else {
            System.out.println("Store not created!");
        }
    }

    /**
     * Is lett boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isLett(String str) {
        String regex = "[a-zA-Z]+";
        return str.matches(regex);
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

}
