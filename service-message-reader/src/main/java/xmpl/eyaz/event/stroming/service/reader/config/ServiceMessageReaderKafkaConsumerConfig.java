package xmpl.eyaz.event.stroming.service.reader.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServiceMessageReaderKafkaConsumerConfig<K extends Serializable, V extends Serializable> {

    private final ServiceMessageReaderKafkaConfigData serviceMessageReaderKafkaConfigData;

    public ServiceMessageReaderKafkaConsumerConfig(ServiceMessageReaderKafkaConfigData serviceMessageReaderKafkaConfigData) {
        this.serviceMessageReaderKafkaConfigData = serviceMessageReaderKafkaConfigData;
    }

    @Bean
    public Map<String, Object> serviceContentConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serviceMessageReaderKafkaConfigData.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, serviceMessageReaderKafkaConfigData.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, serviceMessageReaderKafkaConfigData.getValueDeserializer());
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, serviceMessageReaderKafkaConfigData.getValueDeserializer());
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, serviceMessageReaderKafkaConfigData.getKeyDeserializer());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, serviceMessageReaderKafkaConfigData.getAutoOffsetReset());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, serviceMessageReaderKafkaConfigData.getSessionTimeoutMs());
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, serviceMessageReaderKafkaConfigData.getHeartbeatIntervalMs());
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, serviceMessageReaderKafkaConfigData.getMaxPollIntervalMs());
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,
                serviceMessageReaderKafkaConfigData.getMaxPartitionFetchBytesDefault() *
                        serviceMessageReaderKafkaConfigData.getMaxPartitionFetchBytesBoostFactor());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, serviceMessageReaderKafkaConfigData.getMaxPollRecords());
        return props;
    }

    @Bean
    public ConsumerFactory<K, V> serviceContentConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(serviceContentConsumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>> serviceContentConsumerkafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(serviceContentConsumerFactory());
        factory.setBatchListener(serviceMessageReaderKafkaConfigData.getBatchListener());
        factory.setConcurrency(serviceMessageReaderKafkaConfigData.getConcurrencyLevel());
        factory.setAutoStartup(serviceMessageReaderKafkaConfigData.getAutoStartup());
        factory.getContainerProperties().setPollTimeout(serviceMessageReaderKafkaConfigData.getPollTimeoutMs());
        return factory;
    }
}
