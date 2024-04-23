package tn.esprit.EldSync.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("Configuring resource handlers for file storage paths.");
        registry.addResourceHandler("/file-storage-path/**")
                .addResourceLocations("file:///C:/Users/tbagh/OneDrive/Bureau/ESPRIT/ESPRIT-S3-4SE4/PI Dev 2/EldSync_back/file-storage-path/");
    }
}
