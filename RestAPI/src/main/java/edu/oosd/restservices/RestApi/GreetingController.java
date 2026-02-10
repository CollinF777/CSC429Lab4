package edu.oosd.restservices.RestApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private static final String template_home = "Hello! You are at %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format(template_home, "/"));
    }

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(defaultValue = "World") String name) {
        Greeting bad = new Greeting(counter.incrementAndGet(), "Welcome to the bad realm");
        if (name.equalsIgnoreCase("IM BAD GRAH")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bad);
        }
        Greeting forbid = new Greeting(counter.incrementAndGet(), "Welcome to the forbidden realm");
        if (name.equalsIgnoreCase("IM FORBIDDEN GRAH")) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(forbid);
        }
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(greeting);
    }

}
