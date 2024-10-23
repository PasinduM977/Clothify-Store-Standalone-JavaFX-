package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class productEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;
    //private String image;
    private String productName;
    private String size;
    private double unitPrice;
    private String category;
    private int availableQty;
    private int supplierID;

}
