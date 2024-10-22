package controller.kidsitem;

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
import model.KidsItem;
import model.LadiesItem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddKidsitemFormController implements Initializable {
    public JFXTextField txtPrice;
    public Label lblKidsID;
    public JFXTextField txtQty;
    public JFXTextField txtSize;
    public JFXTextField txtName;

    AddKidsitemController service = new AddKidsitemController();


    private String generateKidsID() {
        int kidsitemCounter = getNextEmployeeCounter();


        return String.format("K%03d", kidsitemCounter);
    }


    private int getNextEmployeeCounter() {
        int kidsitemCounter = 0;

        try {

            String SQL = "SELECT COUNT(*) FROM KidsItem";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();

            if (resultSet.next()) {
                kidsitemCounter = resultSet.getInt(1);
            }

            kidsitemCounter++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kidsitemCounter;
    }


    public void setkidsitemId(String kidsietemId) {
        lblKidsID.setText(kidsietemId);
    }


    public void btnViewTableOnAction(ActionEvent actionEvent) {

        KidsItem kidsItem = new KidsItem(
                lblKidsID.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtSize.getText(),
                Integer.parseInt(txtQty.getText())
        );


        if (service.addKidsitem(kidsItem)) {
            try {

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_kidsitem_datatable_form.fxml")));
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

        String kidsietemId = generateKidsID();
        lblKidsID.setText(kidsietemId);


        txtName.setText(null);
        txtPrice.setText(null);
        txtSize.setText(null);
        txtQty.setText(null);

    }

    public void btnViewTableOnAction2(ActionEvent actionEvent) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/view_kidsitem_datatable_form.fxml")));
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

        lblKidsID.setText(generateKidsID());
    }
}
