package dto;


import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class orders {

    private double totalCost;
    private String paymentType;
    private LocalDate orderDate;
    private String status;
    private int userID;
    private String customerName;
    private String customerEmail;

}
