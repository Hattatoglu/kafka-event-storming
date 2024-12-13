package xmpl.eyaz.event.storming.service.creator.message;

public class ContentCreatedEventHeader {
    private final String agentName;
    private final String correlationId;
    private final long date;
    private final String executorUser;
    private final String messageId;
    private final int version;

    public String getAgentName() {
        return agentName;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public long getDate() {
        return date;
    }

    public String getExecutorUser() {
        return executorUser;
    }

    public String getMessageId() {
        return messageId;
    }

    public int getVersion() {
        return version;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "ContentCreatedEventHeader{" +
                "agentName='" + agentName + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", date=" + date +
                ", executorUser='" + executorUser + '\'' +
                ", messageId='" + messageId + '\'' +
                ", version=" + version +
                '}';
    }

    private ContentCreatedEventHeader(Builder builder) {
        this.agentName = builder.agentName;
        this.correlationId = builder.correlationId;
        this.date = builder.date;
        this.executorUser = builder.executorUser;
        this.messageId = builder.messageId;
        this.version = builder.version;
    }

    public static final class Builder {
        private String agentName;
        private String correlationId;
        private long date;
        private String executorUser;
        private String messageId;
        private int version;

        public Builder agentName(String agentName) {
            this.agentName = agentName;
            return this;
        }

        public Builder correlationId(String correlationId) {
            this.correlationId = correlationId;
            return this;
        }

        public Builder date(long date) {
            this.date = date;
            return this;
        }

        public Builder executorUser(String executorUser) {
            this.executorUser = executorUser;
            return this;
        }

        public Builder messageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder version(int version) {
            this.version = version;
            return this;
        }

        public ContentCreatedEventHeader build() {
            return new ContentCreatedEventHeader(this);
        }
    }
}
