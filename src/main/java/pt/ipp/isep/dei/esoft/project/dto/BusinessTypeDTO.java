package pt.ipp.isep.dei.esoft.project.dto;

/**
 * The type Business type dto.
 */
public class BusinessTypeDTO {
    private String descriptionProperty;
    private String descriptionAnnouncement;

    /**
     * Constructs a new BusinessTypeDTO with the given description property and description announcement.
     *
     * @param descriptionProperty     The description of the property.
     * @param descriptionAnnouncement The description of the announcement.
     */
    public BusinessTypeDTO(String descriptionProperty, String descriptionAnnouncement) {
        this.descriptionProperty = descriptionProperty;
        this.descriptionAnnouncement = descriptionAnnouncement;
    }


    /**
     * Retrieves the description of the property.
     *
     * @return The description of the property.
     */
    public String getDescriptionProperty() {
        return descriptionProperty;
    }


    /**
     * Sets the description of the property.
     *
     * @param descriptionProperty The new description of the property.
     */
    public void setDescriptionProperty(String descriptionProperty) {
        this.descriptionProperty = descriptionProperty;
    }

    /**
     * Retrieves the description of the announcement.
     *
     * @return The description of the announcement.
     */
    public String getDescriptionAnnouncement() {
        return descriptionAnnouncement;
    }

    /**
     * Sets the description of the announcement.
     *
     * @param descriptionAnnouncement The new description of the announcement.
     */
    public void setDescriptionAnnouncement(String descriptionAnnouncement) {
        this.descriptionAnnouncement = descriptionAnnouncement;
    }
}

