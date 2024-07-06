package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Visit request repository test.
 */
public class VisitRequestRepositoryTest {
    private final PropertyDTO p1 =  new PropertyDTO(1.0d, 5.3d, new AddressDTO("streetAddress", "New York", "Texas", "zipCode"),
            13.2, List.of(new PhotographDTO("lala.png"), new PhotographDTO("okey.png"), new PhotographDTO("photo.png")), new PropertyTypeDTO("House"),
            new StoreDTO(10, "PPROG", new AddressDTO("teste","teste","teste","teste"), "pprog@this.app", 000000000),
            new ClientDTO( "Person","person@this.app", 2, parseLong("00000004"),"111-11-0439", new AddressDTO("teste","teste","teste","teste")), new DateTimeDTO());

    private final DateTimeDTO date1 = new DateTimeDTO();

    private  final List<DateTime> timeSlot1 = Arrays.asList(new DateTime(10, 0), new DateTime(11, 0));
    
    private final String clientName1 = "João";

    private final String clientName2 = "Ana";
    
    private final Integer clientPhoneNumber1 = 1;

    /**
     * Ensure new visit request successfully added.
     */
    @Test
    void ensureNewVisitRequestSuccessfullyAdded() {
        VisitRequestRepository VisitRequestRepository = new VisitRequestRepository();
        VisitRequestDTO VisitRequest = new VisitRequestDTO(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);      
        VisitRequestRepository.add(VisitRequest);
    }


    /**
     * Ensure get visit request returns an immutable list.
     */
    @Test
    void ensureGetVisitRequestReturnsAnImmutableList() {
        VisitRequestRepository VisitRequestRepository = new VisitRequestRepository();
         VisitRequestDTO VisitRequest = new VisitRequestDTO(p1,date1, timeSlot1, clientName2, clientPhoneNumber1);
        
        VisitRequestRepository.add(VisitRequest);

        assertThrows(UnsupportedOperationException.class,
                () -> VisitRequestRepository.getVisitRequests().add(VisitRequestDTO.toDTO(new VisitRequestDTO(p1,date1, timeSlot1, clientName2, clientPhoneNumber1))));

    }

    /**
     * Ensure get visit request returns the correct list.
     */
    @Test
    void ensureGetVisitRequestReturnsTheCorrectList() {
        //Arrange
        VisitRequestRepository VisitRequestRepository = new VisitRequestRepository();
         VisitRequestDTO VisitRequest = new VisitRequestDTO(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);
        VisitRequestRepository.add(VisitRequest);
        int expectedSize = 1;

        //Act
        int size = VisitRequestRepository.getVisitRequests().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(VisitRequest.toString(), VisitRequestRepository.getVisitRequests().get(size - 1).toDTO().toString());
    }

    /**
     * Ensure adding duplicate visit request fails.
     */
    @Test
    void ensureAddingDuplicateVisitRequestFails() {
        //Arrange
        VisitRequestRepository VisitRequestRepository = new VisitRequestRepository();
         VisitRequestDTO VisitRequest = new VisitRequestDTO(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);
        //Add the first task
        VisitRequestRepository.add(VisitRequest);

        //Act
        Optional<VisitRequest> duplicateVisitRequest = VisitRequestRepository.add(VisitRequest);

        //Assert
        assertTrue(duplicateVisitRequest.isEmpty());
    }

    /**
     * Ensure adding different visit request works.
     */
    @Test
    void ensureAddingDifferentVisitRequestWorks() {
        //Arrange
        VisitRequestRepository VisitRequestRepository = new VisitRequestRepository();
        VisitRequestDTO VisitRequestOne = new VisitRequestDTO(p1,date1, timeSlot1, clientName1, clientPhoneNumber1);
        VisitRequestDTO VisitRequestTwo = new VisitRequestDTO(p1,date1, timeSlot1, clientName2, clientPhoneNumber1);
        //Add the first task
        VisitRequestRepository.add(VisitRequestOne);

        //Act
        Optional<VisitRequest> result = VisitRequestRepository.add(VisitRequestTwo);

        //Assert
        assertEquals(VisitRequestTwo.toString(), result.get().toString());
    }
}
