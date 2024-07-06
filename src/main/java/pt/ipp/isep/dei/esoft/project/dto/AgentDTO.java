package pt.ipp.isep.dei.esoft.project.dto;


import pt.ipp.isep.dei.esoft.project.domain.Agent;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

/**
 * The type Agent dto.
 */
public class AgentDTO {
    private EmployeeDTO employee;

    /**
     * Instantiates a new Agent dto.
     *
     * @param employee the employee
     */
    public AgentDTO(EmployeeDTO employee) {
        this.employee = employee;
    }

    /**
     * Gets employee.
     *
     * @return the employee
     */
    public EmployeeDTO getEmployee() {
        return employee;
    }

    /**
     * Sets employee.
     *
     * @param employee the employee
     */
    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }





}
