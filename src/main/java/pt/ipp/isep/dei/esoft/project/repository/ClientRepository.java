package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.ClientDTO;
import pt.ipp.isep.dei.esoft.project.mapper.ClientMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A repository class for managing all Clients.
 */
public class ClientRepository {

    private final List<Client> clients = new ArrayList<>();

    /**
     * Retrieves a client from the list of clients by the email address.
     *
     * @param email the email address of the client to retrieve
     * @return the client with the given email address
     */
    public Client getClientByEmail(String email){
        Client client = null;

        for (Client clientCheck : List.copyOf(clients)) {
            if (clientCheck.hasEmail(email)) {
                client = clientCheck;
            }
        }
        if (client == null) {
            throw new IllegalArgumentException(
                    "Client requested for [" + email + "] does not exist.");
        }
        return client;

    }

    /**
     * Adds a new client to the collection of clients managed by this repository.
     *
     * @param client the client to be added to the collection.
     * @return an Optional containing the added client, or an empty Optional if the operation fails
     */


    public Optional<Client> add(Client client) {

        Optional<Client> newClient = Optional.empty();
        boolean operationSuccess = false;

        if (validateClient(client)) {
            newClient = Optional.of(client.clone());
            operationSuccess = clients.add(newClient.get());
        }

        if (!operationSuccess) {
            newClient = Optional.empty();
        }

        return newClient;
    }

    /**
     * Validates if a given client is valid to be added to the repository.
     *
     * @param client the client to be validated
     * @return true if the client is valid for addition, false otherwise
     */
    private boolean validateClient(Client client) {
        boolean isValid = !clients.contains(client);
        return isValid;
    }


    /**
     * Returns an immutable list of clients.
     *
     * @return an immutable list of clients
     */
    public List<Client> getClients(){
        return List.copyOf(clients);

    }

    /**
     * Creates a new client with the provided information and adds it to the client repository.
     * Also adds the client to the authentication repository as a user with the "client" role.
     *
     * @param name           the name of the client
     * @param email          the email of the client
     * @param passportNumber the passport number of the client
     * @param taxNumber      the tax number of the client
     * @param address        the address of the client
     * @param phoneNumber    the phone number of the client
     * @param pass           the password of the client's account
     * @return an Optional containing the new client if the creation was successful, or an empty Optional otherwise
     */
    public Optional<Client> create(String name, String email, int passportNumber, String taxNumber, Address address, long phoneNumber, String pass) {

        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();

        Client clientClone = new Client(name,email,passportNumber,taxNumber,address,phoneNumber);

        Optional<Client> newClient = Optional.of(clientClone.clone());

        clientRepository.add(clientClone);

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        authenticationRepository.addUserWithRole(name, email, pass, AuthenticationController.ROLE_CLIENT);


        return newClient;

    }


    /**
     * Create optional.
     *
     * @param clientDTO the client dto
     * @param pass      the pass
     * @return the optional
     */
    public Optional<Client> create(ClientDTO clientDTO, String pass) {

        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();

        Client clientClone = ClientMapper.toEntity(clientDTO);

        Optional<Client> newClient = Optional.of(clientClone.clone());

        clientRepository.add(clientClone);

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        authenticationRepository.addUserWithRole(clientClone.getName(), clientClone.getEmail(), pass, AuthenticationController.ROLE_CLIENT);


        return newClient;

    }


    /**
     * Retrieves the last client in the list of clients.
     *
     * @return The last client in the list.
     */
    public Client getLast() {

        return clients.get(clients.size()-1);
    }
}