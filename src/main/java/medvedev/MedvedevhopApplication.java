package medvedev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder; // Add this import
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class MedvedevhopApplication extends SpringBootServletInitializer {

    private static final Logger loger = LoggerFactory.getLogger(MedvedevhopApplication.class);

    public static void main(String[] args) {
        loger.info("Starting MedvedevhopApplication...");
        SpringApplication.run(MedvedevhopApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MedvedevhopApplication.class);
    }
}
