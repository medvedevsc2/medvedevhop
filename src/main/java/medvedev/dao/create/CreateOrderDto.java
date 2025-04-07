package medvedev.dao.create;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medvedev.enums.OrderStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {
    private Long clientId;
    private List<Long> sneakerIds; // Renamed to reflect sneaker IDs
    private OrderStatus status; // Updated status type to reflect order status for sneakers
}
