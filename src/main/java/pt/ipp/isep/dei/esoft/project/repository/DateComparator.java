package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.DateTime;
import pt.ipp.isep.dei.esoft.project.domain.Property;

import java.util.Comparator;

/**
 * The type Date comparator.
 */
public class DateComparator implements Comparator<Property> {

    @Override
    public int compare(Property f1, Property f2) {
        DateTime date1 = f1.getDate();
        DateTime date2 = f2.getDate();

        if (date1.isBefore(date2)) {
            return -1;
        } else if (date1.isAfter(date2)) {
            return 1;
        } else {
            return 0;
        }
    }


}
