package medvedev.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import medvedev.dao.get.GetOrderDto;
import medvedev.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order-controller", description = "Operations related to sneaker orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get all sneaker orders")
    @ApiResponse(responseCode = "200", description = "List of all orders")
    @GetMapping
    public List<GetOrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "Get orders by sneaker brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders filtered by brand"),
            @ApiResponse(responseCode = "404", description = "No orders found for the brand")
    })
    @GetMapping("/brand/{brand}")
    public List<GetOrderDto> getOrdersBySneakerBrand(
            @Parameter(description = "Sneaker brand to filter orders by")
            @PathVariable String brand) {
        return orderService.getOrdersBySneakerBrand(brand);
    }
}
