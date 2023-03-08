package main.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Message implements Serializable {
    public Message( String text, LocalDateTime dateTime, User user) {
        this.text = text;
        this.dateTime = dateTime;
        this.user = user;
    }

    @Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

private String text;

@ManyToOne(fetch = FetchType.EAGER,optional = false)
@JoinColumn (name="user_id", nullable = false)
private User user;
private LocalDateTime dateTime;

    public Message() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " " + user.getName() +" id:" + user.getId() + " : " +text;
    }
}
