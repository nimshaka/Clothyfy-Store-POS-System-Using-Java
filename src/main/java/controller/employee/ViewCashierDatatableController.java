package controller.employee;

import db.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewCashierDatatableController implements ViewCashierService{
    @Override
    public boolean removeEmployee(String employeeId) {

        try {
            return CrudUtil.execute("DELETE  FROM Employee WHERE employeeId=?", employeeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee SearchItem(String employeeId) {

        String SQl = "SELECT * FROM Employee WHERE employeeID=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQl,employeeId);
            while (resultSet.next()) {
                return new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)

                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public ObservableList<Employee> getAll() {
        String SQl = "SELECT * FROM Employee";
        ObservableList<Employee> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQl);

            while (resultSet.next()) {
                itemObservableList.add(new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)

                ));
            }
            return itemObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {

        String SQL = "UPDATE Employee SET  employeeName=?, nicNumber=?, emailAddress=? WHERE employeeID=?";
        try {
            return CrudUtil.execute(
                    SQL,
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getNicNumber(),
                    employee.getEmailAddress()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
