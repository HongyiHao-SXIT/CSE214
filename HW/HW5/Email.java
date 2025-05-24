package HW.HW5;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Email implements Serializable, Comparable<Email> {
    private String subject;
    private String body;
    private String sender;
    private LocalDateTime timestamp;

    public Email(String subject, String body, String sender) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
    }

    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public String getSender() { return sender; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public int compareTo(Email other) {
        return other.timestamp.compareTo(this.timestamp); // 按时间降序排序
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] 来自: " + sender + " 主题: " + subject;
    }
}
