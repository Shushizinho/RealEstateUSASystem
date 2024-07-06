package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Register employee controller test.
 */
class RegisterEmployeeControllerTest {




    private final RegisterEmployeeController controller = new RegisterEmployeeController();
    private final RoleRepository roleRepository = Repositories.getInstance().getRoleRepository();
    private final StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
    private final String name = "Diogo";
    private final String email = "diogo@this.app";
    private final int passportNumber = 987123456;
    private final String taxNumber = "121-40-4126";
    private final Address address = new Address("345 Example Street", "The Supreme", "Celestial", "2304-0404");
    private final long phoneNumber = 912232412;

    private final String name2 = "Seele";
    private final String email2 = "seele@this.app";
    private final int passportNumber2 = 987765456;
    private final String taxNumber2 = "211-56-1229";
    private final Address address2 = new Address("412 Example Street", "The Star", "Rail", "4404-8972");
    private final long phoneNumber2 = 917672412;

    /**
     * The Storelist.
     */
    List<Store> storelist = new ArrayList<>();

    /**
     * The Store.
     */
    Store store = new Store(1,"Café","cafe@store.ola",new Address("345 Example Street", "The Supreme", "Celestial", "2304-0404"),912292992);
    /**
     * The Store 2.
     */
    Store store2 = new Store(2, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste") , 000000000);
    /**
     * The Role.
     */
    List<Role> role = new ArrayList<>();
    /**
     * The Role test.
     */
    Role role_test = new Role(1,AuthenticationController.ROLE_ADMIN);
    /**
     * The Role test 2.
     */
    Role role_test2 = new Role(2,AuthenticationController.ROLE_AGENT);


    /**
     * The Employee test.
     */
    Employee employee_test = new Employee(name, email, passportNumber, taxNumber, address, phoneNumber,store, role);

    /**
     * Create.
     */
    @Test
    @DisplayName("Register a new Employee that already exists")
    void create(){

        role.add(role_test);

        storeRepository.add(store);
        storelist.add(store);

        String store_name = store.getDesignation();

        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        employeeRepository.add(employee_test);

        Optional<Employee> result = controller.create(name, email, passportNumber, taxNumber, address, phoneNumber,store_name, role);

        assertFalse(result.isPresent());
        storeRepository.clear(store);

    }

    /**
     * Create if not exists.
     */
    @Test
    @DisplayName("Register a new Employee")
    void createIfNotExists(){

        role.add(role_test);
        
        storeRepository.add(store2);
        storelist.add(store2);
        String store_name = "PPROG";

        Optional<Employee> result = controller.create(name2, email2, passportNumber2, taxNumber2, address2, phoneNumber2,store_name, role);

        assertTrue(result.isPresent());
        storeRepository.clear(store2);
    }

    /**
     * Gets role by description.
     */
    @Test
    @DisplayName("Get Role by Description")
    void getRoleByDescription() {
        
        roleRepository.add(role_test);
        Role result = controller.getRoleByDescription(AuthenticationController.ROLE_ADMIN);
        Role expected = new Role(1,AuthenticationController.ROLE_ADMIN);

        assertEquals(expected,result);
    }

    /**
     * Gets role.
     */
    @Test
    @DisplayName("Get a list of all roles the repository has ")
    void getRole() {

        
        roleRepository.add(role_test);
        roleRepository.add(role_test2);


        List<Role> expected = controller.getRole();

        List<Role> result = new ArrayList<>();
        result.add(new Role(1,"ADMINISTRATOR"));
        result.add(new Role(2,"AGENT"));

        assertEquals(expected,result);

    }

    /**
     * Gets store.
     */
    @Test
    @DisplayName("Get a list of all stores the repository has ")

    void getStore() {


        storeRepository.add(store);
        storeRepository.add(store2);


        List<Store> expected = controller.getStore();

        List<Store> result = new ArrayList<>();
        result.add(store);
        result.add(store2);

        assertEquals(expected,result);

        storeRepository.clear(store);
        storeRepository.clear(store2);

    }
}