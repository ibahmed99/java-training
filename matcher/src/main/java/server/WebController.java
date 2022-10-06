package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Matcher.MatcherApplication;
import server.Matcher.Trade;

import java.util.ArrayList;

@RestController
public class WebController {

    @Autowired
    MatcherApplication matcherApplication;

    @GetMapping
    public String greeting() {
        return "hello CoinFake user";
    }

    @GetMapping("/trades")
    public ResponseEntity<ArrayList<Trade>> getTrades() {
        System.out.println(matcherApplication.getTrades());
        return ResponseEntity.ok(matcherApplication.getTrades());
    }

}