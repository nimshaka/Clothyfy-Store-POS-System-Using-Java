package controller.employee;

import javafx.collections.ObservableList;
import model.Employee;

public interface ViewCashierService {
    boolean removeEmployee(String employeeId);

   Employee SearchItem(String employeeId);
    ObservableList<Employee> getAll();
    boolean updateEmployee(Employee employee);
}
