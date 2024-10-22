package controller.gentsitem;

import db.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.GentsItem;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewGentsitemDatatableController implements  ViewGentsitemService {
    @Override
    public boolean removeGentsitem(String gentsietemId) {
        try {
            return CrudUtil.execute("DELETE  FROM GentsItem WHERE gentsId=?", gentsietemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GentsItem SearchGentesitem(String gentsietemId) {
        String SQl = "SELECT * FROM GentsItem WHERE gentsId=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQl,gentsietemId);
            while (resultSet.next()) {
                return new GentsItem(
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
    public ObservableList<GentsItem> getAll() {
        String SQl = "SELECT * FROM GentsItem";
        ObservableList<GentsItem> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQl);

            while (resultSet.next()) {
                itemObservableList.add(new GentsItem(
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
    public boolean updateGentsitem(GentsItem gentsItem) {
        String SQL = "UPDATE GentsItem SET  name=?, price=?, size=?, Qty=? WHERE gentsId=?";
        try {
            return CrudUtil.execute(
                    SQL,
                    gentsItem.getGentsId(),
                    gentsItem.getName(),
                    gentsItem.getPrice(),
                    gentsItem.getSize(),
                    gentsItem.getQty()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
