package com.example.f1betting.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BetRequestDTO {
    private Long userId;

    @NotBlank(message = "Event ID is required")
    private String eventId;

    @NotBlank(message = "Driver ID is required")
    private String driverId;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Bet amount must be at least 1")
    private Double amount;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount;}
}

