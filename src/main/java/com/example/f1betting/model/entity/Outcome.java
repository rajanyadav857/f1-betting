package com.example.f1betting.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "outcomes")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;
    private String winningDriverId;

    public Outcome() {}

    public Outcome(String eventId, String winningDriverId) {
        this.eventId = eventId;
        this.winningDriverId = winningDriverId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getWinningDriverId() { return winningDriverId; }
    public void setWinningDriverId(String winningDriverId) { this.winningDriverId = winningDriverId; }
}