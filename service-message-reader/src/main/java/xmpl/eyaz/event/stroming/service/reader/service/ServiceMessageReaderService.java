package xmpl.eyaz.event.stroming.service.reader.service;

import xmpl.eyaz.event.storming.app.contract.contentcreated.ContentCreatedEvent;

public interface ServiceMessageReaderService {

    void handle(ContentCreatedEvent event);
}
