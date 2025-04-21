package medvedev.services;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import medvedev.dao.entities.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class OrderCache {

    private static final Logger logger = LoggerFactory.getLogger(OrderCache.class);

    // In-memory cache to store orders by sneaker brand
    private final Map<String, List<Order>> orderCacheByBrand = new HashMap<>();

    // Get orders by brand from cache
    public List<Order> getOrdersByBrandFromCache(String brand) {
        logger.info("Запрос заказов из кеша по бренду: {}", brand);
        return orderCacheByBrand.get(brand);
    }

    // Add orders to cache
    public void addOrdersByBrandToCache(String brand, List<Order> orders) {
        logger.info("Добавление заказов в кеш: бренд = {},"
               + " количество заказов = {}", brand, orders.size());
        orderCacheByBrand.put(brand, orders);
    }

    // Manual clear method if needed
    public void clearCache() {
        logger.info("Очистка кеша вручную...");
        orderCacheByBrand.clear();
    }

    // Auto-clear every minute
    @Scheduled(fixedRate = 60_000) // или cron = "0 * * * * *"
    public void autoClearCache() {
        logger.info("Автоматическая очистка кеша заказов...");
        orderCacheByBrand.clear();
    }

    @PostConstruct
    public void init() {
        logger.info("OrderCache инициализирован");
    }
}
