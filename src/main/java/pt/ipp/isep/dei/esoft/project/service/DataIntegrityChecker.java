package pt.ipp.isep.dei.esoft.project.service;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Data integrity checker.
 */
public class DataIntegrityChecker {


    /**
     * Modifies the address array to conform to a specific format.
     *
     * @param address The address array to modify.
     * @return The modified address array.
     */
    public String[] putInAddressType(String[] address) {

        if (address.length>=7){
            for (int i = 3; i > 0; i--) {
                address[i-1]+=address[i];
                address[i]= "";
            }

            for (int i = 0; i < address.length-4; i++) {
                address[i+1]=address[4+i];
                address[4+i]="";
            }

        }

        if(address.length>=5 && address.length!=7){
            int ko=2;
            address[ko]+=address[ko+1];
            address[ko+1]=address[ko+2];
            address[ko+2]="";
        }

        return address;
    }


    /**
     * Retrieves the value corresponding to the specified search header from the values array.
     *
     * @param search  The header to search for.
     * @param headers The array of headers.
     * @param values  The array of values corresponding to the headers.
     * @return The value associated with the search header.
     */
    public String checkAllData(String search, String[] headers, String[] values){

        String value= null;
        int i=0;

        for (String header: headers) {
            boolean verify = false;
            if(header.compareToIgnoreCase(search)==0){
                value = values[i];
                break;
            }
            i++;
        }
        return value;
    }

    /**
     * Submits client data and creates a new client if all the data is valid.
     *
     * @param clientData       The array of client data.
     * @param address          The array of address data.
     * @param clientRepository the client repository
     * @return {@code true} if the client data is valid and a client is created, {@code false} otherwise.
     */
    public boolean submitClientData(String[] clientData, String[] address, ClientRepository clientRepository) {
        String password = "legacy";

            Address addressObj = new Address(address);

            Optional<Client> client = clientRepository.create( clientData[0],  clientData[3] , Integer.parseInt(clientData[1]), clientData[2], addressObj, Long.parseLong(clientData[4].replaceAll("-", "")), password);

            if (client.isPresent()) {
//                System.out.println("Client successfully created!");
            } else {
                System.out.println("Client not created!");
            }

        return true;
    }


    /**
     * Validates all the client data and address for creating a new client.
     *
     * @param clientData The array of client data.
     * @param address    The array of address data.
     * @return {@code true} if all the client data and address are valid, {@code false} otherwise.
     */
    public boolean validateClientData(String[] clientData, String[] address){


        //Validate Email
        try{
            boolean pointEmail = false;
            pointEmail = isValidEmail(clientData[3]);
            if(!pointEmail){
                return false;
            }
        }
        catch(Exception e){
            return false;
        }


        //Validate Tax Number
        try{
            boolean pointTax = false;
            pointTax = isValidTax(clientData[2]);
            if(!pointTax){
                return false;
            }
        }
        catch(Exception e){
            return false;
        }


        //Validate Passport Number
        try{
            int passport=countDigits(Integer.parseInt(clientData[1]));
            if (passport!=9){
                return false;
            }
        }
        catch(Exception e){
            return false;
        }

        //Validate Phone Number
        try{
            clientData[4] = clientData[4].replaceAll("-","");
            int num = countDigits(Long.parseLong(clientData[4]));
            if (num<9){
                return false;
            }
        }
        catch(Exception e){
            return false;
        }

        //Validate Zip
        try{
            boolean pointZip = true;
            pointZip = isAlpha(address[3]);
            if(pointZip){
                return false;
            }

        }
        catch(Exception e){
            return false;
        }


        return true;
    }


    /**
     * Submits store data and creates a new store if all the data is valid.
     *
     * @param storeValues     The array of store data.
     * @param storeRepository the store repository
     * @return {@code true} if the store data is valid and a store is created, {@code false} otherwise.
     */
    public boolean submitStoreData(String[] storeValues, StoreRepository storeRepository) {
        String[] splited = storeValues[2].split(",");
        String[] address = putInAddressType(splited);

            Address addressObj = new Address(address);
            Optional<Store> store = storeRepository.createStore(Integer.parseInt(storeValues[0]),  storeValues[1], storeValues[4],addressObj  , Long.parseLong(storeValues[3]));

            if (store.isPresent()) {
//                System.out.println("Store successfully created!");
            } else {
                System.out.println("Store not created!");
            }


        return true;

    }


    /**
     * Validate store id boolean.
     *
     * @param storeV the store v
     * @return the boolean
     */
    public boolean validateStoreID (String storeV){
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        Integer id = Integer.parseInt(storeV);

        for(Store store : storeRepository.getStores()){
            if(id==store.getId()){
                return false;
            }
        }

        return true;
    }


