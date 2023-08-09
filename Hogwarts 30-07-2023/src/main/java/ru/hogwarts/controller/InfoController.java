package ru.hogwarts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.springframework.util.StopWatch.*;
import static ru.hogwarts.service.StudentService.logger;

@RequestMapping("/info")
@RestController
public class InfoController {
    private final static Logger logger = LoggerFactory.getLogger(InfoController.class);
    private final int port;

    public InfoController(@Value("${server.port}") int port) {
        this.port = port;
    }

    @GetMapping("/setPort")
    public int getPort() {
        return port;
    }

    @GetMapping("/testPrimitive")
    public int testPrimitive() {
        StopWatch sw = StopWatch.createStarted();
        int sum = IntStream.iterate(1, a -> a + 1).limit(1000000).sum().
        logger.info("Primitive stream completed in {}", sw);
        return sum;

    }
    @GetMapping("/testPrimitiveParallel")
    public int testPrimitiveParallel() {
        StopWatch sw = StopWatch.createStarted();
        int sum = IntStream.iterate(1, a -> a + 1).limit(1000000).parallel().sum();
        logger.info("Primitive parallel stream completed in {}", sw);
        return sum;

    }
    @GetMapping("/testBoxed")
    public int testBoxed() {
        StopWatch sw = StopWatch.createStarted();
        int sum = Stream.iterate(1, a -> a + 1).limit(1000000).reduce(0, Integer::sum);
        logger.info("Boxed stream completed in {}", sw);
        return sum;
    }

}
