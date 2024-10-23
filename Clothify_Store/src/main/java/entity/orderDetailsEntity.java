package entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class orderDetailsEntity {
    private int orderID;
    private int productID;
    private int qty;
}
