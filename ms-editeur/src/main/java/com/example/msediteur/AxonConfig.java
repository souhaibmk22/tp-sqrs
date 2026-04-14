package com.example.msediteur;

import com.thoughtworks.xstream.XStream;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public EntityManagerProvider entityManagerProvider() {
        return () -> entityManager;
    }

    @Bean
    @Primary
    public XStreamSerializer xStreamSerializer() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[]{
                "com.example.coreapi.**",
                "com.example.msediteur.**"
        });
        return XStreamSerializer.builder().xStream(xStream).build();
    }
}
