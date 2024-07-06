package pt.ipp.isep.dei.esoft.project.dto;


/**
 * A DTO class representing a real estate announcement.
 */
public class AnnouncementDTO {

    private double listedPrice = 0;
    private Integer rentDuration = 0;
    private AgentDTO agent;
    private DateTimeDTO date;
    private double commission;
    private BusinessTypeDTO businessType;
    private PropertyDTO property;
    private AnnouncementRequestDTO announcementRequest;


    /**
     * Instantiates a new Announcement dto.
     */
    public AnnouncementDTO() {
    }

    /**
     * Instantiates a new Announcement dto.
     *
     * @param agent        the agent
     * @param date         the date
     * @param commission   the commission
     * @param businessType the business type
     * @param property     the property
     * @param rentDuration the rent duration
     * @param listedPrice  the listed price
     */
    public AnnouncementDTO(AgentDTO agent, DateTimeDTO date, double commission, BusinessTypeDTO businessType, PropertyDTO property, Integer rentDuration, double listedPrice) {
        this.agent = agent;
        this.date = date;
        this.commission = commission;
        this.businessType = businessType;
        this.property = property;
        this.rentDuration = rentDuration;
        this.listedPrice = listedPrice;

    }


    /**
     * Instantiates a new Announcement dto.
     *
     * @param agent        the agent
     * @param date         the date
     * @param commission   the commission
     * @param businessType the business type
     * @param property     the property
     * @param listedPrice  the listed price
     */
    public AnnouncementDTO(AgentDTO agent, DateTimeDTO date, double commission, BusinessTypeDTO businessType, PropertyDTO property, double listedPrice) {
        this.agent = agent;
        this.date = date;
        this.commission = commission;
        this.businessType = businessType;
        this.property = property;
        this.listedPrice = listedPrice;


    }

    /**
     * Gets listed price.
     *
     * @return the listed price
     */
    public double getListedPrice() {
        return listedPrice;
    }

    /**
     * Sets listed price.
     *
     * @param listedPrice the listed price
     */
    public void setListedPrice(double listedPrice) {
        this.listedPrice = listedPrice;
    }

    /**
     * Gets rent duration.
     *
     * @return the rent duration
     */
    public Integer getRentDuration() {
        return rentDuration;
    }

    /**
     * Sets rent duration.
     *
     * @param rentDuration the rent duration
     */
    public void setRentDuration(Integer rentDuration) {
        this.rentDuration = rentDuration;
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public AgentDTO getAgent() {
        return agent;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     */
    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public DateTimeDTO getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(DateTimeDTO date) {
        this.date = date;
    }

    /**
     * Gets commission.
     *
     * @return the commission
     */
    public double getCommission() {
        return commission;
    }

    /**
     * Sets commission.
     *
     * @param commission the commission
     */
    public void setCommission(double commission) {
        this.commission = commission;
    }

    /**
     * Gets business type.
     *
     * @return the business type
     */
    public BusinessTypeDTO getBusinessType() {
        return businessType;
    }

    /**
     * Sets business type.
     *
     * @param businessType the business type
     */
    public void setBusinessType(BusinessTypeDTO businessType) {
        this.businessType = businessType;
    }

    /**
     * Gets property.
     *
     * @return the property
     */
    public PropertyDTO getProperty() {
        return property;
    }

    /**
     * Sets property.
     *
     * @param property the property
     */
    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    /**
     * Gets announcement request.
     *
     * @return the announcement request
     */
    public AnnouncementRequestDTO getAnnouncementRequest() {
        return announcementRequest;
    }

    /**
     * Sets announcement request.
     *
     * @param announcementRequest the announcement request
     */
    public void setAnnouncementRequest(AnnouncementRequestDTO announcementRequest) {
        this.announcementRequest = announcementRequest;
    }



}
