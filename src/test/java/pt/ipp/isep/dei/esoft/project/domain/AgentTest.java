package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.Test;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgentTest{

    private final Address address = new Address("teste","teste","teste","teste");
    private final  List<Role> list = new ArrayList<>();
    private final EmployeeRepository employeeRepository = Repositories.getInstance().getEmployeeRepository();
    private final Store store = new Store(0, "HeadQuarters", "head@this.app", new Address("central","central","central","444-233"),  000000000);

    private final Employee employee = new Employee("Agent", "agent@this.app", 2, "111-11-0439", address ,910532123, store, list);

    private final Employee employee1 = new Employee("Agent", "agent@this.app", 4, "111-11-0449", address ,910532223, store, list);

    @Test
    public void testSaveObjectInRepository() {
        Agent a = new Agent(employee);

        employeeRepository.addAgent(a);

        assertNotNull(employeeRepository.getAgentObject(employee));
    }

    @Test
    public void testEqualsDifferentObjects() {
        Agent a = new Agent(employee);

        Agent b = new Agent(employee1);

        boolean f = a.hashCode() != b.hashCode();

        assertFalse(f);
    }

    @Test
    public void testEqualsSameObjects() {
        Agent a = new Agent(employee);

        Agent b = new Agent(employee);

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(expected=NullPointerException.class)
    public void errorNewAgentNullEmployee() throws Exception{
        Employee employee = new Employee(null, null, null, null,  null, 0, null, null);
        employeeRepository.add(employee);
        Agent a = new Agent(employee);
    }



}