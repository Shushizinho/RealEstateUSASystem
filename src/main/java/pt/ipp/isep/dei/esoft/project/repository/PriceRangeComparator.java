package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Property;

/**
 * The type Price range comparator.
 */
public class PriceRangeComparator {
    private int min = 0;

    private int max = 0;

    /**
     * Instantiates a new Price range comparator.
     *
     * @param min the min
     * @param max the max
     */
    public PriceRangeComparator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Compare int.
     *
     * @param f1 the f 1
     * @return the int
     */
    public int compare(Property f1) {
        double price1 = f1.getPrice();

        if (price1 < min) {
            return -1;
        } else if (price1 < max) {
            return 1;
        } else {
            return 0;
        }
    }

}
