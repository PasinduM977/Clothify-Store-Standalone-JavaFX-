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
public class userEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;
    private String userName;
    private String userEmail;
    private String password;

}
