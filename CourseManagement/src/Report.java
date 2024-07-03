import java.util.Date;

public class Report {
    String userName;
    Date createdAt;
    String content;

    public Report(String userName, Date createdAt, String content) {
        this.userName = userName;
        this.createdAt = createdAt;
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

