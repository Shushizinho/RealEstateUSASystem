package pt.ipp.isep.dei.esoft.project.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a DTO for Role.
 */
public class RoleDTO implements Serializable {

    private Integer id;
    private String description;

    /**
     * Creates a new RoleDTO object with the specified id and description.
     *
     * @param id          the id of the role
     * @param description the description of the role
     */
    public RoleDTO(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Returns the id of the role.
     *
     * @return the id of the role
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of the role.
     *
     * @param id the id of the role
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the description of the role.
     *
     * @return the description of the role
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the role.
     *
     * @param description the description of the role
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Compares this RoleDTO object to another object to check for equality.
     *
     * @param o the object to compare to this RoleDTO object
     * @return true if the object is equal to this RoleDTO object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleDTO)) {
            return false;
        }
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(id, roleDTO.id) && Objects.equals(description, roleDTO.description);
    }

    /**
     * Returns a hash code for this roleDTO based on its id and description fields.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    /**
     * Returns a string representation of the RoleDTO object.
     *
     * @return a string representation of the RoleDTO object
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
