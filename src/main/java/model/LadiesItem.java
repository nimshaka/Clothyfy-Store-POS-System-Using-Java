package model;

import controller.employee.ViewCashierService;
import controller.order.PlaceOrderFormController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class LadiesItem {
    private String ladiesId;
    private  String name;
    private Double price;
    private String size;
    private Integer qty;



}
