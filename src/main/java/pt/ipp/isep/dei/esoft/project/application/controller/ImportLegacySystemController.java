package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.service.DataIntegrityChecker;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * The type Import legacy system controller.
 */
public class ImportLegacySystemController {

    private final int CLIENT_VALUES = 5;
    private final int PROPERTY_VALUES =14;
    private final int ANNOUNCEMENT_VALUES = 5;

    private final int STORE_VALUES = 5;
    private ClientRepository clientRepository = null;

    private StoreRepository storeRepository = null;

    private AnnouncementRepository announcementRepository = null;
    private PropertyRepository propertyRepository = null;
    private PropertyTypeRepository propertyTypeRepository = null;
    private BusinessTypeRepository businessTypeRepository = null;
    private PurchaseOrderRepository purchaseOrderRepository = null;
    private AuthenticationRepository authenticationRepository = null;

    private DataIntegrityChecker dataIntegrityChecker = new DataIntegrityChecker();
    /**
     * The Legacy file.
     */
    File legacyFile = new File("src\\main\\resources\\legacy.properties");
    private Scanner scanner = new Scanner(legacyFile);
    private List<String> arrayComplete = new ArrayList<String>() {{
        add(scanner.nextLine());
    }};


    private String[] arrayHeaders = arrayComplete.get(0).split(";");

    /**
     * Instantiates a new RegisterStoreController.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public ImportLegacySystemController() throws FileNotFoundException {
        getClientRepository();
        getStoreRepository();
        getPropertyRepostiroy();
        getAuthenticationRepository();
        getAnnouncementRepository();
        getPurchaseOrderRepository();
    }

    /**
     * Instantiates a new RegisterStoreController with the given employee and authentication repositories.
     *
     * @param clientRepository         the employee repository
     * @param authenticationRepository the authentication repository
     * @throws FileNotFoundException the file not found exception
     */
    public ImportLegacySystemController(ClientRepository clientRepository, AuthenticationRepository authenticationRepository) throws FileNotFoundException {

        this.clientRepository = clientRepository;
        this.authenticationRepository = this.authenticationRepository;

    }
    /**
     * Retrieves the ClientRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The ClientRepository instance.
     */

