package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.ClientRepository;
import pt.ipp.isep.dei.esoft.project.repository.VisitRequestRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Reply to agent about visit request controller test.
 */
public class ReplyToAgentAboutVisitRequestControllerTest {
    private ReplyToAgentAboutVisitRequestController controller;

    /**
     * The Client.
     */
    static Client client = new Client("Ana",  "test@example.com", 3, "00000003",new Address("teste","teste","teste","teste"),0000003);

    /**
     * The Address property.
     */
    static Address addressProperty = new Address("streetAddress", "New York", "Texas", "zipCode");
    /**
     * The Property 1.
     */
    static Property property_1 = new Property(100.2, 5.3, addressProperty,
                13.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("House"),
            new Store(101, "ESOFT","esoft@this.app", new Address("teste","teste","teste","teste"),  000000001), client, new DateTime(24, 4, 2023));
    /**
     * The List.
     */
    static List<Role> list = new ArrayList<>();
    /**
     * The Address.
     */
    static Address address= new Address("aaaaaa","aaaaaaa","aaaaaaaaa","aaaaaaaa");

    /**
     * The Employee.
     */
    static Employee employee = new Employee("AgentAD", "agent@this.app", 12321, "3213123", address,121212, new Store(100, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),  000000000), list);
    /**
     * The Agent.
     */
    static Agent agent = new Agent(employee);
    /**
     * The Announcement.
     */
    static Announcement announcement =  new Announcement(employee.getAssociatedAgent(), new DateTime(7,5,1995), 8.9,  new BusinessType("Sale", "Buy"),
               property_1, 100.5 );


    /**
     * Sets up the controller instance before each test.
     */
    @BeforeEach
    public void setup() {
        controller = new ReplyToAgentAboutVisitRequestController();
    }

    /**
     * Tests the getvisitRequestRepository() method of ReplyToAgentAboutVisitRequestController.
     * Verifies that the returned VisitRequestRepository instance is not null.
     */
    @Test
    public void testGetVisitRequestRepository() {
        VisitRequestRepository visitRequestRepository = controller.getvisitRequestRepository();
        Assertions.assertNotNull(visitRequestRepository);
    }

    /**
     * Tests the getVisitRequest() method of ReplyToAgentAboutVisitRequestController.
     * Verifies that the returned list of visit requests is not null.
     */
    @Test
    public void testGetVisitRequest() {
        List<VisitRequest> visitRequests = controller.getVisitRequest();
        Assertions.assertNotNull(visitRequests);
    }

    /**
     * Tests the createEmail() method of ReplyToAgentAboutVisitRequestController.
     * Verifies that the email is created and sent without any exceptions.
     */
    @Test
    public void testCreateEmail() {
        String filterGmail = "filter";
        String sender = "sender@example.com";
        String receiver = "receiver@example.com";
        String subject = "Test Email";
        String message = "This is a test email.";
        Assertions.assertDoesNotThrow(() -> controller.createEmail(filterGmail, sender, receiver, subject, message));
    }

    /**
     * Tests the getClientRepository() method of ReplyToAgentAboutVisitRequestController.
     * Verifies that the returned ClientRepository instance is not null.
     */
    @Test
    public void testGetClientRepository() {
        ClientRepository clientRepository = controller.getClientRepository();
        Assertions.assertNotNull(clientRepository);
    }

    /**
     * Tests the getClientList() method of ReplyToAgentAboutVisitRequestController.
     * Verifies that the returned list of clients is not null.
     */
    @Test
    public void testGetClientList() {
        List<Client> clients = controller.getClientList();
        Assertions.assertNotNull(clients);
    }

//    /**
//     * Tests the setVisitRequestClientReply() method of ReplyToAgentAboutVisitRequestController.
//     * Verifies that the client's reply is set without any exceptions.
//     */
//    @Test
//    public void testSetVisitRequestClientReply() {
//        VisitRequest visitRequest = new VisitRequest(property_1, new DateTime(15, 04, 2024), List.of(new DateTime(14, 55), new DateTime( 15, 55)),client.getName(), client.getPhoneNumber() );
//        String answer = "Reply";
//        Assertions.assertDoesNotThrow(() -> controller.setVisitRequestClientReply(visitRequest, answer));
//        Assertions.assertEquals(answer, visitRequest.getClientReply());
//    }
}
