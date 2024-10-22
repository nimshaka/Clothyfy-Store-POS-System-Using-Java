package controller.ladiesitem;


import db.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.LadiesItem;
import model.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddLadiesitemController implements  AddLadiesitemService{

    private static AddLadiesitemController instance;

    AddLadiesitemController() {
    }

    public static AddLadiesitemController getInstance() {
        return instance == null ? instance = new AddLadiesitemController() : instance;
    }

    public ObservableList<String> getladiesitmeId() {
        ObservableList<String> ladiesitmeId = FXCollections.observableArrayList();
        ObservableList<LadiesItem> itemObservableList = getAll();
        itemObservableList.forEach(item -> {
            ladiesitmeId.add(item.getLadiesId());
        });

        return ladiesitmeId;

    }

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
    public boolean addLadiesitem(LadiesItem ladiesitem) {
        String SQL = "INSERT INTO LadiesItem VALUES(?,?,?,?,?)";
        try {
            return CrudUtil.execute(
                    SQL,
                    ladiesitem.getLadiesId(),
                    ladiesitem.getName(),
                    ladiesitem.getPrice(),
                    ladiesitem.getSize(),
                    ladiesitem.getQty()

            );
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

        return false;
    }

    public boolean updateStock(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail: orderDetails){
            boolean updateStock = updateStock((List<OrderDetail>) orderDetail);
            if (!updateStock){
                return false;
            }
        }
        return true;
    }
}
