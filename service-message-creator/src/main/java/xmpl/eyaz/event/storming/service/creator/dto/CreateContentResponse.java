package xmpl.eyaz.event.storming.service.creator.dto;

public class CreateContentResponse {

    private String username;
    private String link;

    public CreateContentResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "CreateContentResponse {" +
                "username='" + username + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
