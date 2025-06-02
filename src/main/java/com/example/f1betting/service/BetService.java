package com.example.f1betting.service;

import com.example.f1betting.model.dto.BetRequestDTO;
import com.example.f1betting.model.dto.OutcomeRequestDTO;

public interface BetService {
    void placeBet(BetRequestDTO betRequest);
    void processOutcome(OutcomeRequestDTO outcomeRequest);
}