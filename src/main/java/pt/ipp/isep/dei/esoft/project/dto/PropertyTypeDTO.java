package pt.ipp.isep.dei.esoft.project.dto;

/**
 * The type Property type dto.
 */
public class PropertyTypeDTO {

    private String description;

    /**
     * Instantiates a new Property type dto.
     *
     * @param description the description
     */
    public PropertyTypeDTO(String description) {
        this.description = description;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
