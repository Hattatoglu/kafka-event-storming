package xmpl.eyaz.event.storming.service.creator.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public class CreateContentRequest {

    private String userId;
    private String username;
    private String content;
    private int version;
    private ZonedDateTime creationDate;
    private UUID aggregateId;

    public CreateContentRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    @Override
    public String toString() {
        return "CreateContentRequest{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", version=" + version +
                ", creationDate=" + creationDate +
                ", aggregateId=" + aggregateId +
                '}';
    }
}
