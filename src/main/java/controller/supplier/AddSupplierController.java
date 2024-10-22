package controller.supplier;


import db.util.CrudUtil;
import javafx.scene.control.Alert;
import model.Supplier;

import java.sql.SQLException;

public class AddSupplierController implements AddSupllierService{
    private static AddSupplierController instance;

    AddSupplierController() {
    }

    public static AddSupplierController getInstance() {
        return instance == null ? instance = new AddSupplierController() : instance;
    }
    @Override
    public boolean addSupplier(Supplier supplier) {
        String SQL = "INSERT INTO Supplier VALUES(?,?,?,?)";
        try {
            return CrudUtil.execute(
                    SQL,
                    supplier.getSupplierId(),
                    supplier.getName(),
                    supplier.getCompanyName(),
                    supplier.getEmailAddress()


            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

        return false;
    }
}
