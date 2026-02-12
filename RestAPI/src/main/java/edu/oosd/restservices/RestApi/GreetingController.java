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

    private Links generateLinks() {
        return new Links(
                "http://localhost:8080/",
                "http://localhost:8080/greeting",
                "http://localhost:8080/greeting?name=YourName",
                "http://localhost:8080/greeting?name=IMBADGRAH",
                "http://localhost:8080/greeting?name=IMFORBIDDENGRAH"
        );
    }

    @GetMapping("/")
    public ResponseEntity<HomeResponse> home() {
        HomeResponse response = new HomeResponse(
                String.format(template_home, "/"),
                generateLinks()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/greeting")
    public ResponseEntity<HomeResponse> greeting(@RequestParam(defaultValue = "World") String name) {
        String message;
        if (name.equalsIgnoreCase("IMBADGRAH")) {
            message = "Welcome to the bad realm";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new HomeResponse(message, generateLinks()));
        }
        if (name.equalsIgnoreCase("IMFORBIDDENGRAH")) {
            message = "Welcome to the forbidden realm";
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new HomeResponse(message, generateLinks()));
        }
        message = String.format(template, name);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new HomeResponse(message, generateLinks()));
    }

}
