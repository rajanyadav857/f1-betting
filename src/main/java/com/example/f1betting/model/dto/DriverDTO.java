package com.example.f1betting.model.dto;

public class DriverDTO {
    private String driverId;
    private String fullName;
    private int odds;

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getOdds() { return odds; }
    public void setOdds(int odds) { this.odds = odds; }
}