    /**
     * Submits store data and creates a new store if all the data is valid.
     *
     * @param storeValues The array of store data.
     * @param address     the address
     * @return {@code true} if the store data is valid and a store is created, {@code false} otherwise.
     */
    public boolean validateStoreData(String[] storeValues, String[] address) {


        //Validate ID


        Integer id = Integer.parseInt(storeValues[0]);
        boolean validid = false;



        try{


            if (id == 0 || id < 0 || id == null){
                return false;
            }else{
                validid = true;
            }

        } catch (Exception e) {
            return false;
        }

        if (!validid){
            return false;
        }


        //Validate Email
        try{
            boolean pointEmail = false;
            pointEmail = isValidEmail(storeValues[4]);
            if(!pointEmail){
                return false;
            }
        }
        catch(Exception e){
            return false;
        }


        //Validate Phone Number
        try{
            storeValues[3] = storeValues[3].replaceAll("-","");
            int num = countDigits(Long.parseLong(storeValues[3]));
            if (num<9){
                return false;
            }
        }
        catch(Exception e){
            return false;
        }



        //Validate Zip
        try{
            boolean pointZip = true;
            pointZip = isAlpha(address[3]);
            if(pointZip){
                return false;
            }

        }
        catch(Exception e){
            return false;
        }


        return true;
    }


    /**
     * Submits property data and creates a new property if all the data is valid.
     *
     * @param propertyValues         The array of property data.
     * @param address                the address
     * @param store                  the store
     * @param owner                  the owner
     * @param propertyRepository     the property repository
     * @param propertyTypeRepository the property type repository
     * @return {@code true} if the property data is valid and a property is created, {@code false} otherwise.
     */
    public boolean submitPropertyData(String[] propertyValues, String[] address, Store store, Client owner, PropertyRepository propertyRepository, PropertyTypeRepository propertyTypeRepository) {

            PropertyType propertyTypeSub = propertyTypeRepository.getPropertyTypeByDescription(propertyValues[0].substring(0, 1).toUpperCase() + propertyValues[0].substring(1)) ;
            Address addressObj = new Address(address);
            List<Photograph> photos = new ArrayList<>();
            Property property;
            PropertyDTO propertyDTO = null;
            List<String> equipmentList = new ArrayList<>();

            if (propertyValues[5].compareTo("NA") == 0 || propertyValues[5] == ""){
                propertyRepository.createProperty(Double.parseDouble(propertyValues[12]), Double.parseDouble(propertyValues[1]), addressObj, Double.parseDouble(propertyValues[3])
                        , photos, propertyTypeSub , store,owner , new DateTime());
            }
            else {
                equipmentList.add(equipmentListAdd(propertyValues, 7,"centralHeating"));
                equipmentList.add(equipmentListAdd(propertyValues, 8,"airconditioned"));
                Iterator<String> iterator = equipmentList.iterator();
                while (iterator.hasNext()) {
                    String element = iterator.next();
                    if (element.isEmpty()) {
                        iterator.remove();
                    }
                }

                if(propertyTypeSub.getDescription().compareTo("House")==0){

                    propertyRepository.createProperty(Double.parseDouble(propertyValues[12]), Double.parseDouble(propertyValues[1]), addressObj, Double.parseDouble(propertyValues[3])
                            , photos, Integer.parseInt(propertyValues[4]), Integer.parseInt(propertyValues[5]), Integer.parseInt(propertyValues[6]),equipmentList,validateIfExist(propertyValues,9),
                            validateIfExist(propertyValues,10), propertyValues[11], propertyTypeSub , store,owner , new DateTime() );

                }
                else{

                    propertyRepository.createProperty(Double.parseDouble(propertyValues[12]), Double.parseDouble(propertyValues[1]), addressObj, Double.parseDouble(propertyValues[3])
                            , photos, Integer.parseInt(propertyValues[4]), Integer.parseInt(propertyValues[5]), Integer.parseInt(propertyValues[6]),equipmentList,validateIfExist(propertyValues,9),
                            validateIfExist(propertyValues,10), propertyValues[11], propertyTypeSub , store,owner , new DateTime() );

                }




            }




        return true;
    }


