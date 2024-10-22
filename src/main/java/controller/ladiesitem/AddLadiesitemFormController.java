package controller.ladiesitem;

import com.jfoenix.controls.JFXTextField;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import model.LadiesItem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddLadiesitemFormController implements Initializable {
    public JFXTextField txtPrice;
    public Label lblLadiesID;
    public JFXTextField txtQty;
    public JFXTextField txtSize;
    public JFXTextField txtName;

   AddLadiesitemController service = new AddLadiesitemController();


    private String generateLadiesID() {
        int ladiesitemCounter = getNextEmployeeCounter();


        return String.format("L%03d", ladiesitemCounter);
    }


    private int getNextEmployeeCounter() {
        int ladiesitemCounter = 0;

        try {

            String SQL = "SELECT COUNT(*) FROM LadiesItem";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            if (resultSet.next()) {
                ladiesitemCounter = resultSet.getInt(1);
            }

            ladiesitemCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ladiesitemCounter;
    }


    public void setladiesitemId(String ladiesietemId) {
        lblLadiesID.setText(ladiesietemId);
    }


    public void btnViewTableOnAction(ActionEvent actionEvent) {

        LadiesItem ladiesitem = new LadiesItem(
                lblLadiesID.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtSize.getText(),
                Integer.parseInt(txtQty.getText())
        );


        if (service.addLadiesitem(ladiesitem)) {
            try {

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_ladiesitem_datatable_form.fxml")));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Clothify Panadura");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            new Alert(Alert.AlertType.INFORMATION, "LadiesItem Added !!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "LadiesIteme Not Added :(").show();
        }

        String ladiesietemId = generateLadiesID();
        lblLadiesID.setText(ladiesietemId);


        txtName.setText(null);
        txtPrice.setText(null);
        txtSize.setText(null);
        txtQty.setText(null);

    }

    public void btnViewTableOnAction2(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_ladiesitem_datatable_form.fxml")));
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

        lblLadiesID.setText(generateLadiesID());
    }
}
