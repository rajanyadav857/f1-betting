package com.example.f1betting.model.dto;

import java.util.List;

public class EventDTO {
    private String eventId;
    private String sessionType;
    private String country;
    private int year;
    private List<DriverDTO> drivers;

    // Getters and Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getSessionType() { return sessionType; }
    public void setSessionType(String sessionType) { this.sessionType = sessionType; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public List<DriverDTO> getDrivers() { return drivers; }
    public void setDrivers(List<DriverDTO> drivers) { this.drivers = drivers; }
}