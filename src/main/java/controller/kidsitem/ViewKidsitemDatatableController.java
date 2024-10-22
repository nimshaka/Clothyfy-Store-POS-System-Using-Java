package controller.kidsitem;

import db.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.KidsItem;
import model.LadiesItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewKidsitemDatatableController implements ViewKidsitemService{
    @Override
    public boolean removeKidsitem(String kidsId) {
        try {
            return CrudUtil.execute("DELETE  FROM KidsItem WHERE kidsId=?", kidsId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KidsItem SearchKidsitem(String kidsId) {
        String SQl = "SELECT * FROM KidsItem WHERE kidsId=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQl,kidsId);
            while (resultSet.next()) {
                return new KidsItem(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ObservableList<KidsItem> getAll() {
        String SQl = "SELECT * FROM KidsItem";
        ObservableList<KidsItem> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQl);

            while (resultSet.next()) {
                itemObservableList.add(new KidsItem(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                ));
            }
            return itemObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateKidsitem(KidsItem kidsItem) {
        String SQL = "UPDATE LadiesItem SET  name=?, price=?, size=?, Qty=? WHERE KidsId=?";
        try {
            return CrudUtil.execute(
                    SQL,
                    kidsItem.getKidsId(),
                    kidsItem.getName(),
                    kidsItem.getPrice(),
                    kidsItem.getSize(),
                    kidsItem.getQty()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
