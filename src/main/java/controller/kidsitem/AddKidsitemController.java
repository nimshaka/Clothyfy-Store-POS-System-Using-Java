package controller.kidsitem;


import db.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.KidsItem;
import model.LadiesItem;
import model.OrderDetail;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddKidsitemController implements AddKidsitemService{
    private static  AddKidsitemController instance;

    AddKidsitemController() {
    }

    public static  AddKidsitemController getInstance() {
        return instance == null ? instance = new AddKidsitemController() : instance;
    }

    public ObservableList<String> getkidssitmeId() {
        ObservableList<String> kidsitemId = FXCollections.observableArrayList();
        ObservableList<KidsItem> itemObservableList = getAll();
        itemObservableList.forEach(item -> {
            kidsitemId.add(item.getKidsId());
        });

        return kidsitemId;

    }


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
    public boolean addKidsitem(KidsItem kidsItem) {
        String SQL = "INSERT INTO KidsItem VALUES(?,?,?,?,?)";
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
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

        return false;
    }
    public boolean updateStock3(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail: orderDetails){
            boolean updateStock3 = updateStock3((List<OrderDetail>) orderDetail);
            if (!updateStock3){
                return false;
            }
        }
        return true;
    }
}
