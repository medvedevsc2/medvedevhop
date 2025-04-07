package medvedev.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import medvedev.dao.create.CreateOrderDto;
import medvedev.dao.get.GetOrderDto;
import medvedev.enums.OrderStatus;
import medvedev.services.OrderService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class  OrderController {

    private final OrderService orderService;

    @PostMapping
    public GetOrderDto createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return orderService.createOrder(createOrderDto);
    }

    @GetMapping
    public List<GetOrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public GetOrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<GetOrderDto> getOrdersByClient(@PathVariable Long clientId) {
        return orderService.getOrdersByClient(clientId);
    }

    @PutMapping("/{id}/status")
    public GetOrderDto updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
