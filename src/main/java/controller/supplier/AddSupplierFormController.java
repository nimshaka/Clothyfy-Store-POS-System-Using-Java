package controller.supplier;

import com.jfoenix.controls.JFXTextField;
import controller.ladiesitem.AddLadiesitemController;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import model.Supplier;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSupplierFormController implements Initializable {


    public JFXTextField txtEmailAddress;
    public JFXTextField txtCompanyName;
    public JFXTextField txtName;
    public Label lblSupplierId;
    AddSupplierController service = new AddSupplierController();


    private String generateSuppliesID() {
        int supplierCounter = getNextEmployeeCounter();


        return String.format("S%03d", supplierCounter);
    }


    private int getNextEmployeeCounter() {
        int supplierCounter = 0;

        try {

            String SQL = "SELECT COUNT(*) FROM Supplier";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            if (resultSet.next()) {
                supplierCounter = resultSet.getInt(1);
            }

            supplierCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplierCounter;
    }


    public void setsupplierId(String supplierId) {
        lblSupplierId.setText(supplierId);
    }


    public void btnViewTableOnAction(ActionEvent actionEvent) {

        Supplier supplier = new Supplier(
                lblSupplierId.getText(),
                txtName.getText(),
                txtCompanyName.getText(),
                txtEmailAddress.getText()
        );


        if (service.addSupplier(supplier)) {
            try {

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_supplier_datatable_form.fxml")));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Clothify Panadura");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Alert(Alert.AlertType.INFORMATION, "Supplier Added !!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Added :(").show();
        }

        String ladiesietemId = generateSuppliesID();
        lblSupplierId.setText(ladiesietemId);


        txtName.setText(null);
        txtCompanyName.setText(null);
        txtEmailAddress.setText(null);


    }

    public void btnViewTableOnAction2(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_supplier_datatable_form.fxml")));
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

        lblSupplierId.setText(generateSuppliesID());
    }
}


