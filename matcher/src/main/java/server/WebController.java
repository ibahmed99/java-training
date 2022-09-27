package server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping
    public String greeting() {
        return "hello CoinFake user";
    }

}