package pt.ipp.isep.dei.esoft.project.domain;


import pt.ipp.isep.dei.esoft.project.dto.AnnouncementRequestDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AnnouncementRequestMapper;


import java.io.Serializable;
import java.util.Objects;
/**
 * Represents a general AnnouncementRequest.
 */
public class AnnouncementRequest implements Serializable {

    private Integer rentDuration = 0;

    private double listedPrice = 0;
    private BusinessType businessType;
    private Property property;
    private Agent agent;
    private Client client;

    private DateTime date;

    private boolean status = false;

    public AnnouncementRequest(Integer rentDuration, BusinessType businessType, Property property, Agent agent, Client client) {
        this.rentDuration = rentDuration;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.client = client;
        this.date = new DateTime();
    }

    public AnnouncementRequest(Integer rentDuration, double listedPrice, BusinessType businessType, Property property, Agent agent, Client client) {
        this.rentDuration = rentDuration;
        this.listedPrice = listedPrice;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.client = client;
        this.date = new DateTime();
    }

    public AnnouncementRequest(BusinessType businessType, Property property, Agent agent, Client client) {
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.client = client;
        this.date = new DateTime();
    }
    public AnnouncementRequest(Integer rentDuration, BusinessType businessType, Property property, Agent agent, Client client, DateTime date) {
        this.rentDuration = rentDuration;
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.client = client;
        this.date = date;
    }

    public AnnouncementRequest(BusinessType businessType, Property property, Agent agent, Client client, DateTime date) {
        this.businessType = businessType;
        this.property = property;
        this.agent = agent;
        this.client = client;
        this.date = date;
    }

    public void acceptAnnouncementRequest(){
        this.status = true;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBusinessType() {
        return String.valueOf(businessType);
    }

    public BusinessType getBusinessTypeObject() {
        return this.businessType;
    }


    /**
     * Checks if the given object is equal to this AnnouncmentRequest object
     * @param o the object to be compared with this AnnouncmentRequest object
     * @return true if the given object is equal to this AnnouncmentRequest object, false otherwise
     */
        @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnnouncementRequest)) {
            return false;
        }
        AnnouncementRequest announcementRequest = (AnnouncementRequest) o;
        Boolean rentDurationEqual = rentDuration == announcementRequest.rentDuration;
        Boolean businessTypeEqual = Objects.equals(businessType, announcementRequest.businessType);
        Boolean propertyEqual = Objects.equals(property, announcementRequest.property);
        Boolean agentEqual = Objects.equals(agent, announcementRequest.agent);
        Boolean clientEqual = Objects.equals(client, announcementRequest.client);
        return ( rentDurationEqual && businessTypeEqual && propertyEqual && agentEqual && clientEqual );
    }

    /**
     * Returns a hash code value for the AnnouncmentRequest object.
     *  The hash code is based on the values of the rentDuration, businessType, property, agent and client fields.
     *
     * @return the hash code value for this AnnouncmentRequest object
     */
    @Override
    public int hashCode() {
        return Objects.hash(rentDuration, businessType, property, agent, client);
    }

    public DateTime getDate() {
        return date;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public AnnouncementRequest clone() {
        return new AnnouncementRequest(this.rentDuration,this.businessType,this.property,this.agent,this.client,this.date);
    }

    /**
     * Returns a string representation of the AnnouncementRequest object.
     *
     * @return A string representation of the AnnouncementRequest object.
     */
    public String toString() {
        String result = String.format("Announcement Request\n ================== \n ");
        if(businessType.getDescriptionAnnoucement().compareTo("Rent")==0){
            result+=String.format( "Rent duration=%d \n",rentDuration);
        } else if (businessType.getDescriptionAnnoucement().compareTo("Sale")==0){

            result+=String.format( "Listed Price=%d \n",listedPrice);
        }

        result+= String.format( " Business type=%s\n ------------------ \n Property:%s \n ------------------ \n Date:%s", businessType.getDescriptionAnnoucement(), property, date);

        return result;
    }


    public double getListedPrice(){
        return listedPrice;
    }

public void setListedPrice( double listedPrice){

        this.listedPrice=listedPrice;
}

    public Integer getRentDuration() {
        return rentDuration;
    }

    public Property getProperty() {
        return property;
    }

    public Agent getAgent() {
        return agent;
    }





    public Client getClient() {
        return client;
    }


    public AnnouncementRequestDTO toDTO (){
        AnnouncementRequest announcementRequest = new AnnouncementRequest(rentDuration,businessType,property,agent,client,date);
        return AnnouncementRequestMapper.toDTO(announcementRequest);
    }



}
