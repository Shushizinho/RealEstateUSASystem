package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GetPropertyListController;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.Filters;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Get property list ui.
 */
public class GetPropertyListUI implements Runnable {

    private final GetPropertyListController controller = new GetPropertyListController();

    private final String APPROVED = "yes";

    private final String REJECTED = "no";


    ArrayList<String> responseFiltersList = new ArrayList<>();

    private GetPropertyListController getController() {
        return controller;
    }

    public void run() {
        List<Property> properties = getPropertyListByAnnouncement();

       displayListOfProperties(properties);

       if(displayConfirmationToFilter()) {
           chooseFiltersOptions();

            if (verifyAnswers(responseFiltersList))  {
                displayAndSelectOtherSortOptions();
            } else {
                String propertyFilter = (displayPropertyFiltersList());
                if (!propertyFilter.equals("0")){
                    properties = getListOfProperties(properties, responseFiltersList);
                    if (properties.isEmpty()) {
                        System.out.println("\nThere are no properties with this filters");
                    } else {
                        List<Property> p = getListOfPropertiesByPorpertyFilter(properties, propertyFilter);
                        displayListOfProperties(p);
                    }
                }
            }
       }
    }

    private void chooseFiltersOptions() {
        String businessType = (displayAndSelectBusinessType());
        if (!businessType.equals("0")){
            responseFiltersList.add(businessType);
            String propertyType = (displayAndSelectPropertyType());
            if (!propertyType.equals("0")){
                responseFiltersList.add(propertyType);
                String numberOfBedrooms;
                if (propertyType.equals("Land")) {
                    numberOfBedrooms = "0";
                } else{
                    numberOfBedrooms = (selectNumberOfBedrooms());
                }
                if (!numberOfBedrooms.equals("\\Cancel")) {
                    responseFiltersList.add(numberOfBedrooms);
                }

            }
        }
    }


    /**
     * This method saves the available list of properties, that have an associated announcement.
     *
     * @return list of properties.
     */
    public List<Property> getPropertyListByAnnouncement(){
        return controller.getPropertieListByAnnouncemnent();
    }

    /**
     * This method display a message to confirm that the user wants to filter this properties.
     *
     * @return 1 if the user confirm and 0 is the user doesn't want to filter.
     */
    public Boolean displayConfirmationToFilter(){
        return  displayOptionYesOrNo("\u001B[35mDo you wish to filter this list of properties?");
    }

    /**
     * This method display the list of possible options to filter the properties on sale or on rent.
     */
    private void displayAndSelectOtherSortOptions(){
        List<Property> pr1= displayPropertiesOnSale();
        System.out.println();
        Boolean resp = selectOtherSortOptions("\n\u001B[35mDo you pretend to sort all the properties that are on sale?");
        if(resp){
            List<Property> p = getListOfPropertiesByPorpertyFilter(pr1, APPROVED);
            displayListOfProperties(p);
        }
        if (!resp){
            List<Property> pr2 = displayPropertiesOnRent();
            System.out.println();
            if (!pr2.isEmpty()){
                Boolean response = selectOtherSortOptions("\n\u001B[35mDo you pretend to sort all the properties that are on rent?");
                if (response){
                    List<Property> p = getListOfPropertiesByPorpertyFilter(pr2, REJECTED);
                    displayListOfProperties(p);
                }
            }
        }
    }

    /**
     * This method display all the properties listed in the system on rent.
     * @return the list of properties.
     */
    private List<Property> displayPropertiesOnRent(){
        System.out.println("\u001B[36m#=======Properties on Rent =======#");
        List<Property> propertiesRent = getController().getPropertiesByBusinessType("Rent");
        displayListOfProperties(propertiesRent);
        if (propertiesRent.isEmpty()){
            System.out.println("\u001B[33mThere are no properties on rent");
        }
        return propertiesRent;
    }

