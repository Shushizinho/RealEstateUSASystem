package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Property;

import java.util.Comparator;

/**
 * The type State comparator.
 */
public class StateComparator implements Comparator<Property> {
    public int compare(Property f1, Property f2) {
        String state1 = f1.getState();
        String state2 = f2.getState();

        if (state1.compareTo(state2) < 0) {
            return -1;
        } else if (state1.compareTo(state2) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
