package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

/**
 * Create Task UI (console). This option is only available for administrators for demonstration purposes.
 */
public class ListEmployeesUI implements Runnable {

    private final RegisterEmployeeController controller = new RegisterEmployeeController();
    private String name;
    private String email;
    private Integer passportNumber;
    private String taxNumber;
    private String[] address = new String[3];
    private Integer phoneNumber;
    private String store;

    private String role;

    private RegisterEmployeeController getController() {
        return controller;
    }

    public void run() {
        System.out.println("=== List ===");

        displayList();
    }

    private void displayList() {
        //display the task categories as a menu with number options to select
        EmployeeRepository emplRep = Repositories.getInstance().getEmployeeRepository();
        int i = 1;
        System.out.println("=======================");
        for (Employee employee : emplRep.getEmployees()) {
            int j = 1;
            System.out.println(i + " - " + employee.getName());
            for (Role role: employee.getRole()) {
                if(j==1){
                    System.out.println("---------Roles--------");
                }
                System.out.println("• " + role.getDescription());
                j=0;
            }
            System.out.println("----------------------");
            System.out.println();
            i++;
        }
        System.out.println("=======================");
    }


}
