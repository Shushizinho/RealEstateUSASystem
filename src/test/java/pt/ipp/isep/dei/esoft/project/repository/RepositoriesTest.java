package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Repositories test.
 */
class RepositoriesTest {

    /**
     * Test get instance.
     */
    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

    /**
     * Test get organization repository.
     */
    @Test
    void testGetOrganizationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getOrganizationRepository());
    }

    /**
     * Test get task category repository.
     */
    @Test
    void testGetTaskCategoryRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getTaskCategoryRepository());
    }

    /**
     * Test get property filters repository.
     */
    @Test
    void testGetPropertyFiltersRepository() {
        Repositories instamce = Repositories.getInstance();
        assertNotNull(instamce.getPropertyFiltersRepository());
    }

    /**
     * Test get property type repository.
     */
    @Test
    void testGetPropertyTypeRepository(){
        Repositories instamce = Repositories.getInstance();
        assertNotNull(instamce.getPropertyTypeRepository());
    }

    /**
     * Test get client repository.
     */
    @Test
    void testGetClientRepository(){
        Repositories instamce = Repositories.getInstance();
        assertNotNull(instamce.getClientRepository());
    }

    /**
     * Test get business type repository.
     */
    @Test
    void testGetBusinessTypeRepository(){
        Repositories instamce = Repositories.getInstance();
        assertNotNull(instamce.getBusinessTypeRepository());
    }

    /**
     * Test get store repository.
     */
    @Test
    void testGetStoreRepository(){
        Repositories instamce = Repositories.getInstance();
        assertNotNull(instamce.getStoreRepository());
    }

    /**
     * Test get employee repository.
     */
    @Test
    void testGetEmployeeRepository(){
        Repositories instamce = Repositories.getInstance();
        assertNotNull(instamce.getEmployeeRepository());
    }

    /**
     * Test get property repository.
     */
    @Test
    void testGetPropertyRepository(){
        Repositories instamce = Repositories.getInstance();
        assertNotNull(instamce.getPropertyRepository());
    }

    /**
     * Test get announcement repository.
     */
    @Test
    void testGetAnnouncementRepository(){
        Repositories instamce = Repositories.getInstance();
        assertNotNull(instamce.getAnnouncementRepository());
    }

}