package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetail {
    private String orderId;
    private Double total;
    private String itemId;
    private Integer qty;
}
