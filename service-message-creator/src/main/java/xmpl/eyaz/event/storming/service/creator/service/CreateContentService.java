package xmpl.eyaz.event.storming.service.creator.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xmpl.eyaz.event.storming.service.creator.dto.CreateContentRequest;
import xmpl.eyaz.event.storming.service.creator.dto.CreateContentResponse;
import xmpl.eyaz.event.storming.service.creator.publisher.EventPublisher;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Slf4j
public class CreateContentService implements ContentService{

    private static final Logger log = LoggerFactory.getLogger(CreateContentService.class);
    private final EventPublisher eventPublisher;

    public CreateContentService(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CreateContentResponse createContent(CreateContentRequest request) {
        String link = generateLink(request.getUsername(), request.getContent());
        request.setUserId(UUID.randomUUID().toString());
        request.setCreationDate(ZonedDateTime.now(ZoneOffset.UTC));
        request.setAggregateId(UUID.randomUUID());
        request.setVersion(0);
        log.info("ContentService processed CreateContentRequest : \nlink : {}\nuserId : {}\ndate : {}\naggregateId : {}\nversion : {}",
                link, request.getUserId(), request.getCreationDate(), request.getAggregateId(), request.getVersion());

        eventPublisher.publish(request, link);

        CreateContentResponse response = new CreateContentResponse();
        response.setUsername(request.getUsername());
        response.setLink(link);

        return response;
    }

    private String generateLink(String username, String content) {
        String link = username+content;
        return link.substring(0, 10);
    }
}
