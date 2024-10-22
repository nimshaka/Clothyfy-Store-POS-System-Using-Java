package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Order {
    private String orderId;
    private String total;
    private String itemId;
    private LocalDate orderDate;
    List<OrderDetail> orderDetails;
}
