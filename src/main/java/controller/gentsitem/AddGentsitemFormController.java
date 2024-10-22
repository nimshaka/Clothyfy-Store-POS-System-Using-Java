package controller.gentsitem;

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
import model.GentsItem;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddGentsitemFormController implements Initializable {
    public JFXTextField txtPrice;
    public Label lblGentsID;
    public JFXTextField txtQty;
    public JFXTextField txtSize;
    public JFXTextField txtName;

    AddGentsitemController service = new AddGentsitemController();


    private String generateGentsID() {
        int gentsitemCounter = getNextEmployeeCounter();


        return String.format("G%03d",  gentsitemCounter);
    }


    private int getNextEmployeeCounter() {
        int  gentsitemCounter = 0;

        try {

            String SQL = "SELECT COUNT(*) FROM GentsItem";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            if (resultSet.next()) {
                gentsitemCounter = resultSet.getInt(1);
            }

            gentsitemCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  gentsitemCounter;
    }


    public void setgentsitemId(String gentsietemId) {
        lblGentsID.setText(gentsietemId);
    }


    public void btnViewTableOnAction(ActionEvent actionEvent) {

        GentsItem gentsItem = new GentsItem(
                lblGentsID.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtSize.getText(),
                Integer.parseInt(txtQty.getText())
        );


        if (service.addGentssitem(gentsItem)) {
            try {

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_gentsitem_datatable_form.fxml")));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Clothify Panadura");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Alert(Alert.AlertType.INFORMATION, "GentsItem Added !!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "GentsItem Not Added :(").show();
        }

        String gentsietemId = generateGentsID();
        lblGentsID.setText(gentsietemId);


        txtName.setText(null);
        txtPrice.setText(null);
        txtSize.setText(null);
        txtQty.setText(null);

    }

    public void btnViewTableOnAction2(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_gentsitem_datatable_form.fxml")));
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

        lblGentsID.setText(generateGentsID());
    }
}
