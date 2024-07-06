package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.BusinessType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A repository class for managing all Business Types.
 */
public class BusinessTypeRepository {

    private final List<BusinessType> businessTypes = new ArrayList<>();

    /**
     * This method returns an exsiting Business Type by its description.
     *
     * @param businessTypeDescription The description of the Business Type to be created.
     * @param mode                    the mode
     * @return The task category.
     * @throws IllegalArgumentException if the Business Type does not exist, which should never happen.
     */
    public BusinessType getBusinessTypeByDescription(String businessTypeDescription, Boolean mode) {

        BusinessType newBusinessType = null;
        for ( BusinessType businessType: businessTypes ) {
            if (mode) {
                if (businessType.getDescriptionAnnoucement().compareTo(businessTypeDescription) == 0 ) newBusinessType = businessTypes.get(businessTypes.indexOf(businessType));
            } else {
                if (businessType.getDescriptionProperty().compareTo(businessTypeDescription) == 0 ) newBusinessType = businessTypes.get(businessTypes.indexOf(businessType));
            }
        }
        if (newBusinessType == null) {
            throw new IllegalArgumentException(
                    "Business Type for [" + businessTypeDescription + "] does not exist.");
        }
        return newBusinessType;
    }


    /**
     * Adds a new business type to the collection of business type managed by this repository.
     *
     * @param businessType the business type to be added to the collection.
     * @return an Optional containing the added business type, or an empty Optional if the operation fails
     */
    public Optional<BusinessType> add(BusinessType businessType) {

        Optional<BusinessType> newBusinessType = Optional.empty();
        boolean operationSuccess = false;

        if (validateBusinessType(businessType)) {
            newBusinessType = Optional.of(businessType.clone());
            operationSuccess = businessTypes.add(newBusinessType.get());
        }

        if (!operationSuccess) {
            newBusinessType = Optional.empty();
        }

        return newBusinessType;
    }
    /**
     * Validates if a given business type is valid to be added to the repository.
     *
     * @param businessType the businessType to be validated
     * @return true if the businessType is valid for addition, false otherwise
     */

    private boolean validateBusinessType(BusinessType businessType) {
        boolean isValid = !businessTypes.contains(businessType);
        return isValid;
    }

    /**
     * Returns a defensive (immutable) copy of the list of Business Types.
     *
     * @return a read-only list of all business types in the system.
     */
    public List<BusinessType> getBusinessTypes() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(businessTypes);
    }

}
