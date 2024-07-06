package pt.ipp.isep.dei.esoft.project.dto;

import java.util.Objects;


/**
 * The type Announcement request dto.
 */
public class AnnouncementRequestDTO {

    private Integer rentDuration = 0;
    private double listedPrice;
    private BusinessTypeDTO businessType;
    private PropertyDTO property;
    private AgentDTO agent;
    private ClientDTO client;

    private DateTimeDTO date;
    private boolean status = false;


    /**
     * Constructor for the AnnouncementRequestDTO class.
     *
     * @param rentDuration The rent duration.
     * @param listedPrice  the listed price
     * @param businessType The business type.
     * @param property     The related property.
     * @param agent        The associated agent.
     * @param client       The related client.
     * @param date         The formatted date.
     */
    public AnnouncementRequestDTO(Integer rentDuration, double listedPrice, BusinessTypeDTO businessType, PropertyDTO property, AgentDTO agent, ClientDTO client, DateTimeDTO date) {
        this.rentDuration = rentDuration;
        this.listedPrice = listedPrice;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.client = client;
        this.date = date;
    }


    public AnnouncementRequestDTO( double listedPrice, BusinessTypeDTO businessType, PropertyDTO property, AgentDTO agent, ClientDTO client, DateTimeDTO date) {
        this.listedPrice = listedPrice;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.client = client;
        this.date = date;
    }

    /**
     * Constructor for the AnnouncementRequestDTO class.
     *
     * @param businessType The business type.
     * @param property     The related property.
     * @param agent        The associated agent.
     * @param client       The related client.
     * @param date         The formatted date.
     */
    public AnnouncementRequestDTO( BusinessTypeDTO businessType, PropertyDTO property, AgentDTO agent, ClientDTO client, DateTimeDTO date) {
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.client = client;
        this.date = date;
    }

    /**
     * Retrieves the listed price.
     *
     * @return The listed price.
     */
    public double getListedPrice(){

        return listedPrice;
    }

    /**
     * Set listed price.
     *
     * @param listedPrice the listed price
     */
    public void setListedPrice(double listedPrice){

        this.listedPrice = listedPrice;
    }

    /**
     * Retrieves the rent duration.
     *
     * @return The rent duration.
     */
    public Integer getRentDuration() {
        return rentDuration;
    }

    /**
     * Sets the rent duration.
     *
     * @param rentDuration The new rent duration.
     */
    public void setRentDuration(Integer rentDuration) {
        this.rentDuration = rentDuration;
    }

    /**
     * Retrieves the business type.
     *
     * @return The business type.
     */
    public BusinessTypeDTO getBusinessType() {
        return businessType;
    }


    /**
     * Sets the business type.
     *
     * @param businessType The new business type.
     */
    public void setBusinessType(BusinessTypeDTO businessType) {
        this.businessType = businessType;
    }


    /**
     * Retrieves the property.
     *
     * @return The property.
     */
    public PropertyDTO getProperty() {
        return property;
    }

    /**
     * Sets the property.
     *
     * @param property The new property.
     */


    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    /**
     * Retrieves the agent.
     *
     * @return The agent.
     */


    public AgentDTO getAgent() {
        return agent;
    }


    /**
     * Sets the agent.
     *
     * @param agent The new agent.
     */
    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    /**
     * Retrieves the client.
     *
     * @return The client.
     */
    public ClientDTO getClient() {
        return client;
    }


    /**
     * Sets the client.
     *
     * @param client The new client.
     */
    public void setClient(ClientDTO client) {
        this.client = client;
    }


    /**
     * Retrieves the date.
     *
     * @return The date.
     */
    public DateTimeDTO getDate() {
        return date;
    }


    /**
     * Sets the date.
     *
     * @param date The new date.
     */
    public void setDate(DateTimeDTO date) {
        this.date = date;
    }


    /**
     * To string dto string.
     *
     * @return the string
     */
    public String toStringDTO() {
        String rent = String.format("Announcement Request\n ================== \n ");
        if(businessType.getDescriptionAnnouncement().compareTo("Rent")==0){
            rent+=String.format( "Rent duration=%d \n",rentDuration);
        }

        rent+= String.format( " Business type=%s\n ------------------ \n Property:%s \n ------------------ \n Date:%s\n ------------------ \n Client:%s", businessType.getDescriptionAnnouncement(), property.toStringDTO(), date.toStringDTO(),client.toString());

        return rent;
    }


    /**
     * Checks if the announcement request has been accepted.
     *
     * @return {@code true} if the announcement request has been accepted, {@code false} otherwise.
     */
    public boolean isAccepted() {
        return status;
    }

    /**
     * Sets the acceptance status of the announcement request.
     *
     * @param accepted {@code true} if the announcement request is accepted, {@code false} otherwise.
     */



    public void setAccepted(boolean accepted) {
        this.status = accepted;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AnnouncementRequestDTO other = (AnnouncementRequestDTO) obj;
        return Objects.equals(rentDuration, other.rentDuration) &&
                Objects.equals(businessType, other.businessType) &&
                Objects.equals(property, other.property) &&
                Objects.equals(agent, other.agent) &&
                Objects.equals(client, other.client) &&
                Objects.equals(date, other.date);
    }










}
