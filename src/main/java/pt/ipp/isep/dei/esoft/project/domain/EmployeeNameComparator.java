package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Comparator;

//StoreNameComparator = new Comparator<Store>()
public class EmployeeNameComparator implements Comparator<Employee>  {

    /**
     * The constant EmployeeNameComparator.
     */
    @Override
    public int compare(Employee employee1, Employee employee2) {

        String employeeName1 = employee1.getName();
        String employeeName2 = employee2.getName();

        //ascending order
        return employeeName1.compareTo(employeeName2);

        //descending order
        //return employeeName2.compareTo(employeeName1);
    }

}
