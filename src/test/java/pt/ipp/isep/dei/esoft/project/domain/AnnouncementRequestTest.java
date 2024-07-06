package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AnnouncementRequestTest {

    private Property property;
    private Agent agent;
    private Client client;

    @Test
    @DisplayName("Should create an AnnouncementRequest with the given rentDuration, businessType, property, agent, and client")
    void announcementRequestWithRentDurationBusinessTypePropertyAgentClient() {
        BusinessType businessType = new BusinessType("Rent", "Rent");
        AnnouncementRequest announcementRequest = new AnnouncementRequest(30, businessType, property, agent, client);

        assertNotNull(announcementRequest);
        assertEquals(30, announcementRequest.getRentDuration());
        assertEquals(businessType, announcementRequest.getBusinessTypeObject());
        assertEquals(property, announcementRequest.getProperty());
        assertEquals(agent, announcementRequest.getAgent());
        assertEquals(client, announcementRequest.getClient());



    }



}