package pl.janeck.tempsaver.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.janeck.tempsaver.model.Measurement;
import pl.janeck.tempsaver.repository.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * ... comment class...
 *
 * @author JKN janeck@protonmail.com
 * @since 02 January 2021 @ 18:22
 */


@Slf4j
@Service
public class MeasurementService {

    private static final String WOEID_URL = "https://www.metaweather.com/api/location/";
    private static final String LOCATION_URL = "https://www.metaweather.com/api/location/search/?query=";
    private final RestTemplate restTemplate;
    private final MeasurementRepository measurementRepository;


    @Autowired
    public MeasurementService(RestTemplate restTemplate, MeasurementRepository measurementRepository) {
        this.restTemplate = restTemplate;
        this.measurementRepository = measurementRepository;
    }

    public Optional<Measurement> addMeasurement(String location) {

        Optional<Double> temp = getTemp(location);
        double temperature = 0.0;

        if (temp.isPresent()) {
            temperature = temp.get();
        }
        log.info(String.valueOf(temperature));
        Measurement measurement = new Measurement(location, temperature, LocalDateTime.now());
        measurementRepository.save(measurement);
        return Optional.of(measurement);
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();

    }


    private Optional<Double> getTemp(String location) {
        int woeid = getWoeid(location).orElse(0);
        Optional<JsonNode> jsonNode = Optional.ofNullable(restTemplate.getForObject(WOEID_URL + woeid, JsonNode.class));

        try {
            return Optional.of(jsonNode.get().get("consolidated_weather").get(0).get("the_temp").asDouble());

        } catch (Exception exception) {
            log.error("getMeasurement() error " + exception);
            return Optional.empty();
        }
    }


    private Optional<Integer> getWoeid(String location) {
        Optional<JsonNode> jsonNode = Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(LOCATION_URL + location, JsonNode[].class))).findFirst();
        try {
            return Optional.of(jsonNode.get().get("woeid").asInt());
        } catch (Exception exception) {
            log.error("getWoeid() error " + exception);
            return Optional.empty();
        }
    }
}



