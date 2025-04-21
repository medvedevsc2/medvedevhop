package medvedev.services;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import medvedev.dao.entities.Sneaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class SneakerCacheService {

    private static final Logger logger = LoggerFactory.getLogger(SneakerCacheService.class);

    private final Map<String, Sneaker> sneakerCache = new HashMap<>();

    // Метод для получения кроссовка из кеша
    public Sneaker getSneakerFromCache(String brand) {
        logger.info("Попытка получить кроссовок из кеша по бренду: {}", brand);
        return sneakerCache.get(brand);
    }

    // Метод для добавления кроссовка в кеш
    public void addSneakerToCache(String brand, Sneaker sneaker) {
        logger.info("Добавление кроссовка в кеш: бренд = {}", brand);
        sneakerCache.put(brand, sneaker);
    }

    // Метод для автоочистки кеша каждую минуту
    @Scheduled(fixedRate = 60_000) // или cron = "0 * * * * *"
    public void autoClearCache() {
        logger.info("Автоматическая очистка кеша...");
        sneakerCache.clear();
    }

    @PostConstruct
    public void init() {
        logger.info("SneakerCacheService инициализирован");
    }
}
