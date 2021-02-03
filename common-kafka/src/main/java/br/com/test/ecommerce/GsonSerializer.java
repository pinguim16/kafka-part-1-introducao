package br.com.test.ecommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author Cesar
 * @see br.com.test.ecommerce
 * @since 31/01/2021
 */
public class GsonSerializer<T> implements Serializer<T> {

    private final Gson gson = new GsonBuilder().create();

    @Override
    public byte[] serialize(String s, T obj) {
        return gson.toJson(obj).getBytes();
    }
}
