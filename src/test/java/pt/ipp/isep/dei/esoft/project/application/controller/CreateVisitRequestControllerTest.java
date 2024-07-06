package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

import java.util.List;

import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Create visit request controller test.
 */
public class CreateVisitRequestControllerTest {
    private final CreateVisitRequestController controller = new CreateVisitRequestController();
    private final AuthenticationController ctrlAuth = new AuthenticationController();
    private final AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    private final static Client client = new Client("Ana",  "test@example.com", 3, "00000003",new Address("teste","teste","teste","teste"),0000003);

    private final PropertyRepository propertyRepository = Repositories.getInstance().getPropertyRepository();

    private final UserSession currentSession = ApplicationSession.getInstance().getCurrentSession();

    private VisitRequestRepository visitRequestRepository = Repositories.getInstance().getVisitRequestRepository();

    private final ClientRepository clientRepository = Repositories.getInstance().getClientRepository();


    /**
     * The Property 1.
     */
    static PropertyDTO property_1 = (new PropertyDTO(1.2, 5.3, new AddressDTO("streetAddress", "city", "state", "zipCode"),
            13.2, List.of(), new PropertyTypeDTO("House"),
            new StoreDTO(10, "PPROG", new AddressDTO("teste","teste","teste","teste"),"pprog@this.app",  000000000),
            new ClientDTO("Person", "person@this.app", 2, parseLong("00000004"), "00000004",new AddressDTO("teste","teste","teste","teste")), new DateTimeDTO(12, 04, 2024)));


    /**
     * The Visit request dto.
     */
    static VisitRequestDTO visitRequestDTO = new VisitRequestDTO(property_1, new DateTimeDTO(15, 04, 2024), List.of(new DateTime(14, 55), new DateTime( 15, 55)),client.getName(), client.getPhoneNumber() );


    /**
     * Get propertie list by announcemnent test.
     */
//    @Test
    void getPropertieListByAnnouncemnentTest(){

        List<Property> result = Repositories.getInstance().getAnnouncementRepository().getPropertyListByAnnouncements();

        List<Property> expected = controller.getPropertieListByAnnouncemnent();

        assertEquals(expected,result);
    }

    /**
     * Gets property repository test.
     */
//    @Test
    @DisplayName("Test if the list of properties in repository is not null ")
    public void getPropertyRepositoryTest() {
        assertNotNull(propertyRepository);
    }

    /**
     * Gets visit requests repository test.
     */
//    @Test
    @DisplayName("Test if the list of visit requests in repository is not null ")
    public void getVisitRequestsRepositoryTest() {
        assertNotNull(visitRequestRepository);
    }

//    @Test
//    @DisplayName("Test if get client from session is null ")
//    void getClientFromSessionNullSessionReturnsNull() {
//        CreateVisitRequestController controller = new CreateVisitRequestController(null, null);
//
//
//        Client result = controller.getClientFromSession();
//
//
//        assertNull(result);
//    }
//
//    @Test
//    void getClientFromSessionReturnsExpectedClient() {
//
//
//        clientRepository.add(client);
//
//        boolean sucessAdd = authenticationRepository.addUserWithRole("Ana", "test@example.com", "pwd", AuthenticationController.ROLE_CLIENT);
//
//        boolean sucessLogin = ctrlAuth.doLogin("admin@this.app","admin");
//
//        System.out.println("mostra: " + sucessAdd);
//        System.out.println("mostra2: " + sucessLogin);
//
//        Client expectedClient = clientRepository.getClientByEmail(client.getEmail());
//
//
//        Client result = controller.getClientFromSession();
//        assertEquals(expectedClient, result);
//    }
//
//    @Test
//    void CreateCreateVisitRequestControllerTest(){
//        CreateVisitRequestController createVisitRequestController = new CreateVisitRequestController(propertyRepository, currentSession);
//
//        assertEquals(controller.getPropertyRepository(), createVisitRequestController.getPropertyRepository());
//
//    }
//
//    @Test
//    void getVisitRequestsReturnsExpectedVisitRequestsTest(){
//        visitRequestRepository.add(visitRequestDTO);
//        visitRequestRepository = controller.getVisitRequestRepository();
//        List<VisitRequestDTO> visitRequestDTOS = VisitRequestMapper.getVisitRequestsDTO(visitRequestRepository.getVisitRequests());
//
//        List<VisitRequest> visitRequests= new ArrayList<>();
//        for (VisitRequestDTO visitRequestDTO : visitRequestDTOS) {
//            visitRequests.add(VisitRequestMapper.toEntity(visitRequestDTO));
//        }
//
//        List<VisitRequest> visitRequests1 = controller.getVisitRequests();
//
//       assertEquals(visitRequests, visitRequests1);
//    }


}
