package xmpl.eyaz.event.storming.service.creator.message;

public class ContentCreatedEventBody {

    private final String createdBy;
    private final String creationDate;
    private final String description;
    private final String eventType;
    private final String aggregateId;
    private final String content;
    private final String link;
    private final String modifiedBy;
    private final String name;
    private final String status;
    private final String version;

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

    public static Builder builder() {
        return new Builder();
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

    private ContentCreatedEventBody(Builder builder) {
        this.createdBy = builder.createdBy;
        this.creationDate = builder.creationDate;
        this.description = builder.description;
        this.eventType = builder.eventType;
        this.aggregateId = builder.aggregateId;
        this.content = builder.content;
        this.link = builder.link;
        this.modifiedBy = builder.modifiedBy;
        this.name = builder.name;
        this.status = builder.status;
        this.version = builder.version;
    }

    public static final class Builder {
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

        public Builder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder creationDate(String creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder aggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Builder modifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public ContentCreatedEventBody build() {
            return new ContentCreatedEventBody(this);
        }
    }
}
