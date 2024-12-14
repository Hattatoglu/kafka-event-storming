package xmpl.eyaz.event.storming.service.creator.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xmpl.eyaz.event.storming.app.contract.contentcreated.ContentCreatedEvent;
import xmpl.eyaz.event.storming.app.contract.contentcreated.ContentCreatedEventBody;
import xmpl.eyaz.event.storming.app.contract.contentcreated.ContentCreatedEventHeader;
import xmpl.eyaz.event.storming.service.creator.dto.CreateContentRequest;
import xmpl.eyaz.event.storming.service.creator.kafka.KafkaProducer;


import java.time.ZonedDateTime;
import java.util.UUID;

@Component
@Slf4j
public class KafkaEventPublisher implements EventPublisher{

    private static final Logger log = LoggerFactory.getLogger(KafkaEventPublisher.class);
    private final KafkaProducer<String, String> kafkaProducer;

    @Value("${service-content-publisher-messaging.topic.service-content-consumer-topic-name}")
    private String TOPIC;

    public KafkaEventPublisher(KafkaProducer<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(CreateContentRequest request, String link) {

        UUID messageId = UUID.randomUUID();
        ContentCreatedEvent event = getEvent(request, link, messageId);

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC,
                messageId.toString(),
                getEventBodyPayload(event.getContentCreatedEventBody()));

        event.getContentCreatedEventHeader().getAllHeaders().forEach(header -> {
            record.headers().add(header.key(), header.value());
        });

        kafkaProducer.sendRecord(record);
        log.info("KafkaEventPublisher sent an event with messageId : {} ", messageId.toString());
    }

    private ContentCreatedEvent getEvent(CreateContentRequest request, String link, UUID messageId) {
        return new ContentCreatedEvent(
                getContentCreatedEventHeader(request, messageId),
                getContentCreatedEventBody(request, link));
    }

    private ContentCreatedEventHeader getContentCreatedEventHeader(CreateContentRequest request, UUID messageId) {
        ZonedDateTime time = ZonedDateTime.now();

        return ContentCreatedEventHeader.builder()
                .agentName("service-content-publisher")
                .correlationId(String.valueOf(UUID.randomUUID()))
                .date(String.valueOf(time.toInstant().toEpochMilli()))
                .executorUser(request.getUsername())
                .messageId(messageId.toString())
                .version(String.valueOf(request.getVersion()))
                .build();

    }

    public ContentCreatedEventBody getContentCreatedEventBody(CreateContentRequest request, String link) {

        return ContentCreatedEventBody.builder()
                .createdBy(request.getUsername())
                .creationDate(String.valueOf(request.getCreationDate().toInstant().toEpochMilli()))
                .description("description")
                .eventType("CONTENT_CREATED")
                .aggregateId(request.getAggregateId().toString())
                .content(request.getContent())
                .link(link)
                .modifiedBy(request.getUsername())
                .name("content create")
                .status("ACTIVE")
                .version(String.valueOf(0))
                .build();
    }

    public String getEventBodyPayload(ContentCreatedEventBody body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ContentCreatedEventBody getContentCreatedEventBodyFromPayload(String payload) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(payload, ContentCreatedEventBody.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
