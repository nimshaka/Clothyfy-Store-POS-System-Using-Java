package controller;


import com.jfoenix.controls.JFXTextField;
import controller.employee.AddCashierFormController;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerLoginFormController {


    public JFXTextField txtPassword;
    public JFXTextField txtUserName;



    public void btnManagerLoginOnAction(ActionEvent actionEvent) {

            try {
                String SQL= "SELECT * FROM Manager WHERE userName =? and password = ?";
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement psTm = connection.prepareStatement(SQL);
                psTm.setString(1, txtUserName.getText());
                psTm.setString(2,txtPassword.getText());

                ResultSet resultSet = psTm.executeQuery();
                Alert alert;

                if (txtUserName.getText().isEmpty() | txtPassword.getText().isEmpty()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill User_Name And Password!");
                    alert.show();
                }else{
                    if(resultSet.next()){
                        Stage stage = new Stage();
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/add_cashier_form.fxml"))));
                            stage.show();

                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Done Message");
                            alert.setHeaderText(null);
                            alert.setContentText("WellCome !");
                            alert.show();





                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong User_Name Or Password");
                        alert.show();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            txtUserName.setText(null);
            txtPassword.setText(null);
        }

    }
