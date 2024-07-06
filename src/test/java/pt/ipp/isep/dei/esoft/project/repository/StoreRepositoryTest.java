package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Store repository test.
 */
class StoreRepositoryTest {

    /**
     * The Store.
     */
    Store store = new Store(1,"Loja","loja@store.ola",new Address("258 Rua das flores", "Porot", "Porto", "4455-014"),912234444);
    /**
     * The Role.
     */
    List<Role> role = new ArrayList<>();
    /**
     * The Role test.
     */
    Role role_test = new Role(1, AuthenticationController.ROLE_ADMIN);

    /**
     * Add store if not exists.
     */
//    @Test
    void addStoreIfNotExists() {
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        Employee employee = new Employee("Diogo", "diogo@this.app", 25458841, "3336524", new Address("666 Rua dos frangos", "Trofa", "Ramaldense", "4470-695"), 919996588, store, role);
        Optional<Employee> result = employeeRepository.add( employee );
        Optional<Employee> expected = Optional.of(employee);
        assertEquals(expected,result);
    }

    /**
     * Add store if exists.
     */
    @Test
    void addStoreIfExists() {
        EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
        employeeRepository.add( new Employee("Diogo", "diogo@this.app", 25458841, "3336524", new Address("666 Rua dos frangos", "Trofa", "Ramaldense", "4470-695"), 919996588, store, role));
        Employee employee = new Employee("Diogo", "diogo@this.app", 25458841, "3336524", new Address("666 Rua dos frangos", "Trofa", "Ramaldense", "4470-695"), 919996588, store, role);
        Optional<Employee> result = employeeRepository.add( employee );
        assertEquals(Optional.empty(), result);
    }


}