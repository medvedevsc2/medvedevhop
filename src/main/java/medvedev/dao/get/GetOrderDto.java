package medvedev.dao.get;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import medvedev.enums.OrderStatus;

@Getter
@Setter
public class GetOrderDto {
    private Long id;
    private GetClientDto client;  // Изменили с clientId на объект
    private List<GetSneakerDto> sneakers;  // Изменили с sneakerIds на список объектов
    private LocalDateTime createdAt;
    private OrderStatus status;
}
