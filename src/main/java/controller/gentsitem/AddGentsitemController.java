package controller.gentsitem;

import db.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.GentsItem;
import model.LadiesItem;
import model.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddGentsitemController implements AddGentsitemService{

    private static AddGentsitemController instance;

    AddGentsitemController() {
    }

    public static AddGentsitemController getInstance() {
        return instance == null ? instance = new AddGentsitemController() : instance;
    }

    public ObservableList<String> getgentsitmeId() {
        ObservableList<String> gentsitemId = FXCollections.observableArrayList();
        ObservableList<GentsItem> itemObservableList = getAll();
        itemObservableList.forEach(item -> {
            gentsitemId.add(item.getGentsId());
        });

        return gentsitemId;

    }

    public GentsItem searchGentsitem(String gentsietemId) {
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
    public boolean addGentssitem(GentsItem gentsItem) {
        String SQL = "INSERT INTO GentsItem VALUES(?,?,?,?,?)";
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
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

        return false;
    }


    public boolean updateStock2(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail: orderDetails){
            boolean updateStock2 = updateStock2((List<OrderDetail>) orderDetail);
            if (!updateStock2){
                return false;
            }
        }
        return true;
    }
}





