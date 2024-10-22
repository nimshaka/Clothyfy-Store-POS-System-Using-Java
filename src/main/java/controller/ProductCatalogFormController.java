package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductCatalogFormController {
    public void btnLadiesOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/add_ladiesitem_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnKidsOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/add_kidsitem_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnGentsOnAction(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/add_gentsitem_form.fxml")));
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Clothify Panadura");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
