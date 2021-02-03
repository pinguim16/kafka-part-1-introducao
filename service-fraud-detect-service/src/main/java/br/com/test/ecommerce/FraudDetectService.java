package br.com.test.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

/**
 * @author Cesar
 * @see br.com.test.ecommerce
 * @since 28/01/2021
 */
public class FraudDetectService {

    public static void main(String[] args) {
        var fraudDetectService = new FraudDetectService();
        try(var service = new KafkaService(FraudDetectService.class.getSimpleName(),"ECOMMERCE_NEW_ORDER",
                fraudDetectService::parse, Order.class, new HashMap<>())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("-----------------------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        System.out.println("Order Processed...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            //ignoring
            e.printStackTrace();
        }
    }
}
