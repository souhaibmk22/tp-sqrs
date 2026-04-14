package com.example.msqueryjoin;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @Bean
    @Primary
    public XStreamSerializer xStreamSerializer() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[]{
                "com.example.coreapi.**",
                "com.example.msqueryjoin.**"
        });
        return XStreamSerializer.builder().xStream(xStream).build();
    }
}
