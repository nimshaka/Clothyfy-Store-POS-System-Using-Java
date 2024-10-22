package controller.ladiesitem;


import db.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import model.LadiesItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewLadiesitemDatatableController implements ViewLadiesitemService {

    @Override
    public boolean removeLadiesitem(String ladiesId) {

        try {
            return CrudUtil.execute("DELETE  FROM LadiesItem WHERE ladiesId=?", ladiesId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LadiesItem SearchLadiesitem(String ladiesId) {

        String SQl = "SELECT * FROM LadiesItem WHERE ladiesId=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQl,ladiesId);
            while (resultSet.next()) {
                return new LadiesItem(
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
    public ObservableList<LadiesItem> getAll() {
        String SQl = "SELECT * FROM LadiesItem";
        ObservableList<LadiesItem> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute(SQl);

            while (resultSet.next()) {
                itemObservableList.add(new LadiesItem(
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
    public boolean updateLadiesitem(LadiesItem ladiesItem) {

        String SQL = "UPDATE LadiesItem SET  name=?, price=?, size=?, Qty=? WHERE ladiesId=?";
        try {
            return CrudUtil.execute(
                    SQL,
                    ladiesItem.getLadiesId(),
                    ladiesItem.getName(),
                    ladiesItem.getPrice(),
                    ladiesItem.getSize(),
                    ladiesItem.getQty()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
