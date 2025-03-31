package medvedev.dao.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientDto {
    private String clientname;
    private String email;
    private String password; // Added password field for user registration
}
