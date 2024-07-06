package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Property;

import java.util.Comparator;

/**
 * The type Price comparator.
 */
public class PriceComparator implements Comparator<Property> {
    @Override
    public int compare(Property f1, Property f2) {
        double price1 = f1.getPrice();
        double price2 = f2.getPrice();

        if (price1 < price2) {
            return -1;
        } else if (price1 > price2) {
            return 1;
        } else {
            return 0;
        }
    }
}
