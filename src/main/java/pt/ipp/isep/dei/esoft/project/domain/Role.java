package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Role that can be used to define the Role of the Employees.
 */
public class Role implements Serializable  {

    private final Integer id;
    private final String description;

    /**
     * Creates a new Role object with the specified id and description.
     *
     * @param id          the id of the role
     * @param description the description of the role
     */
    public Role(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Compares the description of this Role with the specified description for sorting purposes.
     *
     * @param description the description to compare to
     * @return an integer representing the lexicographic difference between the descriptions
     */
    public Integer compareDescription(String description) {
        return this.description.compareTo(description);
    }


    /**
     * Compares this Role object to another object to check for equality.
     *
     * @param o the object to compare to this Role object
     * @return true if the object is equal to this Role object, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role that = (Role) o;
        return description.equals(that.description);
    }

    /**
     * Returns a hash code for this role based on its description field.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
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
     * Returns the ID of the object.
     *
     * @return The ID of the object.
     */
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }

    /**

     Creates a new instance of Role with the same id and description as the current one.
     @return a clone of the current Role object.
     */

    public Role clone() {
        return new Role(this.id,this.description);
    }
}
