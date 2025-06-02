package com.example.f1betting.controller;

import com.example.f1betting.model.dto.BetRequestDTO;
import com.example.f1betting.model.dto.EventDTO;
import com.example.f1betting.model.dto.OutcomeRequestDTO;
import com.example.f1betting.service.BetService;
import com.example.f1betting.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private BetService betService;

    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> getEvents(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String sessionType,
            @RequestParam(required = false) String country) {
        logger.info("Fetching events with year={}, sessionType={}, country={}", year, sessionType, country);
        List<EventDTO> events = eventService.getEvents(sessionType, year, country);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/bet")
    public ResponseEntity<String> placeBet(@Valid @RequestBody BetRequestDTO requestDTO) {
        logger.info("Placing bet: {}", requestDTO);
        betService.placeBet(requestDTO);
        return ResponseEntity.ok("Bet placed successfully.");
    }

    @PostMapping("/simulate")
    public ResponseEntity<String> simulateEvent(@Valid @RequestBody OutcomeRequestDTO outcomeRequest) {
        logger.info("Simulating event outcome: {}", outcomeRequest);
        betService.processOutcome(outcomeRequest);
        return ResponseEntity.ok("Event simulation completed.");
    }
}