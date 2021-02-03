package br.com.test.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Cesar
 * @see br.com.test.ecommerce
 * @since 28/01/2021
 */
public class LogService {

    public static void main(String[] args) {
        var logService = new LogService();
        var service = new KafkaService(LogService.class.getSimpleName(), Pattern.compile("ECOMMERCE.*"),
                logService::parse, String.class, Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class));
        service.run();

    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("-----------------------------------------");
        System.out.println("Log " + record.topic());
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        System.out.println("LOG...");
    }
}
