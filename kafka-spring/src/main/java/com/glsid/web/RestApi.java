package com.glsid.web;

import com.glsid.entities.PageEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/kafka")
public class RestApi {

    @Autowired
    KafkaTemplate<String, PageEvent> kafkaTemplate;

    /*@GetMapping("/publish/{message}/{topic}")
    public String sendMessage(@PathVariable String message,@PathVariable String topic){
        kafkaTemplate.send(topic,"key"+message.length(),message);
        return "Message Published successfully";
    }*/

    @GetMapping("/publish/{page}/{topic}")
    public String sendMessage(@PathVariable String page,@PathVariable String topic){
        PageEvent pageEvent=new PageEvent(page,new Date(),new Random().nextInt());
        kafkaTemplate.send(topic,"key"+pageEvent.getPage(),pageEvent);
        return "Message Published successfully";
    }
}
