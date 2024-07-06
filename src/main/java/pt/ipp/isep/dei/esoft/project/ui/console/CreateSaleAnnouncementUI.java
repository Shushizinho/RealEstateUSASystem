package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.mapper.PropertyMapper;
import pt.ipp.isep.dei.esoft.project.repository.BusinessTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PropertyTypeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.File;
import java.util.*;

/**
 * The type Create sale announcement ui.
 */
public class CreateSaleAnnouncementUI implements Runnable {

    private final List<String> EXTENSIONS = List.of(new String[]{"png", "jpg", "jpeg"});
    private final AnnouncementController controller = new AnnouncementController();
    /**
     * The Scanner.
     */
    static Scanner scanner = new Scanner(System.in);
    /**
     * The Property type repository.
     */
    PropertyTypeRepository propertyTypeRepository= new PropertyTypeRepository();

    /**
     * The Property types.
     */
    List<PropertyType> propertyTypes = Repositories.getInstance().getPropertyTypeRepository().getPropertyTypes();


    /**
     * The Business type repository.
     */
    BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
    /**
     * The Business types.
     */
    List<BusinessType> businessTypes = Repositories.getInstance().getBusinessTypeRepository().getBusinessTypes();
    /**
     * The Announcement title.
     */
    String announcementTitle;
    /**
     * The Commission.
     */
    double commission=0.0;
    /**
     * The Listed price.
     */
    double listedPrice=0.0;
    /**
     * The Rent duration.
     */
    int rentDuration=0;
    /**
     * The Rent price.
     */
    double rentPrice=0.0;
    /**
     * The Phone number.
     */
    long phoneNumber=0;
    /**
     * The Date.
     */
    DateTime date = new DateTime();
    /**
     * The Price.
     */
    double price= 0.0;
    /**
     * The Property.
     */
    Property property = null;
    /**
     * The Store description.
     */
    String storeDescription="PPROG";
    /**
     * The Address.
     */
    Address address= new Address("test","test","teste","teste");

    /**
     * The Agent.
     */
    Agent agent = getController().getAgentFromSession();
    /**
     * The Agent dto.
     */
    AgentDTO agentDTO = AnnouncementMapper.employeeToEmployeeDTO(agent);

    /**
     * The Agent descripton.
     */
    String agentDescripton;
    /**
     * The Business type description.
     */
    String BusinessTypeDescription;
    /**
     * The Property type description.
     */
    String propertyTypeDescription;
    /**
     * The Property price.
     */
    Double propertyPrice;
    /**
     * The Property area.
     */
    Double propertyArea;
    /**
     * The Property address.
     */
    Address propertyAddress;
    /**
     * The Property distance to centre.
     */
    Double propertyDistanceToCentre;
    /**
     * The Property photographs.
     */
    List<Photograph> propertyPhotographs;
    /**
     * The Property bedroom number.
     */
    Integer propertyBedroomNumber = 0;
    /**
     * The Property bathroom number.
     */
    Integer propertyBathroomNumber = 0;
    /**
     * The Property parking space number.
     */
    Integer propertyParkingSpaceNumber = 0;
    /**
     * The Property equipment list.
     */
    List<String> propertyEquipmentList= new ArrayList<>();
    /**
     * The Property has basement.
     */
    Boolean propertyHasBasement = false;
    /**
     * The Property has loft.
     */
    Boolean propertyHasLoft = false;
    /**
     * The Property sun exposure.
     */
    String propertySunExposure = "";
    /**
     * The Property rent duration.
     */
    Integer propertyRentDuration = 0;
    /**
     * The Property 1.
     */
    Property property1 = null;

    /**
     * The Client dto.
     */
    Client clientDTO;




    private AnnouncementController getController() {
        return controller;
    }

    public void run(){


        createAnnouncement();




    }

