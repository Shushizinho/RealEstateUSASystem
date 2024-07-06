package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

/**
 * The type Get property list controller.
 */
public class GetPropertyListController {

    private PropertyFiltersRepository propertyFiltersRepository = null;

    private BusinessTypeRepository businessTypeRepository = null;

    private PropertyTypeRepository propertyTypeRepository = null;

    private PropertyRepository propertyRepository = null;

    private AnnouncementRepository announcementRepository = null;


    /**
     * Instantiates a new Get property list controller.
     */
    public GetPropertyListController() {
        getPropertyFiltersRepository();
        getBusinessTypeRepository();
        getPropertyTypeRepository();
        getPropertyFilters();
        getPropertyRepository();
        getAnnoucemnetRepository();
    }

    /**
     * Instantiates a new Get property list controller.
     *
     * @param filtersRepository         the filters repository
     * @param businessTypeRepository    the business type repository
     * @param propertyTypeRepository    the property type repository
     * @param propertyFiltersRepository the property filters repository
     * @param propertyRepository        the property repository
     * @param announcementRepository    the announcement repository
     */
    public GetPropertyListController(PropertyFiltersRepository filtersRepository,
                                     BusinessTypeRepository businessTypeRepository,
                                     PropertyTypeRepository propertyTypeRepository,
                                     PropertyFiltersRepository propertyFiltersRepository,
                                     PropertyRepository propertyRepository,
                                     AnnouncementRepository announcementRepository) {
        this.propertyFiltersRepository = filtersRepository;
        this.businessTypeRepository = businessTypeRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.propertyRepository = propertyRepository;
        this.propertyFiltersRepository = propertyFiltersRepository;
        this.announcementRepository = announcementRepository;
    }

    /**
     * This method returns the property filters repository.
     *
     *   @return the property filters repository
     */
    private PropertyFiltersRepository getPropertyFiltersRepository() {
        if (propertyFiltersRepository == null) {
            Repositories filters = Repositories.getInstance();
            propertyFiltersRepository = filters.getPropertyFiltersRepository();
        }
        return propertyFiltersRepository;
    }

    /**
     * This method This method retrieves the list of business types.
     *
     * @return the list of business types
     */
    public List<BusinessType> getBusinessTypes() {
        BusinessTypeRepository businessTypeRepository = getBusinessTypeRepository();
        return businessTypeRepository.getBusinessTypes();
    }

    /**
     * This method This method retrieves the BusinessTypeRepository instance.
     *
     * @return the BusinessTypeRepository instance
     */
    private BusinessTypeRepository getBusinessTypeRepository() {
        if (businessTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the BusinessTypeRepository
            businessTypeRepository = repositories.getBusinessTypeRepository();
        }
        return businessTypeRepository;
    }

    /**
     * This method retrieves the list of property types.
     *
     * @return the list of property types
     */
    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }

    /**
     * This method retrieves the PropertyTypeRepository instance.
     *
     * @return the PropertyTypeRepository instance
     */
    public PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories propertyType = Repositories.getInstance();
            propertyTypeRepository = propertyType.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    /**
     * This method retrieves the list of property filters.
     *
     * @return the list of property filters
     */
    public List<Filters> getPropertyFilters() {
        PropertyFiltersRepository propertyFiltersRepository = getPropertyFiltersRepository();
        return propertyFiltersRepository.getFilters();
    }

    /**
     * This method retrieves the list of properties.
     *
     * @return the list of properties
     */
    public List<Property> getProperty(){
        PropertyRepository propertyRepository1 = getPropertyRepository();
        return propertyRepository1.getProperties();
    }

    /**
     * This method retrieves the PropertyRepository instance.
     *
     * @return the PropertyRepository instance
     */
    public PropertyRepository getPropertyRepository() {
        if (propertyRepository == null) {
            Repositories property = Repositories.getInstance();

            //Get the PropertyTypeRepository
            propertyRepository = property.getPropertyRepository();
        }
        return propertyRepository;

    }

    /**
     * This method retrieves the list of all properties based on a given businessType.
     *
     * @param businessType the business type
     * @return the list of properties on sale
     */
    public List<Property> getPropertiesByBusinessType(String businessType){
        AnnouncementRepository announcementRepository = getAnnoucemnetRepository();
        return announcementRepository.getAllPropertiesByBusinessType(businessType);
    }


    /**
     * This method retrieves the AnnouncementRepository instance.
     *
     * @return the AnnouncementRepository instance
     */
    public AnnouncementRepository getAnnoucemnetRepository() {
        if ( announcementRepository== null) {
            Repositories annoucement = Repositories.getInstance();

            //Get the AnnouncementRepository
            announcementRepository = annoucement.getAnnouncementRepository();
        }
        return announcementRepository;

    }

    /**
     * This method retrieves the list of properties based on announcements.
     *
     * @return the list of properties based on announcements
     */
    public List<Property> getPropertieListByAnnouncemnent(){
        return Repositories.getInstance().getAnnouncementRepository().getPropertyListByAnnouncements();
    }
}
