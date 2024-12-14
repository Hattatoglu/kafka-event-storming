package xmpl.eyaz.event.storming.app.contract.contentcreated;

import xmpl.eyaz.event.storming.app.contract.base.EventHeader;

import java.util.ArrayList;
import java.util.List;

public class ContentCreatedEventHeader {

    private final EventHeader agentName;
    private final EventHeader correlationId;
    private final EventHeader date;
    private final EventHeader executorUser;
    private final EventHeader messageId;
    private final EventHeader version;

    private List<EventHeader> headerList;

    public EventHeader getAgentName() {
        return agentName;
    }

    public EventHeader getCorrelationId() {
        return correlationId;
    }

    public EventHeader getDate() {
        return date;
    }

    public EventHeader getExecutorUser() {
        return executorUser;
    }

    public EventHeader getMessageId() {
        return messageId;
    }

    public EventHeader getVersion() {
        return version;
    }

    public List<EventHeader> getAllHeaders() {
        return headerList;
    }

    @Override
    public String toString() {
        return "ContentCreatedEventHeader {" +
                "agentName=" + agentName +
                ", correlationId=" + correlationId +
                ", date=" + date +
                ", executorUser=" + executorUser +
                ", messageId=" + messageId +
                ", version=" + version +
                ", headerList=" + headerList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public ContentCreatedEventHeader(Builder builder) {
        this.agentName = builder.agentName;
        this.correlationId = builder.correlationId;
        this.date = builder.date;
        this.executorUser = builder.executorUser;
        this.messageId = builder.messageId;
        this.version = builder.version;
        this.headerList = builder.headerList;
    }

    public static final class Builder {
        private EventHeader agentName;
        private EventHeader correlationId;
        private EventHeader date;
        private EventHeader executorUser;
        private EventHeader messageId;
        private EventHeader version;
        private List<EventHeader> headerList;

        public Builder() {
            headerList = new ArrayList<>();
        }

        public Builder agentName(String agentName) {
            this.agentName = new EventHeader("agentName", agentName);
            headerList.add(this.agentName);
            return this;
        }

        public Builder correlationId(String correlationId) {
            this.correlationId = new EventHeader("correlationId", correlationId);
            headerList.add(this.correlationId);
            return this;
        }

        public Builder date(String date) {
            this.date = new EventHeader("date", date);
            headerList.add(this.date);
            return this;
        }

        public Builder executorUser(String executorUser) {
            this.executorUser = new EventHeader("executorUser", executorUser);
            headerList.add(this.executorUser);
            return this;
        }

        public Builder messageId(String messageId) {
            this.messageId = new EventHeader("messageId", messageId);
            headerList.add(this.messageId);
            return this;
        }

        public Builder version(String version) {
            this.version = new EventHeader("version", version);
            headerList.add(this.version);
            return this;
        }

        public ContentCreatedEventHeader build() {
            return new ContentCreatedEventHeader(this);
        }
    }
}
