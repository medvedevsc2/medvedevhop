package medvedev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder; // Add this import
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class MedvedevhopApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(MedvedevhopApplication.class);

    public static void main(String[] args) {
        logger.info("Starting MedvedevhopApplication...");
        SpringApplication.run(MedvedevhopApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MedvedevhopApplication.class);
    }
}
