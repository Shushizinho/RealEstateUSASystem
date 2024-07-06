package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreatePropertyController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Create Property UI (console). This option is only available for administrators for demonstration purposes.
 */
public class CreatePropertyUI implements Runnable  {

    private final List<String> EXTENSIONS = List.of(new String[]{"png", "jpg", "jpeg"});
    private final CreatePropertyController controller = new CreatePropertyController();
    private String storeDescription;
    private String agentDescription;
    private String businessTypeDescription;
    private String propertyTypeDescription;
    private Double propertyPrice;
    private Double propertyArea;
    private Address propertyAddress;
    private Double propertyDistanceToCentre;
    private List<Photograph> propertyPhotographs;
    private Integer propertyBedroomNumber = 0;
    private Integer propertyBathroomNumber = 0;
    private Integer propertyParkingSpaceNumber = 0;
    private List<String> propertyEquipmentList;
    private Boolean propertyHasBasement = false;
    private Boolean propertyHasLoft = false;
    private String propertySunExposure = "";
    private Integer propertyRentDuration = 0;

    private static final Client EXAMPLE_USER = new Client("Client", "client@this.app",3, "00000003",new Address("teste","teste","teste","teste"), 0000003);

    private static final Employee TEMPORARY_AGENT = new Employee("Agent", "test@this.app", 3, "00000003", new Address("teste","teste","teste","teste") , 0000003, new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000), List.of(new Role(2, AuthenticationController.ROLE_AGENT)));

    private CreatePropertyController getController() {
        return controller;
    }

    public void run() {
//        throw new NotImplementedException();
        System.out.println("\u001B[36m#=======Create Property=======#");

        propertyTypeDescription = displayAndSelectPropertyType();
        if (propertyTypeDescription == "\\Cancel") return;

        storeDescription = displayAndSelectStore();
        if (Objects.equals(storeDescription, "\\Cancel")) return;
        agentDescription = displayAndSelectAgentsFromStore(controller.getStoreByDescription(storeDescription));
        if (Objects.equals(agentDescription, "\\Cancel")) return;
        businessTypeDescription = displayAndSelectBusinessType();
        if (Objects.equals(businessTypeDescription, "\\Cancel"))  return;
        requestData();
        if (!Objects.equals(propertyTypeDescription, "Land") &&( propertySunExposure.compareTo("0") == 0 || propertySunExposure.isEmpty())) return;
        Property property = submitData();
//        if (property.equals(null)) return;
        requestBusinessData();
        if (Objects.equals(businessTypeDescription, "Rent") && propertyRentDuration == 0) return;
        if (confirmData(property)) submitBusinessData(property);
        else controller.removeProperty(property);
    }

    private boolean confirmData(Property property) {
        System.out.println(property.toStringAll());
        if (Objects.equals(businessTypeDescription, "Rent")) {
            System.out.println("Rent Duration: "+ propertyRentDuration);
        }
        String answer = Utils.readLineFromConsole("Is this data correct? (y/n):");
        while (answer.trim().isEmpty() || (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"))) {
            System.out.println("\u001B[31mInvalid input. Please enter 'y' or 'n'.\u001B[0m");
            answer = Utils.readLineFromConsole("Is this data correct? (y/n):");
        }
        switch (answer) {
            case "y": return true;
            default: return false;
        }
    }

    /**
     * This method submits the property data provided by the user and creates a new Property object
     * with the appropriate characteristics depending on the property type. It returns the created property object
     * or null if it fails to create the property.
     * @return The created Property object or null if the creation fails.
     */
    private Property submitData() {
//        throw new NotImplementedException();
        Property trueProperty = null;
        Optional<Property> property = null;
        switch (propertyTypeDescription) {
            case "Land":
                trueProperty = getController().createProperty(propertyPrice, propertyArea, propertyAddress,
                        propertyDistanceToCentre, propertyPhotographs,
                        controller.getPropertyTypeByDescription(propertyTypeDescription), controller.getStoreByDescription(storeDescription), controller.getClientFromSession(), new DateTime());
                break;
            case "Apartment":
                trueProperty = getController().createProperty(propertyPrice, propertyArea, propertyAddress,
                        propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber, propertyBathroomNumber,
                        propertyParkingSpaceNumber, propertyEquipmentList,
                        controller.getPropertyTypeByDescription(propertyTypeDescription), controller.getStoreByDescription(storeDescription), controller.getClientFromSession(), new DateTime());
                break;
            case "House":
                trueProperty = getController().createProperty(propertyPrice, propertyArea, propertyAddress,
                        propertyDistanceToCentre, propertyPhotographs, propertyBedroomNumber, propertyBathroomNumber,
                        propertyParkingSpaceNumber, propertyEquipmentList, propertyHasBasement, propertyHasLoft, propertySunExposure,
                        controller.getPropertyTypeByDescription(propertyTypeDescription), controller.getStoreByDescription(storeDescription), controller.getClientFromSession(), new DateTime());
                break;
        }
//        property = Optional.ofNullable(trueProperty);
//        if (property.isPresent()) {
//            System.out.println("Property successfully created!");
//        } else {
//            System.out.println("Property not created!");
//        }
        return trueProperty;
    }
    /**
     *
     * Submits business data by creating an announcement request for the given property and business type.
     * @param property the property to create an announcement request for
     */
    private void submitBusinessData(Property property) {
//        throw new NotImplementedException();
        Optional<AnnouncementRequest> announcementRequest = null;
        if (property != null) {
            switch (businessTypeDescription) {
//            Agent agent = (Agent) controller.getAgentByDescription(agentDescription);
                case "Rent":
                    announcementRequest = null;
                            //Optional.ofNullable(getController().createAnnouncementRequest(propertyRentDuration, controller.getBusinessTypeByDescription(businessTypeDescription), property, controller.getAgentByDescription(agentDescription), controller.getClientFromSession()));
                    break;
                case "Sale":
                    announcementRequest = null;
                            //Optional.ofNullable(getController().createAnnouncementRequest(controller.getBusinessTypeByDescription(businessTypeDescription), property, controller.getAgentByDescription(agentDescription), controller.getClientFromSession()));

                    break;
            }
        }
        if (announcementRequest == null) {
            System.out.println("Request was not created! There may already be a pending request for that property.");
        } else if (announcementRequest.isPresent()) {
            System.out.println("Request successfully created!");
            copyPhotographs(property);
        } else {
            System.out.println("Request was not created! There may already be a pending request for that property.");
        }
    }

    private void copyPhotographs(Property property) {
        for (Photograph photo: property.getPhotographs()) {
            File oldFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\input\\"+photo);
            File newFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\photos\\"+property.getAddress().toPath()+"\\"+photo);
            File parentDirectory = newFile.getParentFile();
            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }
            try {
                Files.copy(oldFile.toPath(), newFile.toPath());
            }
            catch (IOException e){
                System.out.println("An error occurred while saving "+photo);
                e.printStackTrace();
            }
        }
    }

    /**
     * Requests and collects the necessary data for creating a Property object.
     * The data includes Property Price, Area, Address, Distance To Centre, Photographs and, depending on the Property Type,
     * it can also ask for Bedroom Number, Bathroom Number and Parking Space Number, if it Has a Basement, if it Has a Loft and Sun Exposure.
     */
    private void requestData() {
//        throw new NotImplementedException();
        //requests the Property Description from the console
        propertyPrice = requestPropertyPrice();
        if (propertyPrice == 0) return;
//        if (Objects.equals(BusinessTypeDescription, "Rent")) propertyRentDuration = requestPropertyRentDuration();
        propertyArea = requestPropertyArea();
        if (propertyArea == 0) return;
        propertyAddress = requestPropertyAddress();
        if (propertyAddress == null) return;
        propertyDistanceToCentre = requestPropertyDistanceToCentre();
        if (propertyDistanceToCentre == -1) return;
        propertyPhotographs = requestPropertyPhotographs();
        if (propertyPhotographs == null) return;
        if (!Objects.equals(propertyTypeDescription, "Land")) {
            propertyBedroomNumber = requestPropertyBedroomNumber();
            if (propertyBedroomNumber == -1) return;
            propertyBathroomNumber = requestPropertyBathroomNumber();
            if (propertyBathroomNumber == -1) return;
            propertyParkingSpaceNumber = requestPropertyParkingSpaceNumber();
            if (propertyParkingSpaceNumber == -1) return;
            propertyEquipmentList = requestPropertyEquipmentList();
            if (propertyEquipmentList == null) return;
        }
        if (Objects.equals(propertyTypeDescription, "House")) {
            propertyHasBasement = requestPropertyHasBasement();
            if (propertyHasBasement == null) return;
            propertyHasLoft = requestPropertyHasLoft();
            if (propertyHasLoft == null) return;
            propertySunExposure = requestPropertySunExposure();
        }
    }
    /**
     * Requests business data from the console, specifically the property rent duration if the business type is "Rent".
     */
    private void requestBusinessData() {
        if (Objects.equals(businessTypeDescription, "Rent")) {
            propertyRentDuration = requestPropertyRentDuration();
        }
    }
    /**
     * Requests the property price from the user through the console.
     * @return the property price entered by the user
     */
    private double requestPropertyPrice() {
        double price = 0d;
        while (true) {
            try {
                boolean positive = false;
                while(!positive) {
                    System.out.println("\u001B[36m#=======" + (!Objects.equals(propertyTypeDescription, "Land") ? "Property Price" : "Property Rent Price") + "=======#");
                    System.out.println("\u001B[33mEnter 0 to cancel.");
                    price = Utils.readDoubleFromConsole("Enter Price: ");
                    positive = price >= 0;
                    if(!positive){
                        System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
                    }
                }
                return price;
            } catch (InputMismatchException | IllegalArgumentException e) {
//                e.printStackTrace();
                System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
            }
        }
    }
    /**
     * Requests the property area from the user through the console.
     * @return the property area entered by the user
     */

    private double requestPropertyArea() {
        double area = 0d;
        while (true) {
            try {
                boolean positive = false;
                while(!positive) {
                    System.out.println("\u001B[36m#=======Property Area=======#");
                    System.out.println("\u001B[33mEnter 0 to cancel.");
                    area = Utils.readDoubleFromConsole("Enter Area: ");
                    positive = area >= 0;
                    if(!positive){
                        System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
                    }
                }
                return area;
            } catch (InputMismatchException | IllegalArgumentException e) {
//                e.printStackTrace();
                System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
            }
        }

    }
    /**
     * Requests the rent duration from the user through the console.
     * @return the rent duration entered by the user
     */

    private int requestPropertyRentDuration() {
        int duration = 0;
        while (true) {
            try {
                boolean positive = false;
                while(!positive) {
                    Scanner input = new Scanner(System.in);
                    System.out.println("Property Rent Duration:");
                    duration = input.nextInt();
                    positive = duration > 0;
                    if(!positive){
                        System.out.println("\u001B[31mMust be a Positive Integer\u001B[0m");
                    }
                }
                return duration;
            } catch (InputMismatchException | IllegalArgumentException e) {
//                e.printStackTrace();
                System.out.println("\u001B[31mMust be a Positive Integer\u001B[0m");
            }
        }
    }
    /**
     * Requests the property address from the user through the console.
     * @return the property address entered by the user
     */

    private Address requestPropertyAddress() {
        ArrayList<String> listadrs = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String temp = new String();
        System.out.println("\u001B[36m#=======Property Address=======#");
        System.out.println("\u001B[33mEnter 0 in any field to cancel.");
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
     * Requests the property's distance to the city centre from the user through the console.
     * @return the property's distance to the city centre entered by the user
     */
    private double requestPropertyDistanceToCentre() {
        double distance = 0;
        while (true) {
            try {
                boolean positive = false;
                while(!positive) {
                    System.out.println("\u001B[36m#=======Distance to City Centre=======#");
                    System.out.println("\u001B[33mEnter -1 to cancel.");
                    distance = Utils.readDoubleFromConsole("Enter Distance (in miles): ");
                    positive = distance >= -1;
                    if(!positive){
                        System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
                    }
                }
                return distance;
            } catch (InputMismatchException | IllegalArgumentException e) {
//                e.printStackTrace();
                System.out.println("\u001B[31mMust be a Positive Number\u001B[0m");
            }
        }
    }
    /**
     * Requests the property's list of photographs from the user through the console.
     * @return the property's list of photographs entered by the user
     */
    private List<Photograph>  requestPropertyPhotographs() {
        Scanner input = new Scanner(System.in);
        List<Photograph>  photographs = new ArrayList<>();
        System.out.println("\u001B[36m#=======Photographs=======#");
        System.out.println("\u001B[31mMinimum 1 photograph, Maximum 30 photographs");
        System.out.println("Leave empty (after having at least 1 photograph) to stop inserting");
        System.out.println("Has to be located in"+System.getProperty("user.dir")+"\\src\\main\\resources\\input");
        System.out.println("Has to include either .png .jpg or .jpeg");
        System.out.println("Enter 0 to cancel.");
        String uri = Utils.readLineFromConsole("Enter Photograph Name: ");
        if (uri.compareTo("0") == 0) return null;
        Photograph photograph = new Photograph(uri);
        while ( (photographs.size() < 30 && !photograph.toString().trim().isEmpty()) || (photograph.toString().trim().isEmpty() && photographs.size() < 1) ) {
            if (!photograph.toString().trim().isEmpty()){
                if ( !photographs.contains(photograph) && EXTENSIONS.contains(getFileExtension(photograph.toString())) ){
                    File locatedFile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\input\\"+photograph);
                    if(locatedFile.exists() && !locatedFile.isDirectory()) {
                        photographs.add(photograph);
                    } else {
                        System.out.println("\u001B[31mSelected File Does Not Exist or is not Image.\u001B[0m");
                        System.out.println("\u001B[31mPlease check if you have spelt it correctly and if it is of the specified image types.\u001B[0m");
                    }
                }else {
                    System.out.println("\u001B[31mSelected File Does Not Exist or is not Image.\u001B[0m");
                    System.out.println("\u001B[31mPlease check if you have spelt it correctly and if it is of the specified image types.\u001B[0m");
                }
            } else {
                if (photograph.toString().trim().isEmpty() && photographs.size() < 1) {
                    System.out.println("\u001B[31mThere must be at least one image.\u001B[0m");
                }
            }
            uri = Utils.readLineFromConsole("Enter Photograph Name: ");
            if (uri.compareTo("0") == 0) return null;
            photograph = new Photograph(uri);
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
    /**
     * Requests the property's number of bedrooms from the user through the console.
     * @return the property's number of bedrooms entered by the user
     */
    private int requestPropertyBedroomNumber() {
        int bedno = 0;
        while (true) {
            try {
                boolean notnegative = false;
                while(!notnegative) {
                    System.out.println("\u001B[36m#=======Number of Bedrooms=======#");
                    System.out.println("\u001B[33mEnter -1 to cancel.");
                    bedno = Utils.readIntegerFromConsole("Enter Number of Bedrooms: ");
                    notnegative = bedno >= -1;
                    if(!notnegative){
                        System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
                    }
                }
                return bedno;
            } catch (InputMismatchException | IllegalArgumentException e) {
//                e.printStackTrace();
                System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
            }
        }
    }
    /**
     * Requests the property's number of bathrooms from the user through the console.
     * @return the property's number of bathrooms entered by the user
     */

    private Integer requestPropertyBathroomNumber() {
        int bathno = 0;
        while (true) {
            try {
                boolean notnegative = false;
                while(!notnegative) {
                    System.out.println("\u001B[36m#=======Number of Bathrooms=======#");
                    System.out.println("\u001B[33mEnter -1 to cancel.");
                    bathno = Utils.readIntegerFromConsole("Enter Number of Bathrooms: ");
                    notnegative = bathno >= -1;
                    if(!notnegative){
                        System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
                    }
                }
                return bathno;
            } catch (InputMismatchException | IllegalArgumentException e) {
//                e.printStackTrace();
                System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
            }
        }
    }

    /**
     * Requests the property's number of parking spaces from the user through the console.
     * @return the property's number of parking spaces entered by the user
     */
    private Integer requestPropertyParkingSpaceNumber() {
        int parkno = 0;
        while (true) {
            try {
                boolean notnegative = false;
                while(!notnegative) {
                    System.out.println("\u001B[36m#=======Number of Parking Spaces=======#");
                    System.out.println("\u001B[33mEnter -1 to cancel.");
                    parkno = Utils.readIntegerFromConsole("Enter Number of Parking Spaces: ");
                    notnegative = parkno >= -1;
                    if(!notnegative){
                        System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
                    }
                }
                return parkno;
            } catch (InputMismatchException | IllegalArgumentException e) {
//                e.printStackTrace();
                System.out.println("\u001B[31mMust be a Non-Negative Integer\u001B[0m");
            }
        }
    }
    /**
     * Requests the property's list of available equipment from the user through the console.
     * @return the property's list of available equipment entered by the user
     */

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
    /**
     * Asks the user if the house has a basement and returns a boolean value.
     * @return true if the house has a basement, false otherwise
     */
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
    /**
     * Asks the user if the house has a loft and returns a boolean value.
     * @return true if the house has a loft, false otherwise
     */

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
    /**
     * Requests the sun exposure of a house property from the console.
     * @return the sun exposure of the house property
     */
    private String requestPropertySunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.println("Sun Exposure:");
        String text = input.nextLine();
        while(text.trim().isEmpty()) {
            text = input.nextLine();
        }
        return text;
    }

    /**
     * Displays the list of property types and prompts the user to select one by entering its corresponding number.
     * @return The description of the selected property type.
     */
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
    /**
     * Displays the list of business types and prompts the user to select one by entering its corresponding number.
     * @return The description of the selected business type.
     */
    private String displayAndSelectBusinessType() {
        //Display the list of property types
        List<BusinessType> businessTypes = controller.getBusinessTypes();

        int listSize = businessTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        while (answer < 1 || answer > listSize) {
            displayBusinessTypeOptions(businessTypes);
            System.out.println("\u001B[35m\nType your option: \u001B[0m");

            answer = input.nextInt();
            if ( answer < 0 || answer > listSize ) {
                System.out.println("\u001B[31mMust be one of the provided options.\u001B[0m");
            }
            if (answer == 0) return "\\Cancel";
        }

        String description = businessTypes.get(answer - 1).getDescriptionProperty();
        return description;

    }
    /**
     * Displays the list of stores and prompts the user to select one by entering its corresponding number.
     * @return The description of the selected store.
     */

    private String displayAndSelectStore() {
        //Display the list of property types
        List<Store> stores = controller.getStoresWithAgents();

        int listSize = stores.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);
        if (listSize<1) {
            while (answer < 1 || answer > listSize) {
                displayStoreOptions(stores);
                System.out.println("\u001B[35m\nType your option: \u001B[0m");
                answer = input.nextInt();
                if (answer < 0 || answer > listSize) {
                    System.out.println("\u001B[31mMust be one of the provided options.\u001B[0m");
                }
                if (answer == 0) return "\\Cancel";
            }
        } else {
            answer = 1;
        }
        String description = stores.get(answer - 1).getDesignation();
        return description;

    }
    /**
     * Displays the list of agents from a specific store and prompts the user to select one by entering its corresponding number.
     * @return The description of the selected agents.
     */

    private String displayAndSelectAgentsFromStore(Store store) {
        //Display the list of property types
        List<Employee> agents = controller.getAgentsFromStore(store);

        int listSize = agents.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayAgentOptions(agents);
            System.out.println("\u001B[35m\nType your option: \u001B[0m");
            answer = input.nextInt();
            if ( answer < 0 || answer > listSize ) {
                System.out.println("\u001B[31mMust be one of the provided options.\u001B[0m");
            }
            if (answer == 0) return "\\Cancel";
        }

        String description = agents.get(answer - 1).getEmail();
        return description;

    }
    /**
     * Displays the list of property types as a menu with number options to select.
     * @param propertyTypes A List of PropertyType objects to be displayed as menu options.
     */
    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        //display the property types as a menu with number options to select
        System.out.println("\u001B[36m#=======Select Type of Property=======#");
        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println("\u001B[0m"+i + ". " + propertyType.getDescription());
            i++;
        }
        System.out.println("\u001B[0m0. Cancel\u001B[0m");
    }
    /**
     * Displays the list of business types as a menu with number options to select.
     * @param businessTypes A List of BusinessType objects to be displayed as menu options.
     */
    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        //display the property types as a menu with number options to select
        int i = 1;
        System.out.println("\u001B[36m#=======Select Type of Request=======#");
        for (BusinessType businessType : businessTypes) {
            System.out.println("\u001B[0m"+i + " - " + businessType.getDescriptionProperty());
            i++;
        }
        System.out.println("\u001B[0m0. Cancel\u001B[0m");
    }
    /**
     * Displays the list of stores as a menu with number options to select.
     * @param stores A List of Store objects to be displayed as menu options.
     */
    private void displayStoreOptions(List<Store> stores) {
        //display the property types as a menu with number options to select
        int i = 1;
        System.out.println("\u001B[36m#=======Select Store=======#");
        for (Store store : stores) {
            System.out.println("\u001B[0m"+i + " - " + store.getDesignation());
            i++;
        }
        System.out.println("\u001B[0m0. Cancel\u001B[0m");

    }
    /**
     * Displays the list of agents as a menu with number options to select.
     * @param agents A List of Employee objects to be displayed as menu options.
     */

    private void displayAgentOptions(List<Employee> agents) {
        //display the property types as a menu with number options to select
        int i = 1;
        System.out.println("\u001B[36m#=======Select Agent=======#");
        for (Employee agent : agents) {
            System.out.println("\u001B[0m"+i + " - " + agent.getName());
            i++;
        }
        System.out.println("\u001B[0m0. Cancel\u001B[0m");
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
