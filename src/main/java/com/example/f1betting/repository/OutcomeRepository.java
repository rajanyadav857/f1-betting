package com.example.f1betting.repository;

import com.example.f1betting.model.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutcomeRepository extends JpaRepository<Outcome, Long> {
    Outcome findByEventId(String eventId);
}