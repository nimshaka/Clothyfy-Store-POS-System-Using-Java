package controller.gentsitem;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GentsItem;


import java.net.URL;
import java.util.ResourceBundle;

public class ViewGentsitemDatatableFormController implements Initializable {

    @FXML
    private TableView<GentsItem> tblGentsitem;

    @FXML
    private TableColumn<?, ?> colGentsitemId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtGentsitemId;

    ViewGentsitemDatatableController service = new ViewGentsitemDatatableController();


    public void initialize(URL url, ResourceBundle resourceBundle) {

        colGentsitemId.setCellValueFactory(new PropertyValueFactory<>("gentsId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblGentsitem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null) {
                setTextToValues((GentsItem) newValue);
            }
        }));
        loadTable();

    }


    private void setTextToValues(GentsItem newValue) {

        txtGentsitemId.setText(newValue.getGentsId());
        txtName.setText(newValue.getName());
        txtPrice.setText(String.valueOf(newValue.getPrice()));
        txtSize.setText(newValue.getSize());
        txtQty.setText(String.valueOf(newValue.getQty()));

    }


    public void btnRemoveOnAction(ActionEvent actionEvent) {

        if (service.removeGentsitem(txtGentsitemId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }

        loadTable();

        txtGentsitemId.setText(null);
        txtName.setText(null);
        txtPrice.setText(null);
        txtSize.setText(null);
        txtQty.setText(null);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        GentsItem gentsItem = new GentsItem(
                txtGentsitemId.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtSize.getText(),
                Integer.parseInt(txtQty.getText())

        );
        service.updateGentsitem((gentsItem));
        loadTable();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        GentsItem gentsItem = service.SearchGentesitem(txtGentsitemId.getText());
        setTextToValues(gentsItem);
    }

    private void loadTable() {
        ObservableList<GentsItem> itemObservableList = service.getAll();
        tblGentsitem.setItems(itemObservableList);
    }


}
