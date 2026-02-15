package edu.oosd.restservices.RestApi;

import org.apache.coyote.Response;
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
    public ResponseEntity<Greeting> home(){
        Greeting body = new Greeting(counter.incrementAndGet(), "This is the homepage! Here is a list of pages you can visit! http://localhost:8080/ http://localhost:8080/greeting http://localhost:8080/greeting?name=YourName http://localhost:8080/greeting?name=badrequest http://localhost:8080/greeting?name=forbidden");
        return new ResponseEntity<Greeting>(body, HttpStatus.OK);
    }

    @GetMapping("/greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(defaultValue = "World") String name) {
        if(name.equalsIgnoreCase("badrequest")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Greeting(counter.incrementAndGet(), "This is a test error page: 400 BAD REQUEST"));
        }
        if(name.equalsIgnoreCase("forbidden")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Greeting(counter.incrementAndGet(), "This is a test error page: 403 FORBIDDEN"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new Greeting(counter.incrementAndGet(), String.format(template, name)));
    }

}