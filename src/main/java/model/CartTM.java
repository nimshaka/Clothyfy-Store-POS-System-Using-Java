package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class CartTM {
    private String itemId;
    private String itemId2;
    private String itemId3;
    private String name;
    private Integer qty;
    private Double unitPrice;
    private Double total;
}
