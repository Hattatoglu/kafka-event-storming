package xmpl.eyaz.event.storming.service.creator.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmpl.eyaz.event.storming.service.creator.dto.CreateContentRequest;
import xmpl.eyaz.event.storming.service.creator.dto.CreateContentResponse;
import xmpl.eyaz.event.storming.service.creator.service.ContentService;

@RestController
@RequestMapping("/api/v1/contents")
@Slf4j
public class ContentController {

    private static final Logger log = LoggerFactory.getLogger(ContentController.class);
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }
    @PostMapping
    public ResponseEntity<CreateContentResponse> createContent(@RequestBody CreateContentRequest request) {
        log.info("A CreateContentRequest was received : {}", request.toString());
        CreateContentResponse response = contentService.createContent(request);
        log.info("ContentCreateResponse was sent : {}", response.toString());
        return ResponseEntity.ok(response);
    }
}
