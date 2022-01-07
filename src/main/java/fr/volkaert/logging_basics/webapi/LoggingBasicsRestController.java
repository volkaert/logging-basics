package fr.volkaert.logging_basics.webapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

@RestController
@RequestMapping(value = "/")
public class LoggingBasicsRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingBasicsRestController.class);

    private Random random = new Random();

    @GetMapping(value="/operation1/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> operation1(@PathVariable Long id) {
        Instant startOfOperation1 = Instant.now();

        Instant startOfStep1 = Instant.now();
        pause(random.nextInt(50)); // replace this line by the actual code to perform for this step !
        Instant endOfStep1 = Instant.now();
        long durationInMillisOfStep1 = Duration.between(startOfStep1, endOfStep1).toMillis();
        LOGGER.trace("It took {} ms to complete the step1 of operation1 for id {}", durationInMillisOfStep1, id);

        Instant startOfStep2 = Instant.now();
        pause(random.nextInt(50)); // replace this line by the actual code to perform for this step !
        Instant endOfStep2 = Instant.now();
        long durationInMillisOfStep2 = Duration.between(startOfStep2, endOfStep2).toMillis();
        LOGGER.trace("It took {} ms to complete the step2 of operation1 for id {}", durationInMillisOfStep2, id);

        Instant startOfStep3 = Instant.now();
        pause(random.nextInt(50)); // replace this line by the actual code to perform for this step !
        Instant endOfStep3 = Instant.now();
        long durationInMillisOfStep3 = Duration.between(startOfStep3, endOfStep3).toMillis();
        LOGGER.trace("It took {} ms to complete the step3 of operation1 for id {}", durationInMillisOfStep3, id);

        Instant endOfOperation1 = Instant.now();
        long durationInMillisOfOperation1 = Duration.between(startOfOperation1, endOfOperation1).toMillis();
        LOGGER.info("It took {} ms to complete the operation1 for id {}", durationInMillisOfOperation1, id);

        return ResponseEntity.ok("Operation1 completed successfully for id " + id);
    }

    private void pause(long pauseInMillis) {
        try { Thread.sleep(pauseInMillis);} catch (Exception ex) {}
    }
}
