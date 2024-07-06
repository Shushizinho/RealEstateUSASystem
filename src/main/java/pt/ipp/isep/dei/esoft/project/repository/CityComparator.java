package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Property;

import java.util.Comparator;

/**
 * The type City comparator.
 */
public class CityComparator implements Comparator<Property> {

    public int compare(Property f1, Property f2) {
        String city1 = f1.getCity();
        String city2 = f2.getCity();

        if (city1.compareTo(city2) < 0) {
            return -1;
        } else if (city1.compareTo(city2) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}



