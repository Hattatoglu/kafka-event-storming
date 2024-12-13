package xmpl.eyaz.event.stroming.service.reader.message;

public class ContentCreatedEventHeader {
    private String agentName;
    private String correlationId;
    private long date;
    private String executorUser;
    private String messageId;
    private int version;

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
}
