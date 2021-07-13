package tests;

import lombok.AllArgsConstructor;
import lombok.Data;;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedEmployee {
    private Integer id;
    private String name;
    private String salary;
    private String age;
}
