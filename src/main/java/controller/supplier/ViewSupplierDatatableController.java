package controller.supplier;

import db.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LadiesItem;
import model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewSupplierDatatableController implements ViewSupllierService{
    @Override
    public boolean removeSupllier(String supplierId) {
        try {
            return CrudUtil.execute("DELETE  FROM Supplier WHERE supplierID=?", supplierId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier SearchSupllier(String supplierId) {
        String SQl = "SELECT * FROM Supplier WHERE supplierID=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQl,supplierId);
            while (resultSet.next()) {
                return new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<Supplier> getAll() {
        String SQl = "SELECT * FROM Supplier";
        ObservableList<Supplier> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQl);

            while (resultSet.next()) {
                itemObservableList.add(new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)

                ));
            }
            return itemObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateSupllier(Supplier supplier) {
        String SQL = "UPDATE Supplier SET  supplierName=?, companyName=?,  emailAddress=? WHERE supplierID=?";
        try {
            return CrudUtil.execute(
                    SQL,
                    supplier.getSupplierId(),
                    supplier.getName(),
                    supplier.getCompanyName(),
                    supplier.getEmailAddress()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
