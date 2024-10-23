package entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class customerEntity {
   private int customerID;
   private String customerName;
   private String customerEmail;
}
