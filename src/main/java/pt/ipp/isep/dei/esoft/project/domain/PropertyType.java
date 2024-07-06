package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class PropertyType implements Serializable {

    private final String Description;

    public PropertyType(String description) {
        this.Description = description;
    }

//    public PropertyType(){
//
//    }

    /**
     * Checks if the given object is equal to this PropertyType object
     * @param o the object to be compared with this PropertyType object
     * @return true if the given object is equal to this PropertyType object, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PropertyType)) {
            return false;
        }
        PropertyType that = (PropertyType) o;
        return Description.equals(that.Description);
    }

    /**
     * Returns a hash code value for the PropertyType object.
     * The hash code is based on the value of the description field.
     *
     * @return the hash code value for this PropertyType object
     */
    @Override
    public int hashCode() {
        return Objects.hash(Description);
    }

    /**
     * This method returns the description of the Property Type.
     *
     * @return The description of the Property Type category.
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current Property Type.
     */
    public PropertyType clone() {
        return new PropertyType(this.Description);
    }

    @Override
    public String toString() {
        return String.format("%s", this.Description);
    }
}
