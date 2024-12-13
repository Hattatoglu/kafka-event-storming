package xmpl.eyaz.event.stroming.service.reader.service;

import xmpl.eyaz.event.stroming.service.reader.message.ContentCreatedEventBody;
import xmpl.eyaz.event.stroming.service.reader.message.ContentCreatedEventHeader;

public interface ServiceMessageReaderService {

    void handle(ContentCreatedEventBody event, ContentCreatedEventHeader header);
}
