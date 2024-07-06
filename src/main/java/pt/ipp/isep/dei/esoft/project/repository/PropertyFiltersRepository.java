package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Filters;
import pt.ipp.isep.dei.esoft.project.domain.Pair;
import pt.ipp.isep.dei.esoft.project.dto.PairDTO;
import pt.ipp.isep.dei.esoft.project.mapper.PairMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A repository class for managing all Filters.
 */
public class PropertyFiltersRepository {

    private final List<Filters> filter = new ArrayList<>();
    private final List<Pair<Integer, Integer>> priceRanges = new ArrayList<>();

    /**
     * Adds a new filter to the collection of filters managed by this repository.
     *
     * @param filters the filter to be added to the collection.
     * @return an Optional containing the added filter, or an empty Optional if the operation fails
     */
    public Optional<Filters> add(Filters filters) {

        Optional<Filters> newFilters = Optional.empty();
        boolean operationSuccess = false;

        if (validateFilter(filters)) {
            newFilters = Optional.of(filters.clone());
            operationSuccess = filter.add(newFilters.get());
        }

        if (!operationSuccess) {
            newFilters = Optional.empty();
        }

        return newFilters;
    }
    /**
     * Validates if a given filter is valid to be added to the repository.
     *
     * @param filters the filter to be validated
     * @return true if the filter is valid for addition, false otherwise
     */

    private boolean validateFilter(Filters filters) {
        boolean isValid = !filter.contains(filters);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of Filters.
     *
     * @return The list of Property Filters.
     */
    public List<Filters> getFilters() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(filter);
    }

    /**
     * Retrieves the list of price ranges.
     *
     * @return a list of price ranges
     */
    public List<PairDTO<Integer, Integer>> getPriceRanges() {
        List<PairDTO<Integer, Integer>> copy = new ArrayList<>();
//        copy.addAll(List.copyOf(priceRanges));
        for (Pair<Integer, Integer> pair: priceRanges) {
            copy.add(PairMapper.toDTO(pair));
        }
        copy.add(new PairDTO<>(getPriceRangeMax(), Integer.MAX_VALUE));
        return copy;
    }
    /**
     * Validates a price range.
     *
     * @param priceRange the price range to validate
     * @return true if the price range is valid, false otherwise
     */
    private boolean validatePriceRange(Pair<Integer, Integer> priceRange) {
        if (priceRange.getLeft() < 0 || (priceRange.getRight() < priceRange.getLeft() && priceRange.getRight()!=0)) return false;
        boolean isValid = !priceRanges.contains(priceRange);
        for (Pair<Integer, Integer> checkPrice: priceRanges) {
            if (comparePriceRanges(checkPrice, priceRange)) isValid = false;
        }
        return isValid;
    }

    /**
     * Compares two price ranges.
     *
     * @param a the first price range to compare
     * @param b the second price range to compare
     * @return true if the price ranges overlap or intersect, false otherwise
     */
    public boolean comparePriceRanges(Pair<Integer, Integer> a, Pair<Integer, Integer> b){

            return ((a.getLeft() < b.getLeft()) && (a.getRight() > b.getLeft()) ||
                (b.getLeft() < a.getLeft()) && (b.getRight() > a.getLeft()) ||
                (a.getLeft() < b.getRight()) && (a.getLeft() > b.getRight()) ||
                (b.getLeft() < a.getRight()) && (b.getLeft() > b.getRight()) );

    }

    /**
     * Retrieves the maximum value for a price range roof in the list of price ranges.
     *
     * @return the maximum value for a price range roof
     */
    public int getPriceRangeMax(){
        int max = priceRanges.get(0).getRight();
        for (Pair<Integer,Integer>priceRange: priceRanges) {
            if (priceRange.getRight() > max) max = priceRange.getRight();
        }
        return max;
    }

    /**
     * Adds a new price range to the list of price ranges.
     *
     * @param priceRange the price range to be added
     * @return an optional containing the new price range if the addition is successful, otherwise an empty optional
     */
    public Optional<Pair<Integer, Integer>> addPriceRange(Pair<Integer, Integer> priceRange) {

        Optional<Pair<Integer, Integer>> newPriceRanges = Optional.empty();
        boolean operationSuccess = false;

        if (validatePriceRange(priceRange)) {
            newPriceRanges = Optional.of(priceRange.clone());
            operationSuccess = priceRanges.add(newPriceRanges.get());
        }

        if (!operationSuccess) {
            newPriceRanges = Optional.empty();
        }

        return newPriceRanges;
    }
}
