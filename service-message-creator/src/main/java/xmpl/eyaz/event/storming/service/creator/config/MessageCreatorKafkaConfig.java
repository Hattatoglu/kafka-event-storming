package xmpl.eyaz.event.storming.service.creator.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessageCreatorKafkaConfig<K extends Serializable, V extends Serializable> {

    private final MessageCreatorKafkaConfigData messageCreatorKafkaConfigData;

    public MessageCreatorKafkaConfig(MessageCreatorKafkaConfigData messageCreatorKafkaConfigData) {
        this.messageCreatorKafkaConfigData = messageCreatorKafkaConfigData;
    }

    @Bean
    public Map<String, Object> kafkaProducerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, messageCreatorKafkaConfigData.getBootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, messageCreatorKafkaConfigData.getKeySerializerClass());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, messageCreatorKafkaConfigData.getValueSerializerClass());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, messageCreatorKafkaConfigData.getBatchSize() *
                messageCreatorKafkaConfigData.getBatchSizeBoostFactor());
        props.put(ProducerConfig.LINGER_MS_CONFIG, messageCreatorKafkaConfigData.getLingerMs());
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, messageCreatorKafkaConfigData.getCompressionType());
        props.put(ProducerConfig.ACKS_CONFIG, messageCreatorKafkaConfigData.getAcks());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, messageCreatorKafkaConfigData.getRequestTimeoutMs());
        props.put(ProducerConfig.RETRIES_CONFIG, messageCreatorKafkaConfigData.getRetryCount());
        return props;
    }

    @Bean
    public ProducerFactory<K, V> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProducerConfig());
    }

    @Bean
    public KafkaTemplate<K, V> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
