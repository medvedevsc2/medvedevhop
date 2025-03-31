package medvedev.dao.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSneakerDto {
    private String brand;
    private String model;
    private Integer size;
    private Integer price;
    private String color;
    private String description;
    private String city;
}
