package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;
/**
 * Represents the type of Business associated with the property.
 */
public class BusinessType implements Serializable {

    private final String descriptionProperty;

    private final String descriptionAnnoucement;

    private final String DESCRIPTIONANNOUNCEMENTOMISSION = "none";

    public BusinessType(String descriptionProperty, String descriptionAnnoucement) {
        this.descriptionProperty = descriptionProperty;
                this.descriptionAnnoucement = descriptionAnnoucement;
    }



    /**
     * Checks if the given object is equal to this Business Type object
     * @param o the object to be compared with this Business Type object
     * @return true if the given object is equal to this Business Type object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusinessType)) {
            return false;
        }
        BusinessType that = (BusinessType) o;
        return descriptionProperty.equals(that.descriptionProperty);
    }


    /**
     * Returns a hash code value for the Business Type  object.
     *  The hash code is based on the value of the email field.
     *
     * @return the hash code value for this Business Type  object
     */
    @Override
    public int hashCode() {
        return Objects.hash(descriptionProperty);
    }

    /**
     * This method returns the description of the Business Type.
     *
     * @return The description of the Business Type.
     */
    public String getDescriptionProperty() {
        return descriptionProperty;
    }

    public String getDescriptionAnnoucement(){
        return descriptionAnnoucement;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current Business Type.
     */
    public BusinessType clone() {
        return new BusinessType(this.descriptionProperty, this.descriptionAnnoucement);
    }


}
