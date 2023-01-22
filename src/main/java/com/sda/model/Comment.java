package com.sda.model;

import lombok.Data;
import javax.persistence.*;


@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String text;

    public Comment() {

    }

    public Comment(Event event, User user, String text) {
        this.event = event;
        this.user = user;
        this.text = text;
    }
}
