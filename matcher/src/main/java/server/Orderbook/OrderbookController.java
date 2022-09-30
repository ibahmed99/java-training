package server.Orderbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.Matcher.MatcherApplication;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/order")
public class OrderbookController {

    private final OrderbookService orderbookService;
    @Autowired
    private MatcherApplication matcherApplication;

    @Autowired
    public OrderbookController(OrderbookService orderbookService) {
        this.orderbookService = orderbookService;
    }

    @GetMapping
    public ResponseEntity<PrivateOrderbook> getOrderbook(@RequestParam(defaultValue = "null") String user) {
        if (user.equals("null")) return ResponseEntity.ok(orderbookService.getPrivateOrderbook());
        else return ResponseEntity.ok(orderbookService.specificUsersOrderbook(user));
    }

    @PostMapping
    public ResponseEntity<PrivateOrderbook> processNewOrder(@RequestBody @Valid Order newOrder) {
        orderbookService.processNewOrder(newOrder);
        return ResponseEntity.ok(matcherApplication.getPrivateExistingOrders());
    }
}
