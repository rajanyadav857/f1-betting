package com.example.f1betting.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String eventId;
    private String driverId;
    private double amount;
    private int odds;
    private String status;

    public Bet() {}

    public Bet(User user, String eventId, String driverId, double amount, int odds, String status) {
        this.user = user;
        this.eventId = eventId;
        this.driverId = driverId;
        this.amount = amount;
        this.odds = odds;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public int getOdds() { return odds; }
    public void setOdds(int odds) { this.odds = odds; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}