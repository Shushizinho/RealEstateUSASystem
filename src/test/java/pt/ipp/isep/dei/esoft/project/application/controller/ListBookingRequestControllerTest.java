package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ListBookingRequestControllerTest {

    private final ListBookingRequestController controller = new ListBookingRequestController();
    private final VisitRequestRepository visitRequestRepository = Repositories.getInstance().getVisitRequestRepository();
    private final ClientRepository clientRepository = Repositories.getInstance().getClientRepository();
    private final EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();

    StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
    RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();


    private String subject;
    private String message;
    private String sender;
    private String receiver;

    private final String name = "Diogo";
    private final String name1 = "Joao";

    private final String email = "diogo@this.app";
    private final String email1 = "joao@gmail.com";
    private final String email2 = "joao@hotmail.com";

    private final String email3 = "joao@yahoo.com";


    private final int passportNumber = 987123456;
    private final int passportNumber1 = 937123456;

    private final String taxNumber = "121-40-4126";
    private final String taxNumber1 = "121-40-4126";

    private final Address address = new Address("345 Example Street", "The Supreme", "Celestial", "2304-0404");
    private final Address address1 = new Address("345 Example Street", "The Supreme", "Celestial", "2304-0404");

    private final long phoneNumber = 912232412;
    private final long phoneNumber1 = 915232412;

    Store store = new Store(1, "Café", "cafe@store.ola", new Address("345 Example Street", "The Supreme", "Celestial", "2304-0404"), 912292992);


    List<Role> role = new ArrayList<>();
    Role role_test = new Role(1, AuthenticationController.ROLE_AGENT);






    Employee employee_test = new Employee(name, email, passportNumber, taxNumber, address, phoneNumber, store, role);

    Client client_test = new Client(name1, email1, passportNumber1, taxNumber1, address1, phoneNumber1);
    private static final String filterGmail = "filter.term.gmail";


    @Test
    void createEmailGmail() {
        subject = "Email create";
        message = "Yes";
        role.add(role_test);
        sender = email;
        receiver = email1;

        controller.createEmail(sender, receiver, subject, message);
    }

    @Test
    void createEmailHotmail() {
        subject = "Email create";
        message = "Yes";
        role.add(role_test);
        sender = email;
        receiver = email3;

        controller.createEmail(sender, receiver, subject, message);
    }

    @Test
    void createEmailYahoo() {
        subject = "Email create";
        message = "Yes";
        role.add(role_test);
        sender = email;
        receiver = email2;

        controller.createEmail(sender, receiver, subject, message);
    }

    @Test
    @DisplayName("Test if the list of properties in repository is not null ")
    public void getVisitRequestRepositoryTest() {
        assertNotNull(visitRequestRepository);
    }

    @Test
    @DisplayName("Test if the list of clients in repository is not null ")
    public void getClientRepositoryTest() {
        assertNotNull(clientRepository);
    }

    @Test
    @DisplayName("Test if the list of clients in repository is not null ")
    public void getEmployeeRepositoryTest() {
        assertNotNull(employeeRepository);
    }

    @Test
    public void testGetClientList() {
        List<Client> clients = controller.getClientList();
        Assertions.assertNotNull(clients);
    }

















}