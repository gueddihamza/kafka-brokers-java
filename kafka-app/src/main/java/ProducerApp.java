import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerApp {

    private int counter;

    public ProducerApp() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG , "client-producer-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG , StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , StringSerializer.class.getName());

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(properties);
        Random random = new Random();

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
            String key = String.valueOf(++counter);
            String value = String.valueOf(random.nextDouble() * 999999);
            kafkaProducer.send(new ProducerRecord<String,String>("test1" , key , value) ,(metadata , exception)->{
                System.out.println("Sending message => "+value+" Partition => "+metadata.partition()
                        + "=> "+metadata.offset());
            });

        }, 1000 , 1000 , TimeUnit.MILLISECONDS);


    }

    public static void main(String[] args) {
        new ProducerApp();
    }
}
