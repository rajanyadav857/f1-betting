package com.example.f1betting.repository;

import com.example.f1betting.model.entity.Bet;
import com.example.f1betting.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByEventId(String eventId);
    List<Bet> findByUser(User user);
}