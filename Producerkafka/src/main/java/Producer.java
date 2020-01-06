import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.StringTokenizer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.connect.json.JsonSerializer;

import java.util.Properties;
import java.util.Scanner;
public class Producer {

    private String KAFKA_BROKER_URL="192.168.76.128:9092";
    private String TopicName="testTopic";
    private String clientID="client_prod_1";
    private int counter;
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
    public static void main(String[] args) {
        new Producer();
    }

    public Producer() {
        Properties properties=new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,KAFKA_BROKER_URL);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,clientID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        ObjectMapper objectMapper = new ObjectMapper();

        Soceite s = new Soceite();
        s.setNom("IMB");
        s.setTypeOrdre("Achat");
        s.setNbrAction(4);
        s.setPrixAction(100000);
        JsonNode  jsonNode = objectMapper.valueToTree(s);
        KafkaProducer kafkaProducer=new KafkaProducer(properties);

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()-> {
            s.setNom("IMB"+Math.random());
            s.setTypeOrdre("Achat");
            s.setNbrAction((int)Math.random());
            s.setPrixAction(Math.random());
            ++counter;
            ProducerRecord<String, JsonNode> pr = new ProducerRecord<String, JsonNode>(TopicName,jsonNode);
            kafkaProducer.send(pr);
        },1000,1000, TimeUnit.MILLISECONDS);
    }
}
