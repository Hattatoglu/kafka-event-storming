package xmpl.eyaz.event.storming.service.creator.publisher;

import xmpl.eyaz.event.storming.service.creator.dto.CreateContentRequest;

public interface EventPublisher {
    void publish(CreateContentRequest request, String link);;
}
