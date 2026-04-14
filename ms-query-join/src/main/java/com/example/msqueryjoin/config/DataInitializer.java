package com.example.msqueryjoin.config;

import com.example.msqueryjoin.Entities.EditeurJoinBook;
import com.example.msqueryjoin.Entities.EditeurTemp;
import com.example.msqueryjoin.dao.EditeurJoinBookRepository;
import com.example.msqueryjoin.dao.EditeurTempRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedLab3Data(EditeurTempRepository editeurTempRepository,
                                   EditeurJoinBookRepository editeurJoinBookRepository) {
        return args -> {
            if (editeurTempRepository.findById("E1").isEmpty()) {
                EditeurTemp editeur = new EditeurTemp();
                editeur.setEditeurId("E1");
                editeur.setName("OReilly");
                editeurTempRepository.save(editeur);
            }

            if (editeurJoinBookRepository.findById("E1_ISBN-001").isEmpty()) {
                EditeurJoinBook join = new EditeurJoinBook();
                join.setId("E1_ISBN-001");
                join.setEditeurId("E1");
                join.setIsbn("ISBN-001");
                join.setEditeurName("OReilly");
                join.setBookTitle("Microservices CQRS");
                editeurJoinBookRepository.save(join);
            }
        };
    }
}
