package medvedev.dao.mappers;

import medvedev.dao.create.CreateOrderDto;
import medvedev.dao.entities.Order;
import medvedev.dao.get.GetOrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = BaseMapper.class,
        uses = {ClientMapper.class, SneakerMapper.class})
public interface OrderMapper extends BaseMapper<Order, GetOrderDto> {

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "client", source = "client")
    @Mapping(target = "sneakers", source = "sneakers")
    GetOrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "sneakers", ignore = true)
    Order toEntity(CreateOrderDto dto);
}