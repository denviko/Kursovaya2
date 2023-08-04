package ru.hogwarts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/info")
@RestController
public class InfoController {
    private final int port;

    public InfoController(@Value("${server.port}") int port) {
        this.port = port;
    }
    @GetMapping("/setPort")
    public int getPort(){
        return port;
    }
}
