package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.mapper.PropertyMapper;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VisitRequestRepository;

import java.util.List;

/**
 * The type Visit request dto.
 */
public class VisitRequestDTO {
    private PropertyDTO property;

    private DateTimeDTO preferredDate;

    private List<DateTime> timeSlot;

    private String clientName;

    private long clientPhoneNumber;

    private String ClientReply;

    private Agent agent;

    private int wasAccepted;


    /**
     * Constructs a new VisitRequest object with the provided parameters.
     *
     * @param property          The property for the visit request.
     * @param preferredDate     The preferred date for the visit request.
     * @param timeSlot          The time slots available for the visit request.
     * @param clientName        The name of the client making the visit request.
     * @param clientPhoneNumber The phone number of the client making the visit request.
     */
    public VisitRequestDTO(PropertyDTO property, DateTimeDTO preferredDate, List<DateTime> timeSlot, String clientName, long clientPhoneNumber) {
        this.property = property;
        this.preferredDate = preferredDate;
        this.timeSlot = timeSlot;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.ClientReply = "";
        this.wasAccepted = 0;
        Announcement p = Repositories.getInstance().getAnnouncementRepository().getAnnouncementByProperty(PropertyMapper.toEntity(property));
        if (p != null){
            this.agent = p.getAgent();
        }


    }

    /**
     * Instantiates a new Visit request dto.
     *
     * @param property          the property
     * @param preferredDate     the preferred date
     * @param timeSlot          the time slot
     * @param clientName        the client name
     * @param clientPhoneNumber the client phone number
     * @param wasAccepted       the was accepted
     */
    public VisitRequestDTO(PropertyDTO property, DateTimeDTO preferredDate, List<DateTime> timeSlot, String clientName, long clientPhoneNumber, int wasAccepted) {
        this.property = property;
        this.preferredDate = preferredDate;
        this.timeSlot = timeSlot;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.ClientReply = "";
        this.wasAccepted = wasAccepted;
        Announcement p = Repositories.getInstance().getAnnouncementRepository().getAnnouncementByProperty(PropertyMapper.toEntity(property));
        if (p != null){
            this.agent = p.getAgent();
        }
    }


    /**
     * This method allows to set the client reply of the current visit request.
     *
     * @param clientReply the client reply
     */
    public void setClientReply(String clientReply) {
        ClientReply = clientReply;
    }

    /**
     * This method returns the client reply of the current visit request.
     *
     * @return client replay
     */
    public String getClientReply() {
        return ClientReply;
    }

    /**
     * This method returns the property of the current visit request.
     *
     * @return the Visit request's property.
     */
    public PropertyDTO getProperty() {
        return property;
    }

    /**
     * This method returns the preferred date of the current visit request.
     *
     * @return the Visit request's preferred date.
     */
    public DateTimeDTO getPreferredDate() {
        return preferredDate;
    }


    /**
     * This method returns the start time of the current visit request.
     *
     * @param i the
     * @return the Visit request's time slot.
     */
    public DateTime getTimeSlot(int i) {
        return timeSlot.get(i);
    }

    /**
     * This method returns the time slot for the current visit request.
     *
     * @return the Visit request's time slot.
     */
    public List<DateTime> getTimeSlot(){
        return timeSlot;
    }


    /**
     * This method returns the client name of the current visit request.
     *
     * @return the Visit request's client name.
     */
    public String getClientName() {
        return clientName;
    }


    /**
     * This method returns the client phone number of the current visit request.
     *
     * @return the Visit request's client phone number.
     */
    public long getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    /**
     * This method accepts the visit request
     */
    public void acceptVisitRequest(){
        this.wasAccepted = 1;
    }

    /**
     * This method decline the visit request
     */
    public void declineVisitRequest(){
        this.wasAccepted = -1;
    }


    /**
     * This method returns the value of wasAccepted for the current visit request
     *
     * @return wasAccepted for the current visit request
     */
    public int accepted(){
        return wasAccepted;
    }

    /**
     * Clone method.
     *
     * @return a clone of the current visit request
     */
    public VisitRequestDTO clone() {
        return new VisitRequestDTO(this.getProperty(), this.getPreferredDate(), this.getTimeSlot(), this.getClientName(),
                this.getClientPhoneNumber(), this.wasAccepted);
    }

    /**
     * To dto visit request.
     *
     * @param visitRequestDTO the visit request dto
     * @return the visit request
     */
    public static VisitRequest toDTO(VisitRequestDTO visitRequestDTO) {
        VisitRequestRepository.CreateVisitRequest(visitRequestDTO);
        VisitRequest visitRequest = VisitRequestMapper.toEntity(visitRequestDTO);
        return  visitRequest;
    }

    public String toString(){
        return String.format("=== Visit Request information ==="+
                        "\n--------------------------------------------------" + "\nProperty- %s" +
                        "\n--------------------------------------------------" + "\nPreferredDate- %s" +
                        "\n--------------------------------------------------" + "\nTimeSlot- %s-%s" +
                        "\n--------------------------------------------------"+ "\nClient name- %s" +
                        "\n--------------------------------------------------" + "\nClient phone number- %d",
                property.toStringDTO(), this.preferredDate.toStringDTO(),this.getTimeSlot(0).getTimestamp(), this.getTimeSlot(1).getTimestamp(), this.clientName, this.clientPhoneNumber);
    }

}
