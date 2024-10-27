package entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class orderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;
    private double totalCost;
    private String paymentType;
    private LocalDate orderDate;
    private String status;
    private int userID;
    private String customerName;
    private String customerEmail;

}
