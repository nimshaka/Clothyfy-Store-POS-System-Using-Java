package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashFormController {
    public void btnManagerLoginOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/manager_login_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public void btnCashierLoginOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/cashier_login_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAboutUsOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/about_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
