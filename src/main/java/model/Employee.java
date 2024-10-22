package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Employee {
    private String employeeId;
    private  String name;
    private String nicNumber;
    private String emailAddress;
}