    /**
     * This method display all properties listed in the system on sale.
     * @return the list of properties.
     */
    private List<Property> displayPropertiesOnSale(){
        System.out.println("\u001B[36m#=======Properties on Sale =======#");
        List<Property> propertiesSale = getController().getPropertiesByBusinessType("Sale");
        displayListOfProperties(propertiesSale);
        if (propertiesSale.isEmpty()){
            System.out.println("\u001B[33mThere are no properties on sale");
        }
        return propertiesSale;
    }

    /**
     * This method verify the given answers to the previous filters are valid.
     *
     * @return true if the filters are null or the option "Do not use this filter"
     */
    public boolean verifyAnswers(ArrayList<String> list){
        Integer bTSize = getController().getBusinessTypes().size()+1;
        Integer pTSize = getController().getPropertyType().size()+1;

        return (list.get(0).equals(String.valueOf(bTSize)) && list.get(1).equals(String.valueOf(pTSize)) && list.get(2).equals(REJECTED));
    }


    /**
     * This method display the property filters list and saves the chosen property filter.
     *
     * @param string the string
     * @return chosen property filter.
     */
    public Boolean selectOtherSortOptions (String string){
        Boolean response = displayOptionYesOrNo(string);
        if (response){
           displayPropertyFiltersList();
        }
        return response;
    }


    /**
     * This method display yes or no options and saves the answer.
     *
     * @param string the string
     * @return answer. string
     */
    public Boolean displayOptionYesOrNo(String string){
        while(true){
            try {
                System.out.println();
                String response = Utils.readLineFromConsole(string + "(yes/no):");
                assert response != null;
                return response.equals(APPROVED);
            }catch ( Exception e){
                System.out.println("\u001B[41mError, please type y or n\u001B[0m");
            }
        }

    }


    /**
     * This method gets the list of properties according to the chosen business type, property type and number of bedrooms
     * @return filtered list of properties
     */
    private List<Property> getListOfProperties(List<Property> properties, ArrayList<String> list){

        List<Property> newProperty= getController().getPropertyRepository().getPropertyList(properties, list);

        return newProperty;
    }


    /**
     * This method sort a given list of properties by a property filter.
     *
     * @param newProperty the new property
     * @param answer      the answer
     * @return sorted list of properties.
     */
    public List<Property> getListOfPropertiesByPorpertyFilter(List<Property> newProperty, String answer){
        return getController().getPropertyRepository().sortPropertyList(answer, newProperty);
    }

    /**
     * This method display a given list of properties.
     * @param newProperty
     */
    private void displayListOfProperties(List<Property> newProperty){
        System.out.println("\u001B[36m#=======Property List=======#");
        for (int i = 0; i < newProperty.size(); i++) {
            System.out.printf("\u001B[0m%d. %s%n", i+1, newProperty.get(i).toString());
        }
        if (newProperty.isEmpty()) System.out.println("\u001B[33mThere are no properties to list");
    }

    /**
     * This method display the list of business type available and saves the chosen business type.
     * @return the chosen business type.
     */
    private String displayAndSelectBusinessType() {
        //Display the list of business types
        while(true){
            try{
                List<BusinessType> businessTypes = getController().getBusinessTypes();

                int answer = -1;

                System.out.println("\u001B[36m#=======Select Business Type =======#");
                displayBusinessTypeOptions(businessTypes);
                System.out.println("\u001B[0m0. Cancel");
                System.out.println();
                answer =Utils.readIntegerFromConsole("Enter business type:");


                if(answer == 0 || answer == businessTypes.size()+1) {
                    return String.valueOf(answer);
                }
                else {
                    String description = businessTypes.get(answer - 1).getDescriptionProperty();
                    return description;
                }
            }
            catch (Exception e){
                System.out.println("\u001B[41mError, select form the following list a business type!\u001B[0m");
            }
        }

    }

