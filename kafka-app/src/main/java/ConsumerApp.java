import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConsumerApp {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG , "1000");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG , "true");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG , "test-group-1");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG  , "30000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , StringDeserializer.class.getName());


        KafkaConsumer<String , String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Collections.singleton("test1"));


        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            System.out.println("---------------");
            ConsumerRecords<String , String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));
            consumerRecords.forEach(consumerRecord -> {
                System.out.println("Key=>"+consumerRecord.key()+"=>"+consumerRecord.value()+" "+consumerRecord.offset());

            });
        } , 1000 , 1000 , TimeUnit.MILLISECONDS);
    }
}
