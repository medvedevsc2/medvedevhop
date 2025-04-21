package medvedev.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import medvedev.dao.get.GetOrderDto;
import medvedev.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Запрос для фильтрации заказов по бренду кроссовок
    @GetMapping("/brand/{brand}")
    public List<GetOrderDto> getOrdersBySneakerBrand(@PathVariable String brand) {
        return orderService.getOrdersBySneakerBrand(brand);
    }
}
