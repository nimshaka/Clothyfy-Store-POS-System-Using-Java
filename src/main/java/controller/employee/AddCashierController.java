package controller.employee;

import db.util.CrudUtil;
import javafx.scene.control.Alert;
import model.Employee;

import java.sql.SQLException;

public class AddCashierController implements AddCashierService {

    private static AddCashierController instance;

    AddCashierController() {
    }

    public static AddCashierController getInstance() {
        return instance == null ? instance = new AddCashierController() : instance;
    }
    @Override
    public boolean addEmployee(Employee employee) {
        String SQL = "INSERT INTO Employee VALUES(?,?,?,?)";
        try {
            return CrudUtil.execute(
                    SQL,
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getNicNumber(),
                    employee.getEmailAddress()

            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

        return false;
    }
}

