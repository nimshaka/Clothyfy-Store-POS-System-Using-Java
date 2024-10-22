package controller.kidsitem;

import com.jfoenix.controls.JFXTextField;
import controller.ladiesitem.ViewLadiesitemDatatableController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GentsItem;
import model.KidsItem;


import java.net.URL;
import java.util.ResourceBundle;

public class ViewKidsitemDatatableFormController implements Initializable {
    public TableView tblKidsitme;


    @FXML
    private TableColumn<?, ?> colKidsitemId;

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
    private JFXTextField txtKidsitemId;

    ViewKidsitemDatatableController service = new ViewKidsitemDatatableController();


    public void initialize(URL url, ResourceBundle resourceBundle) {

        colKidsitemId.setCellValueFactory(new PropertyValueFactory<>("kidsId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblKidsitme.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null) {
                setTextToValues((KidsItem) newValue);
            }
        }));
        loadTable();

    }


    private void setTextToValues(KidsItem newValue) {

        txtKidsitemId.setText(newValue.getKidsId());
        txtName.setText(newValue.getName());
        txtPrice.setText(String.valueOf(newValue.getPrice()));
        txtSize.setText(newValue.getSize());
        txtQty.setText(String.valueOf(newValue.getQty()));

    }


    public void btnRemoveOnAction(ActionEvent actionEvent) {

        if (service.removeKidsitem(txtKidsitemId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }

        loadTable();

        txtKidsitemId.setText(null);
        txtName.setText(null);
        txtPrice.setText(null);
        txtSize.setText(null);
        txtQty.setText(null);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        KidsItem kidsItem = new KidsItem(
                txtKidsitemId.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtSize.getText(),
                Integer.parseInt(txtQty.getText())

        );
        service.updateKidsitem((kidsItem));
        loadTable();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        KidsItem kidsItem = service.SearchKidsitem(txtKidsitemId.getText());
        setTextToValues(kidsItem);
    }

    private void loadTable() {
        ObservableList<KidsItem> itemObservableList = service.getAll();
        tblKidsitme.setItems(itemObservableList);
    }

}
