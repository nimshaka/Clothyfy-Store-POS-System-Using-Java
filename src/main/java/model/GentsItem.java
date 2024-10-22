package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class GentsItem {
    private String gentsId;
    private  String name;
    private Double price;
    private String size;
    private Integer qty;
}
