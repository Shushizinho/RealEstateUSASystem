package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a general filter to be used when listing the properties.
 */
public class Filters {
    private final String filter;


    /**
     * Instantiates a new Filters.
     *
     * @param fil the fil
     */
    public Filters (String fil){
        this.filter= fil;
    }


    /**
     * Checks if the given object is equal to this Filters object
     * @param o the object to be compared with this Filters object
     * @return true if the given object is equal to this Filters object, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Filters)) {
            return false;
        }
        Filters that = (Filters) o;
        return filter.equals(that.filter);
    }

    /**
     * Returns a hash code value for the Filters object.
     *  The hash code is based on the value of the filter field.
     *
     * @return the hash code value for this Filters object
     */

    @Override
    public int hashCode() {
        return Objects.hash(filter);
    }

    /**
     * This method returns the description of the Filter.
     *
     * @return The description of the Property Type category.
     */
    public String getDescription() {
        return filter;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current Filter.
     */
    public Filters clone() {
        return new Filters (this.filter);
    }


}
