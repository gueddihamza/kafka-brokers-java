package com.bdcc.kafkaspring.service;

import com.bdcc.kafkaspring.entity.PageEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @KafkaListener(topics = "test6", groupId = "group-ms")
    public void onMessage(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        System.out.println("*********************");
        PageEvent pageEvent = pageEvent(consumerRecord.value());
        System.out.println(consumerRecord.key());
        System.out.println(pageEvent.getPage() + "," + pageEvent.getDate() + "," + pageEvent.getDuration());
        System.out.println("**********************");
    }

    private PageEvent pageEvent(String jsonPageEvent) throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();
        PageEvent pageEvent = jsonMapper.readValue(jsonPageEvent, PageEvent.class);
        return pageEvent;

    }

}