    private ClientRepository getClientRepository(){
        if(clientRepository == null){
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }
    /**
     * Retrieves the StoreRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The StoreRepository instance.
     */

    private StoreRepository getStoreRepository(){
        if(storeRepository == null){
            Repositories repositories = Repositories.getInstance();
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    /**
     * Retrieves the PropertyTypeRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The PropertyTypeRepository instance.
     */


    private PropertyTypeRepository getPropertyTypeRepository(){
        if(propertyTypeRepository == null){
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    private PurchaseOrderRepository getPurchaseOrderRepository(){
        if(purchaseOrderRepository == null){
            Repositories repositories = Repositories.getInstance();
            purchaseOrderRepository = repositories.getPurchaseOrderRepository();
        }
        return purchaseOrderRepository;
    }

    /**
     * Retrieves the BusinessTypeRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The BusinessTypeRepository instance.
     */

    private BusinessTypeRepository getBusinessTypeRepository(){
        if(businessTypeRepository == null){
            Repositories repositories = Repositories.getInstance();
            businessTypeRepository = repositories.getBusinessTypeRepository();
        }
        return businessTypeRepository;
    }

    /**
     * Retrieves the PropertyRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The PropertyRepository instance.
     */

    private PropertyRepository getPropertyRepostiroy(){
        if(propertyRepository == null){
            Repositories repositories = Repositories.getInstance();
            propertyRepository = repositories.getPropertyRepository();
        }
        return propertyRepository;
    }

    /**
     * Retrieves the AnnouncementRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The AnnouncementRepository instance.
     */

    private AnnouncementRepository getAnnouncementRepository(){
        if(announcementRepository == null){
            Repositories repositories = Repositories.getInstance();
            announcementRepository = repositories.getAnnouncementRepository();
        }
        return announcementRepository;
    }
    /**
     * Retrieves the AuthenticationRepository instance.
     * If the repository is not yet initialized, it initializes it using the Repositories singleton.
     *
     * @return The AuthenticationRepository instance.
     */

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Imports data from a CSV file.
     *
     * @param file The CSV file to import.
     * @return True if the import was successful, false otherwise.
     */
    public boolean importFile(File file) {
        String csvFile = file.getPath();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            // Reads the first line that contains the headers
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(";");

            for (String header: headers) {
                boolean verify = false;
                for (String headerStatic: arrayHeaders) {
                    if(header.compareToIgnoreCase(headerStatic) == 0){
                        verify=true;
                        break;
                    }
                }
                if(!verify){
                    return false;
                }

            }

            Announcement announcement = new Announcement();

            // Store headers in a list

            List<String> headerList = Arrays.asList(headers);

            String line;

            while ((line = reader.readLine()) != null) {

                boolean verifyImport = true;
                Client owner;
                Store store;
                Property property;


                if(!validateAllBeforeSubmit()){
                    return false;
                }
                // Step 1 - Read and Validate Client
                String[] values = line.split(";");


                String [] clientValues = new String[CLIENT_VALUES];
                String[] address = dataIntegrityChecker.putInAddressType(returnAddress("property_location",headers,values));

                for (int i = 0; i < CLIENT_VALUES; i++) {
                    clientValues[i] = dataIntegrityChecker.checkAllData(arrayHeaders[i+1],headers,values);
                }

                clientValues[1] = clientValues[1].replaceAll("'","\'");

                clientValues[3] = clientValues[3].replaceAll("\'","");




                //Step 2 - Read and Validate Store
                if( dataIntegrityChecker.validateExistenceClient(clientValues[3]) && dataIntegrityChecker.validateClientData(clientValues,address) ){


                    //User for test the validation
                    owner = clientRepository.getLast();

                    int pointer = CLIENT_VALUES + PROPERTY_VALUES + ANNOUNCEMENT_VALUES;

                    String [] storeValues = new String[STORE_VALUES];

                    for (int i = 0; i < STORE_VALUES; i++) {
                        storeValues[i] = dataIntegrityChecker.checkAllData(arrayHeaders[pointer+i+1],headers,values);
                    }

                    verifyImport = dataIntegrityChecker.validateStoreData(storeValues,address);


                    //Step 3 - Read and Validate Property
                    if(verifyImport){
                        int pointer2 = CLIENT_VALUES ;

                        String [] propertyValues = new String[PROPERTY_VALUES];

                        for (int i = 0; i < PROPERTY_VALUES; i++) {
                            propertyValues[i] = dataIntegrityChecker.checkAllData(arrayHeaders[pointer2+i+1],headers,values);

                        }

                        getPropertyTypeRepository();

                        verifyImport = dataIntegrityChecker.validatePropertyData(propertyValues,address);
                        property = propertyRepository.getLast();

                        //Step 4 - Read and Validate Announcement
                        if(verifyImport){
                            int pointer3 = CLIENT_VALUES + PROPERTY_VALUES ;

                            String [] annoucementValues = new String[ANNOUNCEMENT_VALUES];

                            for (int i = 0; i < ANNOUNCEMENT_VALUES; i++) {
                                annoucementValues[i] = dataIntegrityChecker.checkAllData(arrayHeaders[pointer3+i+1],headers,values);
                            }

                            try{
                                Repositories.getInstance().getStoreRepository().getStoreByDescription("Legacy Store");
                            }
                            catch(Exception e){
                                Address addressObj = new Address(address);
                                addLegacyAgent(addressObj);
                            }

                            getBusinessTypeRepository();

                            verifyImport = dataIntegrityChecker.validateAnnouncementData(annoucementValues);
                            if(verifyImport){

                            //Step 5 - Read and Validate Announcement
                            int pointer4 = CLIENT_VALUES + PROPERTY_VALUES - 1 ;

                            String [] purchaseOrderValues = new String[2];

                            for (int i = 0; i < 1; i++) {
                            }
                                for (int i = 0; i < 1; i++) {

                                }
                                purchaseOrderValues[0] = dataIntegrityChecker.checkAllData(arrayHeaders[pointer4+1],headers,values);

                                purchaseOrderValues[1] = dataIntegrityChecker.checkAllData(arrayHeaders[pointer3+4],headers,values);


                            verifyImport = dataIntegrityChecker.validataPurchaseOrderData(purchaseOrderValues);

                                if(verifyImport){

                                dataIntegrityChecker.submitClientData(clientValues,address,clientRepository);


                                if(dataIntegrityChecker.validateStoreID(storeValues[0])){
                                    dataIntegrityChecker.submitStoreData(storeValues,storeRepository);
                                    store = storeRepository.getLast();
                                }
                                else{
                                    store = storeRepository.getStoreByID(Integer.parseInt(storeValues[0]));
                                }

                                dataIntegrityChecker.submitPropertyData(propertyValues,address,store,owner, propertyRepository, propertyTypeRepository);

                                property = propertyRepository.getLast();


                                dataIntegrityChecker.submitAnnouncementData(annoucementValues,property,announcementRepository,businessTypeRepository);

                                announcement = announcementRepository.getLast();


                                dataIntegrityChecker.submitPurchaseOrderData(purchaseOrderValues,purchaseOrderRepository,announcement,address);

//                                dataIntegrityChecker.

                                //TODO:Purchase Order Validation and Submition with announcement



                                }
                                else{
                                    showError(values[0],"Purchase Order");
                                }
                            }
                            else{
                                showError(values[0],"Annoucement");
                            }

                        }
                        else{
                            showError(values[0],"Property");
                        }
                    }
                    else{
                        showError(values[0],"Store");
                    }
                }
                else{
                    showError(values[0],"Client");
                }

//                System.out.println("-------------------------");


            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean validateAllBeforeSubmit() {
        return true;
    }

    private void addLegacyAgent(Address address) {
        Store legacyStore = new Store(0,"Legacy Store","legacystore@realstateUSA.com",address,000000000);
//            Store legacyStore = Repositories.getInstance().getStoreRepository().getStoreByID(0);

        List<Role> role = new ArrayList<>();
        role.add(Repositories.getInstance().getRoleRepository().getRoleByDescription("AGENT"));
        Employee legacyAgent = new Employee("Legacy Agent","legacy@realstateUSA.com",000000000,"000000000" ,address,0000000000,legacyStore,role);
        Repositories.getInstance().getEmployeeRepository().add(legacyAgent);
    }

    /**
     * Displays an error message with the provided value and cause.
     *
     * @param value The value associated with the error.
     * @param cause The cause of the error.
     */

    private void showError(String value, String cause) {

        System.out.println("\u001B[31m" +value + ". this information is not correct because of the " + cause + "\u001B[0m");
        System.out.println("-------------------------");

    }


    /**
     * Retrieves the address from the values array based on the specified location header.
     *
     * @param location The location header to search for.
     * @param headers  The array of headers.
     * @param values   The array of values corresponding to the headers.
     * @return The address extracted from the values array.
     */
    private String[] returnAddress(String location, String[] headers, String[] values){

        String localFromFile= null;
        int i=0;

        for (String header: headers) {
            boolean verify = false;
            if(header.compareToIgnoreCase(location)==0){
                localFromFile = values[i];
                break;
            }
            i++;
        }

        return localFromFile.split(",");
    }







}
