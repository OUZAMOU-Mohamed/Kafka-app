package com.glsid.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glsid.entities.PageEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
class Soceite{
    private String nom;
    private String typeOrdre;
    private int nbrAction;
    private double prixAction;


}
@Service
public class ConsumerService {

    @KafkaListener(topics ="testTopic",groupId = "sample_consumer")
    public void consumeMessageJson(ConsumerRecord<String, JsonNode> message) throws Exception{
        PageEvent pageEvent=pageEvent(message.value());
    }

    public PageEvent pageEvent(JsonNode json) throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        PageEvent pageEvent=objectMapper.readValue((DataInput) json,PageEvent.class);
        return pageEvent;
    }
}
