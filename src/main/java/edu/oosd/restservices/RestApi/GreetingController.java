package edu.oosd.restservices.RestApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Greetings, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping("/greeting")
    ResponseEntity<?> greeting(@RequestParam(defaultValue = "World") String name) {
        return ResponseEntity.ok().body(new Greeting(counter.incrementAndGet(), String.format(template, name)));
    }

    @GetMapping("/")
    ResponseEntity<?> home() {
        return ResponseEntity //
                .ok().body(new Routes(handlerMapping));
    }
}
