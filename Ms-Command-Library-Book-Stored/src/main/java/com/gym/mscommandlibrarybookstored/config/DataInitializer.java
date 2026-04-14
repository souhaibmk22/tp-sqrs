package com.gym.mscommandlibrarybookstored.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedCommandLibraryData(JdbcTemplate jdbcTemplate) {
        return args -> {
            jdbcTemplate.update(
                    "INSERT INTO libraries (library_id, name) VALUES (?, ?) " +
                            "ON DUPLICATE KEY UPDATE name = VALUES(name)",
                    "L1", "Central Library"
            );

            jdbcTemplate.update(
                    "INSERT INTO book (isbn, title, editeur_id, library_id) VALUES (?, ?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE title = VALUES(title), editeur_id = VALUES(editeur_id), library_id = VALUES(library_id)",
                    "ISBN-001", "Microservices CQRS", "E1", "L1"
            );
        };
    }
}