    private String requestClientName() {

        String clientName;

        try {
            System.out.print("Enter the client's name: ");
            clientName = scanner.nextLine();

            if (clientName.trim().isEmpty()) {
                throw new IllegalArgumentException("Client's name cannot be empty.");
            }

            if (clientName.matches(".*\\d.*")) {
                throw new IllegalArgumentException("Client's name cannot contain numbers.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            clientName = requestClientName();
        }

        return clientName;
    }





    private String requestClientEmail() {
        String clientEmail;

        try {
            System.out.print("Enter the client's email: ");
            clientEmail = scanner.nextLine();


            if (clientEmail.trim().isEmpty()) {
                throw new IllegalArgumentException("Client's email cannot be empty.");
            }


            if (!isValidEmail(clientEmail)) {
                throw new IllegalArgumentException("Invalid email format.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            clientEmail = requestClientEmail();
        }

        return clientEmail;
    }

    private boolean isValidEmail(String email) {

        return email.contains("@") ;
    }


    private Integer requestPassportNumber() {
        Integer passportNumber;

        try {
            System.out.print("Enter the client's passport number: ");
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                throw new IllegalArgumentException("Passport number cannot be empty.");
            }

            passportNumber = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid passport number format.");
            passportNumber = requestPassportNumber();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            passportNumber = requestPassportNumber();
        }

        return passportNumber;
    }


    private Address requestClientAddress() {
        ArrayList<String> listadrs = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String temp = new String();
        System.out.println("\u001B[36m#=======Client Address=======#");
        temp = Utils.readLineFromConsole("Street Address: ");
        listadrs.add(temp);
        temp = Utils.readLineFromConsole("City: ");
        listadrs.add(temp);
        temp = Utils.readLineFromConsole("State: ");
        listadrs.add(temp);
        String zip = null;
        while (true) {
            try {
                boolean point = true;
                while (point) {
                    zip = Utils.readLineFromConsole("Zip Code: ");
                    point = isAlpha(zip);
                    if (point) {
                        System.out.println("\u001B[31mYour zip code cannot have letters\u001B[0m");
                    }
                }
                listadrs.add(zip);
                Address addrs = new Address(listadrs.get(0), listadrs.get(1), listadrs.get(2), listadrs.get(3));

                return addrs;
            } catch (Exception e) {
                System.out.println("\u001B[31mYour zip format is not valid\u001B[0m");
            }
        }
    }

    private boolean isAlpha(String input) {
        return input.matches(".*[a-zA-Z].*");
    }





    private long requestClientPhoneNumber() {
        long phoneNumber;

        try {
            System.out.print("Enter the client's phone number: ");
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                throw new IllegalArgumentException("Phone number cannot be empty.");
            }

            String numericInput = input.replaceAll("[^0-9]", "");

            phoneNumber = Long.parseLong(numericInput);

            if (!isValidUSPhoneNumberFormat(phoneNumber)) {
                throw new IllegalArgumentException("Invalid phone number format.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid phone number format.");
            phoneNumber = requestClientPhoneNumber();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            phoneNumber = requestClientPhoneNumber();
        }

        return phoneNumber;
    }



    private String requestTaxNumber() {
        String taxNumber;

        try {
            System.out.print("Enter the client tax number: ");
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                throw new IllegalArgumentException("Tax number cannot be empty.");
            }

            String numericInput = input.replaceAll("[^0-9]", "");

            if (!isValidUSTaxNumberFormat(numericInput)) {
                throw new IllegalArgumentException("Invalid tax number format.");
            }

            taxNumber = numericInput;

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            taxNumber = requestTaxNumber();
        }

        return taxNumber;
    }

    /**
     * Is valid us tax number format boolean.
     *
     * @param taxNumber the tax number
     * @return the boolean
     */
    public boolean isValidUSTaxNumberFormat(String taxNumber) {
        return taxNumber.length() == 9;
    }


    /**
     * Is valid us phone number format boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean isValidUSPhoneNumberFormat(long phoneNumber) {
        String phoneNumberString = String.valueOf(phoneNumber);
        return phoneNumberString.length() != 10;
    }








    /**

     Requests the user to input the price of the property and validates the input to ensure it is greater than 0.

     @return the price of the property as a Double

     @throws IllegalArgumentException if the input is less than or equal to 0
     */

    private Double requestPropertyPrice() {
        double price= 0.0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.print("Enter the price of the property: ");
                price = scanner.nextDouble();
                scanner.nextLine();

                if (price <= 0) {
                    throw new IllegalArgumentException("Price must be greater than 0.");
                } else {
                    validPrice = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid price entered. " + e.getMessage() + " Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        return price;

    }



    private double requestPropertyArea() {
        double area = 0d;
        while (true) {
            try {
                boolean positive = false;
                while (!positive) {
                    System.out.println("\u001B[36m#=======Property Area=======#");
                    String input = Utils.readLineFromConsole("Enter Area: ");
                    area = Double.parseDouble(input);
                    positive = area >= 0;
                    if (!positive) {
                        System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
                    }
                }
                return area;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInvalid input. Must be a numeric value.\u001B[0m");
            }
        }
    }



    private double requestPropertyDistanceToCentre() {
        double distance = 0;
        while (true) {
            try {
                boolean positive = false;
                while (!positive) {
                    System.out.println("\u001B[36m#=======Distance to City Centre=======#");
                    String input = Utils.readLineFromConsole("Enter Distance (in miles): ");

                    // Verifica se a entrada é uma string
                    if (!input.matches("^\\d*\\.?\\d+$")) {
                        throw new NumberFormatException("Invalid input. Must be a number.");
                    }

                    distance = Double.parseDouble(input);
                    positive = distance >= 0;
                    if (!positive) {
                        System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
                    }
                }
                return distance;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInvalid input. Must be a Positive Number\u001B[0m");
            }
        }
    }











    /**

     Prompts the user to enter the relative file path of the property photographs, with a minimum of 1 photograph and a maximum of 30 photographs.
     The user can leave the input field empty to stop adding photographs.
     @return a list of strings representing the relative file paths of the property photographs
     */

    private List<Photograph>  requestPropertyPhotographs() {
        Scanner input = new Scanner(System.in);
        List<Photograph>  photographs = new ArrayList<>();
        System.out.println("Photograph Relative File Path [Min 1 photograph; Max. 30 photographs;  Leave empty to stop]:");
        String photograph = input.nextLine();
        while ( (photographs.size() < 30 && photograph.compareTo("") != 0) || (photograph.compareTo("") == 0 && photographs.size() < 1) ) {
            if ( photograph.compareTo("") != 0 ){
                if ( !photographs.contains(new Photograph(photograph)) ) photographs.add(new Photograph(photograph));
            }
            photograph = input.nextLine();
        }
        return photographs;
    }


    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".")+1;
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }


    private int requestPropertyBedroomNumber() {
        int bedno = 0;
        while (true) {
            try {
                boolean notnegative = false;
                while (!notnegative) {
                    System.out.println("\u001B[36m#=======Number of Bedrooms=======#");
                    String input = Utils.readLineFromConsole("Enter Number of Bedrooms: ");

                    // Verifica se a entrada é uma string
                    if (!input.matches("^\\d+$")) {
                        throw new NumberFormatException("Invalid input. Must be a non-negative integer.");
                    }

                    bedno = Integer.parseInt(input);
                    notnegative = bedno >= 0;
                    if (!notnegative) {
                        System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
                    }
                }
                return bedno;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInvalid input. Must be a Non-Negative Integer\u001B[0m");
            }
        }
    }



    private Integer requestPropertyBathroomNumber() {
        int bathno = 0;
        while (true) {
            try {
                boolean notnegative = false;
                while (!notnegative) {
                    System.out.println("\u001B[36m#=======Number of Bathrooms=======#");
                    String input = Utils.readLineFromConsole("Enter Number of Bathrooms: ");

                    // Verifica se a entrada é uma string
                    if (!input.matches("^\\d+$")) {
                        throw new NumberFormatException("Invalid input. Must be a non-negative integer.");
                    }

                    bathno = Integer.parseInt(input);
                    notnegative = bathno >= 0;
                    if (!notnegative) {
                        System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
                    }
                }
                return bathno;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInvalid input. Must be a Non-Negative Integer\u001B[0m");
            }
        }
    }





    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        //display the property types as a menu with number options to select
        int i = 1;
        System.out.println("\u001B[36m#=======Select Type of Request=======#");
        for (BusinessType businessType : businessTypes) {
            System.out.println("\u001B[0m"+i + " - " + businessType.getDescriptionProperty());
            i++;
        }

    }


    private Integer requestPropertyParkingSpaceNumber() {
        int parkno = 0;
        while (true) {
            try {
                boolean notnegative = false;
                while (!notnegative) {
                    System.out.println("\u001B[36m#=======Number of Parking Spaces=======#");
                    String input = Utils.readLineFromConsole("Enter Number of Parking Spaces: ");

                    // Verifica se a entrada é uma string
                    if (!input.matches("^\\d+$")) {
                        throw new NumberFormatException("Invalid input. Must be a non-negative integer.");
                    }

                    parkno = Integer.parseInt(input);
                    notnegative = parkno >= 0;
                    if (!notnegative) {
                        System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
                    }
                }
                return parkno;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInvalid input. Must be a Non-Negative Integer\u001B[0m");
            }
        }
    }






    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        //display the property types as a menu with number options to select
        System.out.println("\u001B[36m#=======Select Type of Property=======#");
        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println("\u001B[0m"+i + ". " + propertyType.getDescription());
            i++;
        }
    }





    private Address requestPropertyAddress() {
        ArrayList<String> listadrs = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String temp = new String();
        System.out.println("\u001B[36m#=======Property Address=======#");
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


    private List<String> requestPropertyEquipmentList() {
        Scanner input = new Scanner(System.in);
        List<String> propertyEquimentList = new ArrayList<>();
        System.out.println("Available Equipments [No Minimum; Leave empty to stop]:");
        String item = input.nextLine();
        if (!item.trim().isEmpty()) {
            propertyEquimentList.add(item);
            item = input.nextLine();
            while (!item.trim().isEmpty()) {
                item = input.nextLine();
            }
        }
        return propertyEquimentList;
    }


    private Boolean requestPropertyHasBasement() {
        Scanner input = new Scanner(System.in);
        System.out.println("Does the House have a basement? (Y/N):");
        String text = input.nextLine();
        while(text.trim().isEmpty() || (text.compareToIgnoreCase("Y")!=0 && text.compareToIgnoreCase("n")!=0)) {
            System.out.println("\u001B[31mMust be either Y or N.\u001B[0m");
            text = input.nextLine();
        }
        return (!(text.compareToIgnoreCase("n")==0));
    }


    private Boolean requestPropertyHasLoft() {
        Scanner input = new Scanner(System.in);
        System.out.println("Does the House have a loft? (Y/N):");
        String text = input.nextLine();
        while(text.trim().isEmpty() || (text.compareToIgnoreCase("Y")!=0 && text.compareToIgnoreCase("n")!=0)) {
            System.out.println("\u001B[31mMust be either Y or N.\u001B[0m");
            text = input.nextLine();
        }
        return (!(text.compareToIgnoreCase("n")==0));
    }



    private String requestPropertySunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.println("Sun Exposure:");
        String text = input.nextLine();
        while(text.trim().isEmpty()) {
            text = input.nextLine();
        }
        return text;
    }



    private String displayAndSelectPropertyType() {
        //Display the list of property types
        List<PropertyType> propertyTypes = controller.getPropertyTypes();

        int listSize = propertyTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayPropertyTypeOptions(propertyTypes);
            System.out.print("\u001B[35m\nType your option: \u001B[0m");
            answer = input.nextInt();
            if ( answer < 0 || answer > listSize ) {
                System.out.println("\u001B[31mMust be one of the provided options.\u001B[0m");
            }
            if (answer == 0) return "\\Cancel";
        }

        String description = propertyTypes.get(answer - 1).getDescription();
        return description;

    }




    private String displayAndSelectBusinessType() {
        //Display the list of property types
        List<BusinessType> businessTypes = controller.getBusinessTypes();

        int listSize = businessTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayBusinessTypeOptions(businessTypes);
            System.out.print("\u001B[35m\nType your option: \u001B[0m");
            answer = input.nextInt();
        }

        String description = businessTypes.get(answer - 1).getDescriptionAnnoucement();
        return description;

    }

    private int requestPropertyRentDuration() {
        int duration = 0;
        while (true) {
            try {
                boolean positive = false;
                while (!positive) {
                    Scanner input = new Scanner(System.in);
                    System.out.println("Property Rent Duration:");
                    String inputString = input.nextLine().trim();

                    // Verifica se a entrada é uma string
                    if (!inputString.matches("^\\d+$")) {
                        throw new NumberFormatException("Invalid input. Must be a positive integer.");
                    }

                    duration = Integer.parseInt(inputString);
                    positive = duration > 0;
                    if (!positive) {
                        System.out.println("\u001B[31mMust be a Positive Integer\u001B[0m");
                    }
                }
                return duration;
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mInvalid input. Must be a Positive Integer\u001B[0m");
            }
        }
    }





    //////ANOUNCEMENT//////






    private double requestAnnouncementCommission(){
        double commission = 0.0;

        while (true) {
            System.out.print("Enter a commission percentage between 1 and 50: ");
            try {
                commission = scanner.nextDouble();
                if (commission >= 1 && commission <= 50) {
                    break;
                } else {
                    throw new IllegalArgumentException("Commission percentage must be between 1 and 50");
                }
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
                scanner.nextLine();
            }
        }
        return  commission;
    }


    private Property LandConfirmation( String propertyTypeDescription,Address propertyAddress,List<Photograph> propertyPhotographs, double propertyDistanceToCentre, double propertyArea, double propertyPrice,String storeDescription, Client client){
        Property trueProperty = null;

        System.out.println("\u001B[36m#=== Land confirmation ===#\u001B[0m");


        System.out.println("Property type = " + propertyTypeDescription + "\nProperty Address = " + propertyAddress + "\nProperty Distance To Centre = " + propertyDistanceToCentre + "\nProperty Area = " + propertyArea + "\n Property Price = " + propertyPrice + "\nStore = " + storeDescription + "\nClient = " + client);


        Property property2 = new Property(propertyPrice, propertyArea,propertyAddress,propertyDistanceToCentre,propertyPhotographs,getController().getPropertyTypeByDescription(propertyTypeDescription), getController().getStoreByDescription(storeDescription),client,date);
        PropertyDTO propertyDTO = PropertyMapper.toDTO(property2);

        LandDTO landDTO = new LandDTO(propertyDTO);




        boolean validInput = false;
        while (!validInput){

            System.out.println("Can you confirm the data entered?");
            System.out.print("Yes/No:");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes")) {
                trueProperty = getController().createProperty(landDTO);
                validInput = true;

                return trueProperty;
            } else if (input.equals("no")) {
                run();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    return null;

    }

    private Property ApartmentConfirmation( String propertyTypeDescription,Address propertyAddress,String storeDescription,List<Photograph> propertyPhotographs,double propertyDistanceToCentre, double propertyArea, double propertyPrice, int propertyBedroomNumber,Client client, int propertyBathroomNumber, int propertyParkingSpaceNumber, List <String> propertyEquipmentList,boolean propertyHasBasement, boolean propertyHasLoft, String propertySunExposure){
        Property trueProperty = null;

        System.out.println("\u001B[36m#=== Apartment confirmation ===#\u001B[0m");



        System.out.printf("Property type: %s%nProperty Address: %s%nProperty Distance To Centre: %.2f%nProperty Area: %.2f%nProperty Price: %.2f%nStore: %s%nClient: %s%n",
                propertyTypeDescription, propertyAddress, propertyDistanceToCentre, propertyArea, propertyPrice, storeDescription, client);
        System.out.println("Bedroom number: " + propertyBedroomNumber);
        System.out.println("Bathroom number: " + propertyBathroomNumber);
        System.out.println("Parking spaces: " + propertyParkingSpaceNumber);




        boolean validInput = false;
        while (!validInput){

            System.out.println("Can you confirm the data entered?");
            System.out.print("Yes/No:");
            String input = scanner.nextLine().trim().toLowerCase();

            Property property2 = new Property(propertyPrice, propertyArea,propertyAddress,propertyDistanceToCentre,propertyPhotographs,getController().getPropertyTypeByDescription(propertyTypeDescription), getController().getStoreByDescription(storeDescription),client,date);

            Inhabitable inhabitable = new Inhabitable(property2,propertyBedroomNumber,propertyBathroomNumber,propertyParkingSpaceNumber,propertyEquipmentList);

            InhabitableDTO inhabitableDTO = PropertyMapper.toDTO(inhabitable);


            ApartmentDTO apartmentDTO = new ApartmentDTO(inhabitableDTO);

            if (input.equals("yes")) {
                trueProperty = getController().createProperty(apartmentDTO);
                validInput = true;
                return trueProperty;

            } else if (input.equals("no")) {
                run();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        return null;
    }


    private Property HouseConfirmation( String propertyTypeDescription,Address propertyAddress,List<Photograph> propertyPhotographs, double propertyDistanceToCentre, double propertyArea, double propertyPrice, int propertyBedroomNumber, int propertyBathroomNumber, int propertyParkingSpaceNumber, List<String> propertyEquipmentList,boolean propertyHasBasement, boolean propertyHasLoft, String propertySunExposure, String storeDescription,Client client){

        System.out.println("\u001B[36m#=== House confirmation ===#\u001B[0m");

        System.out.printf("Property type: %s%nProperty Address: %s%nProperty Distance To Centre: %.2f%nProperty Area: %.2f%nProperty Price: %.2f%nStore: %s%nClient: %s%n",
                propertyTypeDescription, propertyAddress, propertyDistanceToCentre, propertyArea, propertyPrice, storeDescription, client);
        System.out.println("Bedroom number: " + propertyBedroomNumber);
        System.out.println("Bathroom number: " + propertyBathroomNumber);
        System.out.println("Parking spaces: " + propertyParkingSpaceNumber);

        System.out.printf("Basement: %s\n", propertyHasBasement ? "yes" : "no");
        System.out.printf("Inhabitable : %s\n", propertyHasLoft ? "yes" : "no");
        System.out.printf("Sun exposure: %s\n", propertySunExposure);
        Property trueProperty = null;

        boolean validInput = false;
        while (!validInput){

            System.out.println("Can you confirm the data entered?");
            System.out.print("Yes/No:");
            String input = scanner.nextLine().trim().toLowerCase();


            Property property2 = new Property(propertyPrice, propertyArea,propertyAddress,propertyDistanceToCentre,propertyPhotographs,getController().getPropertyTypeByDescription(propertyTypeDescription), getController().getStoreByDescription(storeDescription),client,date);

            Inhabitable inhabitable = new Inhabitable(property2,propertyBedroomNumber,propertyBathroomNumber,propertyParkingSpaceNumber,propertyEquipmentList);

            InhabitableDTO inhabitableDTO = PropertyMapper.toDTO(inhabitable);

            HouseDTO houseDTO = new HouseDTO(propertyHasBasement,propertyHasLoft,propertySunExposure,inhabitableDTO);



            if (input.equals("yes")) {

                trueProperty = getController().createProperty(houseDTO);
                validInput = true;
                return trueProperty;


            } else if (input.equals("no")) {
                run();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        return null;
    }

    private void SaleAnnouncementConfirmation(Property property){
        double percentage = commission / 100;

        listedPrice = property1.getPrice() * percentage + property1.getPrice();

        Announcement announcement = new Announcement(agent,date,commission,getController().getBusinessTypeByDescription(BusinessTypeDescription),property,listedPrice);

        AnnouncementDTO announcementDTO = AnnouncementMapper.toDTO(announcement);

        System.out.println("\u001B[36m#=== Sale Announcement ===#\u001B[0m");


        System.out.printf("Comission: %.2f\n", announcementDTO.getCommission());
        System.out.printf("Listed price: %.2f\n", announcementDTO.getListedPrice());
        Optional<Announcement> saleAnnouncement = null;
        boolean validInput = false;
        while (!validInput){

            System.out.println("Can you confirm the data entered?(Yes/No:)");

            scanner.nextLine();
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("yes")){
                saleAnnouncement = controller.createAnnouncement ( announcementDTO);
                getController().createSMS(agent.getPhoneNumber(),announcementDTO.getAgent().getEmployee().getName(),announcementDTO.getDate(),announcementDTO.getProperty().getAddress());
                validInput = true;
              if(saleAnnouncement.isPresent())  {
                  System.out.println("Announcement was created.");
              }else{
                  System.out.println("Announcement was not created.");
              }
            } else if (input.equalsIgnoreCase("no")){
                System.out.println("Announcement was not created");
                return;

            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    private void RentAnnouncementConfirmation(Property property1){
        double percentage = commission / 100;

        listedPrice = property1.getPrice() * percentage + property1.getPrice();

        Announcement announcement = new Announcement(agent,date,commission,getController().getBusinessTypeByDescription(BusinessTypeDescription),property1,rentDuration,listedPrice);

        AnnouncementDTO announcementDTO = AnnouncementMapper.toDTO(announcement);

        System.out.println("\u001B[36m#=== Rent Announcement ===#\u001B[0m");


        System.out.printf("Comission: %.1f\n", announcementDTO.getCommission());
        System.out.printf("Listed price: %.2f\n", announcementDTO.getListedPrice());
        Optional<Announcement> rentAnnouncement = null;


        boolean validInput = false;
        while (!validInput){

            System.out.println("Can you confirm the data entered?(Yes/No):");
            scanner.next();
            scanner.nextLine();
            String input = scanner.next().trim().toLowerCase();

            if (input.equalsIgnoreCase("Yes")) {
                rentAnnouncement = getController().createAnnouncement(announcementDTO);
                getController().createSMS(announcementDTO.getAgent().getEmployee().getPhoneNumber(),announcementDTO.getAgent().getEmployee().getName(),announcementDTO.getDate(),announcementDTO.getProperty().getAddress());

                validInput = true;
                if(rentAnnouncement.isPresent()){
                    System.out.println("Announcement was created.");
                }else{
                    System.out.println("Announcement was not created.");
                }


            } else if (input.equalsIgnoreCase("No")) {
                System.out.println("Announcement was not created.");
                return;

            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }



    private void createAnnouncement(){

        System.out.println("\u001B[36m#=== Create Announcement ===#\u001B[0m");

        String clientName = requestClientName();
        String clientEmail = requestClientEmail();
        long clientPhoneNumber = requestClientPhoneNumber();
        String clientTaxNumber = requestTaxNumber();
        long clientPassportNumber = requestPassportNumber();
        Address address = requestClientAddress();




        clientDTO = new Client(clientName,clientEmail, (int) clientPassportNumber,clientTaxNumber,address,clientPhoneNumber);


        propertyTypeDescription=displayAndSelectPropertyType();

        BusinessTypeDescription=displayAndSelectBusinessType();

        propertyArea= requestPropertyArea();
        propertyDistanceToCentre = requestPropertyDistanceToCentre();
        propertyPrice = requestPropertyPrice();
        propertyPhotographs=requestPropertyPhotographs();
        propertyAddress=requestPropertyAddress();


        if (Objects.equals(propertyTypeDescription, "Apartment") || Objects.equals(propertyTypeDescription, "House") ) {

            try{
                propertyBedroomNumber = requestPropertyBedroomNumber();
                propertyBathroomNumber = requestPropertyBathroomNumber();
                propertyParkingSpaceNumber = requestPropertyParkingSpaceNumber();
                propertyEquipmentList = requestPropertyEquipmentList();
            } catch (Exception e){
                System.out.println("\nError!");
                System.out.print(e.getMessage());
            }


        }

        if (Objects.equals(propertyTypeDescription, "House")){
            try {
                propertyHasBasement = requestPropertyHasBasement();
                propertyHasLoft = requestPropertyHasLoft();
                propertySunExposure = requestPropertySunExposure();
            } catch (Exception e){
                System.out.println("\nError!");
                System.out.print(e.getMessage());
            }
        }

        if (Objects.equals(propertyTypeDescription, "Land")){
            try{
                property1= LandConfirmation(  propertyTypeDescription, propertyAddress, propertyPhotographs,  propertyDistanceToCentre,  propertyArea,  propertyPrice, storeDescription,clientDTO);
            } catch (Exception e){
                System.out.println("\nError!");
                System.out.print(e.getMessage());
            }
        }


        if (Objects.equals(propertyTypeDescription, "Apartment")){
            try{
                property1= ApartmentConfirmation(propertyTypeDescription, propertyAddress,storeDescription, propertyPhotographs, propertyDistanceToCentre,  propertyArea,propertyPrice,  propertyBedroomNumber,clientDTO,  propertyBathroomNumber,  propertyParkingSpaceNumber,  propertyEquipmentList, propertyHasBasement,  propertyHasLoft,  propertySunExposure);
            } catch (Exception e){
                System.out.println("\nError!");
                System.out.print(e.getMessage());
            }
        }


        if (Objects.equals(propertyTypeDescription, "House")){
            try{
                property1= HouseConfirmation(propertyTypeDescription,propertyAddress,  propertyPhotographs,  propertyDistanceToCentre,  propertyArea,  propertyPrice,  propertyBedroomNumber,  propertyBathroomNumber,  propertyParkingSpaceNumber,  propertyEquipmentList, propertyHasBasement,  propertyHasLoft,  propertySunExposure,  storeDescription,clientDTO);

            } catch (Exception e){
                System.out.println("\nError!");
                System.out.print(e.getMessage());
            }
        }

        commission = requestAnnouncementCommission();





        if(Objects.equals(BusinessTypeDescription, "Buy")){

            try {

                SaleAnnouncementConfirmation(property1);


            } catch (Exception e){
                System.out.println("\nError!");
                System.out.print(e.getMessage());
            }
        }

        if (Objects.equals(BusinessTypeDescription, "Rent")){
            try {
                rentDuration = requestPropertyRentDuration();
                RentAnnouncementConfirmation(property1);
            } catch (Exception e){
                System.out.println("\nError!");
                System.out.print(e.getMessage());
            }
        }



    }





}