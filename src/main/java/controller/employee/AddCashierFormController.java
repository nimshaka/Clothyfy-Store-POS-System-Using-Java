package controller.employee;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Employee;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCashierFormController implements Initializable {
    public JFXTextField txtName;
    public JFXTextField txtNicNumber;
    public JFXTextField txtEmailAddress;
    public Label lblEmployeeID;

    AddCashierController service = new AddCashierController();


    private String generateEmployeeID() {
        int employeeCounter = getNextEmployeeCounter();


        return String.format("E%03d", employeeCounter);
    }


    private int getNextEmployeeCounter() {
        int employeeCounter = 0;

        try {

            String SQL = "SELECT COUNT(*) FROM Employee";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            if (resultSet.next()) {
                employeeCounter = resultSet.getInt(1);
            }

            employeeCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeCounter;
    }


    public void setEmployeeId(String employeeId) {
        lblEmployeeID.setText(employeeId);
    }


    public void btnViewTableOnAction(ActionEvent actionEvent) {

        Employee employee = new Employee(
                lblEmployeeID.getText(),
                txtName.getText(),
                txtNicNumber.getText(),
                txtEmailAddress.getText()
        );


        if (service.addEmployee(employee)) {
            try {

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_cashier_datatable_form.fxml")));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Clothify Panadura");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Alert(Alert.AlertType.INFORMATION, "Employee Added !!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee Not Added :(").show();
        }

        String employeeId = generateEmployeeID();
        lblEmployeeID.setText(employeeId);


        txtName.setText(null);
        txtNicNumber.setText(null);
        txtEmailAddress.setText(null);
    }

    public void btnViewTableOnAction2(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_cashier_datatable_form.fxml")));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblEmployeeID.setText(generateEmployeeID());
    }
}
