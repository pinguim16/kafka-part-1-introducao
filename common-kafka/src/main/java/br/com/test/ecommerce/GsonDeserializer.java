package br.com.test.ecommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author Cesar
 * @see br.com.test.ecommerce
 * @since 31/01/2021
 */
public class GsonDeserializer<T> implements Deserializer {

    public static final String TYPE_CONFIG = "br.com.test.ecommerce.type_config";
    private final Gson gson = new GsonBuilder().create();
    private Class<T> type;

    @Override
    public void configure(Map configs, boolean isKey) {
        String typeName = String.valueOf(configs.get(TYPE_CONFIG));
        try {
            this.type = (Class<T>) Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Type for deserialization does not exist in the class path.", e);
        }
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), type);
    }
}
