package xmpl.eyaz.event.storming.service.creator.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class KafkaProducerImpl<K extends Serializable, V extends Serializable> implements KafkaProducer<K, V>{

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendRecord(ProducerRecord<K, V> record) {
        kafkaTemplate.send(record);
    }
}