    /**
     * This method display the business type options.
     * @param businessTypes
     */
    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        //display the business types as a menu with number options to select
        int i = 1;
        for (BusinessType businessType : businessTypes) {
            System.out.println("\u001B[0m"+ i + ". " + businessType.getDescriptionProperty());
            i++;
        }
        System.out.println("\u001B[0m"+i+ ". Do not use this filter");
        
    }

    /**
     * This method display the list of property type available and saves the chosen property type.
     * @return the chosen property type.
     */
    private String displayAndSelectPropertyType() {
        //Display the list of property types
        while (true) {
            try {
                List<PropertyType> propertyTypes = getController().getPropertyType();

                int answer = -1;

                System.out.println("\u001B[36m#=======Select Property Type =======#");
                displayPropertyTypeOptions(propertyTypes);
                System.out.println("\u001B[0m0. Cancel");
                System.out.println();
                answer = Utils.readIntegerFromConsole("Enter property type:");

                if(answer == 0 || answer == propertyTypes.size()+1) {
                    return String.valueOf(answer);
                }
                else {
                    String description = propertyTypes.get(answer - 1).getDescription();
                    return description;
                }

            }catch (Exception e){
                System.out.println("\u001B[41mError, select form the following list, a property type\u001B[0m");
            }
        }

    }

    /**
     * This method display the property type options.
     * @param propertyTypes
     */
    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        //display the property types as a menu with number options to select
        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println("\u001B[0m"+i + ". " + propertyType.getDescription());
            i++;
        }
        System.out.println("\u001B[0m"+i+ ". Do not use this filter");
    }

    /**
     * This method saves the chosen number of bedrooms.
     * @return the chosen number of bedrooms.
     */
    private String selectNumberOfBedrooms() {
        while (true) {
            try {
                System.out.println("\u001B[36m#=======Select Number of Bedrroms =======#");
                String useFilter = "b";
                while (!useFilter.equals(APPROVED)) {
                    useFilter = Utils.readLineFromConsole("Do you want to use this filter (yes/no):");
                    assert useFilter != null;
                    if (!useFilter.equals(APPROVED) &&!useFilter.equals(REJECTED) && !useFilter.equals("0")) System.out.println("\u001B[41mInvalid Option.\u001B[0m");
                    else {
                        switch (useFilter){
                            case APPROVED:break;
                            case REJECTED: return REJECTED;
                            case "0": return "cancel";
                            default: System.out.println("\u001B[41mThis should be an unreachable statement. If you're seeing this, contact the programmer.\u001B[0m"); break;
                        }
                    }
                }
                System.out.println();
                int answer = Utils.readIntegerFromConsole("Enter number of bedrooms:");

                if (answer < 0){
                    System.out.println("\u001B[41mError,select a valide a number of bedrooms\u001B[0m");
                }else return String.valueOf(answer);

            }catch (Exception e){
            System.out.println("\u001B[41mError, a number of bedrooms\u001B[0m");
            }
        }
    }

    /**
     * This method display the property filters list and saves the chosen property filter.
     * @return the chosen property filter.
     */
    private String displayPropertyFiltersList() {
        while(true){
            try{
                List<Filters> filters = getController().getPropertyFilters();

                int answer = -1;

                System.out.println("\u001B[36m#=======Select Property Filter =======#");
                displayPropertyFiltersOptions(filters);
                System.out.println();
                System.out.println("\u001B[0m0. Cancel");
                System.out.println();
                answer = Utils.readIntegerFromConsole("Enter property filter:");

                if (answer != 0) {
                    String description = filters.get(answer - 1).getDescription();
                    return description;
                }else return String.valueOf(answer);
            }catch (Exception e){
                System.out.println("\u001B[41mError, select form the following list, a property filter!\u001B[0m");
            }
        }
    }

    /**
     * This method display the property filters options.
     * @param filters
     */
    private void displayPropertyFiltersOptions(List<Filters> filters) {
        //display the property filters as a menu with number options to select
        for (int i = 1; i < filters.size(); i++) {
            System.out.println("\u001B[0m"+ i + " - " + filters.get(i-1).getDescription());
        }

    }
}

