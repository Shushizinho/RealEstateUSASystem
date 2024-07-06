package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Sign controller test.
 */
class SignControllerTest {




    private final SignController controller = new SignController();
    private final RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
    private final StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
    private final String name = "Diogo";
    private final String email = "diogo@this.app";
    private final int passportNumber = 987123456;
    private final String taxNumber = "121-40-4126";
    private final Address address = new Address("345 Example Street", "The Supreme", "Celestial", "2304-0404");
    private final long phoneNumber = 912232412;
    private final String password = "clienttest";

    private final String name2 = "Seele";
    private final String email2 = "seele@this.app";
    private final int passportNumber2 = 987765456;
    private final String taxNumber2 = "211-56-1229";
    private final Address address2 = new Address("412 Example Street", "The Star", "Rail", "4404-8972");
    private final long phoneNumber2 = 917672412;
    private final String password2 = "clienttest";

    /**
     * The Client test.
     */
    Client client_test = new Client(name, email, passportNumber, taxNumber, address, phoneNumber);

    /**
     * Create.
     */
    @Test
    @DisplayName("Create a new account as a client that already exists")
    void create(){

        ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
        clientRepository.add(client_test);

        Optional<Client> result = controller.create(name, email, passportNumber, taxNumber, address, phoneNumber,password);

        assertFalse(result.isPresent());
    }

    /**
     * Create if not exists.
     */
    @Test
    @DisplayName("Create a new account as a client")
    void createIfNotExists(){

        Optional<Client> result = controller.create(name2, email2, passportNumber2, taxNumber2, address2, phoneNumber2,password2);

        assertTrue(result.isPresent());
    }

}