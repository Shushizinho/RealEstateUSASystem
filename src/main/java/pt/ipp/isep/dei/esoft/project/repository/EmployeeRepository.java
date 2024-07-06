package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * A repository class for managing all Employees.
 */
public class EmployeeRepository implements Serializable {


    private final List<StoreManager> storeManagers = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<Agent> agents = new ArrayList<>();

    private static final Role AGENT_ROLE = new Role(2,AuthenticationController.ROLE_AGENT);

    /**
     * Returns the Employee object with the specified email address. If no Employee object with the specified email address
     * is found in the list of employees, an IllegalArgumentException is thrown.
     *
     * @param email the email address to look up
     * @return the Employee object with the specified email address
     * @throws IllegalArgumentException if no Employee object with the specified email address is found
     */
    public Employee getEmployeeByEmail(String email){
        Employee employee = null;
        for (Employee employeeCheck : List.copyOf(employees)) {

            if (employeeCheck.hasEmail(email)) {

                employee = employeeCheck;
            }
        }

        if (employee == null) {
            throw new IllegalArgumentException(
                    "Employee requested for [" + email + "] does not exist.");
        }
        return employee;

    }


    /**
     * Returns a list of all the employees who do not have the role of Store Manager.
     *
     * @return a list of all the employees who do not have the role of Store Manager
     */
    public List<Employee> getEmployeeToStoreManagers(){
        List<Employee> copy = List.copyOf(employees);;
        List<Employee> send = new ArrayList<>(copy);

        for (Employee employee2 : copy) {
            if (employee2.getRolesbyDescription(AuthenticationController.ROLE_STORE_MANAGER)){
                send.remove(employee2);
            }
        }
        return send;
    }

    /**
     * Get store managers list.
     *
     * @return the list
     */
    public List<Employee> getStoreManagers(){
        List<Employee> employeeList = List.copyOf(employees);
        List<Employee> storeManagers = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getRolesbyDescription(AuthenticationController.ROLE_STORE_MANAGER)){
                storeManagers.add(employee);
            }
        }

        return  storeManagers;
    }

