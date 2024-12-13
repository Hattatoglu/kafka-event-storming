package xmpl.eyaz.event.storming.service.creator.service;

import xmpl.eyaz.event.storming.service.creator.dto.CreateContentRequest;
import xmpl.eyaz.event.storming.service.creator.dto.CreateContentResponse;

public interface ContentService {
    CreateContentResponse createContent(CreateContentRequest request);
}
