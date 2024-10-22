package controller.order;

import db.util.CrudUtil;
import model.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    public boolean addOrderDetail(List<OrderDetail> orderDetails){
        for (OrderDetail orderDetail:orderDetails) {
            boolean isAdd= addOrderDetail(orderDetail);
            if (!isAdd){
                return false;
            }
        }
        return true;
    }
    public boolean addOrderDetail(OrderDetail orderDetails){
        String SQL = "INSERT INTO orderdetail VALUES(?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,
                    orderDetails.getOrderId(),
                    orderDetails.getTotal(),
                    orderDetails.getItemId(),
                    orderDetails.getQty()

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
