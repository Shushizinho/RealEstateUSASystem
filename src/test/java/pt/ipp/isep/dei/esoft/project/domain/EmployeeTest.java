package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.Test;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    private final Address address = new Address("teste","teste","teste","teste");
    private final  List<Role> list = new ArrayList<>();
    private final Store store = new Store(0, "HeadQuarters", "head@this.app", new Address("central","central","central","444-233"),  000000000);

    private final EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
    private final Employee employee = new Employee("Agent", "agent@this.app", 2, "111-11-0439", address ,910532123, store, list);


    @Test
    public void saveEmployee() {
        employeeRepository.add(employee);
        assertNotNull(employeeRepository.getEmployeeByEmail("agent@this.app"));
    }

    @Test
    public void newEmployeeWithoutName() {
        Employee employee = new Employee(null, "agent@this.app", 2, "111-11-0439", address ,910532123, store, list);

        employeeRepository.add(employee);
        assertNotNull(employeeRepository.getEmployeeByEmail("agent@this.app"));
    }

    @Test(expected=NullPointerException.class)
    public void newEmployeeWithoutRole() throws Exception{
        Employee employee = new Employee("agent", "agent@this.app", 2, "111-11-0439", address ,910532123, store, null);

        employeeRepository.add(employee);
    }


}