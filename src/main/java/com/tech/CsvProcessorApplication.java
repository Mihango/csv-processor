package com.tech;

import com.tech.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class CsvProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvProcessorApplication.class, args);
    }
}
