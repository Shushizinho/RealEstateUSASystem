package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Register store controller test.
 */
class RegisterStoreControllerTest {




    private final RegisterStoreController controller = new RegisterStoreController();
    private final EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();

    private final Integer id = 5;
    private final String designation = "Mercado";
    private final String email5 = "Mercado@this.app";
    private final Address address5 = new Address("666 rua das flores", "Porto", "Porto", "4455-620");
    private final Integer phoneNumber5 = 912547315;

    private final Integer id2 = 6;
    private final String designation2 = "agencia";
    private final String email6 = "agencia@this.app";
    private final Address address6 = new Address("222 rua das flores", "Porto", "Porto", "4455-999");
    private final Integer phoneNumber6 = 911534887;

    private final String name = "Joao";
    private final String email = "joao@this.app";
    private final int passportNumber = 919152354;
    private final String taxNumber = "654-85-8521";
    private final Address address = new Address("666 rua das flores", "Porto", "Porto", "4455-620");
    private final long phoneNumber = 987521460;

    private final String name2 = "Manel";
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
    Store store = new Store(1,"Loja","loja@store.ola",new Address("258 Rua das flores", "Porot", "Porto", "4455-014"),912234444);
    /**
     * The Store 2.
     */
    Store store2 = new Store(2, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste") , 000000000);
    /**
     * The Role.
     */
    List<Role> role = new ArrayList<>();

    /**
     * The Employee test.
     */
    Employee employee_test = new Employee(name, email, passportNumber, taxNumber, address, phoneNumber,store,role);
    /**
     * The Employee test 2.
     */
    Employee employee_test2 = new Employee(name2, email2, passportNumber2, taxNumber2, address2, phoneNumber2,store2,role);

    /**
     * The Store test.
     */
    Store store_test = new Store(id, designation, email5, address5, phoneNumber5);

    /**
     * Create.
     */
//    @Test
    @DisplayName("Register a new Store that already exists")
    void create(){

        employeeRepository.add(employee_test);
        StoreRepository storeRepository = Repositories.getInstance().getStoreRepository();
        storeRepository.add(store_test);
        Optional<Store> result = controller.registerStore(id, designation, email5, address5, phoneNumber5, email);

        assertFalse(result.isPresent());
    }

    /**
     * Create if not exists.
     */
    @Test
    @DisplayName("Register a new Store")
    void createIfNotExists(){

        employeeRepository.add(employee_test2);
        Optional<Store> result = controller.registerStore(id2, designation2, email6, address6, phoneNumber6, email2);

        assertTrue(result.isPresent());
    }

    /**
     * Gets role by description.
     */
    @Test
    @DisplayName("Get Employee by email")
    void getRoleByDescription() {

        employeeRepository.add(employee_test);
        Employee result = controller.getEmployeByEmail(email);
        Employee expected = new Employee(name, email, passportNumber, taxNumber, address, phoneNumber,store,role);

        assertEquals(expected,result);
    }


    /**
     * Gets store.
     */
//    @Test
    @DisplayName("Get a list of employees without the role of store manager ")

    void getStore() {


        employeeRepository.add(employee_test);
        employeeRepository.add(employee_test2);
        List<Employee> expected = controller.getEmployeeToStoreManager();

        List<Employee> result = new ArrayList<>();
        result.add(employee_test);
        result.add(employee_test2);

        for (Employee employee: expected) {
            System.out.println(employee.getName());
        }

        System.out.println("break");

        for (Employee employee1: result) {
            System.out.println(employee1.getName());
        }

        assertEquals(expected,List.copyOf(result));

    }
}