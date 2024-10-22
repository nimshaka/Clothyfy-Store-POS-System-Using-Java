package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Supplier {
    private String supplierId;
    private  String name;
    private String companyName;
    private String emailAddress;

}
