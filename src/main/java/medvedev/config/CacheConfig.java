package medvedev.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CacheConfig {
    private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);
    private static final int MAX_CACHE_SIZE = 100;

    private final Map<String, Object> cache = new LinkedHashMap<>(MAX_CACHE_SIZE, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            boolean shouldRemove = size() > MAX_CACHE_SIZE;
            if (shouldRemove) {
                logger.info("Removing eldest cache entry: {}", eldest.getKey());
            }
            return shouldRemove;
        }
    };

    public Object get(String key) {
        logger.info("Getting data from cache for key: {}", key);
        return cache.get(key);
    }

}