//    public List<Employee> getStoreManagerByDescription(String storeManagerDescription){
//        List<Employee> employeeList = List.copyOf(employees);
//
//
//
//        if (storeManager == null) {
//            throw new IllegalArgumentException(
//                    "Agent requested for [" + storeManagerDescription + "] does not exist.");
//        }
//    }


    /**
     * Returns a list of employees who have the role of "agent" and are associated with the specified store.
     *
     * @param store The store to filter the agents by.
     * @return A list of agents from the specified store.
     */
    public List<Employee> getAgentsFromStore(Store store) {
        List<Employee> employeesCopy = List.copyOf(employees);
        List<Employee> agentsFromStore = new ArrayList<>();
        for (Employee employee : employeesCopy) {
            if (employee.compareRole(AGENT_ROLE) && employee.compareStore(store)) {
                agentsFromStore.add(employee);
            }
        }
        return agentsFromStore;
    }

    /**
     * Gets agent object.
     *
     * @param employee the employee
     * @return the agent object
     */
    public Agent getAgentObject(Employee employee) {
        Agent agentObj = null;
//        System.out.println(employee);
        for (Agent agent : agents) {
//            System.out.println(agent.getEmployee().equals(employee));
            if (agent.getEmployee().equals(employee)) {
                agentObj = agent.clone();
            }
        }
        return agentObj;
    }

    /**
     * Returns the Employee object representing the agent with the specified description.
     *
     * @param agentDescription the description of the agent to retrieve
     * @return the Employee object representing the agent with the specified description
     */
    public Employee getAgentByDescription(String agentDescription) {
//        List<Employee> employeesCopy = List.copyOf(employees);
//
//        Employee agent = null;
////        if (stores.contains(newStore)) {
////            Store = stores.get(stores.indexOf(newStore));
////        }
//        for (Employee employee : List.copyOf(employees)) {
////            if (employee.compareRole(AGENT_ROLE) && employee.compareDescription(agentDescription) == 0) {
//                agent = employee;
////            }
//        }
//        if (agent == null) {
//            throw new IllegalArgumentException(
//                    "Agent requested for [" + agentDescription + "] does not exist.");
//        }

        return getEmployeeByEmail(agentDescription);
    }

    /**
     * Adds a new employee to the list of employees, if the employee passes validation.
     *
     * @param employee The employee to be added.
     * @return An optional containing the newly added employee if the operation was successful,         or an empty optional otherwise.
     */
    public Optional<Employee> add(Employee employee) {

        Optional<Employee> newEmployee = Optional.empty();
        boolean operationSuccess = false;

        if (validateEmployee(employee)) {
            newEmployee = Optional.of(employee.clone());
            operationSuccess = employees.add(newEmployee.get());
        }

        if (!operationSuccess) {
            newEmployee = Optional.empty();
        }

        return newEmployee;
    }

    /**
     * Add agent optional.
     *
     * @param employee the employee
     * @return the optional
     */
    public Optional<Agent> addAgent(Agent employee) {

        Optional<Agent> newEmployee = Optional.empty();
        boolean operationSuccess = false;

        if (validateAgent(employee)) {
            newEmployee = Optional.of(employee.clone());
            operationSuccess = agents.add(newEmployee.get());
        }

        if (!operationSuccess) {
            newEmployee = Optional.empty();
        }

        return newEmployee;
    }

    /**
     * Validates the given employee by checking if the employee is already present in the list of employees.
     *
     * @param employee The employee to be validated.
     * @return true if the employee is valid (i.e., not already present in the list of employees), false otherwise.
     */

    private boolean validateEmployee(Employee employee) {
        boolean isValid = !employees.contains(employee);
        return isValid;
    }
    private boolean validateAgent(Agent employee) {
        boolean isValid = !agents.contains(employee);
        return isValid;
    }

    /**
     * Returns a copy of the list of employees.
     *
     * @return a copy of the list of employees.
     */
    public List<Employee> getEmployees(){
        return List.copyOf(employees);

    }


    /**
     * Creates a new Employee object with the specified details, adds it to the employee repository, and creates a new user
     * account for the Employee in the authentication repository with a randomly generated password. Returns an Optional
     * containing the new Employee object if the creation was successful, or an empty Optional if the email address is already
     * in use by another Employee.
     *
     * @param name           the name of the new Employee
     * @param email          the email address of the new Employee
     * @param passportNumber the passport number of the new Employee
     * @param taxNumber      the tax number of the new Employee
     * @param address        the address of the new Employee
     * @param phoneNumber    the phone number of the new Employee
     * @param store          the Store object that the new Employee belongs to
     * @param role           the list of Role objects that the new Employee has
     * @return an Optional containing the new Employee object if the creation was successful, or an empty Optional if the email         address is already in use by another Employee
     */
    public Optional<Employee> create(String name, String email, int passportNumber, String taxNumber, Address address, long phoneNumber, Store store, List<Role> role) {


        Employee EmployeeClone = new Employee(name,email,passportNumber,taxNumber,address,phoneNumber,store,role);

        Optional<Employee> newEmployee = Optional.of(EmployeeClone.clone());

        employees.add(EmployeeClone);

        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();

        String pass = createPassword();
        String[] stringRoles = new String[role.size()];
        int i =0;
        for (Role roleEach: role) {
            stringRoles[i] = roleEach.getDescription();
            i++;
        }

        authenticationRepository.addUserWithRoles(name, email, pass, stringRoles );

        createPasswordTxt(email,pass);

        return newEmployee;

    }

    /**
     * Creates a text file containing a password for a given email address.
     *
     * @param email the email address for which the password will be created
     * @param pass the password to be written to the file
     */

    private void createPasswordTxt(String email,String pass){

        String fileName = email + ".txt"; // The name of the file to create
        String fileContent = "Email: " + email + "\n" + "Password: " + pass; // The content to write to the file

        String directory = "src\\main\\resources\\passwords\\";

        try {
            File file = new File(directory,fileName); // Create a new File object with the given file name
            FileWriter writer = new FileWriter(file); // Create a new FileWriter object

            writer.write(fileContent); // Write the content to the file
            writer.close(); // Close the FileWriter
            System.out.println("\u001B[36m=======================\u001B[0m");
            System.out.println("\u001B[32mEmail sent successfully.\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31mAn error occurred while creating the email.\u001B[0m");
            e.printStackTrace();
        }
    }


    /**
     * Generates a random password of length 7, consisting of three uppercase letters,
     * two digits, and two lowercase letters.
     *
     * @return the randomly generated password as a string
     */

    private String createPassword() {



        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        Random random = new SecureRandom();

        int length = 7; // Set the desired length of the password

        StringBuilder sb = new StringBuilder(length);
        // Add three uppercase letters
        for (int i = 0; i < 3; i++) {
            sb.append(upper.charAt(random.nextInt(upper.length())));
        }
        // Add two digits
        for (int i = 0; i < 2; i++) {
            sb.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        // Fill up the remaining length with random characters
        for (int i = 0; i < length - 5; i++) {
            sb.append(lower.charAt(random.nextInt(lower.length())));
        }

        return sb.toString();
    }





}
