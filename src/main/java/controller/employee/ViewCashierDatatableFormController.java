package controller.employee;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCashierDatatableFormController implements Initializable {
    public TableView tblEmployee;
    public TableColumn colEmployeeId;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colEmail;
    public JFXTextField txtEmployeeId;
    public JFXTextField txtName;
    public JFXTextField txtNicNumber;
    public JFXTextField txtEmailAddress;


    ViewCashierDatatableController service = new ViewCashierDatatableController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
       colName.setCellValueFactory(new PropertyValueFactory<>("name"));
       colNIC.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
       colEmail.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
       tblEmployee.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if(newValue!=null) {
                setTextToValues((Employee) newValue);
            }
        }));
        loadTable();

    }


    private void setTextToValues(Employee newValue) {
        txtEmployeeId.setText(newValue.getEmployeeId());
        txtName.setText(newValue.getName());
        txtNicNumber.setText(newValue.getNicNumber());
        txtEmailAddress.setText(newValue.getEmailAddress());

    }


    public void btnRemoveOnAction(ActionEvent actionEvent) {

        if (service.removeEmployee(txtEmployeeId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }

        loadTable();

        txtEmployeeId.setText(null);
        txtName.setText(null);
        txtNicNumber.setText(null);
        txtEmailAddress.setText(null);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Employee employee = new Employee(
                txtEmployeeId.getId(),
                txtName.getText(),
                txtNicNumber.getText(),
                txtEmailAddress.getText()

        );
        service.updateEmployee((employee));
        loadTable();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Employee employee = service.SearchItem(txtEmployeeId.getText());
        setTextToValues(employee);
    }

    private void loadTable() {
        ObservableList<Employee> itemObservableList = service.getAll();
        tblEmployee.setItems(itemObservableList);
    }


}
