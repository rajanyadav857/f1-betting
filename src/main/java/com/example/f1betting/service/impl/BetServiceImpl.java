package com.example.f1betting.service.impl;

import com.example.f1betting.model.dto.BetRequestDTO;
import com.example.f1betting.model.dto.OutcomeRequestDTO;
import com.example.f1betting.model.entity.Bet;
import com.example.f1betting.model.entity.Outcome;
import com.example.f1betting.model.entity.User;
import com.example.f1betting.repository.BetRepository;
import com.example.f1betting.repository.OutcomeRepository;
import com.example.f1betting.repository.UserRepository;
import com.example.f1betting.service.BetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OutcomeRepository outcomeRepository;

    private static final Logger logger = LoggerFactory.getLogger(BetServiceImpl.class);
    private static final String STATUS_PENDING = "PENDING";
    private static final String STATUS_WON = "WON";
    private static final String STATUS_LOST = "LOST";
    private static final List<Integer> ODDS_LIST = Arrays.asList(2, 3, 4);

    @Override
    @Transactional
    public void placeBet(BetRequestDTO request) {
        logger.info("Placing bet for userId={}, eventId={}, driverId={}, amount={}",
                request.getUserId(), request.getEventId(), request.getDriverId(), request.getAmount());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (user.getBalance() < request.getAmount()) {
            logger.warn("User {} has insufficient balance", request.getUserId());
            throw new IllegalStateException("Insufficient balance");
        }

        int odds = ODDS_LIST.get(new Random().nextInt(ODDS_LIST.size()));
        Bet bet = new Bet(user, request.getEventId(), request.getDriverId(), request.getAmount(), odds, STATUS_PENDING);
        user.setBalance(user.getBalance() - request.getAmount());

        userRepository.save(user);
        betRepository.save(bet);
        logger.info("Bet placed successfully for userId={}", request.getUserId());
    }

    @Override
    @Transactional
    public void processOutcome(OutcomeRequestDTO request) {
        logger.info("Processing outcome for eventId={}, winningDriverId={}", request.getEventId(), request.getWinningDriverId());
        Outcome outcome = new Outcome(request.getEventId(), request.getWinningDriverId());
        outcomeRepository.save(outcome);

        List<Bet> bets = betRepository.findByEventId(request.getEventId());
        for (Bet bet : bets) {
            if (bet.getDriverId().equals(request.getWinningDriverId())) {
                bet.setStatus(STATUS_WON);
                double prize = bet.getAmount() * bet.getOdds();
                User user = bet.getUser();
                user.setBalance(user.getBalance() + prize);
                userRepository.save(user);
                logger.info("User {} won bet on event {}", user.getId(), request.getEventId());
            } else {
                bet.setStatus(STATUS_LOST);
            }
            betRepository.save(bet);
        }
        logger.info("Outcome processing completed for eventId={}", request.getEventId());
    }
}
