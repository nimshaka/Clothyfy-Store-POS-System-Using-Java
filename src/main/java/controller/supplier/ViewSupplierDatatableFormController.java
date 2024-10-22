package controller.supplier;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Supplier;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewSupplierDatatableFormController implements Initializable {
    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private JFXTextField txtSupplierId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmailAddress;

    ViewSupplierDatatableController service = new ViewSupplierDatatableController();


    public void initialize(URL url, ResourceBundle resourceBundle) {

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null) {
                setTextToValues((Supplier) newValue);
            }
        }));
        loadTable();

    }


    private void setTextToValues(Supplier newValue) {

        txtSupplierId.setText(newValue.getSupplierId());
        txtName.setText(newValue.getName());
        txtCompany.setText(String.valueOf(newValue.getCompanyName()));
        txtEmailAddress.setText(newValue.getEmailAddress());


    }


    public void btnRemoveOnAction(ActionEvent actionEvent) {

        if (service.removeSupllier(txtSupplierId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }

        loadTable();

        txtSupplierId.setText(null);
        txtName.setText(null);
        txtCompany.setText(null);
        txtEmailAddress.setText(null);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmailAddress.getText()

        );
        service.updateSupllier((supplier));
        loadTable();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Supplier supplier = service.SearchSupllier(txtSupplierId.getText());
        setTextToValues(supplier);
    }

    private void loadTable() {
        ObservableList<Supplier> itemObservableList = service.getAll();
        tblSupplier.setItems(itemObservableList);
    }


}