    /**
     * Submits announcement data and creates a new announcement if all the data is valid.
     *
     * @param announcementValues     The array of property data.
     * @param property               the property
     * @param announcementRepository the announcement repository
     * @param businessTypeRepository the business type repository
     * @return {@code true} if the announcement data is valid and a announcement is created, {@code false} otherwise.
     */
    public boolean submitAnnouncementData(String[] announcementValues, Property property, AnnouncementRepository announcementRepository, BusinessTypeRepository businessTypeRepository) {

            BusinessType bussinessTypeSub = businessTypeRepository.getBusinessTypeByDescription(announcementValues[4].substring(0, 1).toUpperCase() + announcementValues[4].substring(1),false);
            String[] date = announcementValues[2].replaceAll("-","/").split("/");
            DateTimeDTO dateTime = new DateTimeDTO(Integer.parseInt(date[0]),Integer.parseInt(date[1]   ),Integer.parseInt(date[2]));

            Employee legacyAgentE = Repositories.getInstance().getEmployeeRepository().getEmployeeByEmail("legacy@realstateUSA.com");
            Agent legacyAgent = Repositories.getInstance().getEmployeeRepository().getAgentObject(legacyAgentE);
            AgentDTO legacyDTO = AnnouncementMapper.employeeToEmployeeDTO(legacyAgent);;
            BusinessTypeDTO businessTypeDTO= AnnouncementMapper.businessTypeToBusinessTypeDTO(bussinessTypeSub);
            PropertyDTO propertyDTO= AnnouncementMapper.propertyToPropertyDTO(property);

            if (announcementValues[1].compareTo("NA")==0) announcementValues[1] = "0";


            AnnouncementDTO announcementDTO = new AnnouncementDTO(legacyDTO,dateTime,Double.parseDouble(announcementValues[0]),businessTypeDTO,propertyDTO,Integer.parseInt(announcementValues[1]),property.getPrice());

            Optional<Announcement> announcement =  announcementRepository.createAnnouncement(announcementDTO);

                if (announcement.isPresent()) {
                    return true;
                } else {
                    return false;

                }
    }


    /**
     * Submit purchase order data boolean.
     *
     * @param purchaseOrderValues     the purchase order values
     * @param purchaseOrderRepository the purchase order repository
     * @param announcement            the announcement
     * @param address                 the address
     * @return the boolean
     */
    public boolean submitPurchaseOrderData(String[] purchaseOrderValues, PurchaseOrderRepository purchaseOrderRepository , Announcement announcement, String[] address) {

        String[] date = purchaseOrderValues[1].replaceAll("-","/").split("/");
        DateTime dateTime = new DateTime(Integer.parseInt(date[0]),Integer.parseInt(date[1]   ),Integer.parseInt(date[2]));

        Address addressObj = new Address(address);

        Client legacyClient = new Client("legacyClient","legacyClient@realstateUSA.com",00000000,"123-12-3333",addressObj,912312376);

        PurchaseOrder purchaseOrder = purchaseOrderRepository.placeOrder(Double.parseDouble(purchaseOrderValues[0]),announcement,legacyClient,1,dateTime);


        return true;
    }


    /**
     * Validate announcement data boolean.
     *
     * @param announcementValues the announcement values
     * @return the boolean
     */
    public boolean validateAnnouncementData(String[] announcementValues) {

        return true;
    }

    /**
     * Validata purchase order data boolean.
     *
     * @param purchaseOrderValue the purchase order value
     * @return the boolean
     */
    public boolean validataPurchaseOrderData(String[] purchaseOrderValue) {

        return true;
    }

    /**
     * Validate property data boolean.
     *
     * @param propertyValues the property values
     * @param address        the address
     * @return the boolean
     */
    public boolean validatePropertyData(String[] propertyValues, String[] address) {

        return true;
    }

    /**
     * Validates if a property value exists based on the provided condition.
     *
     * @param propertyValues The array of property values.
     * @param i              The index of the value to validate.
     * @return true if the value exists (equals "Y"), false otherwise.
     */
    public boolean validateIfExist(String[] propertyValues, int i) {
        if(propertyValues[i].compareTo("Y")==0){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Adds a string to the equipment list based on the value of the property at the given index in the property values array.
     *
     * @param propertyValues The array of property values.
     * @param i              The index of the property value to check.
     * @param string         The string to add to the equipment list.
     * @return The updated equipment list if the property value is "Y", an empty string otherwise.
     */
    private String equipmentListAdd(String[] propertyValues, int i, String string) {
        if(propertyValues[i].compareTo("Y") == 0 ){
            return string;
        }
        return "";
    }


    /**
     * Counts the number of digits in a given number.
     *
     * @param num The number for which to count the digits.
     * @return The number of digits in the given number.
     */
    public static int countDigits(long num) {
        int count = 0;
        while (num != 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    /**
     * Checks if a given email address is valid based on a regular expression pattern.
     *
     * @param email The email address to validate.
     * @return true if the email address is valid, false otherwise.
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
     * Checks if a given tax number is valid based on a regular expression pattern.
     *
     * @param tax The tax number to validate.
     * @return true if the tax number is valid, false otherwise.
     */
    public static boolean isValidTax(String tax) {
        String regex = "^\\d{3}-\\d{2}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tax);
        return matcher.matches();
    }

    /**
     * Checks if a given string consists of alphabetic characters only.
     *
     * @param str The string to check.
     * @return true if the string consists of alphabetic characters only, false otherwise.
     */
    public static boolean isAlpha(String str) {
        String regex = "[a-zA-Z]+";
        return str.matches(regex);
    }

    /**
     * Validate existence client boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean validateExistenceClient(String email) {

        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();

        for (Client client: clientRepository.getClients()) {
            if(client.hasEmail(email)){
                return false;
            }
        }


        return true;
    }
}
