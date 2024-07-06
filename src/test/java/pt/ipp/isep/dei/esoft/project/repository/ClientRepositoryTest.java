package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Client;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Client repository test.
 */
class ClientRepositoryTest {

    /**
     * Gets client by email if not exists.
     */
    @Test
    void getClientByEmailIfNotExists() {
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        String email = "aster@this.app";
        assertThrows(IllegalArgumentException.class, () -> {
            clientRepository.getClientByEmail(email);
        });
    }

    /**
     * Gets client by email if exists.
     */
    @Test
    void getClientByEmailIfExists() {
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        String email = "aster@this.app";
        Client expected = new Client("Aster", email, 04133120, "13040404", new Address("123 Example Street", "The Big One", "Lone Star", "1304-0404"), 13040404);
        clientRepository.add( expected );
        Client result = clientRepository.getClientByEmail(email);
        assertEquals(expected,result);
    }

    /**
     * Add if not exists.
     */
    @Test
    void addIfNotExists() {
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        Client client = new Client("Aster", "aster2@this.app", 04133120, "13040404", new Address("123 Example Street", "The Big One", "Lone Star", "1304-0404"), 13040404);
        Optional<Client> result = clientRepository.add( client );
        Optional<Client> expected = Optional.of(client);
        assertEquals(expected,result);
    }

    /**
     * Add if exists.
     */
    @Test
    void addIfExists() {
        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        clientRepository.add( new Client("Aster", "aster3@this.app", 04133120, "13040404", new Address("123 Example Street", "The Big One", "Lone Star", "1304-0404"), 13040404) );
        Client client = new Client("Aster", "aster3@this.app", 04133120, "13040404", new Address("123 Example Street", "The Big One", "Lone Star", "1304-0404"), 13040404);
        Optional<Client> result = clientRepository.add( client );
        assertEquals(Optional.empty(), result);
    }
}