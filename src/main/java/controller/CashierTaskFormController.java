package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierTaskFormController {
    public void btnProductManageOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/product_catalog_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/place_order_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSupplierManageOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/add_supplier_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
