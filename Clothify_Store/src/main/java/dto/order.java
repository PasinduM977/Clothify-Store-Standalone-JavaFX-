package dto;


import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class order {
    private int orderID;
    private double totalCost;
    private String paymentType;
    private LocalDate orderDate;
    private boolean status;
    private int userID;
    private int customerID;

}
