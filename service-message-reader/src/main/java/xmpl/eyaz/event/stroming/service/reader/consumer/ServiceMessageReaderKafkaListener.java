package xmpl.eyaz.event.stroming.service.reader.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xmpl.eyaz.event.storming.app.contract.contentcreated.ContentCreatedEvent;
import xmpl.eyaz.event.storming.app.contract.contentcreated.ContentCreatedEventBody;
import xmpl.eyaz.event.storming.app.contract.contentcreated.ContentCreatedEventHeader;
import xmpl.eyaz.event.stroming.service.reader.kafka.ServiceMessageReaderKafkaConsumer;
import xmpl.eyaz.event.stroming.service.reader.service.ServiceMessageReaderService;

import java.util.Arrays;

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

        ContentCreatedEvent event = getContentCreatedEventFromRecord(record);

        ContentCreatedEventHeader header = event.getContentCreatedEventHeader();
        log.info("ServiceMessageReaderKafkaListener read an event with \nheader : \n{}", header.toString());
        ContentCreatedEventBody body = event.getContentCreatedEventBody();
        log.info("ServiceMessageReaderKafkaListener read an event with\nevent : \n{} ", body.toString());

        contentConsumerService.handle(event);

    }

    private ContentCreatedEvent getContentCreatedEventFromRecord(ConsumerRecord<String, String> record) {
        return new ContentCreatedEvent(
                getContentCreatedEventHeader(record.headers()),
                getContentCreatedEventBody(record.value()));
    }

    private ContentCreatedEventHeader getContentCreatedEventHeader(Headers headers) {
        return ContentCreatedEventHeader.builder()
                .agentName(Arrays.toString(headers.lastHeader("agentName").value()))
                .correlationId(Arrays.toString(headers.lastHeader("correlationId").value()))
                .date(Arrays.toString(headers.lastHeader("date").value()))
                .executorUser(Arrays.toString(headers.lastHeader("executorUser").value()))
                .messageId(Arrays.toString(headers.lastHeader("messageId").value()))
                .version(Arrays.toString(headers.lastHeader("version").value()))
                .build();
    }

    private ContentCreatedEventBody getContentCreatedEventBody(String eventBody) {
        try {
            return mapper.readValue(eventBody, ContentCreatedEventBody.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
