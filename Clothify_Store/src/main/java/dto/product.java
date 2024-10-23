package dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class product {

    //private String image;
    private String productName;
    private String size;
    private double unitPrice;
    private String category;
    private int availableQty;
    private int supplierID;

}
