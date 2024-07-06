package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GetPropertyListController;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.Filters;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

/**
 * The type Get property list ui.
 */
public class GetPropertyListUI implements Runnable {

    private final GetPropertyListController controller = new GetPropertyListController();


    private String businessType;
    
    private String propertyType;
    
    private String numberOfBedrooms;

    private String propertyFilter;

    private List<Property> properties;

    private GetPropertyListController getController() {
        return controller;
    }

    public void run() {
       properties = getPropertyListByAnnouncement();

       displayListOfProperties(properties);

       String confirmation = displayConfirmationToFilter();

       if(confirmation.equals("y")) {
           businessType = (displayAndSelectBusinessType());
           if (!businessType.equals("0")){
               propertyType = (displayAndSelectPropertyType());
               if (!propertyType.equals("0")){
                   if (propertyType.equals("Land")) {
                       numberOfBedrooms = "0";
                   } else{
                       numberOfBedrooms = (selectNumberOfBedrooms());
                   }
                    if (!numberOfBedrooms.equals("\\Cancel")){
                        if (!verifyAnswers(businessType, propertyType, numberOfBedrooms) && numberOfBedrooms.equals("n"))  {
                            displayAndSelectOtherSortOptions();
                        } else {
                            propertyFilter = (displayPropertyFiltersList());
                            if (!propertyFilter.equals("0")){
                                properties = getListOfProperties(properties, businessType, propertyType, numberOfBedrooms);
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
    public String displayConfirmationToFilter(){
        return  displayOptionYesOrNo("\u001B[35mDo you wish to filter this list of properties?");
    }

    /**
     * This method display the list of possible options to filter the properties on sale or on rent.
     */
    private void displayAndSelectOtherSortOptions(){
        List<Property> pr1= displayPropertiesOnSale();
        System.out.println();
        String resp = selectOtherSortOptions("\n\u001B[35mDo you pretend to sort all the properties that are on sale?");
        if(!resp.equals("n")){
            List<Property> p = getListOfPropertiesByPorpertyFilter(pr1, resp);
            displayListOfProperties(p);
        }
        if (resp.equals("n")){
            List<Property> pr2 = displayPropertiesOnRent();
            System.out.println();
            if (!pr2.isEmpty()){
                String response = selectOtherSortOptions("\n\u001B[35mDo you pretend to sort all the properties that are on rent?");
                if (!response.equals("n")){
                    List<Property> p = getListOfPropertiesByPorpertyFilter(pr2, response);
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
     * @param businessType     the business type
     * @param propertyType     the property type
     * @param numberOfBedrooms the number of bedrooms
     * @return true if none of the filters are null
     */
    public boolean verifyAnswers(String businessType, String propertyType, String numberOfBedrooms){
        Integer bTSize = getController().getBusinessTypes().size()+1;
        Integer pTSize = getController().getPropertyType().size()+1;
        return !businessType.equals(String.valueOf(bTSize)) && !propertyType.equals(String.valueOf(pTSize)) && !numberOfBedrooms.equals("n");
    }


    /**
     * This method display the property filters list and saves the chosen property filter.
     *
     * @param s the s
     * @return chosen property filter.
     */
    public String selectOtherSortOptions (String s){
        String response = displayOptionYesOrNo(s);
        String description = null;
        if (response.equals("y")){
            description= displayPropertyFiltersList();
        }else return String.valueOf(response);
        return description;
    }


    /**
     * This method display yes or no options and saves the answer.
     *
     * @param string the string
     * @return answer. string
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


    /**
     * This method gets the list of properties according to the chosen business type, property type and number of bedrooms
     * @param businessType
     * @param propertyType
     * @param numberOfBedrooms
     * @return filtered list of properties
     */
    private List<Property> getListOfProperties(List<Property> properties, String businessType, String propertyType, String numberOfBedrooms){

        List<Property> newProperty= getController().getPropertyRepository().getPropertyList(properties, businessType, propertyType, numberOfBedrooms);

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
        newProperty = getController().getPropertyRepository().sortPropertyList(answer, newProperty);
        return newProperty;
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
                while (!useFilter.equals("y") && !useFilter.equals("n") && !useFilter.equals("0")) {
                    useFilter = Utils.readLineFromConsole("Do you want to use this filter (y/n):");
                    if (!useFilter.equals("y") && !useFilter.equals("n") && !useFilter.equals("0")) System.out.println("\u001B[41mInvalid Option.\u001B[0m");
                    else {
                        switch (useFilter){
                            case "y": break;
                            case "n": return "n";
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

