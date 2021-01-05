package pl.janeck.tempsaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.janeck.tempsaver.service.MeasurementService;

/**
 * ... comment class...
 *
 * @author JKN janeck@protonmail.com
 * @since 02 January 2021 @ 21:56
 */

@Component
public class App {


    private MeasurementService measurementService;
    @Value("${spring.city}")
    private String city;

    @Autowired
    public App(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    //    @Scheduled(cron = "0 18 * * * ?")
    @Scheduled(fixedDelay = 3000)

    public void init() {
        measurementService.addMeasurement(city);
    }
}
