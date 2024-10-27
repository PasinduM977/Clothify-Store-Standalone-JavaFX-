package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class orderDetailsEntity {
    @Id
    private int orderID;
    @Id
    private int productID;
    private int qty;
}
