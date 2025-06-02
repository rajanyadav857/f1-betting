package com.example.f1betting.service;

import com.example.f1betting.model.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getEvents(String sessionType, String year, String country);
}