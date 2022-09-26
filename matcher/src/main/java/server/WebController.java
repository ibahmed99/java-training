package server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/greeting")
    public String greeting() {
        return "Greetings from Spring Boot!";
    }

}