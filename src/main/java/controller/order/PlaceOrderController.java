package controller.order;

import controller.gentsitem.AddGentsitemController;
import controller.kidsitem.AddKidsitemController;
import controller.ladiesitem.AddLadiesitemController;
import db.DBConnection;
import javafx.scene.control.Alert;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaceOrderController{
    public void placeOrder(Order order) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            String SQL = "INSERT INTO Orders VALUE(?,?,?,?)";
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1, order.getOrderId());
            psTm.setObject(2, order.getOrderDate());
            psTm.setObject(3, order.getItemId());
            psTm.setObject(4, order.getTotal());
            boolean isOrderAdd = psTm.executeUpdate() > 0;
            if (isOrderAdd) {
                boolean isOrderDetailAdd = new OrderDetailController().addOrderDetail(order.getOrderDetails());
                if (isOrderDetailAdd) {
                    boolean isUpdateStock = AddLadiesitemController.getInstance().updateStock(order.getOrderDetails());
                    if (isUpdateStock) {
                        connection.commit();
                        new Alert(Alert.AlertType.INFORMATION, "Order Placed !!").show();
                    }
                }
            }
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
