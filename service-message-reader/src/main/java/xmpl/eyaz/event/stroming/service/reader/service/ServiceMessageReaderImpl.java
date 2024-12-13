package xmpl.eyaz.event.stroming.service.reader.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xmpl.eyaz.event.stroming.service.reader.message.ContentCreatedEventBody;
import xmpl.eyaz.event.stroming.service.reader.message.ContentCreatedEventHeader;

@Service
@Slf4j
public class ServiceMessageReaderImpl implements ServiceMessageReaderService {
    private static final Logger log = LoggerFactory.getLogger(ServiceMessageReaderImpl.class);

    @Override
    public void handle(ContentCreatedEventBody event, ContentCreatedEventHeader header) {

        /**
         *
         *
         *
         *  Do Something
         *
         *
         */

        log.info("Message {} was processed by ServiceMessageReader!", header.getMessageId());
    }
}
