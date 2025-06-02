package com.example.f1betting.service.impl;

import com.example.f1betting.model.dto.DriverDTO;
import com.example.f1betting.model.dto.EventDTO;
import com.example.f1betting.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    @Value("${openf1.api.url}")
    private String API_URL;

    private final RestTemplate restTemplate;

    private static final List<Integer> ODDS = Collections.unmodifiableList(Arrays.asList(2, 3, 4));
    private static final int MOCK_DRIVER_COUNT = 5;
    private final Random random = new Random();

    public EventServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<EventDTO> getEvents(String sessionType, String year, String country) {
        logger.info("Fetching events with sessionType={}, year={}, country={}", sessionType, year, country);
        List<EventDTO> result = new ArrayList<>();
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL);

            if (country != null && !country.isEmpty()) {
                builder.queryParam("country_name", country);
            }
            if (sessionType != null && !sessionType.isEmpty()) {
                builder.queryParam("session_name", sessionType);
            }
            if (year != null && !year.isEmpty()) {
                builder.queryParam("year", year);
            }

            String finalUrl = builder.toUriString();
            logger.debug("Calling external API: {}", finalUrl);

            List<Map<String, Object>> sessions = restTemplate.getForObject(finalUrl, List.class);

            if (sessions != null) {
                for (Map<String, Object> session : sessions) {
                    EventDTO dto = new EventDTO();
                    dto.setEventId(String.valueOf(session.get("session_key")));
                    dto.setSessionType((String) session.get("session_type"));
                    dto.setYear(Integer.parseInt(session.get("year").toString()));
                    dto.setCountry((String) session.get("country_name"));
                    dto.setDrivers(mockDrivers());
                    result.add(dto);
                }
            }
            logger.info("Fetched {} events", result.size());
        } catch (Exception ex) {
            logger.error("Error fetching events from API: {}", ex.getMessage(), ex);
        }
        return result;
    }

    private List<DriverDTO> mockDrivers() {
        List<DriverDTO> drivers = new ArrayList<>(MOCK_DRIVER_COUNT);
        for (int i = 1; i <= MOCK_DRIVER_COUNT; i++) {
            DriverDTO d = new DriverDTO();
            d.setDriverId("DRV" + i);
            d.setFullName("Driver " + i);
            d.setOdds(ODDS.get(random.nextInt(ODDS.size())));
            drivers.add(d);
        }
        return drivers;
    }
}
