package com.bdcc.kafkaspring.configuration;


import com.bdcc.kafkaspring.entity.PageEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    ProducerFactory<String , PageEvent> producerFactory(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG , StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);

    }

    @Bean
    KafkaTemplate<String , PageEvent> kafkaTemplate(){

        return new KafkaTemplate<>(producerFactory());
    }
}
