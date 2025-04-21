package medvedev.services;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import medvedev.dao.get.GetClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClientCache {

    private static final Logger logger = LoggerFactory.getLogger(ClientCache.class);

    // Потокобезопасная мапа: бренд -> список клиентов
    private final Map<String, List<GetClientDto>> clientBrandCache = new ConcurrentHashMap<>();

    public List<GetClientDto> get(String brand) {
        logger.info("Получение клиентов из кеша по бренду: {}", brand);
        return clientBrandCache.get(brand);
    }

    public void put(String brand, List<GetClientDto> clients) {
        logger.info("Добавление в кеш клиентов по бренду: {} ({} клиентов)", brand, clients.size());
        clientBrandCache.put(brand, clients);
    }

    public boolean contains(String brand) {
        boolean exists = clientBrandCache.containsKey(brand);
        logger.info("Проверка наличия бренда {} в кеше: {}", brand, exists);
        return exists;
    }

    public void clear() {
        logger.info("Ручная очистка кеша клиентов");
        clientBrandCache.clear();
    }

    public void remove(String brand) {
        logger.info("Удаление из кеша бренда: {}", brand);
        clientBrandCache.remove(brand);
    }

    @Scheduled(fixedRate = 60_000) // каждую минуту
    public void autoClearCache() {
        logger.info("Автоматическая очистка кеша клиентов...");
        clientBrandCache.clear();
    }

    @PostConstruct
    public void init() {
        logger.info("ClientCache инициализирован");
    }
}
