package pl.janeck.tempsaver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.janeck.tempsaver.model.Measurement;
import pl.janeck.tempsaver.service.MeasurementService;

import java.util.List;

/**
 * ... comment class...
 *
 * @author JKN janeck@protonmail.com
 * @since 02 January 2021 @ 21:23
 */

@RestController
public class MeasurementController {


    private final MeasurementService measurementService;


    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }


    @GetMapping
    public ResponseEntity<List<Measurement>> getAllMeasurements() {
        return new ResponseEntity<>(measurementService.getAllMeasurements(), HttpStatus.OK);
    }

}
