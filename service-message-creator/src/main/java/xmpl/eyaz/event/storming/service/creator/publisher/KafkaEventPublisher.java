package xmpl.eyaz.event.storming.service.creator.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xmpl.eyaz.event.storming.service.creator.dto.CreateContentRequest;
import xmpl.eyaz.event.storming.service.creator.kafka.KafkaProducer;
import xmpl.eyaz.event.storming.service.creator.message.ContentCreatedEventBody;
import xmpl.eyaz.event.storming.service.creator.message.ContentCreatedEventHeader;

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

        String body = getContentCreatedEvent(request, link);

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC,
                messageId.toString(), body);

        record.headers().add("custom-header", getContentCreatedEventHeader(request, messageId));

        kafkaProducer.sendRecord(record);
        log.info("KafkaEventPublisher sent an event with messageId : {} ", messageId.toString());
    }

    private byte[] getContentCreatedEventHeader(CreateContentRequest request, UUID messageId) {
        ZonedDateTime time = ZonedDateTime.now();

        ContentCreatedEventHeader header = ContentCreatedEventHeader.builder()
                .agentName("service-content-publisher")
                .correlationId(String.valueOf(UUID.randomUUID()))
                .date(time.toInstant().toEpochMilli())
                .executorUser(request.getUsername())
                .messageId(messageId.toString())
                .version(request.getVersion())
                .build();
        log.info("KafkaEventPublisher created an event with \nheader : \n{}", header.toString());
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(header);
            return json.getBytes();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public String getContentCreatedEvent(CreateContentRequest request, String link) {

        ContentCreatedEventBody body =  ContentCreatedEventBody.builder()
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

        log.info("KafkaEventPublisher created an event with\nbody : \n{} ", body.toString());
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
