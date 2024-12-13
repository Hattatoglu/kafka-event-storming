package xmpl.eyaz.event.stroming.service.reader.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xmpl.eyaz.event.stroming.service.reader.kafka.ServiceMessageReaderKafkaConsumer;
import xmpl.eyaz.event.stroming.service.reader.message.ContentCreatedEventBody;
import xmpl.eyaz.event.stroming.service.reader.message.ContentCreatedEventHeader;
import xmpl.eyaz.event.stroming.service.reader.service.ServiceMessageReaderService;

@Component
public class ServiceMessageReaderKafkaListener implements ServiceMessageReaderKafkaConsumer<String> {

    private static final Logger log = LoggerFactory.getLogger(ServiceMessageReaderKafkaListener.class);
    private final ServiceMessageReaderService contentConsumerService;

    private ObjectMapper mapper;

    public ServiceMessageReaderKafkaListener(ServiceMessageReaderService contentConsumerService) {
        this.contentConsumerService = contentConsumerService;
        this.mapper = new ObjectMapper();
    }

    @Override
    @KafkaListener(id = "${service-content-consumer-messaging.group.service-content-consumer-group-id}",
            topics = "${service-content-consumer-messaging.topic.service-content-consumer-topic-name}")
    public void consume(ConsumerRecord<String, String> record) {

        log.info("An message was received from \ntopic : {}\npartition : {}\noffset : {}\nkey : {}",
                record.topic(), record.partition(), record.offset(), record.key());

        Header headers = record.headers().lastHeader("custom-header");
        String body = record.value();

        ContentCreatedEventHeader header = getHeader(headers);
        log.info("ServiceMessageReaderKafkaListener read an event with \nheader : \n{}", header.toString());
        ContentCreatedEventBody event = getModel(body);
        log.info("ServiceMessageReaderKafkaListener read an event with\nevent : \n{} ", event.toString());

        contentConsumerService.handle(event, header);

    }


    private ContentCreatedEventHeader getHeader(Header header) {
        String json = new String(header.value());
        try {
            return mapper.readValue(json, ContentCreatedEventHeader.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    private ContentCreatedEventBody getModel(String event) {
        try {
            return mapper.readValue(event, ContentCreatedEventBody.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
