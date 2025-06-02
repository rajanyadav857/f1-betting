package com.example.f1betting.model.dto;

import javax.validation.constraints.NotBlank;

public class OutcomeRequestDTO {
    @NotBlank(message = "Event ID is required")
    private String eventId;

    @NotBlank(message = "Winning driver ID is required")
    private String winningDriverId;

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getWinningDriverId() { return winningDriverId; }
    public void setWinningDriverId(String winningDriverId) { this.winningDriverId = winningDriverId; }
}