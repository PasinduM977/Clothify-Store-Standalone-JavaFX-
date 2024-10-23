package dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class customer {
   private int customerID;
   private String customerName;
   private String customerEmail;
}
