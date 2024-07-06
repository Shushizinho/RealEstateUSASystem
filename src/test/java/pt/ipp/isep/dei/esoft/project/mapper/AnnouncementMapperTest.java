package pt.ipp.isep.dei.esoft.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnnouncementMapperTest {

//    @Test
    @DisplayName("Should convert Announcement to AnnouncementDTO with Rent business type")
    void toDTOWithRentBusinessType() {// Create a mock Announcement object
        Announcement announcement = mock(Announcement.class);

        // Set up the mock Announcement object
        List<Role> list = new ArrayList<>();
        list.add(new Role(1,"Agent"));
        Store store = new Store(100, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"),000000000);
        Address address = new Address("123","123","123","123");

        Employee employee = new Employee("AgentAD", "agent@this.app", 12321, "3213123", address ,121212, store, list);
        Client client = new Client("AgentAD", "agent@this.app", 12321, "3213123", address ,121212);
        Agent agent = new Agent(employee);
        Property property = new Property(300.1, 3.3, address,
                7.2, List.of(new Photograph("lala.png"), new Photograph("okey.png"), new Photograph("photo.png")), new PropertyType("Land"),
                store,
                client, new DateTime(1, 6, 2023));
        when(announcement.getBusinessType()).thenReturn(new BusinessType("rent","rent"));
        when(announcement.getAgent()).thenReturn(agent);
        when(announcement.getDate()).thenReturn(new DateTime());
        when(announcement.getCommission()).thenReturn(10.0);
        when(announcement.getProperty()).thenReturn(property);
        when(announcement.getRentDuration()).thenReturn(12);
        when(announcement.getListedPrice()).thenReturn(1000.0);

        // Call the toDTO method and get the result
        AnnouncementDTO result = AnnouncementMapper.toDTO(announcement);

        // Verify that the result is not null
        assertNotNull(result);

        // Verify that the result has the correct values
        assertEquals(result.getAgent().getEmployee().getName(), "John Doe");
        assertEquals(result.getBusinessType().getDescriptionAnnouncement(), "Rent");
        assertEquals(result.getCommission(), 10.0);
        assertEquals(result.getProperty().getClass(), property);
        assertEquals(result.getRentDuration(), 12);
        assertEquals(result.getListedPrice(), 1000.0);
    }

//    @Test
    @DisplayName("Should convert Announcement to AnnouncementDTO with non-Rent business type")
    void toDTOWithNonRentBusinessType() {// Create a mock Announcement object
        Announcement announcement = mock(Announcement.class);

        // Create a mock BusinessType object
        BusinessType businessType = mock(BusinessType.class);
        when(businessType.getDescriptionAnnoucement()).thenReturn("Sale");
        when(businessType.getDescriptionProperty()).thenReturn("Property for sale");

        // Create a mock Agent object
        Agent agent = mock(Agent.class);
        AgentDTO agentDTO = mock(AgentDTO.class);
        when(AnnouncementMapper.employeeToEmployeeDTO(agent)).thenReturn(agentDTO);

        // Create a mock DateTime object
        DateTime dateTime = mock(DateTime.class);
        DateTimeDTO dateTimeDTO = mock(DateTimeDTO.class);
        when(AnnouncementMapper.DateTimeToDateformattedDTO(dateTime)).thenReturn(dateTimeDTO);

        // Create a mock Property object
        Property property = mock(Property.class);
        PropertyDTO propertyDTO = mock(PropertyDTO.class);
        when(AnnouncementMapper.propertyToPropertyDTO(property)).thenReturn(propertyDTO);

        // Create a mock AnnouncementDTO object
        AnnouncementDTO announcementDTO = mock(AnnouncementDTO.class);

        // Mock the AnnouncementMapper methods
        when(announcement.getBusinessType()).thenReturn(businessType);
        when(announcement.getAgent()).thenReturn(agent);
        when(announcement.getDate()).thenReturn(dateTime);
        when(announcement.getProperty()).thenReturn(property);
        when(announcement.getCommission()).thenReturn(10.0);
        when(announcement.getListedPrice()).thenReturn(100000.0);
        when(AnnouncementMapper.businessTypeToBusinessTypeDTO(businessType)).thenReturn(new BusinessTypeDTO("Buy", "Sale"));
        when(AnnouncementMapper.photographToPhotographDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(AnnouncementMapper.employeeToEmployeeDTO(agent)).thenReturn(agentDTO);
        when(AnnouncementMapper.DateTimeToDateformattedDTO(dateTime)).thenReturn(dateTimeDTO);
        when(AnnouncementMapper.propertyToPropertyDTO(property)).thenReturn(propertyDTO);
        when(AnnouncementMapper.storeToStoreDTO(null)).thenReturn(null);

        // Call the toDTO method and assert the result
        AnnouncementDTO result = AnnouncementMapper.toDTO(announcement);
        assertNotNull(result);
        assertEquals(announcementDTO, result);
    }

}