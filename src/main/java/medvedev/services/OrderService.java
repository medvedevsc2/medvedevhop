package medvedev.services;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import medvedev.dao.create.CreateOrderDto;
import medvedev.dao.entities.Client;
import medvedev.dao.entities.Order;
import medvedev.dao.entities.Sneaker;
import medvedev.dao.get.GetOrderDto;
import medvedev.dao.mappers.OrderMapper;
import medvedev.dao.repository.ClientRepository;
import medvedev.dao.repository.OrderRepository;
import medvedev.dao.repository.SneakerRepository;
import medvedev.enums.OrderStatus;
import medvedev.errors.ErrorMessages;
import medvedev.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final SneakerRepository sneakerRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public GetOrderDto createOrder(CreateOrderDto createOrderDto) {
        // Fetch client based on the clientId from the DTO
        Client client = clientRepository.findById(createOrderDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.USER_NOT_FOUND, createOrderDto.getClientId())
                ));

        // Fetch sneakers based on the sneakerIds from the DTO
        List<Sneaker> sneakers = sneakerRepository.findAllById(createOrderDto.getSneakerIds());
        if (sneakers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    ErrorMessages.NO_VALID_SNEAKERS);
        }

        // Create a new order with the status "CREATED"
        Order order = new Order(null, client, sneakers, LocalDateTime.now(), OrderStatus.CREATED);
        return orderMapper.toDto(orderRepository.save(order));
    }

    public List<GetOrderDto> getAllOrders() {
        return orderMapper.toDtos(orderRepository.findAll());
    }

    public GetOrderDto getOrderById(Long id) {
        Order order = orderRepository.findWithSneakersById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.ORDER_NOT_FOUND, id)
                ));
        return orderMapper.toDto(order);
    }

    public List<GetOrderDto> getOrdersByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.USER_NOT_FOUND, clientId)
                ));

        // Get all orders for the client
        List<Order> orders = orderRepository.findByClient(client);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException(
                    String.format(ErrorMessages.ORDER_NOT_FOUND, clientId)
            );
        }

        return orderMapper.toDtos(orders);
    }

    public GetOrderDto updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findWithSneakersById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format(ErrorMessages.ORDER_NOT_FOUND, id)
                ));

        order.setStatus(status);
        return orderMapper.toDto(orderRepository.save(order));
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    String.format(ErrorMessages.ORDER_NOT_FOUND, id)
            );
        }
        orderRepository.deleteById(id);
    }
}
