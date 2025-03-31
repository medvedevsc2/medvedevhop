package medvedev.dao.get;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSneakerDto {
    private Long id;
    private String brand;
    private String model;
    private Integer size;
    private String color;
    private String description;
    private String city;
}
