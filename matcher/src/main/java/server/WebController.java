package server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Matcher.MatcherApplication;
import server.Matcher.Trade;

import java.util.ArrayList;

@RestController
@Slf4j
public class WebController {

    @Autowired
    MatcherApplication matcherApplication;

    @GetMapping
    public String greeting() {
        return "hello CoinFake user";
    }

    @GetMapping("/cd")
    public String test() {
        return "CI/CD works!";
    }


}