package model;

import controller.kidsitem.ViewKidsitemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class KidsItem {
    private String kidsId;
    private String name;
    private Double price;
    private String size;
    private Integer qty;

}
