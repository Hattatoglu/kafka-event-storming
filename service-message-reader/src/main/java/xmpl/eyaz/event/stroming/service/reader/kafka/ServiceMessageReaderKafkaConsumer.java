package xmpl.eyaz.event.stroming.service.reader.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.Serializable;

public interface ServiceMessageReaderKafkaConsumer<T extends Serializable> {
    void consume(ConsumerRecord<String, T> record);
}
