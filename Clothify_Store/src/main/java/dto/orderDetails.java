package dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class orderDetails {
    private int orderID;
    private int productID;
    private int qty;
}
