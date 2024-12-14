package xmpl.eyaz.event.storming.app.contract.contentcreated;

public class ContentCreatedEvent {

    private final ContentCreatedEventHeader contentCreatedEventHeader;
    private final ContentCreatedEventBody contentCreatedEventBody;

    public ContentCreatedEvent(ContentCreatedEventHeader contentCreatedEventHeader, ContentCreatedEventBody contentCreatedEventBody) {
        this.contentCreatedEventHeader = contentCreatedEventHeader;
        this.contentCreatedEventBody = contentCreatedEventBody;
    }

    public ContentCreatedEventHeader getContentCreatedEventHeader() {
        return contentCreatedEventHeader;
    }

    public ContentCreatedEventBody getContentCreatedEventBody() {
        return contentCreatedEventBody;
    }
}
