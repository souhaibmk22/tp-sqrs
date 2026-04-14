package com.example.msediteur.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedEditeurData(JdbcTemplate jdbcTemplate) {
        return args -> jdbcTemplate.update(
                "INSERT INTO editeur (editeur_id, name, pays) VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE name = VALUES(name), pays = VALUES(pays)",
                "E1", "OReilly", "USA"
        );
    }
}
