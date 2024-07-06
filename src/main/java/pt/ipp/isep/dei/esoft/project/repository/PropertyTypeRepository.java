package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.PropertyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A repository class for managing all Property Types.
 */
public class PropertyTypeRepository {

    private final List<PropertyType> propertyTypes = new ArrayList<>();

    /**
     * This method returns an exsiting Property Type by its description.
     *
     * @param propertyTypeDescription The description of the Property Type to be created.
     * @return The task category.
     * @throws IllegalArgumentException if the Property Type does not exist, which should never happen.
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
        PropertyType newPropertyType = new PropertyType(propertyTypeDescription);
        PropertyType propertyType = null;
        if (propertyTypes.contains(newPropertyType)) {
            propertyType = propertyTypes.get(propertyTypes.indexOf(newPropertyType));
        }
        if (propertyType == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + propertyTypeDescription + "] does not exist.");
        }
        return propertyType;
    }

    /**
     * Adds a new PropertyType to the list of available property types.
     *
     * @param propertyType the PropertyType object to add
     * @return an Optional containing the added PropertyType if the operation was successful, or an empty Optional otherwise
     */
    public Optional<PropertyType> add(PropertyType propertyType) {

        Optional<PropertyType> newPropertyType = Optional.empty();
        boolean operationSuccess = false;

        if (validatePropertyType(propertyType)) {
            newPropertyType = Optional.of(propertyType.clone());
            operationSuccess = propertyTypes.add(newPropertyType.get());
        }

        if (!operationSuccess) {
            newPropertyType = Optional.empty();
        }

        return newPropertyType;
    }
    /**
     * Validates if a given property type is valid to be added to the repository.
     *
     * @param propertyType the property type to be validated
     * @return true if the property type is valid for addition, false otherwise
     */
    private boolean validatePropertyType(PropertyType propertyType) {
        boolean isValid = !propertyTypes.contains(propertyType);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of Property Types.
     *
     * @return The list of Property Types.
     */
    public List<PropertyType> getPropertyTypes() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(propertyTypes);
    }
}
