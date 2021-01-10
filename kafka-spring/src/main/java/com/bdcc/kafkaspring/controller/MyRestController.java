package com.bdcc.kafkaspring.controller;

import com.bdcc.kafkaspring.entity.PageEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class MyRestController {

    private KafkaTemplate<String, PageEvent> kafkaTemplate;
    JsonDeserializer jsonDeserializer;

    public MyRestController(KafkaTemplate<String, PageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send/{page}/{topic}")
    public String send(@PathVariable String page,
                       @PathVariable String topic){
        PageEvent pageEvent = new PageEvent(page , new Date() , new Random().nextInt(1000));
        kafkaTemplate.send(topic ,"key"+ pageEvent.getPage() , pageEvent);
        return "Message sent ...." ;

    }
}
