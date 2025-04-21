package medvedev.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import medvedev.dao.entities.Order;
import medvedev.dao.get.GetOrderDto;
import medvedev.dao.mappers.OrderMapper;
import medvedev.dao.repository.OrderRepository;
import medvedev.errors.ErrorMessages;
import medvedev.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;
    private final OrderCache orderCache;  // Внедряем кэш
    public List<GetOrderDto> getAllOrders() {
        // Получи список всех заказов и преобразуй в DTO
        // Пример:
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    // Метод для получения заказов по бренду кроссовок
    public List<GetOrderDto> getOrdersBySneakerBrand(String brand) {
        // Проверяем, есть ли данные в кэше
        List<Order> cachedOrders = orderCache.getOrdersByBrandFromCache(brand);
        if (cachedOrders != null) {
            // Если данные в кэше есть, возвращаем их
            return orderMapper.toDtos(cachedOrders);
        }

        // Если данных нет в кэше, выполняем запрос в базу данных
        List<Order> orders = orderRepository.findOrdersBySneakerBrand(brand);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(ErrorMessages.ORDER_NOT_FOUND, brand)
            );
        }

        // Сохраняем результаты в кэш
        orderCache.addOrdersByBrandToCache(brand, orders);

        // Возвращаем результат
        return orderMapper.toDtos(orders);
    }

    }

