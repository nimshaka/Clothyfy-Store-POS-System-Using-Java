package controller.ladiesitem;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LadiesItem;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewLadiesitemDatatableFormController implements Initializable {
    @FXML
    private TableView<LadiesItem> tblLadiesitem;

    @FXML
    private TableColumn<?, ?> colLadiesitemId;

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
    private JFXTextField txtLadiesitemId;

    ViewLadiesitemDatatableController service = new ViewLadiesitemDatatableController();


    public void initialize(URL url, ResourceBundle resourceBundle) {

        colLadiesitemId.setCellValueFactory(new PropertyValueFactory<>("ladiesId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblLadiesitem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null) {
                setTextToValues((LadiesItem) newValue);
            }
        }));
        loadTable();

    }


    private void setTextToValues(LadiesItem newValue) {

        txtLadiesitemId.setText(newValue.getLadiesId());
        txtName.setText(newValue.getName());
        txtPrice.setText(String.valueOf(newValue.getPrice()));
        txtSize.setText(newValue.getSize());
        txtQty.setText(String.valueOf(newValue.getQty()));

    }


    public void btnRemoveOnAction(ActionEvent actionEvent) {

        if (service.removeLadiesitem(txtLadiesitemId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }

        loadTable();

        txtLadiesitemId.setText(null);
        txtName.setText(null);
        txtPrice.setText(null);
        txtSize.setText(null);
        txtQty.setText(null);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        LadiesItem ladiesItem = new LadiesItem(
                txtLadiesitemId.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtSize.getText(),
                Integer.parseInt(txtQty.getText())

        );
        service.updateLadiesitem((ladiesItem));
        loadTable();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        LadiesItem ladiesItem = service.SearchLadiesitem(txtLadiesitemId.getText());
        setTextToValues(ladiesItem);
    }

    private void loadTable() {
        ObservableList<LadiesItem> itemObservableList = service.getAll();
        tblLadiesitem.setItems(itemObservableList);
    }


}
