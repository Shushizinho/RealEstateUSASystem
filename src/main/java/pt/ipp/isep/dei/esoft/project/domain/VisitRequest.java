package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.dto.VisitRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.VisitRequestMapper;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class VisitRequest implements Serializable {

    private Property property;

    private DateTime preferredDate;

    private List<DateTime> timeSlot;

    private String clientName;

    private long clientPhoneNumber;

    private String clientReply;
    private Agent agent;

    private int wasAccepted;

    /**
     * Constructs a new VisitRequest object with the provided parameters.
     *
     * @param property           The property for the visit request.
     * @param preferredDate      The preferred date for the visit request.
     * @param timeSlot           The time slots available for the visit request.
     * @param clientName         The name of the client making the visit request.
     * @param clientPhoneNumber The phone number of the client making the visit request.
     */
    public VisitRequest(Property property, DateTime preferredDate, List<DateTime> timeSlot, String clientName, long clientPhoneNumber) {
        this.property = property;
        this.preferredDate = preferredDate;
        this.timeSlot = timeSlot;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientReply = "";
        this.wasAccepted = 0;
        Announcement p = Repositories.getInstance().getAnnouncementRepository().getAnnouncementByProperty((property));
        if (p != null){
            this.agent = p.getAgent();
        }
    }
    public VisitRequest(Property property, DateTime preferredDate, List<DateTime> timeSlot, String clientName, long clientPhoneNumber, int wasAccepted) {
        this.property = property;
        this.preferredDate = preferredDate;
        this.timeSlot = timeSlot;
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientReply = "";
        this.wasAccepted = wasAccepted;
        Announcement p = Repositories.getInstance().getAnnouncementRepository().getAnnouncementByProperty((property));
        if (p != null){
            this.agent = p.getAgent();
        }
    }

//    public VisitRequest(Property property, DateTime preferredDate, List<DateTime> timeSlot, String clientName, long clientPhoneNumber, Agent agent) {
//        this.property = property;
//        this.preferredDate = preferredDate;
//        this.timeSlot = timeSlot;
//        this.clientName = clientName;
//        this.clientPhoneNumber = clientPhoneNumber;
//        this.agent = agent;
//        this.clientReply = "";
//        this.wasAccepted = 0;
//    }

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
     * @return wasAccepted for the current visit request
     */
    public int accepted(){
        return wasAccepted;
    }

    /**
     * This method allows to set the client reply of the current visit request.
     * @param ClientReply
     */
    public void setClientReply(String ClientReply) {
        clientReply = ClientReply;
    }

    /**
     * This method returns the client reply of the current visit request.
     * @return client replay
     */
    public String getClientReply() {
        return clientReply;
    }

    /**
     * This method returns the property of the current visit request.
     * @return the Visit request's property.
     */
    public Property getProperty() {
        return property;
    }

    /**
     * This method returns the preferred date of the current visit request.
     * @return the Visit request's preferred date.
     */
    public DateTime getPreferredDate() {
        return preferredDate;
    }



    /**
     * This method returns the start time of the current visit request.
     * @return the Visit request's time slot.
     */
    public DateTime getTimeSlot(int i) {
        return timeSlot.get(i);
    }

    /**
     * This method returns the time slot for the current visit request.
     * @return the Visit request's time slot.
     */
    public List<DateTime> getTimeSlot(){
        return timeSlot;
    }

    /**
     * This method returns the client name of the current visit request.
     * @return the Visit request's client name.
     */
    public String getClientName() {
        return clientName;
    }


    /**
     * This method returns the client phone number of the current visit request.
     * @return the Visit request's client phone number.
     */
    public long getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public List<DateTime> getTimeSlotByDate(DateTime date){
        List<DateTime> timeSlot = null;

        if (preferredDate.getHour() == date.getHour() && preferredDate.getMinute() == date.getMinute()){
            timeSlot = getTimeSlot();
        }
        return  timeSlot;

    }


    public Agent getAgent() {
        return agent;
    }

    /**
     * Checks if the given object is equal to this Visit request object
     * @param o the object to be compared with this Visit request object
     * @return true if the given object is equal to this Visit request object, false otherwise
     */

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VisitRequest)) {
            return false;
        }
        VisitRequest visitRequest = (VisitRequest) o;
        Boolean propertyEqual = Objects.equals(property, visitRequest.property);
        Boolean preferredDateEqual = preferredDate.equals(visitRequest.preferredDate);
        Boolean timeSlotEqual = timeSlot.equals(visitRequest.timeSlot);
        Boolean clientNameEquals = clientName.equals(visitRequest.clientName);
        Boolean clientPhoneNumberEquals = clientPhoneNumber == visitRequest.clientPhoneNumber;
        return (propertyEqual && preferredDateEqual && clientNameEquals
                && clientPhoneNumberEquals && timeSlotEqual);
    }

    /**
     *
     * The hash code is based on the values of the property, preferredDate, timeSlot, clientName and clientPhoneNumber.
     * @return a hash code value for the visit request object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(property, preferredDate, timeSlot, clientName, clientPhoneNumber);
    }

    /**
     * Clone method.
     *
     * @return a clone of the current visit request
     */
    public VisitRequest clone() {
        return new VisitRequest(this.getProperty(), this.getPreferredDate(), this.getTimeSlot(), this.getClientName(),
                this.getClientPhoneNumber());
    }

    /**
     * This method displays a String of the current visit request object.
     * @return String of the current visit request object.
     */
    @Override
public String toString(){
        return String.format("=== Visit Request information ==="+
                "\n--------------------------------------------------" + "\nProperty- %s" +
                "\n--------------------------------------------------" + "\nPreferredDate- %s" +
                "\n--------------------------------------------------" + "\nTimeSlot- %s-%s" +
                "\n--------------------------------------------------"+ "\nClient name- %s" +
                "\n--------------------------------------------------" + "\nClient phone number- %d",
                property.toString(), this.preferredDate,this.getTimeSlot(0).getTimestamp(), this.getTimeSlot(1).getTimestamp(), this.clientName, this.clientPhoneNumber);
    }
    public String toStringComplete(){
        return String.format("\n=== Visit Request information ===" +
                        "\n--------------------------------------------------" + "\nProperty- %s" +
                        "\n--------------------------------------------------" + "\nPreferredDate- %s" +
                        "\n--------------------------------------------------" + "\nTimeSlot- %s-%s" +
                        "\n--------------------------------------------------" + "\nClient name- %s" +
                        "\n--------------------------------------------------" + "\nClient phone number- %s" +
                        "\n--------------------------------------------------" + "\nAgent name- %s" +
                        "\n--------------------------------------------------" + "\nAgent phone number- %s" +
                        "\n--------------------------------------------------" + "\nStore name- %s" +
                        "\n--------------------------------------------------" + "\nStore ID- %d",
                this.property.toString(), this.preferredDate.toString(), this.getTimeSlot(0).getTimestamp(), this.getTimeSlot(1).getTimestamp(), this.clientName, this.clientPhoneNumber,
                this.getAgent().getName(), this.agent.getPhoneNumber(), this.agent.getStore().getDesignation(), this.agent.getStore().getId());
    }

    public VisitRequestDTO toDTO (){
        VisitRequest visitRequest = new VisitRequest(property,preferredDate,timeSlot,clientName,clientPhoneNumber, wasAccepted);
        return VisitRequestMapper.toDTO(visitRequest);
    }

    public boolean confirmClientReplay(String clientReply){
        return clientReply.equals("");
    }
}
