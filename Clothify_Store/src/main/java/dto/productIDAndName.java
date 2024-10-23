package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class productIDAndName {
    private int pid;
    private String pname;
}
