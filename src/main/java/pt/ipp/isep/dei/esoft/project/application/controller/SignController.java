package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;
import java.util.Optional;


/**
 * The SignController provides methods for signing a new client.
 */
public class SignController {


    private ClientRepository clientRepository = null;

    /**
     * Constructs a new SignController and initializes the necessary repositories.
     */
    public SignController() {
        getClientRepository();
    }

    /**
     * Constructs a new SignController with the given repository.
     *
     * @param clientRepository The repository for managing clients.
     */
    public SignController(ClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }


    /**
     * Returns the EmployeeRepository instance, initializing it if necessary.
     *
     * @return The EmployeeRepository instance.
     */

    private ClientRepository getClientRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the EmployeeRepository
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;

    }


    /**
     * Creates a new client with the given name, email, passport number, tax number, address, phone number,
     * and password, and adds it to the client repository. If a client with the same email address already exists
     * in the repository, no client is created and an empty optional is returned instead.
     *
     * @param name           the name of the client
     * @param email          the email address of the client
     * @param passportNumber the passport number of the client
     * @param taxNumber      the tax number of the client
     * @param address        the address of the client
     * @param phoneNumber    the phone number of the client
     * @param password       the password of the client
     * @return an optional containing the newly created client, or an empty optional if a client with the same         email address already exists in the repository
     */
    public Optional<Client> create(String name, String email, int passportNumber, String taxNumber, Address address, long phoneNumber, String password) {

        int br = 0;

        for ( Client cl : clientRepository.getClients()) {
            if(cl.hasEmail(email)){
                br = 1;
            }

        }


        Optional<Client> client = Optional.empty();

    if (br == 0) {   client = clientRepository.create( name,  email,  passportNumber, taxNumber, address, phoneNumber, password); }



        return client;
    }

}
