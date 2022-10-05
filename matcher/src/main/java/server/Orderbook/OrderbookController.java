package server.Orderbook;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.Matcher.MatcherApplication;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "order")
@Slf4j
public class OrderbookController {

    private final OrderbookService orderbookService;
    @Autowired
    private MatcherApplication matcherApplication;

    @Autowired
    public OrderbookController(OrderbookService orderbookService) {
        this.orderbookService = orderbookService;
    }

    @GetMapping
    public ResponseEntity<PrivateOrderbook> getOrderbook(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestParam(defaultValue = "public") String type) {
        log.info("token: {}", authHeader);
        if (type.equals("public")) return ResponseEntity.ok(orderbookService.getPrivateOrderbook());
        else {
            String token = authHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String user = decodedJWT.getSubject();
            return ResponseEntity.ok(orderbookService.specificUsersOrderbook(user));
        }
    }

    @PostMapping
    public ResponseEntity<PrivateOrderbook> processNewOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestBody @Valid Order newOrder) {
        String token = authHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String user = decodedJWT.getSubject();
        newOrder.setAccount(user);
        System.out.println(newOrder);
        orderbookService.processNewOrder(newOrder);
        return ResponseEntity.ok(matcherApplication.getPrivateExistingOrders());
    }

    @GetMapping("/aggregated")
    public ResponseEntity<PrivateOrderbook> getAggregatedOrderbook(@RequestParam Double aggregationValue) {
        return ResponseEntity.ok(orderbookService.aggregateOrderbook(aggregationValue));
    }
}
