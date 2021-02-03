package br.com.test.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author Cesar
 * @see br.com.test.ecommerce
 * @since 30/01/2021
 */
public interface ConsumerFunction<T> {

    void consume(ConsumerRecord<String, T> record);
}
