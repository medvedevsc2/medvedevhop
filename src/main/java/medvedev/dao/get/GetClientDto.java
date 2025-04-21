package medvedev.dao.get;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetClientDto {
    private Long clientId;
    private String name;
    private String email;
}
