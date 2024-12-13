package xmpl.eyaz.event.stroming.service.reader.message;

public class ContentCreatedEventBody {

    private String createdBy;
    private String creationDate;
    private String description;
    private String eventType;
    private String aggregateId;
    private String content;
    private String link;
    private String modifiedBy;
    private String name;
    private String status;
    private String version;

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public String getEventType() {
        return eventType;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "ContentCreatedEventBody{" +
                "createdBy='" + createdBy + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", description='" + description + '\'' +
                ", eventType='" + eventType + '\'' +
                ", aggregateId='" + aggregateId + '\'' +
                ", content='" + content + '\'' +
                ", link='" + link + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
