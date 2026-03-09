package edu.oosd.restservices.RestApi;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template      = "Hello, %s!";
    private static final String template_home = "Hello! You are at %s";
    private final AtomicLong counter = new AtomicLong();

    // GET http://localhost:8080/
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Greeting> home() {
        Greeting greeting = new Greeting(
                counter.incrementAndGet(),
                String.format(template_home, "/")
        );

        // Self link
        greeting.add(linkTo(methodOn(GreetingController.class).home()).withSelfRel());

        // Link to the /greeting endpoint
        greeting.add(linkTo(methodOn(GreetingController.class)
                .greeting("World")).withRel("greeting"));

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

    // GET http://localhost:8080/greeting?name=YourName
    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(
            @RequestParam(defaultValue = "World") String name) {

        Greeting greeting = new Greeting(
                counter.incrementAndGet(),
                String.format(template, name)
        );

        // Self link
        greeting.add(linkTo(methodOn(GreetingController.class)
                .greeting(name)).withSelfRel());

        // Link back to home
        greeting.add(linkTo(methodOn(GreetingController.class)
                .home()).withRel("home"));

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}